package br.com.homechef.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
public class Chef {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer idChef;
	@NotBlank(message = "Campo NOME obrigatório!")
	private String nome;
	@NotBlank(message = "Campo EMAIL Vazio ou Inválido")
	private String email;
	@Size(min = 8, message = "SENHA COM NO MÍNIMO 8 DIGITOS")
	@NotBlank(message = "Campo SENHA Vazio ou Inválido")
	private String senha;
	@Transient
	private String cnfSenha;

	public Integer getIdChef() {
		return idChef;
	}

	public void setIdChef(Integer idChef) {
		this.idChef = idChef;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getCnfSenha() {
		return cnfSenha;
	}

	public void setCnfSenha(String cnfSenha) {
		this.cnfSenha = cnfSenha;
	}

}
