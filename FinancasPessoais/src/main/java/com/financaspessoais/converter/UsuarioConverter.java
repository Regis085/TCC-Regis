package com.financaspessoais.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.financaspessoais.model.Usuario;
import com.financaspessoais.service.UsuarioService;
import com.financaspessoais.service.impl.UsuarioServiceImpl;

@FacesConverter(forClass = Usuario.class)
public class UsuarioConverter implements Converter {

	private UsuarioService usuarioService;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Usuario retorno = null;
		if (value != null)
			retorno = this.getUsuarioService().buscar(new Short(value));
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Usuario usuario = ((Usuario) value);
			return usuario.getId() == null ? null : usuario.getId().toString();
		}
		return null;
	}

	private UsuarioService getUsuarioService() {
		if (this.usuarioService == null)
			this.usuarioService = new UsuarioServiceImpl();
		return usuarioService;
	}
}
