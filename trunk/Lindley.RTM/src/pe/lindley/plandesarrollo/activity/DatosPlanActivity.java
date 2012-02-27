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
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import pe.lindley.activity.R;
import pe.lindley.activity.RTMApplication;
import pe.lindley.lanzador.to.ClienteResumenTO;
import pe.lindley.lanzador.to.UsuarioTO;
import pe.lindley.plandesarrollo.ws.service.ActualizarPlanProxy;
import pe.lindley.plandesarrollo.ws.service.GuardarPlanProxy;
import pe.lindley.util.ActivityBase;
import pe.lindley.util.MessageBox;
import roboguice.inject.InjectResource;
import roboguice.inject.InjectView;

public class DatosPlanActivity extends ActivityBase {
	
	@Inject GuardarPlanProxy guardarPlanProxy;
	@Inject ActualizarPlanProxy actualizarPlanProxy; 
	@InjectView(R.id.actionBar)ActionBar mActionBar;
	@InjectView(R.id.txtFechaInicio) 	EditText txtFechaInicio;
	//@InjectView(R.id.txtFechaCreacion) 	EditText txtFechaCreacion;
	@InjectView(R.id.txtFechaFin) 	    EditText txtFechaFin;		
	
	@InjectView(R.id.txViewAvance) 	    TextView txViewAvance;
	//@InjectView(R.id.txViewFchCreacion) TextView txViewFchCreacion;
	@InjectView(R.id.btnFechafin) 	    ImageButton btnFechafin;
	//@InjectView(R.id.btnFcCreacion) 	ImageButton btnFcCreacion;	
	@InjectView(R.id.txViewFecFin) 	    TextView txViewFecFin;
	@InjectView(R.id.txtPlan) 	        EditText txtPlan;
	@InjectView(R.id.txtAvance) 	    EditText txtAvance;
	@InjectView(R.id.txtObjetivo) 	    EditText txtObjetivo;
	@InjectView(R.id.radioAbierto) 	    RadioButton radioAbierto;
	@InjectView(R.id.radioCerrado) 	    RadioButton radioCerrado;
	@InjectView(R.id.radioGroupEstado) 	RadioGroup radioGroupEstado;	
	
	@InjectResource(R.string.plandesarrollo_plan_guardado_ok) String plan_guardado_ok;
	@InjectResource(R.string.plandesarrollo_plan_actualizado_ok) String plan_actualizado_ok;
	@InjectResource(R.string.pd_txtplan_empty) String txtplan_empty;
	@InjectResource(R.string.pd_txtobjetivo_empty) String txtobjetivo_empty;

	@InjectResource(R.string.plandesarrollo_guardar_plan_activity_confirm_dialog_si) 		String 	confirm_si;
	@InjectResource(R.string.plandesarrollo_guardar_plan_activity_confirm_dialog_no) 		String 	confirm_no;
	@InjectResource(R.string.plandesarrollo_guardar_plan_activity_confirm_dialog_message) 			String 	confirm_message;
	@InjectResource(R.string.plandesarrollo_guardar_plan_activity_title) 	String 	ficha_guardar_plan_title;
	
	public static final String TIPO_ACCION = "tipo_accion";
	public static final String CODIGO_CLIENTE = "codigo";
	
