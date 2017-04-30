package repositorio;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import teste.Lancamento2;

public class Lancamentos {
	private EntityManager manager;

	public Lancamentos(EntityManager manager) {
		this.manager = manager;
	}

	public List<Lancamento2> todos() {
		TypedQuery<Lancamento2> query = manager.createQuery("from Lancamento", Lancamento2.class);
		return query.getResultList();
	}

	public void adicionar(Lancamento2 lancamento) {
		this.manager.persist(lancamento);
	}
}
