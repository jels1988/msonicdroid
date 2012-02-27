package pe.lindley.plandesarrollo.activity;

import com.google.inject.Inject;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import pe.lindley.activity.R;
import pe.lindley.plandesarrollo.ws.service.EliminarSustentoProxy;
import pe.lindley.util.ActivityBase;
import pe.lindley.util.MessageBox;
import roboguice.inject.InjectResource;

public class EliminarSustentoActivity extends ActivityBase {

	@Inject EliminarSustentoProxy eliminarSustentoProxy; 
	@InjectResource(R.string.pd_eliminar_sustento_ok) String eliminar_sustento_ok;
	@InjectResource(R.string.plandesarrollo_guardar_sustento_activity_confirm_dialog_si) 		String 	confirm_si;
	@InjectResource(R.string.plandesarrollo_guardar_sustento_activity_confirm_dialog_no) 		String 	confirm_no;
	@InjectResource(R.string.plandesarrollo_guardar_sustento_activity_confirm_dialog_message) 			String 	confirm_message;
	@InjectResource(R.string.plandesarrollo_guardar_sustento_activity_title) 	String 	ficha_eliminar_sustento_title;
	
	protected static final String CODIGO_CLIENTE = "cod_cliente";
	protected static final String CODIGO_PLAN = "cod_plan";
	protected static final String CODIGO_ACTIVIDAD = "cod_actividad";
	protected static final String CODIGO_SUSTENTO = "cod_responsable";
	
	public String codigo_cliente = null;
	public String codigo_plan = null;
	public String codigo_actividad = null;
	public String codigo_sustento = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		Intent intent = this.getIntent();
		codigo_cliente = intent.getStringExtra(CODIGO_CLIENTE);	
		codigo_plan = intent.getStringExtra(CODIGO_PLAN);	
		codigo_actividad = intent.getStringExtra(CODIGO_ACTIVIDAD);	
		codigo_sustento = intent.getStringExtra(CODIGO_SUSTENTO);	
		final Context context = this;
		
		MessageBox.showConfirmDialog(context, 
				ficha_eliminar_sustento_title, 
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
		eliminarSustentoProxy.setCodigoActvidad(codigo_actividad);
		eliminarSustentoProxy.setCodigoCliente(codigo_cliente);
		eliminarSustentoProxy.setCodigoPLan(codigo_plan);
		eliminarSustentoProxy.setCodigoSustento(codigo_sustento);
		eliminarSustentoProxy.execute();
	}

	@Override
	protected void processOk() {
		// TODO Auto-generated method stub
		boolean isExito = eliminarSustentoProxy.isExito();
		if (isExito) {
			int status = eliminarSustentoProxy.getResponse().getStatus();
			if (status == 0) {
				showToast(eliminar_sustento_ok);
			}
			else  {
				showToast(eliminarSustentoProxy.getResponse().getDescripcion());
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
