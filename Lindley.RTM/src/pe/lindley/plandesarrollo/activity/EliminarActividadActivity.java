package pe.lindley.plandesarrollo.activity;

import com.google.inject.Inject;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import pe.lindley.activity.R;
import pe.lindley.plandesarrollo.ws.service.EliminarActividadProxy;
import pe.lindley.util.ActivityBase;
import pe.lindley.util.MessageBox;
import roboguice.inject.InjectResource;

public class EliminarActividadActivity extends ActivityBase {

	@Inject EliminarActividadProxy eliminarActividadProxy;
	@InjectResource(R.string.pd_eliminar_actividad_ok) String eliminar_actividad_ok;
	public static final String CODIGO_ACTIVIDAD = "cod_Actividad";
	public String codigo_actividad = null;
	
	@InjectResource(R.string.plandesarrollo_eliminar_actividad_activity_confirm_dialog_si) 		String 	confirm_si;
	@InjectResource(R.string.plandesarrollo_eliminar_actividad_activity_confirm_dialog_no) 		String 	confirm_no;
	@InjectResource(R.string.plandesarrollo_eliminar_actividad_activity_confirm_dialog_message) String 	confirm_message;
	@InjectResource(R.string.plandesarrollo_eliminar_actividad_activity_title) 					String 	pd_eliminar_actividad_title;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		Intent intent = this.getIntent();
		codigo_actividad = intent.getStringExtra(CODIGO_ACTIVIDAD);		
		final Context context = this;
		
		MessageBox.showConfirmDialog(context, 
			pd_eliminar_actividad_title, 
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
		eliminarActividadProxy.setCodigoActividad(codigo_actividad);
		eliminarActividadProxy.execute();
		super.process();
	}

	@Override
	protected void processOk() {
		// TODO Auto-generated method stub
		boolean isExito = eliminarActividadProxy.isExito();
		if (isExito) {
			int status = eliminarActividadProxy.getResponse().getStatus();
			if (status == 0) {
				showToast(eliminar_actividad_ok);
			}
			else  {
				showToast(eliminarActividadProxy.getResponse().getDescripcion());
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
