package com.onlinepetconsultation.configuration;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.onlinepetconsultation.security.JWTAuthenticationEntryPoint;
import com.onlinepetconsultation.security.JwtAuthenticationFilter;
import com.onlinepetconsultation.services.CustomAdminDetailService;
import com.onlinepetconsultation.services.CustomUserDetailService;
import com.onlinepetconsultation.util.Roles;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

//	private final JwtAuthenticationFilter authenticationFilter;

	@Autowired
	private JWTAuthenticationEntryPoint authenticationEntryPoint;
	@Autowired
	@Qualifier("userdetailservices")
	private CustomUserDetailService userDetailService;
	@Autowired
	@Qualifier("admindetailservices")
	private CustomAdminDetailService adminDetailService;
	@Autowired
	private JwtAuthenticationFilter authenticationFilter;

	private static final String[] AUTH_WHITELIST = { "/api/v1/auth/**", "/v3/api-docs/**", "/v3/api-docs.yaml/",
			"/swagger-ui/**", "/swagger-ui.html" };

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.csrf(AbstractHttpConfigurer::disable)
				.authorizeHttpRequests(request -> request.requestMatchers(AUTH_WHITELIST).permitAll()
						.requestMatchers("/onlinepetconsultantion/users","/onlinepetconsultantion/users/login","/onlinepetconsultantion/admins/login").permitAll()
						.requestMatchers("/onlinepetconsultantion/users/**", "/opc/bs/**").authenticated()
						.requestMatchers( "/onlinepetconsultantion/admins/**","/opc/bs/search/**").hasAnyAuthority(Roles.ADMIN.name())
						.anyRequest().authenticated())
				.exceptionHandling(ex -> ex.authenticationEntryPoint(authenticationEntryPoint))
				.sessionManagement(manager -> manager.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class);
		return http.build();
	}

	@Bean
	public AuthenticationProvider authenticationProvider(UserDetailsService userService,
			PasswordEncoder passwordEncoder) {

		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userService);
		authenticationProvider.setPasswordEncoder(passwordEncoder);
		return authenticationProvider;
	}

	@Bean
	@Scope(scopeName=ConfigurableBeanFactory.SCOPE_PROTOTYPE)
	public PasswordEncoder passwordEncoder() {

		return new BCryptPasswordEncoder();
	}
	
	@Bean(name = "adminManger")
	public AuthenticationManager adminAuthenticationManager(CustomAdminDetailService adminDetailService,
			PasswordEncoder encoder) {
		return authenticationManager(adminDetailService, encoder);
	}

	private AuthenticationManager authenticationManager(UserDetailsService userDetailService, PasswordEncoder encoder) {
		List<AuthenticationProvider> authenticationProviders = Arrays
				.asList(authenticationProvider(userDetailService, encoder));
		return new ProviderManager(authenticationProviders);
	}

	@Primary
	@Bean(name = "userManger")
	public AuthenticationManager userAuthenticationManager(CustomUserDetailService customUserDetailService,
			PasswordEncoder encoder) {
		return authenticationManager(customUserDetailService, encoder);
	}

}
