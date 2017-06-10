package com.financaspessoais.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "despesa")
public class Despesa extends Lancamento {
	
	private static final long serialVersionUID = 1L;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "tipo_despesa_id")
	private TipoDespesa tipoDespesa;
}
