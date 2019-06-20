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
	
	//Pagina de Escolher qual o tipo do cadastro - View
	@GetMapping("/Escolher_Cadastro")
	public ModelAndView tipoCadastro() {
		ModelAndView mv = new ModelAndView("Escolher_Cadastro");
		return mv;
	}
	
	//Pagina de Cadastro de um usuario normal - View
	@GetMapping("/form_cadastro")
	public ModelAndView formCadastro() {
		ModelAndView mv = new ModelAndView("form_cadastro");
		return mv;
	}
	
	//Pagina de Cadastro de um Chef - View
	@GetMapping("/form_cadastro_chef")
	public ModelAndView formCadastroChef() {
		ModelAndView mv = new ModelAndView("form_cadastro_chef");
		return mv;
	}
	
	//Pagina do perfil do chef após ele Finalizar o Cadastro! - View.
	@GetMapping("perfil_chefe")
	public ModelAndView perfilChefe() {
		ModelAndView mv = new ModelAndView("perfil_chefe");
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
