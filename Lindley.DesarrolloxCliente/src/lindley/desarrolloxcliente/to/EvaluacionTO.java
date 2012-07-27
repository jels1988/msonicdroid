package lindley.desarrolloxcliente.to;

import java.util.ArrayList;
import java.util.List;

public class EvaluacionTO {

	public EvaluacionTO(){
		oportunidades =  new ArrayList<NuevaOportunidadTO>();
		skuPresentacion = new ArrayList<SKUPresentacionTO>();
	}
	
	public int evaluacionId;
	public String codigoCliente;
	public String activosLindley;
	public String codigoFe; //cluster
	public String codigoUsuario;
	
	public List<NuevaOportunidadTO> oportunidades;
	public List<SKUPresentacionTO> skuPresentacion;
}
