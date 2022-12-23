package ma.bankconnect.service.sms;

import ma.bankconnect.entity.Sms;

public interface SmsService  {
    Sms save(Long customerId);
    Sms findByCode(String code);
    Sms findByCustomerId(Long customerId);
    void deleteSms(Long id);
}
