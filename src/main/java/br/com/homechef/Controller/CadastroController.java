package br.com.homechef.Controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;


import br.com.homechef.DAO.CadastroChefDAO;
import br.com.homechef.DAO.UsuarioDAO;
import br.com.homechef.model.CadastroChef;
import br.com.homechef.model.Usuario;

@Controller
public class CadastroController {
	
	@Autowired
	private CadastroChefDAO cadastrochefDAO;
	@Autowired
	private UsuarioDAO cadastrousuarioDAO;

	
	
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
		
		
		//Pagina de Cadastro de um Chef - View
		@GetMapping("/form_cadastro_chef")
		public ModelAndView formCadastroChef() {
			ModelAndView mv = new ModelAndView("form_cadastro_chef");
			mv.addObject("cadastrochef", new CadastroChef());
			return mv;
		}
		//metodo para realizar a conta do chef + validação
		@PostMapping("/addChef")
		public ModelAndView inserirChef(@ModelAttribute CadastroChef cadastrochef, Errors errors) {
				ModelAndView mv = new ModelAndView("form_cadastro_chef");
				if(errors.hasErrors()) {
					
					return mv;
				}
				cadastrochefDAO.save(cadastrochef);
				System.out.println(cadastrochef);
				mv.addObject("cadastrochef", new CadastroChef());
				mv.addObject("mensagem", "Chef Cadastrado");
				return mv;
		}
		
		
		@GetMapping("/login")
		public String login(Usuario usuario, Model model) {
			model.addAttribute("usuario", new Usuario());
			return "login";
		}
		
		@PostMapping("/login")
		public String efetuarLogin(@ModelAttribute("usuario") Usuario usuario, BindingResult br, Model model, HttpSession sessao) {
			if(br.hasErrors()) {
				System.out.println("Resultado: " + br);
			}
			
			
			 Usuario usuarioCosultado = cadastrousuarioDAO.buscalogin(usuario.getEmail(), usuario.getSenha());
			
			if(usuarioCosultado == null) {
				model.addAttribute("mensagem", "Usuario e senha invalido");
			}else {
				sessao.setAttribute("usuariologado", usuarioCosultado);
				return "usuario_logado_index";

			}
			
			return null;

		}		
		
	
}
