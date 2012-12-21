package lindley.desarrolloxcliente.to;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PosicionTO {

	public PosicionTO()
	{
		seleccionado = true;
	}
	
	@Expose()
	@SerializedName("CVAR")
	private String codigoVariable;
	
	@Expose()
	@SerializedName("DVAR")
	private String descripcionVariable;
	
	@Expose()
	@SerializedName("RED")
	private String red;

	@Expose()
	@SerializedName("PMX")
	private String ptoMaximo;

	@Expose()
	@SerializedName("DIF")
	private String diferencia;

	@Expose()
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
