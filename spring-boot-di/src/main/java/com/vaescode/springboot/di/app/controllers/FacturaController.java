package com.vaescode.springboot.di.app.controllers;

import com.vaescode.springboot.di.app.models.domain.Factura;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/factura")
public class FacturaController {

    @Autowired
    private Factura factura;


    @GetMapping("/ver")
    public String ver(Model model){

        model.addAttribute("titulo", "Ejemplo factura con inyección de dependencias");
        model.addAttribute("factura", factura);
        return "factura/ver";
    }
}
