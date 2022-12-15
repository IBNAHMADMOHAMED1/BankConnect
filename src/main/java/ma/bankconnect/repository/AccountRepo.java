package ma.bankconnect.repository;

import ma.bankconnect.entity.Account;
import ma.bankconnect.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountRepo extends JpaRepository<Account, Long> {
    Account findByAccountNumber(String accountNumber);
    Account findByCustomer(Customer customer);
    List<Account> findAllByCustomer(Customer customer);
}

