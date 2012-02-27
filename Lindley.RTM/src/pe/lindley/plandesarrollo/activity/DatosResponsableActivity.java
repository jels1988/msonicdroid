package pe.lindley.plandesarrollo.activity;

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
import pe.lindley.lanzador.to.ClienteResumenTO;
import pe.lindley.lanzador.to.UsuarioTO;
import pe.lindley.plandesarrollo.to.ParametroTO;
import pe.lindley.plandesarrollo.ws.service.ActualizarResponsableProxy;
import pe.lindley.plandesarrollo.ws.service.GuardarResponsableProxy;
import pe.lindley.util.ActivityBase;
import pe.lindley.util.MessageBox;
import roboguice.inject.InjectResource;
import roboguice.inject.InjectView;

public class DatosResponsableActivity extends ActivityBase {

	@InjectView(R.id.actionBar)		  ActionBar mActionBar;
	@InjectView(R.id.cboResponsable)  Spinner 	cboResponsable;
	@Inject GuardarResponsableProxy		guardarResponsableProxy;
	@Inject ActualizarResponsableProxy	actualizarResponsableProxy;
	@InjectResource(R.string.plandesarrollo_responsable_guardado_ok) String responsable_guardado_ok;
	@InjectResource(R.string.plandesarrollo_responsable_actualizado_ok) String responsable_actualizado_ok;
	@InjectResource(R.string.plandesarrollo_guardar_responsable_activity_confirm_dialog_si) 		String 	confirm_si;
	@InjectResource(R.string.plandesarrollo_guardar_responsable_activity_confirm_dialog_no) 		String 	confirm_no;
	@InjectResource(R.string.plandesarrollo_guardar_responsable_activity_confirm_dialog_message) 			String 	confirm_message;
	@InjectResource(R.string.plandesarrollo_guardar_responsable_activity_title) 	String 	ficha_guardar_motivo_title;
	
	public static final int ACCION_NUEVO = 1;
	public static final int ACCION_ACTUALIZAR = 2;
	
	public static final String TIPO_ACCION =  "tipo_accion";
	
	public static final String LISTA_RESPONSABLE =  "01";
	public static final String LISTA_ACTIVIDAD =  "02";	
	
	public static final String CODIGO_PLAN = "cod_plan";
	public static final String CODIGO_CLIENTE = "cod_cliente";
	public static final String CODIGO_ACTIVIDAD = "cod_actividad";
	protected static final String CODIGO_RESPONSABLE = "cod_responsable";
	
	private static int tipo_accion = 0;
	private static String codigo_cliente = null;
	private static String codigo_plan = null;
	private static String codigo_actividad = null;
	private static String codigo_responsable = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		Intent intent = this.getIntent();
		codigo_cliente = intent.getStringExtra(CODIGO_CLIENTE);
		codigo_plan = intent.getStringExtra(CODIGO_PLAN);
		codigo_actividad = intent.getStringExtra(CODIGO_ACTIVIDAD);
		tipo_accion = intent.getIntExtra(TIPO_ACCION, 0);
				
		setContentView(R.layout.plandesarrollo_datos_responsable_activity);
		final RTMApplication application = (RTMApplication)contextProvider.get().getApplicationContext();
		ClienteResumenTO cliente = application.getClienteTO();
		mActionBar.setSubTitle(cliente.getCodigoCliente()+"-"+cliente.getRazonSocial());
		mActionBar.setTitle(R.string.pd_mostrar_ctividad_activity_title);
		mActionBar.setHomeLogo(R.drawable.header_logo);
		
		cboResponsable.setAdapter(application.getAdapterParametrosPlanDesarrollo(LISTA_RESPONSABLE));
		
		 if(tipo_accion == ACCION_ACTUALIZAR)
		 {
			 codigo_responsable = intent.getStringExtra(CODIGO_RESPONSABLE);
			 cboResponsable.setSelection(application.getAdapterParametrosPlanDesarrollo(LISTA_RESPONSABLE).findByValue(codigo_responsable));
		 }
	}
	
	public void btnGuardar_onclick(View view)
	{
		final Context context = this;
	
		MessageBox.showConfirmDialog(context, 
			ficha_guardar_motivo_title, 
			confirm_message, 
			confirm_si,
			new OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					processAsync(tipo_accion);
				}
			},
			confirm_no,
			null);	
	}
	
	@Override
	protected void process(int accion) {
		// TODO Auto-generated method stub
		RTMApplication application = (RTMApplication)getApplicationContext();
		UsuarioTO usuario = application.getUsuarioTO();
		
		if(accion == ACCION_NUEVO)
		{
			guardarResponsableProxy.setCodigoActvidad(codigo_actividad);
			guardarResponsableProxy.setCodigoCliente(codigo_cliente);
			guardarResponsableProxy.setCodigoPLan(codigo_plan);
			guardarResponsableProxy.setCodigoResponsable(((ParametroTO)cboResponsable.getSelectedItem()).getCodigo());
			guardarResponsableProxy.setUsuario(usuario.getCodigoSap());
			guardarResponsableProxy.execute();
		}
		else if(accion == ACCION_ACTUALIZAR)
		{
			actualizarResponsableProxy.setCodigoActvidad(codigo_actividad);
			actualizarResponsableProxy.setCodigoCliente(codigo_cliente);
			actualizarResponsableProxy.setCodigoPLan(codigo_plan);
			actualizarResponsableProxy.setCodigoResponsableAntiguo(codigo_responsable);
			actualizarResponsableProxy.setCodigoResponsableNuevo(((ParametroTO)cboResponsable.getSelectedItem()).getCodigo());
			actualizarResponsableProxy.setUsuario(usuario.getCodigoSap());
			actualizarResponsableProxy.execute();
		}
		super.process(accion);
	}
	
	@Override
	protected void processOk(int accion) {
		// TODO Auto-generated method stub
		if(accion == ACCION_NUEVO)
		{
			boolean isExito = guardarResponsableProxy.isExito();
			if (isExito) {
				int status = guardarResponsableProxy.getResponse().getStatus();
				if (status == 0) {
					showToast(responsable_guardado_ok);
				}
				else  {
					showToast(guardarResponsableProxy.getResponse().getDescripcion());
				}
			}
			else{
				processError(accion);
			}
		}
		else if(accion == ACCION_ACTUALIZAR)
		{
			boolean isExito = actualizarResponsableProxy.isExito();
			if (isExito) {
				int status = actualizarResponsableProxy.getResponse().getStatus();
				if (status == 0) {
					showToast(responsable_actualizado_ok);
				}
				else  {
					showToast(actualizarResponsableProxy.getResponse().getDescripcion());
				}
			}
			else{
				processError(accion);
			}
		}
		super.processOk(accion);
		finish();
	}
	
	@Override
	protected void processError(int accion) {
		// TODO Auto-generated method stub
		super.processError(accion);
		showToast(error_generico_process);
	}

}
