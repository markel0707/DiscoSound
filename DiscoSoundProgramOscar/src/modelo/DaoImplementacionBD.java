package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
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
	
	//SENTENCIAS SQL
	final String REGISTRO_USU="insert into usuario (dni, nombre, apellido, nomusu, fechanac ,email, contraseina) values (?,?,?,?,?,?,?)";
	final String REGISTRO_CLI="insert into cliente (dnicliente, genero) values (?,?)";
	final String CONSULTA_USU = "select * from usuario where nomusu = ? AND contraseina = ?";
	
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
			stmt=con.prepareStatement(REGISTRO_USU);
			
			stmt.setString(1, usu.getDni());
			stmt.setString(2, usu.getNombre());
			stmt.setString(3, usu.getApellido());
			stmt.setString(4, usu.getNomUsu());
			stmt.setDate(5, java.sql.Date.valueOf(usu.getFechaNac()));
			stmt.setString(6, usu.getEmail());
			stmt.setString(7, usu.getContraseina());
			rs = stmt.executeUpdate();
			
			
			stmt=con.prepareStatement(REGISTRO_CLI);
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
	public void login(String dni, String email, String nomUsu) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Usuario inicioSesion(String nomUsu, String contraseina) {
		// TODO Auto-generated method stub
		Usuario usu = new Usuario();
		this.openConnection();

		ResultSet rs;

		try {
			stmt = con.prepareStatement(CONSULTA_USU);
			stmt.setString(1, nomUsu);
			stmt.setString(2, contraseina);
			rs = stmt.executeQuery();

			usu.setDni(rs.getString(1));
			usu.setNombre(rs.getString(2));
			usu.setApellido(rs.getString(3));
			usu.setNomUsu(rs.getString(4));

			java.sql.Date fechaSQL = rs.getDate(5);
			LocalDate fechaNac = fechaSQL.toLocalDate();

			usu.setFechaNac(fechaNac);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return usu;

	}

}
