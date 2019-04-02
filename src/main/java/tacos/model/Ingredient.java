package tacos.model;

import static tacos.util.StringUtils.LETTERS_ONLY_REGEX;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
@Entity
public class Ingredient 
{
	@Id
	@NotNull
	@Size(min = 4, max = 4)
	@Pattern(regexp = LETTERS_ONLY_REGEX)
	private String id;
	
	@NotNull
	@Size(min = 5, max = 30)
	@Pattern(regexp = LETTERS_ONLY_REGEX)
	private String name;
	
	@NotNull
	private IngredientType type;	
}
