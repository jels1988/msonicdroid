package pe.lindley.preliquidacion;


import pe.lindley.preliquidacion.to.UsuarioTO;
import pe.lindley.preliquidacion.ws.proxy.LoginProxy;

import com.thira.examples.actionbar.widget.ActionBar;
import roboguice.inject.InjectResource;
import roboguice.inject.InjectView;

import com.google.inject.Inject;

import net.msonic.lib.ActivityBase;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.EditText;

public class LoginActivity extends ActivityBase {
	
	@Inject LoginProxy loginProxy;
	@InjectView(R.id.txtUsuario) EditText txtUsuario;
	@InjectView(R.id.txtDeposito) EditText txtDeposito;
	@InjectView(R.id.txtOrdenCarga) EditText txtOrdenCarga;
	@InjectResource(R.string.msg_usuario_empty) String txtlogin_empty;
	@InjectResource(R.string.msg_ordenCarga_empty) String txtOrdenCarga_empty;
	@InjectResource(R.string.msg_desposito_empty) String txtDeposito_empty;
	@InjectResource(R.string.login_activity_ok) String login_activity_ok;
	@InjectResource(R.string.login_activity_error) String login_activity_error;
	@InjectView(R.id.actionBar)  	ActionBar 	mActionBar;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	inicializarRecursos();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        
        mActionBar.setTitle(R.string.login_activity_sub_title);
        //mActionBar.setSubTitle(getString(R.string.login_activity_sub_title));
        mActionBar.setHomeLogo(R.drawable.header_logo);
    	
    		
        
    }

    
	@Override
	protected boolean executeAsyncPre() {
		// TODO Auto-generated method stub
		boolean tieneError=false;
		
		if( txtUsuario.getText().toString().trim().equalsIgnoreCase("")){
			txtUsuario.setError(txtlogin_empty);
			tieneError=true;
		}
		
		if( txtOrdenCarga.getText().toString().trim().equalsIgnoreCase("")){
			txtOrdenCarga.setError(txtOrdenCarga_empty);
			tieneError=true;
		}
		
		if( txtDeposito.getText().toString().trim().equalsIgnoreCase("")){
			txtDeposito.setError(txtDeposito_empty);
			tieneError=true;
		}
		
		
		return !tieneError;
	}

	
	@Override
	protected void process() {
		// TODO Auto-generated method stub
		String usuario = txtUsuario.getText().toString();
		String deposito = txtDeposito.getText().toString();
		String ordenCarga = txtOrdenCarga.getText().toString();
		
		loginProxy.setDeposito(deposito);
		loginProxy.setUsuario(usuario);
		loginProxy.setOrdenCarga(ordenCarga);
		loginProxy.execute();
	}

	@Override
	protected void processOk() {
		// TODO Auto-generated method stub
		
		
		
		Context ctx = this;

		boolean isExito = loginProxy.isExito();

		if (isExito) {
			int status = loginProxy.getResponse().getStatus();
			String deposito = txtDeposito.getText().toString();
			
			String message;
			
			if (status == 0) {
				UsuarioTO usuarioTO = loginProxy.getResponse().getUsuario();
				usuarioTO.setDeposito(deposito);
				
				RTMApplication application = (RTMApplication)contextProvider.get().getApplicationContext();
				application.setUsuarioTO(usuarioTO);
				
				message = String.format(login_activity_ok,usuarioTO.getDescripcion());
				
				SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(ctx);
				int FLAG_DESCARGA = settings.getInt("FLAG_DESCARGA",0);
				
				if(FLAG_DESCARGA==0){
					Intent i = new Intent(this,DescargarDocumentosActivity.class);
					startActivity(i);
				}else{
					Intent i = new Intent(this,MenuActivity.class);
					startActivity(i);
				}
				
			}else{
				
				message = String.format(
						login_activity_error,
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
			message = String.format(login_activity_error,error);
		}else{
			message = error_generico_process;
		}
		super.processError();
		showToast(message);
	}
    
    
	public void btnLogin_onlick(View view){
		
		
		
		processAsync();
		
		
		/*Intent i = new Intent(this, MenuActivity.class);
		startActivity(i);*/
	    
	}
	
	
	/*
	LocationListener locationListener = new LocationListener() {
        public void onLocationChanged(Location location) {
            if (location != null) {
                Log.i("SuperMap", "Location changed : Lat: " + location.getLatitude() + " Lng: " + location.getLongitude());
            }
        }

        public void onProviderDisabled(String provider) {
        }

        public void onProviderEnabled(String provider) {
        }

        public void onStatusChanged(String provider, int status, Bundle extras) {
        }
    };
    
	private void getGPS() {
		// Acquire a reference to the system Location Manager
		LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
		
		locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 10, 0,
                locationListener);
		Location location = locationManager.getLastKnownLocation(LocationManager.PASSIVE_PROVIDER);
		if (location != null) {
		double latitude = location.getLatitude();
		double longitude = location.getLongitude();
		}

		}

	*/
	
	

}