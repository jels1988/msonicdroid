package lindley.desarrolloxcliente;
import java.util.ArrayList;
import java.util.List;


import android.app.Application;
import android.widget.ArrayAdapter;

import com.google.inject.Inject;

import lindley.desarrolloxcliente.activity.CompromisoOpenFalse_Activity;
import lindley.desarrolloxcliente.activity.CompromisoOpen_Activity;
import lindley.desarrolloxcliente.activity.CompromisoPosicionOpenFalse_Activity;
import lindley.desarrolloxcliente.activity.CompromisoPosicionOpen_Activity;
import lindley.desarrolloxcliente.activity.CompromisoPresentacionOpenFalse_Activity;
import lindley.desarrolloxcliente.activity.CompromisoPresentacionOpen_Activity;
import lindley.desarrolloxcliente.activity.TestTAbItem;
import lindley.desarrolloxcliente.dao.FotoDAO;
import lindley.desarrolloxcliente.negocio.FotoBLL;
import lindley.desarrolloxcliente.to.ClienteTO;
import lindley.desarrolloxcliente.to.CompromisoPosicionTO;
import lindley.desarrolloxcliente.to.CompromisoTO;
import lindley.desarrolloxcliente.to.EvaluacionTO;
import lindley.desarrolloxcliente.to.InformacionAdicionalTO;
import lindley.desarrolloxcliente.to.OportunidadTO;
import lindley.desarrolloxcliente.to.PosicionCompromisoTO;
import lindley.desarrolloxcliente.to.PresentacionCompromisoTO;
import lindley.desarrolloxcliente.to.SKUPresentacionCompromisoTO;
import lindley.desarrolloxcliente.to.SKUPresentacionTO;
import lindley.desarrolloxcliente.to.UsuarioTO;
import lindley.desarrolloxcliente.to.upload.PosicionTO;

public class MyApplication extends Application {

	
	
	public PosicionTO posicionCompromisoFotoTO;
	
	@Inject protected FotoBLL fotoBLL;
	@Inject protected FotoDAO fotoDAO; 
	
	public UsuarioTO usuario;
	public ClienteTO cliente;
	public EvaluacionTO evaluacion;
	public lindley.desarrolloxcliente.to.upload.EvaluacionTO evaluacionActual;
	
	public int compromisoPosicion=-1;
	
	
	private ArrayList<OportunidadTO> oportunidadesDesarrollador; 
	private ArrayList<OportunidadTO> oportunidades;
	
	public List<SKUPresentacionTO> guardarSKUPresentacion;
	public String anio;
	public String mes;
	public String dia;
	public InformacionAdicionalTO informacionAdicional;
	public CompromisoPosicionOpenFalse_Activity.EfficientAdapter 	 posicionFalseAdapter;
	public CompromisoPresentacionOpenFalse_Activity.EfficientAdapter presentacionFalseAdapter;
	public CompromisoOpenFalse_Activity.EfficientAdapter 			 openFalseAdapter;
		
	
	///-------------------
	
	
	
	public ArrayList<PosicionCompromisoTO> listPosicionCompromiso;
	public ArrayList<PresentacionCompromisoTO> listPresentacionCompromiso;
	public ArrayList<CompromisoTO> listInventarioCompromiso;
	
	public ArrayList<SKUPresentacionCompromisoTO> listSKUPresentacionCompromiso;
	
	public CompromisoPosicionOpen_Activity.EfficientAdapter 	posicionAdapter;
	public CompromisoPresentacionOpen_Activity.EfficientAdapter presentacionAdapter;
	public CompromisoOpen_Activity.EfficientAdapter 			openAdapter;
	public TestTAbItem.EfficientAdapter 			openAdapter2;
		
	public ArrayList<CompromisoPosicionTO> listCompromiso;
	
	public UsuarioTO getUsuarioTO() {
		return usuario;
	}

	public void setUsuarioTO(UsuarioTO usuarioTO) {
		this.usuario = usuarioTO;
	}
	
	public ClienteTO getClienteTO() {
		return cliente;
	}

	public void setClienteTO(ClienteTO clienteTO) {
		this.cliente = clienteTO;
	}
	
	public String codigoCliente;
//	public synchronized lindley.desarrolloxcliente.adapter.AccionTradeTOAdapter getAdapterAccionTrade(List<AccionTradeTO> listaAcciones){
//		ArrayList<AccionTradeTO> listaAccionTrade = new ArrayList<AccionTradeTO>();
//		AccionTradeTO accionTradeTO = new AccionTradeTO();
//		accionTradeTO.setCodigo("0");
//		accionTradeTO.setDescripcion("--Seleccionar--");
//		listaAccionTrade.addAll(listaAcciones);
//		listaAccionTrade.add(0,accionTradeTO);		
//		AccionTradeTOAdapter accionTradeTOAdapter = new AccionTradeTOAdapter( getApplicationContext(), listaAccionTrade);
//		return accionTradeTOAdapter;
//	}
	
