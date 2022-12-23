package ma.bankconnect.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Basic
    @Column(name = "type", nullable = false)
    private String type;

    @Basic
    @Column(name = "amount", nullable = false)
    private Double amount;

    @Basic
    @Column(name = "account_id", nullable = false)
    private Long accountId;

    @ManyToOne
    @JoinColumn(name = "account_id", insertable = false, updatable = false)
    private Account account;
    @Basic
    @Column
    private Date created;
    @Basic
    @Column
    private Date updated;
    @PrePersist
    protected void onCreate() {
        this.created = new Date();
        this.updated = new Date();
    }
    @PreUpdate
    protected void onUpdate() {
        this.updated = new Date();
    }
}
