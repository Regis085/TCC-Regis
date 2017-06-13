package com.financaspessoais.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.financaspessoais.model.Conta;
import com.financaspessoais.service.ContaService;
import com.financaspessoais.service.impl.ContaServiceImpl;

@FacesConverter(forClass = Conta.class)
public class ContaConverter implements Converter{

	private ContaService contaService;
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Conta retorno = null;
		if (value != null)
			retorno = this.getContaService().buscar(new Integer(value));
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Conta conta = ((Conta) value);
			return conta.getId() == null ? null : conta.getId().toString();
		}
		return null;
	}
	
	private ContaService getContaService() {
		if (this.contaService == null)
			this.contaService = new ContaServiceImpl();
		return contaService;
	}
}
