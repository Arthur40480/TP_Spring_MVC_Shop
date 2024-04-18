package fr.ldnr.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.HashMap;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor @ToString
public class Order implements Serializable
{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    Customer customer;
    HashMap<Long , Article> order;

    public Order(Customer customer , HashMap<Long , Article> order)
    {
       this.customer = customer;
       this.order = order;
    }
}
