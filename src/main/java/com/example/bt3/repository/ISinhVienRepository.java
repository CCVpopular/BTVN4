package com.example.bt3.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.bt3.model.SinhVien;

@Repository
public interface ISinhVienRepository extends JpaRepository<SinhVien,String> {
    List<SinhVien> findByTensinhvienContaining(String tensinhvien);
}