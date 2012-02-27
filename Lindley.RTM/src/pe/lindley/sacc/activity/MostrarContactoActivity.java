package pe.lindley.sacc.activity;

import java.util.List;

import com.google.inject.Inject;
import com.thira.examples.actionbar.widget.ActionBar;

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
import pe.lindley.activity.R;
import pe.lindley.sacc.to.ContactoTO;
import pe.lindley.sacc.ws.service.ObtenerContactoProxy;
import pe.lindley.util.ListActivityBase;
import roboguice.inject.InjectExtra;
import roboguice.inject.InjectView;

public class MostrarContactoActivity extends ListActivityBase{

	public static final String CODIGO_CLIENTE = "cod_cliente";
	protected static final String NOMBRE_CLIENTE = "nom_cliente";
	private EfficientAdapter adap;
	
	@InjectExtra(CODIGO_CLIENTE)	int	codigo_client;
	@InjectExtra(NOMBRE_CLIENTE)	static String	nombre_client;
	@Inject ObtenerContactoProxy obtenerContactoProxy;
	@InjectView(R.id.actionBar)ActionBar mActionBar;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sacc_mostrar_contacto_activity);
		mActionBar.setTitle(R.string.sacc_mostrar_contacto_title);
		mActionBar.setSubTitle(nombre_client);
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
		obtenerContactoProxy.setIdCliente(codigo_client);
		obtenerContactoProxy.execute();
	}

	@Override
	protected void processOk() {
		// TODO Auto-generated method stub
		boolean isExito = obtenerContactoProxy.isExito();
		if (isExito) {
			int status = obtenerContactoProxy.getResponse().getStatus();
			if (status == 0) {
				List<ContactoTO> contactos = obtenerContactoProxy.getResponse().getListaContacto();
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
	    public View getView(final int position, View convertView, ViewGroup parent) {
	    	ContactoTO contactoTO = (ContactoTO) getItem(position);
	    	ViewHolder holder;

	      if (convertView == null) {
	        convertView = mInflater.inflate(R.layout.sacc_mostrar_contacto_content, null);

	        // Creates a ViewHolder and store references to the two children
	        // views
	        // we want to bind data to.
	        holder = new ViewHolder();
	        	    	
	        holder.txViewNum = (TextView) convertView.findViewById(R.id.txViewNum); 
	        holder.txViewIdContacto = (TextView) convertView.findViewById(R.id.txViewIdContacto);
	        holder.txViewDias =  (TextView) convertView.findViewById(R.id.txViewDias);
	        holder.txViewFecha =  (TextView) convertView.findViewById(R.id.txViewFecha);
	        holder.txViewHora =  (TextView) convertView.findViewById(R.id.txViewHora);
	        holder.txViewTipo =  (TextView) convertView.findViewById(R.id.txViewTipo);
	        holder.txViewNombre =  (TextView) convertView.findViewById(R.id.txViewNombre);
	        holder.txViewRsp =  (TextView) convertView.findViewById(R.id.txViewRsp); 
	        holder.txViewSituacion =  (TextView) convertView.findViewById(R.id.txViewSituacion);
	        holder.txViewTipoCto =  (TextView) convertView.findViewById(R.id.txViewTipoCto);
	        holder.txViewTema =  (TextView) convertView.findViewById(R.id.txViewTema);
	        holder.txViewSubTema =  (TextView) convertView.findViewById(R.id.txViewSubTema);
	        holder.txViewCanal =  (TextView) convertView.findViewById(R.id.txViewCanal);
	        holder.txViewDireccion =  (TextView) convertView.findViewById(R.id.txViewDireccion);
	        holder.txViewPlanta =  (TextView) convertView.findViewById(R.id.txViewPlanta);
	        holder.txViewProducto =  (TextView) convertView.findViewById(R.id.txViewProducto);
	        holder.txViewEditar =  (TextView) convertView.findViewById(R.id.txViewEditar);
	            
	        
	        convertView.setTag(holder);
	      } else {
	        // Get the ViewHolder back to get fast access to the TextView
	        // and the ImageView.
	        holder = (ViewHolder) convertView.getTag();
	      }
	      
	      holder.txViewNum.setText((position+1)+"");
	      holder.txViewRsp.setText(contactoTO.getResponsable());
	      holder.txViewIdContacto.setText(contactoTO.getIdContacto()+"");
	      holder.txViewDias.setText(contactoTO.getNumDias()+"");
	      holder.txViewFecha.setText(contactoTO.getFechaRegistro());
	      holder.txViewHora.setText(contactoTO.getHoraRegistro());
	      holder.txViewTipo.setText(contactoTO.getClienteConsumidor());
	      holder.txViewNombre.setText(contactoTO.getNombre());
	      holder.txViewSituacion.setText(contactoTO.getSituacion());
	      holder.txViewTipoCto.setText(contactoTO.getTipoContacto()+"");
	      holder.txViewTema.setText(contactoTO.getTema());
	      holder.txViewSubTema.setText(contactoTO.getSubTema());
	      holder.txViewCanal.setText(contactoTO.getArea());
	      holder.txViewDireccion.setText(contactoTO.getDireccion());
	      holder.txViewPlanta.setText(contactoTO.getPlanta());
	      holder.txViewProducto.setText(contactoTO.getArticulo());
	      
	      holder.txViewEditar.setOnClickListener(new OnClickListener() {
	    	  ContactoTO contactoTemp = (ContactoTO)getItem(position);
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent evento = new Intent(context,MostrarEventoActivity.class);
					evento.putExtra(MostrarEventoActivity.CODIGO_CONTACTO, contactoTemp.getIdContacto());
					evento.putExtra(MostrarEventoActivity.NOMBRE_CLIENTE, nombre_client + "  -  NRO CONTACTO:" + contactoTemp.getIdContacto());
					context.startActivity(evento);			
				}
			});
	      
	      return convertView;
	    }

		static class ViewHolder {
	    	TextView txViewNum;
	    	TextView txViewIdContacto;
	    	TextView txViewDias;
	    	TextView txViewFecha;	    	
	    	TextView txViewHora;
	    	TextView txViewTipo;   	
	    	TextView txViewNombre;	    	
	    	TextView txViewRsp;
	    	TextView txViewSituacion;
	    	TextView txViewTipoCto;	    	
	    	TextView txViewTema;	    	
	    	TextView txViewSubTema;	    	
	    	TextView txViewCanal;	    	
	    	TextView txViewDireccion;	    	
	    	TextView txViewPlanta;
	    	TextView txViewProducto;
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
