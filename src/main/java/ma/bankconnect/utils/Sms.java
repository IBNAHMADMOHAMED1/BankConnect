package ma.bankconnect.utils;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;


@Configuration
public class Sms {
    @Autowired
    private Environment env;
    @Value("${ACCOUNT_SID}")
    private String accountSid;
    @Value("${AUTH_TOKEN}")
    private String authToken;
    @Value("${TWILIO_NUMBER}")
    private String twilioNumber;

        public Boolean sendSms(String to, String body) {
        // Set up account SID and auth token
        System.out.println("accountSid = " + accountSid);
        System.out.println("en" + twilioNumber);
        Twilio.init(accountSid, authToken);
        Message message = Message.creator(new PhoneNumber(to), new PhoneNumber("+19737918082"), body).create();
        return message.getErrorMessage() == null;
    }
}
