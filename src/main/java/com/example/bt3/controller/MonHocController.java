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

import com.example.bt3.model.Lop;
import com.example.bt3.model.MonHoc;
import com.example.bt3.services.MonHocService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/monhoc")
public class MonHocController {
    @Autowired
    private MonHocService monhocService;

    @GetMapping
    public String showAllMonHoc(Model model) {
        List<MonHoc> listmonhoc = monhocService.getAllMonHocs();
        model.addAttribute("listmonhoc",listmonhoc);
        return "monhoc/list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("monhoc", new MonHoc());
        return "monhoc/add";
    }

    @PostMapping("/add")
    public String addMonHoc(@Valid @ModelAttribute("monhoc") MonHoc monhoc,BindingResult result) {
        if (result.hasErrors()) {
            return "/monhoc/add";
        }
        monhocService.addMonHoc(monhoc);  
        return "redirect:/monhoc";
    }

    @GetMapping("/update/{id}")
    public String editMonHoc(@PathVariable("id") String id, Model model) {
        MonHoc listmonhoc = monhocService.getMonHocById(id);
        if(listmonhoc !=null){
            model.addAttribute("monhoc",listmonhoc);
            return "/monhoc/update";
        }
        return "redirect:/monhoc";
    }

    @PostMapping("/update/{id}")
    public String editMonHoc(@Valid @ModelAttribute("monhoc") MonHoc monhoc,BindingResult result,@PathVariable("id") String id) {
        if (result.hasErrors()) {
            return "/monhoc/update";
        }
        MonHoc listmonhoc = monhocService.getMonHocById(id);
        listmonhoc.setTenmonhoc(monhoc.getTenmonhoc());
        monhocService.updateMonHoc(listmonhoc);
        return "redirect:/monhoc";
    }

    @GetMapping("/delete/{id}")
    public String deleteMonHoc(@PathVariable("id") String id) {
        monhocService.deleteMonHoc(id);
        return "redirect:/monhoc";
    }

    @GetMapping("/search")
    public String searchLop(@RequestParam(name = "keyword",required = false,defaultValue = "") String keyword, Model model) {
        List<MonHoc> listmonhoc = monhocService.searchMonhoc(keyword);
        model.addAttribute("listmonhoc",listmonhoc);
        return "monhoc/list";
    }
}
