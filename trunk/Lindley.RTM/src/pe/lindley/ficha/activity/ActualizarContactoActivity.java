package pe.lindley.ficha.activity;

import java.util.Calendar;
import java.util.regex.Pattern;

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
import pe.lindley.activity.R;
import pe.lindley.activity.RTMApplication;
import pe.lindley.ficha.negocio.OpcionMultipleBLL;
import pe.lindley.ficha.to.OpcionMultipleTO;
import pe.lindley.ficha.ws.service.ActualizarContactoProxy;
import pe.lindley.ficha.ws.service.GuardarContactoProxy;
import pe.lindley.lanzador.to.ClienteResumenTO;
import pe.lindley.lanzador.to.UsuarioTO;
import pe.lindley.util.ActivityBase;
import pe.lindley.util.MessageBox;
import roboguice.inject.InjectResource;
import roboguice.inject.InjectView;

public class ActualizarContactoActivity extends ActivityBase {

	@InjectView(R.id.actionBar)             ActionBar mActionBar;
	@InjectView(R.id.cboContacto) 			Spinner cboContacto;	
	@InjectView(R.id.txtNombre) 			EditText txtNombre;
	@InjectView(R.id.txtTelefono) 			EditText txtTelefono;
	@InjectView(R.id.txtEmail) 			    EditText txtEmail;	
	@InjectView(R.id.txtFechaAniversario) 	EditText txtFechaAniversario;
	@InjectResource(R.string.ficha_contacto_guardado) 	String contacto_guardado_ok;
	@InjectResource(R.string.ficha_contacto_actualizado) 	String contacto_actualizado_ok;
	@InjectResource(R.string.ficha_descargarparametros_activity_confirm_dialog_si) 		String 	confirm_si;
	@InjectResource(R.string.ficha_descargarparametros_activity_confirm_dialog_no) 		String 	confirm_no;
	@InjectResource(R.string.ficha_guardar_contacto_activity_confirm_dialog_message) 			String 	confirm_message;
	@InjectResource(R.string.ficha_guardar_contacto_activity_title) 	String 	ficha_guardar_contacto_title;
		
	@InjectResource(R.string.ficha_txtNombre_empty) String txtNombre_empty;
	@InjectResource(R.string.ficha_txtEmail_empty) String txtEmail_empty;
	@Inject ActualizarContactoProxy actualizarContactoProxy;
	@Inject GuardarContactoProxy guardarContactoProxy; 
	
	@Inject OpcionMultipleBLL opcionMultipleBLL;
	public static final String LISTA_CONTACTO = "01";
	static final int DATE_DIALOG_ID = 0;
	private int mYear;
    private int mMonth;
    private int mDay;
    public static final String CODIGO_CLIENTE = "codigo_cliente";
    public static final String CODIGO_CONTACTO = "codigo";
    public static final String NOMBRE_CONTACTO = "nombre";
    public static final String FECHA_NAC_CONTACTO = "fecha_nacimiento";
    public static final String TELEFONO_CONTACTO = "telefono";
    public static final String TIPO_CONTACTO = "tipo";
    public static final String EMAIL_CONTACTO = "email";
    
    public static final String TIPO_ACCION = "tipo_accion";    
    public static final int ACCION_NUEVO = 0;
    public static final int ACCION_ACTUALIZAR = 1;
    
    private static int accion = 0;    
	private static String codigo = null;
	private static String codigo_cliente = null;
	private static String fecha_nacimiento = null;
	
	public final Pattern EMAIL_ADDRESS_PATTERN = Pattern
			.compile("[a-zA-Z0-9+._%-+]{1,256}" + "@"
					+ "[a-zA-Z0-9][a-zA-Z0-9-]{0,64}" + "\\."
					+ "[a-zA-Z0-9][a-zA-Z0-9-]{0,25}" 

			);
	
