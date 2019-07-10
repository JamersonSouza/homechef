package br.com.homechef.Controller;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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

	
	@GetMapping("/editarPerfil")
	public ModelAndView editar(@Valid @RequestParam Integer idUsuario) {
		ModelAndView mv = new ModelAndView("alterarcadastro");
		mv.addObject("usuario", usuarioDao.findById(idUsuario));
		usuarioDao.deleteById(idUsuario);
		return mv;
		
	}
	
	@GetMapping("/usuarioPerfil")
	public ModelAndView perfilUser() {
		ModelAndView mv = new ModelAndView("usuarioPerfil");
		mv.addObject("listaUsuario", usuarioDao.findAll(Sort.by("nome")));
		return mv;
	}
	
	
	public ModelAndView listar() {
	ModelAndView mv = new ModelAndView();
		mv.addObject("listaUsuario", usuarioDao.findAll(Sort.by("nome")));
		mv.addObject("usuario", new Usuario());
		return mv;
	}
	
	@RequestMapping(value = "/loginGoogle", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView loginGoogle(@RequestParam String email, @RequestParam String nome) {
		
		ModelAndView mv = new ModelAndView("cadastro");
		if(email.getEmail() != null && email.getNome() != null) {
		usuarioDao.findByNomeEmailAprox(nome, email);
		}
		return "redirect:/index";
		
	}
	
}
