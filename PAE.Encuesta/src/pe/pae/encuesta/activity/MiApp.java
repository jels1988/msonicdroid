package pe.pae.encuesta.activity;

import java.util.List;

import pe.pae.encuesta.ws.service.LoginProxy;

import com.google.inject.Module;
import com.google.inject.Singleton;

import roboguice.application.RoboApplication;
import roboguice.config.AbstractAndroidModule;

public class MiApp extends RoboApplication {

	
	@Override
	protected void addApplicationModules(List<Module> modules){	
		modules.add(new AbstractAndroidModule() {
			
			@Override
			protected void configure() {
				// TODO Auto-generated method stub
				bind(LoginProxy.class).in(Singleton.class);
			}
		});
	}
}
