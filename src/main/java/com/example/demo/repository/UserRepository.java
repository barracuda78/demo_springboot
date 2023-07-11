package com.example.demo.repository;

import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    @Query(
            "SELECT DISTINCT user FROM User user "
                    + "INNER JOIN FETCH user.authorities authorities "
                    + "WHERE user.username = :username")
    Optional<User> findByUsernameWithFetchedAuthorities(String username);

}
