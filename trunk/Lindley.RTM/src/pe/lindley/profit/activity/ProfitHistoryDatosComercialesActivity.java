package pe.lindley.profit.activity;

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
import pe.lindley.profit.to.HistoryValueTO;
import pe.lindley.profit.ws.service.ProfitHistoryDetalleProxy;
import pe.lindley.util.ListActivityBase;
import roboguice.inject.InjectView;

public class ProfitHistoryDatosComercialesActivity extends ListActivityBase {
	
	public static final String CODIGO_CLIENTE_KEY="codigo_cliente";
	public static final String CLIENTE_KEY="cliente_descripcion";
	public static final String TIPO_KEY="tipo_profit";
	
	public static final int TIPO_DATOS_COMERCIALES=0;
	public static final int TIPO_AVANCE_RESUMEN=1;
	
	@Inject ProfitHistoryDetalleProxy 	profitHistoryDetalleProxy;
	@InjectView(R.id.actionBar)  		ActionBar 	mActionBar;
	
	private String cliente_codigo = null;
	private String cliente_descripcion = null;
	private int tipo = 0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		Intent intent = this.getIntent();
		cliente_codigo = intent.getStringExtra(CODIGO_CLIENTE_KEY);
		cliente_descripcion = intent.getStringExtra(CLIENTE_KEY);
		tipo = intent.getIntExtra(TIPO_KEY, 0);
		
		setContentView(R.layout.profithistorydatoscomerciales_activity);
		
		mActionBar.setHomeLogo(R.drawable.header_logo);
		
		if(TIPO_DATOS_COMERCIALES==tipo)
			mActionBar.setTitle(R.string.profit_datoscomerciales_activity_title);
		else
			mActionBar.setTitle(R.string.profit_avanceresumen_activity_title);
		
		mActionBar.setSubTitle(cliente_descripcion);
		processAsync();
	}
	
	@Override
	protected void process() {
			
		profitHistoryDetalleProxy.setCodigo(this.cliente_codigo);
		if(TIPO_DATOS_COMERCIALES==tipo)
			profitHistoryDetalleProxy.setTipo(ProfitHistoryDetalleProxy.PROFIT_DATOS_COMERCIALES);
		else
			profitHistoryDetalleProxy.setTipo(ProfitHistoryDetalleProxy.PROFIT_DATOS_AVANCERESUMEN);
		
		profitHistoryDetalleProxy.execute();

	}
	

	@Override
	protected void processOk() {
		
		boolean isExito = profitHistoryDetalleProxy.isExito();

		if (isExito) {
			int status = profitHistoryDetalleProxy.getResponse().getStatus();
			
			if (status == 0) {
				List<HistoryValueTO> data = profitHistoryDetalleProxy.getResponse().getDetalle();
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
		if(profitHistoryDetalleProxy.getResponse()!=null){
			message = profitHistoryDetalleProxy.getResponse().getDescripcion();
		}else{
			message = error_generico_process;
		}
		super.processError();
		showToast(message);
	}

	public static class EfficientAdapter extends BaseAdapter implements Filterable {
	    private LayoutInflater mInflater;
	    private List<HistoryValueTO> detalles;
	    
	    public EfficientAdapter(Context context, List<HistoryValueTO> valores) {
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
	      HistoryValueTO historyValueTO = (HistoryValueTO) getItem(position);
	      ViewHolder holder;

	      if (convertView == null) {
	        convertView = mInflater.inflate(R.layout.profithistorydatoscomerciales_content, null);

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



