package dominio;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "estabelecimento")
public class Estabelecimento {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "usuario_id")
	private Usuario proprietario;
	
	@Column(name="nome", nullable=false, length=60)
	private String nome;
	
	@Column(name="cnpj", nullable=true, length=18)
	private String cnpj;
	
	@Column(name="endereco", nullable=true, length=255)
	private String endereco;
	
	@Column(name="telefone", nullable=true, length=20)
	private String telefone;

}
