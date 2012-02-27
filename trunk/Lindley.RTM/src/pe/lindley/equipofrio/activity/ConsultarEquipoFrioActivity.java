package pe.lindley.equipofrio.activity;

import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.google.inject.Inject;
import com.thira.examples.actionbar.widget.ActionBar;

import pe.lindley.activity.R;
import pe.lindley.equipofrio.to.EquipoFrioTO;
import pe.lindley.equipofrio.ws.service.EquiposFrioProxy;
import pe.lindley.util.ListActivityBase;
import roboguice.inject.InjectView;

public class ConsultarEquipoFrioActivity extends ListActivityBase {

	public static final String CODIGO_CLIENTE_KEY="codigo_cliente";
	public static final String CLIENTE_KEY="cliente_descripcion";
	
	private String cliente_codigo = null;
	private String cliente_descripcion = null;
	
	@InjectView(R.id.actionBar)  		ActionBar 	mActionBar;
	@Inject EquiposFrioProxy 	equiposFrioProxy;
	
	
	
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		Intent intent = this.getIntent();
		cliente_codigo = intent.getStringExtra(CODIGO_CLIENTE_KEY);
		cliente_descripcion = intent.getStringExtra(CLIENTE_KEY);
		
		setContentView(R.layout.consultarequipofrio_activity);
		
		mActionBar.setTitle(R.string.profit_equiposfrio_activity_title);
		mActionBar.setHomeLogo(R.drawable.header_logo);
		mActionBar.setSubTitle(cliente_descripcion) ;
		
		processAsync();
		
	}
	
	
	@Override
	protected void process() {
			
		equiposFrioProxy.setCodigo(this.cliente_codigo);
		equiposFrioProxy.execute();

	}
	

	@Override
	protected void processOk() {
		
		boolean isExito = equiposFrioProxy.isExito();

		if (isExito) {
			int status = equiposFrioProxy.getResponse().getStatus();
			
			if (status == 0) {
				
				List<EquipoFrioTO> data = equiposFrioProxy.getResponse().getEquipos();
				EfficientAdapter adap = new EfficientAdapter(this,data);
				setListAdapter(adap);
			}else{
				processError();
			}
			super.processOk();
		}else{
			processError();
		}
	}
	
	@Override
	protected void processError() {
		String message;
		if(equiposFrioProxy.getResponse()!=null){
			message = equiposFrioProxy.getResponse().getDescripcion();
		}else{
			message = error_generico_process;
		}
		super.processError();
		showToast(message);
	}
	
	
	public static class EfficientAdapter extends BaseAdapter implements Filterable {
	    private LayoutInflater mInflater;
	    private List<EquipoFrioTO> detalles;
	    
	    public EfficientAdapter(Context context, List<EquipoFrioTO> valores) {
		      // Cache the LayoutInflate to avoid asking for a new one each time.
		      mInflater = LayoutInflater.from(context);
		      this.detalles = valores;
		    }
	    

	    /**
	     * Make a view to hold each row.
	     * 
	     * @see android.widget.ListAdapter#getView(int, android.view.View,
	     *      android.view.ViewGroup)
	     */
	    public View getView(final int position, View convertView, ViewGroup parent) {
	      EquipoFrioTO equipoFrioTO = (EquipoFrioTO) getItem(position);
	      ViewHolder holder;

	      if (convertView == null) {
	        convertView = mInflater.inflate(R.layout.consultarequipofrio_content, null);

	        // Creates a ViewHolder and store references to the two children
	        // views
	        // we want to bind data to.
	        holder = new ViewHolder();
	        holder.txtCodigoEC = (TextView) convertView.findViewById(R.id.txtCodigoEC);
	        holder.txtSerie = (TextView) convertView.findViewById(R.id.txtSerie);
	        holder.txtCenso = (TextView) convertView.findViewById(R.id.txtCenso);
	        holder.txtModelo = (TextView) convertView.findViewById(R.id.txtModelo);
	        holder.txtMonto = (TextView) convertView.findViewById(R.id.txtMonto);
	        holder.txtMarca = (TextView) convertView.findViewById(R.id.txtMarca);
	        holder.txtTipo = (TextView) convertView.findViewById(R.id.txtTipo);
	        
	        convertView.setTag(holder);
	      } else {
	        // Get the ViewHolder back to get fast access to the TextView
	        // and the ImageView.
	        holder = (ViewHolder) convertView.getTag();
	      }
	      
	      holder.txtCodigoEC.setText(equipoFrioTO.getNumero());
	      holder.txtSerie.setText(equipoFrioTO.getSerie());
	      holder.txtCenso.setText(equipoFrioTO.getCenso());
	      holder.txtModelo.setText(equipoFrioTO.getModelo());
	      holder.txtMonto.setText(String.format("%s",equipoFrioTO.getMonto()));
	      holder.txtMarca.setText(equipoFrioTO.getMarca());
	      holder.txtTipo.setText(equipoFrioTO.getTipo());
	      return convertView;
	    }

	    static class ViewHolder {
	    	TextView txtCodigoEC;
	    	TextView txtSerie;
	    	TextView txtCenso;
	    	TextView txtModelo;
	    	TextView txtMonto;
	    	TextView txtMarca;
	    	TextView txtTipo;
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
