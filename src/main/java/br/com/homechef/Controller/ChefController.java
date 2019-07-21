package br.com.homechef.Controller;

import java.io.IOException;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
	@GetMapping("loginchef")
	public ModelAndView view(Chef chef, Errors erros) {
		ModelAndView mv = new ModelAndView("loginchef");
		mv.addObject("chef", new Chef());
		mv.addObject("chef", chef);
		if(erros.hasErrors()) {
			return mv;
		}
		return mv;
	}
	
	@PostMapping("/logar")
	public String logar(@Valid Chef chef, RedirectAttributes rt, Errors errors,HttpSession session) {
		Chef chefLogado;
		try {
			chefLogado = this.chefservice.efetuarLogin(chef.getEmail(), chef.getSenha());
			session.setAttribute("chefLogado", chef);
			return "redirect:/perfil-chef";
			
		} catch (ServiceException e) {
			rt.addFlashAttribute("mensagemErro", e.getMessage());
			return "redirect:/loginchef";
		}
		
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
	public ModelAndView saveComplementoChef(@ModelAttribute ChefComplemento chefComplemento, @RequestParam("fileFoto") MultipartFile file) {
		ModelAndView mv = new ModelAndView("complemento-perfil-chef");
			mv.addObject("chefComplemento", chefComplemento);
			mv.addObject("mensagem", "salvo com sucesso");
			System.out.println(chefComplemento);
			try {
				chefComplemento.setFoto(file.getBytes());
				
			} catch (IOException e) {
				mv.addObject("mensagemErro", e.getMessage());
			}
			
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
	@GetMapping("/contaExcluida")
	public String conta() {
		return "contaExcluida";
	}
	
	@GetMapping("/ExcluirConta")
	public ModelAndView excluirConta(@RequestParam Integer idChef) {
		ModelAndView mv = new ModelAndView("redirect:contaExcluida");
		chefDAO.deleteById(idChef);
		return mv;
	}
	
	
}
