package com.financaspessoais.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "fatura_cartao")
public class FaturaCartao implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id; // Preenchido automaticamente

	@ManyToOne(optional = false)
	@JoinColumn(name = "usuario_id")
	private Usuario proprietario;

	@Column(name = "ano", nullable = false)
	private Short ano; // Preenchido automaticamente

	@Column(name = "mes", nullable = false)
	private Short mes; // Preenchido automaticamente

	@Temporal(TemporalType.DATE)
	@Column(name = "data_vencimento", nullable = false)
	private Date dataVencimento; // Preenchido automaticamente, porém editável
									// pelo usuário

	@Temporal(TemporalType.DATE)
	@Column(name = "data_pagamento", nullable = false)
	private Date dataPagamento; // Deve ser preenchido por usuário

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private StatusFaturaCartao status; // Preenchido automaticamente.
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

	@ManyToOne(optional = false)
	@JoinColumn(name = "id_cartao_de_credito")
	private CartaoDeCredito cartao;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "faturaCartao", fetch = FetchType.LAZY)
	private List<ItemLancamentoCartao> itenslancamento;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	public StatusFaturaCartao getStatus() {
		return status;
	}

	public void setStatus(StatusFaturaCartao status) {
		this.status = status;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FaturaCartao other = (FaturaCartao) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
