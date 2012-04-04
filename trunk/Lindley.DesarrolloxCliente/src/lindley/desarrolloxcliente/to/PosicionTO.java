package lindley.desarrolloxcliente.to;

import com.google.gson.annotations.SerializedName;

public class PosicionTO {

	public PosicionTO()
	{
		seleccionado = true;
	}
	
	@SerializedName("CVAR")
	private String codigoVariable;
	
	@SerializedName("DVAR")
	private String descripcionVariable;
	
	@SerializedName("RED")
	private String red;

	@SerializedName("PMX")
	private String ptoMaximo;

	@SerializedName("DIF")
	private String diferencia;

	@SerializedName("PTO")
	private String puntosSugeridos;
	
	private boolean seleccionado;

	public String getCodigoVariable() {
		return codigoVariable;
	}

	public void setCodigoVariable(String codigoVariable) {
		this.codigoVariable = codigoVariable;
	}

	public String getDescripcionVariable() {
		return descripcionVariable;
	}

	public void setDescripcionVariable(String descripcionVariable) {
		this.descripcionVariable = descripcionVariable;
	}

	public String getRed() {
		return red;
	}

	public void setRed(String red) {
		this.red = red;
	}

	public String getPtoMaximo() {
		return ptoMaximo;
	}

	public void setPtoMaximo(String ptoMaximo) {
		this.ptoMaximo = ptoMaximo;
	}

	public String getDiferencia() {
		return diferencia;
	}

	public void setDiferencia(String diferencia) {
		this.diferencia = diferencia;
	}

	public String getPuntosSugeridos() {
		return puntosSugeridos;
	}

	public void setPuntosSugeridos(String puntosSugeridos) {
		this.puntosSugeridos = puntosSugeridos;
	}

	public boolean isSeleccionado() {
		return seleccionado;
	}

	public void setSeleccionado(boolean seleccionado) {
		this.seleccionado = seleccionado;
	}
	       
}
