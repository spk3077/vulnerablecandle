package csec.vulnerable.security;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsConfigurationSource;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

import csec.vulnerable.handler.AccessDeniedHandlerImpl;
import csec.vulnerable.handler.AuthenticationEntryPointImpl;
import csec.vulnerable.handler.AuthenticationFailureHandlerImpl;
import csec.vulnerable.handler.AuthenticationSuccessHandlerImpl;
import csec.vulnerable.handler.LogoutSuccessHandlerImpl;

@Configuration
public class SecurityConfig{
	@Autowired
	AuthenticationEntryPointImpl authenticationEntryPointImpl;
	
	@Autowired
	AccessDeniedHandlerImpl accessDeniedHandlerImpl;
	
	@Autowired
	AuthenticationSuccessHandlerImpl authenticationSuccessHandlerImpl;
	
	@Autowired
	AuthenticationFailureHandlerImpl authenticationFailureHandlerImpl;
	
	@Autowired
	LogoutSuccessHandlerImpl logoutSuccessHandlerImpl; 
	
	@Autowired
	UserDetailsService userDetailsService;

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		
		authProvider.setUserDetailsService(userDetailsService);
		authProvider.setPasswordEncoder(passwordEncoder());
	
		return authProvider;
	}

	@Bean
  	public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfiguration) throws Exception {
    	return authConfiguration.getAuthenticationManager();
  	}
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable().cors()
	        .and().authorizeHttpRequests().requestMatchers("/index.html", "/products", "products/*").permitAll().and()
	        .exceptionHandling()
	        .authenticationEntryPoint(authenticationEntryPointImpl).and().exceptionHandling()
	        .accessDeniedHandler(accessDeniedHandlerImpl).and().formLogin().permitAll()
	        .loginProcessingUrl("/login")
	        .successHandler(authenticationSuccessHandlerImpl)
	        .failureHandler(authenticationFailureHandlerImpl)
	        .usernameParameter("username")
	        .passwordParameter("password")
	        .and().logout().permitAll()
	        .logoutUrl("/logout").logoutSuccessHandler(logoutSuccessHandlerImpl).and().rememberMe();

		http.authenticationProvider(authenticationProvider());

        return http.build();
    }

	@Bean
	public CorsConfigurationSource corsConfiguration() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOrigins(Arrays.asList("*"));
		configuration.setAllowedMethods(Arrays.asList("GET","POST","PUT","DELETE","HEAD","OPTION"));
		configuration.addAllowedHeader("/*");
		configuration.setAllowCredentials(true);
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}
	

	@Bean
	public PasswordEncoder passwordEncoder() {
		PasswordEncoder encoder = new BCryptPasswordEncoder(11);
		
		return encoder;
	}

}

