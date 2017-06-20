package com.financaspessoais.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import com.financaspessoais.model.Lancamento;
import com.financaspessoais.model.Transferencia;
import com.financaspessoais.util.Constantes;
import com.financaspessoais.util.FacesContextUtil;

@SuppressWarnings("unchecked")
public class TransferenciaDAO extends AbstractGenericDAO<Transferencia, Long> implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public TransferenciaDAO() {
		super(Transferencia.class);
	}
	
	public Transferencia criarOuAtualizar(Transferencia transferencia) {

		EntityTransaction transacao = entityManager.getTransaction();
		try {
			transacao.begin();
			
			List<Lancamento> lancamentos = transferencia.getListaLancamento();
			transferencia.setListaLancamento(null);
			transferencia = entityManager.merge(transferencia);
			for (Lancamento l : lancamentos) {
				l.setTransferencia(transferencia);
				entityManager.merge(l);
			}
			
//			entityManager.flush();
			transacao.commit();
			return transferencia;
		}
		catch (Exception e) {
			e.printStackTrace();
			FacesContextUtil.adicionarMensagemDeErro(Constantes.MSG_ERRO_GENERICA);
			transacao.rollback();
			return null;
		}
	}

	public List<Transferencia> listarPorProprietario(Short idUsuario) {
		
		try {
			StringBuilder consulta = new StringBuilder();
			consulta.append("SELECT DISTINCT t FROM Transferencia t");
			consulta.append(" INNER JOIN t.proprietario u");
			consulta.append(" INNER JOIN FETCH t.listaLancamento ll");
			consulta.append(" WHERE u.id = :idUsuario");
			Query query = entityManager.createQuery(consulta.toString());
			query.setParameter("idUsuario", idUsuario);
			List<Transferencia> listaTransferencia = (List<Transferencia>) query.getResultList();
			return listaTransferencia;
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public Transferencia buscarPorId(Long codigoTransferencia) {
		
		try {
			StringBuilder consulta = new StringBuilder();
			consulta.append("SELECT t FROM Transferencia t");
			consulta.append(" INNER JOIN FETCH  t.listaLancamento ll");
			consulta.append(" WHERE t.codigoTransferencia = :codigoTransferencia");
			Query query = entityManager.createQuery(consulta.toString());
			query.setParameter("codigoTransferencia", codigoTransferencia);
			Transferencia transferencia = (Transferencia) query.getSingleResult();
			return transferencia;
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
