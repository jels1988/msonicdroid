package pe.lindley.prospector.ws.bean;

import java.util.ArrayList;

import com.google.gson.annotations.SerializedName;

import pe.lindley.prospector.to.TipoDocumentoTO;
import pe.lindley.util.ResponseBase;

public class TipoDocumentoResponse extends ResponseBase {

	@SerializedName("Dcs")
	public ArrayList<TipoDocumentoTO> documentos;
}
