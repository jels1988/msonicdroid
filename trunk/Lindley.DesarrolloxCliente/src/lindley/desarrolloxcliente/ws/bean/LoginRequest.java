package lindley.desarrolloxcliente.ws.bean;

import com.google.gson.annotations.Expose;

public class LoginRequest {
	
	@Expose()
	private String usuario;
	@Expose()
	private String password;
	
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
