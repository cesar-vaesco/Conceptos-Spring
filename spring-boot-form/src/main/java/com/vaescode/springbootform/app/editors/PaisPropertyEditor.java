package com.vaescode.springbootform.app.editors;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.vaescode.springbootform.app.services.IPaisesService;

@Component
public class PaisPropertyEditor extends PropertyEditorSupport {

	@Autowired
	private IPaisesService paisesService;

	@Override
	public void setAsText(String idString) throws IllegalArgumentException {

		try {
			Integer id = Integer.parseInt(idString);
			this.setValue(paisesService.obtenerPorId(id));
		} catch (NumberFormatException e) {
			setValue(null);
		}

		/*
		 * Fuynciona igual if (idString != null && idString.length() > 0) {
		 * 
		 * try { Integer id = Integer.parseInt(idString);
		 * this.setValue(paisesService.obtenerPorId(id)); } catch (NumberFormatException
		 * e) {
		 * 
		 * } } else { setValue(null); }
		 */
	}

}
