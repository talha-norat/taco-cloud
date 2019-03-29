package tacos.dao;

import java.sql.Timestamp;
import java.sql.Types;
import java.util.Arrays;
import java.util.Date;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import lombok.RequiredArgsConstructor;
import tacos.model.Taco;

@RequiredArgsConstructor
public class TacoRepositoryJDBCImpl implements TacoRepository
{

	private final JdbcTemplate jdbcTemplate;
	private final IngredientRepositoryJDBCImpl ingredientRepositoryJDBCImpl;

	@Override
	public Taco save(Taco taco)
	{
		long tacoId = saveTacoInformation(taco);
		taco.setId(tacoId);
		for (String ingredient : taco.getIngredients())
		{

		}
		return null;
	}

	private Long saveTacoInformation(Taco taco)
	{
		taco.setCreatedAt(new Date());
		PreparedStatementCreator preparedStatementCreator = new PreparedStatementCreatorFactory(
				"INSERT INTO Taco (name, createdAt) VALUES (?,?)", Types.VARCHAR, Types.TIMESTAMP)
						.newPreparedStatementCreator(
								Arrays.asList(taco.getName(), new Timestamp(taco.getCreatedAt().getTime())));

		KeyHolder keyHolder = new GeneratedKeyHolder();

		jdbcTemplate.update(preparedStatementCreator, keyHolder);
		return keyHolder.getKey().longValue();
	}

	private void saveTacoIngredients()
	{

	}

}
