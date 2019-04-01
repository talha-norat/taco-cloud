package tacos.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import tacos.dao.IngredientRepository;
import tacos.dao.TacoRepository;
import tacos.model.Ingredient;
import tacos.model.IngredientType;
import tacos.model.Order;
import tacos.model.Taco;
import tacos.util.CollectionUtils;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping(path = "/design")
@SessionAttributes("order")
public class DesignTacoController
{

	private final IngredientRepository ingredientRepo;
	private final TacoRepository tacoRepo;

	@ModelAttribute(name = "order")
	public Order order()
	{
		return new Order();
	}
	
	@ModelAttribute(name = "taco")
	public Taco taco() 
	{
		return new Taco();
	}

	@GetMapping
	public String showDesignForm(Model model)
	{
		List<Ingredient> ingredients = new ArrayList<>();
		ingredientRepo.findAll().forEach(ingredient -> ingredients.add(ingredient));

		IngredientType[] types = IngredientType.values();

		for (IngredientType ingredientType : types)
		{
			model.addAttribute(ingredientType.toString().toLowerCase(),
					CollectionUtils.filterByType(ingredients, ingredientType));
		}

		model.addAttribute("design", new Taco());

		return "designForm";
	}

	@PostMapping
	public String postDesign(@Valid Taco design, Errors errors, @ModelAttribute Order order)
	{
		if (errors.hasErrors())
		{
			return "redirect:/design";
		}
		
		Taco saved = tacoRepo.save(design);
		order.addTaco(saved);
		
		return "redirect:/orders/current";
	}

}
