package com.financaspessoais.service;

import com.financaspessoais.model.Usuario;

public interface UsuarioService {
	boolean criar(Usuario usuario);

	Usuario buscarPorLoginESenha(String login, String senha);

	Usuario buscar(Short id);
	
	void remover(Usuario usuario);
}