	public synchronized ArrayAdapter<lindley.desarrolloxcliente.to.upload.AccionTradeTO> getAdapterAccionTrade(List<lindley.desarrolloxcliente.to.upload.AccionTradeTO> listaAcciones){
		ArrayList<lindley.desarrolloxcliente.to.upload.AccionTradeTO> listaAccionTrade = new ArrayList<lindley.desarrolloxcliente.to.upload.AccionTradeTO>();
		lindley.desarrolloxcliente.to.upload.AccionTradeTO accionTradeTO = new lindley.desarrolloxcliente.to.upload.AccionTradeTO();
		accionTradeTO.codigo="0";
		accionTradeTO.descripcion="--Seleccionar--";
		listaAccionTrade.addAll(listaAcciones);
		listaAccionTrade.add(0,accionTradeTO);				
		
		ArrayAdapter<lindley.desarrolloxcliente.to.upload.AccionTradeTO> adapAccionTradeTO = new ArrayAdapter<lindley.desarrolloxcliente.to.upload.AccionTradeTO>( getApplicationContext(), android.R.layout.simple_spinner_item, listaAccionTrade);
	    adapAccionTradeTO.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		
		
		return adapAccionTradeTO;
	}
	/*
	public synchronized ArrayAdapter<AccionTradeTO> getAdapterAccionTrade(List<AccionTradeTO> listaAcciones){
		ArrayList<AccionTradeTO> listaAccionTrade = new ArrayList<AccionTradeTO>();
		AccionTradeTO accionTradeTO = new AccionTradeTO();
		accionTradeTO.setCodigo("0");
		accionTradeTO.setDescripcion("--Seleccionar--");
		listaAccionTrade.addAll(listaAcciones);
		listaAccionTrade.add(0,accionTradeTO);				
		
		ArrayAdapter<AccionTradeTO> adapAccionTradeTO = new ArrayAdapter<AccionTradeTO>( getApplicationContext(), android.R.layout.simple_spinner_item, listaAccionTrade);
	    adapAccionTradeTO.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		
		
		return adapAccionTradeTO;
	}*/
	
	//ArrayAdapter<AccionTradeTO> adapAccionTradeTO = new ArrayAdapter<AccionTradeTO>( (ConsultarOportunidad_Activity)context, android.R.layout.simple_spinner_item, oportunidad.getListaAccionesTrade());
    //adapAccionTradeTO.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    //holder.cboAccTrade.setAdapter(adapAccionTradeTO);
    
	
	/*
	@Override
	protected void addApplicationModules(List<Module> modules){	
		
		modules.add(new AbstractAndroidModule(){
			@Override
			protected void configure() {
				// TODO Auto-generated method stub
				bind(DBHelper.class).toProvider(DBHelperProvider.class).in(Singleton.class);
				
				
				bind(LoginProxy.class).in(Singleton.class);				
				bind(ConsultarClienteProxy.class).in(Singleton.class);
				bind(ConsultarCabeceraProxy.class).in(Singleton.class);
				bind(ConsultarCompromisoProxy.class).in(Singleton.class);
				bind(ConsultarOportunidadProxy.class).in(Singleton.class);
				bind(ActualizarCompromisoProxy.class).in(Singleton.class);
				bind(GuardarDesarrolloProxy.class).in(Singleton.class);
				bind(CerrarCompromisoProxy.class).in(Singleton.class);
				bind(ConsultarProfitProxy.class).in(Singleton.class);
				bind(ConsultarFotoExitoProxy.class).in(Singleton.class);
				bind(ConsultarArticulosCanjeProxy.class).in(Singleton.class);
				bind(ConsultarSKUPrioritarioProxy.class).in(Singleton.class);
				bind(GuardarNuevoDesarrolloProxy.class).in(Singleton.class);
				bind(ConsultarNuevaOportunidadProxy.class).in(Singleton.class);
				bind(ConsultarInformacionComboProxy.class).in(Singleton.class);
				bind(ConsultarResumenProxy.class).in(Singleton.class);
				bind(ActualizarEstadoProxy.class).in(Singleton.class);
								
				bind(FotoBLL.class).in(Singleton.class);
				bind(FotoDAO.class).in(Singleton.class);
			}});
	}*/

	public ArrayList<OportunidadTO> getOportunidadesDesarrollador() {
		return oportunidadesDesarrollador;
	}

	public void setOportunidadesDesarrollador(
			ArrayList<OportunidadTO> oportunidadesDesarrollador) {
		this.oportunidadesDesarrollador = oportunidadesDesarrollador;
	}

	public ArrayList<OportunidadTO> getOportunidades() {
		return oportunidades;
	}

	public void setOportunidades(ArrayList<OportunidadTO> oportunidades) {
		this.oportunidades = oportunidades;
	}


}
