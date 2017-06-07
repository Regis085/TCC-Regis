package com.financaspessoais.dao;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import com.financaspessoais.model.Conta;
import com.financaspessoais.util.JpaUtil;

public class ContaDAO implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private EntityManager entityManager = JpaUtil.getEntityManager();
	
	public Conta criarOuAtualizar(Conta conta) {

		EntityTransaction transacao = entityManager.getTransaction();
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			transacao.begin();
			entityManager.merge(conta);
			transacao.commit();
			return conta;
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

	public Conta buscarPorId(Integer id) {
		
		try {
			if (id != null)
				return entityManager.find(Conta.class, id);
			return null;
		}
		catch (NoResultException e) {
			return null;
		}
	}

	public List<Conta> listarTudo() {
		TypedQuery<Conta> query = entityManager.createQuery("from Conta", Conta.class);
		return query.getResultList();
	}

	public List<Conta> listarPorProprietario(Short idUsuario) {
		try {
			@SuppressWarnings("unchecked")
			List<Conta> listaConta = (List<Conta>) entityManager
					.createQuery("SELECT c from Conta c " + " INNER JOIN c.proprietario u " + " WHERE u.id = :idUsuario")
					.setParameter("idUsuario", idUsuario).getResultList();
			return listaConta;
		}
		catch (NoResultException e) {
			return null;
		}
	}

	public boolean excluir(Conta conta) {
		EntityTransaction transacao = entityManager.getTransaction();
		try {
			transacao.begin();
			entityManager.remove(conta);
			transacao.commit();
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
