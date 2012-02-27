package pe.lindley.plandesarrollo.ws.bean;

import java.util.List;
import com.google.gson.annotations.SerializedName;
import pe.lindley.plandesarrollo.to.PlanDesarrolloTO;
import pe.lindley.util.ResponseBase;

public class ConsultarPlanResponse extends ResponseBase {
	
	@SerializedName("PDS")
	public List<PlanDesarrolloTO> pLanDesarrollo;

	public List<PlanDesarrolloTO> getpLanDesarrollo() {
		return pLanDesarrollo;
	}

	public void setpLanDesarrollo(List<PlanDesarrolloTO> pLanDesarrollo) {
		this.pLanDesarrollo = pLanDesarrollo;
	}	

}
