package com.financaspessoais.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.financaspessoais.model.Receita;
import com.financaspessoais.service.ReceitaService;
import com.financaspessoais.service.impl.ReceitaServiceImpl;

@FacesConverter(forClass = Receita.class)
public class LancamentoCartaoConverter implements Converter {

	private ReceitaService receitaService;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Receita retorno = null;
		if (value != null)
			retorno = this.getReceitaService().buscar(new Long(value));
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Receita receita = ((Receita) value);
			return receita.getId() == null ? null : receita.getId().toString();
		}
		return null;
	}

	private ReceitaService getReceitaService() {
		if (this.receitaService == null)
			this.receitaService = new ReceitaServiceImpl();
		return receitaService;
	}
}
