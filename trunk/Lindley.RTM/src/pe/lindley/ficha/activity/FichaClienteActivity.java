package pe.lindley.ficha.activity;

import java.util.Calendar;

import com.google.inject.Inject;
import com.thira.examples.actionbar.widget.ActionBar;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import pe.lindley.activity.R;
import pe.lindley.activity.RTMApplication;
import pe.lindley.ficha.ws.service.ActualizarClienteProxy;
import pe.lindley.ficha.ws.service.ObtenerClienteProxy;
import pe.lindley.lanzador.to.ClienteResumenTO;
import pe.lindley.lanzador.to.UsuarioTO;
import pe.lindley.util.ActivityBase;
import pe.lindley.util.MessageBox;
import roboguice.inject.InjectExtra;
import roboguice.inject.InjectResource;
import roboguice.inject.InjectView;

public class FichaClienteActivity extends ActivityBase {
	
	
	public static final int ACCION_UPDATE=0;
	public static final String CODIGO_CLIENTE="codigo";
	
	@InjectExtra(CODIGO_CLIENTE) private String codigo;
	@InjectResource(R.string.ficha_cliente_actualizado_ok) String cliente_actualizado_ok;
	@InjectResource(R.string.ficha_txtRazonComercial_empty) String txtRazonComercial_empty;
	@InjectResource(R.string.ficha_txtReferencia_empty) String txtReferencia_empty;
	@InjectResource(R.string.ficha_descargarparametros_activity_confirm_dialog_si) 		String 	confirm_si;
	@InjectResource(R.string.ficha_descargarparametros_activity_confirm_dialog_no) 		String 	confirm_no;
	@InjectResource(R.string.ficha_guardar_comercial_activity_confirm_dialog_message) 			String 	confirm_message;
	@InjectResource(R.string.ficha_guardar_comercial_activity_title) 	String 	ficha_guardar_comercial_title;
	
