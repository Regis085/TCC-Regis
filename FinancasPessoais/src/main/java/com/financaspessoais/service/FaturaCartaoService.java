package com.financaspessoais.service;

import java.util.List;

import com.financaspessoais.model.FaturaCartao;

public interface FaturaCartaoService {
	boolean criarOuAtualizar(FaturaCartao faturaCartao);

	FaturaCartao buscar(Short codigoCartaoDeCredito, Short ano, Short mes);

	List<FaturaCartao> listarPorUsuario();
	
	List<FaturaCartao> listarPorUsuarioECartaoDeCredito(Short codigoCartaoDeCredito);

	void remover(FaturaCartao faturaCartao);
}
