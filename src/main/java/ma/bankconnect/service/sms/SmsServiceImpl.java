package ma.bankconnect.service.sms;

import lombok.RequiredArgsConstructor;
import ma.bankconnect.entity.Sms;
import ma.bankconnect.repository.sms.SmsRepo;
import ma.bankconnect.service.CustomerServiceImpl;
import ma.bankconnect.utils.SmsUtil;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Random;
@Service
@RequiredArgsConstructor
public class SmsServiceImpl implements SmsService {
    private final SmsRepo smsRepo;
    private final SmsUtil smsUtil;
    private final CustomerServiceImpl customerService;

    @Override
    public Sms save(Long customerId) {
        Sms sms = new Sms();
        String code = generateCodeSms();
        Boolean isSent = smsUtil.sendSms("+212684632770", code);
        if (isSent) {
            sms.setSendDate(new Date());
            sms.setCode(code);
            sms.setPhone("+212684632770");
            sms.setCustomer(customerService.findById(customerId));
            return smsRepo.save(sms);
        }
        return null;
    }
    @Override
    public Sms findByCode(String code) {
        return smsRepo.findByCode(code);
    }
    public Boolean verifyAccountByCodeSms(String codeSms,Long idCustomer) {
        Sms sms = findByCode(codeSms);
        if (sms != null && checkExpiration(sms.getSendDate())) {
            // change verified
            customerService.changeVerified(idCustomer);
            return true;
        }
        return false;
    }
    public String generateCodeSms() {
        Random random = new Random();
        int code = random.nextInt(999999);
        return String.format("%06d", code);

    }
    public Boolean checkExpiration(Date sendDate) {
        Date date = new Date();
        long diff = date.getTime() - sendDate.getTime();
        long diffMinutes = diff / (60 * 1000) % 60;
        if (diffMinutes > 5) {
            return false;
        }
        return true;
    }

    // findByCustomerId
    @Override
    public Sms findByCustomerId(Long id) {
        return smsRepo.findByCustomerId(id);
    }

}
