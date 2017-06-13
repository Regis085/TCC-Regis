package com.financaspessoais.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.financaspessoais.model.pk.LancamentoCartaoPK;

/**
 * @author JRegis
 * Equivale à compra feita em algum estabelecimento, ou a um crédito feito no cartão de crédito.
 * Divide-se em Items Lancamento que são parcelas.
 * Cada um dos itens são associados a uma fatura.
 * 
 * Se for juros, multa, etc. Usuário deve criar um lancamento de parcela única e colocar
 * na observação do que se trata.
 *
 */

@Entity
@Table(name = "lancamento_cartao")
public class LancamentoCartao implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private LancamentoCartaoPK id;

	@ManyToOne(optional = false)
	@JoinColumn(name = "usuario_id")
	private Usuario proprietario;
	
	@Column(length = 255, nullable = false)
	private String descricao;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "data_lancamento", nullable = false)
	private Date dataLancamento;
	
	@Column(precision = 10, scale = 2, nullable = false)
	private BigDecimal valor;
	
	@Column(name = "numero_parcelas", nullable = false, length=3)
	private Short numeroParcelas;
	
	@Column(length = 1, nullable = false)
	private String isCredito; // Se for crédito, valor deve ser negativo.
	
	@Column(length = 100, nullable = true)
	private String observacao;

	@ManyToOne
	@JoinColumn(name="codigo_cartao_de_credito", insertable = false, updatable = false)
	private CartaoDeCredito cartao;
	
	@ManyToOne(optional=true)
	@JoinColumn(name="id_estabelecimento")
	private Estabelecimento estabelecimento; // Não obrigatório
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy="lancamentoCartao", fetch=FetchType.LAZY)
	private List<ItemLancamentoCartao> itensLancamentoCartao;

	public LancamentoCartaoPK getId() {
		return id;
	}

	public void setId(LancamentoCartaoPK id) {
		this.id = id;
	}

	public Usuario getProprietario() {
		return proprietario;
	}

	public void setProprietario(Usuario proprietario) {
		this.proprietario = proprietario;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Date getDataLancamento() {
		return dataLancamento;
	}

	public void setDataLancamento(Date dataLancamento) {
		this.dataLancamento = dataLancamento;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public Short getNumeroParcelas() {
		return numeroParcelas;
	}

	public void setNumeroParcelas(Short numeroParcelas) {
		this.numeroParcelas = numeroParcelas;
	}

	public String getIsCredito() {
		return isCredito;
	}

	public void setIsCredito(String isCredito) {
		this.isCredito = isCredito;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public CartaoDeCredito getCartao() {
		return cartao;
	}

	public void setCartao(CartaoDeCredito cartao) {
		this.cartao = cartao;
	}

	public Estabelecimento getEstabelecimento() {
		return estabelecimento;
	}

	public void setEstabelecimento(Estabelecimento estabelecimento) {
		this.estabelecimento = estabelecimento;
	}

	public List<ItemLancamentoCartao> getItensLancamentoCartao() {
		return itensLancamentoCartao;
	}

	public void setItensLancamentoCartao(List<ItemLancamentoCartao> itensLancamentoCartao) {
		this.itensLancamentoCartao = itensLancamentoCartao;
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
		LancamentoCartao other = (LancamentoCartao) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
