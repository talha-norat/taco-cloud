package tacos.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor()
public class Order
{
	private String firstName;
	private String surname;
	private String addressLine;
	private String city;
	private String postcode;
	private String ccNumber;
	private String ccExpiration;
	private String ccCVC;
}
