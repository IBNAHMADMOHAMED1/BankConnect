package ma.bankconnect.api;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/customers")
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
public class CustomerResource {
    //private final CustomerService customerService;
    @GetMapping("all")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_CUSTOMER')")
    public ResponseEntity<String> getAllCustomers(  ) {
        System.out.println("---------1111111111111111------");
        return ResponseEntity.ok("hhhhhhhhhhhhhhh");
    }
}
