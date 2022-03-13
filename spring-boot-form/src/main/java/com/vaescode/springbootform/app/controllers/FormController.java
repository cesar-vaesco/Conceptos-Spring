package com.vaescode.springbootform.app.controllers;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.vaescode.springbootform.app.editors.NombreMayusculaEditors;
import com.vaescode.springbootform.app.editors.PaisPropertyEditor;
import com.vaescode.springbootform.app.models.domain.Pais;
import com.vaescode.springbootform.app.models.domain.Usuario;
import com.vaescode.springbootform.app.services.IPaisesService;
import com.vaescode.springbootform.app.validation.UsuarioValidador;

@Controller
@SessionAttributes("usuario") // -> nombre con que se pasa el objeto al formulario
public class FormController {

	@Autowired
	private UsuarioValidador validador;

	@Autowired
	private IPaisesService paisesService;

	@Autowired
	private PaisPropertyEditor paisEditor;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.addValidators(validador);

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

		dateFormat.setLenient(false); // Analisis estricto del dato a ingresar
		binder.registerCustomEditor(Date.class, "fechaNacimiento", new CustomDateEditor(dateFormat, true));

		/*
		 * --> para todos los campos del tipo String
		 * binder.registerCustomEditor(String.class, new NombreMayusculaEditors());
		 */
		binder.registerCustomEditor(String.class, "nombre", new NombreMayusculaEditors());
		binder.registerCustomEditor(String.class, "apellido", new NombreMayusculaEditors());

		binder.registerCustomEditor(Pais.class, "pais", paisEditor);
	}

	@ModelAttribute("listaPaises")
	public List<Pais> listaPaises() {
		return paisesService.listar();
	}

	@ModelAttribute("listaRolesString") //-> Así se pasa al formulario
	public List<String> listaRolesString() {

		List<String> roles = new ArrayList<>();

		roles.add("ROLE_ADMIN");
		roles.add("ROLE_USER");
		roles.add("ROLE_MODERATOR");

		return roles;
	}

	@ModelAttribute("paisesMap")
	public Map<String, String> paisesMap() {
		Map<String, String> paises = new HashMap<String, String>();
		// paises.put(key, value)
		paises.put("MX", "México");
		paises.put("ES", "España");
		paises.put("CL", "Chile");
		paises.put("PU", "Perú");
		paises.put("AR", "Argentina");
		paises.put("EC", "Ecuador");
		paises.put("CO", "Colombia");
		paises.put("VE", "Venezuela");

		return paises;
	}

	@ModelAttribute("paises")
	public List<String> paises() {
		return Arrays.asList("España", "México", "Chile", "Perú", "Argentina", "Ecuador", "Colombia", "Venezuela");
	}

	@GetMapping("/form")
	public String form(Model model) {

		Usuario usuario = new Usuario();

		usuario.setIdentificador("123.456.678-K");
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
		model.addAttribute("fechas", "Diferente formato de fechas");

		if (result.hasErrors()) {

			return "form";
		}

		model.addAttribute("usuario", usuario);
		status.setComplete(); // -> cerramos la session del llenado del formulario usando una estancia de
								// SessionStatus

		return "resultado";
	}

}
