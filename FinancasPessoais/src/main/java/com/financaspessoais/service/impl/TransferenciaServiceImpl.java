package com.financaspessoais.service.impl;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import com.financaspessoais.dao.TransferenciaDAO;
import com.financaspessoais.model.Conta;
import com.financaspessoais.model.Lancamento;
import com.financaspessoais.model.SimNao;
import com.financaspessoais.model.Transferencia;
import com.financaspessoais.model.Usuario;
import com.financaspessoais.service.TransferenciaService;
import com.financaspessoais.util.Constantes;
import com.financaspessoais.util.FacesContextUtil;
import com.financaspessoais.util.SessionContext;

public class TransferenciaServiceImpl extends AbstractGenericService implements TransferenciaService, Serializable {
	private static final long serialVersionUID = 1L;
	private TransferenciaDAO transferenciaDAO;

	@Override
	public boolean criarOuAtualizar(Transferencia transferencia) {
		limparListaMensagemErro();

		validarCamposObrigatorios(transferencia);
		configurarCampos(transferencia);

		Usuario u = SessionContext.getInstance().getUsuarioLogado();
		transferencia.setProprietario(u);

		boolean retorno;

		if (this.getListaMensagemErro().isEmpty())
			this.getTransferenciaDAO().criarOuAtualizar(transferencia);

		if (this.getListaMensagemErro().size() > 0) {
			FacesContextUtil.adicionarMensagensDeErro(this.getListaMensagemErro());
			retorno = false;
		} else {
			retorno = true;
		}

		return retorno;
	}

	private void configurarCampos(Transferencia transferencia) {
			
		if (transferencia != null) {
			
			Conta contaOrigem = null;
			Conta contaDestino =  null;
			
			if (transferencia.getListaLancamento() != null && transferencia.getListaLancamento().size() == 2){
				// TODO preencher com TipoDespesa/TipoReceita correspondendo a Transferência.
				//transferencia.getListaLancamento().get(0).getTipoLancamento()
				for (Lancamento l : transferencia.getListaLancamento()) {
					l.setDataRealizacao(transferencia.getDataTransferencia());
					l.setValor(transferencia.getValor());
					l.setIsTransferencia(SimNao.SIM.getCodigo());
				}
				contaOrigem = transferencia.getListaLancamento().get(0).getConta();
				contaDestino = transferencia.getListaLancamento().get(1).getConta();
				
				if (contaOrigem == null || contaDestino == null) {
					this.adicionarMensagemErro(Constantes.MSG_CAMPO_OBRIGATORIO, Constantes.MSG_PREENCHER_CONTAS);
				}
				else if (contaOrigem.getId().equals(contaDestino.getId()))  {
					
					this.adicionarMensagemErro(Constantes.MSG_VALOR_INVALIDO, Constantes.MSG_CAMPO_INVALIDO_CONTAS_IGUAIS);
				}
			}
			else {
				// lancar erro pois tem que ser dois a qtd de itens da lista de lançamentos
			}
		}
	}

	@Override
	public void remover(Transferencia transferencia) {

		this.limparListaMensagemErro();
		try {
			this.getTransferenciaDAO().remover(transferencia.getCodigoTransferencia());
		} catch (Exception e) {
			FacesContextUtil.adicionarMensagemDeErro(Constantes.MSG_ERRO_GENERICA);
			e.printStackTrace();
		}
	}

	@Override
	public List<Transferencia> listarPorUsuario() {
		Usuario u = SessionContext.getInstance().getUsuarioLogado();
		List<Transferencia> listaTransferencia = this.getTransferenciaDAO().listarPorProprietario(u.getId());
		return listaTransferencia;
	}

	@Override
	public Transferencia buscar(Long id) {
		return this.getTransferenciaDAO().buscarPorId(id);
	}

	private void validarCamposObrigatorios(Transferencia transferencia) {

		if (transferencia.getValor() == null || transferencia.getValor().compareTo(BigDecimal.ZERO) == 0) {
			this.adicionarMensagemErro(Constantes.MSG_CAMPO_OBRIGATORIO, Constantes.MSG_PREENCHER_VALOR);
		}
		
		if (transferencia.getDataTransferencia() == null) {
			this.adicionarMensagemErro(Constantes.MSG_CAMPO_OBRIGATORIO, Constantes.MSG_PREENCHER_DATA_TRANSFERENCIA);
		}
	}

	private TransferenciaDAO getTransferenciaDAO() {
		if (this.transferenciaDAO == null)
			this.transferenciaDAO = new TransferenciaDAO();
		return transferenciaDAO;
	}
}
