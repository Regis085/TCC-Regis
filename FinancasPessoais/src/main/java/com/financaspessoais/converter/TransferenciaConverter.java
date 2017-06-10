package com.financaspessoais.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.financaspessoais.model.Transferencia;
import com.financaspessoais.service.TransferenciaService;
import com.financaspessoais.service.impl.TransferenciaServiceImpl;

@FacesConverter(forClass = Transferencia.class)
public class TransferenciaConverter implements Converter {

	private TransferenciaService transferenciaService;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Transferencia retorno = null;
		if (value != null)
			retorno = this.getTransferenciaService().buscar(new Long(value));
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Transferencia transferencia = ((Transferencia) value);
			return transferencia.getId() == null ? null : transferencia.getId().toString();
		}
		return null;
	}

	private TransferenciaService getTransferenciaService() {
		if (this.transferenciaService == null)
			this.transferenciaService = new TransferenciaServiceImpl();
		return transferenciaService;
	}
}
