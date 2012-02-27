package pe.lindley.ventacero.activity;

import com.google.inject.Inject;
import com.thira.examples.actionbar.widget.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.AdapterView.OnItemSelectedListener;
import pe.lindley.activity.R;
import pe.lindley.activity.RTMApplication;
import pe.lindley.lanzador.to.UsuarioTO;
import pe.lindley.util.ActivityBase;
import pe.lindley.ventacero.negocio.ParametroBLL;
import pe.lindley.ventacero.to.ParametroTO;
import roboguice.inject.InjectView;

public class VentaCeroActivity extends ActivityBase {
	@Inject ParametroBLL parametroBLL;
	
	public static final String LISTA_ANIO = "02";
	public static final String LISTA_MES = "03";
	public static final String LISTA_SEMANA = "04";
	public static final String LISTA_RUTA = "05";
	
	@InjectView(R.id.actionBar)			ActionBar mActionBar;
	@InjectView(R.id.txtCodCliente) 	TextView txtCodCliente;
	@InjectView(R.id.txtRazonSocial)	TextView txtRazonSocial;
	@InjectView(R.id.txtRuc) 			TextView txtRuc;
	@InjectView(R.id.txtDni)			TextView txtDni;	
	@InjectView(R.id.txtSegmento)		TextView txtSegmento;
	
