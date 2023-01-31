package com.elephant.dreamhi.repository;

import com.elephant.dreamhi.model.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User save(User user);

    Optional<User> findByEmail(String email);

    Optional<User> findById(Long id);
}
