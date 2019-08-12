package br.com.homechef.Controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.homechef.model.Chef_favorito;
import br.com.homechef.service.Chef_favoritosService;

@Controller
public class Chef_favoritosController {
	
	@Autowired
	private Chef_favoritosService chef_favoritosService;
	
	@Autowired
	private ChefService chefservice;
	
	//metodo listar chef favorito do usuario
		@GetMapping("/lista_chefes_favoritos")
			public ModelAndView listaChefFavoritos() {
				ModelAndView mv = new ModelAndView("lista_chefes_favoritos");
			mv.addObject("listaChefFav", this.chef_favoritosService.findAll());
				return mv;
			}


		//salvar no bd o que foi favoritado
		@PostMapping("/favoritarChef")
		public ModelAndView favoritar(@Valid Chef_favorito chef_favorito, BindingResult br) {
			ModelAndView mv = new ModelAndView ("ContratarChef");
			//mv.addObject("listachef", chefservice.listarTodos() );
			if(br.hasErrors()) {
				return mv;
			}
			this.chef_favoritosService.salvarChef_favorito(chef_favorito);
			return mv;
			}
}
