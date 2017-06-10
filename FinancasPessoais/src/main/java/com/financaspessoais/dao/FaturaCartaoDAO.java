package com.financaspessoais.dao;

import java.util.List;

import com.financaspessoais.model.FaturaCartao;

public class FaturaCartaoDAO extends AbstractGenericDAO<FaturaCartao, Long>{

	public FaturaCartaoDAO() {
		super(FaturaCartao.class);
	}

	public List<FaturaCartao> listarPorProprietario(Short id) {
		// TODO Auto-generated method stub
		return null;
	}
}
