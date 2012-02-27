package pe.lindley.mmil.ws.bean;

import pe.lindley.mmil.to.CdaTO;
import pe.lindley.util.ResponseBase;
import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ListarCdaResponse extends ResponseBase{
		
	@SerializedName("CDA")
	private List<CdaTO> cdas;

	public List<CdaTO> getCdas() {
		return cdas;
	}

	public void setCdas(List<CdaTO> cdas) {
		this.cdas = cdas;
	}

}
