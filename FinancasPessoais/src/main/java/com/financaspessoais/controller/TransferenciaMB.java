package com.financaspessoais.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.financaspessoais.model.Conta;
import com.financaspessoais.model.Estabelecimento;
import com.financaspessoais.model.TipoDespesa;
import com.financaspessoais.model.TipoLancamento;
import com.financaspessoais.model.TipoReceita;
import com.financaspessoais.model.Transferencia;
import com.financaspessoais.service.ContaService;
import com.financaspessoais.service.EstabelecimentoService;
import com.financaspessoais.service.TipoDespesaService;
import com.financaspessoais.service.TipoReceitaService;
import com.financaspessoais.service.TransferenciaService;
import com.financaspessoais.service.impl.ContaServiceImpl;
import com.financaspessoais.service.impl.EstabelecimentoServiceImpl;
import com.financaspessoais.service.impl.TipoDespesaServiceImpl;
import com.financaspessoais.service.impl.TipoReceitaServiceImpl;
import com.financaspessoais.service.impl.TransferenciaServiceImpl;

@ManagedBean
@ViewScoped
public class TransferenciaMB implements Serializable {
	private static final long serialVersionUID = 1L;

	private TransferenciaService TransferenciaService;
	private ContaService contaService;
	private TipoReceitaService tipoReceitaService;
	private TipoDespesaService tipoDespesaService;
	private EstabelecimentoService tipoEstabelecimentoService;
	private Transferencia transferencia;
	private Transferencia transferenciaSelecionada;
	private List<Transferencia> transferenciasDoUsuario;

	private List<Conta> listaConta;
	private List<TipoReceita> listaTipoReceita;
	private List<TipoDespesa> listaTipoDespesa;
	private List<Estabelecimento> listaEstabelecimento;
	
	public void prepararCadastro() {
		if (transferencia == null)
			transferencia = new Transferencia();
		if (listaConta == null)
			listaConta = getContaService().listarPorUsuario();
		if (listaTipoDespesa == null)
			listaTipoDespesa = getTipoDespesaService().listarPorUsuario();
		if (listaTipoReceita == null)
			listaTipoReceita = getTipoReceitaService().listarPorUsuario();
		if (listaEstabelecimento == null)
			listaEstabelecimento = getEstabelecimentoService().listarPorUsuario();
	}

	public String cadastrarTransferencia() {
		String retorno = null;
		boolean inseridoComSucesso = this.getTransferenciaService().criarOuAtualizar(transferencia);
		if (inseridoComSucesso)
			retorno = "/pages/lista-transferencia?faces-redirect=true";
		return retorno;
	}

	public void excluir() {
		this.getTransferenciaService().remover(this.transferenciaSelecionada);
		this.transferenciasDoUsuario = null;
		this.getTransferenciasDoUsuario();
	}

	// Getters e Setters
	public List<Transferencia> getTransferenciasDoUsuario() {
		if (transferenciasDoUsuario == null) {
			transferenciasDoUsuario = getTransferenciaService().listarPorUsuario();
		}
		return transferenciasDoUsuario;
	}

	public Transferencia getTransferencia() {
		return transferencia;
	}

	public void setTransferencia(Transferencia Transferencia) {
		this.transferencia = Transferencia;
	}

	public Transferencia getTransferenciaSelecionada() {
		return transferenciaSelecionada;
	}

	public void setTransferenciaSelecionada(Transferencia TransferenciaSelecionada) {
		this.transferenciaSelecionada = TransferenciaSelecionada;
	}

	private TransferenciaService getTransferenciaService() {
		if (this.TransferenciaService == null)
			this.TransferenciaService = new TransferenciaServiceImpl();
		return TransferenciaService;
	}

	private ContaService getContaService() {
		if (this.contaService == null)
			this.contaService = new ContaServiceImpl();
		return contaService;
	}

	private TipoReceitaService getTipoReceitaService() {
		if (this.tipoReceitaService == null)
			this.tipoReceitaService = new TipoReceitaServiceImpl();
		return tipoReceitaService;
	}

	private TipoDespesaService getTipoDespesaService() {
		if (this.tipoDespesaService == null)
			this.tipoDespesaService = new TipoDespesaServiceImpl();
		return tipoDespesaService;
	}

	private EstabelecimentoService getEstabelecimentoService() {
		if (this.tipoEstabelecimentoService == null)
			this.tipoEstabelecimentoService = new EstabelecimentoServiceImpl();
		return tipoEstabelecimentoService;
	}

	public List<Conta> filtrarListaContas(String query) {

		if (this.listaConta == null)
			listaConta = this.getContaService().listarPorUsuario();
		List<Conta> contasFiltradas = new ArrayList<Conta>();

		for (int i = 0; i < listaConta.size(); i++) {
			Conta conta = listaConta.get(i);
			if (conta.getNome().toLowerCase().contains(query.toLowerCase())) {
				contasFiltradas.add(conta);
			}
		}

		return contasFiltradas;
	}

	public List<TipoReceita> filtrarListaTiposReceita(String query) {

		if (this.listaTipoReceita == null)
			listaTipoReceita = this.getTipoReceitaService().listarPorUsuario();
		List<TipoReceita> TiposReceitaFiltradas = new ArrayList<TipoReceita>();

		for (int i = 0; i < listaTipoReceita.size(); i++) {
			TipoReceita tipoReceita = listaTipoReceita.get(i);
			if (tipoReceita.getNome().toLowerCase().contains(query.toLowerCase())) {
				TiposReceitaFiltradas.add(tipoReceita);
			}
		}

		return TiposReceitaFiltradas;
	}

	public List<TipoDespesa> filtrarListaTiposDespesa(String query) {

		if (this.listaTipoDespesa == null)
			listaTipoDespesa = this.getTipoDespesaService().listarPorUsuario();
		List<TipoDespesa> tiposDespesaFiltradas = new ArrayList<TipoDespesa>();

		for (int i = 0; i < listaTipoDespesa.size(); i++) {
			TipoDespesa tipoDespesa = listaTipoDespesa.get(i);
			if (tipoDespesa.getNome().toLowerCase().contains(query.toLowerCase())) {
				tiposDespesaFiltradas.add(tipoDespesa);
			}
		}

		return tiposDespesaFiltradas;
	}

	public List<Estabelecimento> filtrarListaEstabelecimentos(String query) {

		if (this.listaEstabelecimento == null)
			listaEstabelecimento = this.getEstabelecimentoService().listarPorUsuario();
		List<Estabelecimento> estabelecimentosFiltrados = new ArrayList<Estabelecimento>();

		for (int i = 0; i < listaEstabelecimento.size(); i++) {
			Estabelecimento estabelecimento = listaEstabelecimento.get(i);
			if (estabelecimento.getNome().toLowerCase().contains(query.toLowerCase())) {
				estabelecimentosFiltrados.add(estabelecimento);
			}
		}

		return estabelecimentosFiltrados;
	}

	public TipoLancamento[] getTiposLancamentos() {
		return TipoLancamento.values();
	}
}
