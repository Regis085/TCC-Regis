package com.financaspessoais.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.financaspessoais.model.TipoReceita;
import com.financaspessoais.service.TipoReceitaService;
import com.financaspessoais.service.impl.TipoReceitaServiceImpl;

@FacesConverter(forClass = TipoReceita.class)
public class TipoReceitaConverter implements Converter{

	private TipoReceitaService tipoReceitaService;
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		TipoReceita retorno = null;
		if (value != null)
			retorno = getTipoReceitaService().buscar(new Short(value));
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			TipoReceita tipoReceita = ((TipoReceita) value);
			return tipoReceita.getId() == null ? null : tipoReceita.getId().toString();
		}
		return null;
	}
	
	private TipoReceitaService getTipoReceitaService() {
		if (this.tipoReceitaService == null)
			this.tipoReceitaService = new TipoReceitaServiceImpl();
		return tipoReceitaService;
	}
}
