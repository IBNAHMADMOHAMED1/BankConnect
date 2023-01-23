package ma.bankconnect.api;

import lombok.RequiredArgsConstructor;
import ma.bankconnect.dto.TransactionRequest;
import ma.bankconnect.entity.Account;
import ma.bankconnect.entity.AccountOperation;
import ma.bankconnect.entity.Transaction;
import ma.bankconnect.service.account.AccountServiceImpl;
import ma.bankconnect.service.operation.OperationServiceImpl;
import ma.bankconnect.service.transaction.TransactionServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/transactions")
public class TransactionResource {
    private final TransactionServiceImpl transactionService;
    private final AccountServiceImpl accountService;
    private final OperationServiceImpl operationService;

    @GetMapping("all/{customerId}")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_CUSTOMER')")
    public ResponseEntity<List<Transaction>> getAllTransactions(@PathVariable("customerId") Long customerId) {
        List<Transaction> transactions = transactionService.getAllTransactions(customerId);
        return ResponseEntity.ok().body(transactions);
    }
    @PostMapping("save")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_CUSTOMER')")
    public ResponseEntity<Transaction> saveTransaction(@RequestBody TransactionRequest transactionRequest) {
        AccountOperation operation = new AccountOperation();
        operation.setAmount(transactionRequest.getAmount());
        Account accountFrom = accountService.findAccountByAccountNumber(transactionRequest.getAccountNumberFrom());
        if (transactionRequest.getType().equals("DEPOSIT")) {
            operation.setAccountTo(accountFrom);
            operation.setType("DEPOSIT");
        } else if (transactionRequest.getType().equals("WITHDRAW")) {
            Account accountTo = accountService.findAccountByAccountNumber(transactionRequest.getAccountNumberTo());
            operation.setAccountFrom(accountFrom);
            operation.setAccountTo(accountTo);
            operation.setType("WITHDRAW");
        }
        Transaction transaction = null;
        AccountOperation savedOperation = operationService.save(operation);


        return ResponseEntity.ok().body(transaction);
    }


}
