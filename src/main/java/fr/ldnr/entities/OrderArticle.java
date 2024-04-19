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
public class OrderArticle implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idOrderItem;
    @NotNull
    private Long idArticle;
    @NotNull
    private int quantity;
    @NotNull
    private double unitaryPrice;
    @ManyToOne
    private Commande commande;

    public OrderArticle(Long idArticle, int quantity, double unitaryPrice, Commande commande) {
        this.idArticle = idArticle;
        this.quantity = quantity;
        this.unitaryPrice = unitaryPrice;
        this.commande = commande;
    }
}
