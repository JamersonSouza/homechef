package br.com.homechef.Controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;


import br.com.homechef.DAO.CadastroChefDAO;
import br.com.homechef.DAO.CadastroUsuarioDAO;
import br.com.homechef.model.CadastroChef;
import br.com.homechef.model.CadastroUsuario;

@Controller
public class CadastroController {
	
	@Autowired
	private CadastroChefDAO cadastrochefDAO;
	@Autowired
	private CadastroUsuarioDAO cadastrousuarioDAO;

	
	
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
		
		//Pagina de Cadastro de um usuario normal - View
		@GetMapping("/form_cadastro")
		public ModelAndView formCadastro() {
			ModelAndView mv = new ModelAndView("form_cadastro");
			mv.addObject("cadastrousuario", new CadastroUsuario());
			return mv;
		}
		
		 //Metodo para criar conta do usuario normal
		@PostMapping("/addUser")
		public ModelAndView cadastrarUsuario(@ModelAttribute CadastroUsuario cadastrousuario , Errors errors) {
			ModelAndView mv = new ModelAndView("form_cadastro");
			if(errors.hasErrors()) {
				return mv;
			}
			cadastrousuarioDAO.save(cadastrousuario);
			System.out.println(cadastrousuario);
			mv.addObject("cadastrousuario", new CadastroUsuario());
			mv.addObject("mensagem", "Chef Cadastrado");
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
		
		
		
		@PostMapping("/login")
		public ModelAndView efetuarLogin(@ModelAttribute CadastroUsuario usuario, Errors errors) {
				ModelAndView mv = new ModelAndView("login");
				if(errors.hasErrors()) {
					
					return mv;
				}
		
				
			
			
				
				return mv;
		}
		
		
		
		
		
		
		
		
		
	
}
