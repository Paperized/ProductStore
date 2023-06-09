package com.paperized.productstore.security;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.paperized.productstore.exception.ApiErrorResponse;
import com.paperized.productstore.security.util.SecurityUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
  private final JwtService jwtService;

  public JwtAuthenticationFilter(JwtService jwtService) {
    this.jwtService = jwtService;
  }

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                  FilterChain filterChain) throws ServletException, IOException {
    String authHeader = request.getHeader("Authorization");
    if (authHeader == null || !authHeader.startsWith("Bearer ")) {
      filterChain.doFilter(request, response);
      return;
    }

    String jwt = authHeader.substring(7);
    Claims claims = getClaims(jwt, response);
    if(claims == null)
      return;

    String email = claims.getSubject();
    String idAsString = claims.getId();
    if (email != null && idAsString != null) {
      String[] roles = claims.get("roles").toString().split(",");
      Long id = Long.parseLong(idAsString);
      SecurityUtils.setCurrentAuthentication(id, email, roles, request);
    }

    filterChain.doFilter(request, response);
  }

  /**
   * Needed to handle bad tokens, since this is a filter we cant use our Global Exception Controller
   * @param jwt
   * @param response
   * @return
   * @throws IOException
   */
  private Claims getClaims(String jwt, HttpServletResponse response) throws IOException {
    HttpStatus errorStatus;
    String errorCode , errorMessage;
    try {
      return jwtService.extractAllClaims(jwt);
    } catch (SignatureException | MalformedJwtException | IllegalArgumentException ex) {
      errorStatus = HttpStatus.BAD_REQUEST;
      errorCode = "invalidJwt";
      errorMessage = "Invalid JWT";
    } catch (ExpiredJwtException ex) {
      errorStatus = HttpStatus.UNAUTHORIZED;
      errorCode = "tokenExpired";
      errorMessage = "Expired JWT token";
    } catch (UnsupportedJwtException ex) {
      errorStatus = HttpStatus.INTERNAL_SERVER_ERROR;
      errorCode = "internalError";
      errorMessage = "Internal error";
    }

    ObjectMapper objectMapper = new ObjectMapper();
    String jsonError;
    try {
      jsonError = objectMapper.writeValueAsString(
        ApiErrorResponse.fromErrors(errorStatus, errorCode, errorMessage));
    } catch (JsonProcessingException e) {
      errorStatus = HttpStatus.INTERNAL_SERVER_ERROR;
      jsonError = "{}";
    }

    response.setContentType(MediaType.APPLICATION_JSON_VALUE);
    response.setCharacterEncoding("UTF-8");
    response.addHeader("Access-Control-Allow-Origin", "http://localhost:4200");
    response.setStatus(errorStatus.value());
    response.getWriter().print(jsonError);
    return null;
  }
}
