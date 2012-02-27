package pe.lindley.red.ws.bean;

import java.util.List;

import com.google.gson.annotations.SerializedName;

import pe.lindley.red.to.SoviEmpresaTO;
import pe.lindley.util.ResponseBase;

public class ConsultarSoviEmpresaResponse extends ResponseBase {

	@SerializedName("DAT")
	private List<SoviEmpresaTO> data;

	public List<SoviEmpresaTO> getData() {
		return data;
	}

	public void setData(List<SoviEmpresaTO> data) {
		this.data = data;
	}
	
}
