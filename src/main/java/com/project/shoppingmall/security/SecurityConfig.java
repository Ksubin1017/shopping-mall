package com.project.shoppingmall.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public BCryptPasswordEncoder encodePwd() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/myPage/**").authenticated()
                .antMatchers("/payment").authenticated()
                //.antMatchers("/adminOrderComp").access("hasRole('Role_a')")
                //.antMatchers("/adminOrderNotComp").access("hasRole('Role_a')")
                //.antMatchers("/productEnroll").access("hasRole('Role_a')")
                .anyRequest().permitAll()
                .and()
                .formLogin()
                .usernameParameter("userId")
                .passwordParameter("pwd")
                .loginPage("/loginForm")
                .loginProcessingUrl("/loginComp")
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
                .and()
                .exceptionHandling()
                .accessDeniedPage("/access_denied");

        http.logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .permitAll();

    }

}
