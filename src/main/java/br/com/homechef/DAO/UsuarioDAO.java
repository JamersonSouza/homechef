package br.com.homechef.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.homechef.model.Chef;
import br.com.homechef.model.Usuario;

public interface UsuarioDAO extends JpaRepository<Usuario, Integer> {
	
	//ISSO FUNCIONA
	@Query(value =  "select * from usuario where email = ?1 AND senha = ?2", nativeQuery = true)
    Usuario buscalogin(String email, String senha);
	
	@Query("select u from Usuario u where u.email = :email")
	public Usuario findByEmail(String email);
	
	@Query("select u from Usuario u where u.email = :email")
	public Usuario PesquisarEmail(String email);
	
	

}
