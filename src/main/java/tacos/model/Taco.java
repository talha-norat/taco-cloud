package tacos.model;

import java.sql.Date;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Taco
{

	private long id;

	private Date createdAt;

	@NotNull
	@Size(min = 5, message = "The name must be at least 5 characters long")
	private String name;

	@NotNull
	@Size(min = 1, message = "You must choose at least one ingredient to put in your design")
	private List<String> ingredients;

}
