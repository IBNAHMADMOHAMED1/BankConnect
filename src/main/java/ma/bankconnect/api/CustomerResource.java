package ma.bankconnect.api;

import lombok.RequiredArgsConstructor;
import ma.bankconnect.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("customers")
@RequiredArgsConstructor
public class CustomerResource {
    private final CustomerService customerService;

    @GetMapping("all")
    public ResponseEntity<String> getAllCustomers() {
        System.out.println("---------1111111111111111------");
        return ResponseEntity.ok("hhhhhhhhhhhhhhh");
    }
}
