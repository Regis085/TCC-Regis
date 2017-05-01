package repositorio;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import teste.Pessoa2;

public class Pessoas {
	private EntityManager manager;

	public Pessoas(EntityManager manager) {
		this.manager = manager;
	}

	public Pessoa2 porId(Long id) {
		return manager.find(Pessoa2.class, id);
	}

	public List<Pessoa2> todas() {
		TypedQuery<Pessoa2> query = manager.createQuery("from Pessoa2", Pessoa2.class);
		return query.getResultList();
	}
}