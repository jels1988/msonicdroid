package lindley.desarrolloxcliente.activity;

import lindley.desarrolloxcliente.R;
import lindley.desarrolloxcliente.negocio.DescargaBLL;
import lindley.desarrolloxcliente.to.PeriodoTO;
import lindley.desarrolloxcliente.ws.service.DescargarAccionesTradeProductoProxy;
import lindley.desarrolloxcliente.ws.service.DescargarAccionesTradeProxy;
import lindley.desarrolloxcliente.ws.service.DescargarClienteProxy;
import lindley.desarrolloxcliente.ws.service.DescargarEvaluacionOportunidadProxy;
import lindley.desarrolloxcliente.ws.service.DescargarEvaluacionPosicionCompromisoProxy;
import lindley.desarrolloxcliente.ws.service.DescargarEvaluacionPosicionProxy;
import lindley.desarrolloxcliente.ws.service.DescargarEvaluacionPresentacionProxy;
import lindley.desarrolloxcliente.ws.service.DescargarEvaluacionProxy;
import lindley.desarrolloxcliente.ws.service.DescargarEvaluacionSKUProxy;
import lindley.desarrolloxcliente.ws.service.DescargarPosicionProxy;
import lindley.desarrolloxcliente.ws.service.DescargarPresentacionProxy;
import lindley.desarrolloxcliente.ws.service.DescargarProductosProxy;
import lindley.desarrolloxcliente.ws.service.DescargarOportunidadesProxy;
import lindley.desarrolloxcliente.ws.service.DescargarPuntoProxy;
import lindley.desarrolloxcliente.ws.service.DescargarSkuProxy;



import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;


import com.actionbarsherlock.view.Window;
import com.google.inject.Inject;

import net.msonic.lib.sherlock.ActivityBase;

public class DescargaData_Activity extends ActivityBase {

	public static final int DESCARGAR_PRODUCTO=0;
	public static final int DESCARGAR_OPORTUNIDAD=1;
	public static final int DESCARGAR_SKU=2;
	public static final int DESCARGAR_ACCIONESTRADE=3;
	public static final int DESCARGAR_ACCIONESTRADEPRODUCTO=4;
	public static final int DESCARGAR_CLIENTE=5;
	public static final int DESCARGAR_POSICION=6;
	public static final int DESCARGAR_PRESENTACION=7;
	public static final int DESCARGAR_PUNTO=8;
	public static final int DESCARGAR_EVALUACION=9;
	public static final int DESCARGAR_EVALUACION_OPORTUNIDAD=20;
	public static final int DESCARGAR_EVALUACION_POSICION=21;
	public static final int DESCARGAR_EVALUACION_PRESENTACION=22;
	public static final int DESCARGAR_EVALUACION_SKU=23;
	public static final int DESCARGAR_EVALUACION_POSICION_COMPROMISO=24;
	
	public static final int GUARDAR_PRODUCTO=10;
	public static final int GUARDAR_OPORTUNIDAD=11;
	public static final int GUARDAR_SKU=12;
	public static final int GUARDAR_ACCIONESTRADE=13;
	public static final int GUARDAR_ACCIONESTRADEPRODUCTO=14;
	public static final int GUARDAR_CLIENTE=15;
	public static final int GUARDAR_POSICION=16;
	public static final int GUARDAR_PRESENTACION=17;
	public static final int GUARDAR_PUNTO=18;
	public static final int GUARDAR_EVALUACION=19;
	public static final int GUARDAR_EVALUACION_OPORTUNIDAD=120;
	public static final int GUARDAR_EVALUACION_POSICION=121;
	public static final int GUARDAR_EVALUACION_PRESENTACION=122;
	public static final int GUARDAR_EVALUACION_SKU=123;
	public static final int GUARDAR_EVALUACION_POSICION_COMPROMISO=124;
	
	@Inject DescargarProductosProxy descagarProductosProxy;
	@Inject DescargarOportunidadesProxy descargarOportunidadesProxy;
	@Inject DescargarSkuProxy descargarSkuProxy;
	@Inject DescargarAccionesTradeProxy descargarAccionesTradeProxy;
	@Inject DescargarAccionesTradeProductoProxy descargarAccionesTradeProductoProxy;
	@Inject DescargarClienteProxy descargarClienteProxy;
	@Inject DescargarPosicionProxy descargarPosicionProxy;
	@Inject DescargarPresentacionProxy descargarPresentacionProxy;
	@Inject DescargarPuntoProxy descargarPuntoProxy;
	@Inject DescargarEvaluacionProxy descargarEvaluacionProxy;
	@Inject DescargarEvaluacionOportunidadProxy descargarEvaluacionOportunidadProxy;
	@Inject DescargarEvaluacionPosicionProxy descargarEvaluacionPosicionProxy;
	@Inject DescargarEvaluacionPresentacionProxy descargarEvaluacionPresentacionProxy;
	@Inject DescargarEvaluacionSKUProxy descargarEvaluacionSKUProxy;
	@Inject DescargarEvaluacionPosicionCompromisoProxy descargarEvaluacionPosicionCompromisoProxy;
	
