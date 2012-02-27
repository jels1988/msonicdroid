package pe.lindley.ficha.activity;

import com.google.inject.Inject;
import com.thira.examples.actionbar.widget.ActionBar;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;
import pe.lindley.activity.R;
import pe.lindley.activity.RTMApplication;
import pe.lindley.ficha.negocio.OpcionMultipleBLL;
import pe.lindley.ficha.to.OpcionMultipleTO;
import pe.lindley.ficha.ws.service.ActualizarComercialProxy;
import pe.lindley.lanzador.to.ClienteResumenTO;
import pe.lindley.lanzador.to.UsuarioTO;
import pe.lindley.util.ActivityBase;
import pe.lindley.util.MessageBox;
import roboguice.inject.InjectResource;
import roboguice.inject.InjectView;

public class ActualizarComercialActivity extends ActivityBase {
	
	@Inject ActualizarComercialProxy actualizarComercialProxy; 
	@InjectView(R.id.actionBar)     ActionBar   mActionBar;
	@InjectView(R.id.cboGiro) 		Spinner 	cboGiro;
	@InjectResource(R.string.ficha_actualizar_comercial_ok) String ficha_actualizar_comercial_ok;
	@InjectResource(R.string.ficha_descargarparametros_activity_confirm_dialog_si) 		String 	confirm_si;
	@InjectResource(R.string.ficha_descargarparametros_activity_confirm_dialog_no) 		String 	confirm_no;
	@InjectResource(R.string.ficha_guardar_giro_activity_confirm_dialog_message) 			String 	confirm_message;
	@InjectResource(R.string.ficha_guardar_giro_activity_title) 	String 	ficha_guardar_giro_title;
	@Inject OpcionMultipleBLL opcionMultipleBLL;
	public static final String LISTA_COMERCIAL = "02";
	public static final String CODIGO_CLIENTE = "codigo_cliente";
	public static final String CODIGO_GIRO = "codigo_giro";
	
	public String codigo_cliente = null;
	public String codigo_giro = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ficha_actualizar_giro_activity);
		final RTMApplication application = (RTMApplication)contextProvider.get().getApplicationContext();
		ClienteResumenTO cliente = application.getClienteTO();
		mActionBar.setSubTitle(cliente.getCodigoCliente()+"-"+cliente.getRazonSocial());
		mActionBar.setTitle(R.string.ficha_actualizar_comercial_activity_title);
		mActionBar.setHomeLogo(R.drawable.header_logo);
		Intent intent = this.getIntent();
		codigo_cliente = intent.getStringExtra(CODIGO_CLIENTE);
		codigo_giro = intent.getStringExtra(CODIGO_GIRO);
		
		cboGiro.setAdapter(application.getAdapterParametrosFicha(LISTA_COMERCIAL));
		cboGiro.setSelection(application.getAdapterParametrosFicha(LISTA_COMERCIAL).findByValue(codigo_giro));
	}
	
	@Override
	protected void process() {
		// TODO Auto-generated method stub
		RTMApplication application = (RTMApplication)getApplicationContext();
		UsuarioTO usuario = application.getUsuarioTO();
		
		actualizarComercialProxy.setCliente(codigo_cliente);
		actualizarComercialProxy.setUsuario(usuario.getCodigoSap());
		actualizarComercialProxy.setGiroSecundario(((OpcionMultipleTO)cboGiro.getSelectedItem()).getCodigo());
		actualizarComercialProxy.execute();
		super.process();
	}

	@Override
	protected void processOk() {
		// TODO Auto-generated method stub
		boolean isExito = actualizarComercialProxy.isExito();
		if (isExito) {
			int status = actualizarComercialProxy.getResponse().getStatus();
			if (status == 0) {
				showToast(ficha_actualizar_comercial_ok);
			}
			else  {
				showToast(actualizarComercialProxy.getResponse().getDescripcion());
			}
		}
		else{
			processError();
		}
		super.processOk();
		finish();
	}

	@Override
	protected void processError() {
		// TODO Auto-generated method stub
		super.processError();
		showToast(error_generico_process);
	}

	public void btnActualizar_onclick(View view)
	{
		final Context context = this;
		
		MessageBox.showConfirmDialog(context, 
				ficha_guardar_giro_title, 
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

}
