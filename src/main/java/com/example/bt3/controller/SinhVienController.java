package com.example.bt3.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.bt3.model.MonHoc;
import com.example.bt3.model.SinhVien;
import com.example.bt3.services.LopService;
import com.example.bt3.services.MonHocService;
import com.example.bt3.services.SinhVienService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/sinhvien")
public class SinhVienController {
    @Autowired
    private SinhVienService sinhvienService;

    @Autowired
    private MonHocService monhocService;

    @Autowired
    private LopService lopService;

    @GetMapping
    public String showAllSinhVien(Model model) {
        model.addAttribute("listsinhvien",sinhvienService.getAllSinhViens());
        return "sinhvien/list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("sinhvien", new SinhVien());
        model.addAttribute("dsMonHoc", monhocService.getAllMonHocs());
        model.addAttribute("lops", lopService.getAllLops());
        return "sinhvien/add";
    }

    @PostMapping("/add")
    public String addSinhVien(@Valid @ModelAttribute("sinhvien") SinhVien sinhvien,BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("sinhvien", new SinhVien());
            model.addAttribute("dsMonHoc", monhocService.getAllMonHocs());
            model.addAttribute("lops", lopService.getAllLops());
            return "redirect:/sinhvien/add";
        }    
        sinhvienService.addSinhVien(sinhvien);
        return "redirect:/sinhvien";
    }

    @GetMapping("/update/{id}")
    public String editSinhVien(@PathVariable("id") String id, Model model) {
        SinhVien listsinhvien = sinhvienService.getSinhVienById(id);
        if(listsinhvien !=null){
            model.addAttribute("sinhvien",listsinhvien);
            model.addAttribute("dsMonHoc", monhocService.getAllMonHocs());
            model.addAttribute("lops", lopService.getAllLops());
            return "/sinhvien/update";
        }
        return "redirect:/sinhvien";
    }

    @PostMapping("/update/{id}")
    public String editSinhVien(@Valid @ModelAttribute("sinhvien") SinhVien sinhvien,BindingResult result,@PathVariable("id") String id, Model model) {
        if (result.hasErrors()) {
            SinhVien listsinhvien = sinhvienService.getSinhVienById(id);
            model.addAttribute("sinhvien",listsinhvien);
            model.addAttribute("dsMonHoc", monhocService.getAllMonHocs());
            model.addAttribute("lops", lopService.getAllLops());
            return "/sinhvien/update";
        }
        SinhVien listsinhvien = sinhvienService.getSinhVienById(id);
        listsinhvien.setTensinhvien(sinhvien.getTensinhvien());
        listsinhvien.setNgaysinh(sinhvien.getNgaysinh());
        listsinhvien.setEmail(sinhvien.getEmail());
        listsinhvien.setLop(sinhvien.getLop());
        sinhvienService.updateSinhVien(listsinhvien);
        return "redirect:/sinhvien";
    }

    @GetMapping("/delete/{id}")
    public String deleteSinhVien(@PathVariable("id") String id) {
        sinhvienService.deleteSinhVien(id);
        return "redirect:/sinhvien";
    }

    @GetMapping("/search")
    public String searchSinhvien(@RequestParam(name = "keyword",required = false,defaultValue = "") String keyword, Model model) {
        List<SinhVien> listsinhvien = sinhvienService.searchSinhvien(keyword);
        model.addAttribute("listsinhvien",listsinhvien);
        return "sinhvien/list";
    }
}
