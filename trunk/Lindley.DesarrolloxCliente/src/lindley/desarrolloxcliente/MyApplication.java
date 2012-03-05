package lindley.desarrolloxcliente;

import java.util.ArrayList;
import java.util.List;
import com.google.inject.Module;
import com.google.inject.Singleton;

import lindley.desarrolloxcliente.adapter.AccionTradeTOAdapter;
import lindley.desarrolloxcliente.to.AccionTradeTO;
import lindley.desarrolloxcliente.to.ClienteTO;
import lindley.desarrolloxcliente.to.OportunidadTO;
import lindley.desarrolloxcliente.to.UsuarioTO;
import lindley.desarrolloxcliente.ws.service.ActualizarCompromisoProxy;
import lindley.desarrolloxcliente.ws.service.CerrarCompromisoProxy;
import lindley.desarrolloxcliente.ws.service.ConsultarCompromisoProxy;
import lindley.desarrolloxcliente.ws.service.ConsultarOportunidadProxy;
import lindley.desarrolloxcliente.ws.service.GuardarDesarrolloProxy;
import lindley.desarrolloxcliente.ws.service.LoginProxy;
import lindley.desarrolloxcliente.ws.service.ConsultarClienteProxy;
import lindley.desarrolloxcliente.ws.service.ConsultarCabeceraProxy;
import roboguice.application.RoboApplication;
import roboguice.config.AbstractAndroidModule;

public class MyApplication extends RoboApplication {

	private UsuarioTO usuarioTO;
	private ClienteTO clienteTO;
	
	private ArrayList<OportunidadTO> oportunidadesDesarrollador; 
	private ArrayList<OportunidadTO> oportunidades;
	
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
	
	public synchronized lindley.desarrolloxcliente.adapter.AccionTradeTOAdapter getAdapterAccionTrade(List<AccionTradeTO> listaAcciones){
		ArrayList<AccionTradeTO> listaAccionTrade = new ArrayList<AccionTradeTO>();
		AccionTradeTO accionTradeTO = new AccionTradeTO();
		accionTradeTO.setCodigo("0");
		accionTradeTO.setDescripcion("--Seleccionar--");
		listaAccionTrade.addAll(listaAcciones);
		listaAccionTrade.add(0,accionTradeTO);		
		AccionTradeTOAdapter accionTradeTOAdapter = new AccionTradeTOAdapter( getApplicationContext(), listaAccionTrade);
		return accionTradeTOAdapter;
	}
	
	@Override
	protected void addApplicationModules(List<Module> modules){	
		
		modules.add(new AbstractAndroidModule(){
			@Override
			protected void configure() {
				// TODO Auto-generated method stub
				bind(LoginProxy.class).in(Singleton.class);				
				bind(ConsultarClienteProxy.class).in(Singleton.class);
				bind(ConsultarCabeceraProxy.class).in(Singleton.class);
				bind(ConsultarCompromisoProxy.class).in(Singleton.class);
				bind(ConsultarOportunidadProxy.class).in(Singleton.class);
				bind(ActualizarCompromisoProxy.class).in(Singleton.class);
				bind(GuardarDesarrolloProxy.class).in(Singleton.class);
				bind(CerrarCompromisoProxy.class).in(Singleton.class);
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
