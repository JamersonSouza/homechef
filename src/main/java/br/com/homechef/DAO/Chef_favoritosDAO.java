package br.com.homechef.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.homechef.model.Chef_favoritos;

public interface Chef_favoritosDAO extends JpaRepository<Chef_favoritos, Integer> {
	
	//metodo para pesquisar chef_fav
		public List<Chef_favoritos> findByNomeChefContainingIgnoreCase(String nomePesquisa);

}
