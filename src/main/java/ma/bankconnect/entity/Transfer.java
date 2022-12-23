package ma.bankconnect.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Transfer {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Basic
        @Column(name = "rec_account", nullable = false)
        private Long recAccount; //receiver account

        @Basic
        @Column(name = "transaction_id", nullable = false)
        private Long transactionId; //transaction id

        @Basic
        @Column(name = "status", nullable = false)
        private boolean status; //status of the transfer

        @ManyToOne
        @JoinColumn(name = "rec_account", insertable = false, updatable = false)
        private Account account;

        @ManyToOne
        @JoinColumn(name = "transaction_id", insertable = false, updatable = false)
        private Transaction transaction;
        @Basic @Column
        private Date created;
        @Basic @Column
        private Date updated;
        @PrePersist
        protected void onCreate(){
            this.created = new Date();
            this.updated = new Date();
        }
        @PreUpdate
        protected void onUpdate(){
            this.updated = new Date();
        }

}
