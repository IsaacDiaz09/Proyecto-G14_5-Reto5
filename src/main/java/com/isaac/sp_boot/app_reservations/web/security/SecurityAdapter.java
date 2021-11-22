package com.isaac.sp_boot.app_reservations.web.security;

import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.web.bind.annotation.RestController;

@RestController
/**
 * Sobre-escribir la presente clase define que paths o endpoints estaran "bloqueados",
 * retornando un codigo de error 401, tambien las permitidas; sera trabajo del controlador
 * definir si requeriran autenticacion o no.
 */
public class SecurityAdapter extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests(a -> 
        a.antMatchers("/","/api/**", "/error")
        .permitAll()
        .anyRequest()
        .authenticated())
        .exceptionHandling(e -> 
        e.authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED)))
        .oauth2Login();
        
        http.cors().and().csrf().disable();
    }
}
