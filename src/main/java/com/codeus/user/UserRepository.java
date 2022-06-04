package com.codeus.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findById(String id);

    Optional<Long> countById(String id);

    Optional<User> findByUUID(String uuid);
}
