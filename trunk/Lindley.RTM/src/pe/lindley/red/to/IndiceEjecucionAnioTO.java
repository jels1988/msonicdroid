package pe.lindley.red.to;

import com.google.gson.annotations.SerializedName;

public class IndiceEjecucionAnioTO {
	
	@SerializedName("ANI")
	private int anio;
	
	@SerializedName("EJE")
	private double ejecucion;
	
	@SerializedName("INV")
	private double inventario;
	
	@SerializedName("POS")
	private double posicion;
	
	@SerializedName("PRE")
	private double presentacion;
	
	@SerializedName("PJA")
	private double puntajeAdicional;
	
	public int getAnio() {
		return anio;
	}
	public void setAnio(int anio) {
		this.anio = anio;
	}
	public double getEjecucion() {
		return ejecucion;
	}
	public void setEjecucion(double ejecucion) {
		this.ejecucion = ejecucion;
	}
	public double getInventario() {
		return inventario;
	}
	public void setInventario(double inventario) {
		this.inventario = inventario;
	}
	public double getPosicion() {
		return posicion;
	}
	public void setPosicion(double posicion) {
		this.posicion = posicion;
	}
	public double getPresentacion() {
		return presentacion;
	}
	public void setPresentacion(double presentacion) {
		this.presentacion = presentacion;
	}
	public double getPuntajeAdicional() {
		return puntajeAdicional;
	}
	public void setPuntajeAdicional(double puntajeAdicional) {
		this.puntajeAdicional = puntajeAdicional;
	}
}
