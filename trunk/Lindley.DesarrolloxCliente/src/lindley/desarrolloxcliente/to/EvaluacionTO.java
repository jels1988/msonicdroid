package lindley.desarrolloxcliente.to;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;

public class EvaluacionTO {

	public EvaluacionTO(){
		oportunidades =  new ArrayList<OportunidadTO>();
		skuPresentacion = new ArrayList<SKUPresentacionTO>();
		posiciones = new ArrayList<PosicionCompromisoTO>();
		presentaciones = new ArrayList<PresentacionCompromisoTO>();
	}
	
	@Expose()
	public long evaluacionId;
	public long serverId;
	
	
	public String codigoCliente;
	public String activosLindley;
	public String codigoFe; //cluster
	public String codigoUsuario;
	
	public String fecha;
	public String hora;
	public String fechaCierre;
	public String horaCierre;
	
	public String estado;
	
	public List<OportunidadTO> oportunidades;
	public List<SKUPresentacionTO> skuPresentacion;
	public List<PosicionCompromisoTO> posiciones;
	public List<PresentacionCompromisoTO> presentaciones;
}
