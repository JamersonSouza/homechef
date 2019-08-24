package br.com.homechef.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.homechef.model.Favoritar;

public interface FavoritarDAO extends JpaRepository<Favoritar, Integer> {

}
