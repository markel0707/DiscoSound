package clase;

/**
 * La clase Compra representa una compra realizada por un cliente.
 * Esta clase permite almacenar y gestionar la información relacionada con la compra,
 * incluyendo el método de pago, la cantidad de entradas, el precio total y el teléfono de contacto.
 */
public class Compra {

    // Atributos

    /**
     * Método de pago utilizado para la compra.
     */
    private String metodoPago;

    /**
     * Cantidad de entradas compradas.
     */
    private int cantidadEntradas;

    /**
     * Precio total de la compra.
     */
    private float precioTotal;

    /**
     * Teléfono de contacto asociado a la compra.
     */
    private int telefono;

    // Getters y setters

    public String getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(String metodoPago) {
        this.metodoPago = metodoPago;
    }

    public int getCantidadEntradas() {
        return cantidadEntradas;
    }

    public void setCantidadEntradas(int cantidadEntradas) {
        this.cantidadEntradas = cantidadEntradas;
    }

    public float getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(float precioTotal) {
        this.precioTotal = precioTotal;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }
}
