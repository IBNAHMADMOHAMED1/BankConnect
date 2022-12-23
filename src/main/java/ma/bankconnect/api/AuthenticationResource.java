package ma.bankconnect.api;

import lombok.RequiredArgsConstructor;
import ma.bankconnect.config.CloudinaryConfig;
import ma.bankconnect.dao.UserDao;
import ma.bankconnect.dto.AuthenticationRequest;
import ma.bankconnect.dto.CustomerRequest;
import ma.bankconnect.entity.Customer;
import ma.bankconnect.entity.Sms;
import ma.bankconnect.service.CustomerServiceImpl;
import ma.bankconnect.service.sms.SmsServiceImpl;
import ma.bankconnect.utils.JwtUtils;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationResource {
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final UserDetailsService userDetailsService;
    private final UserDao userDao;
    private final CustomerServiceImpl customerService;
    private final CloudinaryConfig cloud;
    private final SmsServiceImpl smsService;

    @PostMapping("/authenticate")
    public ResponseEntity<String> login(@RequestBody AuthenticationRequest authenticationRequest) {
        userDao.setWho(authenticationRequest.getWho());
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.getEmail(),
                        authenticationRequest.getPassword()
                )
        );
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getEmail());
        if (userDetails != null) {
            return ResponseEntity.ok().body(jwtUtils.generateToken(userDetails));
        }
        return ResponseEntity.badRequest().body("Bad credentials");
    }
    @RequestMapping(value = "/register", method = RequestMethod.POST, consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<String> register(@Validated @ModelAttribute CustomerRequest customerRequest)  {
        if (customerService.findByEmail(customerRequest.getEmail()) != null) {
            return ResponseEntity.badRequest().body("Email already exists");
        }
        Customer customer = new Customer();
        customer.setNom(customerRequest.getNom());
        customer.setAddress(customerRequest.getAdresse());
        customer.setEmail(customerRequest.getEmail());
        customer.setPassword(customerRequest.getPassword());
        customer.setTelephone(customerRequest.getTelephone());
        customer.setCin(customerRequest.getCin());
        MultipartFile file = customerRequest.getFile();
        String url = cloud.uploadImage(file);
        customer.setCinImage(url);
        Customer newCustomer =  customerService.save(customer);
        if (newCustomer != null) {

             Sms smsSent = smsService.save(newCustomer.getId()) ;
             ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body("{\"message\": \"Customer created successfully\", \"Code sms\": \""+smsSent.getCode()+"\"}");
        }
        return ResponseEntity.badRequest().body("Customer not created");
    }
    @GetMapping("/verify/{id}/{code}")
    public ResponseEntity<String> verify(@PathVariable("id") Long id, @PathVariable("code") String code) {
        Customer customer = customerService.findById(id);
        if (customer != null) {
            Sms sms = smsService.findByCustomerId(id);
            if (sms != null) {
                if (sms.getCode().equals(code) && smsService.checkExpiration(sms.getSendDate())) {
                    customer.setVerified(true);
                    customerService.save(customer);
                    return ResponseEntity.ok().body("Customer verified successfully");
                }
            }
        }
        return ResponseEntity.badRequest().body("Customer not verified");
    }
    @GetMapping("/resend/{id}")
    public ResponseEntity<String> resend(@PathVariable("id") Long id) {
        Customer customer = customerService.findById(id);
        if (customer != null) {
            Sms sms = smsService.findByCustomerId(id);
            if (sms != null) {
                smsService.deleteSms(sms.getId());
                Sms smsSent = smsService.save(id);
                return ResponseEntity.ok().body("{\"message\": \"Code sms resent successfully\", \"Code sms\": \""+smsSent.getCode()+"\"}");
            }
        }
        return ResponseEntity.badRequest().body("Customer not found");
    }
}
