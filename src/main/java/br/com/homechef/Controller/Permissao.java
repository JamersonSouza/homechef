package br.com.homechef.Controller;

public enum Permissao {
	
	CHEF("chef"), USUARIO("Usuário padrão");
	
	public String texto;
	
	private Permissao(String t) {
		texto = t;
	}

}
