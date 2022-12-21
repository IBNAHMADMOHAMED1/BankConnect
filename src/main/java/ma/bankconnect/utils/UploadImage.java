package ma.bankconnect.utils;

import lombok.RequiredArgsConstructor;
import ma.bankconnect.config.CloudinaryConfig;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UploadImage {
        private final CloudinaryConfig cloudinaryConfig;


}
