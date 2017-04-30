package managedBeans;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.persistence.EntityManager;

import repositorio.Lancamentos;
import teste.Lancamento2;
import utils.JpaUtil;

@ManagedBean
@ViewScoped
public class ConsultaLancamentosBean {
	
	private List<Lancamento2> lancamentos;

	public void consultar() {
		EntityManager manager = JpaUtil.getEntityManager();
		Lancamentos lancamentos = new Lancamentos(manager);
		this.lancamentos = lancamentos.todos();
		manager.close();
	}

	public List<Lancamento2> getLancamentos() {
		return lancamentos;
	}
}
