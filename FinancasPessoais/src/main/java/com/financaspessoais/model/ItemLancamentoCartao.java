package com.financaspessoais.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "item_lancamento_cartao")
public class ItemLancamentoCartao implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;

	@ManyToOne(optional = false)
	@JoinColumn(name = "usuario_id")
	private Usuario proprietario;

	@ManyToOne(optional = false)
	@JoinColumn(name = "id_lancamento_cartao")
	private LancamentoCartao lancamentoCartao;

	@ManyToOne(optional = false)
	@JoinColumn(name = "id_fatura_cartao")
	private FaturaCartao faturaCartao;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private StatusItemLancamentoCartao status;

	@Column(precision = 10, scale = 2, nullable = false)
	private BigDecimal valor; // Se for ligado a Credito deve ser negativo, caso
								// contrário deve ser positivo.
								// Preenchido automaticamente, porém editável.
	
	@Column(name = "numero_parcela", nullable = false, length = 3)
	private Short numeroParcela;

	@Column(name = "observacao", length = 100, nullable = true)
	private String observacao;

	@Column(name = "is_avulso", length = 1, nullable = false)
	private String isAvulso;

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

	public LancamentoCartao getLancamentoCartao() {
		return lancamentoCartao;
	}

	public void setLancamentoCartao(LancamentoCartao lancamentoCartao) {
		this.lancamentoCartao = lancamentoCartao;
	}

	public FaturaCartao getFaturaCartao() {
		return faturaCartao;
	}

	public void setFaturaCartao(FaturaCartao faturaCartao) {
		this.faturaCartao = faturaCartao;
	}

	public StatusItemLancamentoCartao getStatus() {
		return status;
	}

	public void setStatus(StatusItemLancamentoCartao status) {
		this.status = status;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public Short getNumeroParcela() {
		return numeroParcela;
	}

	public void setNumeroParcela(Short numeroParcela) {
		this.numeroParcela = numeroParcela;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public String getIsAvulso() {
		return isAvulso;
	}

	public void setIsAvulso(String isAvulso) {
		this.isAvulso = isAvulso;
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
		ItemLancamentoCartao other = (ItemLancamentoCartao) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}