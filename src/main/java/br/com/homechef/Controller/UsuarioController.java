package br.com.homechef.Controller;


import javax.servlet.http.HttpSession;
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
//====================== METODO PARA INSERIR UM USUARIO NO BANCO ==================================
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
//==================== FUNCIONALIDADE PARA EDITAR PERFIL =============================
	@PostMapping("/editUser")
	public ModelAndView editUser(@Valid @ModelAttribute Usuario userEdit, Errors errors) {
		
		ModelAndView mv = new ModelAndView("cadastroEdit");
		mv.addObject("userEdit", userEdit);
		if(errors.hasErrors()) {
			return mv;
		}
		usuarioDao.save(userEdit);
		System.out.println(userEdit);
		mv.addObject("userEdit", new Usuario());
		mv.addObject("msgUserEdit", "Usuario Editado");
		return mv;
		
	}
	
	
	@GetMapping("/editarPerfil")
	public ModelAndView editar(@Valid @RequestParam Integer idUsuario) {
		ModelAndView mv = new ModelAndView("alterarcadastro");
		mv.addObject("userEdit", usuarioDao.findById(idUsuario));
		usuarioDao.deleteById(idUsuario);
		return mv;
		
	}
	
//========================= METODO PARA LISTAR USUARIO - OBS PRECISA SER MELHORADO ================================	
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
	
//	============================ METODO PARA COMPLEMENTO DO PERFIL ==================================
	@GetMapping("complemento-perfil")
	public ModelAndView complementoPerfil() {
		ModelAndView mv = new ModelAndView("complemento-perfil");
		return mv;
	}
	
//	============================= METODO PARA LOGAR COM O GOOGLE - PRECISA MELHORAR URGENTE ====================
	
	@RequestMapping(value = "/loginGoogle", method = RequestMethod.POST)
	@ResponseBody
	public String loginGoogle(@RequestParam String email, @RequestParam String nome,HttpSession session) {
		
		ModelAndView mv = new ModelAndView("cadastro");
		Usuario usuario = usuarioDao.findByEmail(email);
		if(usuario!=null) {
			session.setAttribute("usuarioLogado", usuario);
			return "usuario_logado_index";
		}else {
			usuario = new Usuario();
			usuario.setNome(nome);
			usuario.setEmail(email);
			usuarioDao.save(usuario);
			usuario = usuarioDao.findByEmail(email);
			session.setAttribute("usuarioLogado", usuario);
			return "usuario_logado_index";
		}
		
		
		
	}
	
}
