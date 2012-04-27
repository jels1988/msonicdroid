package pe.lindley.mmil.titanium.ws.bean;

import java.util.ArrayList;
import net.msonic.lib.ResponseBase;

import pe.lindley.mmil.titanium.to.SupervisorTO;


import com.google.gson.annotations.SerializedName;

public class ListarSupervisorResponse extends ResponseBase {

	@SerializedName("SUP")
	public ArrayList<SupervisorTO> listaSupervisor;

}
