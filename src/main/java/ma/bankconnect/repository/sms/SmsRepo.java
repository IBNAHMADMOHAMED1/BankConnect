package ma.bankconnect.repository.sms;

import ma.bankconnect.entity.Sms;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SmsRepo extends JpaRepository<Sms,Long> {
    Sms findByCode(String code);
    Sms findByCustomerId(Long customerId);
}
