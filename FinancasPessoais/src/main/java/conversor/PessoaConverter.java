package conversor;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.persistence.EntityManager;

import repositorio.Pessoas;
import teste.Pessoa2;
import utils.JpaUtil;

@FacesConverter(forClass = Pessoa2.class)
public class PessoaConverter implements Converter {
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Pessoa2 retorno = null;
		EntityManager manager = JpaUtil.getEntityManager();
		try {
			if (value != null) {
				Pessoas pessoas = new Pessoas(manager);
				retorno = pessoas.porId(new Long(value));
			}
			return retorno;
		} finally {
			manager.close();
		}
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			return ((Pessoa2) value).getId().toString();
		}
		return null;
	}
}