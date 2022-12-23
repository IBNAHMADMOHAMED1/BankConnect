package ma.bankconnect.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Builder // Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountOperation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate operationDate;
    private double amount;
    @Column(name = "operation_type",columnDefinition = "varchar(255) default 'DEPOSIT'")
    private String type;
    @ManyToOne
    private Account accountTo;
    @ManyToOne
    private Account accountFrom;

}
