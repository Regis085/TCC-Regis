package com.financaspessoais.test;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.financaspessoais.dao.TipoDespesaDAO;
import com.financaspessoais.model.Banco;
import com.financaspessoais.model.Conta;
import com.financaspessoais.model.ContaBancaria;
import com.financaspessoais.model.TipoConta;
import com.financaspessoais.model.TipoDespesa;
import com.financaspessoais.model.Usuario;
import com.financaspessoais.service.ContaService;
import com.financaspessoais.service.TipoDespesaService;
import com.financaspessoais.service.impl.ContaServiceImpl;
import com.financaspessoais.service.impl.TipoDespesaServiceImpl;
import com.financaspessoais.util.JpaUtil;

public class Testes {

	public static void main(String[] args) {
//		testarContas();
		salvarTipoDespesa();
//		removerTipoDespesa();
		listarTiposDespesa();
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
		b.setId(1);
		
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