	@InjectView(R.id.txtCliente) 			EditText txtCliente;
	@InjectView(R.id.txtRazonSocial) 		EditText txtRazonSocial;
	@InjectView(R.id.txtDireccion) 			EditText txtDireccion;
	@InjectView(R.id.txtDistrito) 			EditText txtDistrito;
	@InjectView(R.id.txtRuc) 				EditText txtRuc;
	@InjectView(R.id.txtTelefono) 			EditText txtTelefono;
	@InjectView(R.id.txtRazonComercial) 	EditText txtRazonComercial;
	@InjectView(R.id.txtReferencia) 		EditText txtReferencia;
	@InjectView(R.id.txtFechaAniversario) 	EditText txtFechaAniversario;
	@InjectView(R.id.txtObservacion) 		EditText txtObservacion;
	@InjectView(R.id.actionBar)				ActionBar actionBar;
	
	
	@Inject ObtenerClienteProxy 	obtenerClienteProxy;
	@Inject ActualizarClienteProxy 	actualizarClienteProxy;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ficha_cliente_activity);
		
		RTMApplication application = (RTMApplication)getApplicationContext();
		ClienteResumenTO cliente = application.getClienteTO();
		actionBar.setSubTitle(cliente.getCodigoCliente()+"-"+cliente.getRazonSocial());
		actionBar.setTitle(R.string.ficha_ficha_cliente_actitivy);		
		actionBar.setHomeLogo(R.drawable.header_logo);
		
		
		processAsync();
		
		// get the current date
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);
        
        updateDisplay();
        
       
		
	}

	@Override
	protected void process() {
		// TODO Auto-generated method stub
		
		obtenerClienteProxy.setCliente(codigo);
		obtenerClienteProxy.execute();
		
		super.process();
	}	
	
	@Override
	protected boolean executeAsyncPre(int accion) {
		// TODO Auto-generated method stub
		boolean tieneError = false;
		if (accion == ACCION_UPDATE) {

			if (txtRazonComercial.getText().toString().trim()
					.equalsIgnoreCase("")) {
				txtRazonComercial.setError(txtRazonComercial_empty);
				tieneError = true;
			}

			if (txtReferencia.getText().toString().trim().equalsIgnoreCase("")) {
				txtReferencia.setError(txtReferencia_empty);
				tieneError = true;
			}
		}
		return !tieneError;
	}

	@Override
	protected void process(int accion) {
		// TODO Auto-generated method stub
		
		
		if(accion==ACCION_UPDATE){
			
			RTMApplication application = (RTMApplication)getApplicationContext();
			UsuarioTO usuario = application.getUsuarioTO();
			
			
			actualizarClienteProxy.setCodigo(codigo);
			String sMonth="0";
			mMonth++;
			if(mMonth<10)
				sMonth="0"+mMonth;
			else
				sMonth=""+mMonth;
			
			String sDay="0";
			if(mDay<10)
				sDay="0"+mDay;
			else
				sDay=""+mDay;
				
			actualizarClienteProxy.setFechAniv(mYear+""+sMonth+""+sDay);
			actualizarClienteProxy.setObservaciones(txtObservacion.getText().toString());
			actualizarClienteProxy.setRazonComercial(txtRazonComercial.getText().toString());
			actualizarClienteProxy.setReferencia(txtReferencia.getText().toString());
			actualizarClienteProxy.setUsuario(usuario.getCodigoSap());
			actualizarClienteProxy.execute();
			
		}
		
		super.process(accion);
	}

	@Override
	protected void processOk(int accion) {
		// TODO Auto-generated method stub
		if(accion==ACCION_UPDATE){
			boolean isExito = actualizarClienteProxy.isExito();
			if (isExito) {
				int status = actualizarClienteProxy.getResponse().getStatus();
				if (status == 0) {
					showToast(cliente_actualizado_ok);
				}
				else  {
					showToast(actualizarClienteProxy.getResponse().getDescripcion());
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
		
		boolean isExito = obtenerClienteProxy.isExito();

		if (isExito) {
			int status = obtenerClienteProxy.getResponse().getStatus();
			
			if (status == 0) {
				pe.lindley.ficha.to.ClienteTO clienteTO = obtenerClienteProxy.getResponse().getCliente();
				
				txtCliente.setText(clienteTO.getCodigo());
				txtRazonSocial.setText(clienteTO.getRazonSocial());
				txtDireccion.setText(clienteTO.getDireccion());
				txtDistrito.setText(clienteTO.getDistrito());
				txtRuc.setText(clienteTO.getRuc());
				txtTelefono.setText(clienteTO.getTelefono());
				txtRazonComercial.setText(clienteTO.getRazonComercial());
				txtReferencia.setText(clienteTO.getReferencia());
				txtObservacion.setText(clienteTO.getObservaciones());
				
				String fecha = clienteTO.getFechAniv();
				if(fecha.length() > 7)
				{
					 mYear = Integer.parseInt(fecha.substring(0, 4));
					 mMonth = Integer.parseInt(fecha.substring(4, 6));
					 mDay =  Integer.parseInt(fecha.substring(6));
					 txtFechaAniversario.setText(mDay+"/"+mMonth+"/"+mYear);	
					 mMonth--;
				}
			}else{
				showToast(obtenerClienteProxy.getResponse().getDescripcion());
			}
		}else{
			processError();
		}
	
		super.processOk();
	}

	
	
	
	
	@Override
	protected void processError() {
		// TODO Auto-generated method stub
		super.processError();
		showToast(error_generico_process);
	}
	
	public void btnGrabar_onclick(View view){
		final Context context = this;
		
		MessageBox.showConfirmDialog(context, 
				ficha_guardar_comercial_title, 
				confirm_message, 
				confirm_si,
				new OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						processAsync(ACCION_UPDATE);
					}
				},
				confirm_no,
				null);
	}
	
	
	public void btncalendario_onclick(View view){
		showDialog(DATE_DIALOG_ID);

	}
	
	private int mYear;
    private int mMonth;
    private int mDay;
    static final int DATE_DIALOG_ID = 0;
	
	
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
