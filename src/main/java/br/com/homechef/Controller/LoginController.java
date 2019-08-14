package br.com.homechef.Controller;

import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import br.com.homechef.DAO.ChefDAO;
import br.com.homechef.DAO.UsuarioDAO;
import br.com.homechef.model.Chef;
import br.com.homechef.model.Usuario;

@Controller
public class LoginController {
	
	@Autowired
	private UsuarioDAO cadastrousuarioDAO;
	
	
	@GetMapping("/login")
	public String login(Usuario usuario, Model model) {
		model.addAttribute("usuario", new Usuario());
		return "login";
	}
	
	
	
	@PostMapping("/login")
	public String efetuarLogin(@ModelAttribute("usuario") Usuario usuario, BindingResult br, Model model, HttpSession sessao) throws NoSuchAlgorithmException {
		if(br.hasErrors()) {
			System.out.println("Resultado: " + br);
		}
		
		
		 Usuario usuarioCosultado = cadastrousuarioDAO.buscalogin(usuario.getEmail(), Util.md5(usuario.getSenha()));
		
		if(usuarioCosultado == null) {
			model.addAttribute("mensagem", "Usuario e senha invalido");
		}else {
			sessao.setAttribute("usuariologado", usuarioCosultado);
			return "usuarioLogadoIndex";

		}
		
		return null;

	}	

	//AINDA NÃO FOI TOTALMENTE IMPLEMENTADO!
	@GetMapping("/acesso-negado")
	public String acessoNegado() {
		return "acesso-negado";
	}
	
	
}
