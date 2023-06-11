package com.works.controller;

import com.works.entities.Customer;
import com.works.services.CustomerService;
import com.works.services.TinkEncDec;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequiredArgsConstructor
public class LoginController {
    final CustomerService customerService;
    final HttpServletRequest req;
    final HttpServletResponse response;
    final TinkEncDec tinkEncDec;
    String error = "";
    String success = "";

    @GetMapping("/dashboard/login")
    private String Login(Model model){
        req.getSession().setAttribute("cid", 1l);
        model.addAttribute("error",error);
        model.addAttribute("success",success);
        error = "";
        success = "";
        Customer c = customerService.login("admin@mail.com", "12345");
        System.out.println(c);
        return "login";
    }

    @PostMapping("/dashboard/loginUser")
    private String customerLogin(Customer customer){
        Customer x = customerService.login(customer.getEmail(),customer.getPassword());

        if (x == null){ // kayıt yoksa

            Customer control = customerService.loginUser(customer.getEmail());
            if (control == null){
                error = "E-mail Hatalı ! ";
                return "redirect:/dashboard/login";
            }
            else {
                error = "Şifre Hatalı ! ";
                return "redirect:/dashboard/login";
            }
        }
            req.getSession().setAttribute("customer",x);
            if (customer.getRemember() != null && customer.getRemember().equals("on")){
                String cipherText = tinkEncDec.encrypt(""+x.getCid());
            }
        return "redirect:/dashboard";
    }
}
