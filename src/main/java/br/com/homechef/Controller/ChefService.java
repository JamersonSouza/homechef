package br.com.homechef.Controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.homechef.DAO.CardapioDAO;
import br.com.homechef.DAO.ChefDAO;
import br.com.homechef.excecoes.EmailExistsException;
import br.com.homechef.excecoes.EspecExistsException;
import br.com.homechef.excecoes.ServiceException;
import br.com.homechef.model.Cardapio;
import br.com.homechef.model.Chef;

@Service
public class ChefService {
	
	@Autowired
	private ChefDAO chefdao;
	
	
	public void salvarComplemento(Chef chef) throws Exception{
		if(this.chefdao.findByEmail(chef.getEspecialidade()) != null) {
			throw new EspecExistsException
			("Erro ao Editar seus dados!");
		}
		chefdao.save(chef);
	}
	
	public void salvarChef(Chef chef) throws Exception {
		if(this.chefdao.findByEmail(chef.getEmail()) != null) {
			throw new EmailExistsException
			("Já existe um Chef com este email: " + chef.getEmail());
		}
		chefdao.save(chef);
	}
	
	
	public Chef efetuarLogin(String email, String senha) throws ServiceException {
		Chef chef = this.chefdao.efetuarLogin(email, senha);
		if (chef == null) {
			throw new ServiceException("Email ou Senha Inválidos");
		}
		
		return chef;
	}
	
	//listar todos 
	public List<Chef> listarTodos(){
		return chefdao.findAll(Sort.by("nome"));
	}
	
	//listar por Id
	public Chef findById(Integer idChef) {
		return chefdao.findById(idChef).orElse(null);
	}

	//listar por nome
		public List<Chef> findByNome(String nome) {
			return chefdao.findByNomeContainingIgnoreCase(nome);
		}
		
	//listar por cidade
	public List<Chef> findByCidade(String cidade) {
		return chefdao.findByCidadeContainingIgnoreCase(cidade);
	}
	


}
