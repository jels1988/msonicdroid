package pe.lindley.red.to;

import com.google.gson.annotations.SerializedName;

public class DatosFichaTO {

	@SerializedName("CIU")
	private String ciudad;
	
	@SerializedName("CLU")
	private String cluster;
	
	@SerializedName("COD")
	private String codigo;
	
	@SerializedName("DIR")
	private String direccion;
	
	@SerializedName("DST")
	private String distribuidor;
	
	@SerializedName("DIS")
	private String distrito;
	
	@SerializedName("VRP")
	private short indNoVRP;
	
	@SerializedName("NOM")
	private String nombre;
	
	@SerializedName("RUT")
	private String ruta;
	
	@SerializedName("SEG")
	private String segmento;
	
	@SerializedName("TCL")
	private String tipoCliente;

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getCluster() {
		return cluster;
	}

	public void setCluster(String cluster) {
		this.cluster = cluster;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getDistribuidor() {
		return distribuidor;
	}

	public void setDistribuidor(String distribuidor) {
		this.distribuidor = distribuidor;
	}

	public String getDistrito() {
		return distrito;
	}

	public void setDistrito(String distrito) {
		this.distrito = distrito;
	}

	public short getIndNoVRP() {
		return indNoVRP;
	}

	public void setIndNoVRP(short indNoVRP) {
		this.indNoVRP = indNoVRP;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getRuta() {
		return ruta;
	}

	public void setRuta(String ruta) {
		this.ruta = ruta;
	}

	public String getSegmento() {
		return segmento;
	}

	public void setSegmento(String segmento) {
		this.segmento = segmento;
	}

	public String getTipoCliente() {
		return tipoCliente;
	}

	public void setTipoCliente(String tipoCliente) {
		this.tipoCliente = tipoCliente;
	}
}
