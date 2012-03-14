package pe.pae.encuesta.activity;

import pe.pae.encuesta.R;
import pe.pae.encuesta.to.UsuarioTO;
import pe.pae.encuesta.ws.service.LoginProxy;
import roboguice.inject.InjectResource;
import roboguice.inject.InjectView;

import com.google.inject.Inject;
import com.thira.examples.actionbar.widget.ActionBar;

import net.msonic.lib.ActivityBase;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Login_Activity extends ActivityBase {
    /** Called when the activity is first created. */
	
	@InjectView(R.id.actionBar)  	ActionBar 	mActionBar;
	@Inject 						LoginProxy 	loginProxy;
	
	
	@InjectView(R.id.txtUsuario) 	TextView 	txtUsuario;
	@InjectView(R.id.txtPassword) 	TextView 	txtPassword;
	
	
	@InjectResource(R.string.login_activity_txtlogin_empty) String txtlogin_empty;
	@InjectResource(R.string.login_activity_txtpassword_empty) String txtpassword_empty;
	
	@InjectResource(R.string.login_activity_ok) String login_ok;
	@InjectResource(R.string.login_activity_error) String login_error;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        inicializarRecursos();
        
        setContentView(R.layout.login_activity);
        mActionBar.setTitle(R.string.login_activity_title);
        
        
    }
    
    
    
    
	@Override
	protected boolean executeAsyncPre() {
		// TODO Auto-generated method stub
		boolean tieneError=false;
		
		if( txtUsuario.getText().toString().trim().equalsIgnoreCase("")){
			txtUsuario.setError(txtlogin_empty);
			tieneError=true;
		}
		

		if( txtPassword.getText().toString().trim().equalsIgnoreCase("")){
			txtPassword.setError(txtpassword_empty);
			tieneError=true;
		}
		
		return !tieneError;
	}
	
    
    public void btnIngresar_onclick(View v){
    	
    	processAsync();
    }

	@Override
	protected void process() {
		// TODO Auto-generated method stub
		
		String usuario = txtUsuario.getText().toString();
		String password = txtPassword.getText().toString();
		
		loginProxy.usuario=usuario;
		loginProxy.password=password;
		
		loginProxy.execute();
		
		
	}

	@Override
	protected void processOk() {
	// TODO Auto-generated method stub
	boolean isExito = loginProxy.isExito();
	String message;
	
	if (isExito) {
		int status = loginProxy.getResponse().getStatus();
			if (status == 0) {
				
				UsuarioTO usuarioTO = loginProxy.getResponse().usuario;
				message = String.format(login_ok,usuarioTO.nombres);
				
				Intent i = new Intent("pae.activity.seleccionarTienda");
		    	startActivity(i);
		    	
			}else{
				message = String.format(
						login_error,
						loginProxy.getResponse().getDescripcion());
			}
			
			super.processOk();
			showToast(message);
		}else{
			processError();
		}
	}
	

	@Override
	protected void processError() {
		// TODO Auto-generated method stub
		String message;
		if(loginProxy.getResponse()!=null){
			String error = loginProxy.getResponse().getDescripcion();
			message = String.format(login_error,error);
		}else{
			message = error_generico_process;
		}
		super.processError();
		showToast(message);
	}
    
    
    
}