package br.com.homechef.Controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.homechef.DAO.GrandeEventoDAO;
import br.com.homechef.model.GrandesEventos;

@Controller
public class GrandeEventoController {
	
	@Autowired
	private GrandeEventoDAO grandeseventos;
	
	//Pagina Grandes Eventos
	@GetMapping("/GrandesEventos")
	public ModelAndView viewEvento(GrandesEventos grandeEvento) {
		ModelAndView mv = new ModelAndView("GrandesEventos");
		mv.addObject("grandeEvento", new GrandesEventos());
		mv.addObject("err", grandeEvento);
		return mv;
	}
	
	//validações ainda não estao funcionando.
	@PostMapping("/solicitacao")
	public ModelAndView enviarSolicitacao(@Valid @ModelAttribute GrandesEventos grandeEvento, Errors br) {
		ModelAndView mv = new ModelAndView("GrandesEventos");
		mv.addObject("grandeEvento", grandeEvento);
		mv.addObject("err", grandeEvento);
		if(br.hasErrors()) {
		return mv;
		}
		System.out.println(grandeEvento);
		grandeseventos.save(grandeEvento);
		mv.addObject("mensagem", "Solicitação enviada com sucesso!");
		return mv;
	}
	//@PostMapping("/solicitacao")
	//public ModelAndView enviarSolicitacao(@Valid @ModelAttribute GrandesEventos grandeEvento, Errors errors) {
		//ModelAndView mv= new ModelAndView("GrandesEventos");
		//if(errors.hasErrors()) {
			//return mv;
		//}
		//this.grandeseventos.save(grandeEvento);
		//System.out.println(grandeEvento);
		//mv.addObject("grandeEvento", grandeEvento);
		//mv.addObject("mensagem", "Solicitação enviada com sucesso!");
		//return mv;
		
	//}

}
