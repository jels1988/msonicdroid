package pe.lindley.ficha.activity;

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
import pe.lindley.ficha.to.OpcionMultipleTO;
import pe.lindley.ficha.ws.service.ObtenerOpcionMultipleProxy;
import pe.lindley.ficha.negocio.OpcionMultipleBLL;
import pe.lindley.lanzador.to.ClienteResumenTO;
import pe.lindley.plandesarrollo.negocio.ParametroBLL;
import pe.lindley.plandesarrollo.to.ParametroTO;
import pe.lindley.plandesarrollo.ws.service.DescargarParametrosProxy;
import pe.lindley.util.ActivityBase;
import pe.lindley.util.MessageBox;
import roboguice.inject.InjectResource;
import roboguice.inject.InjectView;

public class DescargarParametrosActivity extends ActivityBase{
		
	@InjectView(R.id.actionBar)  	ActionBar 	mActionBar;
	@Inject ObtenerOpcionMultipleProxy obtenerParametros;
	@Inject DescargarParametrosProxy 	obtenerParametrosPD;
	@Inject OpcionMultipleBLL parametroBLL;
	@Inject ParametroBLL      parametroPDBLL;
	@InjectResource(R.string.ficha_descargarparametros_activity_title) 	String 	ficha_descargar_parametros_title;
	@InjectResource(R.string.ficha_descargarparametros_activity_message) 	String 	ficha_descargarparametros_activity_message;
	@InjectResource(R.string.ficha_descargarparametros_activity_error) 	String 	ficha_descargarparametros_activity_error;
	
	@InjectResource(R.string.ficha_descargarparametros_activity_confirm_dialog_message) 			String 	confirm_message;
	@InjectResource(R.string.ficha_descargarparametros_activity_confirm_dialog_si) 		String 	confirm_si;
	@InjectResource(R.string.ficha_descargarparametros_activity_confirm_dialog_no) 		String 	confirm_no;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		setContentView(R.layout.ficha_descargar_parametros_activity);	
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
		final Context context = this;
		
		MessageBox.showConfirmDialog(context, 
				ficha_descargar_parametros_title, 
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
		obtenerParametros.execute();
		
		parametroPDBLL.deleteAll();		
		obtenerParametrosPD.execute();		
		super.process();
	}

	@Override
	protected void processOk() {
		// TODO Auto-generated method stub
		boolean isExitoGiro = obtenerParametros.isExito();
		boolean isExitoPD = obtenerParametrosPD.isExito();
		
		if (isExitoGiro && isExitoPD) {
			int status = obtenerParametros.getResponse().getStatus();
			int statusPD = obtenerParametrosPD.getResponse().getStatus();
			if (status == 0 && statusPD==0) {
				List<OpcionMultipleTO> parametros =	obtenerParametros.getResponse().getObtenerOpciones();
				if(parametros!=null){
					parametroBLL.save(parametros);
				}
				
				List<ParametroTO> parametrosPD =	obtenerParametrosPD.getResponse().getParametros();
				if(parametrosPD!=null){
					parametroPDBLL.save(parametrosPD);
				}
				
				final Context context = this;
				
				MessageBox.showSimpleDialog(context, ficha_descargar_parametros_title, ficha_descargarparametros_activity_message, "Aceptar", new OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						//Intent intent = new Intent(context,ComercialActivity.class);
						//startActivity(intent);
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
