package com.example.bt3.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bt3.model.Lop;
import com.example.bt3.model.SinhVien;
import com.example.bt3.repository.ISinhVienRepository;

@Service
public class SinhVienService {
    @Autowired
    private ISinhVienRepository sinhvienRepository;

    public List<SinhVien> getAllSinhViens(){
        return sinhvienRepository.findAll();
    }
    public SinhVien getSinhVienById(String Id){
        return sinhvienRepository.findById(Id).orElse(null);
    }
    public void addSinhVien(SinhVien sinhvien){
        sinhvienRepository.save(sinhvien);
    }
    public void deleteSinhVien(String id){
        sinhvienRepository.deleteById(id);
    }
    public void updateSinhVien(SinhVien sinhvien){
        sinhvienRepository.save(sinhvien);
    }

    public List<SinhVien> searchSinhvien(String sinhvien){
        return sinhvienRepository.findByTensinhvienContaining(sinhvien);
    }

    public List<SinhVien> finSinhVienslopList (Lop lop){
        return sinhvienRepository.findByLop(lop);
    }
    
}
