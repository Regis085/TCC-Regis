package dao;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;

import dominio.Conta;
import utils.JpaUtil;

public class ContaDAO {
	private EntityManager em = JpaUtil.getEntityManager();
	
	public Conta getContaPeloId(Long id) {
		try {
			Conta conta = (Conta) em
					.createQuery("SELECT c from Conta c where c.id = :id")
					.setParameter("id", id).getSingleResult();

			return conta;
		} catch (NoResultException e) {
			return null;
		}
	}
	
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
			em.close();
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
