package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import clase.Discoteca;
import clase.Entrada;

/**
 * La clase DiscotecaTest contiene pruebas unitarias para la clase Discoteca.
 * Utiliza JUnit 5 para realizar las pruebas.
 */
class DiscotecaTest {
    
    private Discoteca disco;

    /**
     * Configura el entorno antes de cada prueba.
     * @throws Exception si ocurre un error durante la configuración.
     */
    @BeforeEach
    void setUp() throws Exception {
        disco = new Discoteca();
    }

    /**
     * Prueba el constructor de la clase Discoteca.
     * Verifica que una nueva instancia de Discoteca no sea nula.
     */
    @Test
    void constructorTest() {
        assertNotNull(disco);
    }

    /**
     * Prueba el método getCodigo de la clase Discoteca.
     * Verifica que el método getCodigo devuelve el código correctamente
     * después de establecerlo con setCodigo.
     */
    @Test
    void getCodigoTest() {
        disco.setCodigo("2222");
        assertEquals("2222", disco.getCodigo());
    }

    /**
     * Prueba el método getNombre de la clase Discoteca.
     * Verifica que el método getNombre devuelve el nombre correctamente
     * después de establecerlo con setNombre.
     */
    @Test
    void getNombreTest() {
        disco.setNombre("Moma");
        assertEquals("Moma", disco.getNombre());
    }

    /**
     * Prueba el método getDireccion de la clase Discoteca.
     * Verifica que el método getDireccion devuelve la dirección correctamente
     * después de establecerlo con setDireccion.
     */
    @Test
    void getDireccionTest() {
        disco.setDireccion("Licenciado poza");
        assertEquals("Licenciado poza", disco.getDireccion());
    }

    /**
     * Prueba el método getAforo de la clase Discoteca.
     * Verifica que el método getAforo devuelve el aforo correctamente
     * después de establecerlo con setAforo.
     */
    @Test
    void getAforoTest() {
        disco.setAforo(200);
        assertEquals(200, disco.getAforo());
    }

    /**
     * Prueba el método getEntradas de la clase Discoteca.
     * Verifica que el método getEntradas devuelve la lista de entradas correctamente
     * después de establecerla con setEntradas.
     */
    @Test
    void getEntradasTest() {
        List<Entrada> entrada = null;
        disco.setEntradas(entrada);
        assertEquals(entrada, disco.getEntradas());
    }
}
