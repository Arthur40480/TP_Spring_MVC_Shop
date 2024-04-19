package fr.ldnr.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor @ToString
public class Commande implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCommande;
    @NotNull
    private double amount;
    @ManyToOne
    private Customer customer;

    public Commande(double amount, Customer customer) {
        this.amount = amount;
        this.customer = customer;
    }
}
