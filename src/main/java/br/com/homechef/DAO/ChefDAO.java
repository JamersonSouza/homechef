package br.com.homechef.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.homechef.model.Chef;

public interface ChefDAO extends JpaRepository<Chef, Integer> {
	
	@Query("select u from Chef u where u.email = :email")
	public Chef findByEmail(String email);

}
