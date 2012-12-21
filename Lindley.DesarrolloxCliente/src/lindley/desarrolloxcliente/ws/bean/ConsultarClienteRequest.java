package lindley.desarrolloxcliente.ws.bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ConsultarClienteRequest {

	@Expose()
	@SerializedName("RUC")
	private String ruc;
	
	@Expose()
	@SerializedName("DNI")
	private String dni;
	
	@Expose()
	@SerializedName("COD")
	private String codigo;

	@Expose()
	@SerializedName("RZS")
	private String razonSocial;
	
	public String getRuc() {
		return ruc;
	}
 
	public void setRuc(String ruc) {
		this.ruc = ruc;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getRazonSocial() {
		return razonSocial;
	}

	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}
	
	
}
