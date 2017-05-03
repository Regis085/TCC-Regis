package managedBeans;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import dominio.Usuario;
import service.UsuarioService;
import service.impl.UsuarioServiceImpl;
import utils.SessionContext;
import utils.Util;

@ManagedBean
//@SessionScoped
@ApplicationScoped
public class LoginBean {

	private UsuarioService usuarioService = new UsuarioServiceImpl();
	private Usuario usuario = new Usuario();

	public String cadastrar() {
		return "/CadastroUsuario";
	}

	public String enviar() {

		SessionContext.getInstance().encerrarSessao();

		usuario = usuarioService.getUsuario(usuario.getLogin(), usuario.getSenha());
		if (usuario == null) {
			usuario = new Usuario();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário não encontrado!", "Erro no Login!"));
			return null;
		} else {
//			return "/TelaInicial?faces-redirect=true";
			return "/index?faces-redirect=true";
		}
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String logout() {
		HttpSession session = Util.getSession();
		session.invalidate();
		return "login";
	}
}
