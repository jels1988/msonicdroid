package lindley.desarrolloxcliente.service;

import java.util.ArrayList;
import java.util.List;



import lindley.desarrolloxcliente.negocio.UploadBLL;
import lindley.desarrolloxcliente.to.upload.EvaluacionProcessUploadTO;
import lindley.desarrolloxcliente.to.upload.EvaluacionTO;
import lindley.desarrolloxcliente.to.upload.ProcesoInfoTO;

import lindley.desarrolloxcliente.ws.service.UploadEvaluacionesProxy;


import android.content.Intent;
import android.util.Log;

import com.google.inject.Inject;

import net.msonic.lib.sherlock.ServiceBase;

public class UploadDataService extends ServiceBase {

	public static final String NOTIFICACION_START_SERVICE="lindley.desarrolloxcliente.service.UploadDataService.start";
	public static final String NOTIFICACION_EVALUACION_SEND_SERVICE="lindley.desarrolloxcliente.service.UploadDataService.sendEvaluacion";
	public static final String NOTIFICACION_STOP_SERVICE="lindley.desarrolloxcliente.service.UploadDataService.stop";
	public static String TAG = UploadDataService.class.getCanonicalName();
	
	
	/*
	public static final int CALCULAR_EVALUACIONES=1;
	public static final int LISTAR_EVALUACIONES=2;
	public static final int ACTUALIZAR_EVALUACIONES=3;
	public static final int ACTUALIZAR_EVALUACIONES_PROCESAR=3;
	public static final int UPLOAD_EVALUACION=0;
	*/
	
	public final static int DIFERENCIA=100;
	public final static int UPLOAD_EVALUACION=0;
	
	public final static int MAX_ITEMS_ENVIOS=1;
	public final static int MAX_INTENTOS_ENVIO=1;
	
	private int contadorProcesos=0;
	private int intentosEnvio=0;
	
	@Inject UploadEvaluacionesProxy uploadEvaluacionesProxy;
	@Inject UploadBLL uploadBLL;
	
	
	List<ProcesoInfoTO> lista =null;
	
	
	
	
	
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		
		this.lista=new ArrayList<ProcesoInfoTO>();
		
		//CREAR LOS PROCESOS A EJECUTAR
		ProcesoInfoTO p1 = new ProcesoInfoTO();
		p1.processId=UPLOAD_EVALUACION;		
		p1.estado=ProcesoInfoTO.ESTADO_INICIO;
		lista.add(p1);
			
		processAsync();
		
