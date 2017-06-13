package com.financaspessoais.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import com.financaspessoais.model.Conta;
import com.financaspessoais.model.TipoConta;

@SuppressWarnings("unchecked")
public class ContaDAO extends AbstractGenericDAO<Conta, Integer> implements Serializable {
	private static final long serialVersionUID = 1L;

	public ContaDAO() {
		super(Conta.class);
	}
	
	public List<Conta> listarPorProprietario(Short idUsuario) {
		
		TipoConta tipoContaOutro = TipoConta.OUTRO;
		
		try {
			List<Conta> listaConta = (List<Conta>) entityManager
					.createQuery("SELECT c from Conta c " + " INNER JOIN c.proprietario u " + " WHERE u.id = :idUsuario AND c.tipoConta = :tipoConta")
					.setParameter("idUsuario", idUsuario)
					.setParameter("tipoConta", tipoContaOutro).getResultList();
			return listaConta;
		}
		catch (NoResultException e) {
			return null;
		}
	}
	
	public boolean validarDuplicidade(Conta conta) throws Exception {
		Integer id = conta.getId();
		String nome = conta.getNome();
		Short idUsuario = conta.getProprietario().getId();
		boolean isAtualizando = (id != null);

		StringBuilder consulta = new StringBuilder();
		consulta.append("SELECT c FROM Conta c");
		consulta.append(" INNER JOIN c.proprietario u");
		consulta.append(" WHERE u.id = :idUsuario");
		consulta.append("   AND c.nome = :nome");

		if (isAtualizando)
			consulta.append(" AND c.id != :id");

		Query query = entityManager.createQuery(consulta.toString());
		query.setParameter("idUsuario", idUsuario);
		query.setParameter("nome", nome);

		if (isAtualizando)
			query.setParameter("id", id);

		List<Conta> listaConta = (List<Conta>) query.getResultList();
		
		Boolean isValido = listaConta.isEmpty();

		return isValido;
	}
}
