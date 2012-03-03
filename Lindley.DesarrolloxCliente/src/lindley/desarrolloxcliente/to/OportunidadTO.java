package lindley.desarrolloxcliente.to;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class OportunidadTO {

	@SerializedName("FEC")
	private String fecha;

	@SerializedName("CPRO")
	private String codigoProducto;

	@SerializedName("DPRO")
	private String descripcionProducto;

	@SerializedName("CNR")
	private String concrecion;

	@SerializedName("SOVI")
	private String sovi;

	@SerializedName("RPRE") 
	private String cumplePrecio;

	@SerializedName("NSB")
	private String numeroSabores;

	@SerializedName("PCC")
	private String puntosCocaCola;

	@SerializedName("PBN")
	private String puntosBonus;

	@SerializedName("FECO")
	private String fechaOportunidad;
	
	@SerializedName("TRD")
	private List<AccionTradeTO> listaAccionesTrade;

	private String accioneTrade;
	
	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getCodigoProducto() {
		return codigoProducto;
	}

	public void setCodigoProducto(String codigoProducto) {
		this.codigoProducto = codigoProducto;
	}

	public String getDescripcionProducto() {
		return descripcionProducto;
	}

	public void setDescripcionProducto(String descripcionProducto) {
		this.descripcionProducto = descripcionProducto;
	}

	public String getConcrecion() {
		return concrecion;
	}

	public void setConcrecion(String concrecion) {
		this.concrecion = concrecion;
	}

	public String getSovi() {
		return sovi;
	}

	public void setSovi(String sovi) {
		this.sovi = sovi;
	}

	public String getCumplePrecio() {
		return cumplePrecio;
	}

	public void setCumplePrecio(String cumplePrecio) {
		this.cumplePrecio = cumplePrecio;
	}

	public String getNumeroSabores() {
		return numeroSabores;
	}

	public void setNumeroSabores(String numeroSabores) {
		this.numeroSabores = numeroSabores;
	}

	public String getPuntosCocaCola() {
		return puntosCocaCola;
	}

	public void setPuntosCocaCola(String puntosCocaCola) {
		this.puntosCocaCola = puntosCocaCola;
	}

	public String getPuntosBonus() {
		return puntosBonus;
	}

	public void setPuntosBonus(String puntosBonus) {
		this.puntosBonus = puntosBonus;
	}

	public List<AccionTradeTO> getListaAccionesTrade() {
		return listaAccionesTrade;
	}

	public void setListaAccionesTrade(List<AccionTradeTO> listaAccionesTrade) {
		this.listaAccionesTrade = listaAccionesTrade;
	}

	public String getFechaOportunidad() {
		return fechaOportunidad;
	}

	public void setFechaOportunidad(String fechaOportunidad) {
		this.fechaOportunidad = fechaOportunidad;
	}
	
	public String getAccioneTrade() {
		return accioneTrade;
	}

	public void setAccioneTrade(String accioneTrade) {
		this.accioneTrade = accioneTrade;
	}

}
