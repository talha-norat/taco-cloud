package tacos.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Ingredient {

	private final int id;
	private final String name;
	private final IngredientType type;
	
}
