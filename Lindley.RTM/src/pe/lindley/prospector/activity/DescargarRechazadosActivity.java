package pe.lindley.prospector.activity;


import java.util.ArrayList;

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
import pe.lindley.prospector.negocio.ClienteBLL;
import pe.lindley.prospector.to.ClienteTO;
import pe.lindley.prospector.ws.service.FichasRechazadasProxy;
import pe.lindley.util.ActivityBase;
import pe.lindley.util.MessageBox;
import roboguice.inject.InjectResource;
import roboguice.inject.InjectView;

public class DescargarRechazadosActivity extends ActivityBase {

	@InjectView(R.id.actionBar)  	ActionBar 				mActionBar;
	@Inject							FichasRechazadasProxy 	fichasRechazadasProxy;
	@Inject							ClienteBLL				clienteBLL;
	
	@InjectResource(R.string.fichasrechazadas_clientes_activity_title) String 	descargar_cliente_title;
	@InjectResource(R.string.fichasrechazadas_clientes_activity_confirm_dialog_no) 		String 	confirm_no;
	@InjectResource(R.string.fichasrechazadas_clientes_activity_confirm_dialog_si) 		String 	confirm_si;
	@InjectResource(R.string.fichasrechazadas_clientes_activity_confirm_dialog_ok) 		String 	confirm_ok;
	@InjectResource(R.string.fichasrechazadas_clientes_activity_confirm_dialog_message) 			String 	confirm_message;
	@InjectResource(R.string.fichasrechazadas_clientes_activity_confirm_dialog_message_ok) 		String 	confirm_message_ok;
	@InjectResource(R.string.fichasrechazadas_clientes_activity_confirm_dialog_message_error) 	String 	confirm_message_error;
	
	UsuarioTO usuario = null;
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.descargarrechazados_activity);
        
        mActionBar.setTitle(R.string.sincronizar_clientes_activity_title);
        mActionBar.setHomeLogo(R.drawable.header_logo); 
        
        RTMApplication application = (RTMApplication)contextProvider.get().getApplicationContext();
		usuario = application.getUsuarioTO();
		
		
		
	}
	
	@Override
	protected void process() {
		
		fichasRechazadasProxy.setUsuario(usuario.getCodigoSap());
		fichasRechazadasProxy.execute();
		
	}
	
	@Override
	protected void processOk() {
		
		boolean isExito = fichasRechazadasProxy.isExito();

		if (isExito) {
			int status = fichasRechazadasProxy.getResponse().getStatus();
			
			String message;
			if (status == 0) {
				
				ArrayList<ClienteTO> fichasRechazadas = fichasRechazadasProxy.getResponse().getFichasRechazadas();
				clienteBLL.saveFichasRechazadas(fichasRechazadas, usuario);
				message = confirm_message_ok;
				
				MessageBox.showSimpleDialog(this, 
						descargar_cliente_title, 
						confirm_message_ok, 
						confirm_ok,
						new OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog, int which) {
								// TODO Auto-generated method stub
								terminar(dialog, which);
							}
						});
				
				
			}else{
				message = fichasRechazadasProxy.getResponse().getDescripcion();
			}
			
			super.processOk();
			showToast(message);
		}else{
			processError();
		}
	
	}

	@Override
	protected void processError() {
		String message;
		if(fichasRechazadasProxy.getResponse()!=null){
			String error = fichasRechazadasProxy.getResponse().getDescripcion();
			message = error;
		}else{
			message = error_generico_process;
		}
		super.processError();
		showToast(message);
	}
	
	
	public void btnAceptar_onclick(View view){
		final Context context = this;
		
		MessageBox.showConfirmDialog(context, 
				descargar_cliente_title, 
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
	
	public void btnCancelar_onclick(View view){
		terminar(null, 0);
	}
	
	public final void terminar(DialogInterface dialog, int which) {
		// TODO Auto-generated method stub
		finish();
		
	}
}
