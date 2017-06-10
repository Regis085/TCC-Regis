package com.financaspessoais.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.financaspessoais.model.Despesa;
import com.financaspessoais.service.DespesaService;
import com.financaspessoais.service.impl.DespesaServiceImpl;

@FacesConverter(forClass = Despesa.class)
public class DespesaConverter implements Converter {

	private DespesaService despesaService;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Despesa retorno = null;
		if (value != null)
			retorno = this.getDespesaService().buscar(new Long(value));
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Despesa despesa = ((Despesa) value);
			return despesa.getId() == null ? null : despesa.getId().toString();
		}
		return null;
	}

	private DespesaService getDespesaService() {
		if (this.despesaService == null)
			this.despesaService = new DespesaServiceImpl();
		return despesaService;
	}
}
