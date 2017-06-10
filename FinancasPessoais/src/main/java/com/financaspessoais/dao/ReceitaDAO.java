package com.financaspessoais.dao;

import java.util.List;

import com.financaspessoais.model.Receita;

public class ReceitaDAO extends AbstractGenericDAO<Receita, Long>{

	public ReceitaDAO() {
		super(Receita.class);
	}

	public List<Receita> listarPorProprietario(Short id) {
		// TODO Auto-generated method stub
		return null;
	}
}
