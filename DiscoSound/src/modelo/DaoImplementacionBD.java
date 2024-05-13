package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import clase.Administrador;
import clase.Cliente;
import clase.Compra;
import clase.Discoteca;
import clase.Entrada;
import clase.Usuario;
import controlador.Dao;

public class DaoImplementacionBD implements Dao {

	// Estos atributos son los necesarios para acceder a la base de datos

	private Connection con;
	private PreparedStatement stmt;

	// Estos atributos son los necesarios para recoger los valores del fichero de
	// configuracion

	private ResourceBundle configFichero;
	private String driverBD;
	private String urlBD;
	private String userBD;
	private String passwordBD;
	private String id;

	// SENTENCIAS SQL

	final String REGISTRO_USU = "insert into usuario (dni, nombre, apellido, nomusu, fechanac ,email, contraseina) values (?,?,?,?,?,?,?)";
	final String REGISTRO_CLI = "insert into cliente (dnicliente, genero) values (?,?)";
	final String REGISTRO_COMPRA = "insert into compra values(?, ?, ?, ?, ?, ?)";
	final String CONSULTA_DNI = "select dni from usuario where dni=?";
	final String CONSULTA_EMAIL = "select email from usuario where email=?";
	final String CONSULTA_NOMUSU = "select nomUsu from usuario where nomUsu=?";
	final String CONSULTA_DISCOTECAS = "select * from discoteca";
	final String ENTRADAS_DISCOTECA = "select * from entrada where codigoDisc=?";
	final String ENTRADAS_COMPRADAS = "select * from compra where DNICLIENTE=? and codEntrada=?";
	final String LOGIN_CLI = "select * from usuario u, cliente c where u.dni=c.DNICLIENTE and nomusu = ? AND contraseina = ?";
	final String LOGIN_ADMIN = "select * from usuario u, administrador a where u.dni=a.dniadmin and u.nomusu = ? AND u.contraseina = ?";
	final String CAMBIAR_CLI = "update cliente c, usuario u set u.nombre=?, u.email=?, u.apellido=?,  u.contraseina=?  where u.dni=?";
	final String TABLA_MOD1 = "select codentrada, nomevento, nomdj, precio,categoria, cantconsumi, fecha  from entrada";
	final String MOD2 = "update entrada set nomevento=?, nomdj=?, precio=?, cantconsumi=?, diferenciapreciomujer=? where codEntrada=?";
	final String CONTAR_ENTRADAS = "SELECT COUNT(*) AS total_entradas FROM Entrada";
	final String BUSCAR_CODIGO_DISCO = "select codigodisc from discoteca where nombre = ?";
	final String REGISTRO_ENTRADA = "insert into entrada (CODENTRADA, NOMEVENTO, NOMDJ, PRECIO, CATEGORIA, CANTCONSUMI, FECHA, DIFERENCIAPRECIOMUJER, CODIGODISC) values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
	final String BORRAR_ENTRADA = "delete from entrada where codentrada = ?";
	final String BORRAR_COMPRA = "delete from compra where codentrada = ?";
	final String BUSCAR_NOMBRE_DISCOTECA = "select nombre from discoteca where codigodisc = (select codigodisc from entrada where codentrada = ?)";
	final String CALCULAR_ENTRADAS_DISPONIBLES = "SELECT AforoDisponibleEnFecha(?, ?) AS AforoDisponible";

	public DaoImplementacionBD() {
		this.configFichero = ResourceBundle.getBundle("modelo.config");
		this.driverBD = this.configFichero.getString("Driver");
		this.urlBD = this.configFichero.getString("Conn");
		this.userBD = this.configFichero.getString("DBUser");
		this.passwordBD = this.configFichero.getString("DBPass");
	}

	// Este método sirve para abrir la conexion

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

	// Este metodo sirve para cerrar la conexion

	private void closeConnection() throws SQLException {
		if (stmt != null) {
			stmt.close();
		}
		if (con != null)
			con.close();
	}

