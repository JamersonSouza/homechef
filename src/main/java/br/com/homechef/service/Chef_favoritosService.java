package br.com.homechef.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.homechef.DAO.Chef_favoritosDAO;
import br.com.homechef.model.Chef_favoritos;

@Service
public class Chef_favoritosService {
	
	@Autowired
	private Chef_favoritosDAO chef_favoritosDAO;
	
	public List<Chef_favoritos> findAll(){
		return chef_favoritosDAO.findAll();
	}

}
