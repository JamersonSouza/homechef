package br.com.homechef.Controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;



@Controller
public class CadastroController {
	
	
	@GetMapping("perfil_chefe")
	public ModelAndView perfilChefe() {
		ModelAndView mv = new ModelAndView("perfil_chefe");
		return mv;
	}

	//Pagina de Escolher qual o tipo do cadastro - View
		@GetMapping("/Escolher_Cadastro")
		public ModelAndView tipoCadastro() {
			ModelAndView mv = new ModelAndView("Escolher_Cadastro");
			return mv;
		}
		

		
	
}
