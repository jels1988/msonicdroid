package pe.lindley.sacc.to;

import com.google.gson.annotations.SerializedName;

public class ContactoTO {
	
	@SerializedName("IDC")
	private int idContacto;

	@SerializedName("NDIA")
	private int numDias;

	@SerializedName("FRG")
	private String fechaRegistro;

	@SerializedName("HRG")
	private String horaRegistro;

	@SerializedName("TCT")
	private String tipoContacto;

	@SerializedName("ICS")
	private String clienteConsumidor;

	@SerializedName("NOM")
	private String nombre;

	@SerializedName("RES")
	private String responsable;

	@SerializedName("SIT")
	private String situacion;

	@SerializedName("IDT")
	private int idTipoContacto;

	@SerializedName("TMA")
	private String tema;

	@SerializedName("STM")
	private String subTema;

	@SerializedName("ARE")
	private String area;

	@SerializedName("DIR")
	private String direccion;

	@SerializedName("PLA")
	private String planta;

	@SerializedName("IART")
	private int iDArticulo;

	@SerializedName("ART")
	private String articulo;

	public int getIdContacto() {
		return idContacto;
	}

	public void setIdContacto(int idContacto) {
		this.idContacto = idContacto;
	}

	public int getNumDias() {
		return numDias;
	}

	public void setNumDias(int numDias) {
		this.numDias = numDias;
	}

	public String getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(String fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public String getHoraRegistro() {
		return horaRegistro;
	}

	public void setHoraRegistro(String horaRegistro) {
		this.horaRegistro = horaRegistro;
	}

	public String getTipoContacto() {
		return tipoContacto;
	}

	public void setTipoContacto(String tipoContacto) {
		this.tipoContacto = tipoContacto;
	}

	public String getClienteConsumidor() {
		return clienteConsumidor;
	}

	public void setIdClienteConsumidor(String clienteConsumidor) {
		this.clienteConsumidor = clienteConsumidor;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getResponsable() {
		return responsable;
	}

	public void setResponsable(String responsable) {
		this.responsable = responsable;
	}

	public String getSituacion() {
		return situacion;
	}

	public void setSituacion(String situacion) {
		this.situacion = situacion;
	}

	public int getIdTipoContacto() {
		return idTipoContacto;
	}

	public void setIdTipoContacto(int idTipoContacto) {
		this.idTipoContacto = idTipoContacto;
	}

	public String getTema() {
		return tema;
	}

	public void setTema(String tema) {
		this.tema = tema;
	}

	public String getSubTema() {
		return subTema;
	}

	public void setSubTema(String subTema) {
		this.subTema = subTema;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getPlanta() {
		return planta;
	}

	public void setPlanta(String planta) {
		this.planta = planta;
	}

	public int getiDArticulo() {
		return iDArticulo;
	}

	public void setiDArticulo(int iDArticulo) {
		this.iDArticulo = iDArticulo;
	}

	public String getArticulo() {
		return articulo;
	}

	public void setArticulo(String articulo) {
		this.articulo = articulo;
	}


}
