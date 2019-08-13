package br.com.homechef.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.homechef.model.Cardapio;

public interface CardapioDAO extends JpaRepository<Cardapio, Integer> {

	
	public Cardapio findByNome(String nome);
	
	@Query("select u from Cardapio u where u.valorPessoa > 20 and u.valorPessoa < 50")
	public List<Cardapio> findByPrecoBaixo();
	
	
}
 