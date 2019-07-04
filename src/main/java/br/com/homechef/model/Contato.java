package br.com.homechef.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Entity
public class Contato {
	
	@Id 
	@GeneratedValue (strategy = GenerationType.AUTO)
	private Integer id;
	@NotBlank(message="O nome deve ser preenchido com texto válido.")
	private String nome;
	@NotBlank(message="O email deve ser preenchido corretamente.")
	private String email;
	@NotBlank(message="O telefone deve ser informado.")
	private String telefone;
	private String assunto;
	@NotBlank(message="Escreva a sua mensagem.")
	private String mensagem;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getAssunto() {
		return assunto;
	}
	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}
	public String getMensagem() {
		return mensagem;
	}
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	
	
	

}
