package br.com.homechef.excecoes;

public class UsuarioInexistenteException extends Exception{
	
	public UsuarioInexistenteException (String string) {
		super (string);
	}

	
	private static final long serialVersionUID = 1L;
}
