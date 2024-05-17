package controlador;

import java.util.List;

import clase.Administrador;
import clase.Cliente;
import clase.Compra;
import clase.Discoteca;
import clase.Entrada;
import clase.Usuario;

/**
 * La interfaz Dao define los métodos que deben ser implementados por cualquier clase que actúe como un 
 * objeto de acceso a datos (DAO, por sus siglas en inglés) para interactuar con la base de datos o 
 * cualquier otro sistema de almacenamiento.
 */
public interface Dao {

    // Métodos para operaciones relacionadas con el registro y manejo de usuarios

    /**
     * Registra un nuevo usuario en el sistema.
     * @param usu el usuario a registrar.
     */
    public void registro(Usuario usu);

    /**
     * Comprueba la existencia de un usuario en la base de datos.
     * @param dni el DNI del usuario.
     * @param email el correo electrónico del usuario.
     * @param nomUsu el nombre de usuario.
     * @return el resultado de la comprobación.
     */
    public int comprobarUsuario(String dni, String email, String nomUsu);

    /**
     * Inicia sesión de un usuario en el sistema.
     * @param usuario el nombre de usuario del usuario.
     * @param contraseina la contraseña del usuario.
     * @return el usuario que ha iniciado sesión, o null si las credenciales son incorrectas.
     */
    public Usuario inicioSesion(String usuario, char[] contraseina);

    /**
     * Cambia los datos de un usuario en el sistema.
     * @param usu el usuario con los datos actualizados.
     */
    public void cambiarDatos(Usuario usu);

    // Métodos para operaciones relacionadas con las discotecas

    /**
     * Obtiene una lista de todas las discotecas en el sistema.
     * @return una lista de objetos de tipo Discoteca.
     */
    public List<Discoteca> mostrarDiscotecas();

    // Métodos para operaciones relacionadas con la compra de entradas y gestión de eventos

    /**
     * Realiza el proceso de pago de una entrada por parte de un cliente.
     * @param cli el cliente que realiza la compra.
     * @param entrada la entrada que se está comprando.
     * @param compra los detalles de la compra.
     */
    public void pagar(Cliente cli, Entrada entrada, Compra compra);

    /**
     * Comprueba si un cliente ha comprado una entrada específica.
     * @param cli el cliente.
     * @param codigoEntrada el código de la entrada a comprobar.
     * @return true si el cliente ha comprado la entrada, false en caso contrario.
     */
    public boolean comprobarEntradaComprada(Cliente cli, String codigoEntrada);

    /**
     * Comprueba si un cliente ha realizado una compra de una entrada específica.
     * @param cli el cliente.
     * @param codigoEntrada el código de la entrada a comprobar.
     * @return la cantidad de entradas compradas por el cliente para el evento especificado.
     */
    public int comprobarCompra(Cliente cli, String codigoEntrada);

    /**
     * Obtiene una lista de todas las entradas disponibles en el sistema.
     * @return una lista de objetos de tipo Entrada.
     */
    public List<Entrada> sacarEntradas();

    // Métodos para operaciones adicionales relacionadas con las entradas

    /**
     * Modifica los detalles de una entrada en el sistema.
     * @param entra la entrada con los detalles actualizados.
     */
    public void modificarEntrada(Entrada entra);

    /**
     * Registra una nueva entrada en el sistema por parte de un administrador.
     * @param ent la entrada a registrar.
     * @param admin el administrador que registra la entrada.
     */
    public void registroEntrada(Entrada ent, Administrador admin);

    /**
     * Obtiene el número de entrada más reciente registrado en el sistema.
     * @return el número de la última entrada registrada.
     */
    public int obtenerUltimoNumeroDeEntrada();

    /**
     * Borra una entrada del sistema.
     * @param ent la entrada a borrar.
     */
    public void borrarEntrada(Entrada ent);

    /**
     * Calcula la cantidad de entradas disponibles para una entrada específica.
     * @param ent la entrada para la que se calcula la cantidad de entradas disponibles.
     * @return una cadena de texto que describe la cantidad de entradas disponibles.
     */
    public String calculoCantidadEntradasDisponibles(Entrada ent);

    /**
     * Comprueba la existencia de entradas en el sistema.
     * @param admin el administrador que realiza la comprobación.
     * @return true si existen entradas en el sistema, false en caso contrario.
     */
    public boolean comprobarExistenciaEntradas(Administrador admin);

    /**
     * Obtiene los detalles de una entrada específica del sistema.
     * @param ent la entrada de la que se desean obtener los detalles.
     * @return la entrada con sus detalles.
     */
    public Entrada obtenerEntrada(Entrada ent);

    /**
     * Obtiene la cantidad de entradas vendidas para un evento específico.
     * @param codigoEntrada el código de la entrada para la que se desea obtener la cantidad de entradas vendidas.
     * @return la cantidad de entradas vendidas.
     */
    public int cantidadEntradasVendidas(String codigoEntrada);

    // Métodos para operaciones estadísticas

    /**
     * Calcula el número total de usuarios registrados en el sistema.
     * @return el número total de usuarios registrados.
     */
    public int calcularNumeroTotalUsuarios();

    /**
     * Calcula la edad media de los usuarios que han comprado entradas para un evento específico.
     * @param codigoEntrada el código de la entrada para la que se desea calcular la edad media.
     * @return una cadena de texto que representa la edad media de los usuarios.
     */
    public String calcularEdadMediaEntrada(String codigoEntrada);
}
