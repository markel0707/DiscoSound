package controlador;

import clase.Cliente;
import clase.Usuario;

public interface Dao {
	
	public void registro (Usuario usu, Cliente cli);
	
	public int comprobarUsuario (String dni, String email, String nomUsu);
	
	public boolean inicioSesion(String nomUsu, String contraseina);

}
