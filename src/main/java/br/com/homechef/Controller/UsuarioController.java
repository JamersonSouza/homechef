package br.com.homechef.Controller;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.homechef.DAO.UsuarioDAO;
import br.com.homechef.model.Usuario;

@Controller
public class UsuarioController {
	
	@Autowired
	private UsuarioDAO usuarioDao;
	
	//Pagina de Cadastro de um usuario normal - View
	@GetMapping("/cadastro")
	public ModelAndView formCadastro() {
		ModelAndView mv = new ModelAndView("cadastro");
		mv.addObject("usuario", new Usuario());
		return mv;
	}
	
	@PostMapping("/addUsuario")
	public ModelAndView addUsuario(@Valid @ModelAttribute Usuario usuario, Errors errors) {
		
		ModelAndView mv = new ModelAndView("cadastro");
		mv.addObject("usuario", usuario);
		if(errors.hasErrors()) {
			return mv;
		}
		usuarioDao.save(usuario);
		System.out.println(usuario);
		mv.addObject("usuario", new Usuario());
		mv.addObject("mensagem", "Inserido com sucesso");
		return mv;
		
	}

}
