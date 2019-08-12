package br.com.homechef.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.homechef.DAO.Chef_favoritosDAO;
import br.com.homechef.model.Chef_favorito;

@Service
public class Chef_favoritosService {
	
	@Autowired
	private Chef_favoritosDAO chef_favoritosDAO;
	
	public List<Chef_favorito> findAll(){
		return chef_favoritosDAO.findAll();
	}

	//metodo salvar chef_favoritos
//			public void salvarChef_favorito(Chef_favorito chef_favoritos) {
	//			if(this.chef_favoritosDAO.findByNomeChef(chef_favoritos.getNomeChef())==null) {			
	//			this.chef_favoritosDAO.save(chef_favoritos);
				
	//			}
		//	}
//}
//metodo salvar chef_favoritos
public void salvarChef_favorito(Chef_favorito chef_favoritos) {
	this.chef_favoritosDAO.findByNomeChef(chef_favoritos.getNomeChef());
	this.chef_favoritosDAO.save(chef_favoritos);
	
	}

}