package br.com.homechef.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.homechef.model.CadastroUsuario;

public interface CadastroUsuarioDAO extends JpaRepository<CadastroUsuario, Integer> {

}
