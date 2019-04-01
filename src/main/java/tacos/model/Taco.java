package tacos.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.PrePersist;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
@Entity
public class Taco
{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private Date createdAt;

	@NotNull
	@Size(min = 5, message = "The name must be at least 5 characters long")
	private String name;

	@ManyToMany(targetEntity = Ingredient.class)
	@Size(min = 1, message = "You must choose at least one ingredient to put in your design")
	private List<Ingredient> ingredients;

	@PrePersist
	void createdAt() {
		createdAt = new Date();
	}
}
