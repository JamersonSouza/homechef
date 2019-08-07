package br.com.homechef.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.homechef.model.ChefComplemento;

public interface ChefComplementoDAO extends JpaRepository<ChefComplemento, Integer> {

	//metodo da pesquisa
	public List<ChefComplemento> findByCidadeIgnoreCase(String cidade);
	
	//public List<Chef> findByCidadeIgnoreCase(String cidade);
}
