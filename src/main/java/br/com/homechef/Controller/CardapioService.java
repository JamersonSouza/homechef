package br.com.homechef.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.homechef.DAO.CaradapioDAO;
import br.com.homechef.model.Cardapio;

@Service
public class CardapioService {

	@Autowired
	private CaradapioDAO cardapioDAO;
	
	public void salvarCardapio (Cardapio cardapio) {
		
		cardapioDAO.save(cardapio);
	}
	
	
	
	
}
