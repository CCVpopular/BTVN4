package com.example.bt3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.bt3.model.Lop;

@Repository
public interface ILopRepository extends JpaRepository<Lop, Integer> {
} 
