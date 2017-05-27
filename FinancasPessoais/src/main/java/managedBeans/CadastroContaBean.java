package managedBeans;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import dominio.Conta;
import dominio.TipoConta;
import service.ContaService;
import service.impl.ContaServiceImpl;

@ManagedBean
//@SessionScoped
@ViewScoped
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
	
	public List<Conta> getContasDoUsuario() {
		return contaService.listarContasPorUsuario();
	}

	public String cadastrarConta() {
		String retorno;
		boolean inseridoComSucesso = contaService.inserirConta(conta);
		if (inseridoComSucesso) {
			retorno = "/pages/listaContas2?faces-redirect=true";
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conta não cadastrada!", "Erro no Cadastro!"));
			retorno = null;
		}
		return retorno;
	}
}
