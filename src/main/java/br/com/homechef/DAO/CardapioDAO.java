package br.com.homechef.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.homechef.model.Cardapio;

public interface CardapioDAO extends JpaRepository<Cardapio, Integer> {

	
	public Cardapio findByNome(String nome);
	
	
}
 