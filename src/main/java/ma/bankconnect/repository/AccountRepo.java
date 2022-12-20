package ma.bankconnect.repository;

import ma.bankconnect.entity.Account;
import ma.bankconnect.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface AccountRepo extends JpaRepository<Account, Long> {
    Account findByAccountNumber(String accountNumber);
    Account findByCustomer(Customer customer);
    List<Account> findAllByCustomer(Customer customer);
}

