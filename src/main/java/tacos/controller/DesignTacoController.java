package tacos.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.security.auth.x500.X500Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;
import tacos.model.Ingredient;
import tacos.model.IngredientType;
import tacos.model.Taco;

@Slf4j
@Controller
@RequestMapping(path = "/design")
public class DesignTacoController {

	public String showDesignForm(Model model) 
	{
		List<Ingredient> ingredients = Arrays.asList(
				new Ingredient("FLTO", "Flour Tortilla", IngredientType.WRAP),
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
						   ingredients.stream().filter(x -> x.getType().equals(ingredientType)).collect(Collectors.toList()));
	}
	
	model.addAttribute("design", new Taco());
	
	return "design";
	
	}
	
	public static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor)
    {
        Map<Object, Boolean> seen = new ConcurrentHashMap<>();
        return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }
	
}
