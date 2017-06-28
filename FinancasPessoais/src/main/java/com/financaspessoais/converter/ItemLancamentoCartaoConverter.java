package com.financaspessoais.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.financaspessoais.model.ItemLancamentoCartao;
import com.financaspessoais.service.ItemLancamentoCartaoService;
import com.financaspessoais.service.impl.ItemLancamentoCartaoServiceImpl;

@FacesConverter(forClass = ItemLancamentoCartao.class)
public class ItemLancamentoCartaoConverter implements Converter {

	private ItemLancamentoCartaoService itemLancamentoCartaoService;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		
		ItemLancamentoCartao retorno = null;
		if (value != null) {
			String[] chaves = value.split("#");
			Short codigoCartaoDeCredito = new Short(chaves[0]);
			Long codigoLancamentoCartao = new Long(chaves[1]);
			Long codigoItemLancamentoCartao = new Long(chaves[2]);
			retorno = this.getItemLancamentoCartaoService().buscar(codigoCartaoDeCredito, codigoLancamentoCartao, codigoItemLancamentoCartao);
		}
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		String retorno = null;
		if (value != null) {
			ItemLancamentoCartao itemLancamentoCartao = ((ItemLancamentoCartao) value);
			retorno = itemLancamentoCartao.getNome();
		}
		return retorno;	
	}

	private ItemLancamentoCartaoService getItemLancamentoCartaoService() {
		if (this.itemLancamentoCartaoService == null)
			this.itemLancamentoCartaoService = new ItemLancamentoCartaoServiceImpl();
		return itemLancamentoCartaoService;
	}
}
