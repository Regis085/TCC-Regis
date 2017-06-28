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
		if (value != null) {
			String[] chaves = value.split("#");
			Short codigoCartaoDeCredito = new Short(chaves[0]);
			Short ano = new Short(chaves[1]);
			Short mes = new Short(chaves[2]);
			retorno = this.getFaturaCartaoService().buscar(codigoCartaoDeCredito, ano, mes);
		}
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		String retorno = null;
		if (value != null) {
			FaturaCartao faturaCartao = ((FaturaCartao) value);
			retorno = faturaCartao.getChaveComposta();
		}
		return retorno;
	}

	private FaturaCartaoService getFaturaCartaoService() {
		if (this.faturaCartaoService == null)
			this.faturaCartaoService = new FaturaCartaoServiceImpl();
		return faturaCartaoService;
	}
}
