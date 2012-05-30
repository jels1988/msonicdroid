package pe.lindley.mmil.titanium;

import java.util.List;

import pe.lindley.mmil.titanium.ws.service.ListarMapVendedorProxy;
import pe.lindley.mmil.titanium.ws.service.ListarSupervisorProxy;
import pe.lindley.mmil.titanium.ws.service.ListarVendedorProxy;
import pe.lindley.mmil.titanium.ws.service.LoginProxy;
import pe.lindley.mmil.titanium.ws.service.MostrarPizarraProxy;
import pe.lindley.mmil.titanium.ws.service.ResumenMercaderistasProxy;
import pe.lindley.mmil.titanium.ws.service.ResumenVendedoresProxy;

import com.google.inject.Module;
import com.google.inject.Singleton;

import roboguice.application.RoboApplication;
import roboguice.config.AbstractAndroidModule;

public class App extends RoboApplication {

	@Override
	protected void addApplicationModules(List<Module> modules){	
		
		modules.add(new AbstractAndroidModule(){
			@Override
			protected void configure() {
				// TODO Auto-generated method stub
				
				bind(MostrarPizarraProxy.class).in(Singleton.class);
				bind(ListarSupervisorProxy.class).in(Singleton.class);
				bind(ListarVendedorProxy.class).in(Singleton.class);
				bind(ListarMapVendedorProxy.class).in(Singleton.class);
				bind(LoginProxy.class).in(Singleton.class);
				bind(ResumenVendedoresProxy.class).in(Singleton.class);
				bind(ResumenMercaderistasProxy.class).in(Singleton.class);
				
				
			}
		});
	}
}