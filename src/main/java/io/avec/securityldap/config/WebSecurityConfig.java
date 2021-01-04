package io.avec.securityldap.config;

import io.avec.securityldap.data.authorities.AuthoritiesRepository;
import io.avec.securityldap.data.user.MyUserRepository;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final AuthoritiesRepository authoritiesRepository;
    private final MyUserRepository myUserRepository;

    public WebSecurityConfig(AuthoritiesRepository authoritiesRepository, MyUserRepository myUserRepository) {
        this.authoritiesRepository = authoritiesRepository;
        this.myUserRepository = myUserRepository;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                    .antMatchers("/").permitAll() // Open for all
                    .anyRequest().authenticated() // All others requires authentication
                    .and()
                .formLogin()
                    .defaultSuccessUrl("/hello", true)
                    .and()
                .logout().logoutSuccessUrl("/");
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                // https://spring.io/guides/gs/authenticating-ldap/
                .ldapAuthentication()
                .ldapAuthoritiesPopulator(new CustomAuthoritiesPopulator(authoritiesRepository, myUserRepository))
                .userDetailsContextMapper(new CustomUserDetailsMapper())
                .userDnPatterns("uid={0},ou=people")
                .groupSearchBase("ou=groups")
                .contextSource()
                .url("ldap://localhost:8389/dc=springframework,dc=org")
                .and()
                .passwordCompare()
                .passwordEncoder(new BCryptPasswordEncoder())
                .passwordAttribute("userPassword");

    }

    /* https://stackoverflow.com/questions/52973232/spring-security-ldap-authentication-and-gather-user-details-from-local-database
    // https://medium.com/@viraj.rajaguru/how-to-use-spring-security-to-authenticate-with-microsoft-active-directory-1caff11c57f2
    // Also https://www.ziaconsulting.com/developer-help/spring-security-active-directory/
    // And this one https://tcl-digitrade.com/2017/01/12/spring-boot-active-directory-authentication/
    // and https://javarevisited.blogspot.com/2018/07/ldap-authentication-active-directory-authentication-java-spring-security-example.html
    // https://geeks18.com/?p=474 Nice figure showing different authentication optios

    // this replaces
    @Bean
    public ActiveDirectoryLdapAuthenticationProvider activeDirectoryLdapAuthenticationProvider(){
        ActiveDirectoryLdapAuthenticationProvider activeDirectoryLdapAuthenticationProvider = new
                ActiveDirectoryLdapAuthenticationProvider("pizzashop.com.us", "ldap://10.100.100.100:389/");
        return activeDirectoryLdapAuthenticationProvider;
    }*/

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers(
                "/h2-console/**"
        );

    }

}
