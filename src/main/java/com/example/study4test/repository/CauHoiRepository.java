package com.example.study4test.repository;

import com.example.study4test.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CauHoiRepository extends JpaRepository<Question,Long> {
    @Query(value = "select * from cau_hoi ch",nativeQuery = true)
    List<Question> getAllByIdd(Long id);
}
