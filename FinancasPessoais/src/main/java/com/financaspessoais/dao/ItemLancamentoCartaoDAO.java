package com.financaspessoais.dao;

import java.util.List;

import javax.persistence.NoResultException;

import com.financaspessoais.model.ItemLancamentoCartao;

public class ItemLancamentoCartaoDAO extends AbstractGenericDAO<ItemLancamentoCartao, Long>{

	public ItemLancamentoCartaoDAO() {
		super(ItemLancamentoCartao.class);
	}

	public List<ItemLancamentoCartao> listarPorProprietario(Short idUsuario) {
		try {
			@SuppressWarnings("unchecked")
			List<ItemLancamentoCartao> listaItemLancamentoCartao = (List<ItemLancamentoCartao>) entityManager
					.createQuery("SELECT i from ItemLancamentoCartao i " + " INNER JOIN i.proprietario u " + " WHERE u.id = :idUsuario")
					.setParameter("idUsuario", idUsuario).getResultList();
			return listaItemLancamentoCartao;
		}
		catch (NoResultException e) {
			return null;
		}
	}
}
