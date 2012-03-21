package pe.pae.encuesta.activity;

import pe.pae.encuesta.R;
import pe.pae.encuesta.negocio.ClienteBLL;
import pe.pae.encuesta.to.UsuarioTO;
import pe.pae.encuesta.ws.service.LoginProxy;
import roboguice.inject.InjectResource;
import roboguice.inject.InjectView;

import com.google.inject.Inject;
import com.thira.examples.actionbar.widget.ActionBar;

import net.msonic.lib.ActivityBase;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnKeyListener;
import android.widget.TextView;

public class Login_Activity extends ActivityBase {
    /** Called when the activity is first created. */
	
	@InjectView(R.id.actionBar)  	ActionBar 	mActionBar;
	@Inject 						LoginProxy 	loginProxy;
	@Inject 						ClienteBLL 	clienteBLL; 
	
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
        
        txtPassword.setOnKeyListener(new OnKeyListener() {			
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				// TODO Auto-generated method stub
				if(keyCode == KeyEvent.KEYCODE_ENTER){  
					btnIngresar_onclick(null);
				}
				return false;
			}
		});
        
      
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
	Context ctx=this;
	if (isExito) {
		int status = loginProxy.getResponse().getStatus();
			if (status == 0) {
				
				MiApp miApp = (MiApp)getApplication();
				UsuarioTO usuarioTO = loginProxy.getResponse().usuario;
				miApp.setUsuarioTO(usuarioTO);
				message = String.format(login_ok,usuarioTO.nombres);
				clienteBLL.saveCliente(loginProxy.getResponse().clientes);
				
				
				SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(ctx);
				int ENCUESTA_INICIO = settings.getInt("ENCUESTA_INICIO",0);
				
				if(ENCUESTA_INICIO==0){
					Intent i = new Intent("pae.activity.seleccionarTienda");
			    	startActivity(i);
				}else{
					
					int CLIENTE_ID = settings.getInt("CLIENTE_ID",0);
					int TIENDA_ID = settings.getInt("TIENDA_ID",0);
					String TIENDA = settings.getString("TIENDA","");
					
					 miApp.tiendaId=TIENDA_ID;
				     miApp.tienda=TIENDA;
				     miApp.clienteId=CLIENTE_ID;
				     
					Intent i = new Intent("pae.activity.buscarProducto");
			    	startActivity(i);
				}
		    	
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