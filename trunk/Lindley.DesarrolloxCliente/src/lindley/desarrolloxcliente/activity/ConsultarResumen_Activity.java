package lindley.desarrolloxcliente.activity;

import java.util.List;

import net.msonic.lib.sherlock.ListActivityBase;

import roboguice.inject.InjectExtra;


import com.google.inject.Inject;

import lindley.desarrolloxcliente.MyApplication;
import lindley.desarrolloxcliente.R;
import lindley.desarrolloxcliente.negocio.UploadBLL;
import lindley.desarrolloxcliente.to.ClienteTO;
import lindley.desarrolloxcliente.to.ResumenValueTO;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

public class ConsultarResumen_Activity extends ListActivityBase {

	public static final String EVALUACION_ID_KEY="evaluacion_id_key";
	@InjectExtra(value=EVALUACION_ID_KEY) private long evaluacionId = -1;
	@Inject UploadBLL uploadBLL;
	
	EfficientAdapter adap=null;
	List<ResumenValueTO> detalles = null;
	private ClienteTO cliente;
	public  MyApplication application;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		inicializarRecursos();
		
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		  getSupportActionBar().setDisplayShowHomeEnabled(false);
	    
		  setContentView(R.layout.consultarresumen_activity);   
	      setTitle(R.string.resumen_activity_title);
	      
	        application = (MyApplication)getApplicationContext();
			cliente = application.cliente;
			
			getSupportActionBar().setSubtitle(String.format("%s - %s", cliente.codigo ,cliente.nombre));
			
		
		processAsync();
	}
	
	@Override
	protected void process()  throws Exception{
		detalles = uploadBLL.resumenEvaluacion(evaluacionId);
		adap = new EfficientAdapter(this, detalles);
		
	}
	
	public void verResumen(View v){
		
	}
	
	@Override
	protected void processOk() {
		adap = new EfficientAdapter(this, detalles);
		setListAdapter(adap);
		super.processOk();
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
	        holder = new ViewHolder();
	        holder.txtId = (TextView) convertView.findViewById(R.id.txtId);
	        holder.txtValor = (TextView) convertView.findViewById(R.id.txtValor);
	        holder.txtValor2 = (TextView) convertView.findViewById(R.id.txtValor2);
	        
	        convertView.setTag(holder);
	      } else {
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
