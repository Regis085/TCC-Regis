package teste;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import dominio.Conta;
import utils.JpaUtil;

public class Testes {

	public static void main(String[] args) {
		EntityManager em = JpaUtil.getEntityManager();
		Query q = em.createQuery("Select c FROM Conta c");
		List<Conta> contas = q.getResultList();
		for (Conta c : contas) {
			System.out.println(c.getNome());
		}
	}

}
