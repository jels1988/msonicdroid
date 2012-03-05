package pe.lindley.puntointeres.activity;

import java.util.ArrayList;
import com.google.inject.Inject;
import com.thira.examples.actionbar.widget.ActionBar;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.AdapterView.OnItemSelectedListener;
import pe.lindley.activity.R;
import pe.lindley.activity.RTMApplication;
import pe.lindley.lanzador.to.ClienteResumenTO;
import pe.lindley.lanzador.to.UsuarioTO;
import pe.lindley.puntointeres.negocio.ParametroBLL;
import pe.lindley.puntointeres.to.ParametroTO;
import pe.lindley.puntointeres.to.SubGiroTO;
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
	@InjectView(R.id.cboUbigeo) 		Spinner 	cboUbigeo;

	@InjectView(R.id.cboSubGiro) 		Button 	cboSubGiro;
	//@InjectView(R.id.cboSubGiro) 		Spinner 	cboSubGiro;
	
	@Inject GuardarPuntoInteresProxy guardarPuntoInteresProxy;
	@InjectExtra(LATITUD)  double latitud_pto;
	@InjectExtra(LONGITUD) double longitud_pto;
	public String codigo_cliente = "";
	
	
	//__----------------------------------------------
	
	protected ArrayList<ParametroTO> subGiros;
	protected ArrayList<ParametroTO> selectedsubGiros = new ArrayList<ParametroTO>();
	
	protected void onChangeSelectedSubgiros() {
		StringBuilder stringBuilder = new StringBuilder();

		for(ParametroTO parametro : selectedsubGiros)
			stringBuilder.append(parametro.getDescripcion() + ",");
		String valueText = stringBuilder.toString().substring(0, stringBuilder.length() - 1);
		cboSubGiro.setText(valueText);
	}
	
	
	protected void showSelectSubGiroDialog() {
		boolean[] checkedColours = new boolean[subGiros.size()];
		int count = subGiros.size();

		for(int i = 0; i < count; i++)
			checkedColours[i] = selectedsubGiros.contains((ParametroTO)subGiros.get(i));

		DialogInterface.OnMultiChoiceClickListener subGiroDialogListener = new DialogInterface.OnMultiChoiceClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which, boolean isChecked) {
				if(isChecked)
					selectedsubGiros.add((ParametroTO)subGiros.get(which));
				else
					selectedsubGiros.remove((ParametroTO)subGiros.get(which));

				onChangeSelectedSubgiros();
			}
		};

		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("Selecccionar SubGiros");
		
		
		
		CharSequence[] temp = new CharSequence[count];
		
		for(int i=0;i<count;i++)
		{
			temp[i] = ((ParametroTO) subGiros.get(i)).getDescripcion();
		}
		
		builder.setMultiChoiceItems(temp, checkedColours, subGiroDialogListener);

		AlertDialog dialog = builder.create();
		dialog.show();
	}
	//__----------------------------------------------
	
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
		    	//cboSubGiro.setAdapter(application.getAdapterParametrosPINT(ParametroBLL.TBL_SUB_GIRO, ((ParametroTO)cboGiro.getSelectedItem()).getCodigo()));
		    	subGiros = application.getAdapterListParametrosPINT(ParametroBLL.TBL_SUB_GIRO, ((ParametroTO)cboGiro.getSelectedItem()).getCodigo());
		    	cboSubGiro.setText("--Seleccionar--");
		    	selectedsubGiros = new ArrayList<ParametroTO>();
		    	if(subGiros.size() > 0)
		    		cboSubGiro.setEnabled(true);
		    	else
		    		cboSubGiro.setEnabled(false);
		    }

		    @Override
		    public void onNothingSelected(AdapterView<?> parentView) {
		        // your code here
		    }

		});
	}
	
	public void btnSubGiro_onclick(View view)
	{
		showSelectSubGiroDialog();
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
		
		ArrayList<SubGiroTO> listSubGiro = new ArrayList<SubGiroTO>();
		for(ParametroTO parametro : selectedsubGiros)
		{
			SubGiroTO subGiro = new SubGiroTO();
			subGiro.setCodigo(parametro.getCodigo());
			listSubGiro.add(subGiro);
		}
		
		guardarPuntoInteresProxy.setListSubGiro(listSubGiro);		
		//guardarPuntoInteresProxy.setTipoGiro(((ParametroTO)cboSubGiro.getSelectedItem()).getCodigo());
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
