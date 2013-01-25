package lindley.desarrolloxcliente.service;

import java.util.List;



import lindley.desarrolloxcliente.negocio.UploadBLL;
import lindley.desarrolloxcliente.to.upload.EvaluacionProcessUploadTO;

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
	
	public static final int CALCULAR_EVALUACIONES=1;
	public static final int LISTAR_EVALUACIONES=2;
	public static final int ACTUALIZAR_EVALUACIONES=3;
	public static final int ACTUALIZAR_EVALUACIONES_PROCESAR=3;
	public static final int UPLOAD_EVALUACION=0;
	
	@Inject UploadEvaluacionesProxy uploadEvaluacionesProxy;
	@Inject UploadBLL uploadBLL;
	
	
	private long cantidadEvaluaciones=0;
	
	
	
	
	
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		processAsync(CALCULAR_EVALUACIONES);
		
		Intent intent = new Intent();
		intent.setAction(NOTIFICACION_START_SERVICE);
		sendBroadcast(intent); 
	}
	
	

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

}

