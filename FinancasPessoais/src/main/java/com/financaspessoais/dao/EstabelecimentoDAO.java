package com.financaspessoais.dao;

import java.util.List;

import com.financaspessoais.model.Estabelecimento;

public class EstabelecimentoDAO extends AbstractGenericDAO<Estabelecimento, Long>{

	public EstabelecimentoDAO() {
		super(Estabelecimento.class);
	}

	public List<Estabelecimento> listarPorProprietario(Short id) {
		// TODO Auto-generated method stub
		return null;
	}
}
