package com.financaspessoais.test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import com.financaspessoais.model.Conta;
import com.financaspessoais.model.Lancamento;
import com.financaspessoais.model.SimNao;
import com.financaspessoais.model.StatusLancamento;
import com.financaspessoais.model.TipoLancamento;
import com.financaspessoais.model.Transferencia;
import com.financaspessoais.model.Usuario;
import com.financaspessoais.util.JpaUtil;
import com.financaspessoais.util.Util;

public class TestPersistingList {
	
	protected EntityManager entityManager = JpaUtil.getEntityManager();

	public static void main(String[] args) {

		Usuario u = new Usuario();
		u.setId(new Short("1"));
		Transferencia t = new Transferencia();
		t.setProprietario(u);
		t.setValor(new BigDecimal("100.00"));
		t.setDataTransferencia(Util.getDataAtualZeroHoras());
		setListaLancamento(t, u);
		configurarCampos(t);
		TestPersistingList x = new TestPersistingList();
		x.criarOuAtualizar(t);
	}

	public static void setListaLancamento(Transferencia t, Usuario u) {
		List<Lancamento> listaLancamento = new ArrayList<Lancamento>();

		Lancamento lancamentoDespesa = new Lancamento();
		lancamentoDespesa.setTipoLancamento(TipoLancamento.DESPESA);
		lancamentoDespesa.setIsTransferencia(SimNao.SIM.getCodigo());
		lancamentoDespesa.setProprietario(u);
		lancamentoDespesa.setStatusLancamento(StatusLancamento.REALIZADO);

		Lancamento lancamentoReceita = new Lancamento();
		lancamentoReceita.setTipoLancamento(TipoLancamento.RECEITA);
		lancamentoReceita.setIsTransferencia(SimNao.SIM.getCodigo());
		lancamentoReceita.setProprietario(u);
		lancamentoReceita.setStatusLancamento(StatusLancamento.REALIZADO);

		listaLancamento.add(lancamentoDespesa);
		listaLancamento.add(lancamentoReceita);

		t.setListaLancamento(listaLancamento);
	}

	private static void configurarCampos(Transferencia transferencia) {

		if (transferencia != null) {

			Conta contaOrigem = new Conta();
			Conta contaDestino = new Conta();

			contaOrigem.setId(1);
			contaDestino.setId(2);

			for (Lancamento l : transferencia.getListaLancamento()) {
				l.setDataRealizacao(transferencia.getDataTransferencia());
				l.setValor(transferencia.getValor());
				l.setIsTransferencia(SimNao.SIM.getCodigo());
			}
			contaOrigem = transferencia.getListaLancamento().get(0).getConta();
			contaDestino = transferencia.getListaLancamento().get(1).getConta();
		}
	}
	
	public Transferencia criarOuAtualizar(Transferencia transferencia) {

		EntityTransaction transacao = entityManager.getTransaction();
		try {
			transacao.begin();
			
			List<Lancamento> lancamentos = transferencia.getListaLancamento();
			transferencia.setListaLancamento(null);
			Transferencia transferenciaCriada = entityManager.merge(transferencia);
//			entityManager.flush();
			for (Lancamento l : lancamentos) {
				l.setTransferencia(transferenciaCriada);
				entityManager.merge(l);
			}
			
//			transacao.begin();
			transacao.commit();
			return transferencia;
		}
		catch (RuntimeException re) {
		    re.printStackTrace();
		    transacao.rollback();
		    return null;
		}
		catch (Exception e) {
			e.printStackTrace();
			transacao.rollback();
			return null;
		}
		finally {
			entityManager.close();
		}
	}
	
	// não grava referência a transferência nos lançamentos
	public Transferencia criarOuAtualizar2(Transferencia transferencia) {

		EntityTransaction transacao = entityManager.getTransaction();
		try {
			transacao.begin();
			
			entityManager.merge(transferencia);
//			List<Lancamento> lancamentos = transferencia.getListaLancamento();
//			transferencia.setListaLancamento(null);
//			for (Lancamento l : lancamentos) {
//				l.setTransferencia(transferencia);
//				entityManager.merge(l);
//			}
			
			entityManager.flush();
			transacao.commit();
			return transferencia;
		}
		catch (Exception e) {
			transacao.rollback();
			e.printStackTrace();
			return null;
		}
		finally {
			entityManager.close();
		}
	}
}
