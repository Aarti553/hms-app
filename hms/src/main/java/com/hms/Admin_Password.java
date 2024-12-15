package com.hms;

import org.springframework.security.crypto.bcrypt.BCrypt;

public class Admin_Password {
    public static void main(String[] args) {
        System.out.println(BCrypt.hashpw("testing",BCrypt.gensalt(5)));
    }
}
