package br.com.homechef.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "tab_GrandesEventos")
public class GrandesEventos {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer idSolicitacao;
	private String nome;
	@NotBlank(message = "Campo EMAIL Vazio ou Inválido.")
	private String email;
	private String telefone;
	private String opcoes;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@FutureOrPresent(message = "Data Anterior à DATA ATUAL não é PERMITIDA.")
	private Date dataEvento;
	@DateTimeFormat(pattern = "HH:mm")
	private Date hora;
	@NotBlank(message = "Local do Evento Inválido ou Vazio")
	private String local;
	private String msg;
	private Integer numPessoas;
	
	
	public Integer getNumPessoas() {
		return numPessoas;
	}

	public void setNumPessoas(Integer numPessoas) {
		this.numPessoas = numPessoas;
	}

	public Integer getIdSolicitacao() {
		return idSolicitacao;
	}

	public void setIdSolicitacao(Integer idSolicitacao) {
		this.idSolicitacao = idSolicitacao;
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

	public String getOpcoes() {
		return opcoes;
	}

	public void setOpcoes(String opcoes) {
		this.opcoes = opcoes;
	}

	public Date getDataEvento() {
		return dataEvento;
	}

	public void setDataEvento(Date dataEvento) {
		this.dataEvento = dataEvento;
	}

	public Date getHora() {
		return hora;
	}

	public void setHora(Date hora) {
		this.hora = hora;
	}

	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
