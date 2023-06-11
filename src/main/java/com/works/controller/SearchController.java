package com.works.controller;

import com.works.entities.Product;
import com.works.services.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class SearchController {

    final SearchService service;

    @GetMapping("/search")
    public String search(@RequestParam(defaultValue = "") String q, Model model){
        List<Product> ls = service.search(q);
        model.addAttribute("product",ls);
        model.addAttribute("q",q);
        return "search";
    }
}
