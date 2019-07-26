package br.com.homechef.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.homechef.model.Chef_favoritos;

public interface Chef_favoritosDAO extends JpaRepository<Chef_favoritos, Integer> {

}
