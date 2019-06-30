package br.com.homechef.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.homechef.model.Contato;

@Repository
public interface ContatoDAO extends JpaRepository<Contato, Integer> {

}
