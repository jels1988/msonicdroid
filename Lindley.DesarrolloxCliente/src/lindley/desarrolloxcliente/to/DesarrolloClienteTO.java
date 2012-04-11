package lindley.desarrolloxcliente.to;

import com.google.gson.annotations.SerializedName;

public class DesarrolloClienteTO {

	@SerializedName("COD")
	private String codigo;
	
	@SerializedName("FEC")
	private String fecha;

	@SerializedName("HOR")
	private String hora;

//	@SerializedName("CRB")
//	private String userCrea;

	@SerializedName("FCC")
	private String fechaCierre;

	@SerializedName("HCC")
	private String horaCierre; 

//	@SerializedName("UCE")
//	private String userCierra;

	@SerializedName("EST")
	private String estado;

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

//	public String getUserCrea() {
//		return userCrea;
//	}
//
//	public void setUserCrea(String userCrea) {
//		this.userCrea = userCrea;
//	}

	public String getFechaCierre() {
		return fechaCierre;
	}

	public void setFechaCierre(String fechaCierre) {
		this.fechaCierre = fechaCierre;
	}

	public String getHoraCierre() {
		return horaCierre;
	}

	public void setHoraCierre(String horaCierre) {
		this.horaCierre = horaCierre;
	}

//	public String getUserCierra() {
//		return userCierra;
//	}
//
//	public void setUserCierra(String userCierra) {
//		this.userCierra = userCierra;
//	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
}
