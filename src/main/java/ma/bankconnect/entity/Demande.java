package ma.bankconnect.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.io.Serializable;

@Data
@Entity
@Table(name = "demande")
public class Demande implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "type_account", nullable = false)
    private String type_account;

    @Column(name = "client_id", nullable = false)
    private Long client_id;

    @ManyToOne
    @JoinColumn(name = "client_id", insertable = false, updatable = false)
    private Customer customer;

}
