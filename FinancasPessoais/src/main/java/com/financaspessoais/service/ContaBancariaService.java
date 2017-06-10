package com.financaspessoais.service;

import java.util.List;

import com.financaspessoais.model.ContaBancaria;

public interface ContaBancariaService {

	boolean criarOuAtualizar(ContaBancaria contaBancaria);

	List<ContaBancaria> listarContasBancarias();

	List<ContaBancaria> listarContasBancariasPorUsuario();

	void excluir(ContaBancaria contaBancaria);

	public ContaBancaria buscarContaBancaria(Integer id);

}
