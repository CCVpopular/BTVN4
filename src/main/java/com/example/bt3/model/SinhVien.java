package com.example.bt3.model;

import java.util.Date;
import java.util.Set;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity(name = "SinhVien")
@Table(name = "SinhVien")
public class SinhVien {
    @Id
    @Column(name = "MSSV", length = 10)
    @Size(min = 10, max = 10, message = "MSSV phải có 10 chữ số")
    private String mssv;

    @Size(max = 50,message = "Ten Sinh Vien Khong Qua 50 Ky Tu")
    @NotNull(message = "Ten Sinh Vien Khong Duoc De Trong")
    @Column(name = "TenSinhVien", length = 50)
    private String tensinhvien;

    @Past(message = "Ngay Sinh Phai La Ngay Trong Qua Khu")
    @Temporal(TemporalType.DATE)
    @Column(name = "NgaySinh")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date ngaysinh;

    @Email(message = "Email Phai Hop Le")
    @NotNull(message = "Email Khong Duoc De Trong")
    @Column(name = "Email")
    private String email;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
        name = "MaLop",
        referencedColumnName = "MaLop",
        foreignKey = @ForeignKey(name = "Fk_SINHVIEN_LOP")
    )
    private Lop lop;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "SinhVien_MonHoc",
        joinColumns = {@JoinColumn(name = "MSSV")},
        inverseJoinColumns = {@JoinColumn(name = "MaMon")}
    )
    private Set<MonHoc> monHocs;
}
