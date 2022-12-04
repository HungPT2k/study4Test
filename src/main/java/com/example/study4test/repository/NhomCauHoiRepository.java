package com.example.study4test.repository;

import com.example.study4test.entity.Part;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NhomCauHoiRepository extends JpaRepository<Part,Long> {
    Part findBySttPhan(int sttPhan);
}
