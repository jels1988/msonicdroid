package lindley.desarrolloxcliente;

import javax.inject.Singleton;

import net.msonic.lib.DBHelper;
import net.msonic.lib.DBHelperProvider;

import com.google.inject.AbstractModule;

import lindley.desarrolloxcliente.dao.AccionTradeDAO;
import lindley.desarrolloxcliente.dao.ClienteDAO;
import lindley.desarrolloxcliente.dao.DescargaDAO;
import lindley.desarrolloxcliente.dao.EvaluacionDAO;
import lindley.desarrolloxcliente.dao.FotoClusterDAO;
import lindley.desarrolloxcliente.dao.FotoDAO;
import lindley.desarrolloxcliente.dao.OportunidadDAO;
import lindley.desarrolloxcliente.dao.PosicionDAO;
import lindley.desarrolloxcliente.dao.PresentacionDAO;
import lindley.desarrolloxcliente.dao.UploadDAO;
import lindley.desarrolloxcliente.negocio.AccionTradeBLL;
import lindley.desarrolloxcliente.negocio.ClienteBLL;
import lindley.desarrolloxcliente.negocio.DescargaBLL;
import lindley.desarrolloxcliente.negocio.EvaluacionBLL;
import lindley.desarrolloxcliente.negocio.FotoBLL;
import lindley.desarrolloxcliente.negocio.FotoClusterBLL;
import lindley.desarrolloxcliente.negocio.OportunidadBLL;
import lindley.desarrolloxcliente.negocio.PosicionBLL;
import lindley.desarrolloxcliente.negocio.PresentacionBLL;
import lindley.desarrolloxcliente.negocio.UploadBLL;

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
import lindley.desarrolloxcliente.ws.service.DescargarAccionesTradeProductoProxy;
import lindley.desarrolloxcliente.ws.service.DescargarAccionesTradeProxy;
import lindley.desarrolloxcliente.ws.service.DescargarAceleradorProxy;
import lindley.desarrolloxcliente.ws.service.DescargarArticuloCanjeProxy;
import lindley.desarrolloxcliente.ws.service.DescargarClienteProxy;
import lindley.desarrolloxcliente.ws.service.DescargarEvaluacionOportunidadProxy;
import lindley.desarrolloxcliente.ws.service.DescargarEvaluacionPosicionCompromisoProxy;
import lindley.desarrolloxcliente.ws.service.DescargarEvaluacionPosicionProxy;
import lindley.desarrolloxcliente.ws.service.DescargarEvaluacionPresentacionProxy;
import lindley.desarrolloxcliente.ws.service.DescargarEvaluacionProxy;
import lindley.desarrolloxcliente.ws.service.DescargarEvaluacionSKUProxy;
import lindley.desarrolloxcliente.ws.service.DescargarMotivoProxy;
import lindley.desarrolloxcliente.ws.service.DescargarPosicionProxy;
import lindley.desarrolloxcliente.ws.service.DescargarPresentacionProxy;
import lindley.desarrolloxcliente.ws.service.DescargarProductosProxy;
import lindley.desarrolloxcliente.ws.service.DescargarOportunidadesProxy;
import lindley.desarrolloxcliente.ws.service.DescargarProfitClienteProxy;
import lindley.desarrolloxcliente.ws.service.DescargarProfitProxy;
import lindley.desarrolloxcliente.ws.service.DescargarPuntoProxy;
import lindley.desarrolloxcliente.ws.service.DescargarSkuProxy;
import lindley.desarrolloxcliente.ws.service.DescargarTipoActivoProxy;
import lindley.desarrolloxcliente.ws.service.GuardarDesarrolloProxy;
import lindley.desarrolloxcliente.ws.service.GuardarNuevoDesarrolloProxy;
import lindley.desarrolloxcliente.ws.service.LoginProxy;
import lindley.desarrolloxcliente.ws.service.ConsultarClienteProxy;
import lindley.desarrolloxcliente.ws.service.ConsultarCabeceraProxy;
import lindley.desarrolloxcliente.ws.service.UploadEvaluacionesProxy;


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
		bind(DescargarProductosProxy.class).in(Singleton.class);
		bind(DescargarOportunidadesProxy.class).in(Singleton.class);
		bind(DescargarSkuProxy.class).in(Singleton.class);
		bind(DescargarAccionesTradeProxy.class).in(Singleton.class);
		bind(DescargarAccionesTradeProductoProxy.class).in(Singleton.class);
		bind(DescargarClienteProxy.class).in(Singleton.class);
		bind(DescargarPosicionProxy.class).in(Singleton.class);
		bind(DescargarPresentacionProxy.class).in(Singleton.class);
		bind(DescargarPuntoProxy.class).in(Singleton.class);
		
		bind(DescargarEvaluacionProxy.class).in(Singleton.class);
		bind(DescargarEvaluacionOportunidadProxy.class).in(Singleton.class);
		bind(DescargarEvaluacionPosicionProxy.class).in(Singleton.class);
		bind(DescargarEvaluacionPresentacionProxy.class).in(Singleton.class);
		bind(DescargarEvaluacionSKUProxy.class).in(Singleton.class);
		bind(DescargarEvaluacionPosicionCompromisoProxy.class).in(Singleton.class);
		bind(DescargarProfitProxy.class).in(Singleton.class);
		bind(DescargarProfitClienteProxy.class).in(Singleton.class);
		bind(DescargarMotivoProxy.class).in(Singleton.class);
		bind(DescargarAceleradorProxy.class).in(Singleton.class);
		bind(DescargarArticuloCanjeProxy.class).in(Singleton.class);
		bind(DescargarTipoActivoProxy.class).in(Singleton.class);
		
		
		
		bind(UploadEvaluacionesProxy.class).in(Singleton.class);
		
						
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
		
		bind(DescargaBLL.class).in(Singleton.class);
		bind(DescargaDAO.class).in(Singleton.class);
		

		bind(UploadBLL.class).in(Singleton.class);
		bind(UploadDAO.class).in(Singleton.class);
		
		
	}

}
