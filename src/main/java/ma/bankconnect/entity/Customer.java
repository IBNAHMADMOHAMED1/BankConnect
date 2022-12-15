package ma.bankconnect.entity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;
@Entity
@Setter
@Getter
public class Customer implements Serializable {
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   @Column(name = "id", nullable = false, updatable = false)
    private Long id;
    @Column(name = "nom", nullable = false)
    private String name;
    @Column(name = "adresse", nullable = false)
    private String address;
    @Column(name = "email", nullable = false, unique = true)
    private String email;
    @Column(name = "telephone", nullable = false)
    private String phoneNumber;
    @Column(name = "cin", nullable = false, unique = true)
    private String cin;

    @Column(name = "cin_image", nullable = false)
    private String cinImage;

    public Customer() {
    }
    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", cin='" + cin + '\'' +
                '}';
    }
}