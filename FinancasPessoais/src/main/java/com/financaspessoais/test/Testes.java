package com.financaspessoais.test;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import com.financaspessoais.dao.TipoDespesaDAO;
import com.financaspessoais.model.Banco;
import com.financaspessoais.model.Conta;
import com.financaspessoais.model.ContaBancaria;
import com.financaspessoais.model.FaturaCartao;
import com.financaspessoais.model.ItemLancamentoCartao;
import com.financaspessoais.model.LancamentoCartao;
import com.financaspessoais.model.StatusItemLancamentoCartao;
import com.financaspessoais.model.TipoConta;
import com.financaspessoais.model.TipoDespesa;
import com.financaspessoais.model.Usuario;
import com.financaspessoais.model.pk.FaturaCartaoPK;
import com.financaspessoais.model.pk.ItemLancamentoCartaoPK;
import com.financaspessoais.model.pk.LancamentoCartaoPK;
import com.financaspessoais.service.ContaService;
import com.financaspessoais.service.TipoDespesaService;
import com.financaspessoais.service.impl.ContaServiceImpl;
import com.financaspessoais.service.impl.TipoDespesaServiceImpl;
import com.financaspessoais.util.JpaUtil;

public class Testes {

	public static void main(String[] args) {
//		testarContas();
//		salvarTipoDespesa();
//		removerTipoDespesa();
//		listarTiposDespesa();
//		criarItemLancamentoCartao();
		
//		listarFaturas(new Short("1"));
		listarItensLancamentoCartao(new Short("1"));
	}
	
	@SuppressWarnings("unchecked")
	public static void listarItensLancamentoCartao(Short idUsuario) {
		EntityManager entityManager = JpaUtil.getEntityManager();
		List<ItemLancamentoCartao> listaItensLancamento = null;
		
		try {
			StringBuilder consulta = new StringBuilder();
			consulta.append("SELECT DISTINCT i FROM ItemLancamentoCartao i");
			consulta.append(" INNER JOIN i.proprietario u");
			consulta.append(" WHERE u.id = :idUsuario");
			Query query = entityManager.createQuery(consulta.toString());
			query.setParameter("idUsuario", idUsuario);
			listaItensLancamento = (List<ItemLancamentoCartao>) query.getResultList();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		if (listaItensLancamento != null) {
			for (ItemLancamentoCartao i : listaItensLancamento) {
				System.out.println(i.getFaturaCartao().getNome());
			}
		}
	}

	@SuppressWarnings("unchecked")
	public static void listarFaturas(Short idUsuario) {
		
		EntityManager entityManager = JpaUtil.getEntityManager();
		List<FaturaCartao> listaFatura = null;
		
		try {
			StringBuilder consulta = new StringBuilder();
			consulta.append("SELECT DISTINCT f FROM FaturaCartao f");
			consulta.append(" INNER JOIN f.proprietario u");
			consulta.append(" INNER JOIN FETCH f.itenslancamento ll");
			consulta.append(" WHERE u.id = :idUsuario");
			Query query = entityManager.createQuery(consulta.toString());
			query.setParameter("idUsuario", idUsuario);
			listaFatura = (List<FaturaCartao>) query.getResultList();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		if (listaFatura != null) {
			for (FaturaCartao f : listaFatura) {
				boolean haItens = f.getItenslancamento() != null;
				if (haItens) {
					for (ItemLancamentoCartao i : f.getItenslancamento()) {
						System.out.println(i.toString());
					}
				}
			}
		}
	}
	
	public static void criarItemLancamentoCartao() {
		FaturaCartao f = new FaturaCartao();
		FaturaCartaoPK fId = new FaturaCartaoPK();
		fId.setAno(new Short("2017"));
		fId.setMes(new Short("3"));
		fId.setCodigoCartaoDeCredito(new Short("1"));
		f.setId(fId);
		
		LancamentoCartao l = new LancamentoCartao();
		LancamentoCartaoPK lId = new LancamentoCartaoPK();
		lId.setCodigoCartaoDeCredito(new Short("1"));
		lId.setCodigoLancamentoCartao(1L);
		l.setId(lId);
		
		Usuario u = new Usuario();
		u.setId(new Short("1"));
		
		ItemLancamentoCartao i = new ItemLancamentoCartao();
		ItemLancamentoCartaoPK iId = new ItemLancamentoCartaoPK();
		iId.setCodigoCartaoDeCredito(fId.getCodigoCartaoDeCredito());
		iId.setCodigoItemLancamentoCartao(1L);
		iId.setCodigoLancamentoCartao(lId.getCodigoLancamentoCartao());
		i.setId(iId);
		i.setValor(new BigDecimal("100"));
		i.setIsCredito("N");
		i.setNumeroParcela(new Short("1"));
		i.setProprietario(u);
		i.setStatus(StatusItemLancamentoCartao.PREVISTO);
		
		EntityManager entityManager = JpaUtil.getEntityManager();
		
		EntityTransaction transacao = entityManager.getTransaction();
		try {
			transacao.begin();
			entityManager.merge(i);
			entityManager.flush();
			transacao.commit();
		}
		catch (Exception e) {
			transacao.rollback();
			e.printStackTrace();
		}
	}

	public static void listarTiposDespesa() {
		TipoDespesaDAO dao = new TipoDespesaDAO();
		List<TipoDespesa> lista = dao.listar();
		for(TipoDespesa t : lista) {
			System.out.println(t);
		}
	}

	public static void removerTipoDespesa() {
		TipoDespesaService service = new TipoDespesaServiceImpl();
		TipoDespesa t = service.buscar(new Short("1"));
		System.out.println("Tipo Despesa: " + t.toString());
		service.remover(t);
		t = service.buscar(new Short("1"));
		System.out.println("Tipo Despesa: " + t.toString());
	}
	
	public static void salvarTipoDespesa() {
		TipoDespesa tipoDespesa = new TipoDespesa();
		TipoDespesaDAO dao = new TipoDespesaDAO();
		tipoDespesa.setDescricao("teste 5");
		tipoDespesa.setNome("teste 5");
		tipoDespesa.setValorPrevisto(new BigDecimal("1890.75"));
		dao.criar(tipoDespesa);
		TipoDespesa t = dao.buscarPorId(tipoDespesa.getId());
		System.out.println("Tipo Despesa: " + t.toString());
	}
	
	public static void testarContas() {
		
		Usuario u = new Usuario();
		u.setId(new Short("1"));
		
		Banco b = new Banco();
		b.setId(new Short("1"));
		
		ContaService cs = new ContaServiceImpl();
		ContaBancaria novaConta = new ContaBancaria();
		
		novaConta.setNome("Citibank ");
		novaConta.setProprietario(u);
		novaConta.setBanco(b);
		novaConta.setNumeroAgencia("?");
		novaConta.setNumeroConta("?");
		novaConta.setEnderecoAgencia("Cais do Apolo, Recife - PE");
		novaConta.setTipoConta(TipoConta.BANCARIA);
		
		boolean isSucesso = cs.criarOuAtualizar(novaConta);
		if (isSucesso) {
			System.out.println("Conta inserida com Sucesso");
			System.out.println("Id Conta inserida: " + novaConta.getId());
		}
		else {
			System.out.println("Falha em inserção de Conta");
		}
		
		EntityManager em = JpaUtil.getEntityManager();
		Query q = em.createQuery("Select c FROM Conta c");
		@SuppressWarnings("unchecked")
		List<Conta> contas = q.getResultList();
		for (Conta c : contas) {
			System.out.println(c.getNome());
		}
	}

}
