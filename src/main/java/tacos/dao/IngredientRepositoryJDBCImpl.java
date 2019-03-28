package tacos.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import tacos.model.Ingredient;
import tacos.model.IngredientType;

@Repository
@RequiredArgsConstructor
public class IngredientRepositoryJDBCImpl implements IngredientRepository
{

	private final JdbcTemplate jdbcTemplate;

	@Override
	public Iterable<Ingredient> findAll()
	{
		return jdbcTemplate.query("SELECT * FROM Ingredient", this::mapRowToIngredient);
	}

	@Override
	public Ingredient findById(Long id)
	{
		return jdbcTemplate.queryForObject("SELECT * FROM Ingredient WHERE id = ?", this::mapRowToIngredient, id);
	}

	@Override
	public Ingredient save(Ingredient ingredient)
	{
		jdbcTemplate.update("INSERT INTO Ingredient (id, name, type) VALUES (?,?,?)", ingredient.getId(),
				ingredient.getName(), ingredient.getType().toString());
		return ingredient;
	}

	private Ingredient mapRowToIngredient(ResultSet rs, int rowNumber) throws SQLException
	{
		return new Ingredient(rs.getString("id"), rs.getString("name"), IngredientType.valueOf(rs.getString("type")));
	}

}
