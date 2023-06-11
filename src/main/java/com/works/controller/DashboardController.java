package com.works.controller;

import com.works.entities.Product;
import com.works.repositories.ProductRepository;
import com.works.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
public class DashboardController {

    final ProductService productService;
    private Long updateID = 0l;


    @GetMapping ("/")
    public String home(Model model, @RequestParam(defaultValue = "0") int page){
        model.addAttribute("product",productService.allProductHome(page));
        updateID = 0l;
        return "home";
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model,@RequestParam(defaultValue = "0") int page){
        model.addAttribute("product",productService.allProduct(page));
        updateID = 0l;
        return "dashboard";
    }

    @PostMapping("/productSave")
    public String productSave(Product product){
        productService.save(product);
        return "redirect:/dashboard";
    }

    @GetMapping("/productDelete/{pid}")
    public String productDelete(@PathVariable Long pid){
        productService.productDelete(pid);
        return "redirect:/dashboard";
    }




}
