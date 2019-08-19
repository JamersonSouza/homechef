package br.com.homechef.Controller;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
	
	@Autowired
	private CardapioDAO card;
	
	
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
	public String efetuarLoginChef(@ModelAttribute("chef") Chef chef, BindingResult br, Model model, HttpSession sessao2) throws NoSuchAlgorithmException {
		if(br.hasErrors()) {
			System.out.println("resultado: "+br);
		}
		
		Chef chefLogado = chefDAO.efetuarLogin(chef.getEmail(), Util.md5(chef.getSenha()));
		
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
			mv.addObject("perfilChefLista", chefservice.listarTodos());
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
	public ModelAndView addCardapio(@Valid @ModelAttribute Cardapio cardapio,@ModelAttribute Chef chef, HttpServletRequest request,Errors errors, RedirectAttributes rt, @RequestParam("file") MultipartFile imagemPratos){
		ModelAndView mv = new ModelAndView("Cardapio");
		mv.addObject("cardapio", cardapio);
		mv.addObject("chef", chef);
		Chef chefLogado = (Chef) request.getSession().getAttribute("chefLogado");
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
	

//	============== PESQUISAR PRATOS COM PREÇO ALTO =======================
	
	@PostMapping("/pesquisarComida")
	public ModelAndView pesquisarComida(@RequestParam(required=false) String PesquisaComida) {
		ModelAndView mv = new ModelAndView("/lista-pratos-AltoPreco");
		List<Cardapio> listaPratosAlto;
		if (PesquisaComida == null || PesquisaComida.trim().isEmpty()) {
			listaPratosAlto = this.cardapioService.listarTudo();
		} else {
			listaPratosAlto = this.cardapioService.findByNome(PesquisaComida);
		}
		mv.addObject("listaPratosAlto", listaPratosAlto);
		return mv;
	}
	
//	=============== PESQUISA PRATOS COM PREÇOS MÉDIOS ==========================
	
	
	@PostMapping("/pesquisaComida")
	public ModelAndView pesquisaComida(@RequestParam(required=false) String PesquisaComida) {
		ModelAndView mv = new ModelAndView("/lista-pratos-MedioPreco");
		List<Cardapio> listaPratosMedio;
		if (PesquisaComida == null || PesquisaComida.trim().isEmpty()) {
			listaPratosMedio = this.cardapioService.listarTudo();
		} else {
			listaPratosMedio = this.cardapioService.findByNome(PesquisaComida);
		}
		mv.addObject("listaPratosMedio", listaPratosMedio);
		return mv;
	}
	
	
//	============ PESQUISA PRATOS COM PREÇO SELECT ===============================
	
	
	@PostMapping("/pratosSelect")
	public ModelAndView pratosSelect(@RequestParam(required=false) String PesquisaComida) {
		ModelAndView mv = new ModelAndView("/pratos-PrecoSelect");
		List<Cardapio> listaPratosSelect;
		if (PesquisaComida == null || PesquisaComida.trim().isEmpty()) {
			listaPratosSelect = this.cardapioService.listarTudo();
		} else {
			listaPratosSelect = this.cardapioService.findByNome(PesquisaComida);
		}
		mv.addObject("listaPratosSelect", listaPratosSelect);
		return mv;
	}


//	============== PESQUISA PRATOS COM PREÇO BAIXO ===========================
	
	
	@PostMapping("/pesquisa-pratos-baratos")
	public ModelAndView pratosBaratos(@RequestParam(required=false) String PesquisaComida) {
		ModelAndView mv = new ModelAndView("/lista-pratos-baixoPreco");
		List<Cardapio> listaPratos;
		if (PesquisaComida == null || PesquisaComida.trim().isEmpty()) {
			listaPratos = this.cardapioService.listarTudo();
		} else {
			listaPratos = this.cardapioService.findByNome(PesquisaComida);
		}
		mv.addObject("listaPratos", listaPratos);
		return mv;
	}
	
	
	
//	============== PRATOS DE BAIXO PREÇO =======================
	
	@GetMapping("/")
	public ModelAndView pratosEmAlta(@RequestParam(defaultValue="1") int page) {
		ModelAndView mv = new ModelAndView("listaPratosEmAlta");
		Pageable paginaReq = PageRequest.of(page - 1, 6, Sort.by("nome"));
		Page<Cardapio> paginaResult = this.cardapioService.PratosEmAlta(paginaReq);
		mv.addObject("PratosEmAlta", paginaResult);
		return mv;
	}
	
//	============== PRATOS DE BAIXO PREÇO =======================
	@GetMapping("/lista-pratos-economicos")
	public ModelAndView pratosBaixoPreco() {
		ModelAndView mv = new ModelAndView("lista-pratos-baixoPreco");
		mv.addObject("listaPratos", cardapioService.precosBaixo());
		return mv;
	}
//	============= PRATOS DE PREÇO RAZOÁVEL ===================
	
	@GetMapping("/lista-pratos-MedioPreco")
	public ModelAndView pratosMedioPreco() {
		ModelAndView mv = new ModelAndView("lista-pratos-MedioPreco");
		mv.addObject("listaPratosMedio", cardapioService.precosRazoavel());
		return mv;
	}
	
//	============= PRATOS DE PREÇO ALTO ===================

	@GetMapping("/lista-pratos-AltoPreco")
	public ModelAndView pratosAltoPreco() {
		ModelAndView mv = new ModelAndView("lista-pratos-AltoPreco");
		mv.addObject("listaPratosAlto", cardapioService.precosAltos());
		return mv;
	}
		
//		============= PRATOS DE PREÇO SELECT  ===================
	@GetMapping("/pratos-PrecoSelect")
	public ModelAndView pratosSelectPreco() {
		ModelAndView mv = new ModelAndView("pratos-PrecoSelect");
		mv.addObject("listaPratosSelect", cardapioService.pratosSelect());
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
		
// ========================== EDIÇÃO DO CARDAPIO ===================================
		
		@GetMapping("CardapioEdit")
		public ModelAndView viewEdit() {
			ModelAndView mv = new ModelAndView("CardapioEdit");
			mv.addObject("listaCardapio", cardapioService.listarTodos());
			return mv;
		}
		
		@GetMapping("EditCardapio")
		public ModelAndView editarCardapio(@RequestParam Integer id) {
			ModelAndView mv = new ModelAndView("Cardapio");
			mv.addObject("cardapio", cardapioService.findById(id));
			card.deleteById(id);
			return mv;
		}
		
		@GetMapping("/removerCardapio")
		public ModelAndView removerCardapio(@RequestParam Integer id) {
		ModelAndView mv = new ModelAndView("CardapioEdit");
		mv.addObject("card", new Cardapio());
		card.deleteById(id);
		return mv;
		
		}
}
