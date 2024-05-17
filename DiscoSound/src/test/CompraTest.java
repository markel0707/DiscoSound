package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import clase.Compra;

/**
 * La clase CompraTest contiene pruebas unitarias para la clase Compra.
 * Utiliza JUnit 5 para realizar las pruebas.
 */
class CompraTest {
    
    private Compra compra;

    /**
     * Configura el entorno antes de cada prueba.
     * @throws Exception si ocurre un error durante la configuración.
     */
    @BeforeEach
    void setUp() throws Exception {
        compra = new Compra();
    }

    /**
     * Prueba el método getMetodoPago de la clase Compra.
     * Verifica que el método getMetodoPago devuelve el método de pago correctamente
     * después de establecerlo con setMetodoPago.
     */
    @Test
    void getMetodoPagoTest() {
        compra.setMetodoPago("Bizum");
        assertEquals("Bizum", compra.getMetodoPago());
    }

    /**
     * Prueba el método getCantidadEntradas de la clase Compra.
     * Verifica que el método getCantidadEntradas devuelve la cantidad de entradas correctamente
     * después de establecerlo con setCantidadEntradas.
     */
    @Test
    void getCantidadEntradasTest() {
        compra.setCantidadEntradas(12);
        assertEquals(12, compra.getCantidadEntradas());
    }

    /**
     * Prueba el método getPrecioTotal de la clase Compra.
     * Verifica que el método getPrecioTotal devuelve el precio total correctamente
     * después de establecerlo con setPrecioTotal.
     */
    @Test
    void getPrecioTotalTest() {
        compra.setPrecioTotal(200);
        assertEquals(200, compra.getPrecioTotal());
    }

    /**
     * Prueba el método getTelefono de la clase Compra.
     * Verifica que el método getTelefono devuelve el teléfono correctamente
     * después de establecerlo con setTelefono.
     */
    @Test
    void getTelefonoTest() {
        compra.setTelefono(930449276);
        assertEquals(930449276, compra.getTelefono());
    }

}
