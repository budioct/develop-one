package budhioct.dev.repository;

import budhioct.dev.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Long> {

    Optional<Token> findFirstByToken(String token);

}
