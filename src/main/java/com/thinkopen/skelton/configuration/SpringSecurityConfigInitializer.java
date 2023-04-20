package com.thinkopen.skelton.configuration;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfigInitializer extends 
WebSecurityConfigurerAdapter {
	  @Override
	  protected void configure(HttpSecurity http) throws Exception {
	    http.authorizeRequests().antMatchers("/javax.faces.resource/**")
	        .permitAll().anyRequest().authenticated().and().formLogin();
	    http.logout().deleteCookies("remove").invalidateHttpSession(false).logoutUrl("/logout");
	    http.csrf().disable();
	  }

@Override
protected void configure(AuthenticationManagerBuilder auth) throws 
Exception {
    auth.inMemoryAuthentication()
    .passwordEncoder(NoOpPasswordEncoder.getInstance())
    .withUser("test").password("test123").roles("EMPLOYEE");
    										 
 	}
 }