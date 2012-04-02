package pe.lindley.prospector.to;

import com.google.gson.annotations.SerializedName;

public class TipoDocumentoTO {

	@SerializedName("dc")
	public String documento;
	
	@SerializedName("td")
	public int tipoDocumento;
	
	@SerializedName("ob")
	public int obligatorio;
}
