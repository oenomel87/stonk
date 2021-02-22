package io.oenomel.stonk.app.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long>, UserCustomRepository {

    Optional<UserEntity> findByUserId(Long userId);

    Optional<UserEntity> findByEmail(String email);
}
