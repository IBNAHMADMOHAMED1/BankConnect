package ma.bankconnect.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/clients")
public class CustomerResource {
    //private final CustomerService customerService;

    @PostMapping("all")
    public ResponseEntity<String> getAllCustomers(  ) {
        System.out.println("---------1111111111111111------");
        return ResponseEntity.ok("hhhhhhhhhhhhhhh");
    }
}
