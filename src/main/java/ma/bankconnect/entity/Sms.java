package ma.bankconnect.entity;

import jakarta.persistence.*;
import jdk.jfr.Timestamp;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class Sms {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;
    @Column(name = "code", nullable = false, unique = true)
    private String code;
    @Column(name = "phone", nullable = false)
    private String phone;
    @Column(name = "send_date", nullable = false)
    @Timestamp
    private Date sendDate;
    @ManyToOne(fetch = FetchType.EAGER)
    private Customer customer;
    @JoinColumn(name = "customer_id", nullable = false)
    public Customer getCustomer() {
        return customer;
    }


}
