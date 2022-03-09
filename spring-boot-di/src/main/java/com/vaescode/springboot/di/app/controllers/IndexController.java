package com.vaescode.springboot.di.app.controllers;

import com.vaescode.springboot.di.app.models.service.IServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {


    private IServicio servicio;

    @GetMapping({"", "/", "/index"})
    public String index(Model model) {
        model.addAttribute("titulo", "Inyección de dependencias con autowired");
        model.addAttribute("objeto", servicio.operacion());
        return "index";
    }

    /*
    * @Autowired
    public void setServicio(IServicio servicio) {
        this.servicio = servicio;
    }
    * */

    //No es necesario anotare con @Autowired el constructor
    public IndexController(IServicio servicio) {
        this.servicio = servicio;
    }
}
