package lindley.desarrolloxcliente.activity;

import java.util.List;

import lindley.desarrolloxcliente.MyApplication;
import lindley.desarrolloxcliente.R;
import lindley.desarrolloxcliente.to.ClienteTO;
import lindley.desarrolloxcliente.to.CompromisoTO;
import lindley.desarrolloxcliente.ws.service.ConsultarCompromisoProxy;
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

import net.msonic.lib.ListActivityBase;

public class CompromisoClose_Activity extends ListActivityBase {
	
	public final static String CODIGO_REGISTRO = "codigo_reg";
	
	@InjectExtra(CODIGO_REGISTRO) String codigoRegistro;
//	@InjectView(R.id.actionBar)  	ActionBar 	mActionBar;
	@Inject ConsultarCompromisoProxy consultarCompromisoProxy;
	@InjectView(R.id.txtViewFecha) TextView txtViewFecha;
	@InjectView(R.id.txtViewCliente) TextView txtViewCliente;
	private EfficientAdapter adap;
	public static ClienteTO cliente;
	
	/** Called when the activity is first created. */
    @Override 
    public void onCreate(Bundle savedInstanceState) {
    	inicializarRecursos();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.compromisoclose_activity);        
//        mActionBar.setTitle(R.string.compromiso_activity_title);
        MyApplication application = (MyApplication)getApplicationContext();
		cliente = application.getClienteTO();
		txtViewCliente.setText(cliente.getCodigo() + " - " + cliente.getNombre());
//        mActionBar.setSubTitle(cliente.getNombre());
//        mActionBar.setHomeLogo(R.drawable.header_logo);
        processAsync();
    }
    
    @Override
	protected void process() {
    	consultarCompromisoProxy.setCodigoCliente(cliente.getCodigo());
    	consultarCompromisoProxy.setCodigoRegistro(codigoRegistro);
    	consultarCompromisoProxy.execute();
	}

    @Override
	protected void processOk() {
		// TODO Auto-generated method stub
		boolean isExito = consultarCompromisoProxy.isExito();
		if (isExito) {
			int status = consultarCompromisoProxy.getResponse().getStatus();
			if (status == 0) {
				List<CompromisoTO> compromisos = consultarCompromisoProxy
						.getResponse().getListaCompromiso();
				txtViewFecha.setText(compromisos.get(0).getFecha());
				adap = new EfficientAdapter(this, compromisos);
				setListAdapter(adap);
			}
			else  {
				showToast(consultarCompromisoProxy.getResponse().getDescripcion());
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
	    private List<CompromisoTO> detalles;
	    
	    public EfficientAdapter(Context context, List<CompromisoTO> valores) {
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
	    	final CompromisoTO compromiso = (CompromisoTO) getItem(position);
	    	ViewHolder holder;

	      if (convertView == null) {
	        convertView = mInflater.inflate(R.layout.compromisoclose_content, null);

	        // Creates a ViewHolder and store references to the two children
	        // views
	        // we want to bind data to.
	        holder = new ViewHolder();
	        	        	    	
	        holder.txViewPro = (TextView) convertView.findViewById(R.id.txViewPro); 
	        holder.txViewConcrecion = (TextView) convertView.findViewById(R.id.txViewConcrecion);
	        holder.txViewSOVI =  (TextView) convertView.findViewById(R.id.txViewSOVI);
	        holder.txViewCumPrecio =  (TextView) convertView.findViewById(R.id.txViewCumPrecio);   
	        holder.txViewSabores = (TextView) convertView.findViewById(R.id.txViewSabores);  	    	
	    	holder.txViewPCoca = (TextView) convertView.findViewById(R.id.txViewPCoca);		    	
	    	holder.txViewAccTrade = (TextView) convertView.findViewById(R.id.txViewAccTrade);	          	
	    	holder.txViewSN = (TextView) convertView.findViewById(R.id.txViewSN);	    	
	    	holder.txViewPBonus = (TextView) convertView.findViewById(R.id.txViewPBonus);
	    	holder.btnProfit = (Button) convertView.findViewById(R.id.btnProfit);
	        
	        convertView.setTag(holder);
	      } else {
	        // Get the ViewHolder back to get fast access to the TextView
	        // and the ImageView.
	        holder = (ViewHolder) convertView.getTag();
	      }
	      
	      holder.txViewPro.setText(compromiso.getDescripcionProducto());
	      holder.txViewConcrecion.setText(compromiso.getConcrecion());
	      holder.txViewSOVI.setText(compromiso.getSovi());
	      holder.txViewCumPrecio.setText(compromiso.getCumplePrecio());
	      holder.txViewSabores.setText(compromiso.getNumeroSabores());
	      holder.txViewPCoca.setText(compromiso.getPuntosCocaCola());
	      holder.txViewAccTrade.setText(compromiso.getDescripcionAccion());
	      if(compromiso.getCumplio().equals("0")) holder.txViewSN.setText("N");
	      else holder.txViewSN.setText("S");
	      holder.txViewPBonus.setText(compromiso.getPuntosBonus());
	      
	      holder.btnProfit.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent profit = new Intent(context, VerProfit_Activity.class);
					profit.putExtra(VerProfit_Activity.ANIO, "");
					profit.putExtra(VerProfit_Activity.MES, "");
					profit.putExtra(VerProfit_Activity.CLIENTE, cliente.getCodigo());
					profit.putExtra(VerProfit_Activity.ARTICULO, compromiso.getCodigoProducto());
					profit.putExtra(VerProfit_Activity.NOMBRE_ARTICULO, compromiso.getDescripcionProducto());
					context.startActivity(profit);
				}
			});
	      
	      return convertView;
	    }

	    static class ViewHolder {   
	    	TextView txViewPro;
	    	TextView txViewConcrecion;
	    	TextView txViewSOVI;
	    	TextView txViewCumPrecio;
	    	TextView txViewSabores;  	    	
	    	TextView txViewPCoca;    	
	    	TextView txViewAccTrade;    	
	    	TextView txViewSN;
	    	TextView txViewPBonus;
	    	TextView btnProfit;
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
