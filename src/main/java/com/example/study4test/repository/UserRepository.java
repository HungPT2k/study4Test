package com.example.study4test.repository;

import com.example.study4test.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

public interface UserRepository extends JpaRepository<Users, Long> {

  boolean existsByUsername(String username);

  Users findByUsername(String username);
  @Transactional
  void deleteByUsername(String username);
}
