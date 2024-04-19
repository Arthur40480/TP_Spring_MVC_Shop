package fr.ldnr.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor @ToString
public class OrderItem implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idOrderItem;
    private Long idArticle;
    private int quantity;
    private double unitaryPrice;
    private Long idOrder;

    public OrderItem ( Long idArticle , int quantity , double unitaryPrice)
    {
        this.idArticle = idArticle;
        this.quantity = quantity;
        this.unitaryPrice = unitaryPrice;
    }
}
