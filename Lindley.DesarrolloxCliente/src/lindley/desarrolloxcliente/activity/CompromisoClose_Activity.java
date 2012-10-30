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
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.inject.Inject;

import net.msonic.lib.ActivityUtil;
import net.msonic.lib.ListActivityBase;

public class CompromisoClose_Activity extends ListActivityBase {
	
	public final static String CODIGO_REGISTRO = "codigo_reg";
	
	@InjectExtra(CODIGO_REGISTRO) String codigoRegistro;
	@Inject ConsultarCompromisoProxy consultarCompromisoProxy;
	@InjectView(R.id.txtViewFecha) TextView txtViewFecha;
	@InjectView(R.id.txtViewCliente) TextView txtViewCliente;
	private EfficientAdapter adap;
	public static ClienteTO cliente;
	MyApplication application;
	
	/** Called when the activity is first created. */
    @Override 
    public void onCreate(Bundle savedInstanceState) {
    	inicializarRecursos();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.compromisoclose_activity);      
        
        application = (MyApplication)getApplicationContext();
		cliente = application.cliente;
		txtViewCliente.setText(String.format("%s - %s", cliente.codigo ,cliente.nombre));
        processAsync();
    }
    
    @Override
	protected void process() throws Exception {
    	consultarCompromisoProxy.setCodigoCliente(cliente.codigo);
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
				txtViewFecha.setText(application.dia + "/" + application.mes + "/" + application.anio);
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
	        	        	    	
	        holder.txViewPuntos = (TextView) convertView.findViewById(R.id.txViewPuntos);
	        holder.btnProfit = (ImageButton) convertView.findViewById(R.id.btnProfit);
	        holder.txViewLegacy = (TextView) convertView.findViewById(R.id.txViewLegacy);
	        holder.txViewPro = (TextView) convertView.findViewById(R.id.txViewPro); 
	        holder.txViewConcrecion = (TextView) convertView.findViewById(R.id.txViewConcrecion); 
	        holder.txViewConcrecionActual = (TextView) convertView.findViewById(R.id.txViewConcrecionActual); 
	        holder.txViewSOVI =  (TextView) convertView.findViewById(R.id.txViewSOVI);
	        holder.txViewSOVICmp =  (TextView) convertView.findViewById(R.id.txViewSOVICmp);
	        holder.txViewCumPrecio =  (TextView) convertView.findViewById(R.id.txViewCumPrecio);
	        holder.txViewCumPrecioCmp =  (TextView) convertView.findViewById(R.id.txViewCumPrecioCmp);
	        holder.txViewSabores = (TextView) convertView.findViewById(R.id.txViewSabores);  	    	
	    	holder.txViewSaboresActual = (TextView) convertView.findViewById(R.id.txViewSaboresActual);		    	
	    	holder.txViewAccTrade = (TextView) convertView.findViewById(R.id.txViewAccTrade);	          	
	    	holder.txViewFecha = (TextView) convertView.findViewById(R.id.txViewFecha);
	    	
	    	holder.txViewConcrecionConf = (TextView) convertView.findViewById(R.id.txViewConcrecionConf);    	
	    	holder.txViewSOVIConf = (TextView) convertView.findViewById(R.id.txViewSOVIConf);    	
	    	holder.txViewCumPrecioConf = (TextView) convertView.findViewById(R.id.txViewCumPrecioConf);    	
	    	holder.txViewSaboresConf = (TextView) convertView.findViewById(R.id.txViewSaboresConf);    	
	    		    	
	        convertView.setTag(holder);
	      } else {
	        // Get the ViewHolder back to get fast access to the TextView
	        // and the ImageView.
	        holder = (ViewHolder) convertView.getTag();
	      }
	      
	      holder.txViewPuntos.setText(compromiso.puntosGanados);
	      holder.txViewPro.setText(compromiso.descripcionProducto);	      
		  holder.txViewLegacy.setText(compromiso.codigoLegacy);
	      holder.txViewConcrecion.setText(compromiso.concrecion);
	      holder.txViewConcrecionActual.setText(compromiso.concrecionActual);
	      holder.txViewSOVI.setText(compromiso.sovi);
	      holder.txViewSOVICmp.setText(compromiso.soviActual);
	      holder.txViewCumPrecio.setText(compromiso.cumplePrecio);
	      holder.txViewCumPrecioCmp.setText(compromiso.cumplePrecioActual);
	      holder.txViewSabores.setText(compromiso.numeroSabores);
	      holder.txViewSaboresActual.setText(compromiso.numeroSaboresActual+"");
	      holder.txViewAccTrade.setText(compromiso.descAccionTrade);
	      int mYear,mMonth,mDay;
	      String fecha = compromiso.fechaCompromiso;
	      if(fecha.length() > 7)
	      {
	    	  mYear =  Integer.parseInt(fecha.substring(0, 4));
	    	  mMonth  =  Integer.parseInt(fecha.substring(4, 6));
	    	  mDay  =  Integer.parseInt(fecha.substring(6));
	    	  
	    	  holder.txViewFecha.setText(ActivityUtil.pad(mDay)+"/"+ ActivityUtil.pad(mMonth)+"/"+(mYear));
	     }
	      else{
	    	  
	    	  holder.txViewFecha.setText("");
	      }
	      
	      if(compromiso.cumplePrecio.equals("S")) 
	    	  holder.txViewCumPrecio.setText("SI");
	      else 
	    	  holder.txViewCumPrecio.setText("NO");
	      
	      if(compromiso.cumplePrecioActual.equals("S")) 
	    	  holder.txViewCumPrecioCmp.setText("SI");
	      else 
	    	  holder.txViewCumPrecioCmp.setText("NO");
	      
	      
	      
	      if(compromiso.concrecion.equals("S")) 
	    	  holder.txViewConcrecion.setText("SI");
	      else 
	    	  holder.txViewConcrecion.setText("NO");
	      
	      if(compromiso.concrecionActual.equals("S")) 
	    	  holder.txViewConcrecionActual.setText("SI");
	      else 
	    	  holder.txViewConcrecionActual.setText("NO");
	      
	      if(compromiso.concrecionCumplio.equals("S")) 
	    	  holder.txViewConcrecionConf.setText("SI");
	      else 
	    	  holder.txViewConcrecionConf.setText("NO");
	      
	      if(compromiso.soviCumplio.equals("S")) 
	    	  holder.txViewSOVIConf.setText("SI");
	      else 
	    	  holder.txViewSOVIConf.setText("NO");
	      
	      if(compromiso.cumplePrecioCumplio.equals("S")) 
	    	  holder.txViewCumPrecioConf.setText("SI");
	      else 
	    	  holder.txViewCumPrecioConf.setText("NO");
	      
	      if(compromiso.numeroSaboresCumplio.equals("S")) 
	    	  holder.txViewSaboresConf.setText("SI");
	      else 
	    	  holder.txViewSaboresConf.setText("NO");
	      
	      holder.btnProfit.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent profit = new Intent(context, VerProfit_Activity.class);
					profit.putExtra(VerProfit_Activity.ANIO, "");
					profit.putExtra(VerProfit_Activity.MES, "");
					profit.putExtra(VerProfit_Activity.CLIENTE, cliente.codigo);
					profit.putExtra(VerProfit_Activity.ARTICULO, compromiso.codigoProducto);
					profit.putExtra(VerProfit_Activity.NOMBRE_ARTICULO, compromiso.descripcionProducto);
					context.startActivity(profit);
				}
			});
	      
	      return convertView;
	    }

	    static class ViewHolder {   
	    	TextView txViewPuntos;
	    	ImageButton btnProfit;
	    	TextView txViewLegacy;
	    	TextView txViewPro;
	    	TextView txViewConcrecion;
	    	TextView txViewConcrecionActual;
	    	TextView txViewSOVI;
	    	TextView txViewSOVICmp;
	    	TextView txViewCumPrecio;
	    	TextView txViewCumPrecioCmp;
	    	TextView txViewSabores; 
	    	TextView txViewSaboresActual;
	    	TextView txViewAccTrade;
	    	TextView txViewFecha;
	    	TextView txViewConcrecionConf;
	    	TextView txViewSOVIConf;
	    	TextView txViewCumPrecioConf;
	    	TextView txViewSaboresConf;
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

