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
import pe.lindley.red.to.ComunicacionTO;
import pe.lindley.red.ws.service.ConsultarComunicacionProxy;
import pe.lindley.util.ListActivityBase;
import roboguice.inject.InjectExtra;
import roboguice.inject.InjectView;

public class ComunicacionActivity extends ListActivityBase {

	public static final String CODIGO_CLIENTE_KEY = "codigo_cliente";
	public static final String CLIENTE_KEY = "cliente_descripcion";
	public static final String FECHA_ENCUESTA_KEY = "fecha_encuesta";
	
	@InjectExtra(CODIGO_CLIENTE_KEY) public static String cliente_codigo;
	@InjectExtra(CLIENTE_KEY) public static String cliente_descripcion;
	@InjectExtra(FECHA_ENCUESTA_KEY) public static String fecha_encuesta;	
	 
	@InjectView(R.id.actionBar)  		ActionBar 	mActionBar;
	@Inject ConsultarComunicacionProxy consultarComunicacionProxy;
	private EfficientAdapter adap;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(pe.lindley.activity.R.layout.red_comunicacion_activity);
		mActionBar.setTitle(R.string.red_material_pop_title);
	    mActionBar.setHomeLogo(R.drawable.header_logo);
	    mActionBar.setSubTitle(cliente_descripcion);
		processAsync();
	}

	@Override
	protected void process() {
		// TODO Auto-generated method stub
		fecha_encuesta = fecha_encuesta.replace("/","");
		consultarComunicacionProxy.setAnioMes(fecha_encuesta);
		consultarComunicacionProxy.setCodigoCliente(cliente_codigo);
		consultarComunicacionProxy.execute();
	}

	@Override
	protected void processOk() {
		// TODO Auto-generated method stub
		boolean isExito = consultarComunicacionProxy.isExito();
		if (isExito) {
			int status = consultarComunicacionProxy.getResponse().getStatus();
			if(status==0){
				List<ComunicacionTO> comunicacion = consultarComunicacionProxy.getResponse().getData();
				adap = new EfficientAdapter(this, comunicacion);
				setListAdapter(adap);
			}
			else
			{
				showToast(consultarComunicacionProxy.getResponse().getDescripcion());
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
		private List<ComunicacionTO> comunicacion;

		public EfficientAdapter(Context context, List<ComunicacionTO> comunicacion) {
			// Cache the LayoutInflate to avoid asking for a new one each time.
			mInflater = LayoutInflater.from(context);
			//this.context = context;
			this.comunicacion = comunicacion;
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
		ComunicacionTO comunicacionTO = (ComunicacionTO) getItem(position);
		ViewHolder holder;

		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.red_comunicacion_content, null);

			// Creates a ViewHolder and store references to the two children
			// views
			// we want to bind data to.
			holder = new ViewHolder();
			holder.txtMaterial = (TextView) convertView.findViewById(R.id.txtMaterial);
			holder.txtEstado = (TextView) convertView.findViewById(R.id.txtEstado);
			holder.txtUbic = (TextView) convertView.findViewById(R.id.txtUbic);
			holder.txtInicio = (TextView) convertView.findViewById(R.id.txtInicio);
			holder.txtFin = (TextView) convertView.findViewById(R.id.txtFin);
			
			convertView.setTag(holder);
		} else {
			// Get the ViewHolder back to get fast access to the TextView
			// and the ImageView.
			holder = (ViewHolder) convertView.getTag();
		}
		
		
		holder.txtMaterial.setText(comunicacionTO.getMaterial());
		holder.txtEstado.setText(comunicacionTO.getEstado());
		holder.txtUbic.setText(comunicacionTO.getUbicacion());
		holder.txtInicio.setText(comunicacionTO.getInicio());
		holder.txtFin.setText(comunicacionTO.getFin());
		
		return convertView;
	}

	static class ViewHolder {
		TextView txtMaterial;
		TextView txtEstado;
		TextView txtUbic;
		TextView txtInicio;
		TextView txtFin;
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
		if (comunicacion == null) {
			return 0;
		} else {
			return comunicacion.size();
		}
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return comunicacion.get(position);
	}

	}
}