package tacos.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import tacos.model.Order;
import tacos.model.Taco;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryJDBCImpl implements OrderRepository {

	private final JdbcTemplate jdbcTemplate;
	private SimpleJdbcInsert orderInserter;
	private SimpleJdbcInsert orderTacoInserter;
	private final ObjectMapper objectMapper;
	
	@PostConstruct
	public void init() {
		orderInserter = new SimpleJdbcInsert(jdbcTemplate).withTableName("Taco_Order").usingGeneratedKeyColumns("id");
		orderTacoInserter = new SimpleJdbcInsert(jdbcTemplate).withTableName("Taco_Order_Tacos");
	}

	@Override
	public Order save(Order order) {
		order.setOrderDate(new Date());
		long orderId = saveOrderDetails(order);
		order.setId(orderId);
		List<Taco> tacos = order.getTacos();
		for (Taco taco : tacos) {
			saveTacoToOrder(orderId, taco);
		}
		
		return order;
	}
	
	@SuppressWarnings("unchecked")
	private long saveOrderDetails(Order order) {
		Map<String, Object> values = objectMapper.convertValue(order, Map.class);
		values.put("orderDate", order.getOrderDate());
		
		long orderId = orderInserter.executeAndReturnKey(values).longValue();
		return orderId;
	}
	
	private void saveTacoToOrder(long orderId, Taco taco) {
		Map<String, Object> values = new HashMap<>();
		values.put("orderId", orderId);
		values.put("tacoId", taco.getId());
		orderTacoInserter.execute(values);
	}

}
