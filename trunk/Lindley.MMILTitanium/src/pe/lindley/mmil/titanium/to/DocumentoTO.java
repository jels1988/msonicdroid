package pe.lindley.mmil.titanium.to;

import java.util.ArrayList;

import com.google.gson.annotations.SerializedName;

public class DocumentoTO {
	
	@SerializedName("NUM")
	public String numero;
	
	@SerializedName("TDO")
	public String tipoDocumento;
	
	@SerializedName("IMP")
	public String importe;
	
	@SerializedName("EST")
	public String estado;
	
	@SerializedName("TRA")
	public String transportista;
	
	@SerializedName("PRO")
	public ArrayList<DocumentoDetalleTO> productos;

}
