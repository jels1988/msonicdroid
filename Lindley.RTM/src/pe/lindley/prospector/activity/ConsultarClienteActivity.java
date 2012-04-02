package pe.lindley.prospector.activity;

import java.util.List;

import com.google.inject.Inject;
import com.thira.examples.actionbar.widget.ActionBar;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.TextView;
import pe.lindley.activity.DireccionActivity;
import pe.lindley.activity.R;
import pe.lindley.equipofrio.activity.ConsultarEquipoFrioActivity;
import pe.lindley.lanzador.to.ClienteResumenTO;
import pe.lindley.lanzador.ws.service.ConsultarClienteProxy;
import pe.lindley.profit.activity.ProfitHistoryActivity;
import pe.lindley.profit.activity.ProfitHistoryDatosComercialesActivity;
import pe.lindley.util.ListActivityBase;
import roboguice.inject.InjectResource;
import roboguice.inject.InjectView;

public class ConsultarClienteActivity extends ListActivityBase {

	@InjectView(R.id.actionBar)ActionBar mActionBar;
	@InjectView(R.id.txtCodigo)TextView txtCodigo;
	@InjectView(R.id.txtRuc)TextView txtRuc;
	@InjectView(R.id.txtDni)TextView txtDni;
	@Inject ConsultarClienteProxy consultarClienteProxy;
	@InjectResource(R.string.consultar_cliente_activity_empty) String parametros_empty;

