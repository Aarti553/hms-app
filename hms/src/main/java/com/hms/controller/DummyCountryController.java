package com.hms.controller;

import com.hms.entity.AppUser;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/country")
public class DummyCountryController {

   // http://localhost:8080/api/v1/country
        @PostMapping("/addCountry")
        public AppUser addCountry(
                @AuthenticationPrincipal AppUser appUser
                ){
            return appUser;
        }
    }


 /* http://localhost:8080/api/v1/country
        // when you want to access,it should be accessed only when is send with this URL
       //and that token is valid.otherwise this URL should not be accessible.

*any URL that comes with Token the backend code should Automatically extract the token from URl
verify the token and then decide whether this URL i can accept or reject.
*@AuthenticationPrincipal annotation can helps us to find out who is the current user logged in.
*After login only user can give the reviews,and now we now that which user can give the review after login in
we can control from the config file.
 */

