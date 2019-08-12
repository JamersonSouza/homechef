package br.com.homechef.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Cardapio {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	@NotBlank(message = "Campo NOME obrigatório!")
	private String nome;
	private String descricao;
	@NotNull(message = "CAMPO NÃO PODE FICAR VAZIO")
	private double valorPessoa;
	@Size(min = 1, message = "LIMITE DEVE SER SUPERIOR A 0")
	private String limitePessoas;

	private String imagemPrato;
	@ManyToOne
	private Chef chefCardapio;
	
	

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


	

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public double getValorPessoa() {
		return valorPessoa;
	}

	public void setValorPessoa(double valorPessoa) {
		this.valorPessoa = valorPessoa;
	}

	public String getLimitePessoas() {
		return limitePessoas;
	}

	public void setLimitePessoas(String limitePessoas) {
		this.limitePessoas = limitePessoas;
	}

	public String getImagem() {
		return imagemPrato;
	}

	public void setImagem(String imagem) {
		this.imagemPrato = imagem;
	}

	public Chef getChefCardapio() {
		return chefCardapio;
	}

	public void setChefCardapio(Chef chefCardapio) {
		this.chefCardapio = chefCardapio;
	}



}
