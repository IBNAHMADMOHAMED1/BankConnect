package ma.bankconnect.repository.account;

import ma.bankconnect.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepo extends JpaRepository<Account, Long> {
    Account findAccountsByAccountNumberEquals(String accountNumber);
    Account findAccountByCustomerId(Long id);
}
