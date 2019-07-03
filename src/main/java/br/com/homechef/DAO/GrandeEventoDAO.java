package br.com.homechef.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.homechef.model.GrandesEventos;

public interface GrandeEventoDAO extends JpaRepository<GrandesEventos, Integer> {

}
