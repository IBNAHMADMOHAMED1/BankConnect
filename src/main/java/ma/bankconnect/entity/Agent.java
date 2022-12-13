package ma.bankconnect.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;


@Entity
@Setter
@Getter

public class Agent {

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "nom", nullable = false)
    private String nom;
    @Column(name = "prenom", nullable = false)
    private String prenom;
    @Column(name = "email", nullable = false, unique = true)
    private String email;
    @Column(name = "password", nullable = false)
    private String password;
}

