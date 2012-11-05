package lindley.desarrolloxcliente.to;

import java.util.ArrayList;
import java.util.List;

import lindley.desarrolloxcliente.ConstantesApp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OportunidadTO {
	
	
	
	public OportunidadTO()
	{
		listaAccionesTrade = new ArrayList<AccionTradeTO>();
		concrecionActual = ConstantesApp.RESPUESTA_NO;
		cumplePrecioActual = ConstantesApp.RESPUESTA_NO;
		numeroSaboresActual = "2";
		soviActual = "0";
		
		puntosSugeridos="0";
		puntosGanados="0";
		puntosBonus="0";
		
		codigoAccionTrade="0";
		descAccionTrade="";
		
		confirmacion =  ConstantesApp.RESPUESTA_NO;
		origen =  ConstantesApp.OPORTUNIDAD_SISTEMA;
		estado =  ConstantesApp.OPORTUNIDAD_ABIERTA;
	}
	
	@Expose()
	public long oportunidadId;
	
	@Expose()
	public long productoId;
	public String fecha;
	public String codigoProducto;
	public String descripcionProducto;
	
	public String concrecion;
	public String concrecionActual;
	public int concrecionCumple;
	
	
	public String sovi;
	public String soviActual;
	public int soviCumple;
	
	
	 
	public String cumplePrecio;
	public String cumplePrecioActual;
	public int cumplePrecioCumple;

	
	public String numeroSabores;
	public String numeroSaboresActual;
	public int numeroSaboresCumple;
	
	
	public String puntosCocaCola;

	

	
	public String fechaOportunidad;
	
	@SerializedName("TRD")
	public List<AccionTradeTO> listaAccionesTrade;

	@SerializedName("CAT")
	public String accioneTrade;
	
	@SerializedName("DAT")
	public String descripcionAccioneTrade;
	

	@SerializedName("LEG")
	public String codigoLegacy;
	
	public String puntosSugeridos;
	public String puntosGanados;
	public String puntosBonus;
	
	
	public String descAccionTrade;
	public String codigoAccionTrade;
	public String confirmacion;
	public String origen;
	public String estado;
	
	public boolean seleccionado;
	
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

	public void setListaAccionesTrade(ArrayList<AccionTradeTO> listaAccionesTrade) {
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

	public String getDescripcionAccioneTrade() {
		return descripcionAccioneTrade;
	}

	public void setDescripcionAccioneTrade(String descripcionAccioneTrade) {
		this.descripcionAccioneTrade = descripcionAccioneTrade;
	}

	public boolean isSeleccionado() {
		return seleccionado;
	}

	public void setSeleccionado(boolean seleccionado) {
		this.seleccionado = seleccionado;
	}

}
