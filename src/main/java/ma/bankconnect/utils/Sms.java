package ma.bankconnect.utils;

import com.twilio.http.TwilioRestClient;
import com.twilio.type.PhoneNumber;
import org.apache.logging.log4j.message.Message;
import org.apache.logging.log4j.message.MessageFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

public class Sms {
    @Autowired
    private Environment en;
    TwilioRestClient client = new TwilioRestClient(en.getProperty("ACCOUNT_SID"), en.getProperty("twilio.auth.token"));
}
