package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import clase.Cliente;
import clase.Usuario;
import controlador.Dao;

public class DaoImplementacionBD implements Dao {

	private Connection con;
	private PreparedStatement stmt;

	// Estos atributos son los necesarios para recoger los valores del fichero de
	private ResourceBundle configFichero;
	private String driverBD;
	private String urlBD;
	private String userBD;
	private String passwordBD;
	private String id;
	// configuracion

	// SENTENCIAS SQL
	final String REGISTRO_USU = "insert into usuario (dni, nombre, apellido, nomusu, fechanac ,email, contraseina) values (?,?,?,?,?,?,?)";
	final String REGISTRO_CLI = "insert into cliente (dnicliente, genero) values (?,?)";
	final String COMPROBAR_DNI = "select dni from usuario where dni=?";
	final String COMPROBAR_EMAIL = "select email from usuario where email=?";
	final String COMPROBAR_NOMUSU = "select nomUsu from usuario where nomUsu=?";

	public DaoImplementacionBD() {
		// TODO Auto-generated constructor stub
		this.configFichero = ResourceBundle.getBundle("modelo.config");
		this.driverBD = this.configFichero.getString("Driver");
		this.urlBD = this.configFichero.getString("Conn");
		this.userBD = this.configFichero.getString("DBUser");
		this.passwordBD = this.configFichero.getString("DBPass");
	}

	// Abrimos la conexion
	public void openConnection() {
		try {
			con = DriverManager.getConnection(this.urlBD, this.userBD, this.passwordBD);
		} catch (SQLException e) {
			System.out.println("Error al intentar abrir la BD");
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("Se ha abierto la base de datos");
			e.printStackTrace();
		}
	}

//cerramos la conexion
	private void closeConnection() throws SQLException {
		if (stmt != null) {
			stmt.close();
		}
		if (con != null)
			con.close();
	}

	public void registro(Usuario usu, Cliente cli) {
		this.openConnection();
		int rs;
		try {
			stmt = con.prepareStatement(REGISTRO_USU);

			stmt.setString(1, usu.getDni());
			stmt.setString(2, usu.getNombre());
			stmt.setString(3, usu.getApellido());
			stmt.setString(4, usu.getNomUsu());
			stmt.setDate(5, java.sql.Date.valueOf(usu.getFechaNac()));
			stmt.setString(6, usu.getEmail());
			stmt.setString(7, usu.getContraseina());
			rs = stmt.executeUpdate();

			stmt = con.prepareStatement(REGISTRO_CLI);
			stmt.setString(1, usu.getDni());
			stmt.setString(2, cli.getGenero());
			rs = stmt.executeUpdate();

		} catch (SQLException e) {

			e.printStackTrace();
		}
		try {
			this.closeConnection();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public int comprobarUsuario(String dni, String email, String nomUsu) {
		ResultSet rs;
		this.openConnection();

		try {
			stmt = con.prepareStatement(COMPROBAR_DNI);
			stmt.setString(1, dni);
			rs = stmt.executeQuery();
			if (!rs.next()) {
				stmt = con.prepareStatement(COMPROBAR_EMAIL);
				stmt.setString(1, email);
				rs = stmt.executeQuery();
				if (!rs.next()) {
					stmt = con.prepareStatement(COMPROBAR_NOMUSU);
					stmt.setString(1, nomUsu);
					rs = stmt.executeQuery();
					if (!rs.next()) {
						return 3;
							
					} else {
						return 2;
					}

				} else {
					return 1;
				}
			} else {
				return 0;
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return 0;
	

	}

}
