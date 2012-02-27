package pe.lindley.activity;

import java.util.ArrayList;
import java.util.List;
import pe.lindley.equipofrio.ws.service.EquiposFrioProxy;
import pe.lindley.exmedia.ws.service.ConsultarFilesProxy;
import pe.lindley.ficha.negocio.OpcionMultipleBLL;
import pe.lindley.ficha.to.OpcionMultipleTO;
import pe.lindley.lanzador.adapter.TablaTOAdapter;
import pe.lindley.lanzador.adapter.UbigeoTOAdapter;
import pe.lindley.lanzador.dao.TablaDAO;
import pe.lindley.lanzador.dao.UbigeoDAO;
import pe.lindley.lanzador.negocio.TablaBLL;
import pe.lindley.lanzador.negocio.UbigeoBLL;
import pe.lindley.lanzador.to.ClienteResumenTO;
import pe.lindley.lanzador.to.TablaTO;
import pe.lindley.lanzador.to.UbigeoTO;
import pe.lindley.lanzador.to.UsuarioTO;
import pe.lindley.lanzador.ws.service.ConsultarClienteProxy;
import pe.lindley.lanzador.ws.service.LoginProxy;
import pe.lindley.om.adapter.ParametroTOAdapter;
import pe.lindley.om.dao.ParametroDAO;
import pe.lindley.om.dao.RolDAO;
import pe.lindley.om.negocio.ParametroBLL;
import pe.lindley.om.negocio.RolBLL;
import pe.lindley.om.to.ParametroTO;
import pe.lindley.om.ws.service.DescargarClienteProxy;
import pe.lindley.om.ws.service.DescargarEventosOMProxy;
import pe.lindley.om.ws.service.DescargarOrdenesOMProxy;
import pe.lindley.om.ws.service.DescargarParametrosProxy;
import pe.lindley.om.ws.service.DescargarRolesProxy;
import pe.lindley.om.ws.service.EnviarOrdenesProxy;
import pe.lindley.plandesarrollo.ws.service.GuardarActividadProxy;
import pe.lindley.profit.ws.service.ProfitFamiliaMarcaArticuloProxy;
import pe.lindley.profit.ws.service.ProfitFamiliaMarcaProxy;
import pe.lindley.profit.ws.service.ProfitHistoryArticuloProxy;
import pe.lindley.profit.ws.service.ProfitHistoryDetalleProxy;
import pe.lindley.profit.ws.service.ProfitHistoryProxy;
import pe.lindley.profit.ws.service.ProfitSegmentoMarcaArticuloProxy;
import pe.lindley.profit.ws.service.ProfitSegmentoMarcaProxy;
import pe.lindley.prospector.dao.ClienteDAO;
import pe.lindley.prospector.negocio.ClienteBLL;
import pe.lindley.prospector.ws.service.DatosClienteProxy;
import pe.lindley.prospector.ws.service.FichasRechazadasProxy;
import pe.lindley.prospector.ws.service.GuardarClienteProxy;
import pe.lindley.provider.DBHelperProvider;
import pe.lindley.puntointeres.ws.service.ActualizarPuntoInteresProxy;
import pe.lindley.puntointeres.ws.service.GuardarPuntoInteresProxy;
import pe.lindley.red.ws.service.ConsultarComunicacionProxy;
import pe.lindley.red.ws.service.ConsultarEncuestaProxy;
import pe.lindley.red.ws.service.ConsultarFichaProxy;
import pe.lindley.red.ws.service.ConsultarFotoProxy;
import pe.lindley.red.ws.service.ConsultarGeneradorProxy;
import pe.lindley.red.ws.service.ConsultarIndiceEjecucionAnioProxy;
import pe.lindley.red.ws.service.ConsultarIndiceEjecucionClienteProxy;
import pe.lindley.red.ws.service.ConsultarIndiceEjecucionMatrizProxy;
import pe.lindley.red.ws.service.ConsultarInventarioPrecioMarcadoProxy;
import pe.lindley.red.ws.service.ConsultarInventarioPrecioSugeridoProxy;
import pe.lindley.red.ws.service.ConsultarInventarioPuntoContactoProxy;
import pe.lindley.red.ws.service.ConsultarOrdenTrabajoProxy;
import pe.lindley.red.ws.service.ConsultarPreguntaProxy;
import pe.lindley.red.ws.service.ConsultarSKUPropietarioProxy;
import pe.lindley.red.ws.service.ConsultarSoviCategoriaProxy;
import pe.lindley.red.ws.service.ConsultarSoviCoreProxy;
import pe.lindley.red.ws.service.ConsultarSoviEmpresaProxy;
import pe.lindley.red.ws.service.ConsultarSoviPackageProxy;
import pe.lindley.red.ws.service.ConsultarSoviPuntoContactoProxy;
import pe.lindley.util.DBHelper;
import pe.lindley.ventacero.ws.service.GuardarVtaCeroMotivoProxy;
import pe.lindley.ventacero.ws.service.ObtenerParametroProxy;
import pe.lindley.ventacero.ws.service.ObtenerVentaCeroProxy;
import roboguice.application.RoboApplication;
import roboguice.config.AbstractAndroidModule;


