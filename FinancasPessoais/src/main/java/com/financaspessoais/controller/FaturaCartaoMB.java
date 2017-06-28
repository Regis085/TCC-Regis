package com.financaspessoais.controller;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIOutput;
import javax.faces.event.AjaxBehaviorEvent;

import org.primefaces.event.SelectEvent;

import com.financaspessoais.model.CartaoDeCredito;
import com.financaspessoais.model.FaturaCartao;
import com.financaspessoais.model.MesDoAno;
import com.financaspessoais.model.StatusFaturaCartao;
import com.financaspessoais.service.CartaoDeCreditoService;
import com.financaspessoais.service.FaturaCartaoService;
import com.financaspessoais.service.impl.CartaoDeCreditoServiceImpl;
import com.financaspessoais.service.impl.FaturaCartaoServiceImpl;
import com.financaspessoais.util.Util;

@ManagedBean
@ViewScoped
public class FaturaCartaoMB implements Serializable {
	private static final long serialVersionUID = 1L;
	private FaturaCartao faturaCartao;
	private FaturaCartao faturaCartaoSelecionada;
	private List<FaturaCartao> listaFaturaCartaoDoUsuario;
	
	private List<CartaoDeCredito> listaCartaoDeCredito;
	private List<String> listaAno;
	private List<String> listaAnoFiltrada;
	
	private FaturaCartaoService faturaCartaoService;
	private CartaoDeCreditoService cartaoDeCreditoService;
	
	public List<CartaoDeCredito> filtrarListaCartoes(String query) {
        
		if (this.listaCartaoDeCredito == null)
			listaCartaoDeCredito = this.getCartaoDeCreditoService().listarPorUsuario();
        List<CartaoDeCredito> cartoesFiltrados = new ArrayList<CartaoDeCredito>();
         
        for (int i = 0; i < listaCartaoDeCredito.size(); i++) {
        	CartaoDeCredito cartao = listaCartaoDeCredito.get(i);
            if(cartao.getNome().toLowerCase().contains(query.toLowerCase())) {
                cartoesFiltrados.add(cartao);
            }
        }
         
        return cartoesFiltrados;
    }
	
	@SuppressWarnings("deprecation")
	public void atualizarDataVencimento(AjaxBehaviorEvent e) {
		UIOutput x = ((UIOutput) e.getSource());
		CartaoDeCredito c = (CartaoDeCredito) x.getValue();
		if (faturaCartao.getDataVencimento() == null && c.getDiaVencimento() != null && c.getDiaVencimento().intValue() > 0 && c.getDiaVencimento().intValue() <= 31) {
			
			Date dataAtual = Util.getDataAtualZeroHoras();

			int diaSugerido = c.getDiaVencimento().intValue();
			int diaUtilizar = diaSugerido;
			int mesAtual = dataAtual.getMonth() + 1;
			
			if (mesAtual == 2 && diaSugerido > 28)
				diaUtilizar = 28;
			else if ( (mesAtual == 4 || mesAtual == 6 || mesAtual == 9 || mesAtual == 11) && diaSugerido == 31)
				diaUtilizar = 30;
			
			Calendar cal = Calendar.getInstance();
			cal.setTime(dataAtual);
			cal.set(Calendar.DAY_OF_MONTH, diaUtilizar);
			Date novaDataVencimento = cal.getTime();
			faturaCartao.setDataVencimento(novaDataVencimento);
		}
		
	}
	
	public List<String> carregarListaAno(String query) {
		if (listaAno == null) {
			listaAno = new ArrayList<String>();
			for (short ano = 2000; ano <= 2100; ano++) {
				listaAno.add(Short.valueOf(ano).toString());
			}
		}
		if (listaAnoFiltrada == null)
			listaAnoFiltrada = new ArrayList<String>();		
		
		listaAnoFiltrada.clear();
		for (String item : listaAno)
			if (item.contains(query))
				listaAnoFiltrada.add(item);
			
		return listaAnoFiltrada;
	}
	
	public String getChaveComposta() {
		return faturaCartao.getId().getAno().toString();
	}

	public void prepararCadastro() {
		if (this.faturaCartao == null)
			this.faturaCartao = new FaturaCartao();
		if (this.listaCartaoDeCredito == null)
			listaCartaoDeCredito = this.getCartaoDeCreditoService().listarPorUsuario(); 
	}
	
	public void atualizarStatusPagamentoFatura() {
		if (this.faturaCartao != null) {
			
			BigDecimal valorPago = this.faturaCartao.getValorPago();
			BigDecimal valorDevido = this.faturaCartao.getValorDevido();
			Date dataVencimento = this.faturaCartao.getDataVencimento();
			Date dataPagamento = this.faturaCartao.getDataPagamento();
			
			boolean valorPagoVazio = valorPago == null || valorPago.compareTo(BigDecimal.ZERO) == 0;
			boolean valorDevidoVazio = valorDevido == null || valorDevido.compareTo(BigDecimal.ZERO) == 0;
			
			if (valorPagoVazio == false && valorDevidoVazio) {
				valorDevido = valorPago;
				this.faturaCartao.setValorDevido(valorDevido);
			}
			
			if (valorPagoVazio) {
				
				Date dataAtual = Util.getDataAtualZeroHoras();
				System.out.println("__________________________");
				System.out.println("Data Atual: " + dataAtual);
				System.out.println("Data Vencimento: " + dataVencimento);
				
				if ( (dataVencimento == null && dataPagamento == null)
						|| (dataVencimento != null && dataPagamento != null)
						|| (dataPagamento != null && dataVencimento == null)
						|| (dataPagamento == null && dataVencimento != null && dataAtual.compareTo(dataVencimento) < 0))
					this.faturaCartao.setStatusFaturaCartao(StatusFaturaCartao.PENDENTE);
				else
					this.faturaCartao.setStatusFaturaCartao(StatusFaturaCartao.ATRASADO);
			}
			else {
				if (valorDevido == null || valorPago.compareTo(valorDevido) >= 0) {
					this.faturaCartao.setStatusFaturaCartao(StatusFaturaCartao.PAGO);
				}
				else {
					this.faturaCartao.setStatusFaturaCartao(StatusFaturaCartao.PARCIALMENTE_PAGO);
				}
			}
		}
	}
	
