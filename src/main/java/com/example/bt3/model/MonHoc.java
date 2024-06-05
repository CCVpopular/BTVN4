package com.example.bt3.model;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity(name = "MonHoc")
@Table(name = "MonHoc")
public class MonHoc {
    @Id
    @Column(name = "MaMon", length = 10)
    @Size(min = 1, max = 10, message = "Ma Mon Phai Co Ky Tu Tu 1 -> 10")
    private String maMon;

    @Size(min = 5, max = 50, message = "Ten Mon Hoc Phai Tu 5 -> 50 Ky Tu")
    @Column(name = "TenMonHoc", length = 50)
    private String tenmonhoc;
    
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "SinhVien_MonHoc",
        joinColumns = {@JoinColumn(name = "MaMon")},
        inverseJoinColumns = {@JoinColumn(name = "MSSV")}
    )
    private Set<SinhVien> sinhViens;
}
