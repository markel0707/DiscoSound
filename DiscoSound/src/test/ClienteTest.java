package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import clase.Cliente;

/**
 * La clase ClienteTest contiene pruebas unitarias para la clase Cliente.
 * Utiliza JUnit 5 para realizar las pruebas.
 */
class ClienteTest {

    private Cliente cli;

    /**
     * Configura el entorno antes de cada prueba.
     * @throws Exception si ocurre un error durante la configuración.
     */
    @BeforeEach
    void setUp() throws Exception {
        cli = new Cliente();
    }

    /**
     * Prueba el método getGenero de la clase Cliente.
     * Verifica que el método getGenero devuelve el género correctamente
     * después de establecerlo con setGenero.
     */
    @Test
    void getGeneroTest() {
        cli.setGenero("Mujer");
        assertEquals("Mujer", cli.getGenero());
    }

}
