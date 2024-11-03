package com.hms;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.swing.*;

public class A {
    public static void main(String[] args) {
        String enPwd = BCrypt.hashpw("testing", BCrypt.gensalt(4));
        System.out.println(enPwd);


    }
}
//1. way to encoding the pasword
//PasswordEncoder en = new BCryptPasswordEncoder();
// System.out.println(en.encode("testing"));

//2. Another way to encode the password.
// BCrypt.gensalt minimum 4= 2^2 rounds and maximum 31= 2^31 rounds.