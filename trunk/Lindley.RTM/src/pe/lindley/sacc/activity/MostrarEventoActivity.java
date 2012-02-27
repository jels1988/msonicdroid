package pe.lindley.sacc.activity;

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
import pe.lindley.sacc.to.EventoTO;
import pe.lindley.sacc.ws.service.ObtenerEventoProxy;
import pe.lindley.util.ListActivityBase;
import roboguice.inject.InjectExtra;
import roboguice.inject.InjectView;

public class MostrarEventoActivity extends ListActivityBase {

	public static final String CODIGO_CONTACTO = "cod_cliente";
	protected static final String NOMBRE_CLIENTE = "nom_cliente";
	private EfficientAdapter adap;
	
	@InjectView(R.id.actionBar)ActionBar mActionBar;
	@Inject ObtenerEventoProxy obtenerEventoProxy;
	@InjectExtra(CODIGO_CONTACTO) int codigo_contacto;
	@InjectExtra(NOMBRE_CLIENTE) String nombre_client;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sacc_mostrar_evento_activity);
		mActionBar.setTitle(R.string.sacc_mostrar_evento_title);		
		mActionBar.setSubTitle(nombre_client);
		mActionBar.setHomeLogo(R.drawable.header_logo);
		processAsync();
	}

	@Override
	protected void process() {
		// TODO Auto-generated method stub
		obtenerEventoProxy.setIdContacto(codigo_contacto);
		obtenerEventoProxy.execute();
	}

	@Override
	protected void processOk() {
		// TODO Auto-generated method stub
		boolean isExito = obtenerEventoProxy.isExito();
		if (isExito) {
			int status = obtenerEventoProxy.getResponse().getStatus();
			if (status == 0) {
				List<EventoTO> eventos = obtenerEventoProxy.getResponse().getListaEvento();
				adap = new EfficientAdapter(this, eventos);
				setListAdapter(adap);
			}
			else  {
				showToast(obtenerEventoProxy.getResponse().getDescripcion());
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
	
	public static class EfficientAdapter extends BaseAdapter implements Filterable {
	    private LayoutInflater mInflater;
	    //private Context context;
	    private List<EventoTO> detalles;
	    
	    public EfficientAdapter(Context context, List<EventoTO> valores) {
		      // Cache the LayoutInflate to avoid asking for a new one each time.
		      mInflater = LayoutInflater.from(context);
		      //this.context = context;
		      this.detalles = valores;
		    }
	    

	    /**
	     * Make a view to hold each row.
	     * 
	     * @see android.widget.ListAdapter#getView(int, android.view.View,
	     *      android.view.ViewGroup)
	     */
	    public View getView(final int position, View convertView, ViewGroup parent) {
	    	EventoTO eventoTO = (EventoTO) getItem(position);
	    	ViewHolder holder;

	      if (convertView == null) {
	        convertView = mInflater.inflate(R.layout.sacc_mostrar_evento_content, null);

	        // Creates a ViewHolder and store references to the two children
	        // views
	        // we want to bind data to.
	        holder = new ViewHolder();
	        	    	
	        holder.txViewNum = (TextView) convertView.findViewById(R.id.txViewNum); 
	        holder.txViewEvento = (TextView) convertView.findViewById(R.id.txViewEvento);
	        holder.txViewFecha =  (TextView) convertView.findViewById(R.id.txViewFecha);
	        holder.txViewHora =  (TextView) convertView.findViewById(R.id.txViewHora);
	        holder.txViewResp =  (TextView) convertView.findViewById(R.id.txViewResp);
	        holder.txViewMotivo =  (TextView) convertView.findViewById(R.id.txViewMotivo);
	        
	        convertView.setTag(holder);
	      } else {
	        // Get the ViewHolder back to get fast access to the TextView
	        // and the ImageView.
	        holder = (ViewHolder) convertView.getTag();
	      }
	      
	      holder.txViewNum.setText((position+1)+"");
	      holder.txViewEvento.setText(eventoTO.getIdEvento()+"");
	      holder.txViewFecha.setText(eventoTO.getFechaRegistro());
	      holder.txViewHora.setText(eventoTO.getHoraRegistro());
	      holder.txViewResp.setText(eventoTO.getUsuarioResponsable());
	      holder.txViewMotivo.setText(eventoTO.getMotivo());
	      
	      
	      
	      return convertView;
	    }

		static class ViewHolder {
	    	TextView txViewNum;
	    	TextView txViewEvento;
	    	TextView txViewFecha;
	    	TextView txViewHora;	    	
	    	TextView txViewResp;
	    	TextView txViewMotivo;
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
	      //return data.length;
	    	if(detalles==null){
	    		return 0;
	    	}else{
	    		return detalles.size();
	    	}
	    }

	    @Override
	    public Object getItem(int position) {
	      // TODO Auto-generated method stub
	    	return detalles.get(position);
	    }

	  }

	
}
