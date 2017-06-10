package com.financaspessoais.dao;

import java.util.List;

import com.financaspessoais.model.LancamentoCartao;

public class LancamentoCartaoDAO extends AbstractGenericDAO<LancamentoCartao, Long> {
	
	public LancamentoCartaoDAO() {
		super(LancamentoCartao.class);
	}

	public List<LancamentoCartao> listarPorProprietario(Short id) {
		// TODO Auto-generated method stub
		return null;
	}
}
