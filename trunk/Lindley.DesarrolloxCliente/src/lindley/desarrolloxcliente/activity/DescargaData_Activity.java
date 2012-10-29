package lindley.desarrolloxcliente.activity;

import lindley.desarrolloxcliente.negocio.DescargaBLL;
import lindley.desarrolloxcliente.to.PeriodoTO;
import lindley.desarrolloxcliente.ws.service.DescargarAccionesTradeProductoProxy;
import lindley.desarrolloxcliente.ws.service.DescargarAccionesTradeProxy;
import lindley.desarrolloxcliente.ws.service.DescargarProductosProxy;
import lindley.desarrolloxcliente.ws.service.DescargarOportunidadesProxy;
import lindley.desarrolloxcliente.ws.service.DescargarSkuProxy;


import android.os.Bundle;
import android.util.Log;

import com.google.inject.Inject;

import net.msonic.lib.sherlock.ActivityBase;

public class DescargaData_Activity extends ActivityBase {

	public static final int DESCARGAR_PRODUCTO=0;
	public static final int DESCARGAR_OPORTUNIDAD=1;
	public static final int DESCARGAR_SKU=2;
	public static final int DESCARGAR_ACCIONESTRADE=3;
	public static final int DESCARGAR_ACCIONESTRADEPRODUCTO=4;
	
	public static final int GUARDAR_PRODUCTO=10;
	public static final int GUARDAR_OPORTUNIDAD=11;
	public static final int GUARDAR_SKU=12;
	public static final int GUARDAR_ACCIONESTRADE=13;
	public static final int GUARDAR_ACCIONESTRADEPRODUCTO=14;
	
	
	@Inject DescargarProductosProxy descagarProductosProxy;
	@Inject DescargarOportunidadesProxy descargarOportunidadesProxy;
	@Inject DescargarSkuProxy descargarSkuProxy;
	@Inject DescargarAccionesTradeProxy descargarAccionesTradeProxy;
	@Inject DescargarAccionesTradeProductoProxy descargarAccionesTradeProductoProxy;
	
	
	@Inject DescargaBLL descargaBLL;
	@Inject PeriodoTO periodoTO;
	private  static String TAG = DescargaData_Activity.class.getSimpleName();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		inicializarRecursos();
		this.mostrarWaitMessage=false;
		super.onCreate(savedInstanceState);
		
		processAsync(DESCARGAR_PRODUCTO);
		processAsync(DESCARGAR_OPORTUNIDAD);
		processAsync(DESCARGAR_SKU);
		
		processAsync(DESCARGAR_ACCIONESTRADE);
		processAsync(DESCARGAR_ACCIONESTRADEPRODUCTO);
	}
	
	
	
	
	
	@Override
	protected void process(int accion) {
		// TODO Auto-generated method stub
		periodoTO.anio=2012;
		periodoTO.mes=10;
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
		default:
			break;
		}
		
	}

	@Override
	protected void processOk(int accion) {
		if(accion==DESCARGAR_PRODUCTO){
			
			boolean isExito = descagarProductosProxy.isExito();
			String message="";
			
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
			
		}else if(accion==DESCARGAR_OPORTUNIDAD){
			boolean isExito = descargarOportunidadesProxy.isExito();
			String message="";
			
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
		}else if(accion==DESCARGAR_SKU){
			boolean isExito = descargarSkuProxy.isExito();
			String message="";
			
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
		}else if(accion==DESCARGAR_ACCIONESTRADE){
			boolean isExito = descargarAccionesTradeProxy.isExito();
			String message="";
			
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
		}else if(accion==DESCARGAR_ACCIONESTRADEPRODUCTO){
			boolean isExito = descargarAccionesTradeProductoProxy.isExito();
			String message="";
			
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
		}else if(accion==GUARDAR_PRODUCTO){
			showToast("PRODUCTOS GUARDAR");
		}else if(accion==GUARDAR_OPORTUNIDAD){
			showToast("OPORTUNIDAD GUARDAR");
		}else if(accion==GUARDAR_SKU){
			showToast("sku GUARDAR");
		}else if(accion==GUARDAR_ACCIONESTRADE){
			showToast("guardar GUARDAR_ACCIONESTRADE");
		}else if(accion==GUARDAR_ACCIONESTRADEPRODUCTO){
			showToast("guardar GUARDAR_ACCIONESTRADEPRODUCTO");
		}
		
		
	}
	@Override
	protected void processError() {
		// TODO Auto-generated method stub
		showToast(error_generico_process);
		super.processError();
	}
	
}
