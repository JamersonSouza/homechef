package br.com.homechef.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.homechef.model.CadastroChef;

public interface CadastroChefDAO extends JpaRepository<CadastroChef, Integer> {

}
