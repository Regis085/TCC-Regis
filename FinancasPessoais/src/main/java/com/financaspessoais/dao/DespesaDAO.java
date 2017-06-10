package com.financaspessoais.dao;

import java.util.List;

import com.financaspessoais.model.Despesa;

public class DespesaDAO extends AbstractGenericDAO<Despesa, Long>{

	public DespesaDAO() {
		super(Despesa.class);
	}

	public List<Despesa> listarPorProprietario(Short id) {
		// TODO Auto-generated method stub
		return null;
	}
}
