package com.hms.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/country")
public class DummyCountryController {

    // http://localhost:8080/api/v1/Country
    // when you want to access,it should be accessed only when is send with this URL
    //and that token is valid.otherwise this URL should not be accessible.
    @PostMapping
    public String addCountry(){
        return "added";
    }
}

/*any URL that comes with Token the backend code should Automatically extract the token from URl
verify the token and then decide whether this URL i can accept or reject.
 */
