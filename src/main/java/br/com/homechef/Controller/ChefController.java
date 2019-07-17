package br.com.homechef.Controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.homechef.DAO.ChefDAO;
import br.com.homechef.excecoes.ServiceException;
import br.com.homechef.model.Chef;

@Controller
public class ChefController {

	@Autowired
	private ChefDAO chefDAO;
	
	@Autowired
	private ChefService chefservice;
	
	@GetMapping("/cadastroChef")
	public ModelAndView cadastroChef() {
		ModelAndView mv = new ModelAndView("cadastroChef");
		mv.addObject("chef", new Chef());
		return mv;
	}
	
	@PostMapping("/addChef")
	public ModelAndView addUser(@Valid @ModelAttribute Chef chef, Errors erros, RedirectAttributes rt) {
		ModelAndView mv = new ModelAndView("cadastroChef");
		mv.addObject("chef", chef);
		if(erros.hasErrors()) {
			return mv;
		}else {
			
		try {
			chefservice.salvarChef(chef);
			rt.addFlashAttribute("mensagem", "chef salvo");
			mv.addObject("mensagem", "chef salvo");
			return mv;
		} catch (Exception e) {
				rt.addFlashAttribute("mensagemErro", e.getMessage());
				mv.addObject("mensagemErro", "erro");
				return mv;
		}
		
	}
}
	@GetMapping("loginchef")
	public ModelAndView view(Chef chef, Errors erros) {
		ModelAndView mv = new ModelAndView("loginchef");
		mv.addObject("chef", new Chef());
		mv.addObject("chef", chef);
		if(erros.hasErrors()) {
			return mv;
		}
		return mv;
	}
	
	@PostMapping("/logar")
	public String logar(@Valid Chef chef, RedirectAttributes rt, Errors errors,HttpSession session) {
		Chef chefLogado;
		try {
			chefLogado = this.chefservice.efetuarLogin(chef.getEmail(), chef.getSenha());
			session.setAttribute("chefLogado", chef);
			return "redirect:/perfil-chef";
			
		} catch (ServiceException e) {
			rt.addFlashAttribute("mensagemErro", e.getMessage());
			return "redirect:/loginchef";
		}
		
	}
	
	@GetMapping("perfil-chef")
	public ModelAndView PerfilChef() {
		ModelAndView mv = new ModelAndView("perfil-chef");
		mv.addObject("lista", chefservice.listarTodos());
		return mv;
	}
	//metodo complemento de perfil
	@GetMapping("complemento-perfil-chef")
	public ModelAndView complementoPerfilChef() {
	ModelAndView mv = new ModelAndView("complemento-perfil-chef");
	mv.addObject("chefComplemento", new Chef());
		return mv;
	}
	//@PostMapping("/salvarComplementoChef")
	//public ModelAndView saveComplementoChef(@ModelAttribute Chef chefComplemento) {
		//ModelAndView mv = new ModelAndView("complemento-perfil-chef");
			//mv.addObject("chefComplemento", chefComplemento);
			//mv.addObject("mensagem", "salvo com sucesso");
			//System.out.println(chefComplemento);
			//chefComplemento.salvarChef(chefComplemento);
			//return mv;
		//}
	
}