import com.google.inject.Inject;
import com.google.inject.Module;
import com.google.inject.Singleton;

public class RTMApplication extends RoboApplication {

	private UsuarioTO usuarioTO;
	private ClienteResumenTO clienteTO;

	public ClienteResumenTO getClienteTO() {
		return clienteTO;
	}

	public void setClienteTO(ClienteResumenTO clienteTO) {
		this.clienteTO = clienteTO;
	}

	public UsuarioTO getUsuarioTO() {
		return usuarioTO;
	}

	public void setUsuarioTO(UsuarioTO usuarioTO) {
		this.usuarioTO = usuarioTO;
	}
	
	
	@Inject TablaBLL 	tablaBLL;
	@Inject UbigeoBLL 	ubigeoBLL;
	@Inject ParametroBLL 	parametroBLL;
	@Inject OpcionMultipleBLL 	opcionMultipleBLL;
	@Inject pe.lindley.plandesarrollo.negocio.ParametroBLL pDesarrolloParametroBLL;
	@Inject pe.lindley.ventacero.negocio.ParametroBLL ventaCeroParametroBLL;
	@Inject pe.lindley.profit.negocio.ParametroBLL profitParametroBLL;
	@Inject pe.lindley.puntointeres.negocio.ParametroBLL pintParametroBLL;
	
	private pe.lindley.om.adapter.ParametroTOAdapter tipoContactoAdapter;
	public synchronized pe.lindley.om.adapter.ParametroTOAdapter getTipoContactoAdapter(){
		if(tipoContactoAdapter==null){
			ArrayList<ParametroTO> parametros = parametroBLL.list("11");
			tipoContactoAdapter = new ParametroTOAdapter(getApplicationContext(), parametros);
		}
		
		return tipoContactoAdapter;
	}
	
	private pe.lindley.om.adapter.ParametroTOAdapter tipoOrdenAdapter;
	public synchronized pe.lindley.om.adapter.ParametroTOAdapter getTipoOrdenAdapter(){
		if(tipoOrdenAdapter==null){
			ArrayList<ParametroTO> parametros = parametroBLL.list("8");
			tipoOrdenAdapter = new ParametroTOAdapter(getApplicationContext(), parametros);
		}
		return tipoOrdenAdapter;
	}
	
	public synchronized pe.lindley.om.adapter.ParametroTOAdapter getSubTipoOrdenAdapter(String tipoOrden){
			ArrayList<ParametroTO> parametros = parametroBLL.list("9",tipoOrden);
			pe.lindley.om.adapter.ParametroTOAdapter tipoOrdenAdapter = new ParametroTOAdapter(getApplicationContext(), parametros);
		return tipoOrdenAdapter;
	}
	
	public synchronized pe.lindley.om.adapter.ParametroTOAdapter getMotivoOrdenAdapter(String tipoOrden){
		ArrayList<ParametroTO> parametros = parametroBLL.list("10",tipoOrden);
		pe.lindley.om.adapter.ParametroTOAdapter tipoOrdenAdapter = new ParametroTOAdapter(getApplicationContext(), parametros);
		return tipoOrdenAdapter;
	}
	
	private pe.lindley.om.adapter.ParametroTOAdapter motivosAdapter;
	public synchronized pe.lindley.om.adapter.ParametroTOAdapter getMotivoEventosAdapter(){
		if(motivosAdapter==null){
			ArrayList<ParametroTO> parametros = parametroBLL.list("15");
			motivosAdapter = new ParametroTOAdapter(getApplicationContext(), parametros);
		}
		
		return motivosAdapter;
	}
	
	private pe.lindley.om.adapter.ParametroTOAdapter tipoPrioridasAdapter;
	public synchronized pe.lindley.om.adapter.ParametroTOAdapter getTipoPrioridadAdapter(){
		if(tipoPrioridasAdapter==null){
			ArrayList<ParametroTO> parametros = parametroBLL.list("12");
			tipoPrioridasAdapter = new ParametroTOAdapter(getApplicationContext(), parametros);
		}
		return tipoPrioridasAdapter;
	}
	
