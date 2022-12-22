package ma.bankconnect.entity;

import jakarta.persistence.*;
import jdk.jfr.Timestamp;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
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
    @Column(name = "account_status", nullable = false)
    private String accountStatus;
    @Column(name = "created_at", nullable = false, updatable = false)
    @Timestamp
    private LocalDateTime accountCreationDate;
    @Column(name = "daily_withdrawal_limit")
    private Double dailyWithdrawalLimit;
    @Column(name = "limit_update_date") // date of last update of daily withdrawal limit for traking
    private LocalDateTime limitUpdateDate;
    @Column(name = "annual_withdrawal_total")
    private Double annualWithdrawalLimit;
    @Column(name = "annual_withdrawal_date")
    private LocalDateTime annualWithdrawalDate;
}
