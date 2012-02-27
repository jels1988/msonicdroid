package pe.lindley.om.activity;

import java.util.List;

import com.google.inject.Inject;
import com.thira.examples.actionbar.widget.ActionBar;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.TextView;
import pe.lindley.activity.R;
import pe.lindley.om.negocio.OrdenTrabajoBLL;
import pe.lindley.om.to.OrdenTrabajoTO;
import pe.lindley.util.ListActivityBase;
import roboguice.inject.InjectView;

public class ListadoOrdenesOMPendientesActivity extends ListActivityBase {
	
	@InjectView(R.id.actionBar)  		ActionBar 	mActionBar;
	
	@Inject OrdenTrabajoBLL ordenTrabajoBLL;
	
	private EfficientAdapter adapter;
	private List<OrdenTrabajoTO> ordenes;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.listadoordenesompendientes_activity);
		
		 mActionBar.setTitle(R.string.listadoordenesompendientes_activity_title);
	     mActionBar.setHomeLogo(R.drawable.header_logo);
	     
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		processAsync();
		super.onResume();
		
	}
	@Override
	protected void process() {
		ordenes = ordenTrabajoBLL.listPendientes();
	}
	
	@Override
	protected void processOk() {
		adapter = new EfficientAdapter(this, ordenes);
		setListAdapter(adapter);
		super.processOk();
	}
	/*
	@Override
	protected void process(int accion) {
		if(CARGAR_ORDEN==accion){
			ordenOM = ordenTrabajoBLL.listWithEventos(ordenOMId);
		}
	}
	
	public void cargarOrdenOM(){
		processAsync(CARGAR_ORDEN);
	}
	private long ordenOMId;
	
	public long getOrdenOMId() {
		return ordenOMId;
	}

	public void setOrdenOMId(long ordenOMId) {
		this.ordenOMId = ordenOMId;
	}
	*/

	public static class EfficientAdapter extends BaseAdapter implements Filterable {
		private LayoutInflater mInflater;
		private List<OrdenTrabajoTO> ordenes;
		private Context context;

		public EfficientAdapter(Context context, List<OrdenTrabajoTO> ordenes) {
			// Cache the LayoutInflate to avoid asking for a new one each time.
			mInflater = LayoutInflater.from(context);
			this.ordenes = ordenes;
			this.context = context;
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
		OrdenTrabajoTO ordenTrabajoTO = (OrdenTrabajoTO) getItem(position);
		ViewHolder holder;

		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.listadoordenesompendientes_content, null);

			// Creates a ViewHolder and store references to the two children
			// views
			// we want to bind data to.
			holder = new ViewHolder();
		
			holder.txtNumeroOrden = (TextView) convertView.findViewById(R.id.txtNumeroOrden);
			holder.txtTipoContacto = (TextView) convertView.findViewById(R.id.txtTipoContacto);
			holder.txtTipoOrden = (TextView) convertView.findViewById(R.id.txtTipoOrden);
			holder.txtSubTipoOrden = (TextView) convertView.findViewById(R.id.txtSubTipoOrden);
			holder.txtMotivo = (TextView) convertView.findViewById(R.id.txtMotivo);
			holder.txtPrioridad = (TextView) convertView.findViewById(R.id.txtPrioridad);
			holder.txtEstado = (TextView) convertView.findViewById(R.id.txtEstado);
			holder.txtAsignado = (TextView) convertView.findViewById(R.id.txtAsignado);
			holder.txtFecha = (TextView) convertView.findViewById(R.id.txtFecha);
			holder.btnUpload = (ImageButton)convertView.findViewById(R.id.btn_upload);
			holder.btnenviar = (ImageButton)convertView.findViewById(R.id.btn_enviar);
			convertView.setTag(holder);
		} else {

			holder = (ViewHolder) convertView.getTag();
		}
		
		final long idLocal = ordenTrabajoTO.getNuOrd();
		final long idServer = ordenTrabajoTO.getNuOrd2();
		if(idServer==0)
			holder.txtNumeroOrden.setText(String.format("%s",idLocal)) ;
		else
			holder.txtNumeroOrden.setText(String.format("%s",idServer)) ;
		
		holder.txtTipoContacto.setText(ordenTrabajoTO.getDsTpC()) ;
		holder.txtTipoOrden.setText(ordenTrabajoTO.getDsTpO()) ;
		holder.txtSubTipoOrden.setText(ordenTrabajoTO.getDsStO()) ;
		holder.txtMotivo.setText(ordenTrabajoTO.getDsMtO()) ;
		holder.txtPrioridad.setText(ordenTrabajoTO.getDsPri()) ;
		holder.txtEstado.setText(ordenTrabajoTO.getDsEsO()) ;
		holder.txtAsignado.setText(ordenTrabajoTO.getDsUsA()) ;
		holder.txtFecha.setText(ordenTrabajoTO.getFeCre()) ;
		
		holder.btnUpload.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				/*ListadoOrdenesOMPendientesActivity listadoOrdenesOMPendientesActivity = (ListadoOrdenesOMPendientesActivity)context;
				listadoOrdenesOMPendientesActivity.setOrdenOMId(idLocal);
				listadoOrdenesOMPendientesActivity.cargarOrdenOM();*/
				
				Intent intent = new Intent(context, EnviarOrdenesOMActivity.class);
				intent.putExtra(EnviarOrdenesOMActivity.TIPO_ACCION, EnviarOrdenesOMActivity.ACCION_ENVIAR_TODOS);
				context.startActivity(intent);
				
			}
		});
		
		holder.btnenviar.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View v) {
				String numeroOrden = null;
				if(idServer==0)
					numeroOrden = String.format("%s",idLocal) ;
				else
					numeroOrden = String.format("%s",idServer) ;
				Intent intent = new Intent(context, EnviarOrdenesOMActivity.class);				
				intent.putExtra(EnviarOrdenesOMActivity.ID_ORDEN, Integer.parseInt(numeroOrden));
				intent.putExtra(EnviarOrdenesOMActivity.TIPO_ACCION, EnviarOrdenesOMActivity.ACCION_ENVIAR_UNO);				
				context.startActivity(intent);				
			}
		});
		
		return convertView;
	}

	static class ViewHolder {

		TextView txtNumeroOrden;
		TextView txtTipoContacto;
		TextView txtTipoOrden;
		TextView txtSubTipoOrden;
		TextView txtMotivo;
		TextView txtPrioridad;
		TextView txtEstado;
		TextView txtAsignado;
		TextView txtFecha;
		ImageButton btnUpload;
		ImageButton btnenviar;
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
		if (ordenes == null) {
			return 0;
		} else {
			return ordenes.size();
		}
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return ordenes.get(position);
	}

	}

}
