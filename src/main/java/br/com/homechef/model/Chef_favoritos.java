package br.com.homechef.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Chef_favoritos {

	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String nomeChef;
	private String especialidade;
	//@ManyToOne
	//private Chef chef;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNomeChef() {
		return nomeChef;
	}
	public void setNomeChef(String nomeChef) {
		this.nomeChef = nomeChef;
	}
	public String getEspecialidade() {
		return especialidade;
	}
	public void setEspecialidade(String especialidade) {
		this.especialidade = especialidade;
	}
	//public Chef getChef() {
		//return chef;
	//}
	//public void setChef(Chef chef) {
		//this.chef = chef;
	//}
	
	
}
