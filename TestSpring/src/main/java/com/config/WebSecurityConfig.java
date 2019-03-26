package com.config;

import com.security.MyAbstractSecurityInterceptor;
import com.security.MyAccessDecisionManager;
import com.security.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author jiHongYuan
 * @Title: WebSecurityConfig
 * @ProjectName TestSpring
 * @date 2019/3/2116:56
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private MyUserDetailsService myUserDetailsService;
    @Autowired
    private MyAbstractSecurityInterceptor myAbstractSecurityInterceptor;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(myUserDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                    .anyRequest().authenticated()
                    .antMatchers("/admin/**").hasRole("ADMIN")
                    .and()

                .logout()
                    .logoutUrl("/logout")
                    .invalidateHttpSession(true)
                    .and()

                .formLogin()
                    .loginPage("/login.jsp")
                    .permitAll()
                    .successForwardUrl("/admin/index.jsp")
                    .and()

                .httpBasic()
                    .and()
                .addFilterBefore(myAbstractSecurityInterceptor, FilterSecurityInterceptor.class);
    }

}