	public void atualizarStatusPagamentoFatura2(SelectEvent event) {
		
		String nomeComponente = event.getComponent().getId();
		
		if (this.faturaCartao != null) {
			
			BigDecimal valorPago = this.faturaCartao.getValorPago();
			BigDecimal valorDevido = this.faturaCartao.getValorDevido();
			Date dataVencimento = null;
			Date dataPagamento = null;
			if (nomeComponente.equals("data_pagamento")) {
				dataPagamento = (Date) event.getObject();
				dataVencimento = this.faturaCartao.getDataVencimento();
			} 
			else if (nomeComponente.equals("data_vencimento")) {
				dataVencimento = (Date) event.getObject();
				dataPagamento = this.faturaCartao.getDataPagamento();
			}
			
			boolean valorPagoVazio = valorPago == null || valorPago.compareTo(BigDecimal.ZERO) == 0;
			boolean valorDevidoVazio = valorDevido == null || valorDevido.compareTo(BigDecimal.ZERO) == 0;
			
			if (valorPagoVazio == false && valorDevidoVazio) {
				valorDevido = valorPago;
				this.faturaCartao.setValorDevido(valorDevido);
			}
			
			if (valorPagoVazio) {
				
				Date dataAtual = Util.getDataAtualZeroHoras();
				
				if ( (dataVencimento == null && dataPagamento == null)
						|| (dataVencimento != null && dataPagamento != null)
						|| (dataPagamento != null && dataVencimento == null)
						|| (dataPagamento == null && dataVencimento != null && dataAtual.compareTo(dataVencimento) <= 0))
					this.faturaCartao.setStatusFaturaCartao(StatusFaturaCartao.PENDENTE);
				else
					this.faturaCartao.setStatusFaturaCartao(StatusFaturaCartao.ATRASADO);
			}
			else {
				if (valorDevido == null || valorPago.compareTo(valorDevido) >= 0) {
					this.faturaCartao.setStatusFaturaCartao(StatusFaturaCartao.PAGO);
				}
				else {
					this.faturaCartao.setStatusFaturaCartao(StatusFaturaCartao.PARCIALMENTE_PAGO);
				}
			}
		}
	}
	
	public StatusFaturaCartao[] getListaStatusFaturaCartao() {
		return StatusFaturaCartao.values();
	}
	
	public MesDoAno[] getMeses() {
		return MesDoAno.values();
	}

	public String cadastrarFaturaCartao() {
		String retorno;
		boolean inseridoComSucesso = this.getFaturaCartaoService().criarOuAtualizar(faturaCartao);
		if (inseridoComSucesso) {
			retorno = "/pages/lista-fatura-cartao?faces-redirect=true";
		} else {			
			retorno = null;
		}
		return retorno;
	}

	public void excluir() {
		this.getFaturaCartaoService().remover(this.faturaCartaoSelecionada);
		this.listaFaturaCartaoDoUsuario = null;
		this.getListaFaturaCartaoDoUsuario();
	}

	// Getters e Setters

	public FaturaCartao getFaturaCartao() {
		return faturaCartao;
	}

	public void setFaturaCartao(FaturaCartao faturaCartao) {
		this.faturaCartao = faturaCartao;
	}

	public FaturaCartao getFaturaCartaoSelecionada() {
		return faturaCartaoSelecionada;
	}

	public void setFaturaCartaoSelecionada(FaturaCartao faturaCartaoSelecionado) {
		this.faturaCartaoSelecionada = faturaCartaoSelecionado;
	}
	
	public List<FaturaCartao> getListaFaturaCartaoDoUsuario() {
		if (listaFaturaCartaoDoUsuario == null) {
			listaFaturaCartaoDoUsuario = getFaturaCartaoService().listarPorUsuario();
		}
		return listaFaturaCartaoDoUsuario;
	}
	
//	public List<TipoDespesa> getTiposDespesaDoUsuario() {
//		if (this.tiposDespesaDoUsuario == null) {
//			tiposDespesaDoUsuario = getTipoDespesaService().listarPorUsuario();
//		}
//		return tiposDespesaDoUsuario;
//	}
	
	public List<CartaoDeCredito> getListaCartaoDeCredito() {
		return listaCartaoDeCredito;
	}

	public void setListaCartaoDeCredito(List<CartaoDeCredito> listaCartaoDeCredito) {
		this.listaCartaoDeCredito = listaCartaoDeCredito;
	}

	private FaturaCartaoService getFaturaCartaoService() {
		if (this.faturaCartaoService == null)
			this.faturaCartaoService = new FaturaCartaoServiceImpl();
		return faturaCartaoService;
	}
	
	private CartaoDeCreditoService getCartaoDeCreditoService() {
		if (this.cartaoDeCreditoService == null)
			this.cartaoDeCreditoService = new CartaoDeCreditoServiceImpl();
		return cartaoDeCreditoService;
	}
}
