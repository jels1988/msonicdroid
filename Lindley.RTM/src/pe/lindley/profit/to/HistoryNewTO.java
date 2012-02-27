package pe.lindley.profit.to;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class HistoryNewTO {

	@SerializedName("COD")
	private String codigo;
	
	@SerializedName("DES")
	private String descripcion;
	
	@SerializedName("DET")
	private List<HistoryNewDetalleTO> detalle;

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public List<HistoryNewDetalleTO> getDetalle() {
		return detalle;
	}

	public void setDetalle(List<HistoryNewDetalleTO> detalle) {
		this.detalle = detalle;
	}
}
