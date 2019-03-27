package com.test;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author jiHongYuan
 * @Title: Main
 * @ProjectName springSecurityDemo
 * @date 2019/3/2722:37
 */
public class Main {
    public static void main(String[] args) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        System.out.println(bCryptPasswordEncoder.encode("123456"));
    }
}