	private pe.lindley.om.adapter.ParametroTOAdapter estadoAdapter;
	public synchronized pe.lindley.om.adapter.ParametroTOAdapter getEstadoAdapter(){
		if(estadoAdapter==null){
			ArrayList<ParametroTO> parametros = parametroBLL.list("13");
			estadoAdapter = new ParametroTOAdapter(getApplicationContext(), parametros);
		}
		return estadoAdapter;
	}
	
	
	
	private TablaTOAdapter adapterDistribuidor;
	public synchronized TablaTOAdapter getAdapterDistribuidor(){
		if(adapterDistribuidor==null){
			
			ArrayList<TablaTO> listSubCanal = tablaBLL.Listar("002");
			TablaTO tablaTO = new TablaTO();
			tablaTO.setCodigo("0");
			tablaTO.setDescripcion("--Seleccionar--");
			listSubCanal.add(0,tablaTO);
			adapterDistribuidor = new TablaTOAdapter(getApplicationContext(),listSubCanal );
		}
		return adapterDistribuidor;
	}
	
	private TablaTOAdapter adapterDistritoComercial;
	public synchronized TablaTOAdapter getAdapterDistritoComercial(){
		if(adapterDistritoComercial==null){
			
			ArrayList<TablaTO> listSubCanal = tablaBLL.Listar("003");
			TablaTO tablaTO = new TablaTO();
			tablaTO.setCodigo("0");
			tablaTO.setDescripcion("--Seleccionar--");
			listSubCanal.add(0,tablaTO);
			adapterDistritoComercial = new TablaTOAdapter(getApplicationContext(),listSubCanal );
		}
		return adapterDistritoComercial;
	}
	
	private TablaTOAdapter adapterDistritoGeografico;
	public synchronized TablaTOAdapter getAdapterDistritoGeografico(){
		if(adapterDistritoGeografico==null){
			
			ArrayList<TablaTO> listSubCanal = tablaBLL.Listar("004");
			TablaTO tablaTO = new TablaTO();
			tablaTO.setCodigo("0");
			tablaTO.setDescripcion("--Seleccionar--");
			listSubCanal.add(0,tablaTO);
			adapterDistritoGeografico = new TablaTOAdapter(getApplicationContext(),listSubCanal );
		}
		return adapterDistritoGeografico;
	}
	
	private TablaTOAdapter adapterZona;
	public synchronized TablaTOAdapter getAdapterZona(){
		if(adapterZona==null){
			
			ArrayList<TablaTO> listSubCanal = tablaBLL.Listar("007");
			TablaTO tablaTO = new TablaTO();
			tablaTO.setCodigo("0");
			tablaTO.setDescripcion("--Seleccionar--");
			listSubCanal.add(0,tablaTO);
			adapterZona = new TablaTOAdapter(getApplicationContext(),listSubCanal );
		}
		return adapterZona;
	}
	
	private TablaTOAdapter adapterRutaIK;
	public synchronized TablaTOAdapter getadapterRutaIK(){
		if(adapterRutaIK==null){
			
			ArrayList<TablaTO> listSubCanal = tablaBLL.Listar("005");
			TablaTO tablaTO = new TablaTO();
			tablaTO.setCodigo("0");
			tablaTO.setDescripcion("--Seleccionar--");
			listSubCanal.add(0,tablaTO);
			adapterRutaIK = new TablaTOAdapter(getApplicationContext(),listSubCanal );
		}
		return adapterRutaIK;
	}
	
	private TablaTOAdapter adapterSegmento;
	public synchronized TablaTOAdapter getSegmento(){
		if(adapterSegmento==null){
			
			ArrayList<TablaTO> listSubCanal = tablaBLL.Listar("006");
			TablaTO tablaTO = new TablaTO();
			tablaTO.setCodigo("0");
			tablaTO.setDescripcion("--Seleccionar--");
			listSubCanal.add(0,tablaTO);
			adapterSegmento = new TablaTOAdapter(getApplicationContext(),listSubCanal );
		}
		return adapterSegmento;
	}
	
	private TablaTOAdapter adapterUbicacion;
	public synchronized TablaTOAdapter getUbicacion(){
		if(adapterUbicacion==null){
			
			ArrayList<TablaTO> listSubCanal = tablaBLL.Listar("008");
			TablaTO tablaTO = new TablaTO();
			tablaTO.setCodigo("0");
			tablaTO.setDescripcion("--Seleccionar--");
			listSubCanal.add(0,tablaTO);
			adapterUbicacion = new TablaTOAdapter(getApplicationContext(),listSubCanal );
		}
		return adapterUbicacion;
	}
	
