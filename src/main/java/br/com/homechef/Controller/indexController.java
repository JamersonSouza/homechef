package br.com.homechef.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class indexController {
	
	//Pagina Inicial - View
	@GetMapping("/index")
	public ModelAndView pagInicial() {
		ModelAndView mv = new ModelAndView("index");
		return mv;
	}
	//Pagina de um Usuario Normal Logado - View
	@GetMapping("/usuario_logado_index")
	public ModelAndView usuarioLogadoIndex() {
		ModelAndView mv = new ModelAndView("usuario_logado_index");
		return mv;
	}
	
	//Pagina do Perfil do Usuário - View
	@GetMapping("/perfil_usuario")
	public ModelAndView perfilDoUsuario() {
		ModelAndView mv = new ModelAndView("perfil_usuario");
		return mv;
	}
	
	//encontrar chef
	@GetMapping("/encontrar_chef")
	public ModelAndView buscaChef() {
		ModelAndView mv = new ModelAndView("encontrar_chef");
		return mv;
	}

	//sobre nos
	@GetMapping("/sobre_nos")
		public ModelAndView sobreNos() {
			ModelAndView mv = new ModelAndView("sobre_nos");
			return mv;
	}

	//comida baixo preco
			@GetMapping("/lista-pratos-baixoPreco")
				public ModelAndView pratosBaixoPreco() {
					ModelAndView mv = new ModelAndView("lista-pratos-baixoPreco");
					return mv;
			}
		
			
	//comida medio preco
		@GetMapping("/lista-pratos-MedioPreco")
			public ModelAndView pratosMedioPreco() {
				ModelAndView mv = new ModelAndView("lista-pratos-MedioPreco");
				return mv;
					}
					
	//comida alto preco
		@GetMapping("/lista-pratos-AltoPreco")
			public ModelAndView pratosAltoPreco() {
				ModelAndView mv = new ModelAndView("lista-pratos-AltoPreco");
				return mv;
							}
	//comida preco altissimo
		@GetMapping("/lista-pratos-SelectPreco")
			public ModelAndView pratosSelectPreco() {
				ModelAndView mv = new ModelAndView("lista-pratos-SelectPreco");
				return mv;
						}
			
	
	
				
}
