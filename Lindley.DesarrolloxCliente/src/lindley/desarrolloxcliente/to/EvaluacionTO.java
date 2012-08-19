package lindley.desarrolloxcliente.to;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;

public class EvaluacionTO {

	public EvaluacionTO(){
		oportunidades =  new ArrayList<OportunidadTO>();
		skuPresentacion = new ArrayList<SKUPresentacionTO>();
	}
	
	@Expose()
	public long evaluacionId;
	public String codigoCliente;
	public String activosLindley;
	public String codigoFe; //cluster
	public String codigoUsuario;
	
	public List<OportunidadTO> oportunidades;
	public List<SKUPresentacionTO> skuPresentacion;
}