package pe.lindley.red.to;

import com.google.gson.annotations.SerializedName;

public class OrdenTrabajoTO {

	@SerializedName("COD")
	private String codigo;

	@SerializedName("RUT")
	private String ruta;

	@SerializedName("DIA")
	private String dia;

	@SerializedName("OTR")
	private String ordenTrabajo;

	@SerializedName("PEJ")
	private double puntosEjecutados;

	@SerializedName("PMX")
	private double puntosMaximos;

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getRuta() {
		return ruta;
	}

	public void setRuta(String ruta) {
		this.ruta = ruta;
	}

	public String getDia() {
		return dia;
	}

	public void setDia(String dia) {
		this.dia = dia;
	}

	public String getOrdenTrabajo() {
		return ordenTrabajo;
	}

	public void setOrdenTrabajo(String ordenTrabajo) {
		this.ordenTrabajo = ordenTrabajo;
	}

	public double getPuntosEjecutados() {
		return puntosEjecutados;
	}

	public void setPuntosEjecutados(double puntosEjecutados) {
		this.puntosEjecutados = puntosEjecutados;
	}

	public double getPuntosMaximos() {
		return puntosMaximos;
	}

	public void setPuntosMaximos(double puntosMaximos) {
		this.puntosMaximos = puntosMaximos;
	}
}
