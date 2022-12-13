package ma.bankconnect.utils;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Value;

public class Sms {
    @Value("${ACCOUNT_SID}")
    private String accountSid;
    @Value("${AUTH_TOKEN}")
    private String authToken;
    @Value("${TWILIO_NUMBER}")
    private String twilioNumber;
    public Boolean sendSms(String to, String body) {
        // Set up account SID and auth token
        Twilio.init(accountSid, authToken);
        Message message = Message.creator(new PhoneNumber(to), new PhoneNumber(twilioNumber), body).create();
        return message.getSid() != null;
    }
}
