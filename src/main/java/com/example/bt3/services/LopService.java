package com.example.bt3.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bt3.model.Lop;
import com.example.bt3.repository.ILopRepository;

@Service
public class LopService {
    @Autowired
    private ILopRepository lopRepository;
    public List<Lop> getAllLops(){
        return lopRepository.findAll();
    }
    public Lop getLopById(int Id){
        return lopRepository.findById(Id).orElse(null);
    }
    public void addLop(Lop lop){
        lopRepository.save(lop);
    }
    public void deleteLop(int id){
        lopRepository.deleteById(id);
    }
    public void updateLop(Lop lop){
        lopRepository.save(lop);
    }
    public List<Lop> searchLop(String lop){
        return lopRepository.findByTenlopContaining(lop);
    }
}
    