package pe.lindley.lanzador.to;

import com.google.gson.annotations.SerializedName;

public class ClienteResumenTO {
	
	@SerializedName("NATOUTNUM")
	private String ruc;
	
	@SerializedName("OUTNUM")
	private String codigoCliente;
	
	 @SerializedName("NUDNI")
     private String dni;
	
	@SerializedName("OUTLOC")
	private String codigoCda;
	
	@SerializedName("ADRLIN1")
	private String razonSocial;
	
	@SerializedName("ADRLIN2")
	private String direccion;
	
	
	@SerializedName("ADRLIN3")
	private String nombreCliente;
	
	@SerializedName("IsSuspe")
	private boolean suspendido;
	
	@SerializedName("ltd")
	private double latitud;
	
	@SerializedName("lng")
	private double longitud;
	
	@SerializedName("efrio")
    private boolean equiposFrio;
	
	@SerializedName("SUBTRDCHN")
	private String subCanal;
	
	@SerializedName("SUBTRDCHNDES")
	private String subCanalDes;
	
	@SerializedName("SALRTE")
	private String ruta;
	
	@SerializedName("SPRDAT")
	private String fechaSuspencion;
	
	@SerializedName("CREDAT")
	private String fechaCreacion;
	
	@SerializedName("UPDDAT")
	private String fechaActualizacion;

	public String getRuc() {
		return ruc;
	}

	public void setRuc(String ruc) {
		this.ruc = ruc;
	}

	public String getCodigoCliente() {
		return codigoCliente;
	}

	public void setCodigoCliente(String codigoCliente) {
		this.codigoCliente = codigoCliente;
	}

	public String getCodigoCda() {
		return codigoCda;
	}

	public void setCodigoCda(String codigoCda) {
		this.codigoCda = codigoCda;
	}

	public String getRazonSocial() {
		return razonSocial;
	}

	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getNombreCliente() {
		return nombreCliente;
	}

	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}

	public boolean isSuspendido() {
		return suspendido;
	}

	public void setSuspendido(boolean suspendido) {
		this.suspendido = suspendido;
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

	public boolean isEquiposFrio() {
		return equiposFrio;
	}

	public void setEquiposFrio(boolean equiposFrio) {
		this.equiposFrio = equiposFrio;
	}

	public String getSubCanal() {
		return subCanal;
	}

	public void setSubCanal(String subCanal) {
		this.subCanal = subCanal;
	}

	public String getSubCanalDes() {
		return subCanalDes;
	}

	public void setSubCanalDes(String subCanalDes) {
		this.subCanalDes = subCanalDes;
	}

	public String getRuta() {
		return ruta;
	}

	public void setRuta(String ruta) {
		this.ruta = ruta;
	}

	public String getFechaSuspencion() {
		return fechaSuspencion;
	}

	public void setFechaSuspencion(String fechaSuspencion) {
		this.fechaSuspencion = fechaSuspencion;
	}

	public String getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(String fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public String getFechaActualizacion() {
		return fechaActualizacion;
	}

	public void setFechaActualizacion(String fechaActualizacion) {
		this.fechaActualizacion = fechaActualizacion;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getDni() {
		return dni;
	}

}
