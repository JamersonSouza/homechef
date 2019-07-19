package br.com.homechef.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.homechef.DAO.CaradapioDAO;
import br.com.homechef.model.Cardapio;

@Controller
public class CaradapioController {
	
	
	@Autowired
	private CaradapioDAO cardapioDAO;
	
	@Autowired
	private CardapioService cardapioService;
	
	
	@GetMapping("/cadastroCardapio")
	public ModelAndView cadastroCardapio() {
			ModelAndView mv = new ModelAndView("cadastroCardapio");
			mv.addObject("cardapio", new Cardapio());
			return mv;
	}
	
	
	

}
