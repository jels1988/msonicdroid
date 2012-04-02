package pe.lindley.prospector.activity;

import java.util.ArrayList;

import com.google.inject.Inject;
import com.thira.examples.actionbar.widget.ActionBar;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import pe.lindley.activity.R;
import pe.lindley.prospector.negocio.ClienteBLL;
import pe.lindley.prospector.to.ClienteTO;
import pe.lindley.prospector.ws.service.GuardarClienteProxy;
import pe.lindley.util.ActivityBase;
import pe.lindley.util.MessageBox;
import roboguice.inject.InjectResource;
import roboguice.inject.InjectView;

public class EnviarClientesActivity extends ActivityBase {
	
	
	private static int UPLOAD_DOCUMENTOS=0;
	
	@InjectView(R.id.actionBar)  	ActionBar 			mActionBar;
	@Inject 						ClienteBLL 			clienteBLL;
	@Inject 						GuardarClienteProxy guardarClienteProxy;
	//@Inject 						UploadFileProxy uploadFileProxy;
	
	@InjectResource(R.string.sincronizar_clientes_activity_title) 					String 	sincronizar_cliente_title;
	@InjectResource(R.string.sincronizar_clientes_activity_confirm_dialog_no) 		String 	confirm_no;
	@InjectResource(R.string.sincronizar_clientes_activity_confirm_dialog_si) 		String 	confirm_si;
	@InjectResource(R.string.sincronizar_clientes_activity_confirm_dialog_ok) 		String 	confirm_ok;
	@InjectResource(R.string.sincronizar_clientes_activity_confirm_dialog_message) 			String 	confirm_message;
	@InjectResource(R.string.sincronizar_clientes_activity_confirm_dialog_message_ok) 		String 	confirm_message_ok;
	@InjectResource(R.string.sincronizar_clientes_activity_confirm_dialog_message_error) 	String 	confirm_message_error;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.enviarclientes_activity);
        
        mActionBar.setTitle(R.string.sincronizar_clientes_activity_title);
        mActionBar.setHomeLogo(R.drawable.header_logo); 
        
        
	}

	public void btnAceptar_onclick(View view){
		
		final Context context = this;
		
		MessageBox.showConfirmDialog(context, 
				sincronizar_cliente_title, 
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
	
	public void btnCancelar_onclick(View view){
		finish();
	}
	
	
	@Override
	protected void process() {
		// TODO Auto-generated method stub
		ArrayList<ClienteTO> clientes = clienteBLL.list();
		Log.d("SINCRONIZAR_CLIENTES_TOTAL", String.format("%s", clientes.size()));
        guardarClienteProxy.setClientes(clientes);
        guardarClienteProxy.execute();
        
	}

	
	@Override
	protected void process(int accion) {
		// TODO Auto-generated method stub
		
		
		if(UPLOAD_DOCUMENTOS==accion){
			
			/*
			boolean isExito = guardarClienteProxy.isExito();
			if (isExito) {
				int status = guardarClienteProxy.getResponse().getStatus();
				
				if (status == 0) {
					
				   if((guardarClienteProxy.getResponse()!=null) && (guardarClienteProxy.getResponse().getIdGenerados())!=null){
			        	clienteBLL.updateIdGenerados(guardarClienteProxy.getResponse().getIdGenerados());
			        }
				   
					clienteBLL.deleteAll();
					
					File path = new File( Environment.getExternalStorageDirectory(), this.getPackageName() );
					ArrayList<DocumentoTO> documentos = clienteBLL.listarDocumentos();
					String fileName;
			        for (DocumentoTO documentoTO : documentos) {
			        	
			        	fileName = documentoTO.getNombreArchivo();
			        	File f = new File(path, fileName);
			        	uploadFileProxy.setFilePath(f.getAbsolutePath());
			        	uploadFileProxy.setFileName(fileName);
			        	uploadFileProxy.setServidorId(documentoTO.getServidorId());
			        	uploadFileProxy.setTipoDocumentoId(documentoTO.getDocumentoId());
			        	uploadFileProxy.execute();
			        	
			        	if((uploadFileProxy.getResponse()!=null) && (uploadFileProxy.getResponse().getStatus()==0)){
			        		clienteBLL.deleteAllDocumentos(documentoTO.getId());
			        		f.delete();
			        	}
					}
			        
				}else{
					super.processOk();
					MessageBox.showSimpleDialog(this, sincronizar_cliente_title, confirm_message_error, confirm_ok, null);
				}
			}
			*/
			
			
		}
	}
	
	@Override
	protected void processOk() {
		// TODO Auto-generated method stub
		super.processOk();
		
		//processAsync(UPLOAD_DOCUMENTOS);
		
		
		boolean isExito = guardarClienteProxy.isExito();
		if (isExito) {
			int status = guardarClienteProxy.getResponse().getStatus();
			
			if (status == 0) {
				

				clienteBLL.deleteAll();
				
				final Context context = this;
				MessageBox.showSimpleDialog(context, sincronizar_cliente_title, confirm_message_ok, confirm_ok, new OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						finish();
					}
				});
		        
			}else{
				super.processOk();
				MessageBox.showSimpleDialog(this, sincronizar_cliente_title, confirm_message_error, confirm_ok, null);
			}
		}
				
	}
	

	@Override
	protected void processError(int accion) {
		// TODO Auto-generated method stub
		super.processError(accion);
	}

	@Override
	protected void processError() {
		// TODO Auto-generated method stub
		super.processError();
	}
	
	
}
