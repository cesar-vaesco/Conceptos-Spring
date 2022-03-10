package com.vaescode.springbootform.app.controllers;



import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.vaescode.springbootform.app.models.domain.Usuario;

@Controller
public class FormController {

    @GetMapping("/form")
    public String form(Model model) {
    	
    	Usuario usuario  = new Usuario();
        model.addAttribute("titulo", "Formulario usuario ");
        model.addAttribute("usuario", usuario);
        return "form";
    }
    

    @PostMapping("/form")
    public String procesarFormulario(@Valid  Usuario usuario, BindingResult result, Model model) {


        if(result.hasErrors()){
           
            return "form";
        }

        model.addAttribute("titulo", "Resultado del formulario ");
        model.addAttribute("usuario", usuario);

        return "resultado";
    }
}
