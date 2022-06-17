package com.shopme.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
//
//import com.shopme.security.oauth.CustomerOAuth2UserService;
//import com.shopme.security.oauth.OAuth2LoginSuccessHandler;

import com.shopme.security.oauth.CustomerOAuth2UserService;
import com.shopme.security.oauth.OAuth2LoginSuccessHandler;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//
	@Autowired private CustomerOAuth2UserService oAuth2UserService;
	@Autowired private OAuth2LoginSuccessHandler oauth2LoginHandler;
	@Autowired private DatabaseLoginSuccessHandler databaseLoginHandler;
//	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	//Open Authorization”, is a standard designed to allow a website or application to access resources hosted by other web apps on behalf of a user.

	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/account_details", "/update_account_details",
					"/cart","/address_book/**","/checkout","/place_order", "/process_paypal_order").authenticated()
			.anyRequest().permitAll()
			.and()
			.formLogin()
				.loginPage("/login")
				.usernameParameter("email")
				.successHandler(databaseLoginHandler)
				.permitAll()
			.and()
			.oauth2Login()
				.loginPage("/login")
				.userInfoEndpoint()
				.userService(oAuth2UserService)
				.and()
				.successHandler(oauth2LoginHandler)
				.and()
			.logout().permitAll()
			.and()
			.rememberMe()
				.key("1234567890_aBcDeFgHiJkLmNoPqRsTuVwXyZ")
				.tokenValiditySeconds(14 * 24 * 60 * 60)
			.and()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.ALWAYS);
//			;			
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/images/**", "/js/**", "/webjars/**");
	}

	@Bean
	public UserDetailsService userDetailsService() {
		return new CustomerUserDetailsService();
	}
//	DaoAuthenticationProvider is a simple authentication provider that uses a Data Access Object (DAO) to retrieve user information 
//	from a relational database.It leverages a UserDetailsService (as a DAO) in order to lookup the username, password and GrantedAuthority s.
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

		authProvider.setUserDetailsService(userDetailsService());
		authProvider.setPasswordEncoder(passwordEncoder());

		return authProvider;
	}	
}