package br.com.homechef.DAO;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.homechef.model.CadastroUsuario;


public interface CadastroUsuarioDAO extends JpaRepository<CadastroUsuario, Integer> {

	@Query(value =  "select * from tab_usuario where email = ?1 AND senha_cliente = ?2", nativeQuery = true)
    CadastroUsuario buscalogin(String email, String senha);
	
}
