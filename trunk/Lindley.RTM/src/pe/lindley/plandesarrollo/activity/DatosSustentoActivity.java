package pe.lindley.plandesarrollo.activity;

import java.util.Calendar;

import pe.lindley.activity.R;
import pe.lindley.activity.RTMApplication;
import pe.lindley.lanzador.to.ClienteResumenTO;
import pe.lindley.lanzador.to.UsuarioTO;
import pe.lindley.plandesarrollo.to.ParametroTO;
import pe.lindley.plandesarrollo.ws.service.ActualizarSustentoProxy;
import pe.lindley.plandesarrollo.ws.service.GuardarSustentoProxy;
import pe.lindley.util.ActivityBase;
import pe.lindley.util.MessageBox;
import roboguice.inject.InjectResource;
import roboguice.inject.InjectView;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.inject.Inject;

import com.thira.examples.actionbar.widget.ActionBar;

public class DatosSustentoActivity extends ActivityBase {

	@InjectView(R.id.actionBar)		    ActionBar   mActionBar;	
	@InjectView(R.id.cboTipoActividad)  Spinner 	cboTipoActividad;
	@InjectView(R.id.txtActividad)      EditText 	txtActividad;	
	@InjectView(R.id.txtFechaVisita)    EditText    txtFechaVisita;
	
	@Inject GuardarSustentoProxy 	guardarSustentoProxy;
	@Inject ActualizarSustentoProxy actualizarSustentoProxy;
	@InjectResource(R.string.plandesarrollo_sustento_guardado_ok)	String sustento_guardado_ok;
	@InjectResource(R.string.plandesarrollo_sustento_actualizado_ok) String sustento_actualizado_ok;
	
	@InjectResource(R.string.plandesarrollo_guardar_sustento_activity_confirm_dialog_si) 		String 	confirm_si;
	@InjectResource(R.string.plandesarrollo_guardar_sustento_activity_confirm_dialog_no) 		String 	confirm_no;
	@InjectResource(R.string.plandesarrollo_guardar_sustento_activity_confirm_dialog_message) 			String 	confirm_message;
	@InjectResource(R.string.plandesarrollo_guardar_sustento_activity_title) 	String 	ficha_guardar_motivo_title;
	
	static final int DATE_DIALOG_ID = 0;
	private int mYear;
    private int mMonth;
    private int mDay;
    
	public static final int ACCION_NUEVO = 1;
	public static final int ACCION_ACTUALIZAR = 2;	
	
	public static final String LISTA_RESPONSABLE =  "01";
	public static final String LISTA_ACTIVIDAD =  "02";	
	
	public static final String TIPO_ACCION =  "tipo_accion";	
	public static final String CODIGO_PLAN = "cod_plan";
	public static final String CODIGO_CLIENTE = "cod_cliente";
	public static final String CODIGO_ACTIVIDAD = "cod_actividad";
	
	protected static final String CODIGO_SUSTENTO = "cod_sustento";	
	protected static final String DESCRIPCION_SUSTENTO = "desc_sustento";
	public static final String TIPO_SUSTENTO =  "tipo_sustento";
	public static final String FECHA_VISITA =  "fec_visita";
	
	private static int tipo_accion = 0;
	private static String codigo_cliente = null;
	private static String codigo_plan = null;
	private static String codigo_actividad = null;
	private static String codigo_sustento = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	    setContentView(R.layout.plandesarrollo_datos_sustento_activity);
	    Intent intent = this.getIntent();
		codigo_cliente = intent.getStringExtra(CODIGO_CLIENTE);
		codigo_plan = intent.getStringExtra(CODIGO_PLAN);
		codigo_actividad = intent.getStringExtra(CODIGO_ACTIVIDAD);
		tipo_accion = intent.getIntExtra(TIPO_ACCION, 0);
		final RTMApplication application = (RTMApplication)contextProvider.get().getApplicationContext();
		ClienteResumenTO cliente = application.getClienteTO();
		mActionBar.setSubTitle(cliente.getCodigoCliente()+"-"+cliente.getRazonSocial());
	    mActionBar.setTitle(R.string.pd_mostrar_sustento_activity_title);
		mActionBar.setHomeLogo(R.drawable.header_logo);
		
