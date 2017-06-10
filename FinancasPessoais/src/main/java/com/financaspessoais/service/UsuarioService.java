package com.financaspessoais.service;

import com.financaspessoais.model.Usuario;

public interface UsuarioService {
	boolean criar(Usuario usuario);

	Usuario buscarPorLoginESenha(String login, String senha);

	public boolean excluir(Usuario usuario);
}
