package com.financaspessoais.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.NoResultException;

import com.financaspessoais.model.Lancamento;

public class LancamentoDAO extends AbstractGenericDAO<Lancamento, Long> implements Serializable {
	private static final long serialVersionUID = 1L;

	public LancamentoDAO() {
		super(Lancamento.class);
	}

	public List<Lancamento> listarPorProprietario(Short idUsuario) {
		try {
			@SuppressWarnings("unchecked")
			List<Lancamento> listaLancamento = (List<Lancamento>) entityManager
					.createQuery("SELECT l from Lancamento l " + " INNER JOIN l.proprietario u " + " WHERE u.id = :idUsuario" + " ORDER BY l.dataRealizacao DESC, l.descricao ASC")
					.setParameter("idUsuario", idUsuario).getResultList();
			return listaLancamento;
		}
		catch (NoResultException e) {
			return null;
		}
	}
}
