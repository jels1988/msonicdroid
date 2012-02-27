package pe.lindley.plandesarrollo.activity;

import java.util.List;
import com.google.inject.Inject;
import com.thira.examples.actionbar.widget.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import pe.lindley.activity.R;
import pe.lindley.activity.RTMApplication;
import pe.lindley.lanzador.to.ClienteResumenTO;
import pe.lindley.plandesarrollo.to.SustentoTO;
import pe.lindley.plandesarrollo.ws.service.ConsultarSustentoProxy;
import pe.lindley.util.ListActivityBase;
import roboguice.inject.InjectView;
import android.view.View.OnClickListener;

public class MostrarSustentoActivity extends ListActivityBase {

	@InjectView(R.id.actionBar)ActionBar mActionBar;
	@Inject ConsultarSustentoProxy consultarSustentoProxy;
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
		
		setContentView(R.layout.plandesarrollo_sustento_activity);
		final RTMApplication application = (RTMApplication)contextProvider.get().getApplicationContext();
		ClienteResumenTO cliente = application.getClienteTO();
		mActionBar.setSubTitle(cliente.getCodigoCliente()+"-"+cliente.getRazonSocial());
		mActionBar.setTitle(R.string.pd_mostrar_sustento_activity_title);
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
		consultarSustentoProxy.setCodigoActvidad(codigo_actividad);
		consultarSustentoProxy.setCodigoCliente(codigo_cliente);
		consultarSustentoProxy.setCodigoPLan(codigo_plan);
		consultarSustentoProxy.execute();
	}

	@Override
	protected void processOk() {
		// TODO Auto-generated method stub
		boolean isExito = consultarSustentoProxy.isExito();
		if (isExito) {
			int status = consultarSustentoProxy.getResponse().getStatus();
			if (status == 0) {
				List<SustentoTO> sustento = consultarSustentoProxy.getResponse().getListaSustento();
				adap = new EfficientAdapter(this, sustento);
				setListAdapter(adap);
			}
			else  {
				showToast(consultarSustentoProxy.getResponse().getDescripcion());
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
		Intent datosSustento = new Intent(this,DatosSustentoActivity.class);
		datosSustento.putExtra(DatosSustentoActivity.TIPO_ACCION, DatosSustentoActivity.ACCION_NUEVO);
		datosSustento.putExtra(DatosSustentoActivity.CODIGO_CLIENTE, codigo_cliente);
		datosSustento.putExtra(DatosSustentoActivity.CODIGO_PLAN, codigo_plan);
		datosSustento.putExtra(DatosSustentoActivity.CODIGO_ACTIVIDAD, codigo_actividad);
		startActivity(datosSustento);		
	}
	
	public static class EfficientAdapter extends BaseAdapter implements Filterable {
	    private LayoutInflater mInflater;
	    private Context context;
	    private List<SustentoTO> detalles;
	    
	    public EfficientAdapter(Context context, List<SustentoTO> valores) {
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
	    	SustentoTO sustentoTO = (SustentoTO) getItem(position);
	      ViewHolder holder;

	      if (convertView == null) {
	        convertView = mInflater.inflate(R.layout.plandesarrollo_sustento_content, null);

	        // Creates a ViewHolder and store references to the two children
	        // views
	        // we want to bind data to.
	        holder = new ViewHolder();
	        
	        holder.txViewCodigo = (TextView) convertView.findViewById(R.id.txViewCodigo);
	        holder.txViewFecVisita = (TextView) convertView.findViewById(R.id.txViewFecVisita); 
	        holder.txViewLabor = (TextView) convertView.findViewById(R.id.txViewLabor);
	        holder.txViewDescripcion =  (TextView) convertView.findViewById(R.id.txViewDescripcion);
	        holder.txViewUsuario =  (TextView) convertView.findViewById(R.id.txViewUsuario);
	        holder.txViewHora = (TextView) convertView.findViewById(R.id.txViewHora);
	        holder.txViewEditar = (TextView) convertView.findViewById(R.id.txViewEditar);
	        holder.txViewEliminar = (TextView) convertView.findViewById(R.id.txViewEliminar);
	        	    	
	        convertView.setTag(holder);
	      } else {
	        // Get the ViewHolder back to get fast access to the TextView
	        // and the ImageView.
	        holder = (ViewHolder) convertView.getTag();
	      }
	      
	      holder.txViewCodigo.setText(sustentoTO.getCodigoSustento());
	      holder.txViewLabor.setText(sustentoTO.getNombreActividad());
	      holder.txViewDescripcion.setText(sustentoTO.getDescripcionActividad());
	      String sustento = sustentoTO.getCodigoUsuario()+" - "+sustentoTO.getNombreUsuario();
	      holder.txViewUsuario.setText(sustento);
	      
	      int mYear,mMonth,mDay;
	      String fecha = sustentoTO.getFechaVisita();
	      if(fecha.length() > 7)
	      {
	    	  mYear =  Integer.parseInt(fecha.substring(0, 4));
	    	  mMonth  =  Integer.parseInt(fecha.substring(4, 6));
	    	  mDay  =  Integer.parseInt(fecha.substring(6));
	    	  holder.txViewFecVisita.setText(mDay+"/"+mMonth+"/"+mYear);
	     }
	      else
	    	  holder.txViewFecVisita.setText("0");
	      
	      int mHora,mMinuto,mSegundo;
	      String hora = sustentoTO.getHora();
	      if(hora.length() > 5)
	      {
	    	  mHora =  Integer.parseInt(hora.substring(0, 2));
	    	  mMinuto  =  Integer.parseInt(hora.substring(2, 4));
	    	  mSegundo  =  Integer.parseInt(hora.substring(4));
	    	  holder.txViewHora.setText(mHora+":"+mMinuto+":"+mSegundo);
	     }
	      else
	    	  holder.txViewHora.setText("0");
	   
	      holder.txViewEliminar.setOnClickListener(new OnClickListener() {
	    	  SustentoTO sustentoTemporal = (SustentoTO)getItem(position);
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent elimSustento = new Intent(context,EliminarSustentoActivity.class);
					elimSustento.putExtra(EliminarSustentoActivity.CODIGO_CLIENTE, codigo_cliente);
					elimSustento.putExtra(EliminarSustentoActivity.CODIGO_PLAN, codigo_plan);
					elimSustento.putExtra(EliminarSustentoActivity.CODIGO_ACTIVIDAD, codigo_actividad);
					elimSustento.putExtra(EliminarSustentoActivity.CODIGO_SUSTENTO, sustentoTemporal.getCodigoSustento());
					context.startActivity(elimSustento);
				}
			});
	      
	      holder.txViewEditar.setOnClickListener(new OnClickListener() {
	    	  SustentoTO sustentoTemporal = (SustentoTO)getItem(position);
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent datosSustento = new Intent(context,DatosSustentoActivity.class);
					datosSustento.putExtra(DatosSustentoActivity.TIPO_ACCION, DatosSustentoActivity.ACCION_ACTUALIZAR);
					datosSustento.putExtra(DatosSustentoActivity.CODIGO_CLIENTE, codigo_cliente);
					datosSustento.putExtra(DatosSustentoActivity.CODIGO_PLAN, codigo_plan);
					datosSustento.putExtra(DatosSustentoActivity.CODIGO_ACTIVIDAD, codigo_actividad);					
					datosSustento.putExtra(DatosSustentoActivity.CODIGO_SUSTENTO, sustentoTemporal.getCodigoSustento());
					datosSustento.putExtra(DatosSustentoActivity.DESCRIPCION_SUSTENTO, sustentoTemporal.getDescripcionActividad());
					datosSustento.putExtra(DatosSustentoActivity.TIPO_SUSTENTO, sustentoTemporal.getTipoActividad());
					datosSustento.putExtra(DatosSustentoActivity.FECHA_VISITA, sustentoTemporal.getFechaVisita());					
					context.startActivity(datosSustento);
				}
			});
	          
	      
	      return convertView;
	    }

	    static class ViewHolder {   
	    	TextView txViewCodigo;
	    	TextView txViewFecVisita;
	    	TextView txViewLabor;
	    	TextView txViewDescripcion;
	    	TextView txViewUsuario;
	    	TextView txViewHora;
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
