package pe.lindley.profit.activity;

import java.util.List;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.view.View;
import com.google.inject.Inject;
import com.thira.examples.actionbar.widget.ActionBar;

import pe.lindley.activity.DireccionActivity;
import pe.lindley.activity.R;
import pe.lindley.profit.negocio.ParametroBLL;
import pe.lindley.profit.to.ParametroTO;
import pe.lindley.profit.ws.service.DescargarParametrosProxy;
import pe.lindley.prospector.activity.ConsultarClienteActivity;
import pe.lindley.util.ActivityBase;
import pe.lindley.util.MessageBox;
import roboguice.inject.InjectResource;
import roboguice.inject.InjectView;

public class DescargarParametrosActivity extends ActivityBase {
	
	@InjectView(R.id.actionBar)  	ActionBar 	mActionBar;
	@Inject ParametroBLL parametroBLL;
	@Inject DescargarParametrosProxy descargarParametrosProxy;
	@InjectResource(R.string.profit_descargarparametros_activity_confirm_dialog_message) 	String 	confirm_message;
	@InjectResource(R.string.profit_descargarparametros_activity_confirm_dialog_si) 		String 	confirm_si;
	@InjectResource(R.string.profit_descargarparametros_activity_confirm_dialog_no) 		String 	confirm_no;
	@InjectResource(R.string.profit_descargarparametros_activity_title) 					String 	profit_descargar_parametros_title;
	@InjectResource(R.string.profit_descargarparametros_activity_message) 					String 	profit_descargarparametros_activity_message;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.profit_descargar_parametros_activity);
		mActionBar.setTitle(R.string.profit_descargarparametros_activity_title);
		mActionBar.setHomeLogo(R.drawable.header_logo);
	}
	
	public void btnCancelar_onclick(View view){
		finish();
	}
	
	public void btnAceptar_onclick(View view)
	{
		final Context context = this;
		
		MessageBox.showConfirmDialog(context, 
				profit_descargar_parametros_title, 
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
				null);
	}
	
	@Override
	protected void process() {
		// TODO Auto-generated method stub	
		parametroBLL.deleteAll();
		descargarParametrosProxy.execute();
		super.process();
	}
	

	@Override
	protected void processOk() {
		// TODO Auto-generated method stub
		boolean isExitoGiro = descargarParametrosProxy.isExito();
		
		if (isExitoGiro) {
			int status = descargarParametrosProxy.getResponse().getStatus();
			if (status == 0) {
				List<ParametroTO> parametros =	descargarParametrosProxy.getResponse().getListaParametro();
				if(parametros!=null){
					parametroBLL.save(parametros);
				}
				final Context context = this;
				
				MessageBox.showSimpleDialog(context, profit_descargar_parametros_title, "Se descago la información.", "Aceptar", new OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						Intent consultarCliente = new Intent(context, ConsultarClienteActivity.class);
						context.startActivity(consultarCliente);						
					}
				});	
			}
			else  {
				showToast(descargarParametrosProxy.getResponse().getDescripcion());
			}
		}
		else{
			processError();
		}
		
		super.processOk();
	}

	@Override
	protected void processError() {
		// TODO Auto-generated method stub
		super.processError();
		showToast(error_generico_process);
	}
}
