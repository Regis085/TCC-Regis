package managedBeans;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import excecao.NegocioException;
import negocio.CadastroLancamentos;
import repositorio.Lancamentos;
import repositorio.Pessoas;
import teste.Lancamento2;
import teste.Pessoa2;
import teste.TipoLancamento2;
import utils.JpaUtil;

@ManagedBean
@ViewScoped
public class CadastroLancamentoBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private Lancamento2 lancamento = new Lancamento2();
	private List<Pessoa2> todasPessoas;

	public void prepararCadastro() {
		EntityManager manager = JpaUtil.getEntityManager();
		try {
			Pessoas pessoas = new Pessoas(manager);
			this.todasPessoas = pessoas.todas();
		} finally {
			manager.close();
		}
	}

	public void salvar() {
		EntityManager manager = JpaUtil.getEntityManager();
		EntityTransaction trx = manager.getTransaction();
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			trx.begin();
			CadastroLancamentos cadastro = new CadastroLancamentos(new Lancamentos(manager));
			cadastro.salvar(this.lancamento);
			this.lancamento = new Lancamento2();
			context.addMessage(null, new FacesMessage("Lançamento salvo com sucesso!"));
			trx.commit();
		} catch (NegocioException e) {
			trx.rollback();
			FacesMessage mensagem = new FacesMessage(e.getMessage());
			mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(null, mensagem);
		} finally {
			manager.close();
		}
	}

	public List<Pessoa2> getTodasPessoas() {
		return this.todasPessoas;
	}

	public TipoLancamento2[] getTiposLancamentos() {
		return TipoLancamento2.values();
	}

	public Lancamento2 getLancamento() {
		return lancamento;
	}

	public void setLancamento(Lancamento2 lancamento) {
		this.lancamento = lancamento;
	}
}