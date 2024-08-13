package oct.soft.book.auth;

import oct.soft.book.user.Token;
import oct.soft.book.user.TokenRepository;
import oct.soft.book.user.User;
import oct.soft.book.user.UserRepository;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import oct.soft.book.email.EmailService;
import oct.soft.book.email.EmailTemplateName;
import oct.soft.book.role.RoleRepository;
import oct.soft.book.security.JwtService;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

	private final RoleRepository roleRepository;
	private final PasswordEncoder passwordEncoder;
	private final UserRepository userRepository;
	private final TokenRepository tokenRepository;
	private final EmailService emailService;
	private final AuthenticationManager authenticationManager;
	private final JwtService jwtService;
	
	@Value("${application.mailing.frontend.activation-url}") 
	String activationUrl;
	
	public void register(@Valid RegistrationRequest request) throws MessagingException {
		var userRole = roleRepository.findByName("USER")
				.orElseThrow(() -> new IllegalStateException("ROLE USER was not initialized"));
		var user = User.builder().firstName(request.getFirstname()).lastName(request.getLastname())
				.email(request.getEmail()).password(passwordEncoder.encode(request.getPassword())).accountLocked(false)
				.enabled(false).roles(List.of(userRole)).build();
		userRepository.save(user);
		sendValidationEmail(user);
	}

	public void sendValidationEmail(User user) throws MessagingException {
		var newToken = generateAndSaveActivationToken(user);
		
		// send email
		emailService.sendEmail(user.getEmail(), user.fullName(), EmailTemplateName.ACTIVATE_ACCOUNT, activationUrl, newToken, "Account activation");

	}

	private String generateAndSaveActivationToken(User user) {
		// geenrate a token
		String generatedToken = generateActivationCode(6);
		var token = Token.builder().token(generatedToken).user(user).createdAt(LocalDateTime.now())
				.expiresAt(LocalDateTime.now().plusMinutes(15)).build();
		tokenRepository.save(token);
		return generatedToken;
	}

	private String generateActivationCode(int length) {
		String characters = "0123456789";
		StringBuilder sb = new StringBuilder();
		SecureRandom secureRandom = new SecureRandom();
		for (int i = 0; i < length; i++) {
			int randomIndex = secureRandom.nextInt(characters.length());
			sb.append(characters.charAt(randomIndex));
		}
		return sb.toString();
	}

	public AuthenticationResponse authenticate(AuthenticationRequest request) {
		var auth = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
				);
		var claims = new HashMap<String,Object>();
		var user = ((User) auth.getPrincipal());
		claims.put("fullName", user.fullName());
		var jwtToken = jwtService.generateToken(claims,  user);
		return AuthenticationResponse.builder().token(jwtToken).build();
	}

}
