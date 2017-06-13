package com.financaspessoais.service.impl;

import java.io.Serializable;
import java.util.List;

import com.financaspessoais.dao.FaturaCartaoDAO;
import com.financaspessoais.model.FaturaCartao;
import com.financaspessoais.model.Usuario;
import com.financaspessoais.model.pk.FaturaCartaoPK;
import com.financaspessoais.service.FaturaCartaoService;
import com.financaspessoais.util.Constantes;
import com.financaspessoais.util.FacesContextUtil;
import com.financaspessoais.util.SessionContext;

public class FaturaCartaoServiceImpl extends AbstractGenericService implements FaturaCartaoService, Serializable {
	private static final long serialVersionUID = 1L;

	private FaturaCartaoDAO faturaCartaoDAO;
	
	@Override
	public boolean criarOuAtualizar(FaturaCartao faturaCartao) {
		Usuario u = SessionContext.getInstance().getUsuarioLogado();
		faturaCartao.setProprietario(u);
		configurarId(faturaCartao);

		boolean retorno;
		FaturaCartao faturaCartaoBD = null;

		faturaCartaoBD = this.getFaturaCartaoDAO().criarOuAtualizar(faturaCartao);
		if (faturaCartaoBD != null)
			retorno = true;
		else
			retorno = false;

		return retorno;
	}
	
	private void configurarId(FaturaCartao faturaCartao) {
		FaturaCartaoPK id = new FaturaCartaoPK();
		id.setCodigoCartaoDeCredito(faturaCartao.getCartao().getCodigoCartaoDeCredito());
		id.setAno(faturaCartao.getAno());
		id.setMes(faturaCartao.getMes());
		faturaCartao.setId(id);
	}

	@Override
	public void remover(FaturaCartao faturaCartao) {
		this.limparListaMensagemErro();
		try {
			this.getFaturaCartaoDAO().remover(faturaCartao.getId());
		} 
		catch (Exception e) {
			FacesContextUtil.adicionarMensagemDeErro(Constantes.MSG_ERRO_GENERICA);
			e.printStackTrace();
		}
	}

	@Override
	public List<FaturaCartao> listarPorUsuario() {
		Usuario u = SessionContext.getInstance().getUsuarioLogado();
		List<FaturaCartao> listaFaturaCartao = this.getFaturaCartaoDAO().listarPorProprietario(u.getId());
		return listaFaturaCartao;
	}

	@Override
	public FaturaCartao buscar(Short codigoCartaoDeCredito, Short ano, Short mes) {
		FaturaCartaoPK id = new FaturaCartaoPK();
		id.setCodigoCartaoDeCredito(codigoCartaoDeCredito);
		id.setAno(ano);
		id.setMes(mes);
		return this.getFaturaCartaoDAO().buscarPorId(id);
	}

	private FaturaCartaoDAO getFaturaCartaoDAO() {
		if (this.faturaCartaoDAO == null)
			this.faturaCartaoDAO = new FaturaCartaoDAO();
		return faturaCartaoDAO;
	}
}
