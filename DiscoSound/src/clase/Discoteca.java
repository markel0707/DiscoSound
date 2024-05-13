package clase;

import java.util.ArrayList;
import java.util.List;

public class Discoteca {

	// Atributos

	private String codigo;
	private String nombre;
	private String direccion;
	private int aforo;
	private List<Entrada> entradas;

	// Constructor

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
