package pe.lindley.plandesarrollo.activity;

import com.google.inject.Inject;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import pe.lindley.activity.R;
import pe.lindley.plandesarrollo.ws.service.EliminarResponsableProxy;
import pe.lindley.util.ActivityBase;
import pe.lindley.util.MessageBox;
import roboguice.inject.InjectResource;

public class EliminarResponsableActivity extends ActivityBase {

	@Inject EliminarResponsableProxy eliminarResponsableProxy;
	@InjectResource(R.string.pd_eliminar_responsable_ok) String eliminar_responsable_ok;
	
	@InjectResource(R.string.plandesarrollo_eliminar_responsable_activity_confirm_dialog_si) 		String 	confirm_si;
	@InjectResource(R.string.plandesarrollo_eliminar_responsable_activity_confirm_dialog_no) 		String 	confirm_no;
	@InjectResource(R.string.plandesarrollo_eliminar_responsable_activity_confirm_dialog_message) String 	confirm_message;
	@InjectResource(R.string.plandesarrollo_eliminar_responsable_activity_title) 					String 	pd_eliminar_responsable_title;
	
	
	protected static final String CODIGO_CLIENTE = "cod_cliente";
	protected static final String CODIGO_PLAN = "cod_plan";
	protected static final String CODIGO_ACTIVIDAD = "cod_actividad";
	protected static final String CODIGO_RESPONSABLE = "cod_responsable";
	
	public String codigo_cliente = null;
	public String codigo_plan = null;
	public String codigo_actividad = null;
	public String codigo_responsable = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		Intent intent = this.getIntent();
		codigo_cliente = intent.getStringExtra(CODIGO_CLIENTE);	
		codigo_plan = intent.getStringExtra(CODIGO_PLAN);	
		codigo_actividad = intent.getStringExtra(CODIGO_ACTIVIDAD);	
		codigo_responsable = intent.getStringExtra(CODIGO_RESPONSABLE);		
		final Context context = this;
		
		MessageBox.showConfirmDialog(context, 
			pd_eliminar_responsable_title, 
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
		eliminarResponsableProxy.setCodigoActvidad(codigo_actividad);
		eliminarResponsableProxy.setCodigoCliente(codigo_cliente);
		eliminarResponsableProxy.setCodigoPLan(codigo_plan);
		eliminarResponsableProxy.setCodigoResponsable(codigo_responsable);
		eliminarResponsableProxy.execute();
	}

	@Override
	protected void processOk() {
		// TODO Auto-generated method stub
		boolean isExito = eliminarResponsableProxy.isExito();
		if (isExito) {
			int status = eliminarResponsableProxy.getResponse().getStatus();
			if (status == 0) {
				showToast(eliminar_responsable_ok);
			}
			else  {
				showToast(eliminarResponsableProxy.getResponse().getDescripcion());
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
