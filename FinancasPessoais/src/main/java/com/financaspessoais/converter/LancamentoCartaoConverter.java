package com.financaspessoais.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.financaspessoais.model.LancamentoCartao;
import com.financaspessoais.service.LancamentoCartaoService;
import com.financaspessoais.service.impl.LancamentoCartaoServiceImpl;

@FacesConverter(forClass = LancamentoCartao.class)
public class LancamentoCartaoConverter implements Converter {

	private LancamentoCartaoService lancamentoCartaoService;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		LancamentoCartao retorno = null;
		if (value != null)
			retorno = this.getLancamentoCartaoService().buscar(new Long(value));
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			LancamentoCartao lancamentoCartao = ((LancamentoCartao) value);
			return lancamentoCartao.getId() == null ? null : lancamentoCartao.getId().toString();
		}
		return null;	
	}

	private LancamentoCartaoService getLancamentoCartaoService() {
		if (this.lancamentoCartaoService == null)
			this.lancamentoCartaoService = new LancamentoCartaoServiceImpl();
		return lancamentoCartaoService;
	}
}
