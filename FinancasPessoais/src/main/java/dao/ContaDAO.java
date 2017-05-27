package dao;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import dominio.Conta;
import utils.JpaUtil;

public class ContaDAO {
	private EntityManager em = JpaUtil.getEntityManager();

	public Conta getContaPeloId(Long id) {
		try {
			Conta conta = (Conta) em.createQuery("SELECT c from Conta c where c.id = :id").setParameter("id", id)
					.getSingleResult();

			return conta;
		} catch (NoResultException e) {
			return null;
		}
	}

	public Conta porId(Long id) {
		return em.find(Conta.class, id);
	}

	public List<Conta> todas() {
		TypedQuery<Conta> query = em.createQuery("from Conta", Conta.class);
		return query.getResultList();
	}

	public List<Conta> listarContasPorUsuario(Short idUsuario) {
		try {
			@SuppressWarnings("unchecked")
			List<Conta> listaConta = (List<Conta>) em
					.createQuery("SELECT c from Conta c " + " INNER JOIN c.proprietario u " + " WHERE u.id = :idUsuario")
					.setParameter("idUsuario", idUsuario).getResultList();

			return listaConta;
		} catch (NoResultException e) {
			return null;
		}
	}

	// public Conta getTodasContas(Usuario proprietario) {
	// try {
	// Conta conta = (Conta) em
	// .createQuery("SELECT c from Conta c where c.id = :id")
	// .setParameter("id", id).getSingleResult();
	//
	// return conta;
	// } catch (NoResultException e) {
	// return null;
	// }
	// }

	public Conta inserirConta(Conta conta) {

		EntityTransaction trx = em.getTransaction();
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			trx.begin();
			em.persist(conta);
			trx.commit();
			return conta;
		} catch (Exception e) {
			trx.rollback();
			FacesMessage mensagem = new FacesMessage(e.getMessage());
			mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(null, mensagem);
			e.printStackTrace();
			return null;
		} finally {
			// em.close();
		}
	}

	public boolean deletarConta(Conta conta) {
		try {
			em.remove(conta);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
