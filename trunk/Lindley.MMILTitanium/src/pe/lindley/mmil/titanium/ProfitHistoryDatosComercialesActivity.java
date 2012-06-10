package pe.lindley.mmil.titanium;

import java.util.List;

import pe.lindley.mmil.titanium.to.HistoryValueTO;
import pe.lindley.mmil.titanium.ws.service.ProfitHistoryDetalleProxy;

import roboguice.inject.InjectExtra;
import roboguice.inject.InjectView;
import android.content.Context;
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

import net.msonic.lib.ListActivityBase;

public class ProfitHistoryDatosComercialesActivity extends ListActivityBase {
	
	public static final String NOMBRE_CDA_KEY="NOMBRE_CDA";
	public static final String CODIGO_DEPOSITO_KEY = "CODIGO_DEPOSITO";
	public static final String CODIGO_SUPERVISOR_KEY = "CODIGO_SUPERVISOR";
	public static final String CODIGO_CLIENTE_KEY = "CODIGO_CLIENTE";
	public static final String TIPO_KEY = "TIPO_CONSULTA";
	
	@InjectExtra(CODIGO_SUPERVISOR_KEY) String codigoSupervisor;
	@InjectExtra(CODIGO_DEPOSITO_KEY) String codigoCda;
	@InjectExtra(NOMBRE_CDA_KEY) String nombre_cda;
	@InjectExtra(CODIGO_CLIENTE_KEY) String codigoCliente;
	@InjectExtra(TIPO_KEY) int tipoConsulta;
	
	public static final int TIPO_DATOS_COMERCIALES=0;
	public static final int TIPO_AVANCE_RESUMEN=1;
	
	@Inject ProfitHistoryDetalleProxy 	profitHistoryDetalleProxy;
	@InjectView(R.id.actionBar)  		ActionBar 	mActionBar;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		inicializarRecursos();
		setContentView(R.layout.profitdatoscomerciales_activity);
		
		mActionBar.setHomeLogo(R.drawable.header_logo);
		
		if(TIPO_DATOS_COMERCIALES==tipoConsulta)
			mActionBar.setTitle(R.string.profitdatoscomerciales_activity_title_avanceresumen);
		else
			
			mActionBar.setTitle(R.string.profitdatoscomerciales_activity_title_datoscomerciales);
		
		//mActionBar.setSubTitle(cliente_descripcion);
		processAsync();
	}
	
	@Override
	protected void process() {
			
		profitHistoryDetalleProxy.codigo = codigoCliente;
		if(TIPO_DATOS_COMERCIALES==tipoConsulta)
			profitHistoryDetalleProxy.tipo = ProfitHistoryDetalleProxy.PROFIT_DATOS_COMERCIALES;
		else
			profitHistoryDetalleProxy.tipo = ProfitHistoryDetalleProxy.PROFIT_DATOS_AVANCERESUMEN;
		
		profitHistoryDetalleProxy.execute();

	}
	

	@Override
	protected void processOk() {
		
		boolean isExito = profitHistoryDetalleProxy.isExito();

		if (isExito) {
			int status = profitHistoryDetalleProxy.getResponse().getStatus();
			
			if (status == 0) {
				List<HistoryValueTO> data = profitHistoryDetalleProxy.getResponse().detalle;
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
	        convertView = mInflater.inflate(R.layout.profitdatoscomerciales_content, null);

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
	      
	      
	      holder.txtId.setText(historyValueTO.descripcion);
	      holder.txtValor.setText(historyValueTO.valor);
	      
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



