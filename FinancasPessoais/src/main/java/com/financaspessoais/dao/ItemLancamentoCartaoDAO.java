package com.financaspessoais.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import com.financaspessoais.model.ItemLancamentoCartao;
import com.financaspessoais.model.pk.ItemLancamentoCartaoPK;
import com.financaspessoais.util.Constantes;
import com.financaspessoais.util.FacesContextUtil;

@SuppressWarnings("unchecked")
public class ItemLancamentoCartaoDAO extends AbstractGenericDAO<ItemLancamentoCartao, Long> implements Serializable {
	private static final long serialVersionUID = 1L;

	public ItemLancamentoCartaoDAO() {
		super(ItemLancamentoCartao.class);
	}

	public List<ItemLancamentoCartao> listarPorProprietario(Short idUsuario) {
		try {
			
			List<ItemLancamentoCartao> listaItemLancamentoCartao = (List<ItemLancamentoCartao>) entityManager
					.createQuery("SELECT i from ItemLancamentoCartao i " + " INNER JOIN i.proprietario u " + " WHERE u.id = :idUsuario")
					.setParameter("idUsuario", idUsuario).getResultList();
			return listaItemLancamentoCartao;
		}
		catch (NoResultException e) {
			return null;
		}
	}
	
	public void remover(ItemLancamentoCartaoPK id) throws Exception {
		EntityTransaction transacao = entityManager.getTransaction();
		
		try {
			ItemLancamentoCartao entity = buscarPorId(id);
			transacao.begin();
			ItemLancamentoCartao mergedEntity = entityManager.merge(entity);
			entityManager.remove(mergedEntity);
			entityManager.flush();
			transacao.commit();
		}
		catch (Exception e) {
			transacao.rollback();
			throw e;
		}
	}

	public ItemLancamentoCartao buscarPorId(ItemLancamentoCartaoPK id) {
		try {
			return entityManager.find(ItemLancamentoCartao.class, id);
		}
		catch (Exception e) {
			FacesContextUtil.adicionarMensagemDeErro(Constantes.MSG_ERRO_GENERICA);
			return null;
		}
	}
	
	public Long getNextId(Short codigoCartaoDeCredito, Long codigoLancamentoCartao) {
		
		Long max = this.getMaxId(codigoCartaoDeCredito, codigoLancamentoCartao);
		return ++max;
	}
	
	public Long getMaxId(Short codigoCartaoDeCredito, Long codigoLancamentoCartao) {
		
		Long retorno = null;
		
		StringBuilder consulta = new StringBuilder();
		consulta.append("SELECT MAX(i.id.codigoItemLancamentoCartao) FROM ItemLancamentoCartao i");
		consulta.append(" WHERE i.id.codigoCartaoDeCredito = :codigoCartaoDeCredito");
		consulta.append("   AND i.id.codigoLancamentoCartao = :codigoLancamentoCartao");
		
		try {
			Query query = entityManager.createQuery(consulta.toString());
			query.setParameter("codigoCartaoDeCredito", codigoCartaoDeCredito);
			query.setParameter("codigoLancamentoCartao", codigoLancamentoCartao);
			retorno = (Long) query.getSingleResult();
		}
		catch (Exception e) {
			System.out.println("Retorno: " + retorno);
			e.printStackTrace();
		}
		
		if (retorno == null)
			retorno = 0L;
		
		return retorno;
	}
}
