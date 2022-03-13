package com.vaescode.springbootform.app.services;

import java.util.Arrays;
import java.util.List;

import com.vaescode.springbootform.app.models.domain.Pais;


@Service
public class PaisServiceImpl implements IPaisesService {

	private List<Pais> lista;

	public PaisServiceImpl() {

		this.lista = Arrays.asList(new Pais(1, "ES", "España"), new Pais(2, "MX", "México"), new Pais(3, "CL", "Chile"),
				new Pais(4, "PU", "Perú"), new Pais(5, "AR", "Argentina"), new Pais(6, "EC", "Ecuador"),
				new Pais(7, "CO", "Colombia"), new Pais(8, "VZ", "Venezuela"));

	}

	@Override
	public List<Pais> listar() {
		return lista;
	}

	@Override
	public Pais obtenerPorId(Integer id) {

		Pais resultado = null;

		for (Pais pais : this.lista) {
			if (id == pais.getId()) {
				resultado = pais;
				break;
			}
		}

		return resultado;
	}

}
