package controlador;

import clase.Cliente;
import clase.Usuario;

public interface Dao {
	
	public void registro (Usuario usu);
	
	public int comprobarUsuario (String dni, String email, String nomUsu);
	
	public Usuario inicioSesion(Usuario usu);

}
