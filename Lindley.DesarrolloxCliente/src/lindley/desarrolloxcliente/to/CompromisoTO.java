package lindley.desarrolloxcliente.to;

import com.google.gson.annotations.SerializedName;

public class CompromisoTO {

	@SerializedName("COD")
	private String CodigoRegistro;

	@SerializedName("FEC")
	private String Fecha;

	@SerializedName("CPRO")
	private String CodigoProducto;

	@SerializedName("DPRO")
	private String DescripcionProducto;

	@SerializedName("CNR") 
	private String Concrecion;

	@SerializedName("CCNR")
	private String ConfirmacionConcrecion;

	@SerializedName("SOVI")
	private String Sovi;

	@SerializedName("SOVICM")
	private String SoviCompromiso;
	
	@SerializedName("CSOVI")
	private String ConfirmacionSovi;

	@SerializedName("RPRE")
	private String CumplePrecio;
	
	@SerializedName("RPRECM")
	private String CumplePrecioCompromiso;

	@SerializedName("CRPRE")
	private String ConfirmacionCumplePrecio;

	@SerializedName("NSB")
	private String NumeroSabores;

	@SerializedName("CNSB")
	private String ConfirmacionNumeroSabores;

	@SerializedName("PCC")
	private String PuntosCocaCola;

	@SerializedName("PBN")
	private String PuntosBonus;

	@SerializedName("CACC")
	private String CodigoAccion;

	@SerializedName("DACC")
	private String DescripcionAccion;

	@SerializedName("CUM")
	private String Cumplio;

	@SerializedName("FECC")
	private String FechaCompromiso;

	@SerializedName("EST")
	private String Estado;
	
	@SerializedName("AGR")
	private String agrupacion;
	
	@SerializedName("ORG")
	private String origen;

	public String getCodigoRegistro() {
		return CodigoRegistro;
	}

	public void setCodigoRegistro(String codigoRegistro) {
		CodigoRegistro = codigoRegistro;
	}

	public String getFecha() {
		return Fecha;
	}

	public void setFecha(String fecha) {
		Fecha = fecha;
	}

	public String getCodigoProducto() {
		return CodigoProducto;
	}

	public void setCodigoProducto(String codigoProducto) {
		CodigoProducto = codigoProducto;
	}

	public String getDescripcionProducto() {
		return DescripcionProducto;
	}

	public void setDescripcionProducto(String descripcionProducto) {
		DescripcionProducto = descripcionProducto;
	}

	public String getConcrecion() {
		return Concrecion;
	}

	public void setConcrecion(String concrecion) {
		Concrecion = concrecion;
	}

	public String getConfirmacionConcrecion() {
		return ConfirmacionConcrecion;
	}

	public void setConfirmacionConcrecion(String confirmacionConcrecion) {
		ConfirmacionConcrecion = confirmacionConcrecion;
	}

	public String getSovi() {
		return Sovi;
	}

	public void setSovi(String sovi) {
		Sovi = sovi;
	}

	public String getConfirmacionSovi() {
		return ConfirmacionSovi;
	}

	public void setConfirmacionSovi(String confirmacionSovi) {
		ConfirmacionSovi = confirmacionSovi;
	}

	public String getCumplePrecio() {
		return CumplePrecio;
	}

	public void setCumplePrecio(String cumplePrecio) {
		CumplePrecio = cumplePrecio;
	}

	public String getConfirmacionCumplePrecio() {
		return ConfirmacionCumplePrecio;
	}

	public void setConfirmacionCumplePrecio(String confirmacionCumplePrecio) {
		ConfirmacionCumplePrecio = confirmacionCumplePrecio;
	}

	public String getNumeroSabores() {
		return NumeroSabores;
	}

	public void setNumeroSabores(String numeroSabores) {
		NumeroSabores = numeroSabores;
	}

	public String getConfirmacionNumeroSabores() {
		return ConfirmacionNumeroSabores;
	}

	public void setConfirmacionNumeroSabores(String confirmacionNumeroSabores) {
		ConfirmacionNumeroSabores = confirmacionNumeroSabores;
	}

	public String getPuntosCocaCola() {
		return PuntosCocaCola;
	}

	public void setPuntosCocaCola(String puntosCocaCola) {
		PuntosCocaCola = puntosCocaCola;
	}

	public String getPuntosBonus() {
		return PuntosBonus;
	}

	public void setPuntosBonus(String puntosBonus) {
		PuntosBonus = puntosBonus;
	}

	public String getCodigoAccion() {
		return CodigoAccion;
	}

	public void setCodigoAccion(String codigoAccion) {
		CodigoAccion = codigoAccion;
	}

	public String getDescripcionAccion() {
		return DescripcionAccion;
	}

	public void setDescripcionAccion(String descripcionAccion) {
		DescripcionAccion = descripcionAccion;
	}

	public String getCumplio() {
		return Cumplio;
	}

	public void setCumplio(String cumplio) {
		Cumplio = cumplio;
	}

	public String getFechaCompromiso() {
		return FechaCompromiso;
	}

	public void setFechaCompromiso(String fechaCompromiso) {
		FechaCompromiso = fechaCompromiso;
	}

	public String getEstado() {
		return Estado;
	}

	public void setEstado(String estado) {
		Estado = estado;
	}

	public String getAgrupacion() {
		return agrupacion;
	}

	public void setAgrupacion(String agrupacion) {
		this.agrupacion = agrupacion;
	}

	public String getOrigen() {
		return origen;
	}

	public void setOrigen(String origen) {
		this.origen = origen;
	}

	public String getSoviCompromiso() {
		return SoviCompromiso;
	}

	public void setSoviCompromiso(String soviCompromiso) {
		SoviCompromiso = soviCompromiso;
	}

	public String getCumplePrecioCompromiso() {
		return CumplePrecioCompromiso;
	}

	public void setCumplePrecioCompromiso(String cumplePrecioCompromiso) {
		CumplePrecioCompromiso = cumplePrecioCompromiso;
	}
}
