package ma.bankconnect.utils;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CloudinaryConfig {

    public Cloudinary cloudinary() {
        return new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "ditimjxfy",
                "api_key", "146139492622934",
                "api_secret", "nF94rFrBb-bJV35__ju_YXC_6AQ"
        ));
    }
}

