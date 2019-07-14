package br.com.homechef.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.homechef.DAO.ChefDAO;
import br.com.homechef.DAO.UsuarioDAO;
import br.com.homechef.excecoes.EmailExistsException;
import br.com.homechef.model.Usuario;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioDAO usuarioDAO;
	
	@Autowired
	private ChefDAO chefDAO;
	
	public void salvarUsuario(Usuario usuario) throws Exception {
		if(this.usuarioDAO.findByEmail(usuario.getEmail()) != null) {
			throw new EmailExistsException
			("Já existe um Usuário com este email: " + usuario.getEmail());
		}
		usuarioDAO.save(usuario);
	}
	
	
}
