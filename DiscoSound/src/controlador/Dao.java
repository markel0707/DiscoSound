package controlador;

import java.util.List;

import clase.Administrador;
import clase.Cliente;
import clase.Compra;
import clase.Discoteca;
import clase.Entrada;
import clase.Usuario;

public interface Dao {

	public void registro(Usuario usu);

	public int comprobarUsuario(String dni, String email, String nomUsu);

	public Usuario inicioSesion(String usuario, char[] contraseina);

	public void cambiarDatos(Usuario usu);

	public List<Discoteca> mostrarDiscotecas();

	public void pagar(Cliente cli, Entrada entrada, Compra compra);

	public boolean comprobarEntradaComprada(Cliente cli, String codigoEntrada);

	public int comprobarCompra(Cliente cli, String codigoEntrada);

	public List<Entrada> sacarEntradas();

	public void modificarEntrada(Entrada entra);

	public void registroEntrada(Entrada ent, Administrador admin);

	public int obtenerUltimoNumeroDeEntrada();
	
	public void borrarEntrada(Entrada ent);
	
	public String calculoCantidadEntradasDisponibles(Entrada ent);

}
