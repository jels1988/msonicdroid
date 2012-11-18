package lindley.desarrolloxcliente.activity;

import java.util.List;

import lindley.desarrolloxcliente.R;
import lindley.desarrolloxcliente.negocio.UploadBLL;
import lindley.desarrolloxcliente.to.upload.EvaluacionProcessUploadTO;
import lindley.desarrolloxcliente.ws.service.UploadEvaluacionesProxy;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.actionbarsherlock.view.Window;
import com.google.inject.Inject;

import net.msonic.lib.sherlock.ActivityBase;

public class UploadData_Activity extends ActivityBase {

	public static final int CALCULAR_EVALUACIONES=1;
	public static final int LISTAR_EVALUACIONES=2;
	public static final int ACTUALIZAR_EVALUACIONES=3;
	public static final int UPLOAD_EVALUACION=0;
	
	@Inject UploadEvaluacionesProxy uploadEvaluacionesProxy;
	@Inject UploadBLL uploadBLL;
	
	private long cantidadEvaluaciones=0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		inicializarRecursos();
		this.mostrarWaitMessage=false;
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
		
		setContentView(R.layout.descargadata_activity);
		setSupportProgressBarIndeterminateVisibility(true);
		processAsync(CALCULAR_EVALUACIONES);
	}
	

	
	
	@Override
	protected void process(int accion) throws Exception {
		switch (accion) {
		case CALCULAR_EVALUACIONES:
			cantidadEvaluaciones = uploadBLL.getCantidadEvaluaciones();
			break;
		case LISTAR_EVALUACIONES:
			uploadEvaluacionesProxy.evaluciones = uploadBLL.listarEvaluaciones(10);
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
			/*
			for (EvaluacionTO evaluacionTO : uploadEvaluacionesProxy.evaluciones) {
				uploadBLL.deleteEvaluacion(evaluacionTO.id);
			}*/
			
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
				setSupportProgressBarIndeterminateVisibility(false);
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
			break;
		default:
			break;
		}
	}
	
	@Override
	protected void processError(int accion) {
		setSupportProgressBarIndeterminateVisibility(false);
		// TODO Auto-generated method stub
		Log.d("error", "=============");
		Log.d("error", String.valueOf(accion));
		Log.d("error", "=============");
		super.processError();
	}
	
}
