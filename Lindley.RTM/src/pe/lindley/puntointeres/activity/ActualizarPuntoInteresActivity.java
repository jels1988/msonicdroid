
package pe.lindley.puntointeres.activity;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.AdapterView.OnItemSelectedListener;
import com.google.inject.Inject;
import com.thira.examples.actionbar.widget.ActionBar;
import pe.lindley.activity.R;
import pe.lindley.activity.RTMApplication;
import pe.lindley.lanzador.to.ClienteResumenTO;
import pe.lindley.lanzador.to.UsuarioTO;
import pe.lindley.puntointeres.negocio.ParametroBLL;
import pe.lindley.puntointeres.to.ParametroTO;
import pe.lindley.puntointeres.to.SubGiroTO;
import pe.lindley.puntointeres.ws.service.ActualizarPuntoInteresProxy;
import pe.lindley.util.ActivityBase;
import roboguice.inject.InjectExtra;
import roboguice.inject.InjectResource;
import roboguice.inject.InjectView;

public class ActualizarPuntoInteresActivity extends ActivityBase {

	protected ArrayList<ParametroTO> subGiros;
	protected ArrayList<ParametroTO> selectedsubGiros = new ArrayList<ParametroTO>();
	
	public static final String LATITUD = "latitud_punto";
	public static final String LONGITUD = "longitud_punto";
	protected static final String CODIGO_PUNTO = "punto_codigo";
	protected static final String CODIGO_GIRO = "grio_codigo";
	protected static final String TIPO_GIRO = "giro_tipo";
	protected static final String CODIGO_UBIGEO = "ubigeo_codigo";
	protected static final String DESCRIPCION = "descripcion_pto";
	protected static final String DIRECCION = "direccion_pto";
	protected static final String SLATITUD = "slatitud";
	protected static final String SLONGITUD = "slongitud";
	protected static final String NOMBRE = "nombre_pto";
	
	@InjectResource(R.string.punto_actualizar_ok) String punto_actualizar_ok;
	
	@InjectView(R.id.actionBar)			ActionBar	mActionBar;
	@InjectView(R.id.txtNombre)			TextView 	txtNombre;
	@InjectView(R.id.txtDireccion)		TextView 	txtDireccion;
	@InjectView(R.id.txtDescripcion)	TextView 	txtDescripcion;
	@InjectView(R.id.cboGiro) 			Spinner 	cboGiro;
	@InjectView(R.id.cboSubGiro) 		Button 		cboSubGiro;
	@InjectView(R.id.cboUbigeo) 		Spinner 	cboUbigeo;
	
