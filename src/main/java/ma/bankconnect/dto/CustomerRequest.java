package ma.bankconnect.dto;

import lombok.Data;
@Data
public class CustomerRequest {
    private String email;
    private String password;
    private String adresse;
    private String nom;
    private String telephone;
    private String cin;
    private String cinImage;
}

/*
  {
    "email": "test@test.com"
    "password": "test"
    "adresse": "test"
    "nom": "test"
    "telephone": "test"
    "cin": "test"
    "cinImage": "test"
    }
 */
