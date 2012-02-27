package pe.lindley.plandesarrollo.activity;

import java.util.Calendar;
import com.google.inject.Inject;
import com.thira.examples.actionbar.widget.ActionBar;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import pe.lindley.activity.R;
import pe.lindley.activity.RTMApplication;
import pe.lindley.lanzador.to.ClienteResumenTO;
import pe.lindley.lanzador.to.UsuarioTO;
import pe.lindley.plandesarrollo.ws.service.ActualizarActividadProxy;
import pe.lindley.plandesarrollo.ws.service.GuardarActividadProxy;
import pe.lindley.util.ActivityBase;
import pe.lindley.util.MessageBox;
import roboguice.inject.InjectResource;
import roboguice.inject.InjectView;

public class DatosActividadActivity extends ActivityBase {

	@InjectView(R.id.actionBar)             ActionBar mActionBar;
	@InjectView(R.id.txtFechaTentativa)     EditText txtFechaTentativa;
	@InjectView(R.id.txtActividad)      	EditText txtActividad;
	@InjectView(R.id.chkTerminado)          CheckBox chkTerminado;
	@Inject GuardarActividadProxy 			guardarActividadProxy;
	@Inject ActualizarActividadProxy		actualizarActividadProxy;
	@InjectResource(R.string.plandesarrollo_actividad_guardado_ok) String actividad_guardado_ok;
	@InjectResource(R.string.plandesarrollo_actividad_actualizado_ok) String actividad_actualizado_ok;
	@InjectResource(R.string.plandesarrollo_actividad_txtFechaTentativa_empty) String txtFechaTentativa_empty;
	@InjectResource(R.string.plandesarrollo_actividad_txtActividad_empty) String txtActividad_empty;
	@InjectResource(R.string.plandesarrollo_guardar_actividad_activity_confirm_dialog_si) 		String 	confirm_si;
	@InjectResource(R.string.plandesarrollo_guardar_actividad_activity_confirm_dialog_no) 		String 	confirm_no;
	@InjectResource(R.string.plandesarrollo_guardar_actividad_activity_confirm_dialog_message) 			String 	confirm_message;
	@InjectResource(R.string.plandesarrollo_guardar_actividad_activity_title) 	String 	ficha_guardar_actividad_title;
	static final int DATE_DIALOG_ID = 0;
	private int mYear;
    private int mMonth;
    private int mDay;
    
    public static final String TIPO_ACCION = "tipo_accion";
	public static final String CODIGO_CLIENTE = "codigo";
	public static final String CODIGO_PLAN = "plan_codigo";
	public static final String CODIGO_ACTIVIDAD = "actividad_codigo";
	public static final String DESCRIPCION_ACTIVIDAD = "desc_actividad";
	public static final String FECHA_TENTATIVA = "fec_tentativa";
	public static final String CANT_SUSTENTO = "cant_sustento";
	protected static final String ESTADO = "estado_act";  
	
    public static final int ACCION_NUEVO = 1;   
    public static final int ACCION_ACTUALIZAR = 2;
	  
    
	private static int accion = 0;  
	private static String	codigo_cliente;
	private static String	codigo_plan;
	private static String	codigo_Actividad;
	private static int	cantidadSustento;	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		Intent intent = this.getIntent();
		accion = intent.getIntExtra(TIPO_ACCION, 0);
		codigo_cliente = intent.getStringExtra(CODIGO_CLIENTE);
		codigo_plan = intent.getStringExtra(CODIGO_PLAN);
		
		setContentView(R.layout.plandesarrollo_datos_actividad_activity);
		final RTMApplication application = (RTMApplication)contextProvider.get().getApplicationContext();
		ClienteResumenTO cliente = application.getClienteTO();
		mActionBar.setSubTitle(cliente.getCodigoCliente()+"-"+cliente.getRazonSocial());
		mActionBar.setTitle(R.string.pd_mostrar_actividad_activity_title);
		mActionBar.setHomeLogo(R.drawable.header_logo);
		
