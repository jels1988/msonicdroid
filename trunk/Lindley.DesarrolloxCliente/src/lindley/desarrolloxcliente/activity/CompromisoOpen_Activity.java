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
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.google.inject.Inject;
import com.thira.examples.actionbar.widget.ActionBar;

import net.msonic.lib.ListActivityBase;

public class CompromisoOpen_Activity extends ListActivityBase {

public final static String CODIGO_REGISTRO = "codigo_reg";
	
	@InjectExtra(CODIGO_REGISTRO) String codigoRegistro;
	@InjectView(R.id.actionBar)   ActionBar 	mActionBar;
	@Inject ConsultarCompromisoProxy consultarCompromisoProxy;
	@InjectView(R.id.txtViewFecha) TextView txtViewFecha;
	private EfficientAdapter adap;
	ClienteTO cliente;
	
	/** Called when the activity is first created. */
    @Override 
    public void onCreate(Bundle savedInstanceState) {
    	inicializarRecursos();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.compromisoopen_activity);        
        mActionBar.setTitle(R.string.compromiso_activity_title);
        MyApplication application = (MyApplication)getApplicationContext();
		cliente = application.getClienteTO();
        mActionBar.setSubTitle(cliente.getNombre());
        mActionBar.setHomeLogo(R.drawable.header_logo);
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
				if(compromisos.size()>0)
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
	    //private Context context;
	    private List<CompromisoTO> detalles;
	    
	    public EfficientAdapter(Context context, List<CompromisoTO> valores) {
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
	    	CompromisoTO compromiso = (CompromisoTO) getItem(position);
	    	ViewHolder holder;

	      if (convertView == null) {
	        convertView = mInflater.inflate(R.layout.compromisoopen_content, null);

	        // Creates a ViewHolder and store references to the two children
	        // views
	        // we want to bind data to.
	        holder = new ViewHolder();
	        	        	    		    	
	        holder.txViewPro = (TextView) convertView.findViewById(R.id.txViewPro); 
	        holder.txViewConcrecion = (EditText) convertView.findViewById(R.id.txViewConcrecion);
	        holder.chkConcrecion = (CheckBox) convertView.findViewById(R.id.chkConcrecion);
	        holder.txViewSOVI =  (EditText) convertView.findViewById(R.id.txViewSOVI);
	        holder.chkSOVI = (CheckBox) convertView.findViewById(R.id.chkSOVI);
	        holder.txViewCumPrecio =  (EditText) convertView.findViewById(R.id.txViewCumPrecio);
	        holder.chkPrecio = (CheckBox) convertView.findViewById(R.id.chkPrecio);
	        holder.txViewSabores = (EditText) convertView.findViewById(R.id.txViewSabores);
	        holder.chkSabores = (CheckBox) convertView.findViewById(R.id.chkSabores);
	        
	        
	    	holder.txViewPuntos = (TextView) convertView.findViewById(R.id.txViewPuntos);		    	
	    	holder.txViewAccTrade = (TextView) convertView.findViewById(R.id.txViewAccTrade);	          	
	    	holder.txViewFecha = (TextView) convertView.findViewById(R.id.txViewFecha);	    	
	    	holder.chkCumplio = (CheckBox) convertView.findViewById(R.id.chkCumplio);
	    	
	    	holder.txViewProfit = (TextView) convertView.findViewById(R.id.txViewProfit);
	        
	        convertView.setTag(holder);
	      } else {
	        // Get the ViewHolder back to get fast access to the TextView
	        // and the ImageView.
	        holder = (ViewHolder) convertView.getTag();
	      }
	      
	      holder.txViewPro.setText(compromiso.getDescripcionProducto());
	      holder.txViewConcrecion.setText(compromiso.getConcrecion());
	      if(compromiso.getConfirmacionConcrecion().equals("1")) holder.chkConcrecion.setChecked(true);
	      else holder.chkConcrecion.setChecked(false);
	      
	      holder.txViewSOVI.setText(compromiso.getSovi());
	      if(compromiso.getConfirmacionSovi().equals("1")) holder.chkSOVI.setChecked(true);
	      else holder.chkSOVI.setChecked(false);
	      
	      holder.txViewCumPrecio.setText(compromiso.getCumplePrecio());
	      if(compromiso.getConfirmacionCumplePrecio().equals("1")) holder.chkPrecio.setChecked(true);
	      else holder.chkPrecio.setChecked(false);
	      
	      holder.txViewSabores.setText(compromiso.getNumeroSabores());
	      if(compromiso.getConfirmacionNumeroSabores().equals("1")) holder.chkSabores.setChecked(true);
	      else holder.chkSabores.setChecked(false);
	      
	      holder.txViewPuntos.setText(compromiso.getPuntosBonus());
	      holder.txViewAccTrade.setText(compromiso.getDescripcionAccion());
	      int mYear,mMonth,mDay;
	      String fecha = compromiso.getFechaCompromiso();
	      if(fecha.length() > 7)
	      {
	    	  mYear =  Integer.parseInt(fecha.substring(0, 4));
	    	  mMonth  =  Integer.parseInt(fecha.substring(4, 6));
	    	  mDay  =  Integer.parseInt(fecha.substring(6));
	    	  holder.txViewFecha.setText(mDay+"/"+mMonth+"/"+mYear);
	     }
	      else
	    	  holder.txViewFecha.setText("0");
	      
	      if(compromiso.getCumplio().equals("1")) holder.chkCumplio.setChecked(true);
	      else holder.chkCumplio.setChecked(false);
	      
	      holder.txViewFecha.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					/*Intent profit = new Intent(context,MostrarVendedorActivity.class);
					profit.putExtra(MostrarVendedorActivity.TIPO_SUPERVISOR, 0);
					profit.putExtra(MostrarVendedorActivity.CODIGO_CDA, codigo_cda);
					profit.putExtra(MostrarVendedorActivity.CODIGO_SUPERVISOR, supervisorTemporal.getCodigo());
					profit.putExtra(MostrarVendedorActivity.NOMBRE_SUPERVISOR, supervisorTemporal.getNombre());
					context.startActivity(profit);*/
				}
			});
	      
	      holder.txViewProfit.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					/*Intent profit = new Intent(context,MostrarVendedorActivity.class);
					profit.putExtra(MostrarVendedorActivity.TIPO_SUPERVISOR, 0);
					profit.putExtra(MostrarVendedorActivity.CODIGO_CDA, codigo_cda);
					profit.putExtra(MostrarVendedorActivity.CODIGO_SUPERVISOR, supervisorTemporal.getCodigo());
					profit.putExtra(MostrarVendedorActivity.NOMBRE_SUPERVISOR, supervisorTemporal.getNombre());
					context.startActivity(profit);*/
				}
			});
	      
	      return convertView;
	    }

	    static class ViewHolder {   
	    	TextView txViewPro;
	    	EditText txViewConcrecion;
	    	CheckBox chkConcrecion;
	    	EditText txViewSOVI;
	    	CheckBox chkSOVI;
	    	EditText txViewCumPrecio;
	    	CheckBox chkPrecio;
	    	EditText txViewSabores;
	    	CheckBox chkSabores;
	    	TextView txViewPuntos;   	    	
	    	TextView txViewAccTrade;    	
	    	TextView txViewFecha;
	    	CheckBox chkCumplio;
	    	TextView txViewProfit;
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
