package com.financaspessoais.service.impl;

import java.io.Serializable;
import java.util.List;

import com.financaspessoais.dao.CartaoDeCreditoDAO;
import com.financaspessoais.model.CartaoDeCredito;
import com.financaspessoais.model.Usuario;
import com.financaspessoais.service.CartaoDeCreditoService;
import com.financaspessoais.util.Constantes;
import com.financaspessoais.util.FacesContextUtil;
import com.financaspessoais.util.SessionContext;

public class CartaoDeCreditoServiceImpl extends AbstractGenericService implements CartaoDeCreditoService, Serializable {
	private static final long serialVersionUID = 1L;
	
	private CartaoDeCreditoDAO cartaoDeCreditoDAO;

	@Override
	public boolean criarOuAtualizar(CartaoDeCredito cartaoDeCredito) {
		this.limparListaMensagemErro();
		
		Usuario u = SessionContext.getInstance().getUsuarioLogado();
		cartaoDeCredito.setProprietario(u);
		
		validarCamposObrigatorios(cartaoDeCredito);
		validarDuplicidade(cartaoDeCredito);
		
		if (this.getListaMensagemErro().isEmpty())
			this.getCartaoDeCreditoDAO().criarOuAtualizar(cartaoDeCredito);

		boolean retorno;
		
		if (this.getListaMensagemErro().size() > 0) {
			FacesContextUtil.adicionarMensagensDeErro(this.getListaMensagemErro());
			retorno = false;
		}
		else {
			retorno = true;
		}

		return retorno;
	}

	@Override
	public void remover(CartaoDeCredito cartaoDeCredito) {
		this.limparListaMensagemErro();
		try {
			this.getCartaoDeCreditoDAO().remover(cartaoDeCredito.getCodigoCartaoDeCredito());
			FacesContextUtil.adicionarMensagemDeInfo(Constantes.MSG_EXCLUSAO_BEM_SUCEDIDA);
		}
		catch (Exception e) {
			FacesContextUtil.adicionarMensagemDeErro(Constantes.MSG_ERRO_GENERICA);
			e.printStackTrace();
		}
	}

	@Override
	public List<CartaoDeCredito> listarPorUsuario() {
		Usuario u = SessionContext.getInstance().getUsuarioLogado();
		List<CartaoDeCredito> listaCartaoDeCredito = this.getCartaoDeCreditoDAO().listarPorProprietario(u.getId());
		return listaCartaoDeCredito;
	}

	@Override
	public CartaoDeCredito buscar(Short id) {
		return this.getCartaoDeCreditoDAO().buscarPorId(id);
	}
	
	private CartaoDeCreditoDAO getCartaoDeCreditoDAO() {
		if (this.cartaoDeCreditoDAO == null)
			this.cartaoDeCreditoDAO = new CartaoDeCreditoDAO();
		return cartaoDeCreditoDAO;
	}
	
	private void validarDuplicidade(CartaoDeCredito cartaoDeCredito) {
		boolean isValido = true;
		try {
			isValido = this.getCartaoDeCreditoDAO().validarDuplicidade(cartaoDeCredito);
		} 
		catch (Exception e) {
			this.adicionarMensagemErro(Constantes.MSG_ERRO_GENERICA);
			e.printStackTrace();
		}
		
		if (isValido == false)
			this.adicionarMensagemErro(Constantes.MSG_DUPLICIDADE_CARTAO_DE_CREDITO);
	}
	
	private void validarCamposObrigatorios(CartaoDeCredito cartaoDeCredito) {
		if (cartaoDeCredito.getNome() == null || cartaoDeCredito.getNome().trim().isEmpty())
			this.adicionarMensagemErro(Constantes.MSG_CAMPO_OBRIGATORIO, Constantes.MSG_PREENCHER_NOME);
		if (cartaoDeCredito.getBandeira() == null || cartaoDeCredito.getBandeira().trim().isEmpty())
			this.adicionarMensagemErro(Constantes.MSG_CAMPO_OBRIGATORIO, Constantes.MSG_PREENCHER_BANDEIRA);
	}
}
