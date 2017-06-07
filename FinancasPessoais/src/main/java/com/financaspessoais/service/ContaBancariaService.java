package com.financaspessoais.service;

import java.util.List;

import com.financaspessoais.model.ContaBancaria;

public interface ContaBancariaService {

	boolean criarOuAtualizarContaBancaria(ContaBancaria contaBancaria);

	List<ContaBancaria> listarContasBancarias();

	List<ContaBancaria> listarContasBancariasPorUsuario();

	void excluirConta(ContaBancaria contaBancariaSelecionada);

}
