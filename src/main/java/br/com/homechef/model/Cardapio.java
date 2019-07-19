package br.com.homechef.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

@Entity
public class Cardapio {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer idCardapio;
	@NotBlank(message = "Campo NOME obrigatório!")
	private String nomeCardapio;
	@Lob
	private byte[] fotoCardapio;
	@NotBlank(message = "Campo DESCRIÇÂO obrigatório!")
	private String descricaoCardapio;
	@NotBlank(message = "Campo VALOR obrigatório!")
	private double valorPessoa;
	@NotBlank(message = "Campo LIMITE obrigatório!")
	private int limitePessoas;
	
	
	@ManyToOne
	private Chef chef;
	
	
	
	
	
	public Integer getIdCardapio() {
		return idCardapio;
	}
	public void setIdCardapio(Integer idCardapio) {
		this.idCardapio = idCardapio;
	}
	public String getNomeCardapio() {
		return nomeCardapio;
	}
	public void setNomeCardapio(String nomeCardapio) {
		this.nomeCardapio = nomeCardapio;
	}

	public String getDescricaoCardapio() {
		return descricaoCardapio;
	}
	public void setDescricaoCardapio(String descricaoCardapio) {
		this.descricaoCardapio = descricaoCardapio;
	}
	public double getValorPessoa() {
		return valorPessoa;
	}
	public void setValorPessoa(double valorPessoa) {
		this.valorPessoa = valorPessoa;
	}
	
	
	
	
	
	
	
	
	
	
	
	

}
