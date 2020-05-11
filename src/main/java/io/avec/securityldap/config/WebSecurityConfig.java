package io.avec.securityldap.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
//@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

//    @Value("${ldap.urls}")
//    private String ldapUrls;
//
//    @Value("${ldap.base.dn}")
//    private String ldapBaseDn;
//
//    @Value("${ldap.username}")
//    private String ldapSecurityPrincipal;
//
//    @Value("${ldap.password}")
//    private String ldapPrincipalPassword;
//
//    @Value("${ldap.user.dn.pattern}")
//    private String ldapUserDnPattern;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                    .antMatchers("/", "/home").permitAll() // Open for all
                    .anyRequest().authenticated() // All others requires authentication
                    .and()
                .formLogin()
//                    .loginPage("/login") // requires /login page to exist
                    .permitAll() // when login ok, permit all
                    .and()
                .logout()
                    .permitAll();
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                // https://spring.io/guides/gs/authenticating-ldap/
                .ldapAuthentication()
                .userDnPatterns("uid={0},ou=people")
                .groupSearchBase("ou=groups")
                .contextSource()
                .url("ldap://localhost:8389/dc=springframework,dc=org")
                .and()
                .passwordCompare()
                .passwordEncoder(new BCryptPasswordEncoder())
                .passwordAttribute("userPassword");


                // https://medium.com/codeops/spring-boot-security-ldap-example-1ce1bdfc5816
//                .ldapAuthentication()
//                .contextSource()
//                .url(ldapUrls + ldapBaseDn)
//                .managerDn(ldapSecurityPrincipal)
//                .managerPassword(ldapPrincipalPassword)
//                .and()
//                .userDnPatterns(ldapUserDnPattern);
    }
}
