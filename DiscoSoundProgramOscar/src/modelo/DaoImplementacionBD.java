package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

import clase.Administrador;
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
	final String CONSULTA_DNI = "select dni from usuario where dni=?";
	final String CONSULTA_EMAIL = "select email from usuario where email=?";
	final String CONSULTA_NOMUSU = "select nomUsu from usuario where nomUsu=?";
	final String LOGIN_CLI = "select * from usuario u, cliente c where u.dni=c.DNICLIENTE and nomusu = ? AND contraseina = ?";
	final String LOGIN_ADMIN = "select * from usuario u, administrador a where u.dni=a.dniadmin and u.nomusu = ? AND u.contraseina = ?";

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

	public void registro(Usuario usu) {
		this.openConnection();
		// int rs;
		try {
			stmt = con.prepareStatement(REGISTRO_USU);

			stmt.setString(1, usu.getDni());
			stmt.setString(2, usu.getNombre());
			stmt.setString(3, usu.getApellido());
			stmt.setString(4, usu.getNomUsu());
			stmt.setDate(5, java.sql.Date.valueOf(usu.getFechaNac()));
			stmt.setString(6, usu.getEmail());
			stmt.setString(7, usu.getContraseina());
			stmt.executeUpdate();
			// rs=stmt.executeUpdate();

			stmt = con.prepareStatement(REGISTRO_CLI);
			stmt.setString(1, usu.getDni());
			stmt.setString(2, ((Cliente) usu).getGenero());
			stmt.executeUpdate();

		} catch (SQLException e) {

			e.printStackTrace();
		}
		try {
			this.closeConnection();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public int comprobarUsuario(String dni, String email, String nomUsu) {
		ResultSet rs;

		this.openConnection();

		try {
			stmt = con.prepareStatement(CONSULTA_DNI);
			stmt.setString(1, dni);
			rs = stmt.executeQuery();

			if (!rs.next()) {
				stmt = con.prepareStatement(CONSULTA_EMAIL);
				stmt.setString(1, email);
				rs = stmt.executeQuery();

				if (!rs.next()) {
					stmt = con.prepareStatement(CONSULTA_NOMUSU);
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;

	}

	@Override
	public Usuario inicioSesion(Usuario usu) {
		// TODO Auto-generated method stub
		this.openConnection();
		Usuario usuario = null;
		ResultSet rs;

		try {
			stmt = con.prepareStatement(LOGIN_ADMIN);
			stmt.setString(1, usu.getNomUsu());
			stmt.setString(2, usu.getContraseina());
			rs = stmt.executeQuery();
			if (rs.next()) {
				usuario = new Administrador();

				((Administrador) usuario).setNomDiscoteca(rs.getString(2));
			} else {
				stmt = con.prepareStatement(LOGIN_CLI);
				stmt.setString(1, usu.getNomUsu());
				stmt.setString(2, usu.getContraseina());
				rs = stmt.executeQuery();

				if (rs.next()) {
					usuario = new Cliente();

					((Cliente) usuario).setGenero(rs.getString(2));
				} else {
					return null;
				}

			}
			usuario.setDni(rs.getString(1));
			usuario.setNombre(rs.getString(2));
			usuario.setApellido(rs.getString(3));
			usuario.setNomUsu(rs.getString(4));
			java.sql.Date fechaSQL = rs.getDate(5);
			usuario.setFechaNac(fechaSQL.toLocalDate());
			usuario.setEmail(rs.getString(6));
			usuario.setContraseina(rs.getString(7));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return usuario;

	}

}
