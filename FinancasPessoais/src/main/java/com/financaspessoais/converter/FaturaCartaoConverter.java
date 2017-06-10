package com.financaspessoais.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.financaspessoais.model.FaturaCartao;
import com.financaspessoais.service.FaturaCartaoService;
import com.financaspessoais.service.impl.FaturaCartaoServiceImpl;

@FacesConverter(forClass = FaturaCartao.class)
public class FaturaCartaoConverter implements Converter {

	private FaturaCartaoService faturaCartaoService;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		FaturaCartao retorno = null;
		if (value != null)
			retorno = this.getFaturaCartaoService().buscar(new Long(value));
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			FaturaCartao faturaCartao = ((FaturaCartao) value);
			return faturaCartao.getId() == null ? null : faturaCartao.getId().toString();
		}
		return null;
	}

	private FaturaCartaoService getFaturaCartaoService() {
		if (this.faturaCartaoService == null)
			this.faturaCartaoService = new FaturaCartaoServiceImpl();
		return faturaCartaoService;
	}
}