	@Inject DescargaBLL descargaBLL;
	@Inject PeriodoTO periodoTO;
	private String TAG = DescargaData_Activity.class.getSimpleName();
	@Inject SharedPreferences prefs;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		inicializarRecursos();
		this.mostrarWaitMessage=false;
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
		
		setContentView(R.layout.descargadata_activity);
		
		
		
		processAsync(DESCARGAR_PRODUCTO);
		processAsync(DESCARGAR_OPORTUNIDAD);
		processAsync(DESCARGAR_SKU);
		processAsync(DESCARGAR_ACCIONESTRADE);
		processAsync(DESCARGAR_ACCIONESTRADEPRODUCTO);
		processAsync(DESCARGAR_CLIENTE);
		processAsync(DESCARGAR_POSICION);
		processAsync(DESCARGAR_PRESENTACION);
		processAsync(DESCARGAR_PUNTO);
		processAsync(DESCARGAR_EVALUACION);
		
	
	}
	
	
    
	private int contadorProcesos=0;
	
	private synchronized void addContadorProcesos(){
		contadorProcesos++;
		Log.i("up", String.valueOf(contadorProcesos));
		if(contadorProcesos>0){
			setSupportProgressBarIndeterminateVisibility(true);
		}
	}
	
	private synchronized void removeContadorProcesos(){
		contadorProcesos--;
		Log.i("dow", String.valueOf(contadorProcesos));
		if(contadorProcesos==0){
			setSupportProgressBarIndeterminateVisibility(false);
		}
	}
	
	  
		

	
	
	@Override
	protected boolean executeAsyncPre(int accion) {
		// TODO Auto-generated method stub
		
		addContadorProcesos();
		
		return super.executeAsyncPre(accion);
	}

	@Override
	protected void process(int accion) throws Exception {
		// TODO Auto-generated method stub
		periodoTO.anio=2012;
		periodoTO.mes=11;
		periodoTO.deposito="30";
		periodoTO.ruta="H1";
		
		switch (accion) {
		case DESCARGAR_PRODUCTO:
			descagarProductosProxy.execute();
			break;
		case DESCARGAR_OPORTUNIDAD:
			
			descargarOportunidadesProxy.execute();
			break;
		case DESCARGAR_SKU:
			
			descargarSkuProxy.execute();
			break;
		case DESCARGAR_ACCIONESTRADE:
			
			descargarAccionesTradeProxy.execute();
			break;
		case DESCARGAR_ACCIONESTRADEPRODUCTO:
			
			descargarAccionesTradeProductoProxy.execute();
			break;
		case DESCARGAR_CLIENTE:
			
			descargarClienteProxy.execute();
			break;
		case DESCARGAR_POSICION:
			
			descargarPosicionProxy.execute();
			break;
		case DESCARGAR_PRESENTACION:
			
			descargarPresentacionProxy.execute();
			break;
		
		case DESCARGAR_PUNTO:
			
			descargarPuntoProxy.execute();
			break;
		case DESCARGAR_EVALUACION:
			
			descargarEvaluacionProxy.execute();
			break;
		case DESCARGAR_EVALUACION_OPORTUNIDAD:
			
			descargarEvaluacionOportunidadProxy.execute();
			break;
		case DESCARGAR_EVALUACION_POSICION:
			
			descargarEvaluacionPosicionProxy.execute();
			break;
				
		case DESCARGAR_EVALUACION_PRESENTACION:
			
			descargarEvaluacionPresentacionProxy.execute();
			break;
			
		case DESCARGAR_EVALUACION_SKU:
				
				descargarEvaluacionSKUProxy.execute();
				break;
				
		case DESCARGAR_EVALUACION_POSICION_COMPROMISO:
				descargarEvaluacionPosicionCompromisoProxy.execute();
				break;
		case GUARDAR_PRODUCTO:
			
			descargaBLL.saveProducto(descagarProductosProxy.getResponse().productos);
			break;
		case GUARDAR_OPORTUNIDAD:
			
			descargaBLL.saveOportunidad(descargarOportunidadesProxy.getResponse().oportunidades);
			break;
		
		case GUARDAR_SKU:
			
			descargaBLL.saveSku(descargarSkuProxy.getResponse().skus);
			break;
		case GUARDAR_ACCIONESTRADE:
			
			descargaBLL.saveAccionTrade(descargarAccionesTradeProxy.getResponse().acciones);
			break;
		case GUARDAR_ACCIONESTRADEPRODUCTO:
			
			descargaBLL.saveAccionTradeProducto(descargarAccionesTradeProductoProxy.getResponse().accionesProducto);
			break;
		case GUARDAR_CLIENTE:
			
			descargaBLL.saveCliente(descargarClienteProxy.getResponse().clientes);
			break;
		case GUARDAR_POSICION:
			
			descargaBLL.savePosicion(descargarPosicionProxy.getResponse().posiciones);
			break;
		case GUARDAR_PRESENTACION:
			
			descargaBLL.savePresentacion(descargarPresentacionProxy.getResponse().presentaciones);
			break;
		case GUARDAR_PUNTO:
			
			descargaBLL.savePunto(descargarPuntoProxy.getResponse().puntos);
			break;
		case GUARDAR_EVALUACION:
			
			descargaBLL.saveEvaluacion(descargarEvaluacionProxy.getResponse().evaluaciones);
			break;
		case GUARDAR_EVALUACION_OPORTUNIDAD:
			
			descargaBLL.saveEvaluacionOportunidad(descargarEvaluacionOportunidadProxy.getResponse().oportunidades);
			break;
		case GUARDAR_EVALUACION_POSICION:
			
			descargaBLL.saveEvaluacionPosicion(descargarEvaluacionPosicionProxy.getResponse().posiciones);
			break;
		case GUARDAR_EVALUACION_PRESENTACION:
			
			descargaBLL.saveEvaluacionPresentacion(descargarEvaluacionPresentacionProxy.getResponse().presentaciones);
			break;
		case GUARDAR_EVALUACION_SKU:
			
			descargaBLL.saveEvaluacionSkus(descargarEvaluacionSKUProxy.getResponse().skus);
			break;
		case GUARDAR_EVALUACION_POSICION_COMPROMISO:
			
			descargaBLL.saveEvaluacionPosicionCompromiso(descargarEvaluacionPosicionCompromisoProxy.getResponse().compromisos);
			break;
		default:
			break;
		}
		
	}

	@Override
	protected void processOk(int accion) {
		if(accion==DESCARGAR_PRODUCTO){
			removeContadorProcesos();
			boolean isExito = descagarProductosProxy.isExito();
			String message="";
			
			if(descagarProductosProxy.getResponse()!=null){
				int status = descagarProductosProxy.getResponse().getStatus();
				
				if (isExito) {
					if (status == 0) {
						
						processAsync(GUARDAR_PRODUCTO);
						showToast("DESCARGO PRODUCTOS");
					}else{
						message = descagarProductosProxy.getResponse().getDescripcion();
						Log.d(TAG,message);
						showToast(message);
					}
				}else{
					message=error_generico_process;
					super.processOk();
					showToast(message);
				}
			}
		}else if(accion==DESCARGAR_OPORTUNIDAD){
			removeContadorProcesos();
			boolean isExito = descargarOportunidadesProxy.isExito();
			String message="";
			
			if(descargarOportunidadesProxy.getResponse()!=null){
				int status = descargarOportunidadesProxy.getResponse().getStatus();
				
				if (isExito) {
					if (status == 0) {
						
						processAsync(GUARDAR_OPORTUNIDAD);
						//showToast("DESCARGO OPORTUNIDADES");
					}else{
						message = descargarOportunidadesProxy.getResponse().getDescripcion();
							
						Log.d(TAG,message);
						
						//super.processOk(DESCARGAR_PRODUCTO);
						//showToast(message);
					}
				}else{
					message=error_generico_process;
					super.processOk();
					showToast(message);
				}
			}
		}else if(accion==DESCARGAR_SKU){
			removeContadorProcesos();
			boolean isExito = descargarSkuProxy.isExito();
			String message="";
			
			if(descargarSkuProxy.getResponse()!=null){
				int status = descargarSkuProxy.getResponse().getStatus();
				if (isExito) {
					if (status == 0) {
						
						processAsync(GUARDAR_SKU);
						//showToast("DESCARGO OPORTUNIDADES");
					}else{
						message = descargarSkuProxy.getResponse().getDescripcion();
							
						Log.d(TAG,message);
						
						//super.processOk(DESCARGAR_PRODUCTO);
						//showToast(message);
					}
				}else{
					message=error_generico_process;
					super.processOk();
					showToast(message);
				}
			}
		}else if(accion==DESCARGAR_ACCIONESTRADE){
			removeContadorProcesos();
			boolean isExito = descargarAccionesTradeProxy.isExito();
			String message="";
			
			if(descargarAccionesTradeProxy.getResponse()!=null){
				int status = descargarAccionesTradeProxy.getResponse().getStatus();
				
				if (isExito) {
					if (status == 0) {
						
						processAsync(GUARDAR_ACCIONESTRADE);
						//showToast("DESCARGO OPORTUNIDADES");
					}else{
						message = descargarAccionesTradeProxy.getResponse().getDescripcion();
							
						Log.d(TAG,message);
						
						//super.processOk(DESCARGAR_PRODUCTO);
						//showToast(message);
					}
				}else{
					message=error_generico_process;
					super.processOk();
					showToast(message);
				}
			}
		}else if(accion==DESCARGAR_ACCIONESTRADEPRODUCTO){
			removeContadorProcesos();
			boolean isExito = descargarAccionesTradeProductoProxy.isExito();
			String message="";
			
			if(descargarAccionesTradeProductoProxy.getResponse()!=null){
				int status = descargarAccionesTradeProductoProxy.getResponse().getStatus();
				
				if (isExito) {
					if (status == 0) {
						
						processAsync(GUARDAR_ACCIONESTRADEPRODUCTO);
						//showToast("DESCARGO OPORTUNIDADES");
					}else{
						message = descargarAccionesTradeProductoProxy.getResponse().getDescripcion();
							
						Log.d(TAG,message);
						
						//super.processOk(DESCARGAR_PRODUCTO);
						//showToast(message);
					}
				}else{
					message=error_generico_process;
					super.processOk();
					showToast(message);
				}
			}
		}else if(accion==DESCARGAR_CLIENTE){
			removeContadorProcesos();
			boolean isExito = descargarClienteProxy.isExito();
			String message="";
			
			
			
			if(descargarClienteProxy.getResponse()!=null){
				int status = descargarClienteProxy.getResponse().getStatus();
				if (isExito) {
					if (status == 0) {
						
						processAsync(GUARDAR_CLIENTE);
						//showToast("DESCARGO OPORTUNIDADES");
					}else{
						message = descargarClienteProxy.getResponse().getDescripcion();
							
						Log.d(TAG,message);
					}
				}else{
					message=error_generico_process;
					super.processOk();
					showToast(message);
				}
			}
		}else if(accion==DESCARGAR_POSICION){
			removeContadorProcesos();
			boolean isExito = descargarPosicionProxy.isExito();
			String message="";
			
			
			
			if(descargarPosicionProxy.getResponse()!=null){
				int status = descargarPosicionProxy.getResponse().getStatus();
				if (isExito) {
					if (status == 0) {
						
						processAsync(GUARDAR_POSICION);
						//showToast("DESCARGO OPORTUNIDADES");
					}else{
						message = descargarPosicionProxy.getResponse().getDescripcion();
							
						Log.d(TAG,message);
					}
				}else{
					message=error_generico_process;
					super.processOk();
					showToast(message);
				}
			}
			
		}else if(accion==DESCARGAR_PRESENTACION){
			removeContadorProcesos();
			boolean isExito = descargarPresentacionProxy.isExito();
			String message="";
			
			
			
			if(descargarPresentacionProxy.getResponse()!=null){
				int status = descargarPresentacionProxy.getResponse().getStatus();
				if (isExito) {
					if (status == 0) {
						
						processAsync(GUARDAR_PRESENTACION);
						//showToast("DESCARGO OPORTUNIDADES");
					}else{
						message = descargarPresentacionProxy.getResponse().getDescripcion();
							
						Log.d(TAG,message);
					}
				}else{
					message=error_generico_process;
					super.processOk();
					showToast(message);
				}
			}
		}else if(accion==DESCARGAR_PUNTO){
			removeContadorProcesos();
			boolean isExito = descargarPuntoProxy.isExito();
			String message="";
			
			
			
			if(descargarPuntoProxy.getResponse()!=null){
				int status = descargarPuntoProxy.getResponse().getStatus();
				if (isExito) {
					if (status == 0) {
						
						processAsync(GUARDAR_PUNTO);
						//showToast("DESCARGO OPORTUNIDADES");
					}else{
						message = descargarPuntoProxy.getResponse().getDescripcion();
							
						Log.d(TAG,message);
					}
				}else{
					message=error_generico_process;
					super.processOk();
					showToast(message);
				}
			}
		}else if(accion==DESCARGAR_EVALUACION){
				removeContadorProcesos();
				processAsync(GUARDAR_EVALUACION);
		}else if(accion==DESCARGAR_EVALUACION_OPORTUNIDAD){
			removeContadorProcesos();
			processAsync(GUARDAR_EVALUACION_OPORTUNIDAD);
		}else if(accion==DESCARGAR_EVALUACION_POSICION){
			removeContadorProcesos();
			processAsync(GUARDAR_EVALUACION_POSICION);
		}else if(accion==DESCARGAR_EVALUACION_PRESENTACION){
			removeContadorProcesos();
			processAsync(GUARDAR_EVALUACION_PRESENTACION);
		}else if(accion==DESCARGAR_EVALUACION_SKU){
			removeContadorProcesos();
			processAsync(GUARDAR_EVALUACION_SKU);
		}else if(accion==DESCARGAR_EVALUACION_POSICION_COMPROMISO){
			removeContadorProcesos();
			processAsync(GUARDAR_EVALUACION_POSICION_COMPROMISO);
		}else if(accion==GUARDAR_PRODUCTO){
			removeContadorProcesos();
			showToast("PRODUCTOS GUARDAR");
		}else if(accion==GUARDAR_OPORTUNIDAD){
			removeContadorProcesos();
			showToast("OPORTUNIDAD GUARDAR");
		}else if(accion==GUARDAR_SKU){
			removeContadorProcesos();
			showToast("sku GUARDAR");
		}else if(accion==GUARDAR_ACCIONESTRADE){
			removeContadorProcesos();
			showToast("guardar GUARDAR_ACCIONESTRADE");
		}else if(accion==GUARDAR_ACCIONESTRADEPRODUCTO){
			removeContadorProcesos();
			showToast("guardar GUARDAR_ACCIONESTRADEPRODUCTO");
		}else if(accion==GUARDAR_CLIENTE){
			removeContadorProcesos();
			showToast("guardar GUARDAR_CLIENTE");
		}else if(accion==GUARDAR_POSICION){
			removeContadorProcesos();
			showToast("guardar GUARDAR_POSICION");
		}else if(accion==GUARDAR_PRESENTACION){
			removeContadorProcesos();
			showToast("guardar GUARDAR_PRESENTACION");
		}else if(accion==GUARDAR_PUNTO){
			removeContadorProcesos();
			showToast("guardar GUARDAR_PUNTO");
		}else if(accion==GUARDAR_EVALUACION){
			removeContadorProcesos();
			processAsync(DESCARGAR_EVALUACION_SKU);
			processAsync(DESCARGAR_EVALUACION_OPORTUNIDAD);
			processAsync(DESCARGAR_EVALUACION_POSICION);
			processAsync(DESCARGAR_EVALUACION_PRESENTACION);
			processAsync(DESCARGAR_EVALUACION_POSICION_COMPROMISO);
			showToast("guardar GUARDAR_EVALUACION");
		}else if(accion==GUARDAR_EVALUACION_OPORTUNIDAD){
			removeContadorProcesos();
			showToast("guardar GUARDAR_EVALUACION");
		}else if(accion==GUARDAR_EVALUACION_POSICION){
			removeContadorProcesos();
			showToast("guardar GUARDAR_EVALUACION_POSICION");
		}else if(accion==GUARDAR_EVALUACION_PRESENTACION){
			removeContadorProcesos();
			showToast("guardar GUARDAR_EVALUACION_PRESENTACION");
		}else if(accion==GUARDAR_EVALUACION_SKU){
			removeContadorProcesos();
			showToast("guardar GUARDAR_EVALUACION_SKU");
		}else if(accion==GUARDAR_EVALUACION_POSICION_COMPROMISO){
			removeContadorProcesos();
			showToast("guardar GUARDAR_EVALUACION_POSICION_COMPROMISO");
		}
		
		
		
		
	}
	@Override
	protected void processError(int accion) {
		// TODO Auto-generated method stub
		removeContadorProcesos();
		Log.d("error", "=============");
		Log.d("error", String.valueOf(accion));
		Log.d("error", "=============");
		super.processError();
	}
	
}
