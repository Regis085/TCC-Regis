package com.financaspessoais.dao;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import com.financaspessoais.model.ContaBancaria;
import com.financaspessoais.model.TipoConta;
import com.financaspessoais.util.JpaUtil;

public class ContaBancariaDAO implements Serializable{
	private static final long serialVersionUID = 1L;

	private EntityManager entityManager = JpaUtil.getEntityManager();

	public ContaBancaria criarOuAtualizar(ContaBancaria contaBancaria) {

		EntityTransaction transacao = entityManager.getTransaction();
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			transacao.begin();
			entityManager.merge(contaBancaria);
			transacao.commit();
			return contaBancaria;
		} catch (Exception e) {
			transacao.rollback();
			FacesMessage mensagem = new FacesMessage(e.getMessage());
			mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(null, mensagem);
			e.printStackTrace();
			return null;
		}
	}

	public ContaBancaria buscarPorId(Integer id) {

		try {
			if (id != null)
				return entityManager.find(ContaBancaria.class, id);
			return null;
		} catch (NoResultException e) {
			return null;
		}
	}

	public List<ContaBancaria> listarTudo() {
		TypedQuery<ContaBancaria> query = entityManager.createQuery("from ContaBancaria", ContaBancaria.class);
		return query.getResultList();
	}

	public List<ContaBancaria> listarPorProprietario(Short idUsuario) {
		
		TipoConta tipoContaBancaria = TipoConta.BANCARIA;
		
		try {
			@SuppressWarnings("unchecked")
			List<ContaBancaria> listaContaBancaria = (List<ContaBancaria>) entityManager
					.createQuery(
							"SELECT c from ContaBancaria c " + " INNER JOIN c.proprietario u " + " WHERE u.id = :idUsuario AND c.tipoConta = :tipoConta")
					.setParameter("idUsuario", idUsuario)
					.setParameter("tipoConta", tipoContaBancaria).getResultList();
			return listaContaBancaria;
		} catch (NoResultException e) {
			return null;
		}
	}

	public boolean excluir(ContaBancaria contaBancaria) {
		EntityTransaction transacao = entityManager.getTransaction();
		try {
			transacao.begin();
			entityManager.remove(contaBancaria);
			transacao.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
