package tacos.model;
import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.Data;

@Data
public class RegistrationForm {

	private String username;
	private String password;
	private String firstName;
	private String surname;
	private String address;
	private String city;
	private String postcode;
	private String phoneNo;

	public User toUser(PasswordEncoder passwordEncoder) {
		return new User(username, passwordEncoder.encode(password), firstName,
				surname, address,city, postcode, phoneNo);
	}

}