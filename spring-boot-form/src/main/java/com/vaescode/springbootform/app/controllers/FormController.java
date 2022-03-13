package com.vaescode.springbootform.app.controllers;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.vaescode.springbootform.app.models.domain.Usuario;
import com.vaescode.springbootform.app.validation.UsuarioValidador;

@Controller
@SessionAttributes("usuario") // -> nombre con que se pasa el objeto al formulario
public class FormController {

	@Autowired
	private UsuarioValidador validador;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.addValidators(validador);
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false); //Analisis estricto del dato a ingresar
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}

	@GetMapping("/form")
	public String form(Model model) {

		Usuario usuario = new Usuario();

		usuario.setIdentificador("123.456.678-k");
		usuario.setNombre("Jhon");
		usuario.setApellido("Doe");

		model.addAttribute("titulo", "Formulario usuario ");
		model.addAttribute("usuario", usuario);
		return "form";
	}

	@PostMapping("/form")
	public String procesarFormulario(@Valid Usuario usuario, BindingResult result, Model model, SessionStatus status) {

		// validador.validate(usuario,result);

		model.addAttribute("titulo", "Resultado del formulario ");

		if (result.hasErrors()) {

			return "form";
		}

		model.addAttribute("usuario", usuario);
		status.setComplete(); // -> cerramos la session del llenado del formulario usando una estancia de
								// SessionStatus

		return "resultado";
	}
}
