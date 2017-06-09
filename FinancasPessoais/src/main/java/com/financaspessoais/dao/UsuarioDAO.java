package com.financaspessoais.dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import com.financaspessoais.model.Usuario;
import com.financaspessoais.util.JpaUtil;

public class UsuarioDAO extends GenericDAO<Usuario, Short>{
	
	private EntityManager entityManager = JpaUtil.getEntityManager();
	
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
