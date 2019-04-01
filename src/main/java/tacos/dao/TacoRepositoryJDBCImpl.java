package tacos.dao;

import java.sql.Timestamp;
import java.sql.Types;
import java.util.Arrays;
import java.util.Date;
import java.util.Objects;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import tacos.model.Taco;

@Repository
@RequiredArgsConstructor
public class TacoRepositoryJDBCImpl implements TacoRepository
{

	private final JdbcTemplate jdbcTemplate;

	@Override
	public Taco save(Taco taco)
	{
		long tacoId = saveTacoInformation(taco);
		taco.setId(tacoId);
		for (String ingredient : taco.getIngredients())
		{
			saveTacoIngredients(tacoId, ingredient);
		}
		return null;
	}

	private long saveTacoInformation(Taco taco)
	{
		taco.setCreatedAt(new Date());
		PreparedStatementCreatorFactory pscf = new PreparedStatementCreatorFactory(
				"INSERT INTO Taco (name, createdAt) VALUES (?,?)", Types.VARCHAR, Types.TIMESTAMP);

		pscf.setReturnGeneratedKeys(true);

		PreparedStatementCreator preparedStatementCreator = pscf.newPreparedStatementCreator(
				Arrays.asList(taco.getName(), new Timestamp(taco.getCreatedAt().getTime())));

		KeyHolder keyHolder = new GeneratedKeyHolder();

		jdbcTemplate.update(preparedStatementCreator, keyHolder);

		if (Objects.isNull(keyHolder.getKey()))
		{
			System.out.println("NULL");
		}

		return keyHolder.getKey().longValue();
	}

	private void saveTacoIngredients(long tacoId, String ingredient)
	{
		jdbcTemplate.update("INSERT INTO Taco_Ingredients (taco, ingredient) VALUES (?,?)", tacoId, ingredient);
	}

}
