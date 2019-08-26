package br.com.homechef.Controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.homechef.DAO.ContatoDAO;
import br.com.homechef.model.Contato;


@Controller
public class ContatoController {
	
	@Autowired
	private ContatoDAO contatoRep;
	
	@GetMapping("/contato")
	 public ModelAndView aadcontato() {
		ModelAndView mv = new ModelAndView("contato");
		mv.addObject("contato", new Contato());	
		return mv;
		}


	//metodo para salvar mensagem no banco e tratar erros
	@PostMapping("/contato")
	public ModelAndView addContato(@Valid @ModelAttribute Contato contato, Errors errors) {
		ModelAndView mv= new ModelAndView("contato");
		if(errors.hasErrors()) {
			return mv;
		}
		this.contatoRep.save(contato);
		System.out.println(contato);
		mv.addObject("contato", new Contato());
		mv.addObject("mensagem", "Mensagem enviada");
		return mv;
		
	}
	
}
