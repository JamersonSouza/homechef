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
	
	//Pagina de Login - View
	@GetMapping("/login")
	public ModelAndView userLogin() {
		ModelAndView mv = new ModelAndView("login");
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
	
	@GetMapping("/encontrar_chef")
	public ModelAndView buscaChef() {
		ModelAndView mv = new ModelAndView("encontrar_chef");
		return mv;
	}

}
