package pe.lindley.ficha.ws.bean;

import java.util.List;
import com.google.gson.annotations.SerializedName;
import pe.lindley.ficha.to.VisualizacionTO;
import pe.lindley.util.ResponseBase;

public class ObtenerVisualizacionResponse extends ResponseBase{

	@SerializedName("VIS")
	private List<VisualizacionTO> visualizacion;

	public List<VisualizacionTO> getVisualizacion() {
		return visualizacion;
	}

	public void setVisualizacion(List<VisualizacionTO> visualizacion) {
		this.visualizacion = visualizacion;
	}
	

}
