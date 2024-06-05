package com.example.bt3.model;

import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity(name = "Lop")
@Table(name = "Lop")
public class Lop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MaLop")
    private int malop;
    @Size(min = 1, max = 7, message = "Ten Lop Chi Chua Toi Da 7 Ky Tu")
    @NotNull(message = "Ten Lop Khong Duoc De Trong")
    @Column(name = "TenLop", length = 7)
    private String tenlop;
    @OneToMany(
        mappedBy = "lop",
        cascade = CascadeType.ALL,
        fetch = FetchType.LAZY,
        orphanRemoval = true
    )
    private Set<SinhVien> sinhViens;
}