		Intent intent = new Intent();
		intent.setAction(NOTIFICACION_START_SERVICE);
		sendBroadcast(intent);
		
	}
	
	private synchronized void addContadorProcesos(){
		contadorProcesos++;
		Log.i(TAG,String.format("addContadorProcesos: %s",contadorProcesos));
		if(contadorProcesos>0){
			//setSupportProgressBarIndeterminateVisibility(true);
			
		}
	}
	
	private synchronized void removeContadorProcesos(){
		contadorProcesos--;
		Log.i(TAG,String.format("removeContadorProcesos: %s",contadorProcesos));
		if(contadorProcesos==0){
			int contador=0;
			
			for (ProcesoInfoTO proceso : lista) {
				if(proceso.isExito)
				contador++;
			}
			
			if(contador==lista.size()){
				stoppeService();
			}else{
				if(intentosEnvio<MAX_INTENTOS_ENVIO){
					processAsync();
				}else{
					stoppeService();
				}
			}
		}
	}
	private void stoppeService(){
		
		Log.i(TAG,"Stopping Service");
		stopSelf();
		Log.i(TAG,"Stopped Service");
		
		Intent intent = new Intent();
		intent.setAction(NOTIFICACION_STOP_SERVICE);
		sendBroadcast(intent); 
		
	}
	
	private synchronized ProcesoInfoTO processById(int processId){
		ProcesoInfoTO processSeleccionado=null;
		for (ProcesoInfoTO process : lista) {
			if(process.processId==processId || process.processId==processId-DIFERENCIA){
				processSeleccionado = process;
				break;
			}
		}	
		return processSeleccionado;
	}
	
	@Override
	protected boolean executeAsyncPre() {
		// TODO Auto-generated method stub
		intentosEnvio++;
		Log.i(TAG,String.format("Envi— nœmero %s", intentosEnvio));
		
		return super.executeAsyncPre();
	}

	
	@Override
	protected void process() throws Exception {
		// TODO Auto-generated method stub		
		super.process();
	}

	@Override
	protected void processOk() {
		// TODO Auto-generated method stub
		for (ProcesoInfoTO proceso : lista) {
			processAsync(proceso.processId);
		}
	}

	@Override
	protected boolean executeAsyncPre(int accion) {
		// TODO Auto-generated method stub
		Log.i("executeAsyncPre - accion:", String.valueOf(accion));

		
		addContadorProcesos();
		return true;
	}

	@Override
	protected void process(int accion) throws Exception {
		// TODO Auto-generated method stub
		Log.i("process - accion:", String.valueOf(accion));
		switch (accion) {
		case UPLOAD_EVALUACION:
			List<EvaluacionTO> evaluaciones = uploadBLL.listarEvaluaciones(MAX_ITEMS_ENVIOS);
			if(evaluaciones!=null && evaluaciones.size()>0){
				uploadEvaluacionesProxy.sinDatos=false;
				uploadEvaluacionesProxy.evaluciones = evaluaciones;
				uploadEvaluacionesProxy.execute();
			}else{
				uploadEvaluacionesProxy.sinDatos=true;
			}
			break;

		}
	}
	
	@Override
	protected void processOk(int accion) {
		// TODO Auto-generated method stub
		Log.i("processOk - accion:", String.valueOf(accion));
		boolean isExito;
		ProcesoInfoTO proceso = processById(accion); 
		switch (accion) {
			case UPLOAD_EVALUACION:{
				
				if(!uploadEvaluacionesProxy.sinDatos){
					isExito = uploadEvaluacionesProxy.isExito();
					if(uploadEvaluacionesProxy.getResponse()!=null){
						int status = uploadEvaluacionesProxy.getResponse().getStatus();
						
						if ((isExito) && (status == 0)) {
							List<EvaluacionProcessUploadTO> ids = uploadEvaluacionesProxy.getResponse().ids;
							if(ids!=null){
								uploadBLL.updateEvaluacionServerId(ids);
								
								long cantidadEvaluaciones = uploadBLL.getCantidadEvaluaciones();
								
								if(cantidadEvaluaciones>0){
									processAsync();
								}
							}
						}
					}
				}else{
					proceso.isExito=true;
				}
				
				break;
				
			}	
		}
		
		removeContadorProcesos();
	}
	
	@Override
	protected void processError(int accion) {
		// TODO Auto-generated method stub
		Log.i("processError - accion:", String.valueOf(accion));
		ProcesoInfoTO proceso = processById(accion);
		proceso.isExito=false;
		removeContadorProcesos();
	}
	
	/*

	@Override
	protected void process() throws Exception {
			
	}
	
	
	
	
	@Override
	protected void processOk() {
		// TODO Auto-generated method stub
		super.processOk();
	}




	private void stoppeService(){
		
		Log.i(TAG,"Stopping Service");
		stopSelf();
		Log.i(TAG,"Stopped Service");
		
		Intent intent = new Intent();
		intent.setAction(NOTIFICACION_STOP_SERVICE);
		sendBroadcast(intent); 
		
	}

	
	
	@Override
	protected void process(int accion) throws Exception {
		switch (accion) {
		case CALCULAR_EVALUACIONES:
			cantidadEvaluaciones = uploadBLL.getCantidadEvaluaciones();
			break;
		case LISTAR_EVALUACIONES:
			uploadEvaluacionesProxy.evaluciones = uploadBLL.listarEvaluaciones(1);
			break;
		case UPLOAD_EVALUACION:
			uploadEvaluacionesProxy.execute();
			break;
		case ACTUALIZAR_EVALUACIONES:
			boolean isExito = uploadEvaluacionesProxy.isExito();
			if(uploadEvaluacionesProxy.getResponse()!=null){
				int status = uploadEvaluacionesProxy.getResponse().getStatus();
				if ((isExito) && (status == 0)) {
					List<EvaluacionProcessUploadTO> ids = uploadEvaluacionesProxy.getResponse().ids;
					if(ids!=null){
						uploadBLL.updateEvaluacionServerId(ids);
					}
				}
			}
			break;
		default:
			break;
		}
	}
	
	@Override
	protected void processOk(int accion) {
		switch (accion) {
		case CALCULAR_EVALUACIONES:
			
			
			
			Log.d("Evaluaciones Pendientes", String.valueOf(cantidadEvaluaciones));
			
			if(cantidadEvaluaciones>0){
				processAsync(LISTAR_EVALUACIONES);
				Intent servicioFoto = new Intent("lindley.desarrolloxcliente.uploadFileService");
   				startService(servicioFoto);
			}else{
				stoppeService();
				//menuEnviar.setVisible(true);
				//setSupportProgressBarIndeterminateVisibility(false);
			}
			break;
		case LISTAR_EVALUACIONES:
			processAsync(UPLOAD_EVALUACION);
			break;
		case UPLOAD_EVALUACION:
			processAsync(ACTUALIZAR_EVALUACIONES);
			break;
		case ACTUALIZAR_EVALUACIONES:
			processAsync(CALCULAR_EVALUACIONES);
			//NOTIFICACION_EVALUACION_START_SERVICE
			
			Intent intent = new Intent();
			intent.setAction(NOTIFICACION_EVALUACION_SEND_SERVICE);
			sendBroadcast(intent); 
			
			break;
		default:
			break;
		}
	}
	
	@Override
	protected void processError(int accion) {
		//menuEnviar.setVisible(true);
		//setSupportProgressBarIndeterminateVisibility(false);
		// TODO Auto-generated method stub
		stopSelf();
		Log.d("error", "=============");
		Log.d("error", String.valueOf(accion));
		Log.d("error", "=============");
		super.processError();
	}
*/
}

