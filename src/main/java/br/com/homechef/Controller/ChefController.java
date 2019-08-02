package br.com.homechef.Controller;

import java.io.IOException;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.homechef.DAO.ChefComplementoDAO;
import br.com.homechef.DAO.ChefDAO;
import br.com.homechef.excecoes.ServiceException;
import br.com.homechef.model.Cardapio;
import br.com.homechef.model.Chef;
import br.com.homechef.model.ChefComplemento;
import br.com.homechef.model.Usuario;

@Controller
public class ChefController {

	@Autowired
	private ChefDAO chefDAO;
	
	@Autowired
	private ChefService chefservice;
	
	@Autowired
	private CardapioService cardapioService;
	
	@Autowired
	private ChefComplementoDAO chefcomplemento;
	
	@GetMapping("/cadastroChef")
	public ModelAndView cadastroChef() {
		ModelAndView mv = new ModelAndView("cadastroChef");
		mv.addObject("chef", new Chef());
		return mv;
	}
	
	@PostMapping("/addChef")
	public ModelAndView addUser(@Valid @ModelAttribute Chef chef, Errors erros, RedirectAttributes rt) {
		ModelAndView mv = new ModelAndView("cadastroChef");
		mv.addObject("chef", chef);
		if(erros.hasErrors()) {
			return mv;
		}else {
			
		try {
			chefservice.salvarChef(chef);
			rt.addFlashAttribute("mensagem", "chef salvo");
			mv.addObject("mensagem", "chef salvo");
			return mv;
		} catch (Exception e) {
				rt.addFlashAttribute("mensagemErro", e.getMessage());
				mv.addObject("mensagemErro", "erro");
				return mv;
		}
		
	}
}
	@GetMapping("/loginchef")
	public String login(Chef chef, Model model) {
		model.addAttribute("chef", new Usuario());
		return "loginchef";
	}
	
	
	@PostMapping("/loginchef")
	public String efetuarLoginChef(@ModelAttribute("chef") Chef chef, BindingResult br, Model model, HttpSession sessao2) {
		if(br.hasErrors()) {
			System.out.println("resultado: "+br);
		}
		
		Chef chefconsultado = chefDAO.efetuarLogin(chef.getEmail(), chef.getSenha());
		
		if(chefconsultado==null) {
			model.addAttribute("mensagem", "Usuario ou Senha Inválidos");
		}
		else {
			sessao2.setAttribute("chefLogado", chefconsultado);
			return "chefLogadoIndex";
		}
		
		return null;
	}
	
	@GetMapping("perfil-chef")
	public ModelAndView PerfilChef() {
		ModelAndView mv = new ModelAndView("perfil-chef");
		mv.addObject("lista", chefservice.listarTodos());
		return mv;
	}
	//metodo complemento de perfil
	@GetMapping("complemento-perfil-chef")
	public ModelAndView complementoPerfilChef() {
	ModelAndView mv = new ModelAndView("complemento-perfil-chef");
	mv.addObject("chefComplemento", new ChefComplemento());
		return mv;
	}
	@PostMapping("/salvarComplementoChef")
	public ModelAndView saveComplementoChef(@ModelAttribute ChefComplemento chefComplemento, @RequestParam("fileFoto") MultipartFile file, HttpSession session) {
		ModelAndView mv = new ModelAndView("complemento-perfil-chef");
			mv.addObject("chefComplemento", chefComplemento);
			mv.addObject("mensagem", "salvo com sucesso");
			System.out.println(chefComplemento);
			try {
				chefComplemento.setFoto(file.getBytes());
				
			} catch (IOException e) {
				mv.addObject("mensagemErro", e.getMessage());
			}
			chefComplemento.setChef((Chef) session.getAttribute("chefLogado"));
			chefcomplemento.save(chefComplemento);
			return mv;
		}
	
//	=================== CARDAPIO DO CHEF =========================
	@GetMapping("Cardapio")
	public ModelAndView cardapio(@ModelAttribute Cardapio cardapio) {
		ModelAndView mv = new ModelAndView("Cardapio");
		mv.addObject("cardapio", cardapio);
		return mv;
	}
	
	@PostMapping("/addCardapio")
	public ModelAndView addCardapio(@Valid @ModelAttribute Cardapio cardapio, Errors errors, RedirectAttributes rt, @RequestParam("fileFoto2") MultipartFile file) throws IOException {
		ModelAndView mv = new ModelAndView("Cardapio");
		mv.addObject("cardapio", cardapio);
		if(errors.hasErrors()) {
			return mv;
		}else {
			try {
				cardapioService.salvarCardapio(cardapio);
				cardapio.setFileFoto(file.getBytes());
				System.out.println(cardapio);
				rt.addFlashAttribute("mensagem", "salvo");
				mv.addObject("mensagem", "salvo");
				return mv;
				
			} catch (Exception e) {
				rt.addFlashAttribute("mensagemErro", e.getMessage());
				mv.addObject("mensagemErro", "erro");
				return mv;
			}
		}
		
	}
	
	
//	============ EXCLUIR CONTA DO CHEF ============
	@GetMapping("/exclusaoConta")
	public String conta() {
		return "contaExcluida";
	}
	
	@GetMapping("/ExcluirConta")
	public ModelAndView excluirConta(@RequestParam Integer idChef) {
		ModelAndView mv = new ModelAndView("redirect:contaExcluida");
		chefDAO.deleteById(idChef);
		return mv;
	}
	
	//Método para recuperação de senha
		@GetMapping("/recuperarChef")
		public ModelAndView recChef() {
			ModelAndView mv = new ModelAndView("recuperarChef");
			return mv;
		}	
	
	
		
		//Método para recuperação de senha
		@GetMapping("/redefinirSenhaChef")
		public ModelAndView redefChef() {
			ModelAndView mv = new ModelAndView("redefinirSenhaChef");
			return mv;
		}	
		
//============= metodo listar chef para a view chef_encontrarChef
		@GetMapping(value = "/chef_encontrarChef")
		public ModelAndView viewChef() {
			ModelAndView mv = new ModelAndView("chef_encontrarChef");
			mv.addObject("lista", chefservice.listarTodos());
				return mv;
		}
			
		//metodo listar chef na view encontrar chef
		@GetMapping("/encontrar_chef")
		public ModelAndView encontrarChef() {
			ModelAndView mv = new ModelAndView("encontrar_chef");
			mv.addObject("lista", chefservice.listarTodos());
				return mv;
		}
			
	
}
