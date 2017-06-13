package com.financaspessoais.model.pk;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class FaturaCartaoPK implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Column(name = "codigo_cartao_de_credito", nullable = false)
	private Short codigoCartaoDeCredito;

	@Column(name = "ano_fatura_cartao", nullable = false)
	private Short ano;
	
	@Column(name = "mes_fatura_cartao", nullable = false)
	private Short mes;

	public Short getCodigoCartaoDeCredito() {
		return codigoCartaoDeCredito;
	}

	public void setCodigoCartaoDeCredito(Short codigoCartaoDeCredito) {
		this.codigoCartaoDeCredito = codigoCartaoDeCredito;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ano == null) ? 0 : ano.hashCode());
		result = prime * result + ((codigoCartaoDeCredito == null) ? 0 : codigoCartaoDeCredito.hashCode());
		result = prime * result + ((mes == null) ? 0 : mes.hashCode());
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
		FaturaCartaoPK other = (FaturaCartaoPK) obj;
		if (ano == null) {
			if (other.ano != null)
				return false;
		} else if (!ano.equals(other.ano))
			return false;
		if (codigoCartaoDeCredito == null) {
			if (other.codigoCartaoDeCredito != null)
				return false;
		} else if (!codigoCartaoDeCredito.equals(other.codigoCartaoDeCredito))
			return false;
		if (mes == null) {
			if (other.mes != null)
				return false;
		} else if (!mes.equals(other.mes))
			return false;
		return true;
	}
}
