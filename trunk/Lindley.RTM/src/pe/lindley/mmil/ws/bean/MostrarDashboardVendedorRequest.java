package pe.lindley.mmil.ws.bean;

import com.google.gson.annotations.SerializedName;

public class MostrarDashboardVendedorRequest {

	@SerializedName("COD")
	private String Codigo;

	@SerializedName("FEC")
	private String Fecha;
	
	@SerializedName("TIP")
	private int Tipo;

	@SerializedName("SUP")
	private int Supervisor;

	public String getCodigo() {
		return Codigo;
	}

	public void setCodigo(String codigo) {
		Codigo = codigo;
	}

	public String getFecha() {
		return Fecha;
	}

	public void setFecha(String fecha) {
		Fecha = fecha;
	}
	public int getTipo() {
		return Tipo;
	}

	public void setTipo(int tipo) {
		Tipo = tipo;
	}

	public int getSupervisor() {
		return Supervisor;
	}

	public void setSupervisor(int supervisor) {
		Supervisor = supervisor;
	}

}
