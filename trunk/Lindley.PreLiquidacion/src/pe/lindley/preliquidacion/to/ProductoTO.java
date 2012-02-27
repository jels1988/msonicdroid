package pe.lindley.preliquidacion.to;

import com.google.gson.annotations.SerializedName;

public class ProductoTO {
	
	@SerializedName("PRO")
	private String Producto;
	
	@SerializedName("CCA")
	private String cajasCargadas;
	
	@SerializedName("BCA")
	private String botellasCargadas;
	
	@SerializedName("CEN")
	private String cajasEntregadas;
	
	@SerializedName("BEN")
	private String botellasEntregadas;
	
	@SerializedName("CCB")
	private String cajasCambios;
	
	@SerializedName("BCB")
	private String botellasCambios;

	public String getProducto() {
		return Producto;
	}

	public void setProducto(String producto) {
		Producto = producto;
	}

	public String getCajasCargadas() {
		return cajasCargadas;
	}

	public void setCajasCargadas(String cajasCargadas) {
		this.cajasCargadas = cajasCargadas;
	}

	public String getBotellasCargadas() {
		return botellasCargadas;
	}

	public void setBotellasCargadas(String botellasCargadas) {
		this.botellasCargadas = botellasCargadas;
	}

	public String getCajasEntregadas() {
		return cajasEntregadas;
	}

	public void setCajasEntregadas(String cajasEntregadas) {
		this.cajasEntregadas = cajasEntregadas;
	}

	public String getBotellasEntregadas() {
		return botellasEntregadas;
	}

	public void setBotellasEntregadas(String botellasEntregadas) {
		this.botellasEntregadas = botellasEntregadas;
	}

	public String getCajasCambios() {
		return cajasCambios;
	}

	public void setCajasCambios(String cajasCambios) {
		this.cajasCambios = cajasCambios;
	}

	public String getBotellasCambios() {
		return botellasCambios;
	}

	public void setBotellasCambios(String botellasCambios) {
		this.botellasCambios = botellasCambios;
	}	
}