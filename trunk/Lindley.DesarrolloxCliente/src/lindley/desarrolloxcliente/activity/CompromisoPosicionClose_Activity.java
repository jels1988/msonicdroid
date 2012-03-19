package lindley.desarrolloxcliente.activity;

import java.util.Calendar;
import java.util.List;

import lindley.desarrolloxcliente.MyApplication;
import lindley.desarrolloxcliente.R;
import lindley.desarrolloxcliente.to.ClienteTO;
import lindley.desarrolloxcliente.to.PosicionCompromisoTO;
import lindley.desarrolloxcliente.ws.service.ConsultarPosicionCompromisoProxy;
import net.msonic.lib.ListActivityBase;
import roboguice.inject.InjectExtra;
import roboguice.inject.InjectView;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.google.inject.Inject;
import com.thira.examples.actionbar.widget.ActionBar;

public class CompromisoPosicionClose_Activity extends ListActivityBase {

	public static final String RESPUESTA = "rspta";
	public static final String COD_GESTION = "codGestion";
	@InjectView(R.id.actionBar)  	ActionBar 	mActionBar;
	@Inject ConsultarPosicionCompromisoProxy consultarPosicionCompromisoProxy;
	@InjectView(R.id.txtViewFecha) TextView txtViewFecha;
	ClienteTO cliente;
	private EfficientAdapter adap;	
	private MyApplication application;
	@InjectExtra(RESPUESTA) String respuesta;
	@InjectExtra(COD_GESTION) String codigoGestion;
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	inicializarRecursos();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.consultarposicioncompromisoclose_activity);        
        mActionBar.setTitle(R.string.consultarposicioncompromisoclose_activity_title);
        application = (MyApplication)getApplicationContext();
		cliente = application.getClienteTO();
        mActionBar.setSubTitle(cliente.getNombre());
        mActionBar.setHomeLogo(R.drawable.header_logo);
        processAsync(); 
    }
    
    @Override
   	protected void process() {
    	consultarPosicionCompromisoProxy.setCodigoCliente(cliente.getCodigo());
    	consultarPosicionCompromisoProxy.setRespuesta(respuesta);       	
    	consultarPosicionCompromisoProxy.setCodigoGestion(codigoGestion);
    	consultarPosicionCompromisoProxy.execute();
   	}
    
    @Override
	protected void processOk() {
		// TODO Auto-generated method stub
		boolean isExito = consultarPosicionCompromisoProxy.isExito();
		if (isExito) {
			int status = consultarPosicionCompromisoProxy.getResponse().getStatus();
			if (status == 0) {
				List<PosicionCompromisoTO> posiciones = consultarPosicionCompromisoProxy
						.getResponse().getListaCompromisos();
				adap = new EfficientAdapter(this, posiciones);				
				final Calendar c = Calendar.getInstance();      
				if(posiciones.size()>0)
					txtViewFecha.setText(c.get(Calendar.DAY_OF_MONTH) + "/" + (c.get(Calendar.MONTH)) + "/" + c.get(Calendar.YEAR));
				setListAdapter(adap);
			}
			else  {
				showToast(consultarPosicionCompromisoProxy.getResponse().getDescripcion());
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
	    private List<PosicionCompromisoTO> detalles;
	    
	    public EfficientAdapter(Context context, List<PosicionCompromisoTO> valores) {
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
	    	final PosicionCompromisoTO posicionTO = (PosicionCompromisoTO) getItem(position);
	    	ViewHolder holder;

	      if (convertView == null) {
	        convertView = mInflater.inflate(R.layout.consultarposicioncompromisoclose_content, null);

	        // Creates a ViewHolder and store references to the two children
	        // views
	        // we want to bind data to.
	        holder = new ViewHolder();
	        
	        holder.txViewVariable = (TextView) convertView.findViewById(R.id.txViewVariable);
			holder.txViewRed = (TextView) convertView.findViewById(R.id.txViewRed);
			holder.txViewMaximo = (TextView) convertView.findViewById(R.id.txViewMaximo);
			holder.txViewDiferencia = (TextView) convertView.findViewById(R.id.txViewDiferencia);
			holder.txViewPuntos = (TextView) convertView.findViewById(R.id.txViewPuntos);
			holder.txViewAccComp = (TextView) convertView.findViewById(R.id.txViewAccComp);
			holder.txViewFecComp = (TextView) convertView.findViewById(R.id.txViewFecComp);
			holder.txViewCnfComp = (TextView) convertView.findViewById(R.id.txViewCnfComp);
			
			holder.btnFotoInicial = (Button) convertView.findViewById(R.id.btnFotoInicial);
			holder.btnFotoExito = (Button) convertView.findViewById(R.id.btnFotoExito);
			holder.btnFotoFinal = (Button) convertView.findViewById(R.id.btnFotoFinal);
			
	        convertView.setTag(holder);
	      } else {
	        // Get the ViewHolder back to get fast access to the TextView
	        // and the ImageView.
	        holder = (ViewHolder) convertView.getTag();
	      }
	      
	      holder.txViewVariable.setText(posicionTO.getDescripcionVariable());
	      holder.txViewRed.setText(posicionTO.getRed());
	      holder.txViewMaximo.setText(posicionTO.getPtoMaximo());
	      holder.txViewDiferencia.setText(posicionTO.getDiferencia());
	      holder.txViewPuntos.setText(posicionTO.getPuntosSugeridos());
	      holder.txViewAccComp.setText(posicionTO.getAccionCompromiso());
	      holder.txViewFecComp.setText(posicionTO.getFechaCompromiso());
	      holder.txViewCnfComp.setText(posicionTO.getConfirmacion());
	      	      	      
	      return convertView;
	    }

	    static class ViewHolder {   
	    	public TextView txViewVariable;
	    	public TextView txViewRed;
	    	public TextView txViewMaximo;
	    	public TextView txViewDiferencia;
	    	public TextView txViewPuntos;
	    	public Button btnFotoInicial;
	    	public Button btnFotoExito;
	    	public TextView txViewAccComp;
	    	public TextView txViewFecComp;
	    	public Button btnFotoFinal;
	    	public TextView txViewCnfComp;	
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
