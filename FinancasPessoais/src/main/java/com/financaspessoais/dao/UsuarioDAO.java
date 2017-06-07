package com.financaspessoais.dao;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import com.financaspessoais.model.Usuario;
import com.financaspessoais.util.JpaUtil;

public class UsuarioDAO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private EntityManager entityManager = JpaUtil.getEntityManager();
	
	public Usuario buscarPorId(Short id) {
		
		Usuario usuario = null;
		try {
			if (id != null)
				usuario = entityManager.find(Usuario.class, id);
		}
		catch (NoResultException e) {
			System.out.println("Não localizado na base de dados");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return usuario;
	}

	public Usuario buscarPorLoginESenha(String login, String senha) {

		Usuario usuario = null;
		try {
			Query query = entityManager.createQuery("SELECT u from Usuario u where u.login = :login and u.senha = :senha");
			query.setParameter("login", login);
			query.setParameter("senha", senha);
			usuario = (Usuario) query.getSingleResult();
		}
		catch (NoResultException e) {
			System.out.println("Não localizado na base de dados");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return usuario;
	}

	public Usuario criar(Usuario usuario) {

		EntityTransaction transacao = entityManager.getTransaction();
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			transacao.begin();
			entityManager.persist(usuario);
			transacao.commit();
			return usuario;
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

	public boolean excluir(Usuario usuario) {
		try {
			entityManager.remove(usuario);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
