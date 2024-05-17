package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import clase.Administrador;

/**
 * La clase AdministradorTest contiene pruebas unitarias para la clase Administrador.
 * Utiliza JUnit 5 para realizar las pruebas.
 */
class AdministradorTest {

    private Administrador admin;

    /**
     * Configura el entorno antes de cada prueba.
     * @throws Exception si ocurre un error durante la configuración.
     */
    @BeforeEach
    void setUp() throws Exception {
        admin = new Administrador();
    }

    /**
     * Prueba el método getNomDiscoteca de la clase Administrador.
     * Verifica que el método getNomDiscoteca devuelve el nombre de la discoteca correctamente
     * después de establecerlo con setNomDiscoteca.
     */
    @Test
    void getNomDiscotecaTest() {
        admin.setNomDiscoteca("moma");
        assertEquals("moma", admin.getNomDiscoteca());
    }
}
