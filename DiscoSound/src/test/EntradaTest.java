package test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import clase.Entrada;

/**
 * La clase EntradaTest contiene pruebas unitarias para la clase Entrada.
 * Utiliza JUnit 5 para realizar las pruebas.
 */
class EntradaTest {

    private Entrada entrada;

    /**
     * Configura el entorno antes de cada prueba.
     * @throws Exception si ocurre un error durante la configuración.
     */
    @BeforeEach
    void setUp() throws Exception {
        entrada = new Entrada();
    }

    /**
     * Prueba el método getCodigoEntrada de la clase Entrada.
     * Verifica que el método getCodigoEntrada devuelve el código de entrada correctamente
     * después de establecerlo con setCodigoEntrada.
     */
    @Test
    void getCodigoEntradaTest() {
        entrada.setCodigoEntrada("2222");
        assertEquals("2222", entrada.getCodigoEntrada());
    }

    /**
     * Prueba el método getNombreEvento de la clase Entrada.
     * Verifica que el método getNombreEvento devuelve el nombre del evento correctamente
     * después de establecerlo con setNombreEvento.
     */
    @Test
    void getNombreEventoTest() {
        entrada.setNombreEvento("Dembow");
        assertEquals("Dembow", entrada.getNombreEvento());
    }

    /**
     * Prueba el método getNombreDJ de la clase Entrada.
     * Verifica que el método getNombreDJ devuelve el nombre del DJ correctamente
     * después de establecerlo con setNombreDJ.
     */
    @Test
    void getNombreDjTest() {
        entrada.setNombreDJ("DJ Jon");
        assertEquals("DJ Jon", entrada.getNombreDJ());
    }

    /**
     * Prueba el método getPrecio de la clase Entrada.
     * Verifica que el método getPrecio devuelve el precio correctamente
     * después de establecerlo con setPrecio.
     */
    @Test
    void getPrecioTest() {
        entrada.setPrecio(12);
        assertEquals(12, entrada.getPrecio());
    }

    /**
     * Prueba el método getCategoria de la clase Entrada.
     * Verifica que el método getCategoria devuelve la categoría correctamente
     * después de establecerla con setCategoria.
     */
    @Test
    void getCategoriaTest() {
        entrada.setCategoria("Vip");
        assertEquals("Vip", entrada.getCategoria());
    }

    /**
     * Prueba el método getCantidadConsumo de la clase Entrada.
     * Verifica que el método getCantidadConsumo devuelve la cantidad de consumo correctamente
     * después de establecerla con setCantidadConsumo.
     */
    @Test
    void getCantidadConsumoTest() {
        entrada.setCantidadConsumo(12);
        assertEquals(12, entrada.getCantidadConsumo());
    }

    /**
     * Prueba el método getFecha de la clase Entrada.
     * Verifica que el método getFecha devuelve la fecha correctamente
     * después de establecerla con setFecha.
     */
    @Test
    void getFechaTest() {
        entrada.setFecha(LocalDate.of(2024, 5, 17));
        assertEquals(LocalDate.of(2024, 5, 17), entrada.getFecha());
    }

    /**
     * Prueba el método getDiferenciaPrecioMujer de la clase Entrada.
     * Verifica que el método getDiferenciaPrecioMujer devuelve la diferencia de precio para mujeres correctamente
     * después de establecerla con setDiferenciaPrecioMujer.
     */
    @Test
    void getDiferenciaPrecioMujerTest() {
        entrada.setDiferenciaPrecioMujer(12);
        assertEquals(12, entrada.getDiferenciaPrecioMujer());
    }
}
