package br.com.homechef.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.homechef.model.GaleriaChef;

public interface GaleriaDAO extends JpaRepository<GaleriaChef, Integer> {

}
