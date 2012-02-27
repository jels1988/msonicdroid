package pe.lindley.red.activity;

import java.util.List;

import com.google.inject.Inject;
import com.thira.examples.actionbar.widget.ActionBar;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import pe.lindley.activity.R;
import pe.lindley.red.to.OrdenTrabajoTO;
import pe.lindley.red.ws.service.ConsultarOrdenTrabajoProxy;
import pe.lindley.util.ArrayUtil;
import pe.lindley.util.ListActivityBase;
import roboguice.inject.InjectExtra;
import roboguice.inject.InjectView;

public class OrdenTrabajoActivity extends ListActivityBase {

	public static final String CODIGO_CLIENTE_KEY = "codigo_cliente";
	public static final String CLIENTE_KEY = "cliente_descripcion";
	public static final String FECHA_ENCUESTA_KEY = "fecha_encuesta";
	
	@InjectExtra(CODIGO_CLIENTE_KEY) public static String cliente_codigo;
	@InjectExtra(CLIENTE_KEY) public static String cliente_descripcion;
	@InjectExtra(FECHA_ENCUESTA_KEY) public static String fecha_encuesta;	
	 
	@InjectView(R.id.actionBar)  		ActionBar 	mActionBar;
	@Inject ConsultarOrdenTrabajoProxy consultarOrdenTrabajoProxy;
	private EfficientAdapter adap;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(pe.lindley.activity.R.layout.red_ordentrabajo_activity);
		mActionBar.setTitle(R.string.red_orden_trabajo_title);
	    mActionBar.setHomeLogo(R.drawable.header_logo);
	    mActionBar.setSubTitle(cliente_descripcion);
		processAsync();
	}

	@Override
	protected void process() {
		// TODO Auto-generated method stub
		fecha_encuesta = fecha_encuesta.replace("/","").substring(0,6);
		consultarOrdenTrabajoProxy.setAnioMes(fecha_encuesta);		
		consultarOrdenTrabajoProxy.setCodigoCliente(cliente_codigo);
		consultarOrdenTrabajoProxy.execute();
	}

	@Override
	protected void processOk() {
		// TODO Auto-generated method stub
		boolean isExito = consultarOrdenTrabajoProxy.isExito();
		if (isExito) {
			int status = consultarOrdenTrabajoProxy.getResponse().getStatus();
			if(status==0){
				List<OrdenTrabajoTO> ordenTrabajo = consultarOrdenTrabajoProxy.getResponse().getData();
				adap = new EfficientAdapter(this, ordenTrabajo);
				setListAdapter(adap);
			}
			else
			{
				showToast(consultarOrdenTrabajoProxy.getResponse().getDescripcion());
			}
		}
		super.processOk();
	}

	@Override
	protected void processError() {
		// TODO Auto-generated method stub
		super.processError();
		showToast(error_generico_process);
	}
	public static class EfficientAdapter extends BaseAdapter implements Filterable {
		private LayoutInflater mInflater;
		//private Context context;
		private List<OrdenTrabajoTO> ordenTrabajo;

		public EfficientAdapter(Context context, List<OrdenTrabajoTO> ordenTrabajo) {
			// Cache the LayoutInflate to avoid asking for a new one each time.
			mInflater = LayoutInflater.from(context);
			//this.context = context;
			this.ordenTrabajo = ordenTrabajo;
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
	public View getView(final int position, View convertView, ViewGroup parent) {
		OrdenTrabajoTO ordenTrabajoTO = (OrdenTrabajoTO) getItem(position);
		ViewHolder holder;

		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.red_ordentrabajo_content, null);

			// Creates a ViewHolder and store references to the two children
			// views
			// we want to bind data to.
			holder = new ViewHolder();
			holder.txtOrden = (TextView) convertView.findViewById(R.id.txtOrden);
			holder.txtPEjecucion = (TextView) convertView.findViewById(R.id.txtPEjecucion);
			holder.txtPMax = (TextView) convertView.findViewById(R.id.txtPMax);
			
			convertView.setTag(holder);
		} else {
			// Get the ViewHolder back to get fast access to the TextView
			// and the ImageView.
			holder = (ViewHolder) convertView.getTag();
		}
		
		
		holder.txtOrden.setText(ordenTrabajoTO.getOrdenTrabajo());
		holder.txtPEjecucion.setText(ArrayUtil.round(ordenTrabajoTO.getPuntosEjecutados(),2) + "");
		holder.txtPMax.setText(ArrayUtil.round(ordenTrabajoTO.getPuntosMaximos(),2) + "");
		
		return convertView;
	}

	static class ViewHolder {
		TextView txtOrden;
		TextView txtPEjecucion;
		TextView txtPMax;
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
		if (ordenTrabajo == null) {
			return 0;
		} else {
			return ordenTrabajo.size();
		}
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return ordenTrabajo.get(position);
	}

	}
}