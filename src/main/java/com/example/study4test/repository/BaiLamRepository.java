package com.example.study4test.repository;

import com.example.study4test.entity.Assignment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BaiLamRepository extends JpaRepository<Assignment,Long> {
}
