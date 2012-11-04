package lindley.desarrolloxcliente.activity;

import lindley.desarrolloxcliente.R;
import lindley.desarrolloxcliente.ws.service.UploadEvaluacionesProxy;
import android.os.Bundle;
import android.util.Log;

import com.actionbarsherlock.view.Window;
import com.google.inject.Inject;

import net.msonic.lib.sherlock.ActivityBase;

public class UploadData_Activity extends ActivityBase {

	public static final int UPLOAD_EVALUACION=0;
	@Inject UploadEvaluacionesProxy uploadEvaluacionesProxy;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		inicializarRecursos();
		this.mostrarWaitMessage=false;
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
		
		setContentView(R.layout.descargadata_activity);

		setSupportProgressBarIndeterminateVisibility(true);

		processAsync(UPLOAD_EVALUACION);
	}
	

	private int contadorProcesos=0;
	
	private synchronized void addContadorProcesos(){
		contadorProcesos++;
		Log.i("up", String.valueOf(contadorProcesos));
	}
	
	private synchronized void removeContadorProcesos(){
		contadorProcesos--;
		Log.i("dow", String.valueOf(contadorProcesos));
		if(contadorProcesos==0){
			setSupportProgressBarIndeterminateVisibility(false);
		}
	}
	
	@Override
	protected void process(int accion) throws Exception {
		switch (accion) {
		case UPLOAD_EVALUACION:
			addContadorProcesos();
			uploadEvaluacionesProxy.execute();
			break;
		default:
			break;
		}
	}
	
	@Override
	protected void processOk(int accion) {
		switch (accion) {
		case UPLOAD_EVALUACION:
			removeContadorProcesos();
			
			break;
		default:
			break;
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
