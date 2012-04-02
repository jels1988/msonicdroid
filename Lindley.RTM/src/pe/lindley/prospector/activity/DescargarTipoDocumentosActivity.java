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
import pe.lindley.prospector.negocio.ClienteBLL;
import pe.lindley.prospector.to.TipoDocumentoTO;
import pe.lindley.prospector.ws.service.TipoDocumentoProxy;
import pe.lindley.util.ActivityBase;
import pe.lindley.util.MessageBox;
import roboguice.inject.InjectResource;
import roboguice.inject.InjectView;

public class DescargarTipoDocumentosActivity extends ActivityBase {

	@Inject TipoDocumentoProxy tipoDocumentoProxy;
	@Inject ClienteBLL clienteBLL;
	
	@InjectView(R.id.actionBar)  	ActionBar 				mActionBar;
	
	@InjectResource(R.string.descargarclientes_activity_title) String 	descargar_cliente_title;
	@InjectResource(R.string.descargartipodocumentos_activity_confirm_dialog_message) 			String 	confirm_message;
	@InjectResource(R.string.descargartipodocumentos_activity_message_ok) 		String 	confirm_message_ok;
	@InjectResource(R.string.descargartipodocumentos_activity_message_error) 	String 	confirm_message_error;
	@InjectResource(R.string.fichasrechazadas_clientes_activity_confirm_dialog_si) 		String 	confirm_si;
	@InjectResource(R.string.fichasrechazadas_clientes_activity_confirm_dialog_ok) 		String 	confirm_ok;
	@InjectResource(R.string.fichasrechazadas_clientes_activity_confirm_dialog_no) 		String 	confirm_no;
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.descargartipodocumentos_activity);
        
        mActionBar.setTitle(R.string.descargartipodocumentos_activity_message_title);
        mActionBar.setHomeLogo(R.drawable.header_logo); 
        
	}
	
	
	public void btnAceptar_onclick(View v){
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
	
	public void btnCancelar_onclick(View v){
		finish();
	}
	@Override
	protected void process() {
		tipoDocumentoProxy.execute();
	}
	
	@Override
	protected void processOk() {
		
		boolean isExito = tipoDocumentoProxy.isExito();
		String message;
		
		if (isExito) {
			int status = tipoDocumentoProxy.getResponse().getStatus();
			if (status == 0) {
				message = confirm_message_ok;
				ArrayList<TipoDocumentoTO> documentos = tipoDocumentoProxy.getResponse().documentos;
				clienteBLL.insertTipoDocumento(documentos);
				finish();
			}else{
				message = tipoDocumentoProxy.getResponse().getDescripcion();
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
		if(tipoDocumentoProxy.getResponse()!=null){
			String error = tipoDocumentoProxy.getResponse().getDescripcion();
			message = error;
		}else{
			message = error_generico_process;
		}
		super.processError();
		showToast(message);
	}
}
