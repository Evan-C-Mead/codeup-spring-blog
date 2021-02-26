package com.codeup.springblog;

import com.codeup.springblog.services.UserDetailsLoader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final UserDetailsLoader userDetailsLoader;

    public SecurityConfiguration(UserDetailsLoader userDetailsLoader) {
        this.userDetailsLoader = userDetailsLoader;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsLoader).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
                // define how to login
        http.formLogin()
            .loginPage("/login")
            .defaultSuccessUrl("/posts")
            .permitAll()
            .and()
            .logout()
            .logoutSuccessUrl("/login?logout")
                // define pages where user doesn't need to be logged in
            .and()
            .authorizeRequests()
            .antMatchers("/", "/sign-up", "/posts")
            .permitAll()
                // define pages where user must be logged in
            .and()
            .authorizeRequests()
            .antMatchers("/posts/edit/{id}", "/posts/create")
            .authenticated();
    }

}
