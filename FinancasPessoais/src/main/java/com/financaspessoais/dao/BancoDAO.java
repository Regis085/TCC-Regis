package com.financaspessoais.dao;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import com.financaspessoais.model.Banco;
import com.financaspessoais.util.JpaUtil;

public class BancoDAO implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private EntityManager entityManager = JpaUtil.getEntityManager();
	
	public Banco criarOuAtualizar(Banco banco) {

		EntityTransaction transacao = entityManager.getTransaction();
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			transacao.begin();
			entityManager.merge(banco);
			transacao.commit();
			return banco;
		} 
		catch (Exception e) {
			transacao.rollback();
			FacesMessage mensagem = new FacesMessage(e.getMessage());
			mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(null, mensagem);
			e.printStackTrace();
			return null;
		}
	}

	public Banco buscarPorId(Integer id) {
		
		try {
			if (id != null)
				return entityManager.find(Banco.class, id);
			return null;
		}
		catch (NoResultException e) {
			return null;
		}
	}

	public List<Banco> listarTudo() {
		TypedQuery<Banco> query = entityManager.createQuery("from Banco", Banco.class);
		return query.getResultList();
	}

	public List<Banco> listarPorProprietario(Short idUsuario) {
		try {
			@SuppressWarnings("unchecked")
			List<Banco> listaBanco = (List<Banco>) entityManager
					.createQuery("SELECT b from Banco b " + " INNER JOIN b.proprietario u " + " WHERE u.id = :idUsuario")
					.setParameter("idUsuario", idUsuario).getResultList();
			return listaBanco;
		}
		catch (NoResultException e) {
			return null;
		}
	}

	public boolean excluir(Banco banco) {
		EntityTransaction transacao = entityManager.getTransaction();
		try {
			transacao.begin();
			entityManager.remove(banco);
			transacao.commit();
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
