package com.example.bt3.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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

    // @GetMapping("/update/{id}")
    // public String editLop(@PathVariable("id") int id, Model model) {
    //     Lop listlop = monhocService.getLopById(id);
    //     if(listlop !=null){
    //         model.addAttribute("lop",listlop);
    //         return "/lop/update";
    //     }
    //     return "redirect:/lop";
    // }

    // @PostMapping("/update/{id}")
    // public String editLop(@Valid @ModelAttribute("lop") Lop lop,BindingResult result,@PathVariable("id") int id) {
    //     if (result.hasErrors()) {
    //         return "/lop/update";
    //     }
    //     Lop listlop = monhocService.getLopById(id);
    //     listlop.setTenlop(lop.getTenlop());
    //     monhocService.updateLop(listlop);
    //     return "redirect:/lop";
    // }

    // @GetMapping("/delete/{id}")
    // public String deleteLop(@PathVariable("id") int id) {
    //     monhocService.deleteLop(id);
    //     return "redirect:/lop";
    // }
}