	    cboTipoActividad.setAdapter(application.getAdapterParametrosPlanDesarrollo(LISTA_ACTIVIDAD));
	    if(tipo_accion == ACCION_ACTUALIZAR)
		{
			codigo_sustento = intent.getStringExtra(CODIGO_SUSTENTO);
	    	txtActividad.setText(intent.getStringExtra(DESCRIPCION_SUSTENTO));
	    	cboTipoActividad.setSelection(application.getAdapterParametrosPlanDesarrollo(LISTA_ACTIVIDAD).findByValue(intent.getStringExtra(TIPO_SUSTENTO)));
	    	String fecha_visita = intent.getStringExtra(FECHA_VISITA);	
			if(fecha_visita.length() > 7)
			{
				mYear =  Integer.parseInt(fecha_visita.substring(0, 4));
				mMonth  =  Integer.parseInt(fecha_visita.substring(4, 6));
				mDay  =  Integer.parseInt(fecha_visita.substring(6));
				txtFechaVisita.setText(mDay+"/"+mMonth+"/"+mYear);
				mMonth--;
			}	
			else
			{
				txtFechaVisita.setText("0");
			}
		}
	    else
		{
			final Calendar c = Calendar.getInstance();
	        mYear = c.get(Calendar.YEAR);
	        mMonth = c.get(Calendar.MONTH);
	        mDay = c.get(Calendar.DAY_OF_MONTH);
	        
	        updateDisplay();
		}	
	}

	public void btncalendario_onclick(View view){
		showDialog(DATE_DIALOG_ID);
	}
	
	@Override
	protected Dialog onCreateDialog(int id) {
	    switch (id) {
	    case DATE_DIALOG_ID:
	        return new DatePickerDialog(this,mDateSetListener,mYear, mMonth, mDay);
	    }
	    return null;
	}
	
	 // the callback received when the user "sets" the date in the dialog
    private DatePickerDialog.OnDateSetListener mDateSetListener =
            new DatePickerDialog.OnDateSetListener() {
    			
				@Override
				public void onDateSet(DatePicker view, int year,int monthOfYear, int dayOfMonth) {
					// TODO Auto-generated method stub
					mYear = year;
                    mMonth = monthOfYear;
                    mDay = dayOfMonth;
                    updateDisplay();
				}
            };
            
    // updates the date in the TextView
    private void updateDisplay() {
    	txtFechaVisita.setText(
            new StringBuilder()
                    // Month is 0 based so add 1
            		.append(mDay).append("/")
                    .append(mMonth+1).append("/")
                    .append(mYear).append(""));
    }
        
	@Override
	protected void process(int accion) {
		// TODO Auto-generated method stub
		RTMApplication application = (RTMApplication)getApplicationContext();
		UsuarioTO usuario = application.getUsuarioTO();
		
		if(accion == ACCION_NUEVO)
		{			
			guardarSustentoProxy.setCodigoActvidad(codigo_actividad);
			guardarSustentoProxy.setCodigoCliente(codigo_cliente);
			guardarSustentoProxy.setCodigoPLan(codigo_plan);
			guardarSustentoProxy.setDescripcionActividad(txtActividad.getText().toString());			
			
			mMonth++;
			String sMonthFecha;
			if(mMonth<10)
				sMonthFecha="0"+mMonth;
			else
				sMonthFecha=mMonth+"";
			
			String sDayFecha;
			if(mDay<10)
				sDayFecha="0"+mDay;
			else
				sDayFecha=mDay+"";	
			
			guardarSustentoProxy.setFechaVisita(mYear+""+sMonthFecha+""+sDayFecha);
			guardarSustentoProxy.setTipoActividad(((ParametroTO)cboTipoActividad.getSelectedItem()).getCodigo());
			guardarSustentoProxy.setUsuario(usuario.getCodigoSap());
			guardarSustentoProxy.execute();
		}
		else if(accion == ACCION_ACTUALIZAR)
		{
			actualizarSustentoProxy.setCodigoActvidad(codigo_actividad);
			actualizarSustentoProxy.setCodigoCliente(codigo_cliente);
			actualizarSustentoProxy.setCodigoPLan(codigo_plan);
			actualizarSustentoProxy.setCodigoSustento(codigo_sustento);
			actualizarSustentoProxy.setDescripcionActividad(txtActividad.getText().toString());
			mMonth++;
			String sMonthFecha;
			if(mMonth<10)
				sMonthFecha="0"+mMonth;
			else
				sMonthFecha=mMonth+"";
			
			String sDayFecha;
			if(mDay<10)
				sDayFecha="0"+mDay;
			else
				sDayFecha=mDay+"";	
			
			actualizarSustentoProxy.setFechaVisita(mYear+""+sMonthFecha+""+sDayFecha);
			actualizarSustentoProxy.setTipoActividad(((ParametroTO)cboTipoActividad.getSelectedItem()).getCodigo());
			actualizarSustentoProxy.setUsuario(usuario.getCodigoSap());
			actualizarSustentoProxy.execute();
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
	protected void processOk(int accion) {
		// TODO Auto-generated method stub
		if(accion == ACCION_NUEVO)
		{
			boolean isExito = guardarSustentoProxy.isExito();
			if (isExito) {
				int status = guardarSustentoProxy.getResponse().getStatus();
				if (status == 0) {
					showToast(sustento_guardado_ok);
				}
				else  {
					showToast(guardarSustentoProxy.getResponse().getDescripcion());
				}
			}
			else{
				processError(accion);
			}
		}
		else if(accion == ACCION_ACTUALIZAR)
		{
			boolean isExito = actualizarSustentoProxy.isExito();
			if (isExito) {
				int status = actualizarSustentoProxy.getResponse().getStatus();
				if (status == 0) {
					showToast(sustento_actualizado_ok);
				}
				else  {
					showToast(actualizarSustentoProxy.getResponse().getDescripcion());
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
