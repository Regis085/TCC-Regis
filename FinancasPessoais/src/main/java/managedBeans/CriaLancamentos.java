package managedBeans;

import java.math.BigDecimal;
import java.util.Calendar;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import teste.Lancamento2;
import teste.Pessoa2;
import teste.TipoLancamento2;
import utils.JpaUtil;

public class CriaLancamentos {
	public static void main(String[] args) {
		EntityManager manager = JpaUtil.getEntityManager();
		EntityTransaction trx = manager.getTransaction();
		trx.begin();
		Calendar dataVencimento1 = Calendar.getInstance();
		dataVencimento1.set(2013, 10, 1, 0, 0, 0);
		Calendar dataVencimento2 = Calendar.getInstance();
		dataVencimento2.set(2013, 12, 10, 0, 0, 0);
		Pessoa2 cliente = new Pessoa2();
		cliente.setNome("WWW Indústria de Alimentos");
		Pessoa2 fornecedor = new Pessoa2();
		fornecedor.setNome("SoftBRAX Treinamentos");
		Lancamento2 lancamento1 = new Lancamento2();
		lancamento1.setDescricao("Venda de licença de software");
		lancamento1.setPessoa(cliente);
		lancamento1.setDataVencimento(dataVencimento1.getTime());
		lancamento1.setDataPagamento(dataVencimento1.getTime());
		lancamento1.setValor(new BigDecimal("103000"));
		lancamento1.setTipo(TipoLancamento2.RECEITA);
		Lancamento2 lancamento2 = new Lancamento2();
		lancamento2.setDescricao("Venda de suporte anual");
		lancamento2.setPessoa(cliente);
		lancamento2.setDataVencimento(dataVencimento1.getTime());
		lancamento2.setDataPagamento(dataVencimento1.getTime());
		// lancamento2.setValor(new BigDecimal(15_000));
		lancamento2.setTipo(TipoLancamento2.RECEITA);
		Lancamento2 lancamento3 = new Lancamento2();
		lancamento3.setDescricao("Treinamento da equipe");
		lancamento3.setPessoa(fornecedor);
		lancamento3.setDataVencimento(dataVencimento2.getTime());
		lancamento3.setValor(new BigDecimal("68000"));
		lancamento3.setTipo(TipoLancamento2.DESPESA);
		manager.persist(cliente);
		manager.persist(fornecedor);
		manager.persist(lancamento1);
		manager.persist(lancamento2);
		manager.persist(lancamento3);
		trx.commit();
		manager.close();
	}
}