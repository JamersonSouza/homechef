package br.com.homechef.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.homechef.model.Usuario;

public interface UsuarioDAO extends JpaRepository<Usuario, Integer> {
	
	@Query(value =  "select * from tab_usuario where email = ?1 AND senha = ?2", nativeQuery = true)
    Usuario buscalogin(String email, String senha);
	

}
