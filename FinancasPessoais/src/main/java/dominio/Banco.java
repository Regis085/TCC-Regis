package dominio;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="banco")
public class Banco {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, unique = true)
	private Long id;
	
	@Column(name="nome", nullable = false, unique = true)
	private String nome;
	
	@Column(name="site", nullable = true, length=50)
	private String site;

	@Column(name="site", nullable = true, length=15)
	private String telefone;
	
	@Column(name="endereco", nullable = true, length=255)
	private String endereco;
}
