package br.com.homechef.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
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
	private String imagem;
	
	private String destinatario;
	
	
	
	public String getDestinatario() {
		return destinatario;
	}
	public void setDestinatario(String destinatario) {
		this.destinatario = destinatario;
	}
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
	public String getImagem() {
		return imagem;
	}
	public void setImagem(String imagem) {
		this.imagem = imagem;
	}
		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return senha;
		}

}