	@Inject ActualizarPuntoInteresProxy actualizarPuntoInteresProxy;
	@InjectExtra(LATITUD)  		double 	 latitud_pto;
	@InjectExtra(LONGITUD) 		double 	 longitud_pto;
	@InjectExtra(CODIGO_PUNTO) 	String 	 punto_codigo;
	@InjectExtra(CODIGO_GIRO) 	String 	 giro_codigo;
	@InjectExtra(TIPO_GIRO) 	String[] tipo_giro;
	@InjectExtra(CODIGO_UBIGEO) String   codigo_ubigeo;
	@InjectExtra(DESCRIPCION) 	String 	 descripcion;
	@InjectExtra(DIRECCION) 	String 	 direccion;
	@InjectExtra(SLATITUD) 		String 	 pto_slatitud;
	@InjectExtra(SLONGITUD) 	String 	 pto_slongitud;
	@InjectExtra(NOMBRE) 		String 	 pto_nombre;
	public String codigo_cliente = "";
	boolean inicioActivity = true;
	boolean inicioButton = true;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.puntointeres_actualizar_punto_activity);
		final RTMApplication application = (RTMApplication)contextProvider.get().getApplicationContext();
		ClienteResumenTO cliente = application.getClienteTO();
		codigo_cliente = cliente.getCodigoCliente();
		mActionBar.setTitle(R.string.puntointeres_actualizar_punto_title);
		mActionBar.setHomeLogo(R.drawable.header_logo);
		mActionBar.setSubTitle(cliente.getRazonSocial());

		cboGiro.setAdapter(application.getAdapterParametrosPINT(ParametroBLL.TBL_GIRO));		
		cboUbigeo.setAdapter(application.getAdapterParametrosPINT(ParametroBLL.TBL_UBIGEO));		
		cboGiro.setOnItemSelectedListener(new OnItemSelectedListener() {
		    @Override
		    public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
		        // your code here
		    	//cboSubGiro.setAdapter(application.getAdapterParametrosPINT(ParametroBLL.TBL_SUB_GIRO, ((ParametroTO)cboGiro.getSelectedItem()).getCodigo()));
		    	subGiros = application.getAdapterListParametrosPINT(ParametroBLL.TBL_SUB_GIRO, ((ParametroTO)cboGiro.getSelectedItem()).getCodigo());
		    	if(inicioActivity)
		    	{
		    		cboSubGiro.setText("--Seleccionar--");
		    		selectedsubGiros = new ArrayList<ParametroTO>();
		    	}	    	
		    	inicioActivity = true;
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
		
		txtNombre.setText(pto_nombre);
		txtDireccion.setText(direccion);
		txtDescripcion.setText(descripcion);
		
		cboUbigeo.setSelection(application.getAdapterParametrosPINT(ParametroBLL.TBL_UBIGEO).findByValue(codigo_ubigeo));
		
		if(!giro_codigo.equals(""))			
		{
			cboGiro.setSelection(application.getAdapterParametrosPINT(ParametroBLL.TBL_GIRO).findByValue(giro_codigo));
			//cboSubGiro.setAdapter(application.getAdapterParametrosPINT(ParametroBLL.TBL_SUB_GIRO, ((ParametroTO)cboGiro.getSelectedItem()).getCodigo()));
			subGiros = application.getAdapterListParametrosPINT(ParametroBLL.TBL_SUB_GIRO, ((ParametroTO)cboGiro.getSelectedItem()).getCodigo());
	    	cboSubGiro.setText("--Seleccionar--");
	    	selectedsubGiros = new ArrayList<ParametroTO>();
	    	if(subGiros.size() > 0)
	    		cboSubGiro.setEnabled(true);
	    	else
	    		cboSubGiro.setEnabled(false);
		}		
		
		int count = tipo_giro.length;
		if(count > 0)
		{			
			for(int i=0; i<count; i++)
			{
				for(ParametroTO param : subGiros)
				{
					if(tipo_giro[i].equals(param.getCodigo()))
						selectedsubGiros.add(param);
				}
			}
			onChangeSelectedSubgiros();		
		}
	}
	
	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		inicioActivity = false;
		inicioButton = false;
		super.onStart();
	}

	public void btnActualizar_onclick(View view)
	{
		processAsync();		
	}
	
	@Override
	protected void process() {
		// TODO Auto-generated method stub
		RTMApplication application = (RTMApplication)getApplicationContext();
		UsuarioTO usuario = application.getUsuarioTO();
		
		actualizarPuntoInteresProxy.setCodCliente(codigo_cliente);
		actualizarPuntoInteresProxy.setCodGiro(((ParametroTO)cboGiro.getSelectedItem()).getCodigo());
		
		ArrayList<SubGiroTO> listSubGiro = new ArrayList<SubGiroTO>();
		for(ParametroTO parametro : selectedsubGiros)
		{
			SubGiroTO subGiro = new SubGiroTO();
			subGiro.setCodigo(parametro.getCodigo());
			listSubGiro.add(subGiro);
		}
		
		actualizarPuntoInteresProxy.setListSubGiro(listSubGiro);
		actualizarPuntoInteresProxy.setDescripcion(txtDescripcion.getText().toString());
		actualizarPuntoInteresProxy.setDireccion(txtDireccion.getText().toString());
		actualizarPuntoInteresProxy.setLatitudDec(latitud_pto);
		actualizarPuntoInteresProxy.setLongitudDec(longitud_pto);
		actualizarPuntoInteresProxy.setNombre(txtNombre.getText().toString());
		actualizarPuntoInteresProxy.setUsuario(usuario.getCodigoSap());
		actualizarPuntoInteresProxy.setCodPunto(punto_codigo);
		actualizarPuntoInteresProxy.setCodUbigeo(((ParametroTO)cboUbigeo.getSelectedItem()).getCodigo());
		if(pto_slatitud.equals(""))pto_slatitud=" ";
		if(pto_slongitud.equals(""))pto_slongitud=" ";
		actualizarPuntoInteresProxy.setLatitud(pto_slatitud);
		actualizarPuntoInteresProxy.setLongitud(pto_slongitud);		
		actualizarPuntoInteresProxy.execute();
		super.process();
	}

	@Override
	protected void processOk() {
		// TODO Auto-generated method stub
		boolean isExito = actualizarPuntoInteresProxy.isExito();
		
		if (isExito) {
			int status = actualizarPuntoInteresProxy.getResponse().getStatus();
			if (status == 0) {
				showToast(punto_actualizar_ok);
				finish();
			}
			else  {
				showToast(actualizarPuntoInteresProxy.getResponse().getDescripcion());
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
		
	public void btnSubGiro_onclick(View view)
	{		
		showSelectSubGiroDialog();
	}
	
	protected void onChangeSelectedSubgiros() {
		StringBuilder stringBuilder = new StringBuilder();
		String valueText = "";
		for(ParametroTO parametro : selectedsubGiros)
			stringBuilder.append(parametro.getDescripcion() + ",");
		if(stringBuilder.toString().length()>0)
		{
			valueText = stringBuilder.toString().substring(0, stringBuilder.length() - 1);
			System.out.println(valueText);
			cboSubGiro.setText(valueText);
		}
	}
	
	
	protected void showSelectSubGiroDialog() {
		if(!inicioButton)
		{
			int counts = tipo_giro.length;
			if(counts > 0)
			{			
				for(int i=0; i<counts; i++)
				{
					for(ParametroTO param : subGiros)
					{
						if(tipo_giro[i].equals(param.getCodigo()))
							selectedsubGiros.add(param);
					}
				}
			}
			inicioButton = true;
		}
		boolean[] checkedColours = new boolean[subGiros.size()];
		int count = subGiros.size();

		for(int i = 0; i < count; i++)
		{
			checkedColours[i] = selectedsubGiros.contains((ParametroTO)subGiros.get(i));
			System.out.println(checkedColours[i]);
		}

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
		builder.setTitle("Seleccionar SubGiros");
		
		
		
		CharSequence[] temp = new CharSequence[count];
		
		for(int i=0;i<count;i++)
		{
			temp[i] = ((ParametroTO) subGiros.get(i)).getDescripcion();
		}
		
		builder.setMultiChoiceItems(temp, checkedColours, subGiroDialogListener);

		AlertDialog dialog = builder.create();
		dialog.show();
	}
}
