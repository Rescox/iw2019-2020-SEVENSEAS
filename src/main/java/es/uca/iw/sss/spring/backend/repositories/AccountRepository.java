package es.uca.iw.sss.spring.backend.repositories;

import es.uca.iw.sss.spring.backend.entities.Account;
import es.uca.iw.sss.spring.backend.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;
import java.util.Set;

public interface AccountRepository extends JpaRepository<Account, Long> {
    List<Account> findByUser(User user);

    int countByServiceName (String servicename);

}
