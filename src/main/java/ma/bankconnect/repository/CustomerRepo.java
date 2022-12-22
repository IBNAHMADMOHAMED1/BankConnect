package ma.bankconnect.repository;

import ma.bankconnect.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepo extends JpaRepository<Customer, Long> {
    Customer findByEmail(String email);
    Customer findByCin(String cin);


}

