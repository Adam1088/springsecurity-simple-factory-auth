package pl.bykowski.springsecuritysimplefactoryauth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.bykowski.springsecuritysimplefactoryauth.entity.Token;

import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Long> {

    Optional<Token> findByValue(String value);
}
