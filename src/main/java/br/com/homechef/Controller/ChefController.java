package br.com.homechef.Controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.homechef.DAO.CardapioDAO;
import br.com.homechef.DAO.ChefDAO;
import br.com.homechef.model.Cardapio;
import br.com.homechef.model.Chef;
import br.com.homechef.model.Usuario;

@Controller
public class ChefController {

	@Autowired
	private ChefDAO chefDAO;
	
	@Autowired
	private ChefService chefservice;
	
	@Autowired
	private CardapioService cardapioService;
	
	
	@GetMapping("/cadastroChef")
	public ModelAndView cadastroChef() {
		ModelAndView mv = new ModelAndView("cadastroChef");
		mv.addObject("chef", new Chef());
		return mv;
	}
	
	@PostMapping("/addChef")
	public ModelAndView addUser(@Valid @ModelAttribute Chef chef, Errors erros, RedirectAttributes rt, @RequestParam("file") MultipartFile imagem) {
		ModelAndView mv = new ModelAndView("contaCriada");
		mv.addObject("chef", chef);
		if(erros.hasErrors()) {
			return mv;
		}else {
			
		try {
			if (Util.fazerUploadImagem(imagem)) {
				chef.setImagem(imagem.getOriginalFilename());
				}
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
		
		Chef chefLogado = chefDAO.efetuarLogin(chef.getEmail(), chef.getSenha());
		
		if(chefLogado==null) {
			model.addAttribute("mensagem", "Usuario ou Senha Inválidos");
		}
		else {
			sessao2.setAttribute("chefLogado", chefLogado);
			return "chefLogadoIndex";
		}
		
		return null;
	}
	
//=========================== PÁGINA DO PERFIL ===============================
	
	@GetMapping("perfilChef")
	public ModelAndView perfilchef(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("perfilChef");
		Chef chefLogado = (Chef) request.getSession().getAttribute("chefLogado");
		mv.addObject("perfilChefLista", chefDAO.findAll(Sort.by("nome")));
		return mv;
	}
	
	public ModelAndView listar() {
		ModelAndView mv = new ModelAndView();
			mv.addObject("perfilChefLista", chefDAO.findAll(Sort.by("nome")));
			mv.addObject("chef", new Chef());
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
	public ModelAndView addCardapio(@Valid @ModelAttribute Cardapio cardapio, Errors errors, RedirectAttributes rt, @RequestParam("file") MultipartFile imagemPratos){
		ModelAndView mv = new ModelAndView("Cardapio");
		mv.addObject("cardapio", cardapio);
		if(errors.hasErrors()) {
			return mv;
		}else {
			try {
				if (Util.fazerUploadImagem(imagemPratos)) {
					cardapio.setImagemPrato(imagemPratos.getOriginalFilename());
					}
				cardapioService.salvarCardapio(cardapio);
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
	
//	============== PRATOS DE BAIXO PREÇO =======================
	@GetMapping("/lista-pratos-baixoPreco")
		public ModelAndView pratosBaixoPreco() {
			ModelAndView mv = new ModelAndView("lista-pratos-baixoPreco");
			mv.addObject("listaPratos", cardapioService.precosBaixo());
			return mv;
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
	
// =============Método para recuperação de senha ========================
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
		
//============= metodo listar chef para a view contratarChef
		@GetMapping(value = "/ContratarChef")
		public ModelAndView viewChef() {
			ModelAndView mv = new ModelAndView("ContratarChef");
			mv.addObject("lista", chefservice.listarTodos());
				return mv;
		}
			
		//metodo listar chef na view encontrar chef 
		@GetMapping("/EncontrarChef")
		public ModelAndView encontrarChef(@ModelAttribute Chef chef) {
			ModelAndView mv = new ModelAndView("EncontrarChef");
			mv.addObject("listachef", chefservice.listarTodos());
			return mv;
		}
			
		//pesquisar chef por cidade
		@PostMapping("/pesquisarChef")
		public ModelAndView pesquisarChef(@RequestParam(required=false) String cidadePesquisa) {
			ModelAndView mv = new ModelAndView("/EncontrarChef");		
			List<Chef> listachef;
			if (cidadePesquisa == null || cidadePesquisa.trim().isEmpty()) {
				listachef = this.chefservice.listarTodos();	
			} else {
				listachef = this.chefDAO.findByCidadeContainingIgnoreCase(cidadePesquisa);
			}
			mv.addObject("listachef", listachef);
			return mv;
		}
		

}
