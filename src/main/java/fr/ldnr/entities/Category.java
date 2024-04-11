package fr.ldnr.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Collection;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor @ToString
public class Category implements Serializable
{

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min=1,max=30)
    private String name;
    @Size(max = 50)
    private String description;

    @OneToMany(mappedBy = "category")
    private Collection<Article> articles;

    public Category(String name , String description)
    {
        this.name = name;
        this.description = description;
    }
}
