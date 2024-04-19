package fr.ldnr.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor @ToString
public class Commande implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCommande;
    private double amount;
    private Long idCustomer;

    public Commande(double amount , Long idCustomer)
    {
        this.amount = amount;
        this.idCustomer = idCustomer;
    }
}
