package pe.lindley.puntointeres.activity;

import java.util.List;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.view.View;
import com.google.inject.Inject;
import com.thira.examples.actionbar.widget.ActionBar;
import pe.lindley.activity.R;
import pe.lindley.puntointeres.negocio.ParametroBLL;
import pe.lindley.puntointeres.to.ParametroTO;
import pe.lindley.puntointeres.ws.service.ObtenerParametroProxy;
import pe.lindley.util.ActivityBase;
import pe.lindley.util.MessageBox;
import roboguice.inject.InjectResource;
import roboguice.inject.InjectView;

public class DescargarParametrosActivity extends ActivityBase {
		
	@InjectView(R.id.actionBar)  	ActionBar 	mActionBar;
	@Inject ObtenerParametroProxy obtenerParametroProxy;
	@Inject ParametroBLL parametroBLL;
	@InjectResource(R.string.puntointeres_descargarparametros_activity_title) 	String 	puntointeres_descargarparametros_activity_title;
	@InjectResource(R.string.puntointeres_descargarparametros_activity_message) String 	puntointeres_descargarparametros_activity_message;
	@InjectResource(R.string.puntointeres_descargarparametros_activity_error) 	String 	puntointeres_descargarparametros_activity_error;
	
	@InjectResource(R.string.puntointeres_descargarparametros_activity_confirm_dialog_message) 	String 	confirm_message;
	@InjectResource(R.string.puntointeres_descargarparametros_activity_question_dialog_message) String 	question_message;
	@InjectResource(R.string.puntointeres_descargarparametros_activity_confirm_dialog_si) 		String 	confirm_si;
	@InjectResource(R.string.puntointeres_descargarparametros_activity_confirm_dialog_no) 		String 	confirm_no;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		setContentView(R.layout.puntointeres_descargar_parametros_activity);	
		mActionBar.setTitle(R.string.puntointeres_descargarparametros_activity_title);
		mActionBar.setHomeLogo(R.drawable.header_logo);		
	}
	
	public void btnCancelar_onclick(View view){
		finish();
	}
	
	public void btnAceptar_onclick(View view)
	{
		final Context context = this;
		
		MessageBox.showConfirmDialog(context, 
				puntointeres_descargarparametros_activity_title, 
				question_message, 
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
		obtenerParametroProxy.execute();
	}

	@Override
	protected void processOk() {
		// TODO Auto-generated method stub
		boolean isExito = obtenerParametroProxy.isExito();
		
		if (isExito) {
			int status = obtenerParametroProxy.getResponse().getStatus();
			if (status == 0) {
				List<ParametroTO> parametros =	obtenerParametroProxy.getResponse().getListaParametro();
				if(parametros!=null){
					parametroBLL.deleteAll();		
					parametroBLL.save(parametros);
				}
								
				final Context context = this;
				
				MessageBox.showSimpleDialog(context, puntointeres_descargarparametros_activity_title, confirm_message, "Aceptar", new OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						finish();
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
