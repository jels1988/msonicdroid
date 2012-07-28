package lindley.desarrolloxcliente.to;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ClienteTO {
	
	
	@Expose(serialize = false)
	public long id;
	
	@SerializedName("COD")
	public String codigo;
	
	@SerializedName("FEC")
	public String fecha;	
	
	@SerializedName("NOM")
	public String nombre;
	
	@SerializedName("FRE") 
	public String frecuencia;
	
	@SerializedName("ALC")
	public double alcance;
	
	@SerializedName("FAL")
	public double falta;
	
	@SerializedName("CLU")
	public String cluster;
	
	@SerializedName("MC")
	public String mc;
	
	@SerializedName("PUN")
	public int nroPuntos;
	
	@SerializedName("SIG")
	public int nivelCanje;

	@SerializedName("LAT")
	public double latitud;

	@SerializedName("LNG")
	public double longitud;
	
	@SerializedName("DIR")
	public String direccion;
	
	
	@Expose(serialize = false)
	public int evaluacionesAbiertas;
	        
	
	/*
	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getFrecuencia() {
		return frecuencia;
	}

	public void setFrecuencia(double frecuencia) {
		this.frecuencia = frecuencia;
	}

	public String getAlcance() {
		return alcance;
	}

	public void setAlcance(String alcance) {
		this.alcance = alcance;
	}

	public String getFalta() {
		return falta;
	}

	public void setFalta(String falta) {
		this.falta = falta;
	}

	public String getCluster() {
		return cluster;
	}

	public void setCluster(String cluster) {
		this.cluster = cluster;
	}

	public String getMc() {
		return mc;
	}

	public void setMc(String mc) {
		this.mc = mc;
	}

	public String getNroPuntos() {
		return nroPuntos;
	}

	public void setNroPuntos(String nroPuntos) {
		this.nroPuntos = nroPuntos;
	}

	public String getNivelCanje() {
		return nivelCanje;
	}

	public void setNivelCanje(String nivelCanje) {
		this.nivelCanje = nivelCanje;
	}

	public double getLatitud() {
		return latitud;
	}

	public void setLatitud(double latitud) {
		this.latitud = latitud;
	}

	public double getLongitud() {
		return longitud;
	}

	public void setLongitud(double longitud) {
		this.longitud = longitud;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}*/
		
}
