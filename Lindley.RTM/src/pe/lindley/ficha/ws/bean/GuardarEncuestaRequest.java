package pe.lindley.ficha.ws.bean;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import pe.lindley.ficha.to.SeccionTO;

public class GuardarEncuestaRequest {
	
	@SerializedName("CLI")
	private String codigo;

	@SerializedName("COD")
	private String codigoEncuesta;
	
	@SerializedName("VER")
	private String versionEncuesta;

	@SerializedName("URG")
	private String usuario;

	@SerializedName("SECS")
	private ArrayList<SeccionTO> detalleSeccion;

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getCodigoEncuesta() {
		return codigoEncuesta;
	}

	public void setCodigoEncuesta(String codigoEncuesta) {
		this.codigoEncuesta = codigoEncuesta;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public ArrayList<SeccionTO> getDetalleSeccion() {
		return detalleSeccion;
	}

	public void setDetalleSeccion(ArrayList<SeccionTO> detalleSeccion) {
		this.detalleSeccion = detalleSeccion;
	}
	
	public String getVersionEncuesta() {
		return versionEncuesta;
	}

	public void setVersionEncuesta(String versionEncuesta) {
		this.versionEncuesta = versionEncuesta;
	}
}
