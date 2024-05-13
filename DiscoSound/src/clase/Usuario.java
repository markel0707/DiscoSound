package clase;

import java.time.LocalDate;

public class Usuario {

	// Atributos

	private String dni;
	private String nombre;
	private String apellido;
	private String nomUsu;
	private LocalDate fechaNac;
	private String email;
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
