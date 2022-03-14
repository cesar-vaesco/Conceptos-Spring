package com.vaescode.springbootform.app.editors;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.vaescode.springbootform.app.services.IRoleService;

@Component
public class RolesEditors extends PropertyEditorSupport {

	@Autowired
	private IRoleService roleService;

	@Override
	public void setAsText(String idString) throws IllegalArgumentException {

		try {
			Integer id = Integer.parseInt(idString);
			setValue(roleService.obtenerPorId(id));
		} catch (NumberFormatException e) {
			setValue(null);
		}

	}

}
