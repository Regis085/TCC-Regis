package com.financaspessoais.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import com.financaspessoais.model.Estabelecimento;

@SuppressWarnings("unchecked")
public class EstabelecimentoDAO extends AbstractGenericDAO<Estabelecimento, Long> implements Serializable {
	private static final long serialVersionUID = 1L;

	public EstabelecimentoDAO() {
		super(Estabelecimento.class);
	}

	public List<Estabelecimento> listarPorProprietario(Short idUsuario) {
		try {
			List<Estabelecimento> listaEstabelecimento = (List<Estabelecimento>) entityManager
					.createQuery("SELECT e from Estabelecimento e " + " INNER JOIN e.proprietario u " + " WHERE u.id = :idUsuario")
					.setParameter("idUsuario", idUsuario).getResultList();
			return listaEstabelecimento;
		}
		catch (NoResultException e) {
			return null;
		}
	}
	
	public boolean validarDuplicidade(Estabelecimento estabelecimento) throws Exception {

		Long id = estabelecimento.getId();
		String nome = estabelecimento.getNome();
		Short idUsuario = estabelecimento.getProprietario().getId();
		boolean isAtualizando = (id != null);

		StringBuilder consulta = new StringBuilder();
		consulta.append("SELECT e FROM Estabelecimento e");
		consulta.append(" INNER JOIN e.proprietario u");
		consulta.append(" WHERE u.id = :idUsuario");
		consulta.append("   AND e.nome = :nome");

		if (isAtualizando)
			consulta.append(" AND e.id != :id");

		Query query = entityManager.createQuery(consulta.toString());
		query.setParameter("idUsuario", idUsuario);
		query.setParameter("nome", nome);

		if (isAtualizando)
			query.setParameter("id", id);

		List<Estabelecimento> listaEstabelecimento = (List<Estabelecimento>) query.getResultList();
		
		Boolean isValido = listaEstabelecimento.isEmpty();

		return isValido;
	}
}
