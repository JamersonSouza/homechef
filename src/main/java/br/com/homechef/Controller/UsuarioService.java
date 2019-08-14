package br.com.homechef.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.homechef.DAO.ChefDAO;
import br.com.homechef.DAO.UsuarioDAO;
import br.com.homechef.excecoes.EmailExistsException;
import br.com.homechef.excecoes.ServiceException;
import br.com.homechef.excecoes.UsuarioInexistenteException;
import br.com.homechef.model.Usuario;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioDAO usuarioDAO;
	
	@Autowired
	private ChefDAO chefDAO;
	
	public void salvarUsuario(Usuario usuario) throws Exception {
		try {
			
			if(this.usuarioDAO.findByEmail(usuario.getEmail()) != null) {
				throw new EmailExistsException
				("Já existe um Usuário com este email: " + usuario.getEmail());
			}
			
			usuario.setSenha(Util.md5(usuario.getSenha()));
			
		} catch (Exception e) {
			
			throw new ServiceException("Erro Na Criptografia");
		}
		
		usuarioDAO.save(usuario);
	}
	
	public Usuario recuperarSenha(Usuario usuario) throws Exception{
		Usuario var = this.usuarioDAO.PesquisarEmail(usuario.getEmail());
		if(var == null) {
			throw new UsuarioInexistenteException
			("Não existe um usuario com este email: " + usuario.getEmail());
			
		}
		
		return var;
	}
	
	
	
	
	
	
}
