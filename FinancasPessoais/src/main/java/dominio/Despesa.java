package dominio;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "despesa")
public class Despesa extends Lancamento {
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "tipo_despesa_id")
	private TipoDespesa tipoDespesa;
}
