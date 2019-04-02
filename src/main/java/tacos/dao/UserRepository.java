package tacos.dao;

import org.springframework.data.repository.CrudRepository;

import tacos.model.User;

public interface UserRepository extends CrudRepository<User, Long> 
{
	User findByUsername(String username);	
}
