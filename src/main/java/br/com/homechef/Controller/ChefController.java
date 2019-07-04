package br.com.homechef.Controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.homechef.DAO.ChefDAO;
import br.com.homechef.model.Chef;

@Controller
public class ChefController {

	@Autowired
	private ChefDAO chefDAO;
	
	@GetMapping("/cadastroChef")
	public ModelAndView cadastroChef() {
		ModelAndView mv = new ModelAndView("cadastroChef");
		mv.addObject("chef", new Chef());
		return mv;
	}
	
	@PostMapping("/addChef")
	public ModelAndView addChef(@Valid @ModelAttribute Chef chef, Errors errors) {
		ModelAndView mv = new ModelAndView("cadastroChef");
		mv.addObject("chef", chef);
		if(errors.hasErrors()) {
			return mv;
		}
		
		chefDAO.save(chef);
		System.out.println(chef);
		mv.addObject("mensagem", "chef Cadastrado");
		mv.addObject("chef", new Chef());
		return mv;
	}
	
}
