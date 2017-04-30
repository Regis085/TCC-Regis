package dominio;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "perfil")
public class Perfil {
	
	private Short id;
	private String nome;
	
	@Id
	@GeneratedValue
	public Short getId() {
		return id;
	}
	public void setId(Short id) {
		this.id = id;
	}
	
	@Column(length = 50, nullable = false)
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
}
