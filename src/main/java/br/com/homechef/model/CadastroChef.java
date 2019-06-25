package br.com.homechef.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "tab_chef_conta")
public class CadastroChef {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer idChef;
	private String nomeChef;
	private String emailChef;
	private String senhaChef;

	public Integer getIdChef() {
		return idChef;
	}

	public void setIdChef(Integer idChef) {
		this.idChef = idChef;
	}

	public String getNomeChef() {
		return nomeChef;
	}

	public void setNomeChef(String nomeChef) {
		this.nomeChef = nomeChef;
	}

	public String getEmailChef() {
		return emailChef;
	}

	public void setEmailChef(String emailChef) {
		this.emailChef = emailChef;
	}

	public String getSenhaChef() {
		return senhaChef;
	}

	public void setSenhaChef(String senhaChef) {
		this.senhaChef = senhaChef;
	}

}
