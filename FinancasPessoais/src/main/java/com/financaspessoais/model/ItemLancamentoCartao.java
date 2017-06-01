package com.financaspessoais.model;

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
public class ItemLancamentoCartao {

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
}