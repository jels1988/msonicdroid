package lindley.desarrolloxcliente.activity;

import java.util.ArrayList;
import java.util.List;

import lindley.desarrolloxcliente.MyApplication;
import lindley.desarrolloxcliente.R;
import lindley.desarrolloxcliente.to.AccionTradeTO;
import lindley.desarrolloxcliente.to.ClienteTO;
import lindley.desarrolloxcliente.to.OportunidadTO;
import lindley.desarrolloxcliente.ws.service.ConsultarOportunidadProxy;
import roboguice.inject.InjectView;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.inject.Inject;
import com.thira.examples.actionbar.widget.ActionBar;

import net.msonic.lib.ListActivityBase;
import net.msonic.lib.MessageBox;

public class ConsultarOportunidad_Activity extends ListActivityBase {

	@InjectView(R.id.actionBar)  	ActionBar 	mActionBar;
	@Inject ConsultarOportunidadProxy consultarOportunidadProxy;
	private EfficientAdapter adap;
	@InjectView(R.id.txtViewFecha) TextView txtViewFecha;
	ClienteTO cliente;
	public final String OPORTUNIDAD_SISTEMA = "1";
	public static MyApplication application;
	public static ArrayList<OportunidadTO> finalOportunidades = new ArrayList<OportunidadTO>();
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	inicializarRecursos();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.consultaroportunidad_activity);        
        mActionBar.setTitle(R.string.oportunidad_activity_title);
        application = (MyApplication)getApplicationContext();
		cliente = application.getClienteTO();
        mActionBar.setSubTitle(cliente.getNombre());
        mActionBar.setHomeLogo(R.drawable.header_logo);
        processAsync(); 
    }
    
    public void btnSiguiente_click(View view)
    {
    	Intent intent = new Intent("lindley.desarrolloxcliente.oportunidaddesarrollador");
		startActivity(intent);
    }
    
    @Override
	protected void process() {
    	consultarOportunidadProxy.setCodigoCliente(cliente.getCodigo());
    	consultarOportunidadProxy.setTipoOportunidad(OPORTUNIDAD_SISTEMA);
    	consultarOportunidadProxy.execute();
	}

    @Override
	protected void processOk() {
		// TODO Auto-generated method stub
		boolean isExito = consultarOportunidadProxy.isExito();
		if (isExito) {
			int status = consultarOportunidadProxy.getResponse().getStatus();
			if (status == 0) {
				List<OportunidadTO> oportunidades = consultarOportunidadProxy
						.getResponse().getListaOportunidad();
				adap = new EfficientAdapter(this, oportunidades);
				if(oportunidades.size()>0)
				txtViewFecha.setText(oportunidades.get(0).getFecha());
				setListAdapter(adap);
			}
			else  {
				showToast(consultarOportunidadProxy.getResponse().getDescripcion());
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
	    private List<OportunidadTO> detalles;
	    
	    public EfficientAdapter(Context context, List<OportunidadTO> valores) {
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
	    	final OportunidadTO oportunidad = (OportunidadTO) getItem(position);
	    	ViewHolder holder;

	      if (convertView == null) {
	        convertView = mInflater.inflate(R.layout.consultaroportunidad_content, null);

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
	    	holder.cboAccTrade = (Spinner) convertView.findViewById(R.id.cboAccTrade);	          	
	    	holder.txViewFecha = (TextView) convertView.findViewById(R.id.txViewFecha);	    	
	    	holder.txViewPBonus = (TextView) convertView.findViewById(R.id.txViewPBonus);
	    	holder.txViewProfit = (TextView) convertView.findViewById(R.id.txViewProfit);
	        
	        convertView.setTag(holder);
	      } else {
	        // Get the ViewHolder back to get fast access to the TextView
	        // and the ImageView.
	        holder = (ViewHolder) convertView.getTag();
	      }
	      
	      holder.txViewPro.setText(oportunidad.getDescripcionProducto());
	      holder.txViewConcrecion.setText(oportunidad.getConcrecion());
	      holder.txViewSOVI.setText(oportunidad.getSovi());
	      holder.txViewCumPrecio.setText(oportunidad.getCumplePrecio());
	      holder.txViewSabores.setText(oportunidad.getNumeroSabores());
	      holder.txViewPCoca.setText(oportunidad.getPuntosCocaCola());
	      
	      /*int mYear,mMonth,mDay;
	      String fecha = oportunidad.getFechaOportunidad();
	      if(fecha.length() > 7)
	      {
	    	  mYear =  Integer.parseInt(fecha.substring(0, 4));
	    	  mMonth  =  Integer.parseInt(fecha.substring(4, 6));
	    	  mDay  =  Integer.parseInt(fecha.substring(6));
	    	  holder.txViewFecha.setText(mDay+"/"+mMonth+"/"+mYear);
	     }
	      else
	    	  holder.txViewFecha.setText("0");*/
	      
	      holder.txViewPBonus.setText(oportunidad.getPuntosBonus());
	      holder.cboAccTrade.setAdapter(application.getAdapterAccionTrade(oportunidad.getListaAccionesTrade()));
	      
	      holder.cboAccTrade.setOnItemSelectedListener(new OnItemSelectedListener() {

				@Override
				public void onItemSelected(AdapterView<?> arg0, View arg1,
						int arg2, long arg3) {
					// TODO Auto-generated method stub
					if(arg2 > 0)
					{
						if(finalOportunidades.size() <= 2)
						{
							oportunidad.setAccioneTrade(((AccionTradeTO)arg0.getSelectedItem()).getCodigo());
							finalOportunidades.add(oportunidad);
						}
						else
						{			
							arg0.setSelection(0);
							MessageBox.showSimpleDialog(context, "Mensaje", "Solo puede seleccionar como máximo 3 acciones.", "Aceptar", new android.content.DialogInterface.OnClickListener() {
								
								@Override
								public void onClick(DialogInterface dialog, int which) {
									// TODO Auto-generated method stub
								}
								
							});	
						}
					}
					else
					{
						finalOportunidades.remove(oportunidad);
					}
				}

				@Override
				public void onNothingSelected(AdapterView<?> arg0) {
					// TODO Auto-generated method stub
					
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
	    	TextView txViewConcrecion;
	    	TextView txViewSOVI;
	    	TextView txViewCumPrecio;
	    	TextView txViewSabores;  	    	
	    	TextView txViewPCoca;    	
	    	Spinner cboAccTrade;    	
	    	TextView txViewFecha;
	    	TextView txViewPBonus;
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
