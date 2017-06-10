package com.financaspessoais.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.financaspessoais.model.Estabelecimento;
import com.financaspessoais.service.EstabelecimentoService;
import com.financaspessoais.service.impl.EstabelecimentoServiceImpl;

@FacesConverter(forClass = Estabelecimento.class)
public class EstabelecimentoConverter implements Converter {

	private EstabelecimentoService estabelecimentoService;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Estabelecimento retorno = null;
		if (value != null)
			retorno = this.getEstabelecimentoService().buscar(new Long(value));
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Estabelecimento estabelecimento = ((Estabelecimento) value);
			return estabelecimento.getId() == null ? null : estabelecimento.getId().toString();
		}
		return null;
	}

	private EstabelecimentoService getEstabelecimentoService() {
		if (this.estabelecimentoService == null)
			this.estabelecimentoService = new EstabelecimentoServiceImpl();
		return estabelecimentoService;
	}
}
