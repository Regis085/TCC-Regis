package dominio;

import java.math.BigDecimal;

import javax.persistence.Column;
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

	@Column(name = "nome", nullable = false, length = 60)
	private String nome;

	@Column(name = "nome", nullable = true, length = 255)
	private String descricao;

	@Column(precision = 10, scale = 2, name = "nome", nullable = false, length = 60)
	private BigDecimal valorPrevisto;
}