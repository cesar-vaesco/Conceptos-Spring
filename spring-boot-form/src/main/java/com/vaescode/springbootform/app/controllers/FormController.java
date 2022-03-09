package com.vaescode.springbootform.app.controllers;

import com.vaescode.springbootform.app.models.domain.Usuario;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Controller
public class FormController {

    @GetMapping("/form")
    public String form(Model model) {
        model.addAttribute("titulo", "Formulario usuario ");
        return "form";
    }

    @PostMapping("/form")
    public String procesarFormulario(@Valid Usuario usuario, BindingResult result, Model model) {


        if(result.hasErrors()){
            Map<String, String> errores = new HashMap<>();
            result.getFieldErrors().forEach(err -> {
                errores.put(err.getField(), "El campo ".concat(err.getField()).concat(" ").concat(err.getDefaultMessage()));
            });
            model.addAttribute("error", errores);
            return "form";
        }

        model.addAttribute("titulo", "Resultado del formulario ");
        model.addAttribute("usuario", usuario);

        return "resultado";
    }
}
