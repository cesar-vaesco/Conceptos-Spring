package com.vaescode.springbootform.app.services;

import java.util.List;

import com.vaescode.springbootform.app.models.domain.Role;

public interface IRoleService {

	
	public List<Role> listar();
	
	public Role obtenerPorId(Integer id);
}
