package dominio;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "fatura_cartao")
public class FaturaCartao {

	@Id
	@GeneratedValue
	private Long id; // Preenchido automaticamente

	@Column(name = "ano", nullable = false)
	private Short ano; // Preenchido automaticamente

	@Column(name = "mes", nullable = false)
	private Short mes; // Preenchido automaticamente

	@Temporal(TemporalType.DATE)
	@Column(name = "data_vencimento", nullable = false)
	private Date dataVencimento; // Preenchido automaticamente, porém editável
									// pelo usuário

	@Temporal(TemporalType.DATE)
	@Column(name = "data_pagamento", nullable = false)
	private Date dataPagamento; // Deve ser preenchido por usuário

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private StatusFaturaCartao status; // Preenchido automaticamente.
										// DataPagamento > 0 Pago ou
										// Parcialmente Pago, etc.

	@Column(name = "valor_devido", precision = 10, scale = 2, nullable = false)
	private BigDecimal valorDevido; // Equivale a soma dos valores dos
									// ItensLancamento

	@Column(name = "valor_pago", precision = 10, scale = 2, nullable = false)
	private BigDecimal valorPago; // Deve ser preenchido por usuário

	@Column(name = "saldo_devido", precision = 10, scale = 2, nullable = false)
	private BigDecimal saldoDevido; // Campo Somente leitura valor devido -
									// valor pago.

	@ManyToOne(optional = false)
	@JoinColumn(name = "id_cartao_de_credito")
	private CartaoDeCredito cartao;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "faturaCartao", fetch = FetchType.LAZY)
	private List<ItemLancamentoCartao> itenslancamento;
}
