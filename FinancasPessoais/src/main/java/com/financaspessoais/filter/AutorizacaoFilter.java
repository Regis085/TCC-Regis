package com.financaspessoais.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.financaspessoais.model.Usuario;

@WebFilter("*.xhtml")
public class AutorizacaoFilter implements Filter {
	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletResponse response = (HttpServletResponse) res;
		HttpServletRequest request = (HttpServletRequest) req;
		
		HttpSession session = request.getSession();
		Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
		boolean isNotLogado = usuario == null;
		
		if (request.getRequestURI().contains("/pages")) {
			System.out.println("Em página : " + request.getRequestURI());
			if (isNotLogado)
				System.out.println("Não há usuário logado");
			else
				System.out.println("Usuário logado: " + usuario);
		}
		
		if (isNotLogado && !request.getRequestURI().endsWith("/pages/login.xhtml")
				&& !request.getRequestURI().endsWith("/pages/cadastro-usuario.xhtml")
				&& !request.getRequestURI().contains("/javax.faces.resource/")) {
			
			System.out.println("Acesso não autorizado, não há usuário logado na sessão.");
			response.sendRedirect(request.getContextPath() + "/pages/login.xhtml");
		}
		else {
			chain.doFilter(req, res);
		}
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
	}

	@Override
	public void destroy() {
	}
}