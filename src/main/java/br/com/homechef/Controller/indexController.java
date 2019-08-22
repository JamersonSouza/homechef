package br.com.homechef.Controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpSession;

//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
//
//import br.com.homechef.DAO.UsuarioDAO;
//import br.com.homechef.model.Chef;
//import br.com.homechef.model.Usuario;

@Controller
public class indexController {
//	
//	@Autowired
//	private CompraDAO compra;
//	
//	@Autowired 
//	private UsuarioDAO usuarioDAO;
//	
//	@Autowired
//	private UsuarioService usuarioservice;
	
	//Pagina Inicial - View
	@GetMapping("/index")
	public ModelAndView pagInicial() {
		ModelAndView mv = new ModelAndView("index");
		return mv;
	}
	//Pagina de um Usuario Normal Logado - View
	@GetMapping("/usuario_logado_index")
	public ModelAndView usuarioLogadoIndex() {
		ModelAndView mv = new ModelAndView("usuario_logado_index");
		return mv;
	}

	//sobre nos
	@GetMapping("/sobre_nos")
		public ModelAndView sobreNos() {
			ModelAndView mv = new ModelAndView("sobre_nos");
			return mv;
	}
	
//	@RequestMapping("pagamento")
//	public String pagamento(@RequestParam(name = "valor") String valor,@RequestParam(name = "usuario")int usuario, HttpSession session, Compra user){
//		String url = Util.createPayment(valor, usuario);
//		return "redirect:"+url;
//	}
	
//	@RequestMapping("compraFalha")
//	public String compraFalha(Model model){
//		model.addAttribute("msg", "Compra não foi realizada");
//		return "compraFalha";
//	}

	
	//pagamento comcluido
//		@RequestMapping("compraSucesso")
//		public String compraSucesso(Model model,@RequestParam(name = "paymentId") String paymentId,@RequestParam(name = "token") String token,
//									@RequestParam(name = "PayerID") String PayerID,@RequestParam(name = "usuario") int usuario,
//									@RequestParam(name = "valor") String valor, HttpSession session, Usuario user) throws ParseException{
//
//		
//			Compra pagamento = new Compra();
//			pagamento.setPayerID(PayerID);
//			pagamento.setPaymentId(paymentId);
//			pagamento.setTokem(token);
//			pagamento.setTransacao("compra feita");
//
//			Usuario u1 = new Usuario();
//			u1.setIdUsuario(usuario);
//			
//			
//			Usuario u2 = usuarioDAO.buscarId(u1);
//			
//			
//			pagamento.setIdUsuario(u2);
//			pagamento.setValorCompra(valor);
//
//			SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
//			Date dataFormatada = formato.parse(Util.obterMomentoAtual());
//			pagamento.setDataCompra(dataFormatada);
//			
//			
//			pagamento.setIdUsuario((Usuario) session.getAttribute("usuariologado"));
//			compra.save(pagamento);
//
//			model.addAttribute("msg", "Compra realizada com sucesso");
//			return "CompraConcluida";
//		}
//
//		
//		public ModelAndView listapagamento() {
//			ModelAndView mv = new ModelAndView();
//			mv.addObject("pagamento", compra.findAll());
//			return mv;
//		}

		
}
