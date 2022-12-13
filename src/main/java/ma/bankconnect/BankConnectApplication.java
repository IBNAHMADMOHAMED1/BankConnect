package ma.bankconnect;

import ma.bankconnect.utils.Sms;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BankConnectApplication {

    public static void main(String[] args) {
        SpringApplication.run(BankConnectApplication.class, args);
        Sms sms = new Sms();
       // sms.sendSms("+212684632770", "Hello World");
    }

}
