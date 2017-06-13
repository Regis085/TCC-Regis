package com.financaspessoais.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.financaspessoais.model.Banco;
import com.financaspessoais.model.Conta;
import com.financaspessoais.model.ContaBancaria;
import com.financaspessoais.service.BancoService;
import com.financaspessoais.service.ContaBancariaService;
import com.financaspessoais.service.impl.BancoServiceImpl;
import com.financaspessoais.service.impl.ContaBancariaServiceImpl;

@ManagedBean
@ViewScoped
public class ContaBancariaMB implements Serializable {
	private static final long serialVersionUID = 1L;
	private ContaBancariaService contaBancariaService;
	private ContaBancaria contaBancariaSelecionada;
	private ContaBancaria contaBancaria;
	private BancoService bancoService;
	private List<Banco> listaBanco;

	public void prepararCadastro() {
		if (this.contaBancaria == null)
			this.contaBancaria = new ContaBancaria();
		if (this.listaBanco == null)
			listaBanco = this.getBancoService().listarPorUsuario(); 
	}

	public String cadastrarConta() {
		String retorno;
		boolean inseridoComSucesso = this.getContaBancariaService().criarOuAtualizar(contaBancaria);
		if (inseridoComSucesso)
			retorno = "/pages/lista-conta?faces-redirect=true";
		else
			retorno = null;
		return retorno;
	}

	public void excluir() {
		this.getContaBancariaService().remover(this.contaBancariaSelecionada);
		this.getContasDoUsuario();
	}

	// Getters e Setters

	public Conta getContaBancariaSelecionada() {
		return contaBancariaSelecionada;
	}

	public void setContaBancariaSelecionada(ContaBancaria contaBancariaSelecionada) {
		this.contaBancariaSelecionada = contaBancariaSelecionada;
	}

	public ContaBancaria getContaBancaria() {
		return contaBancaria;
	}

	public void setContaBancaria(ContaBancaria contaBancaria) {
		this.contaBancaria = contaBancaria;
	}

	public List<ContaBancaria> getContasBancarias() {
		return this.getContaBancariaService().listarContasBancarias();
	}

	public List<ContaBancaria> getContasDoUsuario() {
		return this.getContaBancariaService().listarPorUsuario();
	}

	public List<Banco> getListaBanco() {
		return listaBanco;
	}

	public void setListaBanco(List<Banco> listaBanco) {
		this.listaBanco = listaBanco;
	}
	
	private ContaBancariaService getContaBancariaService() {
		if (this.contaBancariaService == null)
			this.contaBancariaService = new ContaBancariaServiceImpl();
		return contaBancariaService;
	}
	
	private BancoService getBancoService() {
		if (this.bancoService == null)
			this.bancoService = new BancoServiceImpl();
		return bancoService;
	}
}