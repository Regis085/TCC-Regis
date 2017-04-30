package managedBeans;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import dominio.Usuario;
import service.UsuarioService;
import service.impl.UsuarioServiceImpl;

@ManagedBean
@SessionScoped
public class CadastroUsuarioBean {
	private UsuarioService usuarioService = new UsuarioServiceImpl();
	private Usuario usuario = new Usuario();

	public String cadastrarUsuario() {
		String retorno;
		boolean inseridoComSucesso = usuarioService.inserirUsuario(usuario);
		if (inseridoComSucesso) {
			retorno = "/TelaInicial";
		} else {
//			FacesContext.getCurrentInstance().addMessage(null,
//					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário não cadastrado!", "Erro no Cadastro!"));
			retorno = null;
		}
		return retorno;
	}

	public String voltar() {
		return "/Login";
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
