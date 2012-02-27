package pe.lindley.om.ws.bean;

import java.util.List;

import com.google.gson.annotations.SerializedName;

import pe.lindley.util.ResponseBase;
import pe.lindley.om.to.RolTO;

public class DescargarRolesResponse extends ResponseBase {
	
	@SerializedName("Roles")
	private List<RolTO> roles;

	public List<RolTO> getRoles() {
		return roles;
	}

	public void setRoles(List<RolTO> roles) {
		this.roles = roles;
	}
	
}
