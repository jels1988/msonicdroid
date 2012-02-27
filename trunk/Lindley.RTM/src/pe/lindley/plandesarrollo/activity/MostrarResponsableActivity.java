package pe.lindley.plandesarrollo.activity;

import java.util.List;

import pe.lindley.activity.R;
import pe.lindley.activity.RTMApplication;
import pe.lindley.lanzador.to.ClienteResumenTO;
import pe.lindley.plandesarrollo.to.ResponsableTO;
import pe.lindley.plandesarrollo.ws.service.ConsultarResponsableProxy;
import pe.lindley.util.ListActivityBase;
import roboguice.inject.InjectView;
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

import com.google.inject.Inject;
import com.thira.examples.actionbar.widget.ActionBar;

public class MostrarResponsableActivity extends ListActivityBase {

	@InjectView(R.id.actionBar) ActionBar mActionBar;
	@Inject ConsultarResponsableProxy consultarResponsableProxy;
	
	public static final String CODIGO_CLIENTE 	= "codigo";
	public static final String CODIGO_PLAN 		= "cod_plan";
	public static final String CODIGO_ACTIVIDAD = "cod_actividad";
	
	private static String codigo_cliente    = null;
	private static String codigo_plan 		= null;
	private static String codigo_actividad  = null;
	private EfficientAdapter adap;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		Intent intent = this.getIntent();
		codigo_cliente = intent.getStringExtra(CODIGO_CLIENTE);
		codigo_plan = intent.getStringExtra(CODIGO_PLAN);
		codigo_actividad = intent.getStringExtra(CODIGO_ACTIVIDAD);
		
		setContentView(R.layout.plandesarrollo_responsable_activity);	
		final RTMApplication application = (RTMApplication)contextProvider.get().getApplicationContext();
		ClienteResumenTO cliente = application.getClienteTO();
		mActionBar.setSubTitle(cliente.getCodigoCliente()+"-"+cliente.getRazonSocial());
		mActionBar.setTitle(R.string.pd_mostrar_responsableactivity_title);
		mActionBar.setHomeLogo(R.drawable.header_logo);
	}
	
	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		processAsync();		
	}
	
	
	@Override
	protected void process() {
		// TODO Auto-generated method stub
		consultarResponsableProxy.setCodigoActvidad(codigo_actividad);
		consultarResponsableProxy.setCodigoCliente(codigo_cliente);
		consultarResponsableProxy.setCodigoPLan(codigo_plan);
		consultarResponsableProxy.execute();
	}

	@Override
	protected void processOk() {
		// TODO Auto-generated method stub
		boolean isExito = consultarResponsableProxy.isExito();
		if (isExito) {
			int status = consultarResponsableProxy.getResponse().getStatus();
			if (status == 0) {
				List<ResponsableTO> responsables = consultarResponsableProxy.getResponse().getListaresponsables();
				adap = new EfficientAdapter(this, responsables);
				setListAdapter(adap);
			}
			else  {
				showToast(consultarResponsableProxy.getResponse().getDescripcion());
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
	
	public void btnAgregar_click(View view)
	{
		Intent datosResponsable = new Intent(this,DatosResponsableActivity.class);
		datosResponsable.putExtra(DatosResponsableActivity.TIPO_ACCION, DatosResponsableActivity.ACCION_NUEVO);
		datosResponsable.putExtra(DatosResponsableActivity.CODIGO_CLIENTE, codigo_cliente);
		datosResponsable.putExtra(DatosResponsableActivity.CODIGO_PLAN, codigo_plan);
		datosResponsable.putExtra(DatosResponsableActivity.CODIGO_ACTIVIDAD, codigo_actividad);
		startActivity(datosResponsable);
	}
	
	public static class EfficientAdapter extends BaseAdapter implements Filterable {
	    private LayoutInflater mInflater;
	    private Context context;
	    private List<ResponsableTO> detalles;
	    
	    public EfficientAdapter(Context context, List<ResponsableTO> valores) {
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
	    	ResponsableTO responsableTO = (ResponsableTO) getItem(position);
	      ViewHolder holder;

	      if (convertView == null) {
	        convertView = mInflater.inflate(R.layout.plandesarrollo_responsable_content, null);

	        // Creates a ViewHolder and store references to the two children
	        // views
	        // we want to bind data to.
	        holder = new ViewHolder();
	          
	        holder.txViewCodigo = (TextView) convertView.findViewById(R.id.txViewCodigo); 
	        holder.txViewResp = (TextView) convertView.findViewById(R.id.txViewResp);
	        holder.txViewCorto =  (TextView) convertView.findViewById(R.id.txViewCorto);
	        holder.txViewEditar =  (TextView) convertView.findViewById(R.id.txViewEditar);
	        holder.txViewEliminar =  (TextView) convertView.findViewById(R.id.txViewEliminar);		        
	        convertView.setTag(holder);
	      } else {
	        // Get the ViewHolder back to get fast access to the TextView
	        // and the ImageView.
	        holder = (ViewHolder) convertView.getTag();
	      }
	      
	      holder.txViewCodigo.setText(responsableTO.getCodigo());     		  
	      holder.txViewResp.setText(responsableTO.getDescripcion());
	      holder.txViewCorto.setText(responsableTO.getNombreCorto());
	      
	      
	      holder.txViewEliminar.setOnClickListener(new OnClickListener() {
	    	  ResponsableTO responsableto = (ResponsableTO)getItem(position);
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent elimResponsable = new Intent(context,EliminarResponsableActivity.class);
					elimResponsable.putExtra(EliminarResponsableActivity.CODIGO_CLIENTE, codigo_cliente);
					elimResponsable.putExtra(EliminarResponsableActivity.CODIGO_PLAN, codigo_plan);
					elimResponsable.putExtra(EliminarResponsableActivity.CODIGO_ACTIVIDAD, codigo_actividad);
					elimResponsable.putExtra(EliminarResponsableActivity.CODIGO_RESPONSABLE, responsableto.getCodigo());
					context.startActivity(elimResponsable);
				}
			});
	      
	      holder.txViewEditar.setOnClickListener(new OnClickListener() {
	    	  ResponsableTO responsableto = (ResponsableTO)getItem(position);
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent datosResponsable = new Intent(context,DatosResponsableActivity.class);
					datosResponsable.putExtra(DatosResponsableActivity.TIPO_ACCION, DatosResponsableActivity.ACCION_ACTUALIZAR);
					datosResponsable.putExtra(DatosResponsableActivity.CODIGO_CLIENTE, codigo_cliente);
					datosResponsable.putExtra(DatosResponsableActivity.CODIGO_PLAN, codigo_plan);
					datosResponsable.putExtra(DatosResponsableActivity.CODIGO_ACTIVIDAD, codigo_actividad);
					datosResponsable.putExtra(DatosResponsableActivity.CODIGO_RESPONSABLE, responsableto.getCodigo());
					context.startActivity(datosResponsable);				
				}
			});
	      
	      return convertView;
	    }

		static class ViewHolder {
	    	TextView txViewCodigo;
	    	TextView txViewResp;
	    	TextView txViewCorto;
	    	TextView txViewEditar;	    	
	    	TextView txViewEliminar;
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
