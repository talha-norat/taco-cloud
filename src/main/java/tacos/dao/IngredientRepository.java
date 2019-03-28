package tacos.dao;

import tacos.model.Ingredient;

public interface IngredientRepository
{

	Iterable<Ingredient> findAll();

	Ingredient findById(Long id);

	Ingredient save(Ingredient ingredient);

}
