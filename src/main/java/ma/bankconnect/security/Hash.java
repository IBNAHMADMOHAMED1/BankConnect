package ma.bankconnect.security;

import de.mkammerer.argon2.*;

public class Hash {

    static Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id, 32, 64);

    public static String getHashedPassword(String password){
        return argon2.hash(2,15*1024,1, password.toCharArray());
    }

    public static Boolean verifiedPassword(String hashedPassword, String password){
        return argon2.verify(hashedPassword, password.toCharArray());
    }

}
