package pe.lindley.mmil.ws.bean;

import com.google.gson.annotations.SerializedName;

public class MostrarDashboardCdaRequest {
	
	@SerializedName("COD")
	private String codigo;

	@SerializedName("GRA")
	private String grafico;

	@SerializedName("TIP")
	private int tipo;

	@SerializedName("SUP")
	private int supervisor;

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getGrafico() {
		return grafico;
	}

	public void setGrafico(String grafico) {
		this.grafico = grafico;
	}

	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	public int getSupervisor() {
		return supervisor;
	}

	public void setSupervisor(int supervisor) {
		this.supervisor = supervisor;
	}


}
