package io.avec.securityldap.data.authorities;

import io.avec.securityldap.data.user.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuthoritiesRepository extends JpaRepository<Authorities, Long> {

    List<Authorities> findAllByUser(MyUser user);
}
