package com.example.bt3.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import com.example.bt3.model.Lop;
import com.example.bt3.services.LopService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;






@Controller
@RequestMapping("/lop")
public class LopController {
    @Autowired
    private LopService lopService;

    @GetMapping
    public String showAllLop(Model model) {
        List<Lop> listlop = lopService.getAllLops();
        model.addAttribute("listlop",listlop);
        return "lop/list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("lop", new Lop());
        return "lop/add";
    }

    @PostMapping("/add")
    public String addLop(@Valid @ModelAttribute("lop") Lop lop,BindingResult result) {
        if (result.hasErrors()) {
            return "/lop/add";
        }
        lopService.addLop(lop);  
        return "redirect:/lop";
    }

    @GetMapping("/update/{id}")
    public String editLop(@PathVariable("id") int id, Model model) {
        Lop listlop = lopService.getLopById(id);
        if(listlop !=null){
            model.addAttribute("lop",listlop);
            return "/lop/update";
        }
        return "redirect:/lop";
    }

    @PostMapping("/update/{id}")
    public String editLop(@Valid @ModelAttribute("lop") Lop lop,BindingResult result,@PathVariable("id") int id) {
        if (result.hasErrors()) {
            return "/lop/update";
        }
        Lop listlop = lopService.getLopById(id);
        listlop.setTenlop(lop.getTenlop());
        lopService.updateLop(listlop);
        return "redirect:/lop";
    }

    @GetMapping("/delete/{id}")
    public String deleteLop(@PathVariable("id") int id) {
        lopService.deleteLop(id);
        return "redirect:/lop";
    }
    
}
