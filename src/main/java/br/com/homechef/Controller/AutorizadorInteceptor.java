package br.com.homechef.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

import br.com.homechef.model.Chef;
import br.com.homechef.model.Usuario;

public class AutorizadorInteceptor implements HandlerInterceptor {
	
	private static final boolean CONTROLAR_ACESSO = true;
	
	private static final String[] RECURSOS_LIVRES = { "/", "/addChef", "/addUsuario", "/index", "/login", "/loginchef", "/acesso-negado", "/cadastro", "/recuperarUsuario", "/RecuperarSenhaUsuario","/Escolher_Cadastro", "/loginchef", "/contato", "/cadastroChef"};
	
	private final String[] PAGINAS_ESTATICAS = {"/css/", "/imagens/", "/js", "/photos"};
	
	private static final String PAGINA_ACESSO_NEGADO = "/acesso-negado";

	private final String[] PAGINAS_LOGADO_USUARIO = {"/", "/index", "/editUser", "/usuarioPerfil", 
			 "/salvarComplemento", "/contaExcluida", "/ExcluirUsuario", "/FavoritarChef",
			 "/ChefsFavoritos", 
			 PAGINA_ACESSO_NEGADO};
	
	private final String[] PAGINAS_LOGADO_CHEF = {"/", "/index", "/addCardapio", "/ExcluirConta", 
			 "/EditCardapio", "/CardapioEdit", "/removerCardapio", "/fotosGaleria", "GaleriaChef",
			 "/ChefsFavoritos", 
			 PAGINA_ACESSO_NEGADO};
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
	throws Exception {
		System.out.println(" >>> INFO:: Interceptor antes da chamada <<< ");
		
		String pagRequisitada = request.getServletPath();
		Usuario usuarioLogado = (Usuario) request.getSession().getAttribute("usuariologado");
		Chef chefLogado = (Chef) request.getSession().getAttribute("chefLogado");
		boolean estaLogado = usuarioLogado != null ? true : false;
		boolean ChefEstaLogado = chefLogado != null ? true : false;
	
		if(!CONTROLAR_ACESSO) {
		return true;
	}
		if (pagRequisitada.contains("/perfilChef") || pagRequisitada.contains("ExcluirConta")) {
			if (ChefEstaLogado) {
					return true;
				} else {
					request.getRequestDispatcher(PAGINA_ACESSO_NEGADO).forward(request, response);
					return false;
				}
		}
		
		if (pagRequisitada.contains("/Cardapio") || pagRequisitada.contains("exclusaoConta")) {
			if (ChefEstaLogado) {
					return true;
				} else {
					request.getRequestDispatcher(PAGINA_ACESSO_NEGADO).forward(request, response);
					return false;
				}
		}
		
		if (pagRequisitada.contains("/alterarcadastro") || pagRequisitada.contains("usuarioPerfil") || 
				pagRequisitada.contains("complemento-perfil") || pagRequisitada.contains("contaExcluida")
				|| pagRequisitada.contains("ChefsFavoritos") ) {
			if (estaLogado) {
					return true;
				} else {
					request.getRequestDispatcher(PAGINA_ACESSO_NEGADO).forward(request, response);
					return false;
				}
		}


		
	for (String recurso : RECURSOS_LIVRES) {
		if (request.getRequestURL().toString().endsWith(recurso)) {
			return true;
		}
	}
	
	for (String paginaEstatica : PAGINAS_ESTATICAS) {
		if (pagRequisitada.contains(paginaEstatica)) {
			//System.out.println("Permitido (estática): "+urlRequisitada);
			return true;
		}
	}
	
	if (request.getSession().getAttribute("usuariologado") == null && request.getSession().getAttribute("chefLogado") == null) {
		request.getRequestDispatcher(PAGINA_ACESSO_NEGADO).forward(request, response);
		return false;
	} else {
		return true;
	}
}

}
