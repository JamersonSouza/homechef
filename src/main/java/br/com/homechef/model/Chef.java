package br.com.homechef.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

@Entity
public class Chef {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer idChef;
//	@NotBlank(message = "Campo NOME obrigatório!")
	private String nome;
//	@NotBlank(message = "Campo EMAIL Vazio ou Inválido")
	private String email;
//	@Size(min = 8, message = "SENHA COM NO MÍNIMO 8 DIGITOS")
//	@NotBlank(message = "Campo SENHA Vazio ou Inválido")
	private String senha;
	@Transient
	private String cnfSenha;
	
	private String imagem;
		
	private String estado;
	private String bairro;
	private String cidade;
	private String cep;
	private String telefone;	
	
	private boolean possuiCertificado;
	private String nomeCertificado;
	private String descricao;
	private String especialidade;
	
	private String rua;
	
	@OneToOne
	private Usuario usuario;
	
	@OneToMany
	private List<Cardapio> cardapio;
	
	@OneToMany
	private List<GaleriaChef> galeriachef;
	
	@OneToMany
	private List<Favoritar> favoritar;
	
	
	
	public List<Favoritar> getFavoritar() {
		return favoritar;
	}

	public void setFavoritar(List<Favoritar> favoritar) {
		this.favoritar = favoritar;
	}

	public List<GaleriaChef> getGaleriachef() {
		return galeriachef;
	}

	public void setGaleriachef(List<GaleriaChef> galeriachef) {
		this.galeriachef = galeriachef;
	}

	public List<Cardapio> getCardapio() {
		return cardapio;
	}

	public void setCardapio(List<Cardapio> cardapio) {
		this.cardapio = cardapio;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public Integer getIdChef() {
		return idChef;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public boolean isPossuiCertificado() {
		return possuiCertificado;
	}

	public void setPossuiCertificado(boolean possuiCertificado) {
		this.possuiCertificado = possuiCertificado;
	}

	public String getNomeCertificado() {
		return nomeCertificado;
	}

	public void setNomeCertificado(String nomeCertificado) {
		this.nomeCertificado = nomeCertificado;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getEspecialidade() {
		return especialidade;
	}

	public void setEspecialidade(String especialidade) {
		this.especialidade = especialidade;
	}

	public void setIdChef(Integer idChef) {
		this.idChef = idChef;
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

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	
	
}
