package pe.lindley.ventacero.activity;

import java.util.List;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.view.View;
import com.google.inject.Inject;
import com.thira.examples.actionbar.widget.ActionBar;
import pe.lindley.activity.R;
import pe.lindley.activity.RTMApplication;
import pe.lindley.lanzador.to.UsuarioTO;
import pe.lindley.util.ActivityBase;
import pe.lindley.util.MessageBox;
import pe.lindley.ventacero.negocio.ParametroBLL;
import pe.lindley.ventacero.to.ParametroTO;
import pe.lindley.ventacero.ws.service.ObtenerParametroProxy;
import roboguice.inject.InjectResource;
import roboguice.inject.InjectView;

public class DescargarParametrosActivity extends ActivityBase {
	
	@InjectView(R.id.actionBar)  	ActionBar 	mActionBar;
	@Inject ParametroBLL parametroBLL;
	@Inject ObtenerParametroProxy obtenerParametroProxy;
	@InjectResource(R.string.ventacero_descargarparametros_activity_title) 	String 	ventacero_descargar_parametros_title;
	@InjectResource(R.string.ventacero_descargarparametros_activity_message) 	String 	ventacero_descargarparametros_activity_message;
	
	@InjectResource(R.string.ventacero_descargarparametros_activity_confirm_dialog_message) 			String 	confirm_message;
	@InjectResource(R.string.ventacero_descargarparametros_activity_confirm_dialog_si) 		String 	confirm_si;
	@InjectResource(R.string.ventacero_descargarparametros_activity_confirm_dialog_no) 		String 	confirm_no;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ventacero_descargar_parametros_activity);
		mActionBar.setTitle(R.string.ventacero_descargarparametros_activity_title);
		mActionBar.setHomeLogo(R.drawable.header_logo);
	}
	
	public void btnCancelar_onclick(View view){
		finish();
	}
	
	public void btnAceptar_onclick(View view)
	{
		final Context context = this;
		
		MessageBox.showConfirmDialog(context, 
				ventacero_descargar_parametros_title, 
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
		RTMApplication application = (RTMApplication)getApplicationContext();
		UsuarioTO usuario = application.getUsuarioTO();		
		parametroBLL.deleteAll();
		obtenerParametroProxy.setCodigo(usuario.getCodigoSap());
		obtenerParametroProxy.setDeposito(usuario.getCodigoDeposito());
		obtenerParametroProxy.execute();
		super.process();
	}

	@Override
	protected void processOk() {
		// TODO Auto-generated method stub
		boolean isExitoGiro = obtenerParametroProxy.isExito();
		
		if (isExitoGiro) {
			int status = obtenerParametroProxy.getResponse().getStatus();
			if (status == 0) {
				List<ParametroTO> parametros =	obtenerParametroProxy.getResponse().getListaParametro();
				if(parametros!=null){
					parametroBLL.save(parametros);
				}
				final Context context = this;
				
				MessageBox.showSimpleDialog(context, ventacero_descargar_parametros_title, ventacero_descargarparametros_activity_message, "Aceptar", new OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						finish();
						//Intent intent = new Intent(context,pe.lindley.ventacero.activity.VentaCeroActivity.class);
						//startActivity(intent);
					}
				});	
			}
			else  {
				showToast(obtenerParametroProxy.getResponse().getDescripcion());
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
