package lindley.desarrolloxcliente.activity;

import net.msonic.lib.JSONHelper;
import lindley.desarrolloxcliente.MyApplication;
import lindley.desarrolloxcliente.R;
import lindley.desarrolloxcliente.to.PeriodoTO;
import lindley.desarrolloxcliente.to.UsuarioTO;
import lindley.desarrolloxcliente.ws.service.LoginProxy;
import roboguice.inject.InjectResource;
import roboguice.inject.InjectView;
import com.google.inject.Inject;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Login_Activity extends net.msonic.lib.sherlock.ActivityBase{
	
	@InjectView(R.id.txtUsuario) 	TextView 	txtUsuario;
	@InjectView(R.id.txtPassword) 	TextView 	txtPassword;
	@InjectResource(R.string.login_activity_ok) String login_ok;
	@InjectResource(R.string.login_activity_error) String login_error;
	@InjectResource(R.string.login_activity_txtlogin_empty) String txtlogin_empty;
	@InjectResource(R.string.login_activity_txtpassword_empty) String txtpassword_empty;
	@Inject 						LoginProxy 	loginProxy;
	@Inject 	 					PeriodoTO 	periodoTO;
	
	@InjectView(R.id.btnLogin) 	Button 	btnLogin;
	private boolean isConectadoInternet=false;
	
	private  static String TAG = Login_Activity.class.getSimpleName();
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        //requestWindowFeature(Window.FEATURE_PROGRESS);
        //requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        super.onCreate(savedInstanceState);
        inicializarRecursos();
    
        setContentView(R.layout.login_activity);
        setTitle(R.string.login_activity_title);
       
        periodoTO.anio = 2012;
        periodoTO.mes=11;
	
		txtUsuario.requestFocus();
	
		
    }
    
    public void btnLogin_click(View v){
    	isConectadoInternet = isNetworkAvailable();
    	
    	Log.d(TAG, String.format("CONECTADO INTERNET %s",isConectadoInternet));
    	
    	if(isConectadoInternet){
    		validarConexionInternet=true;
    	}else{
    		validarConexionInternet=false;
    	}
    	
    	
    	
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
	protected void process()  throws Exception{

		
		String usuario = txtUsuario.getText().toString();
		String password = txtPassword.getText().toString();
		
		if(isConectadoInternet){
			
			loginProxy.setUsuario(usuario);
			loginProxy.setPassword(password);
			loginProxy.execute();
		}else{
			isValidUser=false;
			usuarioFromPreferencia=null;
			validarUsuarioPrefencia(usuario,password);
		}
			

	}
	
	private boolean isValidUser=false;
	private boolean isPresentCredentialUser=false;
	private UsuarioTO usuarioFromPreferencia=null;
	
	private void validarUsuarioPrefencia(String usuario,String password){
		
		Log.d(TAG, String.format("VALIDAR USUARIO PREFERENCIAS: %s",usuario));
		
		SharedPreferences sp1=this.getSharedPreferences("LOGIN_DATA",0);
		String usuario_preferencia = sp1.getString("USUARIO_KEY", null);
		String password_preferencia = sp1.getString("PASSWORD_KEY", null);
		String usuario_data_key = sp1.getString("USUARIO_DATA_KEY", null);
		usuarioFromPreferencia=null;
		isPresentCredentialUser=false;
		
		if(usuario_preferencia==null || password_preferencia==null || usuario_data_key==null){
			Log.d(TAG, "NO EXISTE CREDENCIALES EN PREFERENCIAS");
			isPresentCredentialUser = false;
			return;
		}
		
		isPresentCredentialUser=true;
		isValidUser = (usuario_preferencia.compareTo(usuario)==0) && (password_preferencia.compareTo(password)==0);
		usuarioFromPreferencia = JSONHelper.desSerializar(usuario_data_key, UsuarioTO.class);
		
	}
	
	private void savePreferencia(String usuario,String password,UsuarioTO usuarioTO){
		Log.d(TAG, String.format("GUARDANDO CREDENCIALES: %s",usuario));
		
		SharedPreferences sp=getSharedPreferences("LOGIN_DATA", 0);
		SharedPreferences.Editor Ed=sp.edit();
		Ed.putString("USUARIO_KEY",usuario);              
		Ed.putString("PASSWORD_KEY",password); 
		Ed.putString("USUARIO_DATA_KEY",JSONHelper.serializar(usuarioTO)); 
		Ed.commit();
		
		Log.d(TAG, String.format("FIN GUARDANDO CREDENCIALES: %s",usuario));
	}
	
	@Override
	protected void processOk() {
		
		boolean isExito = false;
		
		
		String message="";
		
		if(isConectadoInternet){
			isExito = loginProxy.isExito();
		}else{
			isExito=true;
		}
			
		if (isExito) {
			
			if(isConectadoInternet){
				
				int status = loginProxy.getResponse().getStatus();
				
				if (status == 0) {
					savePreferencia(txtUsuario.getText().toString(), 
									txtPassword.getText().toString(), 
									loginProxy.getResponse().getUsuario());
					
					MyApplication application = (MyApplication)contextProvider.get().getApplicationContext();
					UsuarioTO usuarioTO = loginProxy.getResponse().getUsuario();
					application.setUsuarioTO(usuarioTO);
					
					txtUsuario.setText("");
					txtPassword.setText("");
					
					message = String.format(login_ok,usuarioTO.getNombres());
					
					String JSONUser = "{\"NOM\":\"MARIA CALERO\",\"CSP\":\"6654\",\"Rol\":\"13\",\"CDDEP\":\"F3\",\"PWD\":\"\",\"CDRUT\":\"\",\"MPER\":{\"1\":\"\",\"2\":\"\",\"3\":\"\",\"7\":\"\",\"9\":\"\",\"10\":\"\",\"11\":\"\"},\"OPER\":{\"20\":\"\",\"10\":\"\",\"11\":\"\",\"12\":\"\",\"13\":\"\",\"21\":\"\",\"14\":\"\",\"30\":\"\",\"31\":\"\",\"32\":\"\",\"33\":\"\",\"34\":\"\",\"35\":\"\",\"36\":\"\",\"300\":\"\",\"350\":\"\",\"351\":\"\",\"320\":\"\",\"310\":\"\",\"311\":\"\",\"15\":\"\"},\"RolId\":\"1\",\"CSB\":\"CT\",\"DDP\":\"ATI SAC\"}";
					Intent intent = new Intent(this,ConsultarCliente_Activity.class);
					intent.putExtra(ConsultarCliente_Activity.CODIGO_CLIENTE_KEY, "5793");
					intent.putExtra(ConsultarCliente_Activity.USUARIO_KEY, JSONUser);
					
					
					startActivity(intent);
				}else{
					message = String.format(
							login_error,
							loginProxy.getResponse().getDescripcion());
					
					txtUsuario.requestFocus();
				}
				
			}else{
				
				if(!isPresentCredentialUser){
					message = String.format(login_error,"No cuenta con una conexi—n a internet y no podemos acceder credenciales.");
				}else{
					if(isValidUser){
						MyApplication application = (MyApplication)contextProvider.get().getApplicationContext();
						application.setUsuarioTO(usuarioFromPreferencia);
						message = String.format(login_ok,usuarioFromPreferencia.getNombres());
						
						String JSONUser = "{\"NOM\":\"MARIA CALERO\",\"CSP\":\"6654\",\"Rol\":\"13\",\"CDDEP\":\"F3\",\"PWD\":\"\",\"CDRUT\":\"\",\"MPER\":{\"1\":\"\",\"2\":\"\",\"3\":\"\",\"7\":\"\",\"9\":\"\",\"10\":\"\",\"11\":\"\"},\"OPER\":{\"20\":\"\",\"10\":\"\",\"11\":\"\",\"12\":\"\",\"13\":\"\",\"21\":\"\",\"14\":\"\",\"30\":\"\",\"31\":\"\",\"32\":\"\",\"33\":\"\",\"34\":\"\",\"35\":\"\",\"36\":\"\",\"300\":\"\",\"350\":\"\",\"351\":\"\",\"320\":\"\",\"310\":\"\",\"311\":\"\",\"15\":\"\"},\"RolId\":\"1\",\"CSB\":\"CT\",\"DDP\":\"ATI SAC\"}";
						Intent intent = new Intent(this,ConsultarCliente_Activity.class);
						intent.putExtra(ConsultarCliente_Activity.CODIGO_CLIENTE_KEY, "5793");
						intent.putExtra(ConsultarCliente_Activity.USUARIO_KEY, JSONUser);
						
						startActivity(intent);
					}else{
						message = String.format(
								login_error,
								"Usuario o Password incorrecto.");
						
						txtUsuario.requestFocus();
					}
				}
				
				
			}
			
			
			Log.d(TAG,message);
			
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