package tacos.model;

import static tacos.util.StringUtils.CC_EXPIRATION_PATTERN;
import static tacos.util.StringUtils.LETTERS_ONLY_REGEX;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.CreditCardNumber;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Order
{

	private Long id;

	private Date orderDate;

	@Size(min = 3, max = 30, message = "First name must be between 3-30 characters in length")
	@Pattern(regexp = LETTERS_ONLY_REGEX, message = "First name must only contain letters")
	private String firstName;

	@Size(min = 3, max = 30, message = "Surname must be between 3-30 characters in length")
	@Pattern(regexp = LETTERS_ONLY_REGEX, message = "Surname must only contain letters")
	private String surname;

	@Size(min = 10, max = 50, message = "Address must be between 10-50 characters in length")
	private String addressLine;

	@Size(min = 3, max = 30, message = "City must be between 3-30 characters in length")
	private String city;

	@Size(min = 6, max = 7, message = "Postcode must be between 3-30 characters in length")
	private String postcode;

	@CreditCardNumber(message = "Not a valid credit card number")
	private String ccNumber;

	@Pattern(regexp = CC_EXPIRATION_PATTERN, message = "Credit Card expiration must be in the format MM/YY")
	private String ccExpiration;

	@Digits(integer = 3, fraction = 0, message = "Invalid CVV")
	private String ccCVC;

	private List<Taco> tacos = new ArrayList<>();
	
	public void addTaco(Taco taco) {
		tacos.add(taco);
	};
}
