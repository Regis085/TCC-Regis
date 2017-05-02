package teste;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import dominio.Banco;
import dominio.Conta;
import dominio.ContaBancaria;
import dominio.TipoConta;
import dominio.Usuario;
import service.ContaService;
import service.impl.ContaServiceImpl;
import utils.JpaUtil;

public class Testes {

	public static void main(String[] args) {
		testarContas();
	}
	
	private static void testarContas() {
		
		Usuario u = new Usuario();
		u.setId(new Short("1"));
		
		Banco b = new Banco();
		b.setId(1L);
		
		ContaService cs = new ContaServiceImpl();
		ContaBancaria novaConta = new ContaBancaria();
		
		novaConta.setNome("Citibank ");
		novaConta.setProprietario(u);
		novaConta.setBanco(b);
		novaConta.setNumeroAgencia("?");
		novaConta.setNumeroConta("?");
		novaConta.setEnderecoAgencia("Cais do Apolo, Recife - PE");
		novaConta.setTipoConta(TipoConta.BANCARIA);
		
		boolean isSucesso = cs.inserirConta(novaConta);
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
