package clase;

import java.time.LocalDate;

/**
 * La clase Usuario representa a un usuario del sistema.
 * Esta clase permite almacenar y gestionar la información personal del usuario,
 * incluyendo su DNI, nombre, apellido, nombre de usuario, fecha de nacimiento, email y contraseña.
 */
public class Usuario {

    // Atributos

    /**
     * Documento Nacional de Identidad (DNI) del usuario.
     */
    private String dni;

    /**
     * Nombre del usuario.
     */
    private String nombre;

    /**
     * Apellido del usuario.
     */
    private String apellido;

    /**
     * Nombre de usuario.
     */
    private String nomUsu;

    /**
     * Fecha de nacimiento del usuario.
     */
    private LocalDate fechaNac;

    /**
     * Email del usuario.
     */
    private String email;

    /**
     * Contraseña del usuario.
     */
    private String contraseina;

    // Getters y setters

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNomUsu() {
        return nomUsu;
    }

    public void setNomUsu(String nomUsu) {
        this.nomUsu = nomUsu;
    }

    public LocalDate getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(LocalDate fechaNac) {
        this.fechaNac = fechaNac;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContraseina() {
        return contraseina;
    }

    public void setContraseina(String contraseina) {
        this.contraseina = contraseina;
    }
}
