package clase;

/**
 * La clase Cliente representa un cliente que extiende de la clase Usuario.
 * Esta clase permite almacenar y gestionar la información relacionada con el género del cliente.
 */
public class Cliente extends Usuario {

    // Atributos

    /**
     * Género del cliente.
     */
    private String genero;

    // Getters y setters

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }
}
