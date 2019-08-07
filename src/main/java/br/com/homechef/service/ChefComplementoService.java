package br.com.homechef.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.homechef.DAO.ChefComplementoDAO;
import br.com.homechef.model.Chef;
import br.com.homechef.model.ChefComplemento;

@Service
public class ChefComplementoService {

	@Autowired
	private ChefComplementoDAO chefComplementoDao;
	
	//metodo da pesquisa
	public List<ChefComplemento> pesquisarCidade(String cidade) {
		return chefComplementoDao.findByCidadeIgnoreCase(cidade);
   }
	
	//listar todos 
		public List<ChefComplemento> listarTodos(){
			return chefComplementoDao.findAll();
		}
		
		
		
}
