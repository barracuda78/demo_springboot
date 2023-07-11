package com.example.demo.repository;

import com.example.demo.entity.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuthorityRepository extends JpaRepository<Authority, Long> {

    List<Authority> findByUsers_id(long id);

}
