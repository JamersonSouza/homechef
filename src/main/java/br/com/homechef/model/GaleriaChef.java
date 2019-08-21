package br.com.homechef.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class GaleriaChef {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String GaleriaImagem;
	
	@ManyToOne
	private Chef chefGaleria;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getGaleriaImagem() {
		return GaleriaImagem;
	}

	public void setGaleriaImagem(String galeriaImagem) {
		GaleriaImagem = galeriaImagem;
	}

	public Chef getChefGaleria() {
		return chefGaleria;
	}

	public void setChefGaleria(Chef chefGaleria) {
		this.chefGaleria = chefGaleria;
	}
	
	
	
}
