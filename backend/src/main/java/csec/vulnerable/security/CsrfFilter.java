package csec.vulnerable.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.WebUtils;

public class CsrfFilter extends OncePerRequestFilter {

    private final CsrfTokenRepository csrfTokenRepository;

    public CsrfFilter(CsrfTokenRepository csrfTokenRepository) {
        this.csrfTokenRepository = csrfTokenRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        CsrfToken csrfToken = csrfTokenRepository.loadToken(request);
        boolean csrfHeaderExists = request.getHeader("X-CSRF-TOKEN") != null;
        boolean csrfCookieExists = WebUtils.getCookie(request, "XSRF-TOKEN") != null;
        if (csrfToken == null || (!csrfHeaderExists && !csrfCookieExists)) {
            filterChain.doFilter(request, response);
            return;
        }
        if (csrfHeaderExists) {
            csrfTokenRepository.saveToken(csrfToken, request, response);
        }
        filterChain.doFilter(request, response);
    }
}
