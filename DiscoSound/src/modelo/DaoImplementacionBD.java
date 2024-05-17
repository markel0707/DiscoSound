package modelo;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
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


/**
 * Implementación de la interfaz Dao para acceder a una base de datos.
 */
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
	final String COMPROBAR_EXISTENCIA_ENTRADAS = "SELECT COUNT(*) AS total_entradas FROM Entrada where CODIGODISC = (SELECT CODIGODISC FROM DISCOTECA WHERE NOMBRE = (SELECT NOMDISCOTECA FROM ADMINISTRADOR WHERE DNIADMIN = ?))";
	final String BUSCAR_ENTRADA = "SELECT * FROM ENTRADA WHERE CODENTRADA = ?";
	final String CANTIDAD_ENTRADAS = "CALL obtenerCantidadEntradasVendidas(?, ?)";
	final String CONTAR_USUARIOS = "SELECT CONTARUSUARIOS() AS TOTALUSUARIOS";
	final String CALCULAR_EDAD_MEDIA = "call CalcularEdadPromedioEntrada(?)";

	
    /**
     * Constructor de DaoImplementacionBD.
     * Inicializa los parámetros de conexión a la base de datos a partir de un archivo de configuración.
     */
	public DaoImplementacionBD() {
		this.configFichero = ResourceBundle.getBundle("modelo.config");
		this.driverBD = this.configFichero.getString("Driver");
		this.urlBD = this.configFichero.getString("Conn");
		this.userBD = this.configFichero.getString("DBUser");
		this.passwordBD = this.configFichero.getString("DBPass");
	}

	// Este método sirve para abrir la conexion
	
    /**
     * Abre la conexión a la base de datos.
     */
	
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
	
    /**
     * Cierra la conexión a la base de datos y el PreparedStatement si están abiertos.
     * 
     * @throws SQLException si ocurre un error al cerrar la conexión o el PreparedStatement.
     */

	// Este metodo sirve para cerrar la conexion

	private void closeConnection() throws SQLException {
		if (stmt != null) {
			stmt.close();
		}
		if (con != null)
			con.close();
	}
	
    /**
     * Registra un nuevo usuario en la base de datos.
     * 
     * @param usu el objeto Usuario que contiene los datos del usuario a registrar.
     */

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
	
	/**
	 * Comprueba la existencia de un usuario en la base de datos a través de su DNI, correo electrónico y nombre de usuario.
	 *
	 * @param dni     El DNI del usuario a comprobar.
	 * @param email   El correo electrónico del usuario a comprobar.
	 * @param nomUsu  El nombre de usuario del usuario a comprobar.
	 * @return 0 si el DNI ya existe en la base de datos, 1 si el correo electrónico ya existe, 2 si el nombre de usuario ya existe, 3 si ninguno de los campos existe.
	 */

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
	
	/**
	 * Inicia sesión en el sistema con el nombre de usuario y contraseña proporcionados.
	 *
	 * @param usuario      El nombre de usuario del usuario que intenta iniciar sesión.
	 * @param contraseina  La contraseña del usuario que intenta iniciar sesión.
	 * @return Un objeto de tipo Usuario que representa al usuario que inició sesión, o null si no se encuentra ningún usuario con las credenciales proporcionadas.
	 */

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
	
	/**
	 * Cambia los datos de un usuario en la base de datos.
	 *
	 * @param usu El usuario cuyos datos se van a cambiar.
	 */
	

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
	
	/**
	 * Obtiene una lista de discotecas con sus respectivas entradas desde la base de datos.
	 *
	 * @return Una lista de objetos Discoteca que representan las discotecas y sus entradas.
	 */

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
					entrada.setNombreEvento(rs2.getString(2));
					entrada.setNombreDJ(rs2.getString(3));
					entrada.setPrecio(rs2.getFloat(4));
					entrada.setCategoria(rs2.getString(5));
					java.sql.Date fechaSQL = rs2.getDate(7);
					entrada.setFecha(fechaSQL.toLocalDate());
					entrada.setDiferenciaPrecioMujer(rs2.getFloat(8));
					discoteca.getEntradas().add(entrada);
				}
				discotecas.add(discoteca);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return discotecas;
	}
	
	/**
	 * Registra una compra en la base de datos.
	 *
	 * @param cliente  El cliente que realiza la compra.
	 * @param entrada  La entrada que se está comprando.
	 * @param compra   Los detalles de la compra.
	 */

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
	
	/**
	 * Comprueba si una entrada ha sido comprada por un cliente.
	 *
	 * @param cli 
	 * El cliente para el que se comprueba la entrada.
	 * @param codigoEntrada 
	 * El código de la entrada que se está comprobando.
	 * @return true 
	 * si la entrada no ha sido comprada por el cliente, false de lo contrario.
	 */

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
	
	/**
	 * Comprueba si una compra ha sido realizada por un cliente para una entrada específica.
	 *
	 * @param cli           
	 * El cliente para el que se comprueba la compra.
	 * @param codigoEntrada
	 *  El código de la entrada para la que se está comprobando la compra.
	 * @return El número de entradas compradas por el cliente para la entrada especificada.
	 */

	@Override
	public int comprobarCompra(Cliente cli, String codigoEntrada) {
		// Este metodo sirve para comprobar una compra

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
	
	/**
	 * Obtiene una lista de todas las entradas disponibles en la base de datos.
	 *
	 * @return Una lista de objetos Entrada que representan las entradas disponibles.
	 */

	@Override
	public List<Entrada> sacarEntradas() {
		// Este metodo sirve para devolver una lista de entradas

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
				entrada.setNombreDJ(rs.getString("nomdj"));
				entrada.setCodigoEntrada(rs.getString("codentrada"));
				entrada.setPrecio(rs.getFloat("precio"));
				entrada.setCategoria(rs.getString("categoria"));
				entrada.setCantidadConsumo(rs.getInt("cantconsumi"));
				java.sql.Date fechaSQL = rs.getDate("fecha");
				entrada.setFecha(fechaSQL.toLocalDate());

				entradas.add(entrada);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return entradas;
	}
	
	/**
	 * Modifica una entrada existente en la base de datos.
	 *
	 * @param entra La entrada que se va a modificar.
	 */

	@Override
	public void modificarEntrada(Entrada entra) {
		// Este metodo lo usamos para modificar una entrada ya creada

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
			e.printStackTrace();
		}
	}
	
	/**
	 * Registra una nueva entrada en la base de datos.
	 *
	 * @param ent   La entrada que se va a registrar.
	 * @param admin El administrador que realiza el registro.
	 */

	@Override
	public void registroEntrada(Entrada ent, Administrador admin) {
		// Este metodo es el que usamos para registrar entradas

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
	
	/**
	 * Obtiene el número total de entradas registradas en la base de datos.
	 *
	 * @return El número total de entradas registradas.
	 */

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
			e.printStackTrace();
		}

		try {
			closeConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return totalEntradas;
	}
	
	/**
	 * Borra una entrada de la base de datos, así como todas las compras asociadas a ella.
	 *
	 * @param ent La entrada que se va a borrar.
	 */

	@Override
	public void borrarEntrada(Entrada ent) {
		// Este metodo sirve para borrar entrada, tambien borrara la compra

		String codEntrada = ent.getCodigoEntrada();

		try {
			stmt = con.prepareStatement(BORRAR_COMPRA);
			stmt.setString(1, codEntrada);
			stmt.executeUpdate();

			stmt = con.prepareStatement(BORRAR_ENTRADA);
			stmt.setString(1, codEntrada);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		try {
			closeConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	/**
	 * Calcula la cantidad de entradas disponibles en una discoteca para una fecha específica.
	 *
	 * @param ent La entrada para la que se va a calcular la cantidad de entradas disponibles.
	 * @return La cantidad de entradas disponibles.
	 */

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
				nombreDiscoteca = rs.getString("nombre");
			} else {
				System.out.println("ERROR");
			}

			stmt = con.prepareStatement(CALCULAR_ENTRADAS_DISPONIBLES);
			stmt.setString(1, nombreDiscoteca);
			stmt.setDate(2, java.sql.Date.valueOf(ent.getFecha()));

			rs = stmt.executeQuery();

			if (rs.next()) {
				String entradasDisponibles = rs.getString("AforoDisponible");

				return entradasDisponibles;
			} else {
				System.out.println("ERROR");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			closeConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}
	
	/**
	 * Comprueba si existen entradas registradas por un administrador.
	 *
	 * @param admin El administrador para el que se comprueba la existencia de entradas.
	 * @return true si existen entradas registradas por el administrador, false de lo contrario.
	 */

	@Override
	public boolean comprobarExistenciaEntradas(Administrador admin) {
		// Este metodo sirve para comprobar la existencia de entradas
		openConnection();
		ResultSet rs;

		try {
			stmt = con.prepareStatement(COMPROBAR_EXISTENCIA_ENTRADAS);
			stmt.setString(1, admin.getDni());
			rs = stmt.executeQuery();

			if (rs.next()) {
				int totalFilas = rs.getInt("total_entradas");

				if (totalFilas > 0) {
					// La tabla tiene datos
					return true;
				} else {
					// La tabla no tiene datos
					return false;
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			closeConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return true;

	}
	
	/**
	 * Obtiene una entrada específica de la base de datos.
	 *
	 * @param ent La entrada que se va a buscar.
	 * @return La entrada encontrada, o null si no se encuentra.
	 */

	@Override
	public Entrada obtenerEntrada(Entrada ent) {
		// Este metodo sirve para buscar una entrada por el codigo y devolver el objeto
		// completo
		openConnection();
		ResultSet rs;
		Entrada nuevaEnt = new Entrada();

		try {
			stmt = con.prepareStatement(BUSCAR_ENTRADA);
			stmt.setString(1, ent.getCodigoEntrada());
			rs = stmt.executeQuery();
			nuevaEnt = new Entrada();

			if (rs.next()) {
				nuevaEnt.setCodigoEntrada(rs.getString("CODENTRADA"));
				nuevaEnt.setCantidadConsumo(rs.getInt("CANTCONSUMI"));
				nuevaEnt.setNombreEvento(rs.getString("NOMEVENTO"));
				nuevaEnt.setNombreDJ(rs.getString("NOMDJ"));
				nuevaEnt.setPrecio(rs.getFloat("PRECIO"));
				nuevaEnt.setCategoria(rs.getString("CATEGORIA"));
				nuevaEnt.setDiferenciaPrecioMujer(rs.getFloat("DIFERENCIAPRECIOMUJER"));

				return nuevaEnt;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			closeConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;

	}
	
	/**
	 * Devuelve la cantidad de entradas vendidas para una entrada específica.
	 *
	 * @param codigoEntrada El código de la entrada para la cual se desea obtener la cantidad vendida.
	 * @return La cantidad de entradas vendidas.
	 */

	@Override
	public int cantidadEntradasVendidas(String codigoEntrada) {
		// Este metodo devuelve un entero con la cantidad de entradas vendidas

		this.openConnection();

		try (CallableStatement statement = con.prepareCall(CANTIDAD_ENTRADAS)) {
			statement.setString(1, codigoEntrada);
			statement.registerOutParameter(2, Types.INTEGER);
			statement.execute();

			return statement.getInt(2);

		} catch (SQLException e) {

		}
		return 0;
	}
	
	/**
	 * Calcula el número total de usuarios en el sistema.
	 *
	 * @return El número total de usuarios.
	 */

	@Override
	public int calcularNumeroTotalUsuarios() {
		// Este metodo sirve para retornar la cantidad de entradas disponibles

		openConnection();

		ResultSet rs;
		int totalEntradas = 0;

		try {
			stmt = con.prepareStatement(CONTAR_USUARIOS);
			rs = stmt.executeQuery();

			if (rs.next()) {
				totalEntradas = rs.getInt("TOTALUSUARIOS");
				return totalEntradas;
			} else {
				System.out.println("ERROR");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			closeConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	/**
	 * Calcula la edad media de los usuarios que han comprado una entrada específica.
	 *
	 * @param codigoEntrada El código de la entrada para la cual se desea calcular la edad media de los compradores.
	 * @return La edad media de los compradores de la entrada.
	 */

	@Override
	public String calcularEdadMediaEntrada(String codigoEntrada) {
	    // Este método devuelve un entero con la media de entradas vendidas
	    this.openConnection();

	    try (CallableStatement statement = con.prepareCall("{call CalcularEdadPromedioEntrada(?, ?)}")) {
	        statement.setString(1, codigoEntrada);
	        statement.registerOutParameter(2, Types.FLOAT);
	        statement.execute();

	        float edadPromedio = statement.getFloat(2);
	        if (statement.wasNull()) {
	            return "0 ventas";
	        } else {
	            return String.valueOf(edadPromedio);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return null;
	}

}
