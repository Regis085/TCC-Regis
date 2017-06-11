package com.financaspessoais.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import com.financaspessoais.util.Constantes;
import com.financaspessoais.util.FacesContextUtil;
import com.financaspessoais.util.JpaUtil;

public abstract class AbstractGenericDAO<T, I extends Serializable> {

	protected EntityManager entityManager = JpaUtil.getEntityManager();

	private Class<T> persistedClass;

	protected AbstractGenericDAO() {
	}

	protected AbstractGenericDAO(Class<T> persistedClass) {
		this();
		this.persistedClass = persistedClass;
	}
	
	public T criarOuAtualizar(T entity) {

		EntityTransaction transacao = entityManager.getTransaction();
		try {
			transacao.begin();
			entityManager.merge(entity);
			entityManager.flush();
			transacao.commit();
			return entity;
		}
		catch (Exception e) {
			transacao.rollback();
			FacesContextUtil.adicionarMensagemDeErro(Constantes.MSG_ERRO_GENERICA);
			e.printStackTrace();
			return null;
		}
	}

	public T criar(T entity) {
		
		EntityTransaction transacao = entityManager.getTransaction();
		
		try {
			transacao.begin();
			entityManager.persist(entity);
			entityManager.flush();
			transacao.commit();
			return entity;
		} 
		catch (Exception e) {
			transacao.rollback();
			FacesContextUtil.adicionarMensagemDeErro(Constantes.MSG_ERRO_GENERICA);
			e.printStackTrace();
			return null;
		}
	}

	public boolean remover(I id) throws Exception{
		
		EntityTransaction transacao = entityManager.getTransaction();
		
		try {
			T entity = buscarPorId(id);
			transacao.begin();
			T mergedEntity = entityManager.merge(entity);
			entityManager.remove(mergedEntity);
			entityManager.flush();
			transacao.commit();
			return true;
		}
		catch (Exception e) {
			transacao.rollback();
			FacesContextUtil.adicionarMensagemDeErro(Constantes.MSG_ERRO_GENERICA);
			e.printStackTrace();
			return false;
		}
	}

	public List<T> listar() {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<T> query = builder.createQuery(persistedClass);
		query.from(persistedClass);
		return entityManager.createQuery(query).getResultList();
	}

	public T buscarPorId(I id) {
		return entityManager.find(persistedClass, id);
	}
}
