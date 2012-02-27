package pe.lindley.om.activity;

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
import pe.lindley.om.negocio.ClienteBLL;
import pe.lindley.om.to.ClienteTO;
import pe.lindley.om.ws.service.DescargarClienteProxy;
import pe.lindley.util.ActivityBase;
import pe.lindley.util.MessageBox;
import roboguice.inject.InjectResource;
import roboguice.inject.InjectView;

public class DescargarClientesActivity extends ActivityBase {

	private UsuarioTO usuario = null;
	
	@InjectView(R.id.actionBar)  	ActionBar 	mActionBar;
	@Inject DescargarClienteProxy descargarClienteProxy;
	
	@Inject ClienteBLL clienteBLL;
	
	@InjectResource(R.string.descargarclientes_activity_title) 		String 	descargarclientes_activity_title;
	@InjectResource(R.string.descargarclientes_activity_message) 	String 	descargarclientes_activity_message;
	@InjectResource(R.string.descargarclientes_activity_error) 		String 	descargarclientes_activity_error;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
		setContentView(R.layout.descargarclientes_activity);
		
		mActionBar.setTitle(R.string.descargarclientes_activity_title);
		mActionBar.setHomeLogo(R.drawable.header_logo);
		
        RTMApplication application = (RTMApplication)contextProvider.get().getApplicationContext();
		usuario = application.getUsuarioTO();
		
	}
	@Override
	protected void process() {
		String codigoDeposito = usuario.getCodigoDeposito();
		String ruta = usuario.getCodigoRuta();
		
		descargarClienteProxy.setCodigoDeposito(codigoDeposito);
		descargarClienteProxy.setCodigoRuta(ruta);
		descargarClienteProxy.execute();
		
		List<ClienteTO> clientes = descargarClienteProxy.getResponse().getClientes();
		
		clienteBLL.save(clientes);
		
	}
	@Override
	protected void processOk() {
		
		boolean isExito = descargarClienteProxy.isExito();
		int status = -1;
		if (!isExito) {
			status = descargarClienteProxy.getResponse().getStatus();
			String message;
			if (status != 0) {
				message = descargarClienteProxy.getResponse().getDescripcion();
				super.processError();
				showToast(message);
				return;
			}else{
				processOk();
				return;
			}
		}
		
		
		super.processOk();
		final Context context = this;
		
		
		MessageBox.showSimpleDialog(context, descargarclientes_activity_title, descargarclientes_activity_message, "Aceptar", new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				finish();
			}
		});

		
	}
	
	@Override
	protected void processError() {
		String message;
		if(descargarClienteProxy.getResponse()!=null){
			String error = descargarClienteProxy.getResponse().getDescripcion();
			message = error;
		}else{
			message = error_generico_process;
		}
		
		super.processError();
		showToast(message);
		return;
	}
	
	public void btnCancelar_onclick(View view){
		finish();
	}
	
	public void btnAceptar_onclick(View view){
		processAsync();
	}

	
}
