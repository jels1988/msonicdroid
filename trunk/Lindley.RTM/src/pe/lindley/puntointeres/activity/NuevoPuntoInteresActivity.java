package pe.lindley.puntointeres.activity;

import com.google.inject.Inject;
import com.thira.examples.actionbar.widget.ActionBar;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.AdapterView.OnItemSelectedListener;
import pe.lindley.activity.R;
import pe.lindley.activity.RTMApplication;
import pe.lindley.lanzador.to.ClienteResumenTO;
import pe.lindley.lanzador.to.UsuarioTO;
import pe.lindley.puntointeres.negocio.ParametroBLL;
import pe.lindley.puntointeres.to.ParametroTO;
import pe.lindley.puntointeres.ws.service.GuardarPuntoInteresProxy;
import pe.lindley.util.ActivityBase;
import roboguice.inject.InjectExtra;
import roboguice.inject.InjectResource;
import roboguice.inject.InjectView;

public class NuevoPuntoInteresActivity extends ActivityBase {

	public static final String LATITUD = "latitud_punto";
	public static final String LONGITUD = "longitud_punto";
	
	@InjectResource(R.string.punto_guardar_ok) String punto_guardar_ok;
	
	@InjectView(R.id.actionBar)			ActionBar	mActionBar;
	@InjectView(R.id.txtNombre)			TextView 	txtNombre;
	@InjectView(R.id.txtDireccion)		TextView 	txtDireccion;
	@InjectView(R.id.txtDescripcion)	TextView 	txtDescripcion;
	@InjectView(R.id.cboGiro) 			Spinner 	cboGiro;
	@InjectView(R.id.cboSubGiro) 		Spinner 	cboSubGiro;
	@InjectView(R.id.cboUbigeo) 		Spinner 	cboUbigeo;
	
	@Inject GuardarPuntoInteresProxy guardarPuntoInteresProxy;
	@InjectExtra(LATITUD)  double latitud_pto;
	@InjectExtra(LONGITUD) double longitud_pto;
	public String codigo_cliente = "";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.puntointeres_nuevo_punto_activity);
		final RTMApplication application = (RTMApplication)contextProvider.get().getApplicationContext();
		ClienteResumenTO cliente = application.getClienteTO();
		codigo_cliente = cliente.getCodigoCliente();
		mActionBar.setTitle(R.string.puntointeres_nuevo_punto_title);
		mActionBar.setHomeLogo(R.drawable.header_logo);
		mActionBar.setSubTitle(cliente.getRazonSocial());
		
		txtDireccion.setText(cliente.getDireccion());
		cboGiro.setAdapter(application.getAdapterParametrosPINT(ParametroBLL.TBL_GIRO));		
		cboUbigeo.setAdapter(application.getAdapterParametrosPINT(ParametroBLL.TBL_UBIGEO));
		cboUbigeo.setSelection(application.getAdapterParametrosPINT(ParametroBLL.TBL_UBIGEO).findByValue("150"+cliente.getSubCanal()));
		cboGiro.setOnItemSelectedListener(new OnItemSelectedListener() {
		    @Override
		    public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
		        // your code here
		    	cboSubGiro.setAdapter(application.getAdapterParametrosPINT(ParametroBLL.TBL_SUB_GIRO, ((ParametroTO)cboGiro.getSelectedItem()).getCodigo()));		    	
		    }

		    @Override
		    public void onNothingSelected(AdapterView<?> parentView) {
		        // your code here
		    }

		});
	}
	
	public void btnGuardar_onclick(View view)
	{
		processAsync();		
	}
	
	@Override
	protected void process() {
		// TODO Auto-generated method stub
		RTMApplication application = (RTMApplication)getApplicationContext();
		UsuarioTO usuario = application.getUsuarioTO();
		
		guardarPuntoInteresProxy.setCodCliente(codigo_cliente);
		guardarPuntoInteresProxy.setCodGiro(((ParametroTO)cboGiro.getSelectedItem()).getCodigo());
		guardarPuntoInteresProxy.setTipoGiro(((ParametroTO)cboSubGiro.getSelectedItem()).getCodigo());
		guardarPuntoInteresProxy.setDescripcion(txtDescripcion.getText().toString());
		guardarPuntoInteresProxy.setDireccion(txtDireccion.getText().toString());
		guardarPuntoInteresProxy.setLatitudDec(latitud_pto);
		guardarPuntoInteresProxy.setLongitudDec(longitud_pto);
		guardarPuntoInteresProxy.setNombre(txtNombre.getText().toString());
		guardarPuntoInteresProxy.setUsuario(usuario.getCodigoSap());
		guardarPuntoInteresProxy.setCodPunto("0");
		guardarPuntoInteresProxy.setCodUbigeo(((ParametroTO)cboUbigeo.getSelectedItem()).getCodigo());
		guardarPuntoInteresProxy.setLatitud(" ");
		guardarPuntoInteresProxy.setLongitud(" ");		
		guardarPuntoInteresProxy.execute();
		super.process();
	}

	@Override
	protected void processOk() {
		// TODO Auto-generated method stub
		boolean isExito = guardarPuntoInteresProxy.isExito();
		
		if (isExito) {
			int status = guardarPuntoInteresProxy.getResponse().getStatus();
			if (status == 0) {
				showToast(punto_guardar_ok);
				finish();
			}
			else  {
				showToast(guardarPuntoInteresProxy.getResponse().getDescripcion());
			}
		}
		else{
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
	
}
