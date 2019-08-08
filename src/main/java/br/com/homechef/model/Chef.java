package br.com.homechef.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

@Entity
public class Chef {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer idChef;
//	@NotBlank(message = "Campo NOME obrigatório!")
	private String nome;
//	@NotBlank(message = "Campo EMAIL Vazio ou Inválido")
	private String email;
//	@Size(min = 8, message = "SENHA COM NO MÍNIMO 8 DIGITOS")
//	@NotBlank(message = "Campo SENHA Vazio ou Inválido")
	private String senha;
	@Transient
	private String cnfSenha;
	
	private String imagem;
	
	
	//@OneToOne(cascade = CascadeType.ALL)
	//@JoinColumn(name = "fk_chefComplemento",nullable=false)
	@OneToOne
	private ChefComplemento chefComplemento;
	
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

	public ChefComplemento getChefComplemento() {
		return chefComplemento;
	}

	public void setChefComplemento(ChefComplemento chefComplemento) {
		this.chefComplemento = chefComplemento;
	}

	public String getImagem() {
		return imagem;
	}

	public void setImagem(String imagem) {
		this.imagem = imagem;
	}


	
}
