package com.financaspessoais.service.impl;

import java.io.Serializable;
import java.util.List;

import com.financaspessoais.dao.CartaoDeCreditoDAO;
import com.financaspessoais.model.CartaoDeCredito;
import com.financaspessoais.model.Usuario;
import com.financaspessoais.service.CartaoDeCreditoService;
import com.financaspessoais.util.SessionContext;

public class CartaoDeCreditoServiceImpl implements CartaoDeCreditoService, Serializable {
	private static final long serialVersionUID = 1L;
	
	private CartaoDeCreditoDAO cartaoDeCreditoDAO;

	@Override
	public boolean criarOuAtualizar(CartaoDeCredito cartao) {
		Usuario u = SessionContext.getInstance().getUsuarioLogado();
		cartao.setProprietario(u);

		boolean retorno;
		CartaoDeCredito cartaoBD = null;

		cartaoBD = this.getCartaoDeCreditoDAO().criarOuAtualizar(cartao);
		if (cartaoBD != null)
			retorno = true;
		else
			retorno = false;

		return retorno;
	}

	@Override
	public void remover(CartaoDeCredito cartao) {
		this.getCartaoDeCreditoDAO().remover(cartao.getId());
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
}
