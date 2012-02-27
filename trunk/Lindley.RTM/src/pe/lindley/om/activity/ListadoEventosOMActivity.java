package pe.lindley.om.activity;

import java.util.List;

import com.google.inject.Inject;
import com.thira.examples.actionbar.widget.ActionBar;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.TextView;
import pe.lindley.activity.R;
import pe.lindley.om.negocio.OrdenTrabajoBLL;
import pe.lindley.om.to.EventoTO;
import pe.lindley.util.ListActivityBase;
import roboguice.inject.InjectView;

public class ListadoEventosOMActivity extends ListActivityBase {

	public static final String ORDEN_TRABAJO_ID = "ORDEN_TRABAJO_ID";
	public static final String ORDEN_TRABAJO_CODIGO = "ORDEN_TRABAJO_CODIGO";
	public static final String CLIENTE_CODIGO_KEY="codigo_cliente";
	public static final String CLIENTE_RAZONSOCIAL_KEY="cliente_descripcion";
	
	@Inject OrdenTrabajoBLL ordenTrabajoBLL;
	
	private String cliente_codigo = null;
	private String cliente_descripcion = null;
	
	private long ordenTrabajoId;
	private String ordenTrabajoCodigo;
	@InjectView(R.id.actionBar)  		ActionBar 	mActionBar;
	@InjectView(R.id.btn_agregar)		ImageButton btnAdd;
	
	
	private EfficientAdapter adapter;
	private List<EventoTO> eventos;
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		processAsync();
		super.onResume();
		
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.listadoeventosom_activity);
		
		
		
		Intent intent = this.getIntent();
		ordenTrabajoId = intent.getLongExtra(ORDEN_TRABAJO_ID, 0);
		cliente_codigo = intent.getStringExtra(CLIENTE_CODIGO_KEY);
		cliente_descripcion = intent.getStringExtra(CLIENTE_RAZONSOCIAL_KEY);
		ordenTrabajoCodigo = intent.getStringExtra(CLIENTE_CODIGO_KEY);
		
		 mActionBar.setTitle(R.string.listadoeventosoma_ctivity_title);
	     mActionBar.setHomeLogo(R.drawable.header_logo);
	     mActionBar.setSubTitle(String.format("%s %s",ordenTrabajoCodigo ,cliente_descripcion) );
	}
	
	private String estado;
	@Override
	protected void process() {
		eventos = ordenTrabajoBLL.listEventos(ordenTrabajoId);
		estado = ordenTrabajoBLL.getEstadoOrden(ordenTrabajoId);
	}
	
	@Override
	protected void processOk() {
		adapter = new EfficientAdapter(this, eventos);
		setListAdapter(adapter);
		
		if(OrdenTrabajoBLL.CERRADO.equalsIgnoreCase(estado)){
			btnAdd.setVisibility(View.GONE);
		}
		
		super.processOk();
	}
	
	public void btnadd_onclick(View view){
		Context context = this;
		Intent intent = new Intent(context,RegistrarEventoOMActivity.class);
		intent.putExtra(RegistrarEventoOMActivity.ORDEN_TRABAJO_ID,ordenTrabajoId);
		intent.putExtra(RegistrarEventoOMActivity.CLIENTE_CODIGO_KEY,cliente_codigo);
		intent.putExtra(RegistrarEventoOMActivity.CLIENTE_RAZONSOCIAL_KEY,cliente_descripcion);
		
		context.startActivity(intent);
	}
	public static class EfficientAdapter extends BaseAdapter implements Filterable {
		private LayoutInflater mInflater;
		private List<EventoTO> eventos;
		//private Context context;
		
		public EfficientAdapter(Context context, List<EventoTO> eventos) {
			// Cache the LayoutInflate to avoid asking for a new one each time.
			mInflater = LayoutInflater.from(context);
			this.eventos = eventos;
			//this.context = context;
		}

	public EfficientAdapter(Context context) {
		// Cache the LayoutInflate to avoid asking for a new one each time.
		mInflater = LayoutInflater.from(context);
		//this.context = context;
	}

	/**
	 * Make a view to hold each row.
	 * 
	 * @see android.widget.ListAdapter#getView(int, android.view.View,
	 *      android.view.ViewGroup)
	 */
	public View getView(final int position, View convertView,ViewGroup parent) {
		EventoTO eventoTO = (EventoTO) getItem(position);
		ViewHolder holder;

		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.listadoeventosom_content, null);

			// Creates a ViewHolder and store references to the two children
			// views
			// we want to bind data to.
			holder = new ViewHolder();
		
			holder.txtMotivo = (TextView) convertView.findViewById(R.id.txtMotivo);
			holder.txtCreadoPor = (TextView) convertView.findViewById(R.id.txtCreadoPor);
			holder.txtFechaCreacion = (TextView) convertView.findViewById(R.id.txtFechaCreacion);
			holder.txtHoraCreacion = (TextView) convertView.findViewById(R.id.txtHoraCreacion);
			holder.txtAsignadoA = (TextView) convertView.findViewById(R.id.txtAsignadoA);
			holder.txtObservacion = (TextView) convertView.findViewById(R.id.txtObservacion);

			
			convertView.setTag(holder);
		} else {

			holder = (ViewHolder) convertView.getTag();
		}
		
		holder.txtMotivo.setText(eventoTO.getDsTpM()) ;
		holder.txtCreadoPor.setText(eventoTO.getDsUsC()) ;
		holder.txtFechaCreacion.setText(eventoTO.getFeCre()) ;
		holder.txtHoraCreacion.setText(eventoTO.getHrCre()) ;
		holder.txtAsignadoA.setText(eventoTO.getDsUsA()) ;
		holder.txtObservacion.setText(eventoTO.getDsObs()) ;
		
		
		return convertView;
	}

	static class ViewHolder {

		TextView txtMotivo;
		TextView txtCreadoPor;
		TextView txtFechaCreacion;
		TextView txtHoraCreacion;
		TextView txtAsignadoA;
		TextView txtObservacion;
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
		if (eventos == null) {
			return 0;
		} else {
			return eventos.size();
		}
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return eventos.get(position);
	}

	}


}
