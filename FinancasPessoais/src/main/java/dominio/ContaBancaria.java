package dominio;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "conta_bancaria")
public class ContaBancaria extends Conta{
	
	@Column(name="numero_agencia", nullable = true, length=10)
	private String numeroAgencia;
	
	@Column(name="numero_conta", nullable = true, length=15)
	private String numeroConta;
	
	@ManyToOne(optional = false)
	@JoinColumn(name="id_banco")
	private Banco banco;
	
	@Column(name="telefone_agencia", nullable = true, length=15)
	private String telefoneAgencia;
	
	@Column(name="endereco_agencia", nullable = true, length=255)
	private String enderecoAgencia;
	
	@Column(name="nome_gerente", nullable = true, length=80)
	private String nomeGerente;
	
	@Column(name="telefone_gerente", nullable = true, length=15)
	private String telefoneGerente;
	
	@Column(name="email_gerente", nullable = true, length=100)
	private String emailGerente;
}
