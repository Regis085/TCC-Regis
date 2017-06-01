package com.financaspessoais.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "receita")
public class Receita extends Lancamento {
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "tipo_receita_id")
	private TipoReceita tipoReceita;
}
