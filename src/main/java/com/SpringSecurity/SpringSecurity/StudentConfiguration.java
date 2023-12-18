package com.SpringSecurity.SpringSecurity;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class StudentConfiguration extends WebSecurityConfigurerAdapter {
    private static  String DEVLOPER_AUTHORITY="devloper";
    private static String DEVOPS_AUTHORITY="devops";
    private static String TESTER_AUTHORITY="tester";
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("ravi")
                .password("1234")
                .authorities(DEVLOPER_AUTHORITY).accountLocked(false)
                .and()
                .withUser("simran")
                .password("1379")
                .authorities(DEVOPS_AUTHORITY).accountLocked(false)
                .and()
                .withUser("anmol")
                .password("12341379")
                .authorities(DEVOPS_AUTHORITY,DEVLOPER_AUTHORITY).accountLocked(false);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic().and().authorizeHttpRequests()
//                .antMatchers("/tester/**").hasAnyAuthority(TESTER_AUTHORITY)

                .antMatchers(HttpMethod.POST,"/devloper/**").hasAuthority(DEVOPS_AUTHORITY)
                .antMatchers(HttpMethod.POST,"/sre/**").hasAnyAuthority(DEVOPS_AUTHORITY)
                .antMatchers("/devloper/**").hasAnyAuthority(DEVLOPER_AUTHORITY,DEVOPS_AUTHORITY)
                .antMatchers("/devops").hasAnyAuthority(DEVOPS_AUTHORITY)

                .and()
                .formLogin();

    }
    @Bean
    public PasswordEncoder pwe(){
        return NoOpPasswordEncoder.getInstance();
    }
}
