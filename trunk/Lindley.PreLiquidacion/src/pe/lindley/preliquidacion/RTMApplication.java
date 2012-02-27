package pe.lindley.preliquidacion;

import java.util.List;

import pe.lindley.preliquidacion.dao.DocumentoDAO;
import pe.lindley.preliquidacion.dao.MotivoDAO;
import pe.lindley.preliquidacion.negocio.DocumentoBLL;
import pe.lindley.preliquidacion.negocio.MotivoBLL;
import pe.lindley.preliquidacion.to.UsuarioTO;
import pe.lindley.preliquidacion.ws.proxy.CalificarDocumentoProxy;
import pe.lindley.preliquidacion.ws.proxy.CerrarOrdenCargaProxy;
import pe.lindley.preliquidacion.ws.proxy.ListarAvanceProxy;
import pe.lindley.preliquidacion.ws.proxy.ListarDocumentoProxy;
import pe.lindley.preliquidacion.ws.proxy.LoginProxy;

import net.msonic.lib.DBHelper;
import net.msonic.lib.DBHelperProvider;
import com.google.inject.Module;
import com.google.inject.Singleton;

import roboguice.application.RoboApplication;
import roboguice.config.AbstractAndroidModule;

public class RTMApplication extends RoboApplication {

	private UsuarioTO usuarioTO;
	

	public UsuarioTO getUsuarioTO() {
		return usuarioTO;
	}


	public void setUsuarioTO(UsuarioTO usuarioTO) {
		this.usuarioTO = usuarioTO;
	}


	@Override
	protected void addApplicationModules(List<Module> modules){	
		
		modules.add(new AbstractAndroidModule(){
			@Override
			protected void configure() {
				
				bind(DBHelper.class).toProvider(DBHelperProvider.class).in(Singleton.class);
				
				bind(ListarDocumentoProxy.class).in(Singleton.class);
				bind(LoginProxy.class).in(Singleton.class);
				bind(CalificarDocumentoProxy.class).in(Singleton.class);
				bind(ListarAvanceProxy.class).in(Singleton.class);
				bind(CerrarOrdenCargaProxy.class).in(Singleton.class);
								
				bind(DocumentoDAO.class).in(Singleton.class);
				bind(DocumentoBLL.class).in(Singleton.class);
				
				bind(MotivoDAO.class).in(Singleton.class);
				bind(MotivoBLL.class).in(Singleton.class);
				
				
			}
		});
	}
}
