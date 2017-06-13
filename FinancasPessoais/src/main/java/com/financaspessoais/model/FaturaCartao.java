package com.financaspessoais.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.financaspessoais.model.pk.FaturaCartaoPK;

@Entity
@Table(name = "fatura_cartao")
public class FaturaCartao implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public String getChaveComposta() {
		String retorno = null;
		if (this != null && this.getId() != null) {
			retorno = this.getId().getCodigoCartaoDeCredito().toString() + "#" + this.getId().getAno().toString() +  "#" + this.getId().getMes().toString();
		}		
		return retorno;
	}
	
	@EmbeddedId
	private FaturaCartaoPK id;

	@ManyToOne(optional = false)
	@JoinColumn(name = "usuario_id")
	private Usuario proprietario;

	@Column(name = "ano_fatura_cartao", insertable = false, updatable = false)
	private Short ano; // Preenchido automaticamente

	@Column(name = "mes_fatura_cartao", insertable = false, updatable = false)
	private Short mes; // Preenchido automaticamente
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "codigo_cartao_de_credito", insertable = false, updatable = false)
	private CartaoDeCredito cartao;

	@Temporal(TemporalType.DATE)
	@Column(name = "data_vencimento", nullable = false)
	private Date dataVencimento; // Preenchido automaticamente, porém editável
									// pelo usuário

	@Temporal(TemporalType.DATE)
	@Column(name = "data_pagamento", nullable = false)
	private Date dataPagamento; // Deve ser preenchido por usuário

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private StatusFaturaCartao statusFaturaCartao; // Preenchido automaticamente.
										// DataPagamento > 0 Pago ou
										// Parcialmente Pago, etc.

	@Column(name = "valor_devido", precision = 10, scale = 2, nullable = false)
	private BigDecimal valorDevido; // Equivale a soma dos valores dos
									// ItensLancamento

	@Column(name = "valor_pago", precision = 10, scale = 2, nullable = false)
	private BigDecimal valorPago; // Deve ser preenchido por usuário

	@Column(name = "saldo_devido", precision = 10, scale = 2, nullable = false)
	private BigDecimal saldoDevido; // Campo Somente leitura valor devido -
									// valor pago.

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "faturaCartao", fetch = FetchType.LAZY)
	private List<ItemLancamentoCartao> itenslancamento;

	public FaturaCartaoPK getId() {
		return id;
	}

	public void setId(FaturaCartaoPK id) {
		this.id = id;
	}

	public Usuario getProprietario() {
		return proprietario;
	}

	public void setProprietario(Usuario proprietario) {
		this.proprietario = proprietario;
	}

	public Short getAno() {
		return ano;
	}

	public void setAno(Short ano) {
		this.ano = ano;
	}

	public Short getMes() {
		return mes;
	}

	public void setMes(Short mes) {
		this.mes = mes;
	}

	public Date getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public Date getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

	public StatusFaturaCartao getStatusFaturaCartao() {
		return statusFaturaCartao;
	}

	public void setStatusFaturaCartao(StatusFaturaCartao status) {
		this.statusFaturaCartao = status;
	}

	public BigDecimal getValorDevido() {
		return valorDevido;
	}

	public void setValorDevido(BigDecimal valorDevido) {
		this.valorDevido = valorDevido;
	}

	public BigDecimal getValorPago() {
		return valorPago;
	}

	public void setValorPago(BigDecimal valorPago) {
		this.valorPago = valorPago;
	}

	public BigDecimal getSaldoDevido() {
		return saldoDevido;
	}

	public void setSaldoDevido(BigDecimal saldoDevido) {
		this.saldoDevido = saldoDevido;
	}

	public CartaoDeCredito getCartao() {
		return cartao;
	}

	public void setCartao(CartaoDeCredito cartao) {
		this.cartao = cartao;
	}

	public List<ItemLancamentoCartao> getItenslancamento() {
		return itenslancamento;
	}

	public void setItenslancamento(List<ItemLancamentoCartao> itenslancamento) {
		this.itenslancamento = itenslancamento;
	}

}
