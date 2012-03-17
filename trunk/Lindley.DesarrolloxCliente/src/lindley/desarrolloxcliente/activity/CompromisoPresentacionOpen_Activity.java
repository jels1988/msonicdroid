package lindley.desarrolloxcliente.activity;

import java.util.Calendar;
import java.util.List;

import lindley.desarrolloxcliente.MyApplication;
import lindley.desarrolloxcliente.R;
import lindley.desarrolloxcliente.to.ClienteTO;
import lindley.desarrolloxcliente.to.PresentacionTO;
import lindley.desarrolloxcliente.ws.service.ConsultarPresentacionProxy;
import roboguice.inject.InjectView;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import android.widget.CompoundButton.OnCheckedChangeListener;

import com.google.inject.Inject;
import com.thira.examples.actionbar.widget.ActionBar;

import net.msonic.lib.ListActivityBase;

public class CompromisoPresentacionOpen_Activity extends ListActivityBase {

	@InjectView(R.id.actionBar)  	ActionBar 	mActionBar;
	@Inject ConsultarPresentacionProxy consultarPresentacionProxy;
	private EfficientAdapter adap;
	@InjectView(R.id.txtViewFecha) TextView txtViewFecha;
	ClienteTO cliente;
	private MyApplication application;
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	inicializarRecursos();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.consultarpresentacion_activity);        
        mActionBar.setTitle(R.string.consultarpresentacion_activity_title);
        application = (MyApplication)getApplicationContext();
		cliente = application.getClienteTO();
        mActionBar.setSubTitle(cliente.getNombre());
        mActionBar.setHomeLogo(R.drawable.header_logo);
        processAsync(); 
    }
	
    @Override
   	protected void process() {
    	consultarPresentacionProxy.setCodigoCliente(cliente.getCodigo());
    	consultarPresentacionProxy.execute();
   	}
    
    @Override
	protected void processOk() {
		// TODO Auto-generated method stub
		boolean isExito = consultarPresentacionProxy.isExito();
		if (isExito) {
			int status = consultarPresentacionProxy.getResponse().getStatus();
			if (status == 0) {
				List<PresentacionTO> presentaciones = consultarPresentacionProxy
						.getResponse().getListaPresentacion();
				adap = new EfficientAdapter(this, presentaciones);				
				final Calendar c = Calendar.getInstance();      
				if(presentaciones.size()>0)
					txtViewFecha.setText(c.get(Calendar.DAY_OF_MONTH) + "/" + (c.get(Calendar.MONTH)) + "/" + c.get(Calendar.YEAR));
				setListAdapter(adap);
			}
			else  {
				showToast(consultarPresentacionProxy.getResponse().getDescripcion());
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
	    private List<PresentacionTO> detalles;
	    
	    public EfficientAdapter(Context context, List<PresentacionTO> valores) {
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
	    	final PresentacionTO presentacionTO = (PresentacionTO) getItem(position);
	    	ViewHolder holder;

	      if (convertView == null) {
	        convertView = mInflater.inflate(R.layout.consultarpresentacion_content, null);

	        // Creates a ViewHolder and store references to the two children
	        // views
	        // we want to bind data to.
	        holder = new ViewHolder();
	        	        	        	    	
	        holder.chkSeleccion = (CheckBox) convertView.findViewById(R.id.chkSeleccion);
	        holder.txViewVariable = (TextView) convertView.findViewById(R.id.txViewVariable);
	        holder.txViewPuntos = (TextView) convertView.findViewById(R.id.txViewPuntos);
	        holder.btnSKU = (Button) convertView.findViewById(R.id.btnSKU);        
	    	
	        convertView.setTag(holder);
	      } else {
	        // Get the ViewHolder back to get fast access to the TextView
	        // and the ImageView.
	        holder = (ViewHolder) convertView.getTag();
	      }
	      
	      holder.txViewVariable.setText(presentacionTO.getDescripcionVariable());
	      holder.txViewPuntos.setText(presentacionTO.getPuntosSugeridos());
	      
	      holder.chkSeleccion.setOnCheckedChangeListener(new OnCheckedChangeListener() {
				
				@Override
				public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
					// TODO Auto-generated method stub
					if(isChecked){
						presentacionTO.setSeleccionado(true);
					}else{
						presentacionTO.setSeleccionado(false);
					}
				}
			});
	      	      
	      return convertView;
	    }

	    static class ViewHolder {   
	    	CheckBox chkSeleccion;
	    	TextView txViewVariable;
	    	TextView txViewPuntos;  	
	    	Button   btnSKU;
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
