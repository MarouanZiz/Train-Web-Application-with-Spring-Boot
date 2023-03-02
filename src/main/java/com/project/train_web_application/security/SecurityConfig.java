package com.project.train_web_application.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        PasswordEncoder passwordEncoder = passwordEncoder();
     System.out.println("Code : "+passwordEncoder.encode("123"));
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery("select \"username\" as principal, \"password\" as credentials, \"active\" from \"user\" where \"username\"=?")
                .authoritiesByUsernameQuery("select \"username\" as principal, \"role_name\" as role from \"user\" u, \"role\" r, \"role_users\" ru where u.\"user_id\"=ru.\"users_user_id\" and r.\"role_id\"=ru.\"roles_role_id\" and u.\"username\"=?")
                .rolePrefix("ROLE_")
                .passwordEncoder(passwordEncoder);

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin();

//        http.authorizeRequests().anyRequest().authenticated();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
