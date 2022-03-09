package com.vaescode.springbootform.app.controllers;

import com.vaescode.springbootform.app.models.domain.Usuario;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FormController {

    @GetMapping("/form")
    public String form(Model model) {
        model.addAttribute("titulo", "Formulario usuario ");
        return "form";
    }

    @PostMapping("/form")
    public String procesarFormulario(Model model,
                                     @RequestParam String username,
                                     @RequestParam String password,
                                     @RequestParam String email) {

        Usuario usuario = new Usuario();
        usuario.setUsername(username);
        usuario.setPassword(password);
        usuario.setEmail(email);

        model.addAttribute("titulo", "Resultado del formulario ");
        model.addAttribute("usuario", usuario);


        return "resultado";
    }
}