	private boolean checkEmail(String email) {
		return EMAIL_ADDRESS_PATTERN.matcher(email).matches();
		}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ficha_datos_contacto_activity);
		final RTMApplication application = (RTMApplication)contextProvider.get().getApplicationContext();
		ClienteResumenTO cliente = application.getClienteTO();
		mActionBar.setSubTitle(cliente.getCodigoCliente()+"-"+cliente.getRazonSocial());
		mActionBar.setTitle(R.string.ficha_actualizar_contacto_activity_title);
		mActionBar.setHomeLogo(R.drawable.header_logo);

		
		cboContacto.setAdapter(application.getAdapterParametrosFicha(LISTA_CONTACTO));
		
		Intent intent = this.getIntent();
		accion = intent.getIntExtra(TIPO_ACCION, 0);
		codigo_cliente  = intent.getStringExtra(CODIGO_CLIENTE);
		if(accion == ACCION_ACTUALIZAR)
		{
			codigo = intent.getStringExtra(CODIGO_CONTACTO);
			txtNombre.setText(intent.getStringExtra(NOMBRE_CONTACTO));
			txtTelefono.setText(intent.getStringExtra(TELEFONO_CONTACTO));
			txtEmail.setText(intent.getStringExtra(EMAIL_CONTACTO));
			fecha_nacimiento = intent.getStringExtra(FECHA_NAC_CONTACTO);
			
			if(fecha_nacimiento.length() > 7)
			{
				mYear =  Integer.parseInt(fecha_nacimiento.substring(0, 4));
				mMonth  =  Integer.parseInt(fecha_nacimiento.substring(4, 6));
				mDay  =  Integer.parseInt(fecha_nacimiento.substring(6));
				txtFechaAniversario.setText(mDay+"/"+mMonth+"/"+mYear);
				mMonth--;
			}	
			else
			{
				final Calendar c = Calendar.getInstance();
		        mYear = c.get(Calendar.YEAR);
		        mMonth = c.get(Calendar.MONTH);
		        mDay = c.get(Calendar.DAY_OF_MONTH);
		        updateDisplay();
			}			
			cboContacto.setSelection(application.getAdapterParametrosFicha(LISTA_CONTACTO).findByValue(intent.getStringExtra(TIPO_CONTACTO)));
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
	
	
	@Override
	protected boolean executeAsyncPre(int accion) {
		// TODO Auto-generated method stub
		boolean tieneError = false;
		
		if (txtNombre.getText().toString().trim()
				.equalsIgnoreCase("")) {
			txtNombre.setError(txtNombre_empty);
			tieneError = true;
		}
		if(!checkEmail(txtEmail.getText().toString()))
		{
			txtEmail.setError(txtEmail_empty);
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
			actualizarContactoProxy.setCodigo(codigo_cliente);
			actualizarContactoProxy.setCodigoContacto(codigo);
			actualizarContactoProxy.setNombreContacto(txtNombre.getText().toString());
			actualizarContactoProxy.setEmail(txtEmail.getText().toString());
			actualizarContactoProxy.setTelefono(txtTelefono.getText().toString());
			actualizarContactoProxy.setTipoContacto(((OpcionMultipleTO)cboContacto.getSelectedItem()).getCodigo());
			
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
			
			actualizarContactoProxy.setFechaNacimiento(mYear+""+sMonthFecha+""+sDayFecha);
			actualizarContactoProxy.setUsuario(usuario.getCodigoSap());
			actualizarContactoProxy.execute();
		}
		else if(accion == ACCION_NUEVO)
		{
			guardarContactoProxy.setCodigo(codigo_cliente);
			guardarContactoProxy.setNombreContacto(txtNombre.getText().toString());
			guardarContactoProxy.setEmail(txtEmail.getText().toString());
			guardarContactoProxy.setTelefono(txtTelefono.getText().toString());
			guardarContactoProxy.setTipoContacto(((OpcionMultipleTO)cboContacto.getSelectedItem()).getCodigo());
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
			guardarContactoProxy.setFechaNacimiento(mYear+""+sMonthFecha+""+sDayFecha);
			guardarContactoProxy.setUsuario(usuario.getCodigoSap());
			guardarContactoProxy.execute();
		}
		super.process(accion);
	}

	@Override
	protected void processOk(int accion) {
		// TODO Auto-generated method stub
		if(accion==ACCION_ACTUALIZAR){
			boolean isExito = actualizarContactoProxy.isExito();
			if (isExito) {
				int status = actualizarContactoProxy.getResponse().getStatus();
				if (status == 0) {
					showToast(contacto_actualizado_ok);
				}
				else  {
					showToast(actualizarContactoProxy.getResponse().getDescripcion());
				}
			}
			else{
				processError(accion);
			}
		}
		else if(accion == ACCION_NUEVO){
			boolean isExito = guardarContactoProxy.isExito();
			if (isExito) {
				int status = guardarContactoProxy.getResponse().getStatus();
				if (status == 0) {
					showToast(contacto_guardado_ok);
				}
				else  {
					showToast(guardarContactoProxy.getResponse().getDescripcion());
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

	public void btnGuardar_onclick(View view)
	{
		final Context context = this;
		
		MessageBox.showConfirmDialog(context, 
				ficha_guardar_contacto_title, 
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
    	txtFechaAniversario.setText(
            new StringBuilder()
                    // Month is 0 based so add 1
            		.append(mDay).append("/")
                    .append(mMonth + 1).append("/")
                    .append(mYear).append(""));
    }
}

