package br.com.homechef.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.homechef.model.Chef;

public interface ChefDAO extends JpaRepository<Chef, Integer> {
	
	@Query("select u from Chef u where u.email = :email")
	public Chef findByEmail(String email);

	@Query("select u from Chef u ")
	public Chef findBynome();
	
	@Query("select u from Chef u where u.email = :email and u.senha = :senha")
	public Chef efetuarLogin(String email, String senha);
	
	//public List<Chef> findByCidadeIgnoreCase(String cidade);
	public List<Chef> findByCidadeContainingIgnoreCase(String cidadePesquisa);
	
	public List<Chef> findByNomeContainingIgnoreCase(String nomePesquisa);
	
	
}