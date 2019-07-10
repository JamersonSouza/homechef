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
@Table(name = "tab_usuario")
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer idUsuario;
	@NotBlank(message = "Campo NOME Vazio ou Inválido")
	private String nome;
	@NotBlank(message = "Campo EMAIL Vazio ou Inválido")
	private String email;
	//@Size(min = 8, message = "SENHA no Mínimo 8 Digitos")
	//@NotBlank(message = "Campo SENHA Vazio ou Inválido")
	private String senha;
	@Transient 
	private String cnfSenha;
	//@NotBlank(message = "Campo ENDEREÇO Vazio ou Inválido")
	private String endereco;
	//@NotBlank(message = "Campo CEP Vazio ou Inválido")
	private String cep;
	//@NotBlank(message = "Campo COMPLEMENTO Vazio ou Inválido")
	private String complemento;
	//@NotBlank(message = "Campo TELEFONE Vazio ou Inválido")
	private String telefone;
	
	public Integer getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
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
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public String getComplemento() {
		return complemento;
	}
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	

}
