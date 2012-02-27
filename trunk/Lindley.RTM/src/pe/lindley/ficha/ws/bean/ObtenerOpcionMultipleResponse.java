package pe.lindley.ficha.ws.bean;

import java.util.List;
import com.google.gson.annotations.SerializedName;
import pe.lindley.ficha.to.OpcionMultipleTO;
import pe.lindley.util.ResponseBase;

public class ObtenerOpcionMultipleResponse extends ResponseBase{
	
	@SerializedName("OPC")
	private List<OpcionMultipleTO> obtenerOpciones;

	public List<OpcionMultipleTO> getObtenerOpciones() {
		return obtenerOpciones;
	}

	public void setObtenerOpciones(List<OpcionMultipleTO> obtenerOpciones) {
		this.obtenerOpciones = obtenerOpciones;
	}

}