	public static final String CODIGO_PLAN = "codigo_plan";
	public static final String DESCRIPCION_PLAN = "descripcion_plan";
	public static final String DESCRIPCION_OBJETIVO = "descripcion_objetivo";	
	public static final String CODIGO_ESTADO = "codigo_estado";
	public static final String PORCENTAJE_AVANCE = "porcentaje_avance";
	public static final String DATA_FECHA_CREACION = "fecha_creacion";
	public static final String DATA_FECHA_FIN = "fecha_fin";
	public static final String DATA_FECHA_INICIO = "fecha_inicio";
	
	
    public static final int ACCION_NUEVO = 1;   
    public static final int ACCION_ACTUALIZAR = 2;
    public static final int ESTADO_NUEVO = 1; 	
	private int SET_FECHA = 0;
	static final int DATE_DIALOG_ID = 0;
	static final int FECHA_CREACION = 1;
	static final int FECHA_INICIO = 2;
	static final int FECHA_FIN = 3;
	private static int accion = 0;    
	private static String codigo_cliente = null;
	private static String codigoPlan = null;
	//private static String fechaCreacion = "";
	private static String fechaFin = "";
	private static String fechaInicio = "";
	private int mYear,mYearFechaCreacion,mYearFechaInicio,mYearFechaFin;
    private int mMonth,mMonthFechaCreacion,mMonthFechaInicio,mMonthFechaFin;
    private int mDay,mDayFechaCreacion,mDayFechaInicio,mDayFechaFin;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		Intent intent = this.getIntent();
		accion = intent.getIntExtra(TIPO_ACCION, 0);
		codigo_cliente = intent.getStringExtra(CODIGO_CLIENTE);
		setContentView(R.layout.plandesarrollo_datos_plan_activity);
		final RTMApplication application = (RTMApplication)contextProvider.get().getApplicationContext();
		ClienteResumenTO cliente = application.getClienteTO();
		mActionBar.setSubTitle(cliente.getCodigoCliente()+"-"+cliente.getRazonSocial());
		mActionBar.setTitle(R.string.pd_mostrar_principal_activity_title);
		mActionBar.setHomeLogo(R.drawable.header_logo);
		
