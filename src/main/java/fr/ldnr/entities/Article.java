package fr.ldnr.entities;

import java.io.Serializable;

import javax.persistence.*;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor @ToString
public class Article implements Serializable {

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@NotNull @Size(min=1,max=20)
	private String brand;
	@NotNull @Size(min=1,max=50)
	private String description;
	@DecimalMin("1")
	private double price;
	@ManyToOne
	private Category category;
	@JsonProperty("quantity")
	private int quantity = 1;
	
	public Article(String brand, String description, double price) {
		this.brand = brand;
		this.description = description;
		this.price = price;
	}

	public Article(String description, String brand, double price, Category cat) {
		this.description = description;
		this.brand = brand;
		this.price = price;
		this.category = cat;
	}

	public Article(Long id, String brand, String description, double price, Category category) {
		this.id = id;
		this.brand = brand;
		this.description = description;
		this.price = price;
		this.category = category;
	}
}
