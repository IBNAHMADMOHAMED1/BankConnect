package ma.bankconnect.service.account;

import ma.bankconnect.entity.Account;

public interface AccountService {
    Account createAccount(Account account);
    Account findAccountByAccountNumber(String accountNumber);
    Account findAccountByCustomerId(Long id);
}
