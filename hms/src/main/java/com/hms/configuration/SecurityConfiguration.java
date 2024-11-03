package com.hms.configuration;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.AuthorizationFilter;

@Configuration
public class SecurityConfiguration {
 private JWTFilter jwtFilter;

    public SecurityConfiguration(JWTFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http
    ) throws Exception{
        //h(cd)2
        http.csrf().disable().cors().disable();
     http.addFilterBefore(jwtFilter,AuthorizationFilter.class);
        //haap
        http.authorizeHttpRequests().anyRequest().permitAll();
       return http.build();

    }
}


 /* note.1. Csrf-Cross Site Request Forgery attack
        when I enable csrf this will avoid Cross Site Request Forgery attacks in your Application.
        Cors-when I enable your Api is accessible only for certain websites ,
        or certain Clients Apart from that cannot be accessed.
        when I develop an API can you tell where this API I can get access?
        Obviously it will be accessed from some Frontend Framework.e.g Angular,React or any other framework.
        note2.@Bean will hand over the object to Spring Ioc.and now Spring Ioc will manage this Object.
       * @Configuration annotation will run automatically.
       *SecurityFilterChain will filter the which URL who can access the which URL.
       *401 status code which is basically for unAuthorized access.
                 */