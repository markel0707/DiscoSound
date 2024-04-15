package clases;

import java.time.LocalDate;

public class Entrada {
	
	//Atributos
	private String codigoEntrada;
    private String nombreEvento;
    private String nombreDJ;
    private float precio;
    private String categoria;
    private int cantidadConsumo;
    private LocalDate fecha;
    private float diferenciaPrecioMujer;

    
    //Getters y setters 
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

}
