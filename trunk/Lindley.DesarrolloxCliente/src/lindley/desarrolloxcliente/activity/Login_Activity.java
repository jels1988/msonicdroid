package lindley.desarrolloxcliente.activity;

import roboguice.inject.InjectResource;
import roboguice.inject.InjectView;
import com.google.inject.Inject;
import com.thira.examples.actionbar.widget.ActionBar;
import net.msonic.lib.ActivityBase;
import lindley.desarrolloxcliente.MyApplication;
import lindley.desarrolloxcliente.R;
import lindley.desarrolloxcliente.to.UsuarioTO;
import lindley.desarrolloxcliente.ws.service.LoginProxy;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Login_Activity extends ActivityBase {
	
	@InjectView(R.id.txtUsuario) 	TextView 	txtUsuario;
	@InjectView(R.id.txtPassword) 	TextView 	txtPassword;
	@InjectView(R.id.actionBar)  	ActionBar 	mActionBar;
	@InjectResource(R.string.login_activity_ok) String login_ok;
	@InjectResource(R.string.login_activity_error) String login_error;
	@InjectResource(R.string.login_activity_txtlogin_empty) String txtlogin_empty;
	@InjectResource(R.string.login_activity_txtpassword_empty) String txtpassword_empty;
	@Inject 						LoginProxy 	loginProxy;

	@InjectView(R.id.btnLogin) 	Button 	btnLogin;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	inicializarRecursos();
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.login_activity);
        
        mActionBar.setTitle(R.string.login_activity_title);
		mActionBar.setHomeLogo(R.drawable.header_logo);
	
		txtUsuario.requestFocus();
	
		
    }
    
    public void btnLogin_click(View v){
		processAsync();
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

	@Override
	protected void process() {

		//consultarFotoProxy.setPeriodo("201011");
		//consultarFotoProxy.execute();
		
		String usuario = txtUsuario.getText().toString();
		String password = txtPassword.getText().toString();
		
		loginProxy.setUsuario(usuario);
		loginProxy.setPassword(password);

		loginProxy.execute();

	}
	
	
	@Override
	protected void processOk() {
		
		boolean isExito = loginProxy.isExito();

		if (isExito) {
			int status = loginProxy.getResponse().getStatus();
			
			String message;
			if (status == 0) {
				UsuarioTO usuarioTO = loginProxy.getResponse().getUsuario();
				MyApplication application = (MyApplication)contextProvider.get().getApplicationContext();
				application.setUsuarioTO(usuarioTO);
				
				txtUsuario.setText("");
				txtPassword.setText("");
				
				message = String.format(login_ok,usuarioTO.getNombres());
				
				Intent intent = new Intent("lindley.desarrolloxcliente.consultarcliente");
				startActivity(intent);
				
			}else{
				message = String.format(
						login_error,
						loginProxy.getResponse().getDescripcion());
				
				txtUsuario.requestFocus();
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