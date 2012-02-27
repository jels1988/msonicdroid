package pe.lindley.om.activity;

import java.util.ArrayList;
import java.util.List;

import com.google.inject.Inject;
import com.thira.examples.actionbar.widget.ActionBar;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.TextView;
import pe.lindley.activity.R;
import pe.lindley.equipofrio.activity.ConsultarEquipoFrioActivity;
import pe.lindley.om.negocio.ClienteBLL;
import pe.lindley.om.to.ClienteTO;
import pe.lindley.profit.activity.ProfitHistoryActivity;
import pe.lindley.profit.activity.ProfitHistoryDatosComercialesActivity;
import pe.lindley.util.ListActivityBase;
import roboguice.inject.InjectResource;
import roboguice.inject.InjectView;

public class ConsultarClienteOMActivity extends ListActivityBase {

	@InjectView(R.id.actionBar)  	ActionBar 	mActionBar;
	@InjectView(R.id.txtCodigo)		TextView txtCodigo;
	@InjectView(R.id.txtRazonSocial)TextView txtRazonSocial;
	@InjectResource(R.string.consultar_cliente_om_activity_empty) String parametros_empty;
	
	@Inject ClienteBLL clienteBLL;
	
	private ArrayList<ClienteTO> clientes;
	private EfficientAdapter adap;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.consultarclienteom_activity);
		

		mActionBar.setTitle(R.string.consultar_cliente_om_activity_title);
		mActionBar.setHomeLogo(R.drawable.header_logo);
		
	}
	
	@Override
	protected boolean executeAsyncPre() {
		// TODO Auto-generated method stub
		boolean isCodigo = txtCodigo.getText().toString().trim().equalsIgnoreCase("");
		boolean isRazonSocial = txtRazonSocial.getText().toString().trim().equalsIgnoreCase("");
		

		if (isCodigo && isRazonSocial) {
			txtCodigo.setError(parametros_empty);
			txtRazonSocial.setError(parametros_empty);
			return false;
		} else {
			txtCodigo.setError(null);
			txtRazonSocial.setError(null);
		}

		return true;

	}
	
	@Override
	protected void process() {
		// TODO Auto-generated method stub
		String codigo = txtCodigo.getText().toString();
		String razonSocial = txtRazonSocial.getText().toString();
		
		clientes = clienteBLL.list(codigo, razonSocial);

	}
	
	@Override
	protected void processOk() {
		adap = new EfficientAdapter(this, clientes);
		setListAdapter(adap);
		super.processOk();
	}
	public void btnbuscar_onclick(View view) {
		processAsync();
	}

	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.consultarclienteom_menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
		switch (item.getItemId()) {
			case R.id.mnuParametrosGenerales: {
				Intent intent = new Intent(this,DescargarParametrosActivity.class);
				startActivity(intent);
				return true;
			}
			
			case R.id.mnuClientes: {
				Intent intent = new Intent(this,DescargarClientesActivity.class);
				startActivity(intent);
				return true;
			}
			
			case R.id.mnuOportunidadesMejora: {
				Intent intent = new Intent(this,DescargarOrdenesOMActivity.class);
				startActivity(intent);
				return true;
			}
			
			case R.id.mnuPendientes: {
				Intent intent = new Intent(this,ListadoOrdenesOMPendientesActivity.class);
				startActivity(intent);
				return true;
			}
		}
		return true;
	}
	
	
public static class EfficientAdapter extends BaseAdapter implements Filterable {
	private LayoutInflater mInflater;
	private Context context;
	private List<ClienteTO> clientes;

