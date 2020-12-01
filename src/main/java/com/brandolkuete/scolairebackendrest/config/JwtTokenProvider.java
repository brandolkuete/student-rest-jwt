package com.brandolkuete.scolairebackendrest.config;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;

import com.brandolkuete.scolairebackendrest.entities.Role;
import com.brandolkuete.scolairebackendrest.entities.UserDetailsImpl;
import com.brandolkuete.scolairebackendrest.exception.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


@Component
public class JwtTokenProvider {

	@Autowired
	private MyUserDetails myUserDetails;

	public String createToken(Authentication authentication) {

		UserDetailsImpl userDetails= (UserDetailsImpl) authentication.getPrincipal();
		
		//Claims c'est la premiere partie du token
		final Claims claims = Jwts.claims().setSubject(userDetails.getUsername());
		claims.put("auth", userDetails.getAuthorities());

		return Jwts.builder()
				.setClaims(claims)
				.setExpiration(new Date(System.currentTimeMillis()+SecurityConstants.EXPIRATION_TIME))
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
}
