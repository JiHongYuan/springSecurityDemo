package com.config;

import com.security.MyAbstractSecurityInterceptor;
import com.security.handler.MyAccessDeniedHandler;
import com.security.MyUserDetailsService;
import com.security.handler.MyAuthenticationFailureHandler;
import com.security.handler.MyAuthenticationSuccessHandler;
import com.security.handler.MyLogoutSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

/**
 * @author jiHongYuan
 * @Title: WebSecurityConfig
 * @ProjectName TestSpring
 * @date 2019/3/2116:56
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    // 查用户的role
    @Autowired
    private MyUserDetailsService myUserDetailsService;

    @Autowired
    private MyAbstractSecurityInterceptor myAbstractSecurityInterceptor;
    // 没权限的到这里
    @Autowired
    private MyAccessDeniedHandler myAccessDeniedHandler;
    @Autowired
    private MyAuthenticationSuccessHandler myAuthenticationSuccessHandler;
    @Autowired
    private MyAuthenticationFailureHandler myAuthenticationFailureHandler;
    @Autowired
    private MyLogoutSuccessHandler myLogoutSuccessHandler;
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(myUserDetailsService).passwordEncoder(new BCryptPasswordEncoder());;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                    .anyRequest().authenticated()
                    .and()
                .logout()
                    .logoutUrl("/logout")
                    .invalidateHttpSession(true)
                    .logoutSuccessHandler(myLogoutSuccessHandler)
                    .and()

                .formLogin()
                    .loginPage("/login.jsp")
                    .permitAll()
                    .successHandler(myAuthenticationSuccessHandler)
                    .failureHandler(myAuthenticationFailureHandler)
                    .and()

                .httpBasic()
                    .and()

                .addFilterBefore(myAbstractSecurityInterceptor, FilterSecurityInterceptor.class)
                    .exceptionHandling()
                    .accessDeniedHandler(myAccessDeniedHandler)
                    .and()
                .csrf().disable();
    }

}
