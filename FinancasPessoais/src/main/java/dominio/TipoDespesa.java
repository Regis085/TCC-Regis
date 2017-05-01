package dominio;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tipo_despesa")
public class TipoDespesa {
	
	@Id
	@GeneratedValue
	private Short id;
	private String nome;
	private String descricao;
	private BigDecimal valorPrevisto;
}