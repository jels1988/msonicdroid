package pe.lindley.mmil.titanium.to;

import java.util.ArrayList;

import com.google.gson.annotations.SerializedName;

public class ResumenTO {
	
	@SerializedName("CAB")
	public ArrayList<ResumenVentaTO> resumen;
	
	@SerializedName("DET")
	public ArrayList<ResumenVentaDetalleTO> detalle;
	
	
}
