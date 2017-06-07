package com.financaspessoais.test;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.financaspessoais.model.Banco;
import com.financaspessoais.model.Conta;
import com.financaspessoais.model.ContaBancaria;
import com.financaspessoais.model.TipoConta;
import com.financaspessoais.model.Usuario;
import com.financaspessoais.service.ContaService;
import com.financaspessoais.service.impl.ContaServiceImpl;
import com.financaspessoais.util.JpaUtil;

public class Testes {

	public static void main(String[] args) {
//		testarContas();
		testarContaBancaria();
	}
	
	private static void testarContaBancaria() {
		
	}
	
	private static void testarContas() {
		
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
		
		boolean isSucesso = cs.criarOuAtualizarConta(novaConta);
		if (isSucesso) {
			System.out.println("Conta inserida com Sucesso");
			System.out.println("Id Conta inserida: " + novaConta.getId());
		}
		else {
			System.out.println("Falha em inserção de Conta");
		}
		
		EntityManager em = JpaUtil.getEntityManager();
		Query q = em.createQuery("Select c FROM Conta c");
		List<Conta> contas = q.getResultList();
		for (Conta c : contas) {
			System.out.println(c.getNome());
		}
	}

}
