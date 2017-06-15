package com.financaspessoais.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.financaspessoais.util.SessionContext;

@Entity
@Table(name = "transferencia")
public class Transferencia implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "codigo_transferencia")
	private Long codigoTransferencia;

	@ManyToOne(optional = false)
	@JoinColumn(name = "usuario_id")
	private Usuario proprietario;

	@Temporal(TemporalType.DATE)
	@Column(name = "data_transferencia", nullable = false)
	private Date dataTransferencia;
	
	@Column(name="valor", precision = 10, scale = 2, nullable = false)
	private BigDecimal valor;
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy="transferencia", fetch=FetchType.LAZY)
	private List<Lancamento> listaLancamento;
	
	public Long getCodigoTransferencia() {
		return codigoTransferencia;
	}

	public void setCodigoTransferencia(Long codigoTransferencia) {
		this.codigoTransferencia = codigoTransferencia;
	}

	public Usuario getProprietario() {
		return proprietario;
	}

	public void setProprietario(Usuario proprietario) {
		this.proprietario = proprietario;
	}

	public Date getDataTransferencia() {
		return dataTransferencia;
	}

	public void setDataTransferencia(Date dataTransferencia) {
		this.dataTransferencia = dataTransferencia;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	
	public List<Lancamento> getListaLancamento() {
		if (listaLancamento == null || listaLancamento.isEmpty()) {
			listaLancamento = new ArrayList<Lancamento>();
			
			Lancamento lancamentoDespesa = new Lancamento();
			lancamentoDespesa.setTipoLancamento(TipoLancamento.DESPESA);
			lancamentoDespesa.setIsTransferencia(SimNao.SIM.getCodigo());
			lancamentoDespesa.setProprietario(SessionContext.getInstance().getUsuarioLogado());
			lancamentoDespesa.setStatusLancamento(StatusLancamento.REALIZADO);
			
			Lancamento lancamentoReceita = new Lancamento();
			lancamentoReceita.setTipoLancamento(TipoLancamento.RECEITA);
			lancamentoReceita.setIsTransferencia(SimNao.SIM.getCodigo());
			lancamentoReceita.setProprietario(SessionContext.getInstance().getUsuarioLogado());
			lancamentoReceita.setStatusLancamento(StatusLancamento.REALIZADO);
			
			listaLancamento.add(lancamentoDespesa);
			listaLancamento.add(lancamentoReceita);
		}
		
		return listaLancamento;
	}
	
	public void setListaLancamento(List<Lancamento> listaLancamento) {
		this.listaLancamento = listaLancamento;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigoTransferencia == null) ? 0 : codigoTransferencia.hashCode());
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
		Transferencia other = (Transferencia) obj;
		if (codigoTransferencia == null) {
			if (other.codigoTransferencia != null)
				return false;
		} else if (!codigoTransferencia.equals(other.codigoTransferencia))
			return false;
		return true;
	}
}
