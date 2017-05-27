package managedBeans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.servlet.http.HttpSession;

import dominio.Usuario;
import utils.SessionContext;
import utils.Util;

@ManagedBean
@SessionScoped
public class TelaInicioBean {

	private String nome;
	private String login;
	private Short id;
	private String nomePerfil;
	private String cpf;

	public TelaInicioBean() {
		System.out.println("Entrou em LoginBean");
		Usuario u = (Usuario) SessionContext.getInstance().getAttribute("usuarioLogado");
		if (u != null) {
			System.out.println(u.getNome());
			
			this.nome = u.getNome();
			this.login = u.getLogin();
			this.id = u.getId();
			this.nomePerfil = u.getPerfil().getNome();
			this.cpf = u.getCpf();
		}
		else {
			System.out.println("Sessão Finalizada!");
		}
	}
	
	public String logout() {
		HttpSession session = Util.getSession();
		session.invalidate();
		return "/pages/login2?faces-redirect=true";
	}

	// public String getNomeUsuario() {
	// Usuario u = (Usuario)
	// SessionContext.getInstance().getAttribute("usuarioLogado");
	// return u.getNome();
	// }

	public String getNome() {
		return nome;
	}

	public String getLogin() {
		return login;
	}

	public Short getId() {
		return id;
	}

	public String getNomePerfil() {
		return nomePerfil;
	}

	public String getCpf() {
		return cpf;
	}

}
