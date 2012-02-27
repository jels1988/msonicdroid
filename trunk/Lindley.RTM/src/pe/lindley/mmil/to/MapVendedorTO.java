package pe.lindley.mmil.to;

import com.google.gson.annotations.SerializedName;

public class MapVendedorTO {
	
	@SerializedName("COD")
	private int codigoCliente;

	@SerializedName("NOM")
	private String nombre;

	@SerializedName("DIR")
	private String direccion;

	@SerializedName("FEC")
	private String fechaReg;

	@SerializedName("LTC")
	private String latitudCliente;

	@SerializedName("LNC")
	private String longitudCliente;

	@SerializedName("LTP")
	private String latitudPhone;

	@SerializedName("LNP")
	private String longitudPhone;

	@SerializedName("TPR")
	private String tipoRegistro;

	@SerializedName("MON")
	private double montoPedido;

	@SerializedName("CDP")
	private String codigoPedido;

	@SerializedName("PPD")
	private String primerPedido;

	@SerializedName("UPD")
	private String ultimoPedido;

	@SerializedName("NPD")
	private int cantidadPedido;

	@SerializedName("CJF")
	private String cajaFisicas;

	@SerializedName("CJU")
	private String cajaUnitaria;

	@SerializedName("DIS")
	private String distancia;

	@SerializedName("TIE")
	private String tiempo;

	@SerializedName("NOP")
	private String numeroPedido;

	public int getCodigoCliente() {
		return codigoCliente;
	}

	public void setCodigoCliente(int codigoCliente) {
		this.codigoCliente = codigoCliente;
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

	public String getFechaReg() {
		return fechaReg;
	}

	public void setFechaReg(String fechaReg) {
		this.fechaReg = fechaReg;
	}

	public String getLatitudCliente() {
		return latitudCliente;
	}

	public void setLatitudCliente(String latitudCliente) {
		this.latitudCliente = latitudCliente;
	}

	public String getLongitudCliente() {
		return longitudCliente;
	}

	public void setLongitudCliente(String longitudCliente) {
		this.longitudCliente = longitudCliente;
	}

	public String getLatitudPhone() {
		return latitudPhone;
	}

	public void setLatitudPhone(String latitudPhone) {
		this.latitudPhone = latitudPhone;
	}

	public String getLongitudPhone() {
		return longitudPhone;
	}

	public void setLongitudPhone(String longitudPhone) {
		this.longitudPhone = longitudPhone;
	}

	public String getTipoRegistro() {
		return tipoRegistro;
	}

	public void setTipoRegistro(String tipoRegistro) {
		this.tipoRegistro = tipoRegistro;
	}

	public double getMontoPedido() {
		return montoPedido;
	}

	public void setMontoPedido(double montoPedido) {
		this.montoPedido = montoPedido;
	}

	public String getCodigoPedido() {
		return codigoPedido;
	}

	public void setCodigoPedido(String codigoPedido) {
		this.codigoPedido = codigoPedido;
	}

	public String getPrimerPedido() {
		return primerPedido;
	}

	public void setPrimerPedido(String primerPedido) {
		this.primerPedido = primerPedido;
	}

	public String getUltimoPedido() {
		return ultimoPedido;
	}

	public void setUltimoPedido(String ultimoPedido) {
		this.ultimoPedido = ultimoPedido;
	}

	public int getCantidadPedido() {
		return cantidadPedido;
	}

	public void setCantidadPedido(int cantidadPedido) {
		this.cantidadPedido = cantidadPedido;
	}

	public String getCajaFisicas() {
		return cajaFisicas;
	}

	public void setCajaFisicas(String cajaFisicas) {
		this.cajaFisicas = cajaFisicas;
	}

	public String getCajaUnitaria() {
		return cajaUnitaria;
	}

	public void setCajaUnitaria(String cajaUnitaria) {
		this.cajaUnitaria = cajaUnitaria;
	}

	public String getDistancia() {
		return distancia;
	}

	public void setDistancia(String distancia) {
		this.distancia = distancia;
	}

	public String getTiempo() {
		return tiempo;
	}

	public void setTiempo(String tiempo) {
		this.tiempo = tiempo;
	}

	public String getNumeroPedido() {
		return numeroPedido;
	}

	public void setNumeroPedido(String numeroPedido) {
		this.numeroPedido = numeroPedido;
	}

	
}
