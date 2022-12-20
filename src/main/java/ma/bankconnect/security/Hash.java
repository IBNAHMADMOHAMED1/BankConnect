package ma.bankconnect.security;

import de.mkammerer.argon2.*;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;

@Component
public class Hash {

    static Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id, 32, 64);

    public static String getHashedPassword(String password){
        return argon2.hash(2,15*1024,1, password.toCharArray());
    }

    public static Boolean verifiedPassword(String hashedPassword, String password){
        return argon2.verify(hashedPassword, password.toCharArray());
    }

    // bcrypt hashing
    public  String BCryptHashing(String password){
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

}
