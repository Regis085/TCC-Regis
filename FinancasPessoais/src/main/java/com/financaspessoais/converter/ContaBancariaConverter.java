package com.financaspessoais.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.financaspessoais.model.Conta;
import com.financaspessoais.model.ContaBancaria;
import com.financaspessoais.service.ContaBancariaService;
import com.financaspessoais.service.impl.ContaBancariaServiceImpl;

@FacesConverter(forClass = ContaBancaria.class)
public class ContaBancariaConverter implements Converter {

	private ContaBancariaService contaBancariaService;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Conta retorno = null;
		if (value != null)
			retorno = this.getContaBancariaService().buscar(new Integer(value));
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			ContaBancaria contaBancaria = ((ContaBancaria) value);
			return contaBancaria.getId() == null ? null : contaBancaria.getId().toString();
		}
		return null;
	}
	
	private ContaBancariaService getContaBancariaService() {
		if (this.contaBancariaService == null)
			this.contaBancariaService = new ContaBancariaServiceImpl();
		return contaBancariaService;
	}
}