	private TablaTOAdapter adapterSubCanal;
	public synchronized TablaTOAdapter getAdapterSubCanal(){
		if(adapterSubCanal==null){
			
			
			ArrayList<TablaTO> listSubCanal = tablaBLL.Listar("001");
			TablaTO tablaTO = new TablaTO();
			tablaTO.setCodigo("0");
			tablaTO.setDescripcion("--Seleccionar--");
			listSubCanal.add(0,tablaTO);
			adapterSubCanal = new TablaTOAdapter(getApplicationContext(),listSubCanal );
		}
		return adapterSubCanal;
	}
	
	private TablaTOAdapter adapterTamanio;
	public synchronized TablaTOAdapter getAdapterTamanio(){
		if(adapterTamanio==null){
			ArrayList<TablaTO> listTamanio = tablaBLL.Listar("010");
			TablaTO tablaTO = new TablaTO();
			tablaTO.setCodigo("0");
			tablaTO.setDescripcion("--Seleccionar--");
			listTamanio.add(0,tablaTO);
			adapterTamanio = new TablaTOAdapter(getApplicationContext(), listTamanio);
		}
		return adapterTamanio;
	}
	
	
	
	private UbigeoTOAdapter ubigeoTOSpinnerAdapter;
	public synchronized UbigeoTOAdapter spinnerDepartamentos(){
		if(ubigeoTOSpinnerAdapter==null){
			ArrayList<UbigeoTO> departamentos = ubigeoBLL.listarDepartamentos();
			UbigeoTO ubigeoTO = new UbigeoTO();
		 	ubigeoTO.setCodigo("0");
		 	ubigeoTO.setDescripcion("--Seleccionar--");
		 	departamentos.add(0, ubigeoTO);
			ubigeoTOSpinnerAdapter =  new UbigeoTOAdapter(getApplicationContext(), departamentos);
		}
		return ubigeoTOSpinnerAdapter;
	}
	
	public UbigeoTOAdapter spinnerProvincias(String codigoDepartamento){
		ArrayList<UbigeoTO> provincias = ubigeoBLL.listarProvincia(codigoDepartamento);
		UbigeoTO ubigeoTO = new UbigeoTO();
	 	ubigeoTO.setCodigo("0");
	 	ubigeoTO.setDescripcion("--Seleccionar--");
	 	provincias.add(0, ubigeoTO);
	 	
		UbigeoTOAdapter ubigeoTOSpinnerAdapter = new UbigeoTOAdapter(getApplicationContext(), provincias);
		
		return ubigeoTOSpinnerAdapter;
	}
	
	public UbigeoTOAdapter spinnerDistrito(String codigoDistito){
		ArrayList<UbigeoTO> distritos = ubigeoBLL.listarDistrito(codigoDistito);
		UbigeoTO ubigeoTO = new UbigeoTO();
	 	ubigeoTO.setCodigo("0");
	 	ubigeoTO.setDescripcion("--Seleccionar--");
	 	distritos.add(0, ubigeoTO);
	 	
		UbigeoTOAdapter ubigeoTOSpinnerAdapter = new UbigeoTOAdapter(getApplicationContext(), distritos);
		
		return ubigeoTOSpinnerAdapter;
	}
	
	public  boolean validarRuc(String ruc){
		
		if(ruc.length()!=11){
			return false;
		}
		
		byte[] valores = { 2, 3, 4, 5, 6, 7, 2, 3, 4, 5 };
		byte NUMEROEVALUAR = 9;
		
		 int peso = 0;
         for (int i = 0; i <= NUMEROEVALUAR; i++)
         {
             String digito = ruc.substring(i, i + 1);
             peso = peso + Integer.valueOf(digito) * valores[NUMEROEVALUAR - i];
             
         }
         
         int digitControl = 11 - (peso % 11);
         
         if (digitControl == 11)
             digitControl = 1;
         else if (digitControl == 10)
             digitControl = 0;
         
         return String.valueOf(digitControl).equalsIgnoreCase(ruc.substring(10,11));
         
         
		
	}
    
	public synchronized pe.lindley.ficha.adapter.ParametroTOAdapter getAdapterParametrosFicha(String codOpcion){
		ArrayList<OpcionMultipleTO> opcionMultiple = opcionMultipleBLL.list(codOpcion);
		OpcionMultipleTO opcionMultipleTO = new OpcionMultipleTO();
		opcionMultipleTO.setCodigo("0");
		opcionMultipleTO.setDescripcion("--Seleccionar--");
		opcionMultiple.add(0,opcionMultipleTO);
		pe.lindley.ficha.adapter.ParametroTOAdapter adapterGiroSecundario  = new pe.lindley.ficha.adapter.ParametroTOAdapter(getApplicationContext(),opcionMultiple );
		return adapterGiroSecundario;
	}
	
	public synchronized pe.lindley.plandesarrollo.adapter.ParametroTOAdapter getAdapterParametrosPlanDesarrollo(String codOpcion){
		ArrayList<pe.lindley.plandesarrollo.to.ParametroTO> listaparametro = pDesarrolloParametroBLL.list(codOpcion);
		pe.lindley.plandesarrollo.to.ParametroTO parametroTO = new pe.lindley.plandesarrollo.to.ParametroTO();
		parametroTO.setCodigo("0");
		parametroTO.setDescripcion("--Seleccionar--");
		listaparametro.add(0,parametroTO);
		pe.lindley.plandesarrollo.adapter.ParametroTOAdapter adapterPlanDesarrollo  = new pe.lindley.plandesarrollo.adapter.ParametroTOAdapter(getApplicationContext(),listaparametro );
		return adapterPlanDesarrollo;
	}
	
	public synchronized pe.lindley.ventacero.adapter.ParametroTOAdapter getAdapterParametrosVentaCero(String codOpcion){
		ArrayList<pe.lindley.ventacero.to.ParametroTO> listaparametro = ventaCeroParametroBLL.list(codOpcion);
		pe.lindley.ventacero.to.ParametroTO parametroTO = new pe.lindley.ventacero.to.ParametroTO();
		parametroTO.setCodigo("0");
		parametroTO.setDescripcion("--Seleccionar--");
		listaparametro.add(0,parametroTO);
		pe.lindley.ventacero.adapter.ParametroTOAdapter adapterVentaCero  = new pe.lindley.ventacero.adapter.ParametroTOAdapter(getApplicationContext(),listaparametro );
		return adapterVentaCero;
	}
	
	public synchronized pe.lindley.ventacero.adapter.ParametroTOAdapter getAdapterParametrosVentaCero(String codOpcion, String codigo, String subcodigo){
		ArrayList<pe.lindley.ventacero.to.ParametroTO> listaparametro = ventaCeroParametroBLL.list(codOpcion, codigo, subcodigo);
		pe.lindley.ventacero.to.ParametroTO parametroTO = new pe.lindley.ventacero.to.ParametroTO();
		parametroTO.setCodigo("0");
		parametroTO.setDescripcion("--Seleccionar--");
		listaparametro.add(0,parametroTO);
		pe.lindley.ventacero.adapter.ParametroTOAdapter adapterVentaCero  = new pe.lindley.ventacero.adapter.ParametroTOAdapter(getApplicationContext(),listaparametro );
		return adapterVentaCero;
	}
	
	public synchronized pe.lindley.profit.adapter.ParametroTOAdapter getAdapterParametrosProfit(String codOpcion){
		ArrayList<pe.lindley.profit.to.ParametroTO> listaparametro = profitParametroBLL.list(codOpcion);
		pe.lindley.profit.to.ParametroTO parametroTO = new pe.lindley.profit.to.ParametroTO();
		parametroTO.setCodigoTabla("0");
		parametroTO.setCodigo("0");
		parametroTO.setDescripcion("--Seleccionar--");
		listaparametro.add(0,parametroTO);
		pe.lindley.profit.adapter.ParametroTOAdapter adapterVentaCero  = new pe.lindley.profit.adapter.ParametroTOAdapter(getApplicationContext(),listaparametro );
		return adapterVentaCero;
	}
	
	public synchronized pe.lindley.profit.adapter.ParametroTOAdapter getAdapterParametrosProfit(String codOpcion, String codigo){
		ArrayList<pe.lindley.profit.to.ParametroTO> listaparametro = profitParametroBLL.list(codOpcion, codigo);
		pe.lindley.profit.to.ParametroTO parametroTO = new pe.lindley.profit.to.ParametroTO();
		parametroTO.setCodigoTabla("0");
		parametroTO.setCodigo("0");
		parametroTO.setDescripcion("--Seleccionar--");
		listaparametro.add(0,parametroTO);
		pe.lindley.profit.adapter.ParametroTOAdapter adapterVentaCero  = new pe.lindley.profit.adapter.ParametroTOAdapter(getApplicationContext(),listaparametro );
		return adapterVentaCero;
	}
		
