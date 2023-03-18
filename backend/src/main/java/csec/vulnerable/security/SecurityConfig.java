package csec.vulnerable.security;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
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
public class SecurityConfig {

    private static final String[] PUBLIC_URLS = {
        "/index.html", 
        "/products", 
        "/products/*"
    };

    private final AuthenticationEntryPointImpl authenticationEntryPointImpl;
    private final AccessDeniedHandlerImpl accessDeniedHandlerImpl;
    private final AuthenticationSuccessHandlerImpl authenticationSuccessHandlerImpl;
    private final AuthenticationFailureHandlerImpl authenticationFailureHandlerImpl;
    private final LogoutSuccessHandlerImpl logoutSuccessHandlerImpl;
    private final BlockUserDetailsService blockUserDetailsService;

    @Autowired
    public SecurityConfig(
            AuthenticationEntryPointImpl authenticationEntryPointImpl,
            AccessDeniedHandlerImpl accessDeniedHandlerImpl,
            AuthenticationSuccessHandlerImpl authenticationSuccessHandlerImpl,
            AuthenticationFailureHandlerImpl authenticationFailureHandlerImpl,
            LogoutSuccessHandlerImpl logoutSuccessHandlerImpl,
            BlockUserDetailsService blockUserDetailsService) {
        this.authenticationEntryPointImpl = authenticationEntryPointImpl;
        this.accessDeniedHandlerImpl = accessDeniedHandlerImpl;
        this.authenticationSuccessHandlerImpl = authenticationSuccessHandlerImpl;
        this.authenticationFailureHandlerImpl = authenticationFailureHandlerImpl;
        this.logoutSuccessHandlerImpl = logoutSuccessHandlerImpl;
        this.blockUserDetailsService = blockUserDetailsService;
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(blockUserDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfiguration) throws Exception {
        return authConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .cors()
            .and()
            .authorizeRequests()
            .antMatchers(PUBLIC_URLS).permitAll()
            .anyRequest().authenticated()
            .and()
            .exceptionHandling()
            .authenticationEntryPoint(authenticationEntryPointImpl)
            .accessDeniedHandler(accessDeniedHandlerImpl)
            .and()
            .formLogin()
            .loginProcessingUrl("/login")
            .successHandler(authenticationSuccessHandlerImpl)
            .failureHandler(authenticationFailureHandlerImpl)
            .usernameParameter("username")
            .passwordParameter("password")
            .permitAll()
            .and()
            .logout()
            .logoutUrl("/logout")
            .logoutSuccessHandler(logoutSuccessHandlerImpl)
            .permitAll()
            .and()
            .rememberMe();

        http.authenticationProvider(authenticationProvider());

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfiguration() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "HEAD", "OPTION"));
        configuration.addAllowedHeader("/*");
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(11);
    }
}
