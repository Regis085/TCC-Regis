package com.financaspessoais.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Query;

import com.financaspessoais.model.Banco;

@SuppressWarnings("unchecked")
public class BancoDAO extends AbstractGenericDAO<Banco, Short> implements Serializable {
	private static final long serialVersionUID = 1L;

	public BancoDAO() {
		super(Banco.class);
	}
	
	public List<Banco> listarPorProprietario(Short idUsuario) {
		try {
			StringBuilder consulta = new StringBuilder();
			consulta.append("SELECT b FROM Banco b");
			consulta.append(" INNER JOIN b.proprietario u");
			consulta.append(" WHERE u.id = :idUsuario");
			Query query = entityManager.createQuery(consulta.toString());
			query.setParameter("idUsuario", idUsuario);
			List<Banco> listaBanco = (List<Banco>) query.getResultList();
			return listaBanco;
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean validarDuplicidade(Banco banco) throws Exception {
		Short id = banco.getId();
		String nome = banco.getNome();
		Short idUsuario = banco.getProprietario().getId();
		boolean isAtualizando = (id != null);
		
		StringBuilder consulta = new StringBuilder();
		consulta.append("SELECT b FROM Banco b");
		consulta.append(" INNER JOIN b.proprietario u");
		consulta.append(" WHERE u.id = :idUsuario");
		consulta.append("   AND UPPER(b.nome) LIKE :nome");

		if (isAtualizando)
			consulta.append(" AND b.id != :id");

		Query query = entityManager.createQuery(consulta.toString());
		query.setParameter("idUsuario", idUsuario);
		query.setParameter("nome", nome.toUpperCase());

		if (isAtualizando)
			query.setParameter("id", id);

		List<Banco> listaBanco = (List<Banco>) query.getResultList();
		
		Boolean isValido = listaBanco.isEmpty();

		return isValido;
	}
}