	public EfficientAdapter(Context context, List<ClienteTO> clientes) {
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
	ClienteTO cliente = (ClienteTO) getItem(position);
	ViewHolder holder;

	if (convertView == null) {
		convertView = mInflater.inflate(
				R.layout.consultarclienteom_content, null);

		// Creates a ViewHolder and store references to the two children
		// views
		// we want to bind data to.
		holder = new ViewHolder();
		holder.txtRazonSocial = (TextView) convertView.findViewById(R.id.txtRazonSocial);
		holder.txtCodigo = (TextView) convertView.findViewById(R.id.txtCodigo);
		holder.txtDireccion = (TextView) convertView.findViewById(R.id.txtDireccion);
		holder.txtCanal = (TextView) convertView.findViewById(R.id.txtCanal);
		holder.txtSubCanal = (TextView) convertView.findViewById(R.id.txtSubCanal);
		holder.txtTamanio = (TextView) convertView.findViewById(R.id.txtTamanio);
		holder.txtPotencial = (TextView) convertView.findViewById(R.id.txtPotencial);
		holder.txtModalidad = (TextView) convertView.findViewById(R.id.txtModalidad);
		holder.txtRutaVenta = (TextView) convertView.findViewById(R.id.txtRutaVenta);
		holder.txtRutaMercaderista = (TextView) convertView.findViewById(R.id.txtRutaMercaderista);
		holder.txtRutaDesarrollo = (TextView) convertView.findViewById(R.id.txtRutaDesarrollo);
		holder.txtRutaMaestro = (TextView) convertView.findViewById(R.id.txtRutaMaestro);
		
		holder.imgOportunidadMejora = (ImageButton) convertView.findViewById(R.id.btn_oportunidadM);
		holder.imgEquipoFrio = (ImageButton) convertView.findViewById(R.id.btn_equipofrio);
		holder.imgProfit = (ImageButton) convertView.findViewById(R.id.btn_profit);
		holder.imgDatosComerciales = (ImageButton) convertView.findViewById(R.id.btn_profitDatosComerciales);
		holder.imgAvanceResumen = (ImageButton) convertView.findViewById(R.id.btn_profitAvanceResumen);

		convertView.setTag(holder);
	} else {

		holder = (ViewHolder) convertView.getTag();
	}
	
	
	holder.txtRazonSocial.setText(cliente.getRazonSocial());
	holder.txtCodigo.setText(cliente.getCodigo());
	holder.txtDireccion.setText(cliente.getDireccion());
	holder.txtCanal.setText(cliente.getCanal());
	holder.txtTamanio.setText(cliente.getTamanio());
	holder.txtPotencial.setText(cliente.getPotencial());
	holder.txtModalidad.setText(cliente.getModoservicio());
	holder.txtRutaVenta.setText(cliente.getRutaVentas());
	holder.txtRutaMercaderista.setText(cliente.getRutaMecaderista());
	holder.txtRutaDesarrollo.setText(cliente.getRutaDesarrollador());
	holder.txtRutaMaestro.setText(cliente.getRutaTecnicoMan());
	
	holder.imgOportunidadMejora.setOnClickListener(new OnClickListener() {
		ClienteTO clienteTO = (ClienteTO)getItem(position);
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent consultarOportunidadesMejoraActivity = new Intent(context, BuscarOrdenesOMActivity.class);
			consultarOportunidadesMejoraActivity.putExtra(BuscarOrdenesOMActivity.CLIENTE_CODIGO_KEY,clienteTO.getCodigo());
			consultarOportunidadesMejoraActivity.putExtra(BuscarOrdenesOMActivity.CLIENTE_RAZONSOCIAL_KEY,clienteTO.getRazonSocial());
			context.startActivity(consultarOportunidadesMejoraActivity);
			
		}
	});
	holder.imgEquipoFrio.setOnClickListener(new OnClickListener() {
		ClienteTO clienteTO = (ClienteTO)getItem(position);
		
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			Intent consultarEquipoFrioActivity = new Intent(context, ConsultarEquipoFrioActivity.class);
			consultarEquipoFrioActivity.putExtra(ProfitHistoryActivity.CODIGO_CLIENTE_KEY,clienteTO.getCodigo());
			consultarEquipoFrioActivity.putExtra(ProfitHistoryActivity.CLIENTE_KEY,clienteTO.getRazonSocial());
			context.startActivity(consultarEquipoFrioActivity);
		}
	});
	
	holder.imgProfit.setOnClickListener(new OnClickListener() {
		
		ClienteTO clienteTO = (ClienteTO)getItem(position);
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent profit = new Intent(context,ProfitHistoryActivity.class);
			profit.putExtra(ProfitHistoryActivity.CODIGO_CLIENTE_KEY,clienteTO.getCodigo());
			profit.putExtra(ProfitHistoryActivity.CLIENTE_KEY,clienteTO.getRazonSocial());
			context.startActivity(profit);
		}
		
	});
	
	holder.imgDatosComerciales.setOnClickListener(new OnClickListener() {
		ClienteTO clienteTO = (ClienteTO)getItem(position);
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			
			Intent profit = new Intent(context,ProfitHistoryDatosComercialesActivity.class);
			profit.putExtra(ProfitHistoryDatosComercialesActivity.CODIGO_CLIENTE_KEY,clienteTO.getCodigo());
			profit.putExtra(ProfitHistoryDatosComercialesActivity.CLIENTE_KEY,clienteTO.getRazonSocial());
			profit.putExtra(ProfitHistoryDatosComercialesActivity.TIPO_KEY,ProfitHistoryDatosComercialesActivity.TIPO_DATOS_COMERCIALES);
			context.startActivity(profit);
			
		}
	});
	
	holder.imgAvanceResumen.setOnClickListener(new OnClickListener() {
		ClienteTO clienteTO = (ClienteTO)getItem(position);
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent profit = new Intent(context,ProfitHistoryDatosComercialesActivity.class);
			profit.putExtra(ProfitHistoryDatosComercialesActivity.CODIGO_CLIENTE_KEY,clienteTO.getCodigo());
			profit.putExtra(ProfitHistoryDatosComercialesActivity.CLIENTE_KEY,clienteTO.getRazonSocial());
			profit.putExtra(ProfitHistoryDatosComercialesActivity.TIPO_KEY,ProfitHistoryDatosComercialesActivity.TIPO_AVANCE_RESUMEN);
			context.startActivity(profit);
		}
		
	});
	
	return convertView;
}

static class ViewHolder {
	TextView txtRazonSocial;
	TextView txtCodigo;
	TextView txtDireccion;
	TextView txtCanal;
	TextView txtSubCanal;
	TextView txtTamanio;
	TextView txtPotencial;
	TextView txtModalidad;
	TextView txtRutaVenta;
	TextView txtRutaMercaderista;
	TextView txtRutaDesarrollo;
	TextView txtRutaMaestro;
	
	ImageButton imgOportunidadMejora;
	ImageButton imgEquipoFrio;
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
