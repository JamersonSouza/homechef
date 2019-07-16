package br.com.homechef.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.homechef.model.UsuarioComplemento;

public interface ComplementoUsuarioDao extends JpaRepository<UsuarioComplemento, Integer> {

}
