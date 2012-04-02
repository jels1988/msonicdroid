package pe.lindley.prospector.to;

import com.google.gson.annotations.SerializedName;

public class DocumentoEnviarTO {

	
	@SerializedName("nom")
    public String nombre;
	
	@SerializedName("Tdoc")
    public int tipoDocumento;
	
	@SerializedName("Cli")
    public int codigoCliente;
	
}
