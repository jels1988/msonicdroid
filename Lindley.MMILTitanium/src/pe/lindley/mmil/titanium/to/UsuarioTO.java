package pe.lindley.mmil.titanium.to;


import com.google.gson.annotations.SerializedName;

public class UsuarioTO {

	
	@SerializedName("NOM")
	public String nombres;
	
	@SerializedName("CSP")
	public String codigoSap;
	
	@SerializedName("Rol")
	public String rol;
	
	@SerializedName("CDDEP")
	public String codigoDeposito;
	
	@SerializedName("PWD")
	public String password;
	
	@SerializedName("CDRUT")
	public String codigoRuta;
	
	@SerializedName("RolId")
	public String rolId;
	
	@SerializedName("CSB")
	public String codigoBasis;
	
	@SerializedName("DDP")
	public String descripcionDeposito;
	
}
