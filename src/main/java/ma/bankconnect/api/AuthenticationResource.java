package ma.bankconnect.api;

import lombok.RequiredArgsConstructor;
import ma.bankconnect.dao.UserDao;
import ma.bankconnect.dto.AuthenticationRequest;
import ma.bankconnect.dto.CustomerRequest;
import ma.bankconnect.entity.Customer;
import ma.bankconnect.service.CustomerServiceImpl;
import ma.bankconnect.utils.JwtUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationResource {
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final UserDetailsService userDetailsService;
    private final UserDao userDao;
    private final CustomerServiceImpl customerService;

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
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody CustomerRequest customerRequest) {
        Customer customer = new Customer();
        customer.setName(customerRequest.getNom());
        customer.setAddress(customerRequest.getAdresse());
        customer.setEmail(customerRequest.getEmail());
        customer.setPassword(customerRequest.getPassword());
        customer.setPhoneNumber(customerRequest.getTelephone());
        customer.setCin(customerRequest.getCin());
        customer.setCinImage(customerRequest.getCinImage());

        Customer newCustomer =  customerService.save(customer);
        if (newCustomer != null) {
            return ResponseEntity.ok().body("Customer created");
        }
        return ResponseEntity.badRequest().body("Customer not created");
    }
}
