package com.financaspessoais.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.financaspessoais.model.CartaoDeCredito;
import com.financaspessoais.service.CartaoDeCreditoService;
import com.financaspessoais.service.impl.CartaoDeCreditoServiceImpl;

@FacesConverter(forClass = CartaoDeCredito.class)
public class CartaoDeCreditoConverter implements Converter {

	private CartaoDeCreditoService cartaoDeCreditoService;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		CartaoDeCredito retorno = null;
		if (value != null)
			retorno = this.getCartaoDeCreditoService().buscar(new Short(value));
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			CartaoDeCredito CartaoDeCredito = ((CartaoDeCredito) value);
			return CartaoDeCredito.getCodigoCartaoDeCredito() == null ? null : CartaoDeCredito.getCodigoCartaoDeCredito().toString();
		}
		return null;
	}

	private CartaoDeCreditoService getCartaoDeCreditoService() {
		if (this.cartaoDeCreditoService == null)
			this.cartaoDeCreditoService = new CartaoDeCreditoServiceImpl();
		return cartaoDeCreditoService;
	}
}
