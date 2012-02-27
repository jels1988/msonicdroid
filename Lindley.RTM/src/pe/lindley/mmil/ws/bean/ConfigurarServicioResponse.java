package pe.lindley.mmil.ws.bean;

import java.util.List;
import pe.lindley.mmil.to.ConfiguracionTO;
import pe.lindley.util.ResponseBase;

import com.google.gson.annotations.SerializedName;

public class ConfigurarServicioResponse  extends ResponseBase {
	
	@SerializedName("MOD")
	private List<ConfiguracionTO> getConfiguracion;

	public List<ConfiguracionTO> getGetConfiguracion() {
		return getConfiguracion;
	}

	public void setGetConfiguracion(List<ConfiguracionTO> getConfiguracion) {
		this.getConfiguracion = getConfiguracion;
	}

}