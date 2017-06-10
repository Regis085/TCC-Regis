package com.financaspessoais.model;

import java.io.Serializable;
import java.math.BigDecimal;
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

@Entity
@Table(name = "cartao_de_credito")
public class CartaoDeCredito implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, unique = true)
	private Short id;

	@ManyToOne(optional = false)
	@JoinColumn(name = "usuario_id")
	private Usuario proprietario;
	
	@Column(name="nome", length = 60, nullable = false)
	private String nome;
	
	@Column(name="bandeira", length = 60, nullable = true)
	private String bandeira;
	
	@Temporal(TemporalType.DATE)
	@Column(name="data_inicio", nullable=true)
	private Date dataInicio;
	
	@Column(name = "dia_vencimento", nullable = false, length=2)
	private Short diaVencimento;
	
	@Column(name = "melhor_dia_compra", nullable = false, length=2)
	private Short melhorDiaCompra;
	
	@Column(name = "quatro_ultimos_digitos", nullable = true, length=2)
	private String quatroUltimosDigitos;
	
	@Column(name = "limite_credito", precision = 10, scale = 2, nullable = true)
	private BigDecimal limiteDeCredito;
	
	@Column(name = "telefone_cartao", length = 20, nullable = true)
	private String telefoneCartao;
	
	@Column(name = "site_cartao", length = 60, nullable = true)
	private String siteDoCartao;
	
	@Column(name = "login_site_cartao", length = 60, nullable = true)
	private String loginSiteDoCartao;
	
	@Column(name = "senha_site_cartao", length = 60, nullable = true)
	private String senhaSiteDoCartao;
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy="cartao", fetch=FetchType.LAZY)
	private List<FaturaCartao> faturas;
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy="cartao", fetch=FetchType.LAZY)
	private List<LancamentoCartao> lancamentos;

	public Short getId() {
		return id;
	}

	public void setId(Short id) {
		this.id = id;
	}

	public Usuario getProprietario() {
		return proprietario;
	}

	public void setProprietario(Usuario proprietario) {
		this.proprietario = proprietario;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getBandeira() {
		return bandeira;
	}

	public void setBandeira(String bandeira) {
		this.bandeira = bandeira;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Short getDiaVencimento() {
		return diaVencimento;
	}

	public void setDiaVencimento(Short diaVencimento) {
		this.diaVencimento = diaVencimento;
	}

	public Short getMelhorDiaCompra() {
		return melhorDiaCompra;
	}

	public void setMelhorDiaCompra(Short melhorDiaCompra) {
		this.melhorDiaCompra = melhorDiaCompra;
	}

	public String getQuatroUltimosDigitos() {
		return quatroUltimosDigitos;
	}

	public void setQuatroUltimosDigitos(String quatroUltimosDigitos) {
		this.quatroUltimosDigitos = quatroUltimosDigitos;
	}

	public BigDecimal getLimiteDeCredito() {
		return limiteDeCredito;
	}

	public void setLimiteDeCredito(BigDecimal limiteDeCredito) {
		this.limiteDeCredito = limiteDeCredito;
	}

	public String getTelefoneCartao() {
		return telefoneCartao;
	}

	public void setTelefoneCartao(String telefoneCartao) {
		this.telefoneCartao = telefoneCartao;
	}

	public String getSiteDoCartao() {
		return siteDoCartao;
	}

	public void setSiteDoCartao(String siteDoCartao) {
		this.siteDoCartao = siteDoCartao;
	}

	public String getLoginSiteDoCartao() {
		return loginSiteDoCartao;
	}

	public void setLoginSiteDoCartao(String loginSiteDoCartao) {
		this.loginSiteDoCartao = loginSiteDoCartao;
	}

	public String getSenhaSiteDoCartao() {
		return senhaSiteDoCartao;
	}

	public void setSenhaSiteDoCartao(String senhaSiteDoCartao) {
		this.senhaSiteDoCartao = senhaSiteDoCartao;
	}

	public List<FaturaCartao> getFaturas() {
		return faturas;
	}

	public void setFaturas(List<FaturaCartao> faturas) {
		this.faturas = faturas;
	}

	public List<LancamentoCartao> getLancamentos() {
		return lancamentos;
	}

	public void setLancamentos(List<LancamentoCartao> lancamentos) {
		this.lancamentos = lancamentos;
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
		CartaoDeCredito other = (CartaoDeCredito) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
