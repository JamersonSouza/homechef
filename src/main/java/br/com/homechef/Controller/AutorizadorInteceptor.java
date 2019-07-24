///* package br.com.homechef.Controller;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.web.servlet.HandlerInterceptor;
//
//public class AutorizadorInteceptor implements HandlerInterceptor {
//	
//	private static final boolean CONTROLAR_ACESSO = true;
//	
//	private static final String[] RECURSOS_LIVRES = { "/", "/addChef", "/addUsuario", "/index", "/login", "/login.html", "/acesso-negado", "/cadastro", "/recuperarSenha", "/Escolher_Cadastro", "/loginchef", "/contato", "/cadastroChef", "/CadasrtoCardapio"};
//	
//	private final String[] PAGINAS_ESTATICAS = {"/css/", "/imagens/"};
//	
//	private static final String PAGINA_ACESSO_NEGADO = "/acesso-negado";
//
//	
//	@Override
//	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
//	throws Exception {
//		System.out.println(" >>> INFO:: Interceptor antes da chamada <<< ");
//		
//		String pagRequisitada = request.getServletPath();
//	
//		if(!CONTROLAR_ACESSO) {
//		return true;
//	}
//	
//	for (String recurso : RECURSOS_LIVRES) {
//		if (request.getRequestURL().toString().endsWith(recurso)) {
//			return true;
//		}
//	}
//	
//	for (String paginaEstatica : PAGINAS_ESTATICAS) {
//		if (pagRequisitada.contains(paginaEstatica)) {
//			//System.out.println("Permitido (estática): "+urlRequisitada);
//			return true;
//		}
//	}
//	
//	if (request.getSession().getAttribute("usuariologado") == null || request.getSession().getAttribute("chefLogado") == null) {
//		request.getRequestDispatcher(PAGINA_ACESSO_NEGADO).forward(request, response);
//		return false;
//	} else {
//		return true;
//	}
//}
//
//}
