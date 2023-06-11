package com.works.controller;

import com.works.services.ImageService;
import com.works.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class DetailController {

    final ImageService imageService;
    final ProductService productService;

    @GetMapping("/dashboard/detail/{pid}")
    public String detail(Model model, @PathVariable Long pid){
        model.addAttribute("product",productService.allProductDetail());
        model.addAttribute("product",productService.getSingleProduct(pid));
        model.addAttribute("images",imageService.list(pid));
        return "detail";
    }
}
