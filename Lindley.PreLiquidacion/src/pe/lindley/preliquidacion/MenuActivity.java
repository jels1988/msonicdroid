package pe.lindley.preliquidacion;

import pe.lindley.preliquidacion.negocio.DocumentoBLL;
import pe.lindley.preliquidacion.to.UsuarioTO;
import pe.lindley.preliquidacion.ws.proxy.CerrarOrdenCargaProxy;
import roboguice.inject.InjectResource;
import roboguice.inject.InjectView;
import net.msonic.lib.*;
import com.google.inject.Inject;
import com.thira.examples.actionbar.widget.ActionBar;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.DialogInterface.OnClickListener;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import net.msonic.lib.ActivityBase;

public class MenuActivity extends ActivityBase {

	@InjectResource(R.string.cerrar_documento_title) 	String cerrar_documento_title;
	@InjectResource(R.string.cerrar_documento_message)	String cerrar_documento_message;
	@Inject DocumentoBLL documentoBLL;
	@Inject CerrarOrdenCargaProxy cerrarOrdenCargaProxy;
	UsuarioTO usuarioTO;
	private static final String TAG=MenuActivity.class.getName();
	
	@InjectView(R.id.actionBar)  	ActionBar 	mActionBar;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.menu_activity);
		
		mActionBar.setTitle(R.string.login_activity_sub_title);
        //mActionBar.setSubTitle(getString(R.string.login_activity_sub_title));
        mActionBar.setHomeLogo(R.drawable.header_logo);
    	
		RTMApplication application = (RTMApplication)contextProvider.get().getApplicationContext();
		inicializarRecursos();
		usuarioTO = application.getUsuarioTO();	
		
		
	}
	
	public static final int REQUEST_CODE = 0x0ba7c0de; // get it?
	public void btnScanner_onclik(View view){
		Intent intent = new Intent("com.google.zxing.client.android.SCAN");
	   //intent.putExtra("SCAN_WIDTH", 800);
	   //intent.putExtra("SCAN_HEIGHT", 200);
	    startActivityForResult(intent, REQUEST_CODE);
	}
    
	public void onActivityResult(int requestCode, int resultCode, Intent intent) {
		   
	      if (resultCode == RESULT_OK) {
	    	  
	         String codigoBarra = intent.getStringExtra("SCAN_RESULT");
	         String format = intent.getStringExtra("SCAN_RESULT_FORMAT");
	         
	         
	         String nroDocumento=codigoBarra.substring(5, 15);
	         
	         // Handle successful scan
	    	  Log.d(TAG, "CODIGO SCAN:'" + nroDocumento +  "'" );
	    	  Log.d(TAG, "CODIGO FORMAT:'" + format +  "'" );
	    	  
	         String codigoCliente = documentoBLL.consultarCodigoCliente(nroDocumento);
	         if(codigoCliente!=null){
	        	 Log.d(TAG, "CLIENTE ENCONTRADO" );
	        	  Intent i =new Intent(this,DocumentoActivity.class);
	        	  i.putExtra(DocumentoActivity.CODIGO_CLIENTE_KEY, codigoCliente);
		  		  startActivity(i);
	         }else{
	        	 Log.d(TAG, "CLIENTE NO ENCONTRADO" );
	         }
	         
	    	
	  		
	      } else if (resultCode == RESULT_CANCELED) {
	         // Handle cancel
	    	  Log.d(TAG, "SCAN CANCELADO" );
	      }
	   
	}
	

	public void btnBuscarCliente_onclick(View view){
		Intent i = new Intent(this,BuscarClienteActivity.class);
		startActivity(i);				
	}
	
	public void btnAvance_onclick(View view){
		Intent i = new Intent(this,ListarAvanceActivity.class);
		startActivity(i);				
	}
	
	public void btnCierre_onclick(View view){
		
		
		
		
		final Context context = this;
		
		MessageBox.showConfirmDialog(
				context, 
				"Confirmar", 
				"ÀSeguro de cerrar la orden de carga?", 
				"Si",
				new OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						processAsync();
					}
				},
				"No",
				null);
		
		
		
	}

	@Override
	protected void process() {
		// TODO Auto-generated method stub
		cerrarOrdenCargaProxy.setUsuario(usuarioTO.getUsuario());
		cerrarOrdenCargaProxy.setDeposito(usuarioTO.getDeposito());
		cerrarOrdenCargaProxy.setOrdenCarga(usuarioTO.getOrdenCarga());
		cerrarOrdenCargaProxy.execute();
	}

	@Override
	protected void processOk() {
		// TODO Auto-generated method stub
		boolean isExito = cerrarOrdenCargaProxy.isExito();
		
		if (isExito) {
			int status = cerrarOrdenCargaProxy.getResponse().getStatus();
			if (status == 0) {
				final Context context = this;
				
				SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(context);
				Editor editor = settings.edit();
				editor.putInt("FLAG_DESCARGA", 0);
				editor.commit();
				
				MessageBox.showSimpleDialog(context, cerrar_documento_title, cerrar_documento_message, "Aceptar", new OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						finish();
					}
				});	
			}
			else  {
				showToast(cerrarOrdenCargaProxy.getResponse().getDescripcion());
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
		showToast(error_generico_process);
		super.processError();
	}
}
