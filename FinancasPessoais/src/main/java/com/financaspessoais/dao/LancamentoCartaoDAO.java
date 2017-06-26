package com.financaspessoais.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import com.financaspessoais.model.LancamentoCartao;
import com.financaspessoais.model.pk.LancamentoCartaoPK;
import com.financaspessoais.util.Constantes;
import com.financaspessoais.util.FacesContextUtil;

public class LancamentoCartaoDAO extends AbstractGenericDAO<LancamentoCartao, Long> implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public LancamentoCartaoDAO() {
		super(LancamentoCartao.class);
	}

	public List<LancamentoCartao> listarPorProprietario(Short idUsuario) {
		try {
			@SuppressWarnings("unchecked")
			List<LancamentoCartao> listaLancamentoCartao = (List<LancamentoCartao>) entityManager
					.createQuery("SELECT l from LancamentoCartao l " + " INNER JOIN l.proprietario u " + " WHERE u.id = :idUsuario")
					.setParameter("idUsuario", idUsuario).getResultList();
			return listaLancamentoCartao;
		}
		catch (NoResultException e) {
			return null;
		}
	}
	
	public Long getNextId(Short codigoCartaoDeCredito) {
		
		Long max = this.getMaxId(codigoCartaoDeCredito);
		return ++max;
	}
	
	public Long getMaxId(Short codigoCartaoDeCredito) {
		
		Long retorno = null;
		
		StringBuilder consulta = new StringBuilder();
		consulta.append("SELECT MAX(l.id.codigoLancamentoCartao) FROM LancamentoCartao l");
		consulta.append(" WHERE l.id.codigoCartaoDeCredito = :codigoCartaoDeCredito");
		
		try {
			Query query = entityManager.createQuery(consulta.toString());
			query.setParameter("codigoCartaoDeCredito", codigoCartaoDeCredito);
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
	
	public void remover(LancamentoCartaoPK id) throws Exception {
		EntityTransaction transacao = entityManager.getTransaction();
		
		try {
			LancamentoCartao entity = buscarPorId(id);
			transacao.begin();
			LancamentoCartao mergedEntity = entityManager.merge(entity);
			entityManager.remove(mergedEntity);
			entityManager.flush();
			transacao.commit();
		}
		catch (Exception e) {
			transacao.rollback();
			throw e;
		}
	}
	
	public LancamentoCartao buscarPorId(LancamentoCartaoPK id) {
		try {
			return entityManager.find(LancamentoCartao.class, id);
		}
		catch (Exception e) {
			FacesContextUtil.adicionarMensagemDeErro(Constantes.MSG_ERRO_GENERICA);
			return null;
		}
	}
}
