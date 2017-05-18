package managedBeans;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import dominio.Conta;
import dominio.TipoConta;
import service.ContaService;
import service.impl.ContaServiceImpl;

@ManagedBean
@SessionScoped
public class CadastroContaBean {
	private ContaService contaService = new ContaServiceImpl();
	private Conta conta = new Conta();

	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}

	public TipoConta[] getTiposConta() {
		return TipoConta.values();
	}
	
	public List<Conta> getContas() {
		return contaService.listarContas();
	}

	public String cadastrarConta() {
		String retorno;
		boolean inseridoComSucesso = contaService.inserirConta(conta);
		if (inseridoComSucesso) {
			retorno = "/listaContas?faces-redirect=true";
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conta não cadastrada!", "Erro no Cadastro!"));
			retorno = null;
		}
		return retorno;
	}
}
