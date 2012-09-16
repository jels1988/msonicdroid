package lindley.desarrolloxcliente;

import javax.inject.Singleton;

import net.msonic.lib.DBHelper;
import net.msonic.lib.DBHelperProvider;

import com.google.inject.AbstractModule;

import lindley.desarrolloxcliente.dao.AccionTradeDAO;
import lindley.desarrolloxcliente.dao.ClienteDAO;
import lindley.desarrolloxcliente.dao.EvaluacionDAO;
import lindley.desarrolloxcliente.dao.FotoClusterDAO;
import lindley.desarrolloxcliente.dao.FotoDAO;
import lindley.desarrolloxcliente.dao.OportunidadDAO;
import lindley.desarrolloxcliente.dao.PosicionDAO;
import lindley.desarrolloxcliente.dao.PresentacionDAO;
import lindley.desarrolloxcliente.negocio.AccionTradeBLL;
import lindley.desarrolloxcliente.negocio.ClienteBLL;
import lindley.desarrolloxcliente.negocio.EvaluacionBLL;
import lindley.desarrolloxcliente.negocio.FotoBLL;
import lindley.desarrolloxcliente.negocio.FotoClusterBLL;
import lindley.desarrolloxcliente.negocio.OportunidadBLL;
import lindley.desarrolloxcliente.negocio.PosicionBLL;
import lindley.desarrolloxcliente.negocio.PresentacionBLL;

import lindley.desarrolloxcliente.to.PeriodoTO;
import lindley.desarrolloxcliente.ws.service.ActualizarCompromisoProxy;
import lindley.desarrolloxcliente.ws.service.ActualizarEstadoProxy;
import lindley.desarrolloxcliente.ws.service.CerrarCompromisoProxy;
import lindley.desarrolloxcliente.ws.service.ConsultarArticulosCanjeProxy;
import lindley.desarrolloxcliente.ws.service.ConsultarCompromisoProxy;
import lindley.desarrolloxcliente.ws.service.ConsultarEvaluacionesAbiertasProxy;
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


public class MyCustomModule extends AbstractModule {

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
		bind(ConsultarEvaluacionesAbiertasProxy.class).in(Singleton.class);
		
						
		bind(FotoBLL.class).in(Singleton.class);
		bind(FotoDAO.class).in(Singleton.class);
		
		
		bind(OportunidadBLL.class).in(Singleton.class);
		bind(OportunidadDAO.class).in(Singleton.class);
		

		bind(ClienteBLL.class).in(Singleton.class);
		bind(ClienteDAO.class).in(Singleton.class);
		
		bind(EvaluacionBLL.class).in(Singleton.class);
		bind(EvaluacionDAO.class).in(Singleton.class);
		
		bind(AccionTradeBLL.class).in(Singleton.class);
		bind(AccionTradeDAO.class).in(Singleton.class);
		
		bind(PosicionBLL.class).in(Singleton.class);
		bind(PosicionDAO.class).in(Singleton.class);
		
		
		bind(PresentacionBLL.class).in(Singleton.class);
		bind(PresentacionDAO.class).in(Singleton.class);
		
		bind(FotoClusterBLL.class).in(Singleton.class);
		bind(FotoClusterDAO.class).in(Singleton.class);
		
		
		
		bind(PeriodoTO.class).in(Singleton.class);
		
		
		
		
	}

}
