package pe.lindley.sacc.activity;

import java.util.List;
import com.google.inject.Inject;
import com.thira.examples.actionbar.widget.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.TextView;
import pe.lindley.activity.DireccionActivity;
import pe.lindley.activity.R;
import pe.lindley.activity.RTMApplication;
import pe.lindley.ficha.negocio.OpcionMultipleBLL;
import pe.lindley.lanzador.to.ClienteResumenTO;
import pe.lindley.lanzador.ws.service.ConsultarClienteProxy;
import pe.lindley.util.ListActivityBase;
import roboguice.inject.InjectResource;
import roboguice.inject.InjectView;

public class ConsultarClienteActivity extends ListActivityBase {
	
	@Inject ConsultarClienteProxy consultarClienteProxy;
	@InjectView(R.id.actionBar)ActionBar mActionBar;
	@InjectView(R.id.txtCodigo)TextView txtCodigo;
	@InjectView(R.id.txtRuc)TextView txtRuc;
	@InjectView(R.id.txtDni)TextView txtDni;
	@InjectResource(R.string.ficha_consultar_cliente_activity_empty) String parametros_empty;
	@InjectResource(R.string.consultar_cliente_activity_sub_title) String subtitulo;
	@InjectResource(R.string.consultar_cliente_activity_title) String titulo;
	private EfficientAdapter adap;
	
	@Inject OpcionMultipleBLL parametroBLL;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.sacc_consultar_cliente_activity);
				
		mActionBar.setTitle(titulo);
		mActionBar.setSubTitle(subtitulo);
		mActionBar.setHomeLogo(R.drawable.header_logo);
		txtCodigo.addTextChangedListener(txtCodigoTextWatcher);
		txtRuc.addTextChangedListener(txtRucTextWatcher);
		txtDni.addTextChangedListener(txtDniTextWatcher);
	}	
	
	@Override
	protected boolean executeAsyncPre() {
		// TODO Auto-generated method stub
		boolean isCodigo = txtCodigo.getText().toString().trim().equalsIgnoreCase("");
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
				RTMApplication application = (RTMApplication)contextProvider.get().getApplicationContext();
				application.setClienteTO(clientes.get(0));
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
	
	
	public void btnbuscar_onclick(View view){
		processAsync();	
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
@Override
public View getView(final int position, View convertView,
		ViewGroup parent) {
	ClienteResumenTO cliente = (ClienteResumenTO) getItem(position);
	ViewHolder holder;

	if (convertView == null) {
		convertView = mInflater.inflate(
				R.layout.sacc_consultar_cliente_content, null);

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
		holder.txtFecActualizacion = (TextView) convertView.findViewById(R.id.txtFecActualizacion);
		holder.txtFecSuspencion = (TextView) convertView.findViewById(R.id.txtFecSuspencion);
		
		
		holder.imgDireccion = (ImageButton) convertView.findViewById(R.id.btn_buscar);
		holder.imgSACC = (ImageButton) convertView.findViewById(R.id.btn_sacc);
		
		
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
	holder.txtSubCanal.setText(String.format("%s - %s",cliente.getSubCanal(), cliente.getSubCanalDes()));
	holder.txtRuta.setText(cliente.getRuta());
	holder.txtFecCreacion.setText(cliente.getFechaCreacion());
	holder.txtFecActualizacion.setText(cliente.getFechaActualizacion());
	holder.txtFecSuspencion.setText(cliente.getFechaSuspencion());


	
	double lat = cliente.getLatitud();
	double lng = cliente.getLongitud();

	if (lat != 0 && lng != 0) {
		holder.imgDireccion.setVisibility(View.VISIBLE);
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

	holder.imgSACC.setOnClickListener(new OnClickListener() {
		ClienteResumenTO clienteTemporal = (ClienteResumenTO) getItem(position);

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent fichaCliente = new Intent(context,MostrarContactoActivity.class);
			fichaCliente.putExtra(MostrarContactoActivity.CODIGO_CLIENTE, Integer.parseInt(clienteTemporal.getCodigoCliente()));
			fichaCliente.putExtra(MostrarContactoActivity.NOMBRE_CLIENTE, clienteTemporal.getRazonSocial());
			context.startActivity(fichaCliente);
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
	ImageButton imgSACC;
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
