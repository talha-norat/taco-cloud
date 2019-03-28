package tacos.util;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import tacos.model.Ingredient;
import tacos.model.IngredientType;

public class CollectionUtils
{

	public static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor)
	{
		Map<Object, Boolean> seen = new ConcurrentHashMap<>();
		return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
	}

	public static List<Ingredient> filterByType(List<Ingredient> ingredients, IngredientType type)
	{
		return ingredients.stream().filter(ingredient -> ingredient.getType().equals(type))
				.collect(Collectors.toList());
	}

}