	public synchronized pe.lindley.puntointeres.adapter.ParametroTOAdapter getAdapterParametrosPINT(String codOpcion){
		ArrayList<pe.lindley.puntointeres.to.ParametroTO> listaparametro = pintParametroBLL.list(codOpcion);
		pe.lindley.puntointeres.to.ParametroTO parametroTO = new pe.lindley.puntointeres.to.ParametroTO();
		parametroTO.setCodigo("0");
		parametroTO.setDescripcion("--Seleccionar--");
		listaparametro.add(0,parametroTO);
		pe.lindley.puntointeres.adapter.ParametroTOAdapter adapterVentaCero  = new pe.lindley.puntointeres.adapter.ParametroTOAdapter(getApplicationContext(),listaparametro );
		return adapterVentaCero;
	}
	
	public synchronized pe.lindley.puntointeres.adapter.ParametroTOAdapter getAdapterParametrosPINT(String codOpcion, String codigo){
		ArrayList<pe.lindley.puntointeres.to.ParametroTO> listaparametro = pintParametroBLL.list(codOpcion, codigo);
		pe.lindley.puntointeres.to.ParametroTO parametroTO = new pe.lindley.puntointeres.to.ParametroTO();
		parametroTO.setCodigo("0");
		parametroTO.setDescripcion("--Seleccionar--");
		listaparametro.add(0,parametroTO);
		pe.lindley.puntointeres.adapter.ParametroTOAdapter adapterVentaCero  = new pe.lindley.puntointeres.adapter.ParametroTOAdapter(getApplicationContext(),listaparametro );
		return adapterVentaCero;
	}
	
