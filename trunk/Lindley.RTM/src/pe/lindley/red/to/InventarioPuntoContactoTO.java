package pe.lindley.red.to;

import com.google.gson.annotations.SerializedName;

public class InventarioPuntoContactoTO {

	@SerializedName("CAN")
	private int cantidad;

	@SerializedName("PRO")
	private String propietario;

	@SerializedName("TCT")
	private String tipoPuntoContacto;

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public String getPropietario() {
		return propietario;
	}

	public void setPropietario(String propietario) {
		this.propietario = propietario;
	}

	public String getTipoPuntoContacto() {
		return tipoPuntoContacto;
	}

	public void setTipoPuntoContacto(String tipoPuntoContacto) {
		this.tipoPuntoContacto = tipoPuntoContacto;
	}
		        
}
