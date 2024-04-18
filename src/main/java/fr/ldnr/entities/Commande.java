package fr.ldnr.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashMap;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor @ToString
public class Commande implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private Customer customer;

    private HashMap<Long , Article> cart;

    public Commande(Customer customer , HashMap<Long , Article> cart)
    {
        this.customer = customer;
        this.cart = cart;
    }
}
