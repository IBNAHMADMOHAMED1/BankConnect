package ma.bankconnect;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

@SpringBootApplication
public class BankConnectApplication {

    private static Environment env;
    public static void main(String[] args) {
      SpringApplication.run(BankConnectApplication.class, args);
    }


}
