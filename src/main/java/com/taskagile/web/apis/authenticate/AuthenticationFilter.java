package com.taskagile.web.apis.authenticate;

import com.taskagile.utils.JsonUtils;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class AuthenticationFilter extends AbstractAuthenticationProcessingFilter {

  public AuthenticationFilter() {
    super(new AntPathRequestMatcher("/api/authentications", "POST"));
  }

  @Override
  public Authentication attemptAuthentication(final HttpServletRequest request, final HttpServletResponse response) throws AuthenticationException, IOException{
    log.debug("Processing login request");

    final String requestBody = String.valueOf(request.getReader());
    final LoginRequest loginRequest = JsonUtils.toObject(requestBody, LoginRequest.class);
    if (loginRequest == null || loginRequest.isInvalid()) {
      throw new InsufficientAuthenticationException("Invalid authentication request");
    }

    final UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(loginRequest.username, loginRequest.password);
    return this.getAuthenticationManager().authenticate(token);
  }

  @Getter
  @Setter
  static class LoginRequest {
    private String username;
    private String password;

    public boolean isInvalid() {
      return username.isBlank() || password.isBlank();
    }

  }
}
