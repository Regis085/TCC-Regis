package com.financaspessoais.dao;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;

import com.financaspessoais.model.Usuario;
import com.financaspessoais.util.JpaUtil;

public class UsuarioDAO {
	private EntityManager em = JpaUtil.getEntityManager();

	public Usuario getUsuario(String login, String senha) {

		try {
			Usuario usuario = (Usuario) em
					.createQuery("SELECT u from Usuario u where u.login = :login and u.senha = :senha")
					.setParameter("login", login).setParameter("senha", senha).getSingleResult();

			return usuario;
		} catch (NoResultException e) {
			return null;
		}
	}

	public Usuario inserirUsuario(Usuario usuario) {

		EntityTransaction trx = em.getTransaction();
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			trx.begin();
			em.persist(usuario);
			trx.commit();
			return usuario;
		} catch (Exception e) {
			trx.rollback();
			FacesMessage mensagem = new FacesMessage(e.getMessage());
			mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(null, mensagem);
			e.printStackTrace();
			return null;
		} finally {
//			em.close();
		}
	}

	public boolean deletarUsuario(Usuario usuario) {
		try {
			em.remove(usuario);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
