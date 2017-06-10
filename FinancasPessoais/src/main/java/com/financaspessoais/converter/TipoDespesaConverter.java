package com.financaspessoais.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.financaspessoais.model.TipoDespesa;
import com.financaspessoais.service.TipoDespesaService;
import com.financaspessoais.service.impl.TipoDespesaServiceImpl;

@FacesConverter(forClass = TipoDespesa.class)
public class TipoDespesaConverter implements Converter {

	private TipoDespesaService tipoDespesaService;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		TipoDespesa retorno = null;
		if (value != null)
			retorno = getTipoDespesaService().buscar(new Short(value));
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			TipoDespesa tipoDespesa = ((TipoDespesa) value);
			return tipoDespesa.getId() == null ? null : tipoDespesa.getId().toString();
		}
		return null;
	}

	private TipoDespesaService getTipoDespesaService() {
		if (this.tipoDespesaService == null)
			this.tipoDespesaService = new TipoDespesaServiceImpl();
		return tipoDespesaService;
	}
}
