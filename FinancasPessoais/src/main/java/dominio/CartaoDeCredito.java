package dominio;

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
public class CartaoDeCredito {
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
}
