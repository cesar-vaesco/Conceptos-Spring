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
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.vaescode.springbootform.app.editors.NombreMayusculaEditors;
import com.vaescode.springbootform.app.editors.PaisPropertyEditor;
import com.vaescode.springbootform.app.editors.RolesEditors;
import com.vaescode.springbootform.app.models.domain.Pais;
import com.vaescode.springbootform.app.models.domain.Role;
import com.vaescode.springbootform.app.models.domain.Usuario;
import com.vaescode.springbootform.app.services.IPaisesService;
import com.vaescode.springbootform.app.services.IRoleService;
import com.vaescode.springbootform.app.validation.UsuarioValidador;

@Controller
@SessionAttributes("usuario") // -> nombre con que se pasa el objeto al formulario
public class FormController {

	@Autowired
	private UsuarioValidador validador;

	@Autowired
	private IPaisesService paisesService;

	@Autowired
	private IRoleService roleService;

	@Autowired
	private PaisPropertyEditor paisEditor;

	@Autowired
	private RolesEditors roleEditor;

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
		binder.registerCustomEditor(Role.class, "roles", roleEditor);
	}

	@ModelAttribute("genero")
	public List<String> genero() {
		return Arrays.asList("Hombre", "Mujer", "Otro");
	}

	@ModelAttribute("listaRoles")
	public List<Role> listaRoles() {
		return this.roleService.listar();
	}

	@ModelAttribute("listaPaises")
	public List<Pais> listaPaises() {
		return paisesService.listar();
	}

	@ModelAttribute("listaRolesMap")
	public Map<String, String> listaRolesMap() {
		Map<String, String> roles = new HashMap<String, String>();
		// roles.put(key, value)
		roles.put("ROLE_ADMIN", "Admnistrador");
		roles.put("ROLE_USER", "Usuario");
		roles.put("ROLE_MODERATOR", "Moderador");

		return roles;
	}

	@ModelAttribute("listaRolesString") // -> Así se pasa al formulario
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
		usuario.setHabilitar(true);
		usuario.setValorSecreto("Algún valor secreto");

		usuario.setPais(new Pais(2, "MX", "México"));
		usuario.setRoles(Arrays.asList(new Role(2, "Usuario", "ROLE_USER")));

		model.addAttribute("titulo", "Formulario usuario ");
		model.addAttribute("usuario", usuario);
		return "form";
	}

	@PostMapping("/form")
	public String procesarFormulario(@Valid Usuario usuario, BindingResult result, Model model) {

		if (result.hasErrors()) {
			model.addAttribute("titulo", "Resultado del formulario ");
			return "form";
		}

		return "redirect:/ver";
	}

	@GetMapping("/ver")
	public String ver(@SessionAttribute(name = "usuario", required = false) Usuario usuario, Model model,
			SessionStatus status) {

		
		if(usuario == null) { //Cuando se vuelve a enviar el formulario y ya no exista usuario en la sesion
			return "redirect:/form";
		}
		model.addAttribute("titulo", "Resultado del formulario ");
		model.addAttribute("usuario", usuario);
		model.addAttribute("fechas", "Diferente formato de fechas");

		status.setComplete(); // -> cerramos la session del llenado del formulario usando una estancia de
								// SessionStatus
		return "resultado";
	}

}
