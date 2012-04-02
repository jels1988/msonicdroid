package lindley.desarrolloxcliente.activity;

import java.util.List;

import lindley.desarrolloxcliente.MyApplication;
import lindley.desarrolloxcliente.R;
import lindley.desarrolloxcliente.to.ClienteTO;
import lindley.desarrolloxcliente.to.DesarrolloClienteTO;
import lindley.desarrolloxcliente.ws.service.ConsultarCabeceraProxy;
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
import net.msonic.lib.ListActivityBase;

public class ConsultarCabecera_Activity extends ListActivityBase {

	@InjectView(R.id.actionBar)  	ActionBar 	mActionBar;
	@Inject ConsultarCabeceraProxy ConsultarCabeceraProxy;
	private EfficientAdapter adap;
	ClienteTO cliente;
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	inicializarRecursos();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cabeceracliente_activity);        
        mActionBar.setTitle(R.string.cabeceracliente_activity_title);
        MyApplication application = (MyApplication)getApplicationContext();
		cliente = application.getClienteTO();
		mActionBar.setSubTitle(cliente.getCodigo() + " - " + cliente.getNombre());
        mActionBar.setHomeLogo(R.drawable.header_logo);
    }
    
    @Override
    public void onBackPressed() {
    // check if page 2 is open
    	Intent intent = new Intent("lindley.desarrolloxcliente.consultarcliente");
		startActivity(intent);
    }

    @Override
	protected void onStart() {
		// TODO Auto-generated method stub
    	processAsync();
		super.onStart();
	}

	@Override
	protected void process() {
    	ConsultarCabeceraProxy.setCodigoCliente(cliente.getCodigo());
    	ConsultarCabeceraProxy.execute();
	}
    
    @Override
	protected void processOk() {
		// TODO Auto-generated method stub
		boolean isExito = ConsultarCabeceraProxy.isExito();
		if (isExito) {
			int status = ConsultarCabeceraProxy.getResponse().getStatus();
			if (status == 0) {
				List<DesarrolloClienteTO> cabecera = ConsultarCabeceraProxy
						.getResponse().getListaDesarrolloCliente();
				adap = new EfficientAdapter(this, cabecera);
				setListAdapter(adap);
			}
			else  {
				showToast(ConsultarCabeceraProxy.getResponse().getDescripcion());
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
	    private List<DesarrolloClienteTO> detalles;
	    
	    public EfficientAdapter(Context context, List<DesarrolloClienteTO> valores) {
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
	    	DesarrolloClienteTO desarrollo = (DesarrolloClienteTO) getItem(position);
	    	ViewHolder holder;

	      if (convertView == null) {
	        convertView = mInflater.inflate(R.layout.cabeceracliente_content, null);

	        // Creates a ViewHolder and store references to the two children
	        // views
	        // we want to bind data to.
	        holder = new ViewHolder();
	        	        
	        holder.txViewCodigo = (TextView) convertView.findViewById(R.id.txViewCodigo); 
	        holder.txViewFecha = (TextView) convertView.findViewById(R.id.txViewFecha);
	        holder.txViewHora =  (TextView) convertView.findViewById(R.id.txViewHora);
	        holder.txViewCreado =  (TextView) convertView.findViewById(R.id.txViewCreado);   
	        holder.txViewFechaCierre = (TextView) convertView.findViewById(R.id.txViewFechaCierre);  	    	
	    	holder.txViewHoraCierre = (TextView) convertView.findViewById(R.id.txViewHoraCierre);		    	
	    	holder.txViewCerrado = (TextView) convertView.findViewById(R.id.txViewCerrado);	          	
	    	holder.txViewestado = (TextView) convertView.findViewById(R.id.txViewestado);	    	
	    	holder.txViewVerDetalle = (Button) convertView.findViewById(R.id.txViewVerDetalle);	  
	        
	        convertView.setTag(holder);
	      } else {
	        // Get the ViewHolder back to get fast access to the TextView
	        // and the ImageView.
	        holder = (ViewHolder) convertView.getTag();
	      }
	      
	      holder.txViewCodigo.setText(desarrollo.getCodigo());
	      holder.txViewFecha.setText(desarrollo.getFecha());
	      holder.txViewHora.setText(desarrollo.getHora());
	      holder.txViewCreado.setText(desarrollo.getUserCrea());
	      holder.txViewFechaCierre.setText(desarrollo.getFechaCierre());
	      holder.txViewHoraCierre.setText(desarrollo.getHoraCierre());
	      holder.txViewCerrado.setText(desarrollo.getUserCierra());
	      
	      //if(position == 0)
	      if(desarrollo.getEstado().equals("A"))
	    	  holder.txViewestado.setText("Abierto");
	      else
	    	  holder.txViewestado.setText("Cerrado");
	      
	      holder.txViewVerDetalle.setOnClickListener(new OnClickListener() {
	    	  DesarrolloClienteTO desarrolloTemp = (DesarrolloClienteTO) getItem(position);
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					//if(position == 0)
					if(desarrolloTemp.getEstado().equals("A"))
					{
						Intent compromisoOpen = new Intent(context, CompromisoPrincipalOpen_Resumen.class);
						compromisoOpen.putExtra(CompromisoPrincipalOpen_Resumen.CODIGO_REGISTRO, desarrolloTemp.getCodigo());
						compromisoOpen.putExtra(CompromisoPrincipalOpen_Resumen.FLAG_FECHA, CompromisoPrincipalOpen_Resumen.FLAG_OPEN_FECHA_CERRADA);
						context.startActivity(compromisoOpen);
						//Intent compromisoOpen = new Intent(context, CompromisoOpen_Activity.class);
						//compromisoOpen.putExtra(CompromisoOpen_Activity.CODIGO_REGISTRO, desarrolloTemp.getCodigo());
						//compromisoOpen.putExtra(CompromisoOpen_Activity.FLAG_FECHA, CompromisoOpen_Activity.FLAG_OPEN_FECHA_CERRADA);
						//context.startActivity(compromisoOpen);
					}
					else
					{
						Intent compromisoClose = new Intent(context, CompromisoPrincipalClose_Resumen.class);
						compromisoClose.putExtra(CompromisoPrincipalClose_Resumen.CODIGO_REGISTRO, desarrolloTemp.getCodigo());
						context.startActivity(compromisoClose);
						//Intent compromisoClose = new Intent(context, CompromisoClose_Activity.class);
						//compromisoClose.putExtra(CompromisoClose_Activity.CODIGO_REGISTRO, desarrolloTemp.getCodigo());
						//context.startActivity(compromisoClose);
					}
				}
			});
	      
	      return convertView;
	    }

	    static class ViewHolder {   
	    	TextView txViewCodigo;
	    	TextView txViewFecha;
	    	TextView txViewHora;
	    	TextView txViewCreado;
	    	TextView txViewFechaCierre;  	    	
	    	TextView txViewHoraCierre;    	
	    	TextView txViewCerrado;    	
	    	TextView txViewestado;
	    	Button txViewVerDetalle;	    	
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
