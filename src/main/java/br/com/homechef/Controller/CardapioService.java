package br.com.homechef.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.homechef.DAO.CardapioDAO;
import br.com.homechef.excecoes.CardapioExistsException;
import br.com.homechef.model.Cardapio;

@Service
public class CardapioService {

	@Autowired
	private CardapioDAO cardapioDAO;
	
	public void salvarCardapio (Cardapio cardapio) throws Exception {
		
		if(this.cardapioDAO.findByNome(cardapio.getNome()) != null) {
			throw new CardapioExistsException("Já existe um Prato Cadastrado Com esse nome" + cardapio.getNome());
		}
		
		cardapioDAO.save(cardapio);
	}
	
	
	
	
}
