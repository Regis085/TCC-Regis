package dominio;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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
public class LancamentoCartao {
	@Id
	@GeneratedValue
	private Long id;
	
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

	@ManyToOne(optional=false)
	@JoinColumn(name="id_cartao_de_credito")
	private CartaoDeCredito cartao;
	
	@ManyToOne(optional=true)
	@JoinColumn(name="id_estabelecimento")
	private Estabelecimento estabelecimento; // Não obrigatório
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy="lancamentoCartao", fetch=FetchType.LAZY)
	private List<ItemLancamentoCartao> itensLancamentoCartao;
}
