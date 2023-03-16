package csec.vulnerable.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import csec.vulnerable.security.BlockUserDetailsService;
import csec.vulnerable.security.SecurityUtils;

@Component
public class AuthenticationFailureHandlerImpl implements AuthenticationFailureHandler {
	private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationFailureHandlerImpl.class);
	private final BlockUserDetailsService blockUserDetailsService;

    public AuthenticationFailureHandlerImpl(BlockUserDetailsService blockUserDetailsService) {
        this.blockUserDetailsService = blockUserDetailsService;
    }

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
										AuthenticationException exception) throws IOException, ServletException {
		String username = request.getParameter("username");
		blockUserDetailsService.increaseFailedAttempts(username);
	
		if (blockUserDetailsService.isBlocked(username)) {
			LOGGER.warn("User {} is blocked due to multiple failed login attempts", username);
			SecurityUtils.sendResponse(response, HttpServletResponse.SC_FORBIDDEN, "User is blocked", null);
			return;
		}
	
		SecurityUtils.sendResponse(response, HttpServletResponse.SC_UNAUTHORIZED, "Login failed", exception);
	}

}
