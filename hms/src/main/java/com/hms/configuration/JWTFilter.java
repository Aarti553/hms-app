package com.hms.configuration;

import com.hms.entity.AppUser;
import com.hms.repository.AppUserRepository;
import com.hms.service.JWTService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

@Component //i can do dependency injection
public class JWTFilter extends OncePerRequestFilter {
    private JWTService jwtService;
    private AppUserRepository appUserRepository;

    public JWTFilter(JWTService jwtService, AppUserRepository appUserRepository) {
        this.jwtService = jwtService;
        this.appUserRepository = appUserRepository;
    }

    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        String token = request.getHeader("Authorization");
        System.out.println(token);
        if(token!=null && token.startsWith("Bearer ")){
            String tokenVal = token.substring(8,token.length()-1);
          //  System.out.println(tokenVal);
            String username = jwtService.getUsername(tokenVal);
           // System.out.println(username);
            Optional<AppUser> opUsername = appUserRepository.findByUsername(username);
            if (opUsername.isPresent()){

            }
        }
        filterChain.doFilter(request, response);//run after login
    }
}





/* filter it means the incoming request coming with the token if valid accept not valid filter.
*firstly submitting username and password to the backend code ,This has been implemented.
 if these are valid ,it returns back a JWT token.The subsequent request that i made along with that the
 JWT token will go.the Second URL that i am accessing now along with Token will go,and this Token is
 need to be verified in the backend.if token above verification is valid give the response back.
 not valid accordingly tell token Invalid.
 * i have to firstly work on the subsequent URLS that comes with Token that's how i am protecting the URL
that needs to be accessed after you logged in.how with the Token.The Url is accessible after you login
will be protected by a Token.that token will decide this URL can be processed or Rejected.

*  HttpServletRequest request:- nothing but reference variable,whenever incoming request comes Automatically
that incoming request has to come here.when that incoming request has a token.
the incoming url with the token will Automatically come to this request object.all happen internally behind the scene.
* if any URL comes with the token that url automatically go to the request object.
* incoming URL has a Header, and inside the header there are so many key value pairs,one of the key value pair in that incoming
http URL is Authorization and the token.Now from that header i have to get the value that the stored inside the Authorization key.
* when i submit that URL,with that token url will come to this object and from this object we are going now extract the token.but
* where is that token is stored in this header - under Authorization key.
* DoFilter method is the designed in the way that whenever a URL comes with the token that url Automatically comes with
 DoFilter method.
 * Authentication- username ,password are invalid
 * Authorization- only we can access some features as a user.not All
 */