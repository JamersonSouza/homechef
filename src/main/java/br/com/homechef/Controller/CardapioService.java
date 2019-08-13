package br.com.homechef.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.homechef.DAO.CardapioDAO;
import br.com.homechef.excecoes.CardapioExistsException;
import br.com.homechef.model.Cardapio;
import br.com.homechef.model.Chef;

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
	
	//listar Preço de Prato
	public List<Cardapio> precosBaixo(){
		return cardapioDAO.findByPrecoBaixo();
	}
	
	//Lista Preços Razoavel de pratos (Médios)
	public List<Cardapio> precosRazoavel(){
		return cardapioDAO.findByPrecoRazoavel();
		}
	
	//Lista Preços Altos de Pratos
	public List<Cardapio> precosAltos(){
		return cardapioDAO.findByPrecoAlto();
	}
	
	//Lista Preços Muitos Altos de Pratos
	public List<Cardapio> pratosSelect(){
		return cardapioDAO.findBySelect();
	}
	
	//listar nome da comida
		public List<Cardapio> findByNome(String PesquisaComida) {
			return cardapioDAO.findByNomeContainingIgnoreCase(PesquisaComida);
		}
		
		
		

		//listar todos 
		public List<Cardapio> listarTudo(){
			return cardapioDAO.findAll(Sort.by("nome"));
		}
	
}
