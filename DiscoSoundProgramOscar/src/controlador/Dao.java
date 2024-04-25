package controlador;

import clase.Cliente;
import clase.Usuario;

public interface Dao {
	
	public void registro (Usuario usu, Cliente cli);
	
	public void login (String dni, String email, String nomUsu);
	
	public Usuario inicioSesion(String nomUsu, String contraseina);

}
