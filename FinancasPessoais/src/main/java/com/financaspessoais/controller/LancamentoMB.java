package com.financaspessoais.controller;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.SelectEvent;

import com.financaspessoais.model.Conta;
import com.financaspessoais.model.Estabelecimento;
import com.financaspessoais.model.Lancamento;
import com.financaspessoais.model.StatusLancamento;
import com.financaspessoais.model.TipoDespesa;
import com.financaspessoais.model.TipoLancamento;
import com.financaspessoais.model.TipoReceita;
import com.financaspessoais.service.ContaService;
import com.financaspessoais.service.EstabelecimentoService;
import com.financaspessoais.service.LancamentoService;
import com.financaspessoais.service.TipoDespesaService;
import com.financaspessoais.service.TipoReceitaService;
import com.financaspessoais.service.impl.ContaServiceImpl;
import com.financaspessoais.service.impl.EstabelecimentoServiceImpl;
import com.financaspessoais.service.impl.LancamentoServiceImpl;
import com.financaspessoais.service.impl.TipoDespesaServiceImpl;
import com.financaspessoais.service.impl.TipoReceitaServiceImpl;

@ManagedBean
@ViewScoped
public class LancamentoMB implements Serializable {
	private static final long serialVersionUID = 1L;

	private LancamentoService lancamentoService;
	private ContaService contaService;
	private TipoReceitaService tipoReceitaService;
	private TipoDespesaService tipoDespesaService;
	private EstabelecimentoService estabelecimentoService;
	private Lancamento lancamento;
	private Lancamento lancamentoSelecionado;
	private List<Lancamento> lancamentosDoUsuario;

	private List<Conta> listaConta;
	private List<TipoReceita> listaTipoReceita;
	private List<TipoDespesa> listaTipoDespesa;
	private List<Estabelecimento> listaEstabelecimento;

	public BigDecimal getSaldoLancamentos() {
		BigDecimal resultado = BigDecimal.ZERO;
		for (Lancamento l: lancamentosDoUsuario) {
			if (l.getTipoLancamento().equals(TipoLancamento.DESPESA)) {
				resultado = resultado.subtract(l.getValor());
			}
			else {
				resultado = resultado.add(l.getValor());
			}
		}
		return resultado;
	}
	
	public TipoLancamento[] getTiposLancamentos() {
		return TipoLancamento.values();
	}
	
	public void atualizarStatusLancamento() {
		if (lancamento.getDataRealizacao() != null)
			lancamento.setStatusLancamento(StatusLancamento.REALIZADO);
		else
			lancamento.setStatusLancamento(StatusLancamento.PENDENTE);
	}
	
	public void atualizarStatusLancamento2(SelectEvent event) {
		if (this.lancamento != null) {
			Date dataRealizacao = (Date) event.getObject();
			if (dataRealizacao != null)
				lancamento.setStatusLancamento(StatusLancamento.REALIZADO);
			else
				lancamento.setStatusLancamento(StatusLancamento.PENDENTE);
		}
	}
	
	public List<Conta> filtrarListaContas(String query) {
        
		if (this.listaConta == null)
			listaConta = this.getContaService().listarPorUsuario();
        List<Conta> contasFiltradas = new ArrayList<Conta>();
         
        for (int i = 0; i < listaConta.size(); i++) {
        	Conta conta = listaConta.get(i);
            if(conta.getNome().toLowerCase().contains(query.toLowerCase())) {
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
			if(tipoReceita.getNome().toLowerCase().contains(query.toLowerCase())) {
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
			if(tipoDespesa.getNome().toLowerCase().contains(query.toLowerCase())) {
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
			if(estabelecimento.getNome().toLowerCase().contains(query.toLowerCase())) {
				estabelecimentosFiltrados.add(estabelecimento);
			}
		}
		
		return estabelecimentosFiltrados;
	}
	
	public void prepararCadastro() {
		if (lancamento == null)
			lancamento = new Lancamento();
		if (listaConta == null)
			listaConta = getContaService().listarPorUsuario(); 
		if (listaTipoDespesa == null)
			listaTipoDespesa = getTipoDespesaService().listarPorUsuario(); 
		if (listaTipoReceita == null)
			listaTipoReceita = getTipoReceitaService().listarPorUsuario(); 
		if (listaEstabelecimento == null)
			listaEstabelecimento = getEstabelecimentoService().listarPorUsuario(); 
	}
	
	public String cadastrarLancamento() {
		String retorno = null;
		boolean inseridoComSucesso = this.getLancamentoService().criarOuAtualizar(lancamento);
		if (inseridoComSucesso)
			retorno = "/pages/lista-lancamento?faces-redirect=true";
		return retorno;
	}
	
	public void excluir() {
		this.getLancamentoService().remover(this.lancamentoSelecionado);
		this.lancamentosDoUsuario = null;
		this.getLancamentosDoUsuario();
	}

	// Getters e Setters
	public List<Lancamento> getLancamentosDoUsuario() {
		if (lancamentosDoUsuario == null) {
			lancamentosDoUsuario = getLancamentoService().listarPorUsuario();
		}
		return lancamentosDoUsuario;
	}
	
	public Lancamento getLancamento() {
		return lancamento;
	}

	public void setLancamento(Lancamento lancamento) {
		this.lancamento = lancamento;
	}

	public Lancamento getLancamentoSelecionado() {
		return lancamentoSelecionado;
	}

	public void setLancamentoSelecionado(Lancamento lancamentoSelecionado) {
		this.lancamentoSelecionado = lancamentoSelecionado;
	}

	private LancamentoService getLancamentoService() {
		if (this.lancamentoService == null)
			this.lancamentoService = new LancamentoServiceImpl();
		return lancamentoService;
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
		if (this.estabelecimentoService == null)
			this.estabelecimentoService = new EstabelecimentoServiceImpl();
		return estabelecimentoService;
	}
}
