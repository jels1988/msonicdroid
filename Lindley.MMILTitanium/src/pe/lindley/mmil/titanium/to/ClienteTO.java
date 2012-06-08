package pe.lindley.mmil.titanium.to;

import java.util.ArrayList;

import com.google.gson.annotations.SerializedName;

public class ClienteTO {

	@SerializedName("COD")
	public String codigo;
	
	@SerializedName("NOM")
	public String nombre;
	
	@SerializedName("DIR")
	public String direccion;
	

	@SerializedName("DOC")
	public ArrayList<DocumentoTO> documentos;
	
}
