package br.com.homechef.Controller;


import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.homechef.DAO.ChefDAO;
import br.com.homechef.DAO.ComplementoUsuarioDao;
import br.com.homechef.DAO.FavoritarDAO;
import br.com.homechef.DAO.UsuarioDAO;
import br.com.homechef.model.Chef;
import br.com.homechef.model.Favoritar;
import br.com.homechef.model.Usuario;
import br.com.homechef.model.UsuarioComplemento;
import br.com.homechef.service.EmailService;

@Controller
public class UsuarioController {
	
	@Autowired
	private UsuarioDAO usuarioDao;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private ComplementoUsuarioDao usercomplemento;
	
	@Autowired
	private ChefDAO chef;
	
	@Autowired
	EmailService emailService;
	
	@Autowired
	JavaMailSender emailSender;
	
	@Autowired
	private FavoritarDAO favoritarDAO;
	
	//Pagina de Cadastro de um usuario normal - View
	@GetMapping("/cadastro")
	public ModelAndView formCadastro() {
		ModelAndView mv = new ModelAndView("cadastro");
		mv.addObject("usuario", new Usuario());
		return mv;
	}
//====================== METODO PARA INSERIR UM USUARIO NO BANCO ==================================
	@PostMapping("/addUsuario")
		public ModelAndView addUser(@Valid @ModelAttribute Usuario user, Errors erros, RedirectAttributes rt, HttpSession session,HttpServletRequest request,@RequestParam("file") MultipartFile imagem) {
			ModelAndView mv = new ModelAndView("cadastro");
			mv.addObject("usuario", user);
			if(erros.hasErrors()) {
				return mv;
			}else {
				
			try {
				if (Util.fazerUploadImagem(imagem)) {
					user.setImagem(imagem.getOriginalFilename());
					}
				usuarioService.salvarUsuario(user);
				request.setAttribute("usuario", new Usuario());
				rt.addFlashAttribute("mensagem", "Usuario salvo");
				mv.addObject("mensagem", "usuario salvo");
				return mv;
			} catch (Exception e) {
					rt.addFlashAttribute("mensagemErro", e.getMessage());
					mv.addObject("mensagemErro", "erro");
					return mv;
			}
			
		}
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
	
	@GetMapping("alterarcadastro")
	public ModelAndView altCadastastro(@ModelAttribute Usuario userEdit) {
		ModelAndView mv = new ModelAndView("alterarcadastro");
		mv.addObject("userEdit", userEdit);
		mv.addObject("userEdit", new Usuario());
		return mv;
	}
	
	@GetMapping("/edituser")
	public ModelAndView editar(@RequestParam Integer idUsuario) {
		ModelAndView mv = new ModelAndView("alterarcadastro");
		mv.addObject("userEdit", usuarioDao.findById(idUsuario));
		usuarioDao.deleteById(idUsuario);
		return mv;
		
	}
//	================= CONFIGURAÇÃO DA CONTA ==============
	@GetMapping("Configuracoes-Conta")
	public ModelAndView configConta() {
		ModelAndView mv = new ModelAndView("UsuarioConfig");
		mv.addObject("listaUsuario", usuarioDao.findAll());
		return mv;
	}
	
//========================= METODO PARA LISTAR USUARIO - OBS PRECISA SER MELHORADO ================================	
	@GetMapping("/usuarioPerfil")
	public ModelAndView perfilUser() {
		ModelAndView mv = new ModelAndView("usuarioPerfil");
		mv.addObject("listaUsuario", usuarioDao.findAll());
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
		mv.addObject("userComplemento", new UsuarioComplemento());
		return mv;
	}
	
	@PostMapping("/salvarComplemento")
	public ModelAndView saveComplemento(@ModelAttribute UsuarioComplemento userComplemento, HttpSession session) {
		ModelAndView mv = new ModelAndView("complemento-perfil");
		mv.addObject("userComplemento", userComplemento);
		mv.addObject("mensagem", "salvo com sucesso");
		System.out.println(userComplemento);
		userComplemento.setUsuario((Usuario) session.getAttribute("usuariologado"));
		usercomplemento.save(userComplemento);
		return mv;
	}
	
//	============================= METODO PARA LOGAR COM O GOOGLE - PRECISA MELHORAR URGENTE ====================
	
	@RequestMapping(value = "/loginGoogle", method = RequestMethod.POST)
	@ResponseBody
	public String loginGoogle(@RequestParam String email, @RequestParam String nome,HttpSession session) {
		
		ModelAndView mv = new ModelAndView("redirect:index");
		Usuario usuario = usuarioDao.findByEmail(email);
		if(usuario!=null) {
			session.setAttribute("usuariologado", usuario);
			return "index";
		}else {
			usuario = new Usuario();
			usuario.setNome(nome);
			usuario.setEmail(email);
			usuarioDao.save(usuario);
			usuario = usuarioDao.findByEmail(email);
			session.setAttribute("usuariologado", usuario);
			return "index";
		}
		
		
		
	}
	
//	==============================EXCLUIR CONTA USUARIO ======================================
	@GetMapping("/contaExcluida")
	public String conta() {
		return "contaExcluida";
	}
	
	@GetMapping("/ExcluirUsuario")
	public ModelAndView excluirConta(@RequestParam Integer idUsuario) {
		ModelAndView mv = new ModelAndView("redirect:contaExcluida");
		usuarioDao.deleteById(idUsuario);
		return mv;
	}
	
	
//	================================================================
	@GetMapping("recuperarUsuario")
	public ModelAndView viewrece(@ModelAttribute Usuario usuario) {
		ModelAndView mv = new ModelAndView("recuperarUsuario");
		mv.addObject("usuario", new Usuario());
		mv.addObject("usuario", usuario);
		mv.addObject("err", "Email não Encontrado em nossa Base de Dados");
		return mv;
	}

	@PostMapping("RecuperarSenhaUsuario")
	public ModelAndView viewrecovery(Usuario usuario) throws Exception {
		ModelAndView mva = new ModelAndView("EmailNaoEncontrado");
		if (this.usuarioDao.findByEmail(usuario.getEmail()) != null) {
			ModelAndView mv = new ModelAndView("EmailEnviado");
			SimpleMailMessage mail = new SimpleMailMessage();
			mail.setFrom("homechefjaboatao@gmail.com");
			mail.setSubject("RECUPERAÇÃO DE CONTA - HOMECHEF");
			mail.setTo(usuario.getEmail());
			usuario.setEmail(usuario.getEmail());
			usuario.setSenha(Util.md5("1fp3"));
			usuarioDao.save(usuario);
			System.out.println("email enviado" + usuario.getDestinatario());
			mail.setText("A sua senha é " + " 1fp3");
			System.out.println(usuario.getSenha());
			emailSender.send(mail);

			return mv;

		}

		return mva;
	}
	
//	================== NOVO FAVORITAR CHEF =================
	
	@GetMapping("ChefsFavoritos")
	public ModelAndView cheffavoritos(Favoritar chef, HttpSession session) {
		ModelAndView mv = new ModelAndView("ChefsFavoritos");
		chef.setUser((Usuario) session.getAttribute("usuariologado"));
		mv.addObject("chefLista", this.favoritarDAO.findAll());
		return mv;
	}
	
	@PostMapping("/FavoritarChef")
	public ModelAndView favoritar(Favoritar chef, HttpSession session, Chef nome) {
		ModelAndView mv = new ModelAndView("ContratarChef");
		chef.setUser((Usuario) session.getAttribute("usuariologado"));
		Chef cheff = this.chef.findBynome();
		chef.setChef(cheff);
		this.favoritarDAO.save(chef);
		return mv;
	}
	
	
}
