package pe.lindley.red.ws.bean;

import java.util.ArrayList;

import com.google.gson.annotations.SerializedName;

import pe.lindley.red.to.EncuestaTO;
import pe.lindley.util.ResponseBase;

public class ConsultarEncuestaResponse extends ResponseBase {

	@SerializedName("ENC")
	private ArrayList<EncuestaTO> encuestas;

	public ArrayList<EncuestaTO> getEncuestas() {
		return encuestas;
	}

	public void setEncuestas(ArrayList<EncuestaTO> encuestas) {
		this.encuestas = encuestas;
	}
}
