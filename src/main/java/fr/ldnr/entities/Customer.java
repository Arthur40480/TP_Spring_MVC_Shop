package fr.ldnr.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.*;
import java.io.Serializable;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor @ToString
public class Customer implements Serializable
{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @NotNull @Size(min=1,max=20)
    private String name;
    @NotNull @Size(min=1,max=20)
    private String firstName;
    @NotNull @Size(min=1,max=100)
    private String address;
    @NotNull @Email
    private String email;
    @NotNull @Size(min =10 , max = 10)
    private String phone;

    public Customer(String name , String firstName , String address , String email , String phone) {
        this.name = name;
        this.firstName = firstName;
        this.address = address;
        this.email = email;
        this.phone = phone;
    }
}