	@InjectView(R.id.cboAnio) 		Spinner cboAnio;
	@InjectView(R.id.cboMes) 		Spinner cboMes;
	@InjectView(R.id.cboSemana) 	Spinner cboSemana;
	@InjectView(R.id.cboRuta) 		Spinner cboRuta;
		
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ventacero_busqueda_activity);
		mActionBar.setTitle(R.string.vtacero_consultar_activity_title);
		mActionBar.setHomeLogo(R.drawable.header_logo);
		txtCodCliente.addTextChangedListener(txtCodClienteTextWatcher);
		txtRazonSocial.addTextChangedListener(txtRazonSocialTextWatcher);
		txtRuc.addTextChangedListener(txtRucTextWatcher);
		txtDni.addTextChangedListener(txtDniTextWatcher);
		
		final RTMApplication application = (RTMApplication)contextProvider.get().getApplicationContext();
		cboAnio.setAdapter(application.getAdapterParametrosVentaCero(LISTA_ANIO));
		cboRuta.setAdapter(application.getAdapterParametrosVentaCero(LISTA_RUTA));

		
		
		cboAnio.setOnItemSelectedListener(new OnItemSelectedListener() {
		    @Override
		    public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
		        // your code here
		    	cboMes.setAdapter(application.getAdapterParametrosVentaCero(LISTA_MES, ((ParametroTO)cboAnio.getSelectedItem()).getCodigo(), LISTA_MES));		    	
		    }

		    @Override
		    public void onNothingSelected(AdapterView<?> parentView) {
		        // your code here
		    }

		});
		
		cboMes.setOnItemSelectedListener(new OnItemSelectedListener() {
		    @Override
		    public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
		        // your code here
		    	cboSemana.setAdapter(application.getAdapterParametrosVentaCero(LISTA_SEMANA, ((ParametroTO)cboAnio.getSelectedItem()).getCodigo(), ((ParametroTO)cboMes.getSelectedItem()).getCodigo()));
		    }

		    @Override
		    public void onNothingSelected(AdapterView<?> parentView) {
		        // your code here
		    }

		});
	}
	
	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
			
		/*cboAnio.setSelection(0);
		cboMes.setSelection(0);
		cboSemana.setSelection(0);
		cboRuta.setSelection(0);
		txtSegmento.setText("");
		txtCodCliente.setText("");
		txtRazonSocial.setText("");
		txtRuc.setText("");
		txtDni.setText("");	
		*/
		super.onStart();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.ventacero_descargarparametros_menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
			case R.id.mnuDescargar: {
				Intent descargarParametros = new Intent(this,
						DescargarParametrosActivity.class);
				startActivity(descargarParametros);
				return true;
			}		
		}
		return true;
	}

	private TextWatcher txtRucTextWatcher = new TextWatcher() {
		@Override
		public void onTextChanged(CharSequence s, int start, int before,
				int count) {
			// TODO Auto-generated method stub
			if (txtRuc.getText() != null) {

				txtDni.removeTextChangedListener(txtDniTextWatcher);

				txtDni.setText(null);

				txtDni.addTextChangedListener(txtDniTextWatcher);
			}
		}

		@Override
		public void beforeTextChanged(CharSequence s, int start, int count,
				int after) {
			// TODO Auto-generated method stub

		}

		@Override
		public void afterTextChanged(Editable s) {
			// TODO Auto-generated method stub

		}
	};

	private TextWatcher txtDniTextWatcher = new TextWatcher() {

		@Override
		public void onTextChanged(CharSequence s, int start, int before,
				int count) {
			// TODO Auto-generated method stub
			if (txtDni.getText() != null) {
				txtRuc.removeTextChangedListener(txtRucTextWatcher);

				txtRuc.setText(null);

				txtRuc.addTextChangedListener(txtRucTextWatcher);
			}
		}

		@Override
		public void beforeTextChanged(CharSequence s, int start, int count,
				int after) {
			// TODO Auto-generated method stub

		}

		@Override
		public void afterTextChanged(Editable s) {
			// TODO Auto-generated method stub

		}
	};
	
	private TextWatcher txtCodClienteTextWatcher = new TextWatcher() {
		@Override
		public void onTextChanged(CharSequence s, int start, int before,
				int count) {
			// TODO Auto-generated method stub
			if (txtCodCliente.getText() != null) {

				txtRazonSocial.removeTextChangedListener(txtRazonSocialTextWatcher);

				txtRazonSocial.setText(null);

				txtRazonSocial.addTextChangedListener(txtRazonSocialTextWatcher);
			}
		}

		@Override
		public void beforeTextChanged(CharSequence s, int start, int count,
				int after) {
			// TODO Auto-generated method stub

		}

		@Override
		public void afterTextChanged(Editable s) {
			// TODO Auto-generated method stub

		}
	};
	
	private TextWatcher txtRazonSocialTextWatcher = new TextWatcher() {
		@Override
		public void onTextChanged(CharSequence s, int start, int before,
				int count) {
			// TODO Auto-generated method stub
			if (txtRazonSocial.getText() != null) {

				txtCodCliente.removeTextChangedListener(txtCodClienteTextWatcher);

				txtCodCliente.setText(null);

				txtCodCliente.addTextChangedListener(txtCodClienteTextWatcher);
			}
		}

		@Override
		public void beforeTextChanged(CharSequence s, int start, int count,
				int after) {
			// TODO Auto-generated method stub

		}

		@Override
		public void afterTextChanged(Editable s) {
			// TODO Auto-generated method stub

		}
	};
	
	
	public void btnBuscar_onclick(View view)
	{
		RTMApplication application = (RTMApplication)getApplicationContext();
		UsuarioTO usuario = application.getUsuarioTO();
		
		Intent mostrarVentaCero = new Intent(this,MostrarVentaCeroActivity.class);
		mostrarVentaCero.putExtra(MostrarVentaCeroActivity.CODIGO_DEPOSITO, usuario.getCodigoDeposito());
		mostrarVentaCero.putExtra(MostrarVentaCeroActivity.CODIGO_SAP, usuario.getCodigoSap());
		mostrarVentaCero.putExtra(MostrarVentaCeroActivity.ANIO, ((ParametroTO)cboAnio.getSelectedItem()).getCodigo());
		mostrarVentaCero.putExtra(MostrarVentaCeroActivity.MES, ((ParametroTO)cboMes.getSelectedItem()).getCodigo());
		mostrarVentaCero.putExtra(MostrarVentaCeroActivity.SEMANA, ((ParametroTO)cboSemana.getSelectedItem()).getCodigo());
		mostrarVentaCero.putExtra(MostrarVentaCeroActivity.RUTA, ((ParametroTO)cboRuta.getSelectedItem()).getCodigo());
		mostrarVentaCero.putExtra(MostrarVentaCeroActivity.SEGMENTO, txtSegmento.getText().toString());
		mostrarVentaCero.putExtra(MostrarVentaCeroActivity.CODIGO_CLIENTE, txtCodCliente.getText().toString());
		mostrarVentaCero.putExtra(MostrarVentaCeroActivity.NOMBRE_CLIENTE, txtRazonSocial.getText().toString());
		mostrarVentaCero.putExtra(MostrarVentaCeroActivity.RUC, txtRuc.getText().toString());
		mostrarVentaCero.putExtra(MostrarVentaCeroActivity.DNI, txtDni.getText().toString());		
		startActivity(mostrarVentaCero);
	}	
	
}

