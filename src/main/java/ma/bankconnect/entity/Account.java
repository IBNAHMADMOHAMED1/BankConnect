package ma.bankconnect.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "account")
public class Account implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @OneToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @Column(name = "balance", nullable = false)
    private double balance;

    @Column(name = "account_number", nullable = false, unique = true)
    private String accountNumber;

    @Column(name = "account_type", nullable = false)
    private String accountType;

    @Column(name = "account_status",columnDefinition= "varchar(255) default 'pending'")
    private String accountStatus;

    @Column(name = "created_at", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime accountCreationDate;

    @Column(name = "daily_withdrawal_limit")
    private Double dailyWithdrawalLimit;

    @Column(name = "limit_update_date",updatable = false) //
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime limitUpdateDate;

    @Column(name = "annual_withdrawal_total")
    private Double annualWithdrawalLimit;

    @Column(name = "annual_withdrawal_date")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime annualWithdrawalDate;


    @PrePersist
    protected void onCreate() {
        this.accountCreationDate = LocalDateTime.now();
    }

}
