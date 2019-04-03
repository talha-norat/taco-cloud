package tacos.controller;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import tacos.dao.IngredientRepository;
import tacos.model.Ingredient;
import tacos.model.IngredientType;

@Controller
@RequiredArgsConstructor
@RequestMapping("/ingredients")
public class IngredientController {

	private final IngredientRepository ingredientRepository;
	
	@GetMapping()
	public String showAddIngredientPage(Model model) {
		List<IngredientType> ingredientTypes = new ArrayList<IngredientType>(EnumSet.allOf(IngredientType.class));
		model.addAttribute("IngredientTypes", ingredientTypes);
		
		model.addAttribute("ingredient", new Ingredient());
		
		return "addIngredient";
	}
	
	@PostMapping
	public String addNewIngredient(@Valid Ingredient ingredient, Errors errors, Model model) {
		
		if(errors.hasErrors()) {
			return "redirect:/ingredients";
		}
		
		ingredientRepository.save(ingredient);
		return "redirect:/design";
	}
}
