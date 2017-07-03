package com.financaspessoais.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import com.financaspessoais.model.LancamentoCartao;
import com.financaspessoais.model.pk.LancamentoCartaoPK;
import com.financaspessoais.util.Constantes;
import com.financaspessoais.util.FacesContextUtil;

@SuppressWarnings("unchecked")
public class LancamentoCartaoDAO extends AbstractGenericDAO<LancamentoCartao, Long> implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public LancamentoCartaoDAO() {
		super(LancamentoCartao.class);
	}

	public List<LancamentoCartao> listarPorProprietario(Short idUsuario) {
		try {
			StringBuilder consulta = new StringBuilder();
			consulta.append("SELECT l FROM LancamentoCartao l");
			consulta.append(" INNER JOIN l.proprietario u");
			consulta.append(" WHERE u.id = :idUsuario");
			Query query = entityManager.createQuery(consulta.toString());
			query.setParameter("idUsuario", idUsuario);
			List<LancamentoCartao> listaLancamentoCartao = (List<LancamentoCartao>) query.getResultList();
			return listaLancamentoCartao;
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<LancamentoCartao> listarPorProprietarioECartaoDeCredito(Short idUsuario, Short codigoCartaoDeCredito) {
		
		try {
			StringBuilder consulta = new StringBuilder();
			consulta.append("SELECT l FROM LancamentoCartao l");
			consulta.append(" INNER JOIN l.proprietario u");
			consulta.append(" INNER JOIN l.cartao c");
			consulta.append(" WHERE u.id = :idUsuario");
			consulta.append("   AND c.codigoCartaoDeCredito = :codigoCartaoDeCredito");
			Query query = entityManager.createQuery(consulta.toString());
			query.setParameter("idUsuario", idUsuario);
			query.setParameter("codigoCartaoDeCredito", codigoCartaoDeCredito);
			List<LancamentoCartao> listaLancamentoCartao = (List<LancamentoCartao>) query.getResultList();
			return listaLancamentoCartao;
		}
		catch (Exception e) {
			e.printStackTrace();
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
