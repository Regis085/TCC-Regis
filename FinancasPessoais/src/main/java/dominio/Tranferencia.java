package dominio;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "transferencia")
public class Tranferencia {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(optional = false)
	@JoinColumn(name = "usuario_id")
	private Usuario proprietario;

	@Temporal(TemporalType.DATE)
	@Column(name = "data_transferencia", nullable = false)
	private Date dataTransferencia;
	
	@ManyToOne(optional=false)
	@JoinColumn(name="id_conta_origem")
	private Conta contaOrigem;
	
	@ManyToOne(optional=false)
	@JoinColumn(name="id_conta_destino")
	private Conta contaDestino;
	
	@Column(name="valor", precision = 10, scale = 2, nullable = false)
	private BigDecimal valor;
}
