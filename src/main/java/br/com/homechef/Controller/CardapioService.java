package br.com.homechef.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.homechef.DAO.CaradapioDAO;

@Service
public class CardapioService {

	
	
	@Autowired
	private CaradapioDAO cardapioDAO;
}
