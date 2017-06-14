package com.financaspessoais.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tipo_receita")
public class TipoReceita implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Short id;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "usuario_id")
	private Usuario proprietario;
	
	@Column(name = "nome", nullable = false, length = 60)
	private String nome;
	
	@Column(name = "descricao", length = 255)
	private String descricao;
	
	@Column(name = "recorrente", nullable = false, length = 1)
	private String recorrente; // se for recorrente, renderizar valor previsto e dia Recebimento Previsto

	@Column(name = "valor_previsto", precision = 10, scale = 2, length = 60)
	private BigDecimal valorPrevisto;
	
	@Column(name = "dia_recebimento_previsto", length=2)
	private Short diaRecebimentoPrevisto;
	
	public Short getId() {
		return id;
	}

	public void setId(Short id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public String getRecorrente() {
		return recorrente;
	}

	public void setRecorrente(String recorrente) {
		this.recorrente = recorrente;
	}

	public Short getDiaRecebimentoPrevisto() {
		return diaRecebimentoPrevisto;
	}

	public void setDiaRecebimentoPrevisto(Short diaRecebimentoPrevisto) {
		this.diaRecebimentoPrevisto = diaRecebimentoPrevisto;
	}

	public BigDecimal getValorPrevisto() {
		return valorPrevisto;
	}

	public void setValorPrevisto(BigDecimal valorPrevisto) {
		this.valorPrevisto = valorPrevisto;
	}
	
	public Usuario getProprietario() {
		return proprietario;
	}

	public void setProprietario(Usuario proprietario) {
		this.proprietario = proprietario;
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
		TipoReceita other = (TipoReceita) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
