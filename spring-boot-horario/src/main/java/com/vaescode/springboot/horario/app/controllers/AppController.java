package com.vaescode.springboot.horario.app.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AppController {
	
	
	private static final Logger log = LoggerFactory.getLogger(AppController.class);

	
	@Value("${config.horario.apertura}")
	private Integer apertura;
	
	@Value("${config.horario.cierre}")
	private Integer cierre;
	
	@GetMapping({"/", "/index"})
	public String index(Model model) {
		model.addAttribute("titulo", "Bienvenido al horario de atención a clientes");
		return "index";
	}
	
	@GetMapping("/cerrado")
	public String cerrado(Model model) {
		
		log.info("Apertura: " + apertura);
		
		StringBuilder mensaje = new StringBuilder("Cerrado, por favor visítenos desde las ");
		mensaje.append(apertura);
		mensaje.append(" y las ");
		mensaje.append(cierre);
		mensaje.append(" hrs. Gracias.");
		
		model.addAttribute("titulo", "Fuera del horario de atención");
		model.addAttribute("mensaje", mensaje);
		return "cerrado";
	}
	
}
