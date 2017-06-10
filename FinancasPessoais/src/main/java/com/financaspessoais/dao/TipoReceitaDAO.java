package com.financaspessoais.dao;

import java.util.List;

import com.financaspessoais.model.TipoReceita;

public class TipoReceitaDAO extends AbstractGenericDAO<TipoReceita, Short>{

	public TipoReceitaDAO() {
		super(TipoReceita.class);
	}

	public List<TipoReceita> listarPorProprietario(Short id) {
		// TODO Auto-generated method stub
		return null;
	}
}
