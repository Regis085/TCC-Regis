package negocio;

import java.util.Date;

import excecao.NegocioException;
import repositorio.Lancamentos;
import teste.Lancamento2;

public class CadastroLancamentos {
	private Lancamentos lancamentos;

	public CadastroLancamentos(Lancamentos lancamentos) {
		this.lancamentos = lancamentos;
	}

	public void salvar(Lancamento2 lancamento) throws NegocioException {
		if (lancamento.getDataPagamento() != null && lancamento.getDataPagamento().after(new Date())) {
			throw new NegocioException("Data de pagamento não pode ser uma data futura.");
		}
		this.lancamentos.adicionar(lancamento);
	}
}
