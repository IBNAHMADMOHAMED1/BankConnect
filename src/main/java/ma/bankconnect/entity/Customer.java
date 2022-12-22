package ma.bankconnect.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.io.Serializable;

@Data
@Entity
@Table(name = "customer")
public class Customer implements Serializable {

   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   @Column(name = "id", nullable = false, updatable = false)
   private Long id;

   @Column(name = "nom", nullable = false)
   private String nom;

   @Column(name = "prenom", nullable = false)
   private String prenom;

   @Column(name = "adresse", nullable = false)
   private String address;

   @Column(name = "email", nullable = false, unique = true)
   private String email;

   @Column(name = "password", nullable = false)
   private String password;

   @Column(name = "telephone", nullable = false)
   private String telephone;

   @Column(name = "cin", nullable = false, unique = true)
   private String cin;

   @Column(name = "cin_image", nullable = false)
   private String cinImage;

   @Column(name = "status", nullable = false)
   private Boolean status;

}