package ma.bankconnect.api;

import lombok.RequiredArgsConstructor;
import ma.bankconnect.entity.Account;
import ma.bankconnect.service.account.AccountServiceImpl;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/account")
public class AccountResource {

    private final AccountServiceImpl accountService;

    @PostMapping("create")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_CUSTOMER')")
    public ResponseEntity<Account> createAccount(@RequestBody Account account) {
        System.out.println("cousomer id: " + account.getCustomer().getId());
        Account newAccount = accountService.createAccount(account);
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(newAccount);
    }

}
