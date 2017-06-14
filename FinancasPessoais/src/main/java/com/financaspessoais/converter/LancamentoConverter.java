package com.financaspessoais.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.financaspessoais.model.Lancamento;
import com.financaspessoais.service.LancamentoService;
import com.financaspessoais.service.impl.LancamentoServiceImpl;

@FacesConverter(forClass = Lancamento.class)
public class LancamentoConverter implements Converter {

	private LancamentoService lancamentoService;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Lancamento retorno = null;
		if (value != null)
			retorno = this.getLancamentoService().buscar(new Long(value));
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Lancamento lancamento = ((Lancamento) value);
			return lancamento.getCodigoLancamento() == null ? null : lancamento.getCodigoLancamento().toString();
		}
		return null;
	}

	private LancamentoService getLancamentoService() {
		if (this.lancamentoService == null)
			this.lancamentoService = new LancamentoServiceImpl();
		return lancamentoService;
	}
}
