package lindley.desarrolloxcliente.to;

import com.google.gson.annotations.SerializedName;

public class ClienteTO {
	
	@SerializedName("COD")
	private String codigo;
	
	@SerializedName("FEC")
	private String fecha;	
	
	@SerializedName("NOM")
	private String nombre;
	
	@SerializedName("FRE")
	private String frecuencia;
	
	@SerializedName("ALC")
	private String alcance;
	
	@SerializedName("FAL")
	private String falta;
	
	@SerializedName("CLU")
	private String cluster;
	
	@SerializedName("MC")
	private String mc;
	
	@SerializedName("PUN")
	private String nroPuntos;
	
	@SerializedName("SIG")
	private String nivelCanje;

	@SerializedName("LAT")
	private double latitud;

	@SerializedName("LNG")
	private double longitud;
	
	@SerializedName("DIR")
	private String direccion;
	        
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

	public String getFrecuencia() {
		return frecuencia;
	}

	public void setFrecuencia(String frecuencia) {
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
	}
		
}
