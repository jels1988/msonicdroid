package lindley.desarrolloxcliente.activity;

import java.util.List;

import roboguice.inject.InjectView;


import com.google.inject.Inject;
import com.thira.examples.actionbar.widget.ActionBar;

import lindley.desarrolloxcliente.MyApplication;
import lindley.desarrolloxcliente.R;
import lindley.desarrolloxcliente.to.ClienteTO;
import lindley.desarrolloxcliente.to.ResumenValueTO;
import lindley.desarrolloxcliente.ws.service.ConsultarResumenProxy;
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
import net.msonic.lib.ListActivityBase;

public class ConsultarResumen_Activity extends ListActivityBase {

	public static final String CODIGO_REGISTRO_KEY="codigo_cliente";
	
	private String codigo_registro = null;
	
	
	@InjectView(R.id.actionBar)  	ActionBar 	mActionBar;
	@Inject ConsultarResumenProxy consultarResumenProxy;
	
	ClienteTO cliente;
	public static MyApplication application;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		inicializarRecursos();
		
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		Intent intent = this.getIntent();
		codigo_registro = intent.getStringExtra(CODIGO_REGISTRO_KEY);
		
		
		setContentView(R.layout.consultarresumen_activity);
		
		 mActionBar.setTitle(R.string.resumen_activity_title);
		application = (MyApplication)getApplicationContext();
	   cliente = application.getClienteTO();
		mActionBar.setSubTitle(cliente.getCodigo() + " - " + cliente.getNombre());
        mActionBar.setHomeLogo(R.drawable.header_logo);
        
		
		processAsync();
	}
	
	@Override
	protected void process() {
		consultarResumenProxy.codigoRegistro = codigo_registro;
		consultarResumenProxy.execute();
		
	}
	
	public void verResumen(View v){
		
	}
	
	@Override
	protected void processOk() {
		
		boolean isExito = consultarResumenProxy.isExito();

		if (isExito) {
			int status = consultarResumenProxy.getResponse().getStatus();
			
			if (status == 0) {
				List<ResumenValueTO> data = consultarResumenProxy.getResponse().datos;
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
		if(consultarResumenProxy.getResponse()!=null){
			message = consultarResumenProxy.getResponse().getDescripcion();
		}else{
			message = error_generico_process;
		}
		super.processError();
		showToast(message);
	}
	
	
	
	
	
	
	public static class EfficientAdapter extends BaseAdapter implements Filterable {
	    private LayoutInflater mInflater;
	    private List<ResumenValueTO> detalles;
	    
	    public EfficientAdapter(Context context, List<ResumenValueTO> valores) {
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
	    	ResumenValueTO historyValueTO = (ResumenValueTO) getItem(position);
	      ViewHolder holder;

	      if (convertView == null) {
	        convertView = mInflater.inflate(R.layout.consultarresumen_content, null);

	        // Creates a ViewHolder and store references to the two children
	        // views
	        // we want to bind data to.
	        holder = new ViewHolder();
	        holder.txtId = (TextView) convertView.findViewById(R.id.txtId);
	        holder.txtValor = (TextView) convertView.findViewById(R.id.txtValor);
	        holder.txtValor2 = (TextView) convertView.findViewById(R.id.txtValor2);
	        
	        convertView.setTag(holder);
	      } else {
	        // Get the ViewHolder back to get fast access to the TextView
	        // and the ImageView.
	        holder = (ViewHolder) convertView.getTag();
	      }
	      
	      
	      holder.txtId.setText(historyValueTO.descripcion);
	      holder.txtValor.setText(historyValueTO.valor);
	      holder.txtValor2.setText(historyValueTO.valor2);
	      
	      return convertView;
	    }

	    static class ViewHolder {
	    	TextView txtId;
	    	TextView txtValor;
	    	TextView txtValor2;
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
