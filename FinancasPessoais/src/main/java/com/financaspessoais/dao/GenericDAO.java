package com.financaspessoais.dao;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import com.financaspessoais.util.JpaUtil;

public class GenericDAO<T, I extends Serializable> {

	protected EntityManager entityManager = JpaUtil.getEntityManager();

	private Class<T> persistedClass;

	protected GenericDAO() {
	}

	protected GenericDAO(Class<T> persistedClass) {
		this();
		this.persistedClass = persistedClass;
	}
	
	public T criarOuAtualizar(T entity) {

		EntityTransaction t = entityManager.getTransaction();
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			t.begin();
			entityManager.merge(entity);
			entityManager.flush();
			t.commit();
			return entity;
		}
		catch (Exception e) {
			t.rollback();
			FacesMessage mensagem = new FacesMessage(e.getMessage());
			mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(null, mensagem);
			e.printStackTrace();
			return null;
		}
	}

	public T criar(T entity) {
		
		EntityTransaction t = entityManager.getTransaction();
		FacesContext context = FacesContext.getCurrentInstance();
		
		try {
			t.begin();
			entityManager.persist(entity);
			entityManager.flush();
			t.commit();
			return entity;
		} 
		catch (Exception e) {
			t.rollback();
			FacesMessage mensagem = new FacesMessage(e.getMessage());
			mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(null, mensagem);
			e.printStackTrace();
			return null;
		}
	}

	public void remover(I id) {
		
		EntityTransaction t = entityManager.getTransaction();
		FacesContext context = FacesContext.getCurrentInstance();
		
		try {
			T entity = buscarPorId(id);
			t.begin();
			T mergedEntity = entityManager.merge(entity);
			entityManager.remove(mergedEntity);
			entityManager.flush();
			t.commit();
		}
		catch (Exception e) {
			t.rollback();
			FacesMessage mensagem = new FacesMessage(e.getMessage());
			mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(null, mensagem);
			e.printStackTrace();
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
