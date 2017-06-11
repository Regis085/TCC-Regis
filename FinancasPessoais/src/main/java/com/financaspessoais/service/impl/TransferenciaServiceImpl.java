package com.financaspessoais.service.impl;

import java.io.Serializable;
import java.util.List;

import com.financaspessoais.dao.TransferenciaDAO;
import com.financaspessoais.model.Transferencia;
import com.financaspessoais.model.Usuario;
import com.financaspessoais.service.TransferenciaService;
import com.financaspessoais.util.SessionContext;

public class TransferenciaServiceImpl extends AbstractGenericService implements TransferenciaService, Serializable {
	private static final long serialVersionUID = 1L;
	private TransferenciaDAO transferenciaDAO;
	
	@Override
	public boolean criarOuAtualizar(Transferencia transferencia) {
		Usuario u = SessionContext.getInstance().getUsuarioLogado();
		transferencia.setProprietario(u);

		boolean retorno;
		Transferencia transferenciaBD = null;

		transferenciaBD = this.getTransferenciaDAO().criarOuAtualizar(transferencia);
		if (transferenciaBD != null)
			retorno = true;
		else
			retorno = false;

		return retorno;
	}

	@Override
	public void remover(Transferencia transferencia) {
		try {
			this.getTransferenciaDAO().remover(transferencia.getId());
		} catch (Exception e) {
			// TODO Auto-generated catch block
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
	
	private TransferenciaDAO getTransferenciaDAO() {
		if (this.transferenciaDAO == null)
			this.transferenciaDAO = new TransferenciaDAO();
		return transferenciaDAO;
	}
}
