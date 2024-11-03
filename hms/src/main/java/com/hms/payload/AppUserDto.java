package com.hms.payload;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AppUserDto {
    private String username;
    private String name;
    private String email;
    private String password;
}

