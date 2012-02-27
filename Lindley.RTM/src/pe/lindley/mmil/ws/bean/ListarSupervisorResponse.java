package pe.lindley.mmil.ws.bean;

import java.util.List;
import pe.lindley.mmil.to.SupervisorTO;
import pe.lindley.util.ResponseBase;

import com.google.gson.annotations.SerializedName;

public class ListarSupervisorResponse  extends ResponseBase {
	
	@SerializedName("SUP")
	private List<SupervisorTO> listaSupervisor;

	public List<SupervisorTO> getListaSupervisor() {
		return listaSupervisor;
	}

	public void setListaSupervisor(List<SupervisorTO> listaSupervisor) {
		this.listaSupervisor = listaSupervisor;
	}
}
