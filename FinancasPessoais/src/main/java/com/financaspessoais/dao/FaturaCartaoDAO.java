package com.financaspessoais.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import com.financaspessoais.model.FaturaCartao;
import com.financaspessoais.model.pk.FaturaCartaoPK;
import com.financaspessoais.util.Constantes;
import com.financaspessoais.util.FacesContextUtil;

@SuppressWarnings("unchecked")
public class FaturaCartaoDAO extends AbstractGenericDAO<FaturaCartao, Long> implements Serializable {
	private static final long serialVersionUID = 1L;

	public FaturaCartaoDAO() {
		super(FaturaCartao.class);
	}

	public List<FaturaCartao> listarPorProprietario(Short idUsuario) {
		
		try {
			StringBuilder consulta = new StringBuilder();
			consulta.append("SELECT f FROM FaturaCartao f");
			consulta.append(" INNER JOIN f.proprietario u");
			consulta.append(" WHERE u.id = :idUsuario");
			Query query = entityManager.createQuery(consulta.toString());
			query.setParameter("idUsuario", idUsuario);
			List<FaturaCartao> listaFaturaCartao = (List<FaturaCartao>) query.getResultList();
			return listaFaturaCartao;
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<FaturaCartao> listarPorProprietarioECartaoDeCredito(Short idUsuario, Short codigoCartaoDeCredito) {
		
		try {
			StringBuilder consulta = new StringBuilder();
			consulta.append("SELECT f FROM FaturaCartao f");
			consulta.append(" INNER JOIN f.proprietario u");
			consulta.append(" INNER JOIN f.cartao c");
			consulta.append(" WHERE u.id = :idUsuario");
			consulta.append("   AND c.codigoCartaoDeCredito = :codigoCartaoDeCredito");
			Query query = entityManager.createQuery(consulta.toString());
			query.setParameter("idUsuario", idUsuario);
			query.setParameter("codigoCartaoDeCredito", codigoCartaoDeCredito);
			List<FaturaCartao> listaFaturaCartao = (List<FaturaCartao>) query.getResultList();
			return listaFaturaCartao;
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public void remover(FaturaCartaoPK id) throws Exception {
		EntityTransaction transacao = entityManager.getTransaction();
		
		try {
			FaturaCartao entity = buscarPorId(id);
			transacao.begin();
			FaturaCartao mergedEntity = entityManager.merge(entity);
			entityManager.remove(mergedEntity);
			entityManager.flush();
			transacao.commit();
		}
		catch (Exception e) {
			transacao.rollback();
			throw e;
		}
	}
	
	public FaturaCartao buscarPorId(FaturaCartaoPK id) {
		try {
			return entityManager.find(FaturaCartao.class, id);
		}
		catch (Exception e) {
			FacesContextUtil.adicionarMensagemDeErro(Constantes.MSG_ERRO_GENERICA);
			return null;
		}
	}
}
