package br.com.homechef.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.homechef.model.Chef_favoritos;
import br.com.homechef.service.Chef_favoritosService;

@Controller
public class Chef_favoritosController {
	
	@Autowired
	private Chef_favoritosService chef_favoritosService;
	
	//metodo apenas para exibir a view a pagina que possue o botao favoritar
		@GetMapping("/chef_encontrarChef")
		public ModelAndView chefEncontarChef() {
			ModelAndView mv = new ModelAndView("chef_encontrarChef");
			mv.addObject("chef_favoritos", new Chef_favoritos());
			return mv;
		}
	
	//metodo listar chef favorito do usuario
		@GetMapping("/lista_chefes_favoritos")
			public ModelAndView listaChefFavoritos() {
				ModelAndView mv = new ModelAndView("lista_chefes_favoritos");
			mv.addObject("listaChefFav", this.chef_favoritosService.findAll());
				return mv;
			}

}
