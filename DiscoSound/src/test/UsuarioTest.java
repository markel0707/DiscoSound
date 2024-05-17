package test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import clase.Usuario;

/**
 * La clase UsuarioTest contiene pruebas unitarias para la clase Usuario.
 * Utiliza JUnit 5 para realizar las pruebas.
 */
class UsuarioTest {

    private Usuario usu;

    /**
     * Configura el entorno antes de cada prueba.
     * @throws Exception si ocurre un error durante la configuración.
     */
    @BeforeEach
    void setUp() throws Exception {
        usu = new Usuario();
    }

    /**
     * Prueba el método getDni de la clase Usuario.
     * Verifica que el método getDni devuelve el DNI correctamente
     * después de establecerlo con setDni.
     */
    @Test
    void getDniTest() {
        usu.setDni("20978340V");
        assertEquals("20978340V", usu.getDni());
    }

    /**
     * Prueba el método getNombre de la clase Usuario.
     * Verifica que el método getNombre devuelve el nombre correctamente
     * después de establecerlo con setNombre.
     */
    @Test
    void getNombreTest() {
        usu.setNombre("Paco");
        assertEquals("Paco", usu.getNombre());
    }

    /**
     * Prueba el método getApellido de la clase Usuario.
     * Verifica que el método getApellido devuelve el apellido correctamente
     * después de establecerlo con setApellido.
     */
    @Test
    void getApellidoTest() {
        usu.setApellido("Gonzalez");
        assertEquals("Gonzalez", usu.getApellido());
    }

    /**
     * Prueba el método getFechaNac de la clase Usuario.
     * Verifica que el método getFechaNac devuelve la fecha de nacimiento correctamente
     * después de establecerla con setFechaNac.
     */
    @Test
    void getFechaNacTest() {
        usu.setFechaNac(LocalDate.of(2005, 5, 14));
        assertEquals(LocalDate.of(2005, 5, 14), usu.getFechaNac());
    }

    /**
     * Prueba el método getEmail de la clase Usuario.
     * Verifica que el método getEmail devuelve el email correctamente
     * después de establecerlo con setEmail.
     */
    @Test
    void getEmailTest() {
        usu.setEmail("andoni@gmail.com");
        assertEquals("andoni@gmail.com", usu.getEmail());
    }

    /**
     * Prueba el método getContraseina de la clase Usuario.
     * Verifica que el método getContraseina devuelve la contraseña correctamente
     * después de establecerla con setContraseina.
     */
    @Test
    void getContraseinaTest() {
        usu.setContraseina("1111");
        assertEquals("1111", usu.getContraseina());
    }
}
