package br.com.homechef.DAO;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.homechef.model.CadastroUsuario;


public interface CadastroUsuarioDAO extends JpaRepository<CadastroUsuario, Integer> {

	
	@Query("select u from tab_usuario u where u.email=:usuario.email and u.senha=:usuario.senha")
	public CadastroUsuario efetuarlogin(CadastroUsuario usuario);
	
}
