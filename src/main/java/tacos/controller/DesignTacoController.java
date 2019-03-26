package tacos.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import tacos.model.Ingredient;
import tacos.model.IngredientType;
import tacos.model.Taco;
import tacos.util.CollectionUtils;

@Controller
@RequestMapping(path = "/design")
public class DesignTacoController {

	@GetMapping
	public String showDesignForm(Model model) {
		List<Ingredient> ingredients = Arrays.asList(new Ingredient("FLTO", "Flour Tortilla", IngredientType.WRAP),
				new Ingredient("COTO", "Corn Tortilla", IngredientType.WRAP),
				new Ingredient("GRBF", "Ground Beef", IngredientType.PROTEIN),
				new Ingredient("CARN", "Carnitas", IngredientType.PROTEIN),
				new Ingredient("TMTO", "Diced Tomatoes", IngredientType.VEGGIE),
				new Ingredient("LETC", "Lettuce", IngredientType.VEGGIE),
				new Ingredient("CHED", "Cheddar", IngredientType.CHEESE),
				new Ingredient("JACK", "Monterrey Jack", IngredientType.CHEESE),
				new Ingredient("SLSA", "Salsa", IngredientType.SAUCE),
				new Ingredient("SRCR", "Sour Cream", IngredientType.SAUCE));

		IngredientType[] types = IngredientType.values();

		for (IngredientType ingredientType : types) {
			model.addAttribute(ingredientType.toString().toLowerCase(),
					CollectionUtils.filterByType(ingredients, ingredientType));
		}

		model.addAttribute("design", new Taco());

		return "design";

	}

}