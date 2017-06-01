package com.financaspessoais.service;

import com.financaspessoais.model.Usuario;

public interface UsuarioService {
	Usuario getUsuario(String login, String senha);
	boolean inserirUsuario(Usuario usuario);
	public boolean deletarUsuario(Usuario usuario);
}
