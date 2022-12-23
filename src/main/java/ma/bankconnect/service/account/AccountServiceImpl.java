package ma.bankconnect.service.account;

import ma.bankconnect.config.JwtAuthFilter;
import ma.bankconnect.entity.Account;
import ma.bankconnect.repository.account.AccountsRepo;
import ma.bankconnect.service.CustomerServiceImpl;
import ma.bankconnect.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {
    private final AccountsRepo accountRepo;
    private final JwtAuthFilter jwtAuthFilter;
    private final JwtUtils jwtUtils;
    private final CustomerServiceImpl customerService;

    @Autowired
    public AccountServiceImpl(AccountsRepo accountRepo, JwtAuthFilter jwtAuthFilter, JwtUtils jwtUtils, CustomerServiceImpl customerService) {
        this.accountRepo = accountRepo;
        this.jwtAuthFilter = jwtAuthFilter;
        this.jwtUtils = jwtUtils;
        this.customerService = customerService;
    }

    @Override
    public Account createAccount(Account account) {
        Long customerId = account.getCustomer().getId();
        Boolean hasAccount = hasAccount(customerId);
        if (hasAccount) {
            System.out.println("customer already has an account");
            throw new RuntimeException("Problème lors de la création du compte");
        }
        account.setCustomer(customerService.findById(customerId));
        if (account.getAccountType().equals("Standard")) {
            account.setDailyWithdrawalLimit(5000.0);
            account.setAnnualWithdrawalLimit(100000.0);
            account.setAccountNumber(generateAccountNumber("ST"));
        }
        if (account.getAccountType().equals("Professionnel")) {
            account.setDailyWithdrawalLimit(10000.0);
            account.setAnnualWithdrawalLimit(200000.0);
            account.setAccountNumber(generateAccountNumber("PR"));
        }
        System.out.println(account);
        return accountRepo.save(account);
    }
    public String generateAccountNumber(String prefix) {
       String accountN = prefix + (int) (Math.random() * 1000000000);
       if (!isValidAccountNumber(accountN)) {
           return accountN;
       } else {
           return generateAccountNumber(prefix);
       }
    }
    @Override
    public Account findAccountByAccountNumber(String accountNumber) {
        return accountRepo.findAccountsByAccountNumberEquals(accountNumber);
    }

    @Override
    public Account findAccountByCustomerId(Long id) {
        return accountRepo.findAccountByCustomerId(id);
    }
    // method to check if the account number is valid
    public boolean isValidAccountNumber(String accountNumber) {
        return accountRepo.findAccountsByAccountNumberEquals(accountNumber) != null;
    }
    // method to check if the customer has an account
    public boolean hasAccount(Long id) {
        return accountRepo.findAccountByCustomerId(id) != null;
    }
    // check if the customerid is the same in token and in the request
    public boolean isSameCustomer( Long id) {
        String token = jwtAuthFilter.getToken();
        String customerEmail = jwtUtils.extractUsername(token);
        Long customerId = customerService.findIdCustomerByEmail(customerEmail);
        return customerId.equals(id);
    }
}
