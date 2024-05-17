package clase;

import java.time.LocalDate;

/**
 * La clase Entrada representa una entrada para un evento en una discoteca.
 * Esta clase permite almacenar y gestionar la información relacionada con la entrada,
 * incluyendo el código de la entrada, el nombre del evento, el nombre del DJ, el precio,
 * la categoría, la cantidad de consumo, la fecha y la diferencia de precio para mujeres.
 */
public class Entrada {

    // Atributos

    /**
     * Código único de la entrada.
     */
    private String codigoEntrada;

    /**
     * Nombre del evento.
     */
    private String nombreEvento;

    /**
     * Nombre del DJ del evento.
     */
    private String nombreDJ;

    /**
     * Precio de la entrada.
     */
    private float precio;

    /**
     * Categoría de la entrada.
     */
    private String categoria;

    /**
     * Cantidad de consumo incluida con la entrada.
     */
    private int cantidadConsumo;

    /**
     * Fecha del evento.
     */
    private LocalDate fecha;

    /**
     * Diferencia de precio para mujeres.
     */
    private float diferenciaPrecioMujer;

    // Getters y setters

    public String getCodigoEntrada() {
        return codigoEntrada;
    }

    public void setCodigoEntrada(String codigoEntrada) {
        this.codigoEntrada = codigoEntrada;
    }

    public String getNombreEvento() {
        return nombreEvento;
    }

    public void setNombreEvento(String nombreEvento) {
        this.nombreEvento = nombreEvento;
    }

    public String getNombreDJ() {
        return nombreDJ;
    }

    public void setNombreDJ(String nombreDJ) {
        this.nombreDJ = nombreDJ;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getCantidadConsumo() {
        return cantidadConsumo;
    }

    public void setCantidadConsumo(int cantidadConsumo) {
        this.cantidadConsumo = cantidadConsumo;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public float getDiferenciaPrecioMujer() {
        return diferenciaPrecioMujer;
    }

    public void setDiferenciaPrecioMujer(float diferenciaPrecioMujer) {
        this.diferenciaPrecioMujer = diferenciaPrecioMujer;
    }

    @Override
    public String toString() {
        return "Entrada [codigoEntrada=" + codigoEntrada + ", nombreEvento=" + nombreEvento + ", nombreDJ=" + nombreDJ
                + ", precio=" + precio + ", categoria=" + categoria + ", cantidadConsumo=" + cantidadConsumo
                + ", fecha=" + fecha + ", diferenciaPrecioMujer=" + diferenciaPrecioMujer + "]";
    }
}
