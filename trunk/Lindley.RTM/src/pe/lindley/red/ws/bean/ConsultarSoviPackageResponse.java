package pe.lindley.red.ws.bean;

import java.util.List;

import com.google.gson.annotations.SerializedName;

import pe.lindley.red.to.SoviPackageTO;
import pe.lindley.util.ResponseBase;

public class ConsultarSoviPackageResponse extends ResponseBase {

	@SerializedName("DAT")
	private List<SoviPackageTO> data;

	public List<SoviPackageTO> getData() {
		return data;
	}

	public void setData(List<SoviPackageTO> data) {
		this.data = data;
	}
}
