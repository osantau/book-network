package oct.soft.book.config;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import oct.soft.book.user.User;

public class ApplicationAuditAware implements AuditorAware<Long> {

	@Override
	public Optional<Long> getCurrentAuditor() {
		// TODO Auto-generated method stub
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if(authentication == null || !authentication.isAuthenticated() || authentication instanceof AnonymousAuthenticationToken)			
		{
			return Optional.empty();
		}
		
		User userPrincipal = (User) authentication.getPrincipal();
		
		return Optional.ofNullable(userPrincipal.getId());
	}

	
}
