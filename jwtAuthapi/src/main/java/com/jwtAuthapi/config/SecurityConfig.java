package com.jwtAuthapi.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.jwtAuthapi.filter.JwtFilter;
import com.jwtAuthapi.service.CustomUserDetailsService;

import springfox.documentation.swagger2.annotations.EnableSwagger2;


@Configuration
@EnableGlobalMethodSecurity(
	    prePostEnabled = true)
@EnableWebMvc
public class SecurityConfig  {
	
	public static final String [] public_url = {
			"/authenticate",
			"/v2/api-docs",
			"/v3/api-docs",
			"/swagger-ui/**",
			"/swagger-resources/**",
			"/webjars/**"
	};
	
	@Autowired
    private CustomUserDetailsService userDetailsService;
	
	@Autowired
    private JwtFilter jwtFilter;
	

	
	  @Bean
	  public DaoAuthenticationProvider authenticationProvider() {
	      DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
	       
	      authProvider.setUserDetailsService(userDetailsService);
	      authProvider.setPasswordEncoder(passwordEncoder());
	   
	      return authProvider;
	  }
	  
	  @Bean
	    public PasswordEncoder passwordEncoder(){
			
	        return NoOpPasswordEncoder.getInstance();
	    }

	 @Bean
	  public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
	    return authConfig.getAuthenticationManager();
	  }
	 @Bean
	  public  SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	        http.csrf().disable().authorizeRequests().antMatchers(public_url).permitAll()
	        .anyRequest().authenticated() 
	        .and().exceptionHandling().and().sessionManagement()
	         .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);;
			return http.build();
	    }
	 @Bean
	  public InternalResourceViewResolver defaultViewResolver() {
	    return new InternalResourceViewResolver();
	  }
	    
}
