package br.com.homechef.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.homechef.model.Chef_favorito;

public interface Chef_favoritosDAO extends JpaRepository<Chef_favorito, Integer> {
	
	public Chef_favorito findByNomeChef(String nomeChef);
	
	//metodo para pesquisar chef_fav
		public List<Chef_favorito> findByNomeChefContainingIgnoreCase(String nomePesquisa);

}
