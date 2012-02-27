package pe.lindley.prospector.ws.bean;

import java.util.ArrayList;

import com.google.gson.annotations.SerializedName;

import pe.lindley.prospector.to.ClienteTO;
import pe.lindley.util.ResponseBase;

public class FichasRechazadasResponse extends ResponseBase {
	
	
	@SerializedName("FichasRechazadas")
	private ArrayList<ClienteTO> fichasRechazadas;

	public ArrayList<ClienteTO> getFichasRechazadas() {
		return fichasRechazadas;
	}

	public void setFichasRechazadas(ArrayList<ClienteTO> fichasRechazadas) {
		this.fichasRechazadas = fichasRechazadas;
	}
	
}
