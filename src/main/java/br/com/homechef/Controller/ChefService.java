package br.com.homechef.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.homechef.DAO.ChefDAO;
import br.com.homechef.excecoes.EmailExistsException;
import br.com.homechef.model.Chef;

@Service
public class ChefService {
	
	@Autowired
	private ChefDAO chefdao;
	
	public void salvarChef(Chef chef) throws Exception {
		if(this.chefdao.findByEmail(chef.getEmail()) != null) {
			throw new EmailExistsException
			("Já existe um Chef com este email: " + chef.getEmail());
		}
		chefdao.save(chef);
	}


}
