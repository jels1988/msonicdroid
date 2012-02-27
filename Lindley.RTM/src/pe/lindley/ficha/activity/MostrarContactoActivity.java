package pe.lindley.ficha.activity;

import java.util.List;

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
import android.widget.TextView;
import com.google.inject.Inject;
import com.thira.examples.actionbar.widget.ActionBar;
import pe.lindley.activity.R;
import pe.lindley.activity.RTMApplication;
import pe.lindley.ficha.to.ContactoTO;
import pe.lindley.ficha.ws.service.ObtenerContactoProxy;
import pe.lindley.lanzador.to.ClienteResumenTO;
import pe.lindley.util.ListActivityBase;
import roboguice.inject.InjectView;

public class MostrarContactoActivity extends ListActivityBase {
	
	@InjectView(R.id.actionBar)ActionBar mActionBar;
	@Inject ObtenerContactoProxy obtenerContactoProxy;
	private EfficientAdapter adap;
	
	public static final String CODIGO_CLIENTE_KEY="codigo_cliente";
	private String codigo_cliente = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);		
		setContentView(R.layout.ficha_mostrar_contacto_activity);
		Intent intent = this.getIntent();
		codigo_cliente = intent.getStringExtra(CODIGO_CLIENTE_KEY);
		RTMApplication application = (RTMApplication)getApplicationContext();
		ClienteResumenTO cliente = application.getClienteTO();
		mActionBar.setSubTitle(cliente.getCodigoCliente()+"-"+cliente.getRazonSocial());
		mActionBar.setTitle(R.string.ficha_contacto_activity_title);
		mActionBar.setHomeLogo(R.drawable.header_logo);		
	}

	
	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		processAsync();
	}


	public void btnAgregar_click(View view)
	{
		Intent profit = new Intent(this,ActualizarContactoActivity.class);
		profit.putExtra(ActualizarContactoActivity.CODIGO_CLIENTE,codigo_cliente);
		startActivity(profit);
	}
	
	@Override
	protected void process() {
		obtenerContactoProxy.setCodigo(codigo_cliente);
		obtenerContactoProxy.execute();
	}
	
	@Override
	protected void processOk() {
		// TODO Auto-generated method stub
		boolean isExito = obtenerContactoProxy.isExito();
		if (isExito) {
			int status = obtenerContactoProxy.getResponse().getStatus();
			if (status == 0) {
				List<ContactoTO> contactos = obtenerContactoProxy.getResponse().getContactos();
				adap = new EfficientAdapter(this, contactos);
				setListAdapter(adap);
			}
			else  {
				showToast(obtenerContactoProxy.getResponse().getDescripcion());
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
	    private List<ContactoTO> detalles;
	    
	    public EfficientAdapter(Context context, List<ContactoTO> valores) {
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
	    @Override
		public View getView(final int position, View convertView, ViewGroup parent) {
	    	ContactoTO contactoTO = (ContactoTO) getItem(position);
	      ViewHolder holder;

	      if (convertView == null) {
	        convertView = mInflater.inflate(R.layout.ficha_mostrar_contacto_content, null);

	        // Creates a ViewHolder and store references to the two children
	        // views
	        // we want to bind data to.
	        holder = new ViewHolder();
	        
	        holder.txViewCodigo = (TextView) convertView.findViewById(R.id.txViewCodigo); 
	        holder.txViewNombre = (TextView) convertView.findViewById(R.id.txViewNombre);
	        holder.txViewFechaCumple =  (TextView) convertView.findViewById(R.id.txViewFechaCumple);
	        holder.txViewFono =  (TextView) convertView.findViewById(R.id.txViewFono);
	        holder.txViewTipo = (TextView) convertView.findViewById(R.id.txViewTipo);    	
	    	holder.txViewEmail = (TextView) convertView.findViewById(R.id.txViewEmail);
	    	holder.txViewEditar = (TextView) convertView.findViewById(R.id.txViewEditar);
	        
	        convertView.setTag(holder);
	      } else {
	        // Get the ViewHolder back to get fast access to the TextView
	        // and the ImageView.
	        holder = (ViewHolder) convertView.getTag();
	      }
	      
	      holder.txViewCodigo.setText(contactoTO.getCodigoContacto());     		  
	      holder.txViewNombre.setText(contactoTO.getNombrecontacto());	
	      
	      int mYear,mMonth,mDay;
	      String fecha = contactoTO.getFechaNacimiento();
	      if(fecha.length() > 7)
	      {
	    	  mYear =  Integer.parseInt(fecha.substring(0, 4));
	    	  mMonth  =  Integer.parseInt(fecha.substring(4, 6));
	    	  mDay  =  Integer.parseInt(fecha.substring(6));
	    	  holder.txViewFechaCumple.setText(mDay+"/"+mMonth+"/"+mYear);
	     }
	      else
	    	  holder.txViewFechaCumple.setText("0");	      
	      
	      holder.txViewFono.setText(contactoTO.getTelefono());
	      holder.txViewTipo.setText(contactoTO.getDescripcionContacto());	 
	      holder.txViewEmail.setText(contactoTO.getEmail());	 
	      
	      holder.txViewEditar.setOnClickListener(new OnClickListener() {
	    	  ContactoTO contactoTemp = (ContactoTO)getItem(position);
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent profit = new Intent(context,ActualizarContactoActivity.class);
					profit.putExtra(ActualizarContactoActivity.TIPO_ACCION,ActualizarContactoActivity.ACCION_ACTUALIZAR);
					profit.putExtra(ActualizarContactoActivity.CODIGO_CLIENTE,contactoTemp.getCodigo());
					profit.putExtra(ActualizarContactoActivity.CODIGO_CONTACTO,contactoTemp.getCodigoContacto());
					profit.putExtra(ActualizarContactoActivity.NOMBRE_CONTACTO,contactoTemp.getNombrecontacto());
					profit.putExtra(ActualizarContactoActivity.FECHA_NAC_CONTACTO,contactoTemp.getFechaNacimiento());
					profit.putExtra(ActualizarContactoActivity.TELEFONO_CONTACTO,contactoTemp.getTelefono());
					profit.putExtra(ActualizarContactoActivity.TIPO_CONTACTO,contactoTemp.getTipoContacto());
					profit.putExtra(ActualizarContactoActivity.EMAIL_CONTACTO,contactoTemp.getEmail());
					context.startActivity(profit);
				}
			});
	      return convertView;
	    }

	    static class ViewHolder {   
	    	TextView txViewCodigo;
	    	TextView txViewNombre;
	    	TextView txViewFechaCumple;
	    	TextView txViewFono;
	    	TextView txViewTipo;
	    	TextView txViewEmail;  	
	    	TextView txViewEditar;
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

