package lindley.desarrolloxcliente.to;

import java.util.ArrayList;
import java.util.List;

import lindley.desarrolloxcliente.ConstantesApp;

import com.google.gson.annotations.Expose;

public class EvaluacionTO {

	public EvaluacionTO(){
		oportunidades =  new ArrayList<OportunidadTO>();
		skuPresentacion = new ArrayList<SKUPresentacionTO>();
		posiciones = new ArrayList<PosicionCompromisoTO>();
		presentaciones = new ArrayList<PresentacionCompromisoTO>();
		usuarioCierre="0";
		serverId=0;
		combosSS = ConstantesApp.RESPUESTA_NO;
		combosMS = ConstantesApp.RESPUESTA_NO;
		observacionSS = "";
		observacionMS = "";
	}
	
	@Expose()
	public long id;
	public long serverId;
	
	
	public String codigoCliente;
	public String activosLindley;
	public String tipoActivoLindley;
	public String codigoFe; //cluster
	//public String codigoUsuario;
	
	public String usuarioCrea;
	public String fecha;
	public String hora;
	
	public String usuarioCierre;
	public String fechaCierre;
	public String horaCierre;
	
	public String estado;
	public String combosSS;
	public String observacionSS;
	public String combosMS;
	public String observacionMS;
	
	public List<OportunidadTO> oportunidades;
	public List<SKUPresentacionTO> skuPresentacion;
	public List<PosicionCompromisoTO> posiciones;
	public List<PresentacionCompromisoTO> presentaciones;
}