	@Override
	protected void addApplicationModules(List<Module> modules){	
		
		modules.add(new AbstractAndroidModule(){
			@Override
			protected void configure() {
				// TODO Auto-generated method stub
				bind(LoginProxy.class).in(Singleton.class);
				bind(ConsultarClienteProxy.class).in(Singleton.class);
				bind(DatosClienteProxy.class).in(Singleton.class);
				bind(GuardarClienteProxy.class).in(Singleton.class);
				
				//Profit
				bind(ProfitHistoryProxy.class).in(Singleton.class);
				bind(ProfitHistoryDetalleProxy.class).in(Singleton.class);
				bind(pe.lindley.profit.ws.service.DescargarParametrosProxy.class).in(Singleton.class);
				bind(ProfitFamiliaMarcaArticuloProxy.class).in(Singleton.class);
				bind(ProfitFamiliaMarcaProxy.class).in(Singleton.class);
				bind(ProfitHistoryArticuloProxy.class).in(Singleton.class);
				bind(ProfitSegmentoMarcaArticuloProxy.class).in(Singleton.class);
				bind(ProfitSegmentoMarcaProxy.class).in(Singleton.class);
				
				bind(pe.lindley.profit.dao.ParametroDAO.class).in(Singleton.class);
				bind(pe.lindley.profit.negocio.ParametroBLL.class).in(Singleton.class);
				
				
				
				
				bind(EquiposFrioProxy.class).in(Singleton.class);
				bind(FichasRechazadasProxy.class).in(Singleton.class);
				bind(DescargarParametrosProxy.class).in(Singleton.class);
				bind(DescargarRolesProxy.class).in(Singleton.class);
				bind(DescargarClienteProxy.class).in(Singleton.class);
				bind(DescargarOrdenesOMProxy.class).in(Singleton.class);
				bind(DescargarEventosOMProxy.class).in(Singleton.class);
				bind(EnviarOrdenesProxy.class).in(Singleton.class);
				
				bind(ConsultarEncuestaProxy.class).in(Singleton.class);
				bind(ConsultarFichaProxy.class).in(Singleton.class);
				bind(ConsultarFotoProxy.class).in(Singleton.class);
				
				bind(ConsultarIndiceEjecucionAnioProxy.class).in(Singleton.class);
				bind(ConsultarIndiceEjecucionClienteProxy.class).in(Singleton.class);
				bind(ConsultarIndiceEjecucionMatrizProxy.class).in(Singleton.class);
				bind(ConsultarSKUPropietarioProxy.class).in(Singleton.class);
				bind(ConsultarSoviCoreProxy.class).in(Singleton.class);
				bind(ConsultarSoviEmpresaProxy.class).in(Singleton.class);
				bind(ConsultarSoviPackageProxy.class).in(Singleton.class);
				bind(ConsultarSoviPuntoContactoProxy.class).in(Singleton.class);
				bind(ConsultarFilesProxy.class).in(Singleton.class);
				
				bind(DBHelper.class).toProvider(DBHelperProvider.class).in(Singleton.class);
				
				bind(TablaDAO.class).in(Singleton.class);
				bind(TablaBLL.class).in(Singleton.class);
				
				bind(UbigeoDAO.class).in(Singleton.class);
				bind(UbigeoBLL.class).in(Singleton.class);
				
				bind(ClienteDAO.class).in(Singleton.class);
				bind(ClienteBLL.class).in(Singleton.class);
				
				bind(ParametroDAO.class).in(Singleton.class);
				bind(ParametroBLL.class).in(Singleton.class);
				
				bind(RolDAO.class).in(Singleton.class);
				bind(RolBLL.class).in(Singleton.class);
				
				bind(pe.lindley.om.dao.ClienteDAO.class).in(Singleton.class);
				bind(pe.lindley.om.negocio.ClienteBLL.class).in(Singleton.class);
				
				bind(pe.lindley.om.dao.EventoDAO.class).in(Singleton.class);
				bind(pe.lindley.om.dao.OrdenTrabajoDAO.class).in(Singleton.class);
				bind(pe.lindley.om.negocio.OrdenTrabajoBLL.class).in(Singleton.class);
				
				
				
				
				
				//Ficha
				bind(pe.lindley.ficha.ws.service.ActualizarClienteProxy.class).in(Singleton.class);
				bind(pe.lindley.ficha.ws.service.ActualizarComercialProxy.class).in(Singleton.class);
				bind(pe.lindley.ficha.ws.service.ActualizarContactoProxy.class).in(Singleton.class);
				bind(pe.lindley.ficha.ws.service.ActualizarEncuestaProxy.class).in(Singleton.class);
				bind(pe.lindley.ficha.ws.service.ActualizarVisualizacionProxy.class).in(Singleton.class);
				bind(pe.lindley.ficha.ws.service.CerrarEncuestaProxy.class).in(Singleton.class);
				bind(pe.lindley.ficha.ws.service.GuardarContactoProxy.class).in(Singleton.class);
				bind(pe.lindley.ficha.ws.service.GuardarEncuestaProxy.class).in(Singleton.class);
				bind(pe.lindley.ficha.ws.service.GuardarVisualizacionProxy.class).in(Singleton.class);
				bind(pe.lindley.ficha.ws.service.ObtenerClienteProxy.class).in(Singleton.class);
				bind(pe.lindley.ficha.ws.service.ObtenerComercialProxy.class).in(Singleton.class);
				bind(pe.lindley.ficha.ws.service.ObtenerContactoProxy.class).in(Singleton.class);
				bind(pe.lindley.ficha.ws.service.ObtenerEncuestaProxy.class).in(Singleton.class);
				bind(pe.lindley.ficha.ws.service.ObtenerFiguraComercialProxy.class).in(Singleton.class);
				bind(pe.lindley.ficha.ws.service.ObtenerOpcionMultipleProxy.class).in(Singleton.class);
				bind(pe.lindley.ficha.ws.service.ObtenerVisualizacionProxy.class).in(Singleton.class);		
				bind(pe.lindley.ficha.ws.service.ConsultarEncuestasProxy.class).in(Singleton.class);		
				
				bind(pe.lindley.ficha.dao.OpcionMultipleDAO.class).in(Singleton.class);
				bind(pe.lindley.ficha.negocio.OpcionMultipleBLL.class).in(Singleton.class);
				
				//MMIL
				bind(pe.lindley.mmil.ws.service.ConfigurarServicioProxy.class).in(Singleton.class);
				bind(pe.lindley.mmil.ws.service.ListarCdaProxy.class).in(Singleton.class);
				bind(pe.lindley.mmil.ws.service.ListarMapVendedorProxy.class).in(Singleton.class);
				bind(pe.lindley.mmil.ws.service.ListarPoligonoMapProxy.class).in(Singleton.class);
				bind(pe.lindley.mmil.ws.service.ListarRegionProxy.class).in(Singleton.class);
				bind(pe.lindley.mmil.ws.service.ListarSupervisorProxy.class).in(Singleton.class);
				bind(pe.lindley.mmil.ws.service.ListarVendedorProxy.class).in(Singleton.class);
				bind(pe.lindley.mmil.ws.service.MostrarDashboardCdaProxy.class).in(Singleton.class);
				bind(pe.lindley.mmil.ws.service.MostrarDashboardVendedorProxy.class).in(Singleton.class);
				bind(pe.lindley.mmil.ws.service.MostrarPizarraCdaProxy.class).in(Singleton.class);
				bind(pe.lindley.mmil.ws.service.MostrarPizarraVendedorProxy.class).in(Singleton.class);
	
				//Plan Desarrollo
				bind(GuardarActividadProxy.class).in(Singleton.class);
				bind(pe.lindley.plandesarrollo.ws.service.GuardarPlanProxy.class).in(Singleton.class);
				bind(pe.lindley.plandesarrollo.ws.service.GuardarResponsableProxy.class).in(Singleton.class);
				bind(pe.lindley.plandesarrollo.ws.service.ConsultarActividadProxy.class).in(Singleton.class);
				bind(pe.lindley.plandesarrollo.ws.service.ConsultarPlanProxy.class).in(Singleton.class);
				bind(pe.lindley.plandesarrollo.ws.service.ConsultarResponsableProxy.class).in(Singleton.class);
				bind(pe.lindley.plandesarrollo.ws.service.EliminarActividadProxy.class).in(Singleton.class);
				bind(pe.lindley.plandesarrollo.ws.service.EliminarPlanProxy.class).in(Singleton.class);
				bind(pe.lindley.plandesarrollo.ws.service.EliminarResponsableProxy.class).in(Singleton.class);
				bind(pe.lindley.plandesarrollo.ws.service.ActualizarActividadProxy.class).in(Singleton.class);
				bind(pe.lindley.plandesarrollo.ws.service.ActualizarPlanProxy.class).in(Singleton.class);
				bind(pe.lindley.plandesarrollo.ws.service.ActualizarResponsableProxy.class).in(Singleton.class);
				bind(pe.lindley.plandesarrollo.ws.service.DescargarParametrosProxy.class).in(Singleton.class);
				bind(pe.lindley.plandesarrollo.ws.service.ActualizarSustentoProxy.class).in(Singleton.class);
				bind(pe.lindley.plandesarrollo.ws.service.EliminarSustentoProxy.class).in(Singleton.class);
				bind(pe.lindley.plandesarrollo.ws.service.GuardarSustentoProxy.class).in(Singleton.class);
				bind(pe.lindley.plandesarrollo.ws.service.ConsultarSustentoProxy.class).in(Singleton.class);
				
								
				bind(pe.lindley.plandesarrollo.dao.ParametroDAO.class).in(Singleton.class);
				bind(pe.lindley.plandesarrollo.negocio.ParametroBLL.class).in(Singleton.class);
				
				
				//Venta Cero				
				bind(GuardarVtaCeroMotivoProxy.class).in(Singleton.class);
				bind(ObtenerVentaCeroProxy.class).in(Singleton.class);
				bind(ObtenerParametroProxy.class).in(Singleton.class);
				
				bind(pe.lindley.ventacero.dao.ParametroDAO.class).in(Singleton.class);
				bind(pe.lindley.ventacero.negocio.ParametroBLL.class).in(Singleton.class);
				
				//Puntos de Interes
				bind(pe.lindley.puntointeres.ws.service.ActualizarPuntoInteresProxy.class).in(Singleton.class);
				bind(pe.lindley.puntointeres.ws.service.GuardarPuntoInteresProxy.class).in(Singleton.class);
				bind(pe.lindley.puntointeres.ws.service.ObtenerClienteProxy.class).in(Singleton.class);
				bind(pe.lindley.puntointeres.ws.service.ObtenerPuntoInteresProxy.class).in(Singleton.class);
				bind(pe.lindley.puntointeres.ws.service.ObtenerParametroProxy.class).in(Singleton.class);
				bind(pe.lindley.puntointeres.ws.service.EliminarPuntoInteresProxy.class).in(Singleton.class);
				
				
				//SACC
				bind(pe.lindley.sacc.ws.service.ObtenerContactoProxy.class).in(Singleton.class);
				bind(pe.lindley.sacc.ws.service.ObtenerEventoProxy.class).in(Singleton.class);
				

				//RED
				
				bind(ConsultarComunicacionProxy.class).in(Singleton.class);
				bind(ConsultarInventarioPrecioSugeridoProxy.class).in(Singleton.class);
				bind(ConsultarInventarioPuntoContactoProxy.class).in(Singleton.class);
				bind(ConsultarPreguntaProxy.class).in(Singleton.class);
				bind(ConsultarGeneradorProxy.class).in(Singleton.class);				
				bind(ConsultarInventarioPrecioMarcadoProxy.class).in(Singleton.class);
				
				bind(ConsultarOrdenTrabajoProxy.class).in(Singleton.class);
				bind(ConsultarSoviCategoriaProxy.class).in(Singleton.class);
				
				
			}});
	}
}

