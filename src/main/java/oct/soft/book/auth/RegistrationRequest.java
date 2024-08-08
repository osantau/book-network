package oct.soft.book.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class RegistrationRequest {
	@NotEmpty(message = "Firstname is mandatory")
	@NotBlank(message = "Firstname is mandatory")
	private String firstName;
	@NotEmpty(message = "Lastname is mandatory")
	@NotEmpty(message = "Lastname is mandatory")
	private String lastName;
	@NotEmpty(message = "Email is mandatory")
	@NotBlank(message = "Email is mandatory")
	@Email(message = "Email is not well formatted")
	private String email;
	@NotEmpty(message = "Passsword is mandatory")
	@NotBlank (message = "Passsword is mandatory")
	@Size(min=8, message = "Password should be 8 characters long minimumsss")
	private String password;
}
