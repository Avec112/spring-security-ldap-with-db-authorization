package io.avec.securityldap.config;

import io.avec.securityldap.ldap.LdapUser;
import lombok.Getter;
import org.springframework.ldap.core.DirContextAdapter;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.ldap.userdetails.LdapUserDetails;
import org.springframework.security.ldap.userdetails.LdapUserDetailsMapper;
import org.springframework.security.ldap.userdetails.UserDetailsContextMapper;

import javax.naming.NamingException;
import javax.naming.directory.Attributes;
import java.util.Collection;

@Getter
public class CustomUserDetailsMapper extends LdapUserDetailsMapper {

    private LdapUser ldapUser;
    private String commonName;

    @Override
    public UserDetails mapUserFromContext(DirContextOperations ctx, String username, Collection<? extends GrantedAuthority> authorities) {
        Attributes attributes = ctx.getAttributes();
        LdapUserDetails ldapUserDetails = (LdapUserDetails) super.mapUserFromContext(ctx, username, authorities);
        try {
            // Firstname and Lastname as stored in LDAP
            commonName = attributes.get("cn").get().toString();
        } catch (NamingException e) {
            e.printStackTrace();
        }
        ldapUser = new LdapUser(ldapUserDetails, commonName);

        return ldapUser;
    }

//    @Override
//    public void mapUserToContext(UserDetails userDetails, DirContextAdapter dirContextAdapter) {
//
//    }
}
