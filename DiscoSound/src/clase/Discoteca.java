package clase;

import java.util.ArrayList;
import java.util.List;

/**
 * La clase Discoteca representa una discoteca con su información básica y entradas asociadas.
 * Esta clase permite almacenar y gestionar la información de la discoteca, incluyendo su código,
 * nombre, dirección, aforo y una lista de entradas.
 */
public class Discoteca {

    // Atributos

    /**
     * Código único de la discoteca.
     */
    private String codigo;

    /**
     * Nombre de la discoteca.
     */
    private String nombre;

    /**
     * Dirección de la discoteca.
     */
    private String direccion;

    /**
     * Capacidad máxima de la discoteca.
     */
    private int aforo;

    /**
     * Lista de entradas asociadas a la discoteca.
     */
    private List<Entrada> entradas;

    // Constructor

    /**
     * Crea una nueva instancia de Discoteca con una lista vacía de entradas.
     */
    public Discoteca() {
        entradas = new ArrayList<>();
    }

    // Getters y setters

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getAforo() {
        return aforo;
    }

    public void setAforo(int aforo) {
        this.aforo = aforo;
    }

    public List<Entrada> getEntradas() {
        return entradas;
    }

    public void setEntradas(List<Entrada> entradas) {
        this.entradas = entradas;
    }
}
