package tacos.model;

import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Taco
{

	@NotNull
	@Size(min = 5, message = "The name must be at least 5 characters long")
	private String name;

	@NotNull
	@Size(min = 1, message = "You must choose at least one ingredient to put in your design")
	private List<String> ingredients;

}
