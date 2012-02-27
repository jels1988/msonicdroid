package pe.lindley.plandesarrollo.activity;

import java.util.List;
import com.google.inject.Inject;
import com.thira.examples.actionbar.widget.ActionBar;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.view.View;
import pe.lindley.activity.R;
import pe.lindley.activity.RTMApplication;
import pe.lindley.lanzador.to.ClienteResumenTO;
import pe.lindley.plandesarrollo.negocio.ParametroBLL;
import pe.lindley.plandesarrollo.ws.service.DescargarParametrosProxy;
import pe.lindley.util.ActivityBase;
import pe.lindley.util.MessageBox;
import roboguice.inject.InjectResource;
import roboguice.inject.InjectView;

public class DescargarParametrosActivity extends ActivityBase {

	@InjectView(R.id.actionBar)  	ActionBar 	mActionBar;
	@Inject DescargarParametrosProxy 	obtenerParametros;
	@Inject ParametroBLL           		parametroBLL;
	@InjectResource(R.string.plandesarrollo_descargarparametros_activity_title) 	String 	plandesarrollo_descargar_parametros_title;
	@InjectResource(R.string.plandesarrollo_descargarparametros_activity_message) 	String 	plandesarrollo_descargarparametros_activity_message;
	@InjectResource(R.string.plandesarrollo_descargarparametros_activity_error) 	String 	plandesarrollo_descargarparametros_activity_error;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.plandesarrollo_descargar_parametros_activity);
		final RTMApplication application = (RTMApplication)contextProvider.get().getApplicationContext();
		ClienteResumenTO cliente = application.getClienteTO();
		mActionBar.setSubTitle(cliente.getCodigoCliente()+"-"+cliente.getRazonSocial());
		mActionBar.setTitle(R.string.descargarparametros_activity_title);
		mActionBar.setHomeLogo(R.drawable.header_logo);
	}
	
	public void btnCancelar_onclick(View view){
		finish();
	}
	
	public void btnAceptar_onclick(View view)
	{
		processAsync();
	}
	
	@Override
	protected void process() {
		// TODO Auto-generated method stub
		parametroBLL.deleteAll();		
		obtenerParametros.execute();
		super.process();
	}

	@Override
	protected void processOk() {
		// TODO Auto-generated method stub
boolean isExitoGiro = obtenerParametros.isExito();
		
		if (isExitoGiro) {
			int status = obtenerParametros.getResponse().getStatus();
			if (status == 0) {
				List<pe.lindley.plandesarrollo.to.ParametroTO> parametros =	obtenerParametros.getResponse().getParametros();
				if(parametros!=null){
					parametroBLL.save(parametros);
				}
				final Context context = this;
				
				MessageBox.showSimpleDialog(context, plandesarrollo_descargar_parametros_title, plandesarrollo_descargarparametros_activity_message, "Aceptar", new OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						finish();
					}
				});	
			}
			else  {
				showToast(obtenerParametros.getResponse().getDescripcion());
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
