package pe.lindley.mmil.titanium;

import pe.lindley.mmil.titanium.ws.service.ClientesCreditosProxy;
import pe.lindley.mmil.titanium.ws.service.ConfrontacionProxy;
import pe.lindley.mmil.titanium.ws.service.ConsultarClienteProxy;
import pe.lindley.mmil.titanium.ws.service.ListarMapVendedorProxy;
import pe.lindley.mmil.titanium.ws.service.ListarSupervisorProxy;
import pe.lindley.mmil.titanium.ws.service.ListarVendedorProxy;
import pe.lindley.mmil.titanium.ws.service.LoginProxy;
import pe.lindley.mmil.titanium.ws.service.MixProductoProxy;
import pe.lindley.mmil.titanium.ws.service.MostrarPizarraProxy;
import pe.lindley.mmil.titanium.ws.service.ObtenerFiguraComercialProxy;
import pe.lindley.mmil.titanium.ws.service.PedidoServiceProxy;
import pe.lindley.mmil.titanium.ws.service.ProfitHistoryDetalleProxy;
import pe.lindley.mmil.titanium.ws.service.ProfitHistoryProxy;
import pe.lindley.mmil.titanium.ws.service.ResumenGerenteVentasDepositosProxy;
import pe.lindley.mmil.titanium.ws.service.ResumenGerenteVentasProxy;
import pe.lindley.mmil.titanium.ws.service.ResumenJefeComercialesProxy;
import pe.lindley.mmil.titanium.ws.service.ResumenMercaderistasProxy;
import pe.lindley.mmil.titanium.ws.service.ResumenVendedoresProxy;
import pe.lindley.mmil.titanium.ws.service.ResumenAdminFranquiciaProxy;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;

public class Modulo extends AbstractModule {

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
		bind(MixProductoProxy.class).in(Singleton.class);
		bind(PedidoServiceProxy.class).in(Singleton.class);
		bind(ConfrontacionProxy.class).in(Singleton.class);
		bind(ClientesCreditosProxy.class).in(Singleton.class);
		bind(ConsultarClienteProxy.class).in(Singleton.class);
		bind(ProfitHistoryDetalleProxy.class).in(Singleton.class);
		bind(ObtenerFiguraComercialProxy.class).in(Singleton.class);
		bind(ProfitHistoryProxy.class).in(Singleton.class);
		bind(ResumenAdminFranquiciaProxy.class).in(Singleton.class);
		bind(ResumenJefeComercialesProxy.class).in(Singleton.class);
		bind(ResumenGerenteVentasProxy.class).in(Singleton.class);
		bind(ResumenGerenteVentasDepositosProxy.class).in(Singleton.class);
		
		
	
	}

}
