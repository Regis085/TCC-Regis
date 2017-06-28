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
		if (value != null) {
			String[] chaves = value.split("#");
			Short codigoCartaoDeCredito = new Short(chaves[0]);
			Long codigoLancamentoCartao = new Long(chaves[1]);
			retorno = this.getLancamentoCartaoService().buscar(codigoCartaoDeCredito, codigoLancamentoCartao);
		}
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		String retorno = null;
		if (value != null) {
			LancamentoCartao lancamentoCartao = ((LancamentoCartao) value);
//			retorno = lancamentoCartao.getNome();
			retorno = lancamentoCartao.getChaveComposta();
		}
		return retorno;	
	}

	private LancamentoCartaoService getLancamentoCartaoService() {
		if (this.lancamentoCartaoService == null)
			this.lancamentoCartaoService = new LancamentoCartaoServiceImpl();
		return lancamentoCartaoService;
	}
}
