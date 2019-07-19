package br.com.homechef.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.homechef.DAO.CardapioDAO;
import br.com.homechef.model.Cardapio;

@Controller
public class CardapioController {
	
	
	@Autowired
	private CardapioDAO cardapioDAO;
	
	@Autowired
	private CardapioService cardapioService;
	
	
	@GetMapping("/CadastroCardapio")
	public ModelAndView CadastroCardapio() {
			ModelAndView mv = new ModelAndView("CadastroCardapio");
			mv.addObject("cardapio", new Cardapio());
			return mv;
	}
	
	
	

}
