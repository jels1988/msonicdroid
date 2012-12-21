package lindley.desarrolloxcliente.to;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ClienteTO {
	
	
	@Expose(serialize = false)
	public long id;
	
	@Expose()
	@SerializedName("COD")
	public String codigo;
	
	@Expose()
	@SerializedName("FEC")
	public String fecha;	
	
	@Expose()
	@SerializedName("NOM")
	public String nombre;
	
	@Expose()
	@SerializedName("FRE") 
	public String frecuencia;
	
	@Expose()
	@SerializedName("ALC")
	public double alcance;
	
	@Expose()
	@SerializedName("FAL")
	public double falta;
	
	@Expose()
	@SerializedName("CLU")
	public String cluster;
	
	@Expose()
	@SerializedName("MC")
	public String mc;
	
	@Expose()
	@SerializedName("PUN")
	public int nroPuntos;
	
	@Expose()
	@SerializedName("SIG")
	public int nivelCanje;

	@Expose()
	@SerializedName("LAT")
	public double latitud;

	@Expose()
	@SerializedName("LNG")
	public double longitud;
	
	@Expose()
	@SerializedName("DIR")
	public String direccion;
	
	@Expose(serialize = false)
	public String tppro;
	
	
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
