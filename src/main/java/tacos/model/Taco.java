package tacos.model;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Taco {

	private String name;
	private List<Ingredient> ingredients;

}