		if(accion == ACCION_ACTUALIZAR)
		{

			codigo_Actividad = intent.getStringExtra(CODIGO_ACTIVIDAD);
			txtActividad.setText(intent.getStringExtra(DESCRIPCION_ACTIVIDAD));	
			cantidadSustento = intent.getIntExtra(CANT_SUSTENTO, 0);
			
			System.out.println(cantidadSustento);
			
			String fecha_tentativa = intent.getStringExtra(FECHA_TENTATIVA);	
			if(fecha_tentativa.length() > 7)
			{
				mYear =  Integer.parseInt(fecha_tentativa.substring(0, 4));
				mMonth  =  Integer.parseInt(fecha_tentativa.substring(4, 6));
				mDay  =  Integer.parseInt(fecha_tentativa.substring(6));
				txtFechaTentativa.setText(mDay+"/"+mMonth+"/"+mYear);
				mMonth--;
			}	
			if(intent.getIntExtra(ESTADO,0)==2)
			{
				chkTerminado.setChecked(true);
			}
			
			chkTerminado.setOnCheckedChangeListener(
				    new CheckBox.OnCheckedChangeListener() {
				    	@Override
				        public void onCheckedChanged(CompoundButton buttonView,
				                                                  boolean isChecked) {
				            if (isChecked) {
				            	if(cantidadSustento==0)
				            	{
				            		showToast("Debe registrar por lo menos un sustento.");
				            		chkTerminado.setChecked(false);				            		
				            	}
				            }
				    }
				});
		}
		else
		{
			chkTerminado.setVisibility(View.GONE);
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
    	txtFechaTentativa.setText(
            new StringBuilder()
                    // Month is 0 based so add 1
            		.append(mDay).append("/")
                    .append(mMonth+1).append("/")
                    .append(mYear).append(""));
    }
    
	@Override
	protected void process() {
		// TODO Auto-generated method stub
		super.process();
	}

	@Override
	protected boolean executeAsyncPre(int accion) {
		// TODO Auto-generated method stub
		boolean tieneError = false;
		
		if (txtActividad.getText().toString().trim()
				.equalsIgnoreCase("")) {
			txtActividad.setError(txtActividad_empty);
			tieneError = true;
		}
		
		if (txtFechaTentativa.getText().toString().trim()
				.equalsIgnoreCase("")) {
			txtFechaTentativa.setError(txtFechaTentativa_empty);
			tieneError = true;
		}

		return !tieneError;
	}
	
	@Override
	protected void process(int accion) {
		// TODO Auto-generated method stub
		RTMApplication application = (RTMApplication)getApplicationContext();
		UsuarioTO usuario = application.getUsuarioTO();
		
		if(accion==ACCION_ACTUALIZAR){
			actualizarActividadProxy.setCodigoCliente(codigo_cliente);
			actualizarActividadProxy.setCodigoPLan(codigo_plan);
			actualizarActividadProxy.setCodigoActividad(codigo_Actividad);
			actualizarActividadProxy.setDescripcionActividad(txtActividad.getText().toString());
			
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
			
			if(chkTerminado.isChecked())
				actualizarActividadProxy.setEstado("2");
			else
				actualizarActividadProxy.setEstado("1");
			actualizarActividadProxy.setFechTentativa(mYear+""+sMonthFecha+""+sDayFecha);
			actualizarActividadProxy.setUsuario(usuario.getCodigoSap());
			actualizarActividadProxy.execute();
		}
		else if(accion == ACCION_NUEVO)
		{
			guardarActividadProxy.setCodigoCliente(codigo_cliente);
			guardarActividadProxy.setCodigoPLan(codigo_plan);
			guardarActividadProxy.setDescripcionActividad(txtActividad.getText().toString());
			
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
			
			guardarActividadProxy.setFechTentativa(mYear+""+sMonthFecha+""+sDayFecha);
			
			guardarActividadProxy.setUsuario(usuario.getCodigoSap());
			guardarActividadProxy.execute();
		}
		super.process(accion);
	}

	@Override
	protected void processOk(int accion) {
		// TODO Auto-generated method stub
		if(accion==ACCION_ACTUALIZAR){
		    boolean isExito = actualizarActividadProxy.isExito();
			if (isExito) {
				int status = actualizarActividadProxy.getResponse().getStatus();
				if (status == 0) {
					showToast(actividad_actualizado_ok);
				}
				else  {
					showToast(actualizarActividadProxy.getResponse().getDescripcion());
				}
			}
			else{
				processError(accion);
			}
		}
		else if(accion == ACCION_NUEVO){
			boolean isExito = guardarActividadProxy.isExito();
			if (isExito) {
				int status = guardarActividadProxy.getResponse().getStatus();
				if (status == 0) {
					showToast(actividad_guardado_ok);
				}
				else  {
					showToast(guardarActividadProxy.getResponse().getDescripcion());
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

	@Override
	protected void processOk() {
		// TODO Auto-generated method stub
		super.processOk();
	}

	@Override
	protected void processError() {
		// TODO Auto-generated method stub
		super.processError();
	}
	
	public void btnGuardar_onclick(View view)
	{
		final Context context = this;
	
		MessageBox.showConfirmDialog(context, 
			ficha_guardar_actividad_title, 
			confirm_message, 
			confirm_si,
			new OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					processAsync(accion);
				}
			},
			confirm_no,
			null);		
	}
}

