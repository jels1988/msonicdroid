package pe.pae.encuesta.activity;

import java.util.List;

import net.msonic.lib.DBHelper;
import net.msonic.lib.DBHelperProvider;
import pe.pae.encuesta.dao.ClienteDAO;
import pe.pae.encuesta.negocio.ClienteBLL;
import pe.pae.encuesta.to.UsuarioTO;
import pe.pae.encuesta.ws.service.EncuestaProxy;
import pe.pae.encuesta.ws.service.LoginProxy;

import com.google.inject.Module;
import com.google.inject.Singleton;

import roboguice.application.RoboApplication;
import roboguice.config.AbstractAndroidModule;

public class MiApp extends RoboApplication {

	private UsuarioTO usuarioTO;
	

	public UsuarioTO getUsuarioTO() {
		return usuarioTO;
	}


	public void setUsuarioTO(UsuarioTO usuarioTO) {
		this.usuarioTO = usuarioTO;
	}
	
	@Override
	protected void addApplicationModules(List<Module> modules){	
		modules.add(new AbstractAndroidModule() {
			
			@Override
			protected void configure() {
				
				
				
				// TODO Auto-generated method stub
				bind(LoginProxy.class).in(Singleton.class);
				bind(EncuestaProxy.class).in(Singleton.class);
				
				bind(DBHelper.class).toProvider(DBHelperProvider.class).in(Singleton.class);
				
				bind(ClienteDAO.class).in(Singleton.class);
				bind(ClienteBLL.class).in(Singleton.class);
				
			}
		});
	}
}
