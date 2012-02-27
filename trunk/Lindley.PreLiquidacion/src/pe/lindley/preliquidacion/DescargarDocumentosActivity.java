package pe.lindley.preliquidacion;


import java.util.ArrayList;

import pe.lindley.preliquidacion.negocio.DocumentoBLL;
import pe.lindley.preliquidacion.negocio.MotivoBLL;
import pe.lindley.preliquidacion.to.DocumentoTO;
import pe.lindley.preliquidacion.to.MotivoTO;
import pe.lindley.preliquidacion.to.UsuarioTO;
import pe.lindley.preliquidacion.ws.proxy.ListarDocumentoProxy;
import roboguice.inject.InjectResource;
import roboguice.inject.InjectView;

import com.google.inject.Inject;
import com.thira.examples.actionbar.widget.ActionBar;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import net.msonic.lib.ActivityBase;

public class DescargarDocumentosActivity extends ActivityBase {
	private static final String TAG = DescargarDocumentosActivity.class.getName();
	
	@InjectResource(R.string.descargardocumento_activity_ok) String messageOK;
	@InjectResource(R.string.descargardocumento_activity_error) String messageError;
	
	@Inject DocumentoBLL documentoBLL;
	@Inject MotivoBLL motivoBLL;
	
	@Inject ListarDocumentoProxy listarDocumentoProxy;
	UsuarioTO usuarioTO;
	
	@InjectView(R.id.actionBar)  	ActionBar 	mActionBar;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.descargardocumentos_activity);
		mActionBar.setTitle(R.string.login_activity_sub_title);
        mActionBar.setHomeLogo(R.drawable.header_logo);
    	
		
		
		inicializarRecursos();
		
		
		RTMApplication application = (RTMApplication)contextProvider.get().getApplicationContext();
		usuarioTO = application.getUsuarioTO();
		
		
		processAsync();
		
		
	}
	
	@Override
	protected void process() {
		listarDocumentoProxy.setDesposito(usuarioTO.getDeposito());
		listarDocumentoProxy.setNumeroCarga(usuarioTO.getOrdenCarga());
		listarDocumentoProxy.execute();
	}
	
	@Override
	protected void processOk() {
		// TODO Auto-generated method stub
		Context ctx = this;
		
		boolean isExito = listarDocumentoProxy.isExito();
		String message;
		
		if (isExito) {
			int status = listarDocumentoProxy.getResponse().getStatus();
			if(status==0){
				ArrayList<DocumentoTO> documentos = listarDocumentoProxy.getResponse().getDocumentos();
				ArrayList<MotivoTO> motivos = listarDocumentoProxy.getResponse().getMotivos();
				
				String documentoEncontrados = "0";
				String motivosEncontrados = "0";
				
				if(documentos!=null){
					documentoBLL.descargarDocumentos(documentos);
					documentoEncontrados = String.valueOf(documentos.size());
					Log.d(TAG, "DOCUMENTOS ENCONTRADOS:" + documentoEncontrados);
					
				}else{
					Log.d(TAG, "DOCUMENTOS ES NULO.");
				}
				
				if(motivos!=null){
					motivoBLL.descargarMotivos(motivos);
					motivosEncontrados=String.valueOf(motivos.size());
					Log.d(TAG, "MOTIVOS ENCONTRADOS:" + motivosEncontrados);
					
				}else{
					Log.d(TAG, "MOTIVOS ES NULO.");
				}
				
				message = String.format(messageOK,documentoEncontrados,motivosEncontrados);
				Intent i = new Intent(this,MenuActivity.class);
				startActivity(i);
				
				SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(ctx);
				Editor editor = settings.edit();
				editor.putInt("FLAG_DESCARGA", 1);
				editor.commit();
				
				
			}else{
				message = String.format(
						messageError,
						listarDocumentoProxy.getResponse().getDescripcion());
			}
			super.processOk();
			showToast(message);
		}else{
			processError();
		}
	}

}
