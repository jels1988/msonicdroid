package pe.lindley.mmil.titanium;



import pe.lindley.mmil.titanium.to.UsuarioTO;
import pe.lindley.mmil.titanium.ws.service.LoginProxy;
import net.msonic.lib.ActivityBase;
import roboguice.inject.InjectResource;
import roboguice.inject.InjectView;

import com.google.inject.Inject;
import com.thira.examples.actionbar.widget.ActionBar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LoginActivity extends ActivityBase {
	
	
	
	
	@InjectView(R.id.btnLogin) 	Button 	btnLogin;
	@InjectView(R.id.txtUsuario) 	TextView 	txtUsuario;
	@InjectView(R.id.txtPassword) 	TextView 	txtPassword;
	@InjectView(R.id.actionBar)  	ActionBar 	mActionBar;
	@InjectResource(R.string.login_activity_ok) String login_ok;
	@InjectResource(R.string.login_activity_error) String login_error;
	@InjectResource(R.string.login_activity_txtlogin_empty) String txtlogin_empty;
	@InjectResource(R.string.login_activity_txtpassword_empty) String txtpassword_empty;
	@InjectResource(R.string.login_activity_no_tiene_acceso) String usuario_no_tiene_acceso;
	
	
	@Inject 						LoginProxy 	loginProxy;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        inicializarRecursos();
        
        setContentView(R.layout.login_activity);
        
        mActionBar.setHomeLogo(R.drawable.header_logo);
        mActionBar.setTitle(R.string.login_activity_title);
        
        /*
        txtPassword.setOnKeyListener(new OnKeyListener() {			
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				// TODO Auto-generated method stub
				if(keyCode == KeyEvent.KEYCODE_ENTER){  
					btnLogin.performClick();
				}
				return false;
			}
		});*/
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

		String usuario = txtUsuario.getText().toString();
		String password = txtPassword.getText().toString();
		
		loginProxy.usuario = usuario;
		loginProxy.password = password;

		loginProxy.execute();

	}
	
    @Override
	protected void processOk() {
		
		boolean isExito = loginProxy.isExito();

		if (isExito) {
			int status = loginProxy.getResponse().getStatus();
			
			String message;
			if (status == 0) {
				UsuarioTO usuarioTO = loginProxy.getResponse().usuario;
				usuarioTO.codigoSap=txtPassword.getText().toString();
				
				txtUsuario.setText("");
				txtPassword.setText("");
				
				message = String.format(login_ok,usuarioTO.nombres);
				
				
				//*******************
				//TEMPORAL
				usuarioTO.rolId="9";
				usuarioTO.codigoSap="00351";
				usuarioTO.codigoDeposito="C4";
				//*******************
				
				
				if(usuarioTO.rolId.compareToIgnoreCase("7")==0){ //GERENTE
					Intent intent = new Intent(this, TableroActivity.class);
					intent.putExtra(TableroActivity.CODIGO_CDA_KEY, usuarioTO.codigoDeposito.trim());
			    	startActivity(intent);
				}else if(usuarioTO.rolId.compareToIgnoreCase("8")==0){//SUPERVISOR
					
					
					Intent intent = new Intent(this, ResumenVentaActivity.class);
					intent.putExtra(ResumenVentaActivity.CODIGO_SUPERVISOR_KEY, usuarioTO.codigoSap.trim());
					intent.putExtra(ResumenVentaActivity.CODIGO_DEPOSITO_KEY, usuarioTO.codigoDeposito.trim());
					intent.putExtra(ResumenVentaActivity.NOMBRE_CDA_KEY, usuarioTO.descripcionDeposito.trim());
			    	startActivity(intent);
					
			    	/*
			    	Intent intent = new Intent(this, ListaVendedoresActivity.class);
					intent.putExtra(ListaVendedoresActivity.CODIGO_SUPERVISOR_KEY, usuarioTO.codigoSap.trim());
					intent.putExtra(ListaVendedoresActivity.CODIGO_CDA_KEY, usuarioTO.codigoDeposito.trim());
					intent.putExtra(ListaVendedoresActivity.NOMBRE_CDA_KEY, usuarioTO.descripcionDeposito.trim());
			    	startActivity(intent);
					*/
			    	
				}else if(usuarioTO.rolId.compareToIgnoreCase("9")==0){//VENDEDOR
					
					/*
					Intent intent = new Intent(this, ListaPedidosActivity.class);
					intent.putExtra(ListaPedidosActivity.CODIGO_VENDEDOR_KEY, usuarioTO.codigoSap.trim());
					intent.putExtra(ListaPedidosActivity.CODIGO_CDA_KEY, usuarioTO.codigoDeposito.trim());
					intent.putExtra(ListaPedidosActivity.NOMBRE_VENDEDOR_KEY, usuarioTO.nombres.trim());
			    	startActivity(intent);*/
					
					Intent intent = new Intent(this, DetallePedidosActivity.class);
					intent.putExtra(DetallePedidosActivity.CODIGO_VENDEDOR_KEY, usuarioTO.codigoSap.trim());
					intent.putExtra(DetallePedidosActivity.CODIGO_CDA_KEY, usuarioTO.codigoDeposito.trim());
					intent.putExtra(DetallePedidosActivity.NOMBRE_VENDEDOR_KEY, usuarioTO.nombres.trim());
			    	startActivity(intent);
			    	
				}else{
					message = usuario_no_tiene_acceso;
				}
				
			}else{
				message = String.format(
						login_error,
						loginProxy.getResponse().getDescripcion());
				
				//txtUsuario.requestFocus();
			}
			
			showToast(message);
			super.processOk();
			
		}else{
			processError();
		}
	
	}
    
    public void btnLogin_click(View v){
    	processAsync();
    	
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