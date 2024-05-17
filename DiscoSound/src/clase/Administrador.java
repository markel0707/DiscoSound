package clase;

/**
 * La clase Administrador representa un administrador que extiende de la clase Usuario.
 * Esta clase permite la gestión de la discoteca por parte del administrador.
 */

public class Administrador extends Usuario {
    private String nomDiscoteca;

    public String getNomDiscoteca() {
        return nomDiscoteca;
    }
    
    public void setNomDiscoteca(String nomDiscoteca) {
        this.nomDiscoteca = nomDiscoteca;
    }
}
