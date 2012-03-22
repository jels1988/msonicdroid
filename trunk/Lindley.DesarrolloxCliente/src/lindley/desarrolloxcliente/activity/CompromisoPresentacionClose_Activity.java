package lindley.desarrolloxcliente.activity;

import java.util.Calendar;
import java.util.List;

import lindley.desarrolloxcliente.MyApplication;
import lindley.desarrolloxcliente.R;
import lindley.desarrolloxcliente.to.ClienteTO;
import lindley.desarrolloxcliente.to.PresentacionCompromisoTO;
import lindley.desarrolloxcliente.ws.service.ConsultarPresentacionCompromisoProxy;
import net.msonic.lib.ListActivityBase;
import roboguice.inject.InjectExtra;
import roboguice.inject.InjectView;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.google.inject.Inject;
import com.thira.examples.actionbar.widget.ActionBar;

public class CompromisoPresentacionClose_Activity extends ListActivityBase {

	public static final String COD_GESTION = "codGestion";
	@InjectView(R.id.actionBar)  	ActionBar 	mActionBar;
	@Inject ConsultarPresentacionCompromisoProxy consultarPresentacionCompromisoProxy;
	private EfficientAdapter adap;
	@InjectView(R.id.txtViewFecha) TextView txtViewFecha;
	ClienteTO cliente;
	private MyApplication application;
	@InjectExtra(COD_GESTION) String codigoGestion;
	
	private static String pad(int c) {
		if (c >= 10)
			return String.valueOf(c);
		else
			return "0" + String.valueOf(c);
	}
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	inicializarRecursos();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.consultarpresentacioncompromisoclose_activity);        
        mActionBar.setTitle(R.string.consultarpresentacioncompromisoclose_activity_title);
        application = (MyApplication)getApplicationContext();
		cliente = application.getClienteTO();
        mActionBar.setSubTitle(cliente.getNombre());
        mActionBar.setHomeLogo(R.drawable.header_logo);
        processAsync(); 
    }
	
    @Override
   	protected void process() {
    	consultarPresentacionCompromisoProxy.setCodigoCliente(cliente.getCodigo());
    	consultarPresentacionCompromisoProxy.setCodigoRegistro(codigoGestion);
    	consultarPresentacionCompromisoProxy.execute();
   	}
    
    @Override
	protected void processOk() {
		// TODO Auto-generated method stub
		boolean isExito = consultarPresentacionCompromisoProxy.isExito();
		if (isExito) {
			int status = consultarPresentacionCompromisoProxy.getResponse().getStatus();
			if (status == 0) {
				List<PresentacionCompromisoTO> presentaciones = consultarPresentacionCompromisoProxy
						.getResponse().getListaCompromisos();
				adap = new EfficientAdapter(this, presentaciones);				
				final Calendar c = Calendar.getInstance();      
				if(presentaciones.size()>0)
					txtViewFecha.setText(c.get(Calendar.DAY_OF_MONTH) + "/" + (c.get(Calendar.MONTH)) + "/" + c.get(Calendar.YEAR));
				setListAdapter(adap);
			}
			else  {
				showToast(consultarPresentacionCompromisoProxy.getResponse().getDescripcion());
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
	    private Context context;
	    private List<PresentacionCompromisoTO> detalles;
	    
	    public EfficientAdapter(Context context, List<PresentacionCompromisoTO> valores) {
		      // Cache the LayoutInflate to avoid asking for a new one each time.
		      mInflater = LayoutInflater.from(context);
		      this.context = context;
		      this.detalles = valores;
		    }
	    

	    /**
	     * Make a view to hold each row.
	     * 
	     * @see android.widget.ListAdapter#getView(int, android.view.View,
	     *      android.view.ViewGroup)
	     */
	    public View getView(final int position, View convertView, ViewGroup parent) {
	    	final PresentacionCompromisoTO presentacionTO = (PresentacionCompromisoTO) getItem(position);
	    	ViewHolder holder;

	      if (convertView == null) {
	        convertView = mInflater.inflate(R.layout.consultarpresentacioncompromisoclose_content, null);

	        // Creates a ViewHolder and store references to the two children
	        // views
	        // we want to bind data to.
	        holder = new ViewHolder();
	        	        	        	    	
	        holder.txViewVariable = (TextView) convertView.findViewById(R.id.txViewVariable);
	        holder.txViewPuntos = (TextView) convertView.findViewById(R.id.txViewPuntos);
	        holder.btnSKU = (Button) convertView.findViewById(R.id.btnSKU);       
	        holder.txViewFecComp = (TextView) convertView.findViewById(R.id.txViewFecComp);
	        holder.txViewCnfComp = (TextView) convertView.findViewById(R.id.txViewCnfComp);
	        	    	
	        convertView.setTag(holder);
	      } else {
	        // Get the ViewHolder back to get fast access to the TextView
	        // and the ImageView.
	        holder = (ViewHolder) convertView.getTag();
	      }
	      
	      int mYear, mMonth, mDay;
			String fecha = presentacionTO.getFechaCompromiso();
			if (fecha.length() > 7) {
				mYear = Integer.parseInt(fecha.substring(0, 4));
				mMonth = Integer.parseInt(fecha.substring(4, 6));
				mDay = Integer.parseInt(fecha.substring(6));

				holder.txViewFecComp.setText(pad(mDay) + "/" + pad(mMonth) + "/" + pad(mYear));
			} else {
				holder.txViewFecComp.setText("");
			}
	      holder.txViewVariable.setText(presentacionTO.getDescripcionVariable());
	      holder.txViewPuntos.setText(presentacionTO.getPuntosSugeridos());
	      holder.txViewCnfComp.setText(presentacionTO.getConfirmacion());
	      	      	     
	      holder.btnSKU.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					MyApplication application = (MyApplication) context.getApplicationContext();
					application.listSKUPresentacionCompromiso = presentacionTO.getListaSKU();
					Intent skuPresentacion = new Intent(context, SKUPrioritarioCompromiso_Activity.class);
					skuPresentacion.putExtra(SKUPrioritarioCompromiso_Activity.FLAG_ESTADO, SKUPrioritarioCompromiso_Activity.FLAG_OPEN_ESTADO_CERRADO);
					context.startActivity(skuPresentacion);
				}
			});
	      
	      return convertView;
	    }

	    static class ViewHolder {   
	    	TextView txViewVariable;
	    	TextView txViewPuntos;  	
	    	Button   btnSKU;
	    	TextView txViewFecComp;
	    	TextView txViewCnfComp;
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
