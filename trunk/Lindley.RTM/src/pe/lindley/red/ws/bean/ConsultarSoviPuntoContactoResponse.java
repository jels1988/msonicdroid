package pe.lindley.red.ws.bean;

import java.util.List;

import com.google.gson.annotations.SerializedName;

import pe.lindley.red.to.SoviPuntoContactoTO;
import pe.lindley.util.ResponseBase;

public class ConsultarSoviPuntoContactoResponse extends ResponseBase {

	@SerializedName("DAT")
	private List<SoviPuntoContactoTO> data;

	public List<SoviPuntoContactoTO> getData() {
		return data;
	}

	public void setData(List<SoviPuntoContactoTO> data) {
		this.data = data;
	}
}
