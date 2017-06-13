package com.financaspessoais.model.pk;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class LancamentoCartaoPK implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name = "codigo_cartao_de_credito", nullable = false)
	private Short codigoCartaoDeCredito;

	@Column(name = "codigo_lancamento_cartao", nullable = false)
	private Long codigoLancamentoCartao;

	public Short getCodigoCartaoDeCredito() {
		return codigoCartaoDeCredito;
	}

	public void setCodigoCartaoDeCredito(Short codigoCartaoDeCredito) {
		this.codigoCartaoDeCredito = codigoCartaoDeCredito;
	}

	public Long getCodigoLancamentoCartao() {
		return codigoLancamentoCartao;
	}

	public void setCodigoLancamentoCartao(Long codigoLancamentoCartao) {
		this.codigoLancamentoCartao = codigoLancamentoCartao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigoCartaoDeCredito == null) ? 0 : codigoCartaoDeCredito.hashCode());
		result = prime * result + ((codigoLancamentoCartao == null) ? 0 : codigoLancamentoCartao.hashCode());
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
		LancamentoCartaoPK other = (LancamentoCartaoPK) obj;
		if (codigoCartaoDeCredito == null) {
			if (other.codigoCartaoDeCredito != null)
				return false;
		} else if (!codigoCartaoDeCredito.equals(other.codigoCartaoDeCredito))
			return false;
		if (codigoLancamentoCartao == null) {
			if (other.codigoLancamentoCartao != null)
				return false;
		} else if (!codigoLancamentoCartao.equals(other.codigoLancamentoCartao))
			return false;
		return true;
	}
}
