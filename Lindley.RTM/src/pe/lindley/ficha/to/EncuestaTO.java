package pe.lindley.ficha.to;

import java.util.ArrayList;
import com.google.gson.annotations.SerializedName;

public class EncuestaTO {
	
	public EncuestaTO(){
		detalleSeccion = new ArrayList<SeccionTO>();
	}
	
	@SerializedName("CLI")
	private String codigo;

	@SerializedName("COD")
	private String codigoEncuesta;

	@SerializedName("VER")
	private String versionEncuesta;
    
	@SerializedName("SECS")
	private ArrayList<SeccionTO> detalleSeccion;

	@SerializedName("URG")
	private String usuario;
	
	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

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
