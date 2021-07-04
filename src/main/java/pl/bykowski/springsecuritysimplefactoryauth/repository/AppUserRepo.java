package pl.bykowski.springsecuritysimplefactoryauth.repository;

import pl.bykowski.springsecuritysimplefactoryauth.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AppUserRepo extends JpaRepository<AppUser, Long> {

    Optional<AppUser> findByLogin(String login);

}
