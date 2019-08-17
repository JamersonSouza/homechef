package br.com.homechef.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
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
	@Min(value = 30, message = "O VALOR MÍNIMO DEVE SER R$ 30,00")
	private double valorPessoa;
	@Size(min = 1, message = "LIMITE DE PESSOAS DEVE SER MAIOR QUE 0")
	private String limitePessoas;

	private String imagemPrato;
	
	
	
	

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


	public String getImagemPrato() {
		return imagemPrato;
	}

	public void setImagemPrato(String imagemPrato) {
		this.imagemPrato = imagemPrato;
	}



}
