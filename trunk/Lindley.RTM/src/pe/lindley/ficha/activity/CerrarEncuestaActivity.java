package pe.lindley.ficha.activity;

import com.google.inject.Inject;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import pe.lindley.activity.R;
import pe.lindley.activity.RTMApplication;
import pe.lindley.ficha.ws.service.CerrarEncuestaProxy;
import pe.lindley.lanzador.to.UsuarioTO;
import pe.lindley.util.ActivityBase;
import pe.lindley.util.MessageBox;
import roboguice.inject.InjectResource;

public class CerrarEncuestaActivity extends ActivityBase {

	
	@Inject CerrarEncuestaProxy cerrarEncuestaProxy;
	public static String CODIGO_ENCUESTA  = "encuesta_codigo";
	private String cod_encuesta = null;
	@InjectResource(R.string.ficha_cerrarEncuestaProxy_ok) String cerrarEncuestaProxy_ok;
	
	@InjectResource(R.string.ficha_cerrar_encuesta_activity_confirm_dialog_si) 		String 	confirm_si;
	@InjectResource(R.string.ficha_cerrar_encuesta_activity_confirm_dialog_no) 		String 	confirm_no;
	@InjectResource(R.string.ficha_cerrar_encuesta_activity_confirm_dialog_message) String 	confirm_message;
	@InjectResource(R.string.ficha_cerrar_encuesta_activity_title) 					String 	ficha_cerrar_encuesta_title;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		Intent intent = this.getIntent();
		cod_encuesta = intent.getStringExtra(CODIGO_ENCUESTA);
		
		final Context context = this;
		
		MessageBox.showConfirmDialog(context, 
			ficha_cerrar_encuesta_title, 
			confirm_message, 
			confirm_si,
			new OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					processAsync();
				}
			},
			confirm_no,
			new OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					finish();
				}
			});	
		
	}
	
	@Override
	protected void process() {
		// TODO Auto-generated method stub
		RTMApplication application = (RTMApplication)getApplicationContext();
		UsuarioTO usuario = application.getUsuarioTO();
		cerrarEncuestaProxy.setCodigoEncuesta(cod_encuesta);
		cerrarEncuestaProxy.setUsuario(usuario.getCodigoSap());
		cerrarEncuestaProxy.execute();
		super.process();
	}

	@Override
	protected void processOk() {
		// TODO Auto-generated method stub
		boolean isExito = cerrarEncuestaProxy.isExito();
		if (isExito) {
			int status = cerrarEncuestaProxy.getResponse().getStatus();
			if (status == 0) {
				showToast(cerrarEncuestaProxy_ok);
			}
			else  {
				showToast(cerrarEncuestaProxy.getResponse().getDescripcion());
			}
		}
		else{
			processError();
		}
		super.processOk();
		finish();
	}

	@Override
	protected void processError() {
		// TODO Auto-generated method stub
		super.processError();
		showToast(error_generico_process);
	}

	
	

}
