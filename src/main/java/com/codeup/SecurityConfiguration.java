package com.codeup;

import com.codeup.services.UserDetailsLoader;
import com.codeup.services.UserWithRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Created by user on 2/13/17.
 */
@Configuration
@EnableWebSecurity
@ComponentScan(basePackageClasses = UserWithRoles.class)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsLoader userDetails;

    @Bean(name = "passwordEncoder")
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .formLogin()
                // this section is for login itself
                .loginPage("/login")
                .defaultSuccessUrl("/posts") // user's home page, it can be any URL
                .permitAll() // Anyone can go to the login page
            .and()
                // this section is for non-logged in users
                .authorizeRequests()
                .antMatchers("/", "/logout") // anyone can see the home and logout page
                .permitAll()
            .and()
                // this section is for logout itself
                .logout()
                .logoutSuccessUrl("/login?logout") // append a query string value
            .and()
                // restricted area(only logged in users can see the pages in this section)
                .authorizeRequests()
                .antMatchers("/posts/create", "/users/{id}") // only authenticated users can create ads, etc.
                .authenticated()
        ;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetails).passwordEncoder(passwordEncoder());
    }
}