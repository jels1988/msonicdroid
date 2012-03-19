package lindley.desarrolloxcliente.activity;

import java.util.Calendar;
import java.util.List;
import lindley.desarrolloxcliente.MyApplication;
import lindley.desarrolloxcliente.R;
import lindley.desarrolloxcliente.to.ClienteTO;
import lindley.desarrolloxcliente.to.PosicionTO;
import lindley.desarrolloxcliente.ws.service.ConsultarPosicionProxy;
import net.msonic.lib.ListActivityBase;
import roboguice.inject.InjectExtra;
import roboguice.inject.InjectView;
import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;
import com.google.inject.Inject;
import com.thira.examples.actionbar.widget.ActionBar;

public class CompromisoPosicionOpen_Activity extends ListActivityBase {

	public static final String RESPUESTA = "rspta";
	@InjectView(R.id.actionBar)  	ActionBar 	mActionBar;
	@Inject ConsultarPosicionProxy consultarPosicionProxy;
	private EfficientAdapter adap;
	@InjectView(R.id.txtViewFecha) TextView txtViewFecha;
	ClienteTO cliente;
	private MyApplication application;
	@InjectExtra(RESPUESTA) String respuesta;
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	inicializarRecursos();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.consultarposicion_activity);        
        mActionBar.setTitle(R.string.consultarposicion_activity_title);
        application = (MyApplication)getApplicationContext();
		cliente = application.getClienteTO();
        mActionBar.setSubTitle(cliente.getNombre());
        mActionBar.setHomeLogo(R.drawable.header_logo);
        processAsync(); 
    }
    
    @Override
   	protected void process() {
    	consultarPosicionProxy.setCodigoCliente(cliente.getCodigo());
    	consultarPosicionProxy.setRespuesta(respuesta);       	
    	consultarPosicionProxy.execute();
   	}
    
    @Override
	protected void processOk() {
		// TODO Auto-generated method stub
		boolean isExito = consultarPosicionProxy.isExito();
		if (isExito) {
			int status = consultarPosicionProxy.getResponse().getStatus();
			if (status == 0) {
				List<PosicionTO> posiciones = consultarPosicionProxy
						.getResponse().getListaPosicion();
				adap = new EfficientAdapter(this, posiciones);				
				final Calendar c = Calendar.getInstance();      
				if(posiciones.size()>0)
					txtViewFecha.setText(c.get(Calendar.DAY_OF_MONTH) + "/" + (c.get(Calendar.MONTH)) + "/" + c.get(Calendar.YEAR));
				setListAdapter(adap);
			}
			else  {
				showToast(consultarPosicionProxy.getResponse().getDescripcion());
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
    
    public static class EfficientAdapter extends ArrayAdapter<PosicionTO> {
    	private Activity context;
		private List<PosicionTO> posiciones;

		public EfficientAdapter(Activity context,List<PosicionTO> posiciones ){
			super(context, R.layout.consultarposicion_content, posiciones);
			this.context=context;
			this.posiciones=posiciones;
		}
		
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
		
			
			View view = null;
			if (convertView == null) {
				LayoutInflater inflator = context.getLayoutInflater();
				view = inflator.inflate(R.layout.consultarposicion_content, null);
				final ViewHolder viewHolder = new ViewHolder();
				viewHolder.txViewVariable = (TextView) view.findViewById(R.id.txViewVariable);
				viewHolder.txViewRed = (TextView) view.findViewById(R.id.txViewRed);
				viewHolder.txViewMaximo = (TextView) view.findViewById(R.id.txViewMaximo);
				viewHolder.txViewDiferencia = (TextView) view.findViewById(R.id.txViewDiferencia);
				viewHolder.txViewPuntos = (TextView) view.findViewById(R.id.txViewPuntos);
				
				viewHolder.chkSeleccion = (CheckBox) view.findViewById(R.id.chkSeleccion);
		        				
				
				view.setTag(viewHolder);
				viewHolder.chkSeleccion.setTag(posiciones.get(position));
				
				
			}else{
				view = convertView;
				((ViewHolder) view.getTag()).chkSeleccion.setTag(posiciones.get(position));
			}
			
			ViewHolder holder = (ViewHolder) view.getTag();
			final PosicionTO posicionTO = posiciones.get(position);
			
			holder.txViewVariable.setText(posicionTO.getDescripcionVariable());
			holder.txViewRed.setText(posicionTO.getRed());
			holder.txViewMaximo.setText(posicionTO.getPtoMaximo());
			holder.txViewDiferencia.setText(posicionTO.getDiferencia());
			holder.txViewPuntos.setText(posicionTO.getPuntosSugeridos());

			holder.chkSeleccion.setOnCheckedChangeListener(new OnCheckedChangeListener() {
					
					@Override
					public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
						// TODO Auto-generated method stub
						if(isChecked){
							posicionTO.setSeleccionado(true);
						}else{
							posicionTO.setSeleccionado(false);
						}
					}
				});
		      
			return view;
		}

	    static class ViewHolder {   
	    	CheckBox chkSeleccion;
	    	TextView txViewVariable;
	    	TextView txViewRed;
	    	TextView txViewMaximo;
	    	TextView txViewDiferencia;
	    	TextView txViewPuntos;  	
	    }
	    
	  }
    /*public static class EfficientAdapter extends BaseAdapter implements Filterable {
	    private LayoutInflater mInflater;
	    //private Context context;
	    private List<PosicionTO> detalles;
	    
	    public EfficientAdapter(Context context, List<PosicionTO> valores) {
		      // Cache the LayoutInflate to avoid asking for a new one each time.
		      mInflater = LayoutInflater.from(context);
		      //this.context = context;
		      this.detalles = valores;
		    }
	    

	    public View getView(final int position, View convertView, ViewGroup parent) {
	    	final PosicionTO posicionTO = (PosicionTO) getItem(position);
	    	ViewHolder holder;

	      if (convertView == null) {
	        convertView = mInflater.inflate(R.layout.consultarposicion_content, null);

	        // Creates a ViewHolder and store references to the two children
	        // views
	        // we want to bind data to.
	        holder = new ViewHolder();
	        	        	        	    	
	        holder.chkSeleccion = (CheckBox) convertView.findViewById(R.id.chkSeleccion);
	        holder.txViewVariable = (TextView) convertView.findViewById(R.id.txViewVariable);
	        holder.txViewRed = (TextView) convertView.findViewById(R.id.txViewRed);
	        holder.txViewMaximo = (TextView) convertView.findViewById(R.id.txViewMaximo);
	        holder.txViewDiferencia = (TextView) convertView.findViewById(R.id.txViewDiferencia);
	        holder.txViewPuntos = (TextView) convertView.findViewById(R.id.txViewPuntos);	        
	    	
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
	      
	      holder.chkSeleccion.setOnCheckedChangeListener(new OnCheckedChangeListener() {
				
				@Override
				public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
					// TODO Auto-generated method stub
					if(isChecked){
						posicionTO.setSeleccionado(true);
					}else{
						posicionTO.setSeleccionado(false);
					}
				}
			});
	      	      
	      return convertView;
	    }

	    static class ViewHolder {   
	    	CheckBox chkSeleccion;
	    	TextView txViewVariable;
	    	TextView txViewRed;
	    	TextView txViewMaximo;
	    	TextView txViewDiferencia;
	    	TextView txViewPuntos;  	
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

	  }*/
}
