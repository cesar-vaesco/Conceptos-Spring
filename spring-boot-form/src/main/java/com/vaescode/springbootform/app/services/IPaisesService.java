package com.vaescode.springbootform.app.services;

import java.util.List;

import com.vaescode.springbootform.app.models.domain.Pais;

public interface IPaisesService {

	public List<Pais> listar();

	public Pais obtenerPorId(Integer id);
}