		if(accion == ACCION_ACTUALIZAR)
		{			
			final Calendar c = Calendar.getInstance();
			mYearFechaFin=mYearFechaInicio=mYearFechaCreacion=mYear = c.get(Calendar.YEAR);
			mMonthFechaCreacion=mMonthFechaInicio=mMonthFechaFin=mMonth = c.get(Calendar.MONTH);
			mDayFechaCreacion=mDayFechaInicio=mDayFechaFin=mDay = c.get(Calendar.DAY_OF_MONTH);
			codigoPlan = intent.getStringExtra(CODIGO_PLAN);
			txtAvance.setText(intent.getStringExtra(PORCENTAJE_AVANCE));
			txtPlan.setText(intent.getStringExtra(DESCRIPCION_PLAN));
			txtObjetivo.setText(intent.getStringExtra(DESCRIPCION_OBJETIVO));
			if(intent.getStringExtra(CODIGO_ESTADO).equals("1"))
				radioAbierto.setChecked(true);
			else
				radioCerrado.setChecked(false);
			
			/*fechaCreacion = intent.getStringExtra(DATA_FECHA_CREACION);
			if(fechaCreacion.length() > 7)
			{
				mYearFechaCreacion =  Integer.parseInt(fechaCreacion.substring(0, 4));
				mMonthFechaCreacion  =  Integer.parseInt(fechaCreacion.substring(4, 6));
				mDayFechaCreacion  =  Integer.parseInt(fechaCreacion.substring(6));
				txtFechaCreacion.setText(mDayFechaCreacion+"/"+mMonthFechaCreacion+"/"+mYearFechaCreacion);
			}	*/		
			fechaFin = intent.getStringExtra(DATA_FECHA_FIN);
			if(fechaFin.length() > 7)
			{
				mYearFechaFin =  Integer.parseInt(fechaFin.substring(0, 4));
				mMonthFechaFin  =  Integer.parseInt(fechaFin.substring(4, 6));
				mDayFechaFin  =  Integer.parseInt(fechaFin.substring(6));
				txtFechaFin.setText(mDayFechaFin+"/"+mMonthFechaFin+"/"+mYearFechaFin);
			}
			fechaInicio = intent.getStringExtra(DATA_FECHA_INICIO);
			if(fechaInicio.length() > 7)
			{
				mYearFechaInicio =  Integer.parseInt(fechaInicio.substring(0, 4));
				mMonthFechaInicio  =  Integer.parseInt(fechaInicio.substring(4, 6));
				mDayFechaInicio  =  Integer.parseInt(fechaInicio.substring(6));
				txtFechaInicio.setText(mDayFechaInicio+"/"+mMonthFechaInicio+"/"+mYearFechaInicio);
			}
		}
		else
		{
			radioGroupEstado.setVisibility(View.GONE);
			txViewAvance.setVisibility(View.GONE);
			txtAvance.setVisibility(View.GONE);
			txtFechaFin.setVisibility(View.GONE);
			txViewFecFin.setVisibility(View.GONE);
			btnFechafin.setVisibility(View.GONE);
			final Calendar c = Calendar.getInstance();
	        mYear = c.get(Calendar.YEAR);
	        mMonth = c.get(Calendar.MONTH);
	        mDay = c.get(Calendar.DAY_OF_MONTH);
	        
	        updateDisplay();
		}		
		/*txViewFchCreacion.setVisibility(View.GONE);
		txtFechaCreacion.setVisibility(View.GONE);
		btnFcCreacion.setVisibility(View.GONE);*/
		//btnFechafin.setVisibility(View.GONE);
	}
	
	public void btnFecCreacion_onclick(View view){
		SET_FECHA = FECHA_CREACION;
		showDialog(DATE_DIALOG_ID);
	}
	
	public void btnFecInicio_onclick(View view){
		SET_FECHA = FECHA_INICIO;
		showDialog(DATE_DIALOG_ID);
	}
	
	public void btnFecFin_onclick(View view){
		SET_FECHA = FECHA_FIN;
		showDialog(DATE_DIALOG_ID);
	}

	protected Dialog onCreateDialog(int id) {
	    switch (id) {
	    case DATE_DIALOG_ID:
	        return new DatePickerDialog(this,mDateSetListener,mYear, mMonth, mDay);
	    }
	    return null;
	}
	
	private void updateDisplay() {
		StringBuilder fechaActual = new StringBuilder()
				// Month is 0 based so add 1
				.append(mDay).append("/").append(mMonth + 1).append("/")
				.append(mYear).append("");
		
		switch (SET_FECHA) {
		/*case FECHA_CREACION:	
			mYearFechaCreacion = mYear;
			mMonthFechaCreacion = mMonth;
			mDayFechaCreacion = mDay;
			mMonthFechaCreacion++;
			fechaActual = new StringBuilder()
			.append(mDayFechaCreacion).append("/").append(mMonthFechaCreacion).append("/")
			.append(mYearFechaCreacion).append("");
			txtFechaCreacion.setText(fechaActual);
			break;
*/
		case FECHA_INICIO:
			mYearFechaInicio = mYear;
			mMonthFechaInicio = mMonth;
			mDayFechaInicio = mDay;
			mMonthFechaInicio++;
			fechaActual = new StringBuilder()
			.append(mDayFechaInicio).append("/").append(mMonthFechaInicio).append("/")
			.append(mYearFechaInicio).append("");
			txtFechaInicio.setText(fechaActual);
			break;

		case FECHA_FIN:	
			mYearFechaFin = mYear;
			mMonthFechaFin = mMonth;
			mDayFechaFin = mDay;
			mMonthFechaFin++;
			fechaActual = new StringBuilder()
			.append(mDayFechaFin).append("/").append(mMonthFechaFin).append("/")
			.append(mYearFechaFin).append("");
			txtFechaFin.setText(fechaActual);
			break;

		default:
			mYearFechaInicio = mYear;
			mMonthFechaInicio = mMonth+1;
			mDayFechaInicio = mDay;
			mYearFechaCreacion = mYear;
			mMonthFechaCreacion = mMonth+1;
			mDayFechaCreacion = mDay;
			//txtFechaCreacion.setText(fechaActual);
			txtFechaInicio.setText(fechaActual);
			break;
		}
	}
	 
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
	    
	public void btnGuardar_onclick(View view)
	{
		final Context context = this;
	
		MessageBox.showConfirmDialog(context, 
			ficha_guardar_plan_title, 
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
	            
	@Override
	protected void process() {
		// TODO Auto-generated method stub
		super.process();
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
		showToast(error_generico_process);
	}
	
	@Override
	protected boolean executeAsyncPre(int accion) {
		// TODO Auto-generated method stub
		boolean tieneError = false;
		
		if (txtPlan.getText().toString().trim()
				.equalsIgnoreCase("")) {
			txtPlan.setError(txtplan_empty);
			tieneError = true;
		}
		
		if (txtObjetivo.getText().toString().trim()
				.equalsIgnoreCase("")) {
			txtObjetivo.setError(txtobjetivo_empty);
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
			actualizarPlanProxy.setCodigoCliente(codigo_cliente);
			actualizarPlanProxy.setCodigoPLan(codigoPlan);
			actualizarPlanProxy.setPorcentajeAvance(txtAvance.getText().toString());			
			actualizarPlanProxy.setDescripcionPLan(txtPlan.getText().toString());
			actualizarPlanProxy.setDescripcionObjetivo(txtObjetivo.getText().toString());
			actualizarPlanProxy.setUsuario(usuario.getCodigoSap());
			String sMonthFecha;
			if(mMonthFechaCreacion<10)
				sMonthFecha="0"+mMonthFechaCreacion;
			else
				sMonthFecha=mMonthFechaCreacion+"";
			
			String sDayFecha;
			if(mDayFechaCreacion<10)
				sDayFecha="0"+mDayFechaCreacion;
			else
				sDayFecha=mDayFechaCreacion+"";				
			actualizarPlanProxy.setFechaCreacion(mYearFechaCreacion+""+sMonthFecha+""+sDayFecha);	
			
			if(mMonthFechaInicio<10)
				sMonthFecha="0"+mMonthFechaInicio;
			else
				sMonthFecha=mMonthFechaInicio+"";
			
			if(mDayFechaInicio<10)
				sDayFecha="0"+mDayFechaInicio;
			else
				sDayFecha=mDayFechaInicio+"";	
			
			actualizarPlanProxy.setFechaInicio(mYearFechaInicio+""+sMonthFecha+""+sDayFecha);
			
			if(mMonthFechaFin<10)
				sMonthFecha="0"+mMonthFechaFin;
			else
				sMonthFecha=mMonthFechaFin+"";
			
			if(mDayFechaFin<10)
				sDayFecha="0"+mDayFechaFin;
			else
				sDayFecha=mDayFechaFin+"";	
			
			actualizarPlanProxy.setFechaFin(mYearFechaFin+""+sMonthFecha+""+sDayFecha);
			String estado = "";
			if(radioCerrado.isChecked())
				estado = "2";
			else if(radioAbierto.isChecked())
				estado = "1";
			actualizarPlanProxy.setEstado(estado);
			actualizarPlanProxy.execute();
		}
		else if(accion == ACCION_NUEVO)
		{
			guardarPlanProxy.setCodigoCliente(codigo_cliente);			
			guardarPlanProxy.setDescripcionPLan(txtPlan.getText().toString());
			guardarPlanProxy.setDescripcionObjetivo(txtObjetivo.getText().toString());			
			String sMonthFecha;
			if(mMonthFechaCreacion<10)
				sMonthFecha="0"+mMonthFechaCreacion;
			else
				sMonthFecha=mMonthFechaCreacion+"";
			
			String sDayFecha;
			if(mDayFechaCreacion<10)
				sDayFecha="0"+mDayFechaCreacion;
			else
				sDayFecha=mDayFechaCreacion+"";		
			
			guardarPlanProxy.setFechaCreacion(mYearFechaCreacion+""+sMonthFecha+""+sDayFecha);
			
			if(mMonthFechaInicio<10)
				sMonthFecha="0"+mMonthFechaInicio;
			else
				sMonthFecha=mMonthFechaInicio+"";
			
			if(mDayFechaInicio<10)
				sDayFecha="0"+mDayFechaInicio;
			else
				sDayFecha=mDayFechaInicio+"";	
			guardarPlanProxy.setFechaInicio(mYearFechaInicio+""+sMonthFecha+""+sDayFecha);			
			guardarPlanProxy.setUsuario(usuario.getCodigoSap());
			guardarPlanProxy.execute();
		}
	}

	@Override
	protected void processOk(int accion) {
		// TODO Auto-generated method stub
		if(accion==ACCION_ACTUALIZAR){
		    boolean isExito = actualizarPlanProxy.isExito();
			if (isExito) {
				int status = actualizarPlanProxy.getResponse().getStatus();
				if (status == 0) {
					showToast(plan_actualizado_ok);
				}
				else  {
					showToast(actualizarPlanProxy.getResponse().getDescripcion());
				}
			}
			else{
				processError(accion);
			}
		}
		else if(accion == ACCION_NUEVO){
			boolean isExito = guardarPlanProxy.isExito();
			if (isExito) {
				int status = guardarPlanProxy.getResponse().getStatus();
				if (status == 0) {
					showToast(plan_guardado_ok);
				}
				else  {
					showToast(guardarPlanProxy.getResponse().getDescripcion());
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
