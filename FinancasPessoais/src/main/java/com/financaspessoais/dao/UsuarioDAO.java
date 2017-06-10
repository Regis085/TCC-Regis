package com.financaspessoais.dao;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import com.financaspessoais.model.Usuario;

public class UsuarioDAO extends AbstractGenericDAO<Usuario, Short>{
	
	public UsuarioDAO() {
		super(Usuario.class);
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
	
	public Usuario buscarPorLogin(Usuario usuario) {
		Usuario usuarioBD = null;
		try {
			Query query = entityManager.createQuery("SELECT u FROM Usuario u WHERE u.login = :login");
			query.setParameter("login", usuario.getLogin());
			usuarioBD = (Usuario) query.getSingleResult();
		}
		catch (NoResultException e) {
			System.out.println("Não localizado na base de dados");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return usuarioBD;
	}
}
