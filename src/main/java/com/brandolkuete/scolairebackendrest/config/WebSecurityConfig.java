package com.brandolkuete.scolairebackendrest.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled= true, jsr250Enabled=true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private JwtTokenProvider jwtTokenProvider;

	@Autowired
	private MyUserDetails myUserDetails;

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		// Disable CSRF (cross site request forgery)
		http.cors().and().csrf().disable();
			//.exceptionHandling().authenticationEntryPoint((AuthenticationEntryPoint) jwtTokenProvider); //pour permettre l'envoie des requettes par la methode post

		// No session will be created or used by spring security
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		//http.addFilterBefore(jwtTokenFilter, (Class<? extends Filter>) UsernamePasswordAuthenticationToken.class);

		// Entry points
		http.authorizeRequests()//
		.antMatchers("/scolairerest/api/**").permitAll()//
		.antMatchers("/scolairerest/users/signin").permitAll()//
		.antMatchers("/scolairerest/users/signup").permitAll()
		// Disallow everything else..
		.anyRequest().authenticated();

		// If a user try to access a resource without having enough permissions
		http.exceptionHandling().accessDeniedPage("/login");

		// Apply JWT
		http.apply(new JwtTokenFilterConfigurer(jwtTokenProvider));
	}

	@Override
	public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
		authenticationManagerBuilder.userDetailsService(myUserDetails).passwordEncoder(passwordEncoder());
	}

	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(12);
	}

}