package controlador;

import modelo.DaoImplementacionBD;
import vista.VInicio;

/**
 * La clase Main inicia la aplicación y muestra la ventana de inicio.
 */
public class Main {

    /**
     * Método principal que inicia la aplicación.
     * @param args los argumentos de la línea de comandos (no se utilizan en esta aplicación).
     */
    public static void main(String[] args) {
        // Se instancia el DAO utilizando la implementación de base de datos.
        Dao dao = new DaoImplementacionBD();
        // Se crea la ventana de inicio y se hace visible.
        VInicio ini = new VInicio(dao);
        ini.setVisible(true);
    }

}
