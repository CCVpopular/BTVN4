package com.example.bt3.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.bt3.model.MonHoc;

@Repository
public interface IMonHocRepository extends JpaRepository<MonHoc,String> {
    List<MonHoc> findByTenmonhocContaining(String tenmonhoc);

    @Query(value = "SELECT sv.*, mh.* FROM mon_hoc mh LEFT JOIN sinh_vien_mon_hoc svmh ON mh.ma_mon = svmh.ma_mon LEFT JOIN sinh_vien sv ON svmh.mssv = sv.mssv",nativeQuery = true)
    List<MonHoc> getAllSinhVienMonHoc();
}