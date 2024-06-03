package com.example.bt3.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bt3.model.MonHoc;
import com.example.bt3.repository.IMonHocRepository;

@Service
public class MonHocService {
    @Autowired
    private IMonHocRepository monHocRepository;
    public List<MonHoc> getAllMonHocs(){
        return monHocRepository.findAll();
    }
    public MonHoc getMonHocById(String Id){
        return monHocRepository.findById(Id).orElse(null);
    }
    public void addMonHoc(MonHoc monhoc){
        monHocRepository.save(monhoc);
    }
    public void deleteMonHoc(String id){
        monHocRepository.deleteById(id);
    }
    public void updateMonHoc(MonHoc monhoc){
        monHocRepository.save(monhoc);
    }
}
