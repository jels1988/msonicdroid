package lindley.desarrolloxcliente;

import java.util.List;
import com.google.inject.Module;
import com.google.inject.Singleton;
import lindley.desarrolloxcliente.to.ClienteTO;
import lindley.desarrolloxcliente.to.UsuarioTO;
import lindley.desarrolloxcliente.ws.service.ConsultarCompromisoProxy;
import lindley.desarrolloxcliente.ws.service.ConsultarOportunidadProxy;
import lindley.desarrolloxcliente.ws.service.LoginProxy;
import lindley.desarrolloxcliente.ws.service.ConsultarClienteProxy;
import lindley.desarrolloxcliente.ws.service.ConsultarCabeceraProxy;
import roboguice.application.RoboApplication;
import roboguice.config.AbstractAndroidModule;

public class MyApplication extends RoboApplication {

	private UsuarioTO usuarioTO;
	private ClienteTO clienteTO;

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
			}});
	}	
}