	public void registro(Usuario usu) {
		// El metodo registro es el metodo que registra a los usuarios

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
		// El metodo comprobarUsuario, valida los campos dni, email, nomUsu

		this.openConnection();
		ResultSet rs;

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

			e.printStackTrace();
		}
		return 0;

	}

	@Override
	public Usuario inicioSesion(String usuario, char[] contraseina) {
		// Este metodo es el usado al iniciar sesion

		this.openConnection();
		Usuario usu = null;
		ResultSet rs;
		String contra = "";
		for (int i = 0; i < contraseina.length; i++) {
			contra = contra + String.valueOf(contraseina[i]);

		}

		try {
			stmt = con.prepareStatement(LOGIN_ADMIN);
			stmt.setString(1, usuario);
			stmt.setString(2, contra);
			rs = stmt.executeQuery();
			if (rs.next()) {
				usu = new Administrador();

				((Administrador) usu).setNomDiscoteca(rs.getString(9));
			} else {
				stmt = con.prepareStatement(LOGIN_CLI);
				stmt.setString(1, usuario);
				stmt.setString(2, contra);
				rs = stmt.executeQuery();

				if (rs.next()) {
					usu = new Cliente();

					((Cliente) usu).setGenero(rs.getString(9));
				} else {
					return null;
				}

			}
			usu.setDni(rs.getString(1));
			usu.setNombre(rs.getString(2));
			usu.setApellido(rs.getString(3));
			usu.setNomUsu(rs.getString(4));
			java.sql.Date fechaSQL = rs.getDate(5);
			usu.setFechaNac(fechaSQL.toLocalDate());
			usu.setEmail(rs.getString(6));
			usu.setContraseina(rs.getString(7));
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return usu;

	}

	@Override
	public void cambiarDatos(Usuario usu) {
		// Este metodo sirve para cambiar los datos

		this.openConnection();

		try {
			stmt = con.prepareStatement(CAMBIAR_CLI);
			stmt.setString(1, usu.getNombre());
			stmt.setString(2, usu.getEmail());
			stmt.setString(3, usu.getApellido());
			stmt.setString(4, usu.getContraseina());
			stmt.setString(5, usu.getDni());
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
	public List<Discoteca> mostrarDiscotecas() {
		// Este metodo sirve para mostrar las entradas, ya que las mostramos en varias
		// partes del programa

		this.openConnection();
		ResultSet rs1;
		ResultSet rs2;
		Entrada entrada;
		Discoteca discoteca;
		List<Discoteca> discotecas = new ArrayList<>();

		try {
			stmt = con.prepareStatement(CONSULTA_DISCOTECAS);
			rs1 = stmt.executeQuery();

			while (rs1.next()) {
				discoteca = new Discoteca();
				discoteca.setCodigo(rs1.getString(1));
				discoteca.setNombre(rs1.getString(2));
				discoteca.setAforo(rs1.getInt(4));

				stmt = con.prepareStatement(ENTRADAS_DISCOTECA);
				stmt.setString(1, discoteca.getCodigo());
				rs2 = stmt.executeQuery();
				while (rs2.next()) {
					entrada = new Entrada();
					entrada.setCodigoEntrada(rs2.getString(1));
					java.sql.Date fechaSQL = rs2.getDate(7);
					entrada.setFecha(fechaSQL.toLocalDate());
					entrada.setPrecio(rs2.getFloat(4));
					entrada.setNombreEvento(rs2.getString(2));
					entrada.setNombreDJ(rs2.getString(3));
					discoteca.getEntradas().add(entrada);
				}
				discotecas.add(discoteca);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return discotecas;
	}

	@Override
	public void pagar(Cliente cliente, Entrada entrada, Compra compra) {
		// Este metodo sirve para registrar una compra

		this.openConnection();

		try {
			stmt = con.prepareStatement(REGISTRO_COMPRA);

			stmt.setString(1, cliente.getDni());
			stmt.setString(2, entrada.getCodigoEntrada());
			stmt.setString(3, compra.getMetodoPago());
			stmt.setFloat(4, compra.getPrecioTotal());
			stmt.setInt(5, compra.getCantidadEntradas());
			stmt.setInt(6, compra.getTelefono());
			stmt.executeUpdate();
			this.closeConnection();
		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	@Override
	public boolean comprobarEntradaComprada(Cliente cli, String codigoEntrada) {
		// Este metodo sirve para mostrar las entradas que ha comprado el cliente

		this.openConnection();
		ResultSet rs;

		try {
			stmt = con.prepareStatement(ENTRADAS_COMPRADAS);
			stmt.setString(1, cli.getDni());
			stmt.setString(2, codigoEntrada);
			rs = stmt.executeQuery();
			while (rs.next()) {
				return false;
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return true;
	}

	@Override
	public int comprobarCompra(Cliente cli, String codigoEntrada) {

		this.openConnection();
		ResultSet rs;

		try {
			stmt = con.prepareStatement(ENTRADAS_COMPRADAS);
			stmt.setString(1, cli.getDni());
			stmt.setString(2, codigoEntrada);
			rs = stmt.executeQuery();
			while (rs.next()) {
				return rs.getInt(5);
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return 0;
	}

	@Override
	public List<Entrada> sacarEntradas() {

		List<Entrada> entradas = new ArrayList<Entrada>();
		ResultSet rs;
		Entrada entrada;
		this.openConnection();
		try {

			stmt = con.prepareStatement(TABLA_MOD1);
			rs = stmt.executeQuery();

			while (rs.next()) {
				entrada = new Entrada();
				entrada.setCodigoEntrada(rs.getString("codentrada"));
				entrada.setNombreEvento(rs.getString("nomevento"));
				entrada.setNombreDJ(rs.getNString("nomdj"));
				entrada.setCodigoEntrada(rs.getString("codentrada"));
				entrada.setPrecio(rs.getFloat("precio"));
				entrada.setCategoria(rs.getString("categoria"));
				entrada.setCantidadConsumo(rs.getInt("cantconsumi"));
				java.sql.Date fechaSQL = rs.getDate("fecha");
				entrada.setFecha(fechaSQL.toLocalDate());

				entradas.add(entrada);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return entradas;
	}

	@Override
	public void modificarEntrada(Entrada entra) {

		this.openConnection();
		try {

			stmt = con.prepareStatement(MOD2);
			stmt.setString(1, entra.getNombreEvento());
			stmt.setString(2, entra.getNombreDJ());
			stmt.setFloat(3, entra.getPrecio());
			stmt.setInt(4, entra.getCantidadConsumo());
			stmt.setFloat(5, entra.getDiferenciaPrecioMujer());
			stmt.setString(6, entra.getCodigoEntrada());

			stmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void registroEntrada(Entrada ent, Administrador admin) {

		String codDisc;

		ResultSet rs;
		openConnection();
		try {
			stmt = con.prepareStatement(BUSCAR_CODIGO_DISCO);
			stmt.setString(1, admin.getNomDiscoteca());
			rs = stmt.executeQuery();
			if (rs.next()) {
				codDisc = rs.getString(1);

				stmt = con.prepareStatement(REGISTRO_ENTRADA);

				stmt.setString(1, ent.getCodigoEntrada());
				stmt.setString(2, ent.getNombreEvento());
				stmt.setString(3, ent.getNombreDJ());
				stmt.setFloat(4, ent.getPrecio());
				stmt.setString(5, ent.getCategoria());
				stmt.setInt(6, ent.getCantidadConsumo());
				stmt.setDate(7, java.sql.Date.valueOf(ent.getFecha()));
				stmt.setFloat(8, ent.getDiferenciaPrecioMujer());
				stmt.setString(9, codDisc);
				stmt.executeUpdate();
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}
		try {
			closeConnection();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public int obtenerUltimoNumeroDeEntrada() {
		int totalEntradas = 0;
		openConnection();

		try {
			PreparedStatement ps = con.prepareStatement(CONTAR_ENTRADAS);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				totalEntradas = rs.getInt("total_entradas");
			}

			rs.close();
			ps.close();
		} catch (SQLException e) {
			// Manejo de excepciones
			e.printStackTrace();
		}
		try {
			closeConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return totalEntradas;
	}

	@Override
	public void borrarEntrada(Entrada ent) {
		// TODO Auto-generated method stub

		String codEntrada = ent.getCodigoEntrada();

		try {
			stmt = con.prepareStatement(BORRAR_COMPRA);
			stmt.setString(1, codEntrada);
			stmt.executeUpdate();

			stmt = con.prepareStatement(BORRAR_ENTRADA);
			stmt.setString(1, codEntrada);
			stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			closeConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public String calculoCantidadEntradasDisponibles(Entrada ent) {
		// Este metodo sirve para retornar la cantidad de entradas disponibles	
		openConnection();
		ResultSet rs;
		String nombreDiscoteca = null;

		try {
			stmt = con.prepareStatement(BUSCAR_NOMBRE_DISCOTECA);
			stmt.setString(1, ent.getCodigoEntrada());
			rs = stmt.executeQuery();
			
			if (rs.next()) {
		        nombreDiscoteca = rs.getString("nombre"); // Nombre de la columna
		        // Realizar otras operaciones con el nombre de la discoteca aquí
		    } else {
		        System.out.println("ERROR");
		    }
			
			stmt = stmt = con.prepareStatement(CALCULAR_ENTRADAS_DISPONIBLES);
			stmt.setString(1, nombreDiscoteca);
			stmt.setDate(2, java.sql.Date.valueOf(ent.getFecha()));
			rs = stmt.executeQuery();
			
			if (rs.next()) {
				String entradasDisponibles = rs.getString("AforoDisponible"); // Nombre de la columna
		        // Realizar otras operaciones con el nombre de la discoteca aquí
				
				return entradasDisponibles;
		    } else {
		        System.out.println("ERROR");
		    }
			
			
			

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			closeConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

}
