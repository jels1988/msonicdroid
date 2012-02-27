package pe.lindley.ventacero.activity;

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
import android.widget.TextView;
import pe.lindley.activity.R;
import pe.lindley.ficha.to.FiguraComercialTO;
import pe.lindley.ficha.ws.service.ObtenerFiguraComercialProxy;
import pe.lindley.util.ListActivityBase;
import roboguice.inject.InjectView;

public class MostrarFiguraComercialActivity extends ListActivityBase {
	
	@InjectView(R.id.actionBar)ActionBar mActionBar;
	@Inject ObtenerFiguraComercialProxy obtenerFiguraComercialProxy; 
	private EfficientAdapter adap;	

	public static final String CODIGO_CLIENTE_KEY="codigo_cliente";
	private String cliente_codigo = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ficha_figura_comercial_activity);
		Intent intent = this.getIntent();
		cliente_codigo = intent.getStringExtra(CODIGO_CLIENTE_KEY);
		mActionBar.setTitle(R.string.ficha_figura_comercial_activity_title);
		mActionBar.setHomeLogo(R.drawable.header_logo);
		processAsync();
	}
	
	@Override
	protected void process() {
			
		obtenerFiguraComercialProxy.setCliente(cliente_codigo);
		obtenerFiguraComercialProxy.execute();
	}
	
	@Override
	protected void processOk() {
		// TODO Auto-generated method stub
		boolean isExito = obtenerFiguraComercialProxy.isExito();
		if (isExito) {
			int status = obtenerFiguraComercialProxy.getResponse().getStatus();
			if (status == 0) {
				List<FiguraComercialTO> figuraComercial = obtenerFiguraComercialProxy
						.getResponse().getFiguracomercial();
				adap = new EfficientAdapter(this, figuraComercial);
				setListAdapter(adap);
			}
			else  {
				showToast(obtenerFiguraComercialProxy.getResponse().getDescripcion());
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
	    private List<FiguraComercialTO> detalles;
	    
	    public EfficientAdapter(Context context, List<FiguraComercialTO> valores) {
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
	    @Override
		public View getView(final int position, View convertView, ViewGroup parent) {
	    	FiguraComercialTO historyValueTO = (FiguraComercialTO) getItem(position);
	      ViewHolder holder;

	      if (convertView == null) {
	        convertView = mInflater.inflate(R.layout.ficha_figura_comercial_content, null);

	        // Creates a ViewHolder and store references to the two children
	        // views
	        // we want to bind data to.
	        holder = new ViewHolder();
	        holder.txtId = (TextView) convertView.findViewById(R.id.txtId);
	        holder.txtValor = (TextView) convertView.findViewById(R.id.txtValor);
	        
	        convertView.setTag(holder);
	      } else {
	        // Get the ViewHolder back to get fast access to the TextView
	        // and the ImageView.
	        holder = (ViewHolder) convertView.getTag();
	      }
	      
	      
	      holder.txtId.setText(historyValueTO.getDescripcion());
	      holder.txtValor.setText(historyValueTO.getValor());
	      
	      return convertView;
	    }

	    static class ViewHolder {
	    	TextView txtId;
	    	TextView txtValor;
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
