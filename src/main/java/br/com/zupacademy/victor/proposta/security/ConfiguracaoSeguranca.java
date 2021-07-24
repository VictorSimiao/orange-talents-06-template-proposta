package br.com.zupacademy.victor.proposta.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;

import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;

@Configuration
public class ConfiguracaoSeguranca extends WebSecurityConfigurerAdapter {

	  @Override
	    protected void configure(HttpSecurity http) throws Exception{
		  http.authorizeRequests(authorizeRequests ->
          authorizeRequests
          			.antMatchers(GET, "actuator/**").hasAuthority("SCOPE_actuator:red")
          			.antMatchers(POST, "api/propostas/**").hasAuthority("SCOPE_propostas:write")
          			.antMatchers(GET, "api/propostas/**").hasAuthority("SCOPE_propostas:read")
          			.antMatchers(POST, "api/cartoes/**").hasAuthority("SCOPE_cartoes:write")
          			.antMatchers(GET, "api/cartoes/**").hasAuthority("SCOPE_cartoes:red")
          			
          			.anyRequest().authenticated()
				  )
		  .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt);
	  }

}

