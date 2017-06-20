package com.financaspessoais.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "lancamento")
public class Lancamento implements Serializable, Cloneable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name="codigo_lancamento")
	private Long codigoLancamento;
	
//	@ManyToOne(cascade = {CascadeType.DETACH}, optional = true)
	@ManyToOne(optional = true)
	@JoinColumn(name = "codigo_transferencia")
	private Transferencia transferencia; // Não obrigatório

	public Transferencia getTransferencia() {
		return transferencia;
	}
	
	public void setTransferencia(Transferencia transferencia) {
		this.transferencia = transferencia;
	}
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "usuario_id")
	private Usuario proprietario;
	
	@Enumerated(EnumType.ORDINAL)
	@Column(nullable = false)
	private TipoLancamento tipoLancamento; // 1 - Receita, 2 - Despesa
	
	@Enumerated(EnumType.ORDINAL)
	@Column(nullable = false)
	private StatusLancamento statusLancamento; // 1 - Pendente, 2 - Realizado
	
	@ManyToOne
	@JoinColumn(name = "tipo_despesa_id")
	private TipoDespesa tipoDespesa;
	
	@ManyToOne(optional=true)
	@JoinColumn(name="id_estabelecimento")
	private Estabelecimento estabelecimento; // Não obrigatório

	@ManyToOne
	@JoinColumn(name = "tipo_receita_id")
	private TipoReceita tipoReceita;
	
	@Column(name = "valor", precision = 10, scale = 2, nullable = false)
	private BigDecimal valor;

	@ManyToOne
	@JoinColumn(name = "conta_id")
	private Conta conta;

	@Column(name = "descricao", length = 255)
	private String descricao;
	
	@Column(name = "is_transferencia", length = 1, nullable = false)
	private String isTransferencia;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "data_prevista")
	private Date dataPrevista;

	@Temporal(TemporalType.DATE)
	@Column(name = "data_realizacao")
	private Date dataRealizacao;
	
	public String getNome1() {
		String retorno = null;
		if (conta != null) {
			
		}
		else {
			
		}
		return retorno;
	}
	
	public String getNome2() {
		String retorno = null;
		if (conta != null) {
			
		}
		return retorno;
	}
	
	public Long getCodigoLancamento() {
		return codigoLancamento;
	}

	public void setCodigoLancamento(Long id) {
		this.codigoLancamento = id;
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

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public Date getDataPrevista() {
		return dataPrevista;
	}

	public void setDataPrevista(Date dataVencimento) {
		this.dataPrevista = dataVencimento;
	}

	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}

	public Date getDataRealizacao() {
		return dataRealizacao;
	}

	public void setDataRealizacao(Date dataRealizacao) {
		this.dataRealizacao = dataRealizacao;
	}
	
	public TipoLancamento getTipoLancamento() {
		return tipoLancamento;
	}

	public void setTipoLancamento(TipoLancamento tipoLancamento) {
		this.tipoLancamento = tipoLancamento;
	}
	
	public StatusLancamento getStatusLancamento() {
		return statusLancamento;
	}
	
	public void setStatusLancamento(StatusLancamento statusLancamento) {
		this.statusLancamento = statusLancamento;
	}
	
	public TipoDespesa getTipoDespesa() {
		return tipoDespesa;
	}

	public void setTipoDespesa(TipoDespesa tipoDespesa) {
		this.tipoDespesa = tipoDespesa;
	}

	public TipoReceita getTipoReceita() {
		return tipoReceita;
	}

	public void setTipoReceita(TipoReceita tipoReceita) {
		this.tipoReceita = tipoReceita;
	}
	
	public Estabelecimento getEstabelecimento() {
		return estabelecimento;
	}

	public void setEstabelecimento(Estabelecimento estabelecimento) {
		this.estabelecimento = estabelecimento;
	}
	
	public String getIsTransferencia() {
		return isTransferencia;
	}

	public void setIsTransferencia(String isTransferencia) {
		this.isTransferencia = isTransferencia;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigoLancamento == null) ? 0 : codigoLancamento.hashCode());
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
		Lancamento other = (Lancamento) obj;
		if (codigoLancamento == null) {
			if (other.codigoLancamento != null)
				return false;
		} else if (!codigoLancamento.equals(other.codigoLancamento))
			return false;
		return true;
	}
}
