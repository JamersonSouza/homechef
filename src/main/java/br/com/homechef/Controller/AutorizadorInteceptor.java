package br.com.homechef.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

public class AutorizadorInteceptor implements HandlerInterceptor {
	
	private static final boolean CONTROLAR_ACESSO = true;
	
	private static final String[] RECURSOS_LIVRES = {"loginchef"};
	
	private static final String PAGINA_ACESSO_NEGADO = "/acesso-negado";

	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
	throws Exception {
		System.out.println(" >>> INFO:: Interceptor antes da chamada <<< ");
	
	if(!CONTROLAR_ACESSO) {
		return true;
	}
	
	for (String recurso : RECURSOS_LIVRES) {
		if (request.getRequestURL().toString().endsWith(recurso)) {
			return true;
		}
	}
	
	if (request.getSession().getAttribute("chefLogado") == null	) {
		request.getRequestDispatcher(PAGINA_ACESSO_NEGADO).forward(request, response);
		return false;
	} else {
		return true;
	}

}
	
}
