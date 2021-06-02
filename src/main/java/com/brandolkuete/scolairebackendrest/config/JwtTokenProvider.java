package com.brandolkuete.scolairebackendrest.config;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.brandolkuete.scolairebackendrest.entities.UserDetailsImpl;
import com.brandolkuete.scolairebackendrest.exception.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


@Component
public class JwtTokenProvider implements AuthenticationEntryPoint {

	@Autowired
	private MyUserDetails myUserDetails;

	public String createToken(Authentication authentication) {

		UserDetailsImpl userDetails= (UserDetailsImpl) authentication.getPrincipal();
		
		//Claims c'est la premiere partie du token
		final Claims claims = Jwts.claims().setSubject(userDetails.getUsername());
		claims.put("auth", userDetails.getAuthorities());

		LocalDate date = LocalDate.now().plusDays(1);
		Instant instant = date.atStartOfDay(ZoneId.systemDefault()).toInstant();

		return Jwts.builder()
				.setClaims(claims)
				.setExpiration(Date.from(instant))
				.signWith(SignatureAlgorithm.HS256, SecurityConstants.SECRET)
				.compact();
	}

	public Authentication getAuthentication(String token) {
		final UserDetails userDetails = myUserDetails.loadUserByUsername(getUsername(token));
		return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
	}

	public String getUsername(String token) {
		return Jwts.parser().setSigningKey(SecurityConstants.SECRET).parseClaimsJws(token).getBody().getSubject();
	}

	public String resolveToken(HttpServletRequest req) {
		final String bearerToken = req.getHeader("Authorization");
		if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
			return bearerToken.substring(7);
		}
		return null;
	}

	public boolean validateToken(String token) {
		try {
			Jwts.parser().setSigningKey(SecurityConstants.SECRET).parseClaimsJws(token);
			return true;
		} catch (JwtException | IllegalArgumentException e) {
			throw new CustomException("Expired or invalid JWT token", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
		httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Error: Unauthorized");
	}
}
