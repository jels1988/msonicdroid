package pe.lindley.red.ws.bean;

import java.util.List;

import com.google.gson.annotations.SerializedName;

import pe.lindley.red.to.SKUPropietarioTO;
import pe.lindley.util.ResponseBase;

public class ConsultarSKUPropietarioResponse extends ResponseBase  {
	
	@SerializedName("SKU")
	private List<SKUPropietarioTO> skus;

	public List<SKUPropietarioTO> getSkus() {
		return skus;
	}

	public void setSkus(List<SKUPropietarioTO> skus) {
		this.skus = skus;
	}
}