	private EfficientAdapter adap;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.consultarcliente_activity);

		mActionBar.setTitle(R.string.consultar_cliente_activity_title);
		mActionBar.setHomeLogo(R.drawable.header_logo);
		txtCodigo.addTextChangedListener(txtCodigoTextWatcher);
		txtRuc.addTextChangedListener(txtRucTextWatcher);
		txtDni.addTextChangedListener(txtDniTextWatcher);
		

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.consultarcliente_menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {
		case R.id.mnuEnviar: {
			Intent subirClientes = new Intent(this,
					EnviarClientesActivity.class);
			startActivity(subirClientes);
			return true;
		}
		case R.id.mnuDescargar: {
			Intent descargarClientes = new Intent(this,
					DescargarRechazadosActivity.class);
			startActivity(descargarClientes);
			return true;
		}
		
		case R.id.mnuFichas: {
			Intent fichasLocales = new Intent(this,ConsultarClienteLocalActivity.class);
			startActivity(fichasLocales);
			return true;
		}
		case R.id.mnuTDocumentos:{
			Intent tipoDocumentos = new Intent(this,DescargarTipoDocumentosActivity.class);
			startActivity(tipoDocumentos);
			return true;
		}
		}
		return true;

	}

	@Override
	protected boolean executeAsyncPre() {
		// TODO Auto-generated method stub
		boolean isCodigo = txtCodigo.getText().toString().trim()
				.equalsIgnoreCase("");
		boolean isRuc = txtRuc.getText().toString().trim().equalsIgnoreCase("");
		boolean isDni = txtDni.getText().toString().trim().equalsIgnoreCase("");

		if (isCodigo && isRuc && isDni) {
			txtCodigo.setError(parametros_empty);
			txtRuc.setError(parametros_empty);
			txtDni.setError(parametros_empty);
			return false;
		} else {
			txtCodigo.setError(null);
			txtRuc.setError(null);
			txtDni.setError(null);
		}

		return true;

	}

	public void btnbuscar_onclick(View view) {
		processAsync();
	}

	@Override
	protected void process() {
		// TODO Auto-generated method stub
		String codigo = txtCodigo.getText().toString();
		String ruc = txtRuc.getText().toString();
		String dni = txtDni.getText().toString();

		consultarClienteProxy.setCodigo(codigo);
		consultarClienteProxy.setRuc(ruc);
		consultarClienteProxy.setDni(dni);

		consultarClienteProxy.execute();

	}

	@Override
	protected void processOk() {
		// TODO Auto-generated method stub
		boolean isExito = consultarClienteProxy.isExito();
		if (isExito) {
			int status = consultarClienteProxy.getResponse().getStatus();
			if (status == 0) {
				List<ClienteResumenTO> clientes = consultarClienteProxy.getResponse().getClientes();
				adap = new EfficientAdapter(this, clientes);
				setListAdapter(adap);
			}
		}
		super.processOk();
	}

	@Override
	protected void processError() {
		// TODO Auto-generated method stub
		super.processError();
	}

	private TextWatcher txtRucTextWatcher = new TextWatcher() {
		@Override
		public void onTextChanged(CharSequence s, int start, int before,
				int count) {
			// TODO Auto-generated method stub
			if (txtRuc.getText() != null) {

				txtCodigo.removeTextChangedListener(txtCodigoTextWatcher);
				txtDni.removeTextChangedListener(txtDniTextWatcher);

				txtCodigo.setText(null);
				txtDni.setText(null);

				txtCodigo.addTextChangedListener(txtCodigoTextWatcher);
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
				txtCodigo.removeTextChangedListener(txtCodigoTextWatcher);
				txtRuc.removeTextChangedListener(txtRucTextWatcher);

				txtCodigo.setText(null);
				txtRuc.setText(null);

				txtCodigo.addTextChangedListener(txtCodigoTextWatcher);
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
	private TextWatcher txtCodigoTextWatcher = new TextWatcher() {
		@Override
		public void onTextChanged(CharSequence s, int start, int before,
				int count) {
			// TODO Auto-generated method stub
			if (txtCodigo.getText() != null) {

				txtRuc.removeTextChangedListener(txtRucTextWatcher);
				txtDni.removeTextChangedListener(txtDniTextWatcher);

				txtRuc.setText(null);
				txtDni.setText(null);

				txtRuc.addTextChangedListener(txtRucTextWatcher);
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

	public static class EfficientAdapter extends BaseAdapter implements
			Filterable {
		private LayoutInflater mInflater;
		private Context context;
		private List<ClienteResumenTO> clientes;

		public EfficientAdapter(Context context, List<ClienteResumenTO> clientes) {
			// Cache the LayoutInflate to avoid asking for a new one each time.
			mInflater = LayoutInflater.from(context);
			this.context = context;
			this.clientes = clientes;
		}

		public EfficientAdapter(Context context) {
			// Cache the LayoutInflate to avoid asking for a new one each time.
			mInflater = LayoutInflater.from(context);
			this.context = context;
		}

		/**
		 * Make a view to hold each row.
		 * 
		 * @see android.widget.ListAdapter#getView(int, android.view.View,
		 *      android.view.ViewGroup)
		 */
		public View getView(final int position, View convertView,
				ViewGroup parent) {
			ClienteResumenTO cliente = (ClienteResumenTO) getItem(position);
			ViewHolder holder;

			if (convertView == null) {
				convertView = mInflater.inflate(
						R.layout.consultarcliente_content, null);

				// Creates a ViewHolder and store references to the two children
				// views
				// we want to bind data to.
				holder = new ViewHolder();
				holder.txtRazonSocial = (TextView) convertView
						.findViewById(R.id.txtRazonSocial);
				holder.txtRuc = (TextView) convertView
						.findViewById(R.id.txtRuc);
				holder.txtDni = (TextView) convertView
						.findViewById(R.id.txtDni);
				holder.txtCodigo = (TextView) convertView
						.findViewById(R.id.txtCodigo);
				holder.txtDireccion = (TextView) convertView
						.findViewById(R.id.txtDireccion);
				holder.txtCda = (TextView) convertView
						.findViewById(R.id.txtCda);
				holder.txtRepresentante = (TextView) convertView
						.findViewById(R.id.txtRepresentante);
				holder.txtSubCanal = (TextView) convertView
						.findViewById(R.id.txtSubCanal);
				holder.txtRuta = (TextView) convertView
						.findViewById(R.id.txtRuta);
				holder.txtFecCreacion = (TextView) convertView
						.findViewById(R.id.txtFecCreacion);
				holder.txtFecActualizacion = (TextView) convertView
						.findViewById(R.id.txtFecActualizacion);
				holder.txtFecSuspencion = (TextView) convertView
						.findViewById(R.id.txtFecSuspencion);
				holder.imgDireccion = (ImageButton) convertView
						.findViewById(R.id.btn_buscar);
				holder.imgEquipoFrio = (ImageButton) convertView
						.findViewById(R.id.btn_equipofrio);

				convertView.setTag(holder);
			} else {
				// Get the ViewHolder back to get fast access to the TextView
				// and the ImageView.
				holder = (ViewHolder) convertView.getTag();
			}

			holder.txtRazonSocial.setText(cliente.getRazonSocial());
			holder.txtRuc.setText(cliente.getRuc());
			holder.txtDni.setText(cliente.getDni());
			holder.txtCodigo.setText(cliente.getCodigoCliente());
			holder.txtDireccion.setText(cliente.getDireccion());
			holder.txtCda.setText(cliente.getCodigoCda());
			holder.txtRepresentante.setText(cliente.getNombreCliente());
			holder.txtSubCanal.setText(String.format("%s - %s",
					cliente.getSubCanal(), cliente.getSubCanalDes()));
			holder.txtRuta.setText(cliente.getRuta());
			holder.txtFecCreacion.setText(cliente.getFechaCreacion());
			holder.txtFecActualizacion.setText(cliente.getFechaActualizacion());
			holder.txtFecSuspencion.setText(cliente.getFechaSuspencion());

			holder.imgDireccion = (ImageButton) convertView.findViewById(R.id.btn_buscar);
			holder.imgEquipoFrio = (ImageButton) convertView.findViewById(R.id.btn_equipofrio);
			holder.imgAddCliente = (ImageButton) convertView.findViewById(R.id.btn_agregar);
			holder.imgProfit = (ImageButton) convertView.findViewById(R.id.btn_profit);
			holder.imgDatosComerciales = (ImageButton) convertView.findViewById(R.id.btn_profitDatosComerciales);
			holder.imgAvanceResumen = (ImageButton) convertView.findViewById(R.id.btn_profitAvanceResumen);
			
			double lat = cliente.getLatitud();
			double lng = cliente.getLongitud();

			if (lat != 0 && lng != 0) {
				holder.imgDireccion.setVisibility(ImageButton.VISIBLE);
				holder.imgAddCliente.setVisibility(ImageButton.VISIBLE);
			}

			holder.imgDireccion.setOnClickListener(new OnClickListener() {
				ClienteResumenTO clienteTemporal = (ClienteResumenTO) getItem(position);

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent direccionCliente = new Intent(context,DireccionActivity.class);
					direccionCliente.putExtra(DireccionActivity.LATITUD_KEY,clienteTemporal.getLatitud());
					direccionCliente.putExtra(DireccionActivity.LONGITUD_KEY,clienteTemporal.getLongitud());
					direccionCliente.putExtra(DireccionActivity.DIRECCION_KEY,clienteTemporal.getDireccion());
					context.startActivity(direccionCliente);
				}
			});

			if (cliente.isEquiposFrio()) {

				holder.imgEquipoFrio.setOnClickListener(new OnClickListener() {
					ClienteResumenTO clienteTemporal = (ClienteResumenTO) getItem(position);

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						Intent consultarEquipoFrioActivity = new Intent(
								context, ConsultarEquipoFrioActivity.class);
						consultarEquipoFrioActivity.putExtra(
								ProfitHistoryActivity.CODIGO_CLIENTE_KEY,
								clienteTemporal.getCodigoCliente());
						consultarEquipoFrioActivity.putExtra(
								ProfitHistoryActivity.CLIENTE_KEY,
								clienteTemporal.getRazonSocial());
						context.startActivity(consultarEquipoFrioActivity);

					}
				});
			} else {
				holder.imgEquipoFrio.setVisibility(ImageButton.GONE);
			}

			holder.imgAddCliente.setOnClickListener(new OnClickListener() {
				ClienteResumenTO clienteTemporal = (ClienteResumenTO) getItem(position);

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent registrarCliente = new Intent(context,RegistrarClienteActivity.class);
					
					
					registrarCliente.putExtra(RegistrarClienteActivity.CLIENTE_CODIGO_KEY,clienteTemporal.getCodigoCliente());
					registrarCliente.putExtra(RegistrarClienteActivity.CODIGO_CLIENTE_ACCION, RegistrarClienteActivity.CLIENTE_CARGAR_FROM_WS);
					registrarCliente.putExtra(RegistrarClienteActivity.CLIENTE_REFERENCIA_ID_KEY,clienteTemporal.getCodigoCliente());
					registrarCliente.putExtra(RegistrarClienteActivity.CLIENTE_REFERENCIA_RAZONSOCIAL_KEY,clienteTemporal.getRazonSocial());
					context.startActivity(registrarCliente);
				}
			});

			holder.imgProfit.setOnClickListener(new OnClickListener() {
				ClienteResumenTO clienteTemporal = (ClienteResumenTO) getItem(position);

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent profit = new Intent(context,ProfitHistoryActivity.class);
					profit.putExtra(ProfitHistoryActivity.CODIGO_CLIENTE_KEY,clienteTemporal.getCodigoCliente());
					profit.putExtra(ProfitHistoryActivity.CLIENTE_KEY,clienteTemporal.getRazonSocial());
					context.startActivity(profit);
				}
			});

			holder.imgDatosComerciales.setOnClickListener(new OnClickListener() {
						ClienteResumenTO clienteTemporal = (ClienteResumenTO) getItem(position);

						@Override
						public void onClick(View v) {
							// TODO Auto-generated method stub
							Intent profit = new Intent(context,ProfitHistoryDatosComercialesActivity.class);
							profit.putExtra(ProfitHistoryDatosComercialesActivity.CODIGO_CLIENTE_KEY,clienteTemporal.getCodigoCliente());
							profit.putExtra(ProfitHistoryDatosComercialesActivity.CLIENTE_KEY,clienteTemporal.getRazonSocial());
							profit.putExtra(ProfitHistoryDatosComercialesActivity.TIPO_KEY,ProfitHistoryDatosComercialesActivity.TIPO_DATOS_COMERCIALES);
							context.startActivity(profit);
						}
					});

			holder.imgAvanceResumen.setOnClickListener(new OnClickListener() {
				ClienteResumenTO clienteTemporal = (ClienteResumenTO) getItem(position);

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent profit = new Intent(context,ProfitHistoryDatosComercialesActivity.class);
					profit.putExtra(ProfitHistoryDatosComercialesActivity.CODIGO_CLIENTE_KEY,clienteTemporal.getCodigoCliente());
					profit.putExtra(ProfitHistoryDatosComercialesActivity.CLIENTE_KEY,clienteTemporal.getRazonSocial());
					profit.putExtra(ProfitHistoryDatosComercialesActivity.TIPO_KEY,ProfitHistoryDatosComercialesActivity.TIPO_AVANCE_RESUMEN);
					context.startActivity(profit);
				}
			});

			return convertView;
		}

		static class ViewHolder {
			TextView txtRazonSocial;
			TextView txtRuc;
			TextView txtDni;
			TextView txtCodigo;
			TextView txtDireccion;
			TextView txtCda;
			TextView txtRepresentante;
			TextView txtSubCanal;
			TextView txtRuta;
			TextView txtFecCreacion;
			TextView txtFecActualizacion;
			TextView txtFecSuspencion;
			ImageButton imgDireccion;
			ImageButton imgEquipoFrio;
			ImageButton imgAddCliente;
			ImageButton imgProfit;
			ImageButton imgDatosComerciales;
			ImageButton imgAvanceResumen;

		}

		@Override
		public Filter getFilter() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			// return data.length;
			if (clientes == null) {
				return 0;
			} else {
				return clientes.size();
			}
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return clientes.get(position);
		}

	}

}
