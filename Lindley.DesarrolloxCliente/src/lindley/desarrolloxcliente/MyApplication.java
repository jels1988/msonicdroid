package lindley.desarrolloxcliente;
import java.util.ArrayList;
import java.util.List;

import net.msonic.lib.DBHelper;
import net.msonic.lib.DBHelperProvider;


import android.widget.ArrayAdapter;

import com.google.inject.Inject;
import com.google.inject.Module;
import com.google.inject.Singleton;

import lindley.desarrolloxcliente.activity.CompromisoOpenFalse_Activity;
import lindley.desarrolloxcliente.activity.CompromisoOpen_Activity;
import lindley.desarrolloxcliente.activity.CompromisoPosicionOpenFalse_Activity;
import lindley.desarrolloxcliente.activity.CompromisoPosicionOpen_Activity;
import lindley.desarrolloxcliente.activity.CompromisoPresentacionOpenFalse_Activity;
import lindley.desarrolloxcliente.activity.CompromisoPresentacionOpen_Activity;
import lindley.desarrolloxcliente.dao.FotoDAO;
import lindley.desarrolloxcliente.negocio.FotoBLL;
import lindley.desarrolloxcliente.to.AccionTradeTO;
import lindley.desarrolloxcliente.to.ClienteTO;
import lindley.desarrolloxcliente.to.CompromisoPosicionTO;
import lindley.desarrolloxcliente.to.CompromisoTO;
import lindley.desarrolloxcliente.to.GuardarOportunidadTO;
import lindley.desarrolloxcliente.to.InformacionAdicionalTO;
import lindley.desarrolloxcliente.to.OportunidadTO;
import lindley.desarrolloxcliente.to.PosicionCompromisoTO;
import lindley.desarrolloxcliente.to.PresentacionCompromisoTO;
import lindley.desarrolloxcliente.to.SKUPresentacionCompromisoTO;
import lindley.desarrolloxcliente.to.SKUPresentacionTO;
import lindley.desarrolloxcliente.to.UsuarioTO;
import lindley.desarrolloxcliente.ws.service.ActualizarCompromisoProxy;
import lindley.desarrolloxcliente.ws.service.ActualizarEstadoProxy;
import lindley.desarrolloxcliente.ws.service.CerrarCompromisoProxy;
import lindley.desarrolloxcliente.ws.service.ConsultarArticulosCanjeProxy;
import lindley.desarrolloxcliente.ws.service.ConsultarCompromisoProxy;
import lindley.desarrolloxcliente.ws.service.ConsultarFotoExitoProxy;
import lindley.desarrolloxcliente.ws.service.ConsultarInformacionComboProxy;
import lindley.desarrolloxcliente.ws.service.ConsultarNuevaOportunidadProxy;
import lindley.desarrolloxcliente.ws.service.ConsultarOportunidadProxy;
import lindley.desarrolloxcliente.ws.service.ConsultarProfitProxy;
import lindley.desarrolloxcliente.ws.service.ConsultarResumenProxy;
import lindley.desarrolloxcliente.ws.service.ConsultarSKUPrioritarioProxy;
import lindley.desarrolloxcliente.ws.service.GuardarDesarrolloProxy;
import lindley.desarrolloxcliente.ws.service.GuardarNuevoDesarrolloProxy;
import lindley.desarrolloxcliente.ws.service.LoginProxy;
import lindley.desarrolloxcliente.ws.service.ConsultarClienteProxy;
import lindley.desarrolloxcliente.ws.service.ConsultarCabeceraProxy;
import roboguice.application.RoboApplication;
import roboguice.config.AbstractAndroidModule;

public class MyApplication extends RoboApplication {

	@Inject FotoBLL fotoBLL;
	@Inject FotoDAO fotoDAO; 
	
	private UsuarioTO usuarioTO;
	private ClienteTO clienteTO;
	
	private ArrayList<OportunidadTO> oportunidadesDesarrollador; 
	private ArrayList<OportunidadTO> oportunidades;
	
	///Agregado 10/04/2012
	public ArrayList<GuardarOportunidadTO> guardarOportunidades;
	public String activosLindley = "";
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
		
	public ArrayList<CompromisoPosicionTO> listCompromiso;
	
	public UsuarioTO getUsuarioTO() {
		return usuarioTO;
	}

	public void setUsuarioTO(UsuarioTO usuarioTO) {
		this.usuarioTO = usuarioTO;
	}
	
	public ClienteTO getClienteTO() {
		return clienteTO;
	}

	public void setClienteTO(ClienteTO clienteTO) {
		this.clienteTO = clienteTO;
	}
	
	public ClienteTO cliente;
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
	}
	//ArrayAdapter<AccionTradeTO> adapAccionTradeTO = new ArrayAdapter<AccionTradeTO>( (ConsultarOportunidad_Activity)context, android.R.layout.simple_spinner_item, oportunidad.getListaAccionesTrade());
    //adapAccionTradeTO.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    //holder.cboAccTrade.setAdapter(adapAccionTradeTO);
    
	
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
	}

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
