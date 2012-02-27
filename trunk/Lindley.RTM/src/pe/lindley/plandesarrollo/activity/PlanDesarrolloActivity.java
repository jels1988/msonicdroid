package pe.lindley.plandesarrollo.activity;

import java.util.List;
import com.google.inject.Inject;
import com.thira.examples.actionbar.widget.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import pe.lindley.activity.R;
import pe.lindley.activity.RTMApplication;
import pe.lindley.lanzador.to.ClienteResumenTO;
import pe.lindley.plandesarrollo.to.PlanDesarrolloTO;
import pe.lindley.plandesarrollo.ws.service.ConsultarPlanProxy;
import pe.lindley.util.ListActivityBase;
import roboguice.inject.InjectView;

public class PlanDesarrolloActivity extends ListActivityBase {

	@InjectView(R.id.actionBar)ActionBar mActionBar;
	@Inject ConsultarPlanProxy consultarPlanProxy;
	public static final String CODIGO_CLIENTE = "codigo";
	private EfficientAdapter adap;
	
	private static String codigo_cliente = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		Intent intent = this.getIntent();
		codigo_cliente = intent.getStringExtra(CODIGO_CLIENTE);
		
		setContentView(R.layout.plandesarrollo_principal_activity);
		final RTMApplication application = (RTMApplication)contextProvider.get().getApplicationContext();
		ClienteResumenTO cliente = application.getClienteTO();
		mActionBar.setSubTitle(cliente.getCodigoCliente()+"-"+cliente.getRazonSocial());
		mActionBar.setTitle(R.string.pd_mostrar_principal_activity_title);
		mActionBar.setHomeLogo(R.drawable.header_logo);
		processAsync();
	}
	
	@Override
	protected void process() {
		// TODO Auto-generated method stub
		consultarPlanProxy.setCodigo(codigo_cliente);
		consultarPlanProxy.execute();
	}

	@Override
	protected void processOk() {
		// TODO Auto-generated method stub
		boolean isExito = consultarPlanProxy.isExito();
		if (isExito) {
			int status = consultarPlanProxy.getResponse().getStatus();
			if (status == 0) {
				List<PlanDesarrolloTO> plan = consultarPlanProxy.getResponse().getpLanDesarrollo();
				adap = new EfficientAdapter(this, plan);
				setListAdapter(adap);
			}
			else  {
				showToast(consultarPlanProxy.getResponse().getDescripcion());
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
	    private List<PlanDesarrolloTO> detalles;
	    
	    public EfficientAdapter(Context context, List<PlanDesarrolloTO> valores) {
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
	    	PlanDesarrolloTO planTO = (PlanDesarrolloTO) getItem(position);
	      ViewHolder holder;

	      if (convertView == null) {
	        convertView = mInflater.inflate(R.layout.plandesarrollo_principal_content, null);

	        // Creates a ViewHolder and store references to the two children
	        // views
	        // we want to bind data to.
	        holder = new ViewHolder();
	        
	        holder.txViewCodigo = (TextView) convertView.findViewById(R.id.txViewCodigo); 
	        holder.txViewPlan = (TextView) convertView.findViewById(R.id.txViewPlan);
	        holder.txViewObjetivo =  (TextView) convertView.findViewById(R.id.txViewObjetivo);
	        holder.txViewFchCreacion =  (TextView) convertView.findViewById(R.id.txViewFchCreacion);
	        holder.txViewFecInicio = (TextView) convertView.findViewById(R.id.txViewFecInicio);    
	        holder.txViewVer = (TextView) convertView.findViewById(R.id.txViewVer);
	        
	        convertView.setTag(holder);
	      } else {
	        // Get the ViewHolder back to get fast access to the TextView
	        // and the ImageView.
	        holder = (ViewHolder) convertView.getTag();
	      }
	      
	      holder.txViewCodigo.setText(planTO.getCodigoPLan());     		  
	      holder.txViewPlan.setText(planTO.getDescripcionPLan());		      
	      holder.txViewObjetivo.setText(planTO.getDescripcionObjetivo());  
	      holder.txViewFchCreacion.setText(planTO.getFechaCreacion());
	      holder.txViewFecInicio.setText(planTO.getFechaInicio());
	      
	   
	      holder.txViewVer.setOnClickListener(new OnClickListener() {
	    	  PlanDesarrolloTO planTemporal = (PlanDesarrolloTO)getItem(position);
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent profit = new Intent(context,MostrarActividadActivity.class);
					profit.putExtra(MostrarActividadActivity.CODIGO_CLIENTE, codigo_cliente);
					profit.putExtra(MostrarActividadActivity.CODIGO_PLAN, planTemporal.getCodigoPLan());
					context.startActivity(profit);
				}
			});
	            
	      
	      return convertView;
	    }

	    static class ViewHolder {   
	    	TextView txViewCodigo;
	    	TextView txViewPlan;
	    	TextView txViewObjetivo;
	    	TextView txViewFchCreacion;
	    	TextView txViewFecInicio;
	    	TextView txViewVer;	    	
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
