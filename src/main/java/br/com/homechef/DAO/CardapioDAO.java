package br.com.homechef.DAO;

import java.util.List;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.homechef.model.Cardapio;

public interface CardapioDAO extends JpaRepository<Cardapio, Integer> {

	
	public Cardapio findByNome(String nome);
	
	@Query("select u from Cardapio u where u.valorPessoa > 20 and u.valorPessoa < 50")
	public List<Cardapio> findByPrecoBaixo();
	
	
	@Query("select u from Cardapio u")
	public Page<Cardapio> listaPratosEmAlta(org.springframework.data.domain.Pageable paginaReq);
	
	@Query("select u from Cardapio u where u.valorPessoa > 50 and u.valorPessoa < 120")
	public List<Cardapio> findByPrecoRazoavel();
	
	
	@Query("select u from Cardapio u where u.valorPessoa > 120 and u.valorPessoa < 180")
	public List<Cardapio> findByPrecoAlto();
	
	@Query("select u from Cardapio u where u.valorPessoa > 180")
	public List<Cardapio> findBySelect();
	
	public List<Cardapio> findByNomeContainingIgnoreCase(String PesquisaComida);
}
 