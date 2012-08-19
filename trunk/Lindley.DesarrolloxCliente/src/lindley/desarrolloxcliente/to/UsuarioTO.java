package lindley.desarrolloxcliente.to;

import java.util.HashMap;

import com.google.gson.annotations.SerializedName;

public class UsuarioTO {

	public UsuarioTO(){
		moduloPermisos = new HashMap<String,String>();
		opcionPermisos = new HashMap<String,String>();
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getCodigoSap() {
		return codigoSap; 
	}

	public void setCodigoSap(String codigoSap) {
		this.codigoSap = codigoSap;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	public String getCodigoDeposito() {
		return codigoDeposito;
	}

	public void setCodigoDeposito(String codigoDeposito) {
		this.codigoDeposito = codigoDeposito;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCodigoRuta() {
		return codigoRuta;
	}

	public void setCodigoRuta(String codigoRuta) {
		this.codigoRuta = codigoRuta;
	}

	public HashMap<String, String> getModuloPermisos() {
		return moduloPermisos;
	}

	public void setModuloPermisos(HashMap<String, String> moduloPermisos) {
		this.moduloPermisos = moduloPermisos;
	}

	public HashMap<String, String> getOpcionPermisos() {
		return opcionPermisos;
	}

	public void setOpcionPermisos(HashMap<String, String> opcionPermisos) {
		this.opcionPermisos = opcionPermisos;
	}
	
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
	
	@SerializedName("MPER")
	public HashMap<String,String> moduloPermisos;
	
	@SerializedName("OPER")
	public HashMap<String,String> opcionPermisos;
	
	@SerializedName("RolId")
	public String rolId;
	
	@SerializedName("CSB")
	public String codigoBasis;

	public String getRolId() {
		return rolId;
	}

	public void setRolId(String rolId) {
		this.rolId = rolId;
	}

	public String getCodigoBasis() {
		return codigoBasis;
	}

	public void setCodigoBasis(String codigoBasis) {
		this.codigoBasis = codigoBasis;
	}


}
