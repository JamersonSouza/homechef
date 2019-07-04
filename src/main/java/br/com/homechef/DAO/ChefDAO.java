package br.com.homechef.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.homechef.model.Chef;

public interface ChefDAO extends JpaRepository<Chef, Integer> {

}
