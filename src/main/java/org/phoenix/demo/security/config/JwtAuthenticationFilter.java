package org.phoenix.demo.security.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.phoenix.demo.security.service.JwtService;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

  private final JwtService jwtService;

  public JwtAuthenticationFilter(JwtService jwtService) {
    this.jwtService = jwtService;
  }

  @Override
  protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
    return request.getServletPath().equals("/api/v1/auth/authenticate");
  }

  @Override
  protected void doFilterInternal(
      @NonNull HttpServletRequest request,
      @NonNull HttpServletResponse response,
      @NonNull FilterChain filterChain
  ) throws ServletException, IOException {
    final String jwt = request.getHeader("X-Auth-Token");
    if (!StringUtils.hasText(jwt)) {
      filterChain.doFilter(request, response);
      return;
    }

    Authentication authentication = jwtService.getAuthentication(jwt);
    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
        authentication.getName(),
        null,
        authentication.getAuthorities()
    );
    authToken.setDetails(
        new WebAuthenticationDetailsSource().buildDetails(request)

    );
    SecurityContextHolder.getContext().setAuthentication(authToken);
    filterChain.doFilter(request, response);

  }

}
