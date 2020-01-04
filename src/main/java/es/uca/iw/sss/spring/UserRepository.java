package es.uca.iw.sss.spring;

import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User,Long> {
    User findById(int id);

    User findByUsername(String user);

    User findByUsername(User userInstance);

    User findByEmail(String email);

    User findByUsernameStartsWithIgnoreCase(String lastName);
}
