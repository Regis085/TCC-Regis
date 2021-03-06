package com.financaspessoais.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.financaspessoais.model.Banco;
import com.financaspessoais.service.BancoService;
import com.financaspessoais.service.impl.BancoServiceImpl;

@FacesConverter(forClass = Banco.class)
public class BancoConverter implements Converter {

	private BancoService bancoService;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Banco retorno = null;
		if (value != null)
			retorno = this.getBancoService().buscar(new Short(value));
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Banco banco = ((Banco) value);
			return banco.getId() == null ? null : banco.getId().toString();
		}
		return null;
	}

	private BancoService getBancoService() {
		if (this.bancoService == null)
			this.bancoService = new BancoServiceImpl();
		return bancoService;
	}
}
