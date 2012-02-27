package pe.lindley.preliquidacion;

import java.util.List;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.thira.examples.actionbar.widget.ActionBar;

import pe.lindley.preliquidacion.to.AvanceTO;
import pe.lindley.preliquidacion.to.ProductoTO;
import pe.lindley.preliquidacion.to.UsuarioTO;
import pe.lindley.preliquidacion.ws.proxy.ListarAvanceProxy;
import roboguice.inject.InjectView;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import net.msonic.lib.ListActivityBase;

public class ListarAvanceActivity extends ListActivityBase {

	@InjectView(R.id.actionBar)  	ActionBar 	mActionBar;
	
	@Inject ListarAvanceProxy listarAvanceProxy;
	UsuarioTO usuarioTO;
	private EfficientAdapter adap;
	@Inject  protected Provider<Context> contextProvider;
	@InjectView(R.id.txValueAvance) TextView txValueAvance;
	@InjectView(R.id.txViewCfPRog) TextView txViewCfPRog;
	//@InjectView(R.id.txViewEntreg) TextView txViewEntreg;
	//@InjectView(R.id.txViewRech) TextView txViewRech;
	@InjectView(R.id.txtViewSoles) TextView txtViewSoles;
	@InjectView(R.id.txtViewCliProg) TextView txtViewCliProg;
	//@InjectView(R.id.txtViewCliEntg) TextView txtViewCliEntg;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.avance_activity);
		

		mActionBar.setTitle(R.string.login_activity_sub_title);
        mActionBar.setHomeLogo(R.drawable.header_logo);
        
		RTMApplication application = (RTMApplication)contextProvider.get().getApplicationContext();
		inicializarRecursos();
		usuarioTO = application.getUsuarioTO();		
		processAsync();
	}
	
	@Override
	protected void process() {
		// TODO Auto-generated method stub
		listarAvanceProxy.setDesposito(usuarioTO.getDeposito());
		listarAvanceProxy.setNumeroCarga(usuarioTO.getOrdenCarga());
		listarAvanceProxy.execute();
	}

	@Override
	protected void processOk() {
		// TODO Auto-generated method stub
		boolean isExito = listarAvanceProxy.isExito();
		if (isExito) {
			int status = listarAvanceProxy.getResponse().getStatus();
			if (status == 0) {
				AvanceTO avance = listarAvanceProxy.getResponse().getAvance();
				txValueAvance.setText(avance.getAvanceEsperado() + " - " + avance.getAvanceActual());
				
				
				
				String glosa = avance.getCajasFisicasProgramadas()  + " - " + 
						avance.getCajasFisicasEntregadas() + " - " +
						avance.getCajasFisicasRechazadas() ;
			 	
				txViewCfPRog.setText(glosa);
				
				
				if(avance.getSolesProgramados().equals(""))avance.setSolesProgramados("0");
				if(avance.getSolesEntregados().equals(""))avance.setSolesEntregados("0");
				
				txtViewSoles.setText("S/." + avance.getSolesProgramados() + " - S/." + avance.getSolesEntregados() );
				txtViewCliProg.setText(avance.getClientesProgramados() + " - " + avance.getClientesEntregados());
				
				List<ProductoTO> avanceProducto = avance.getProductos();
				adap = new EfficientAdapter(this, avanceProducto);
				setListAdapter(adap);
			}
			else  {
				showToast(listarAvanceProxy.getResponse().getDescripcion());
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
		showToast(error_generico_process);
		super.processError();
	}
	

	public static class EfficientAdapter extends BaseAdapter implements Filterable {
	    private LayoutInflater mInflater;
	    private List<ProductoTO> detalles;
	    //private Context context;
	    
	    public EfficientAdapter(Context context, List<ProductoTO> valores) {
		      // Cache the LayoutInflate to avoid asking for a new one each time.
		      mInflater = LayoutInflater.from(context);
		      this.detalles = valores;
		     //this.context = context;
		    }
	    

	    /**
	     * Make a view to hold each row.
	     * 
	     * @see android.widget.ListAdapter#getView(int, android.view.View,
	     *      android.view.ViewGroup)
	     */
	    public View getView(final int position, View convertView, ViewGroup parent) {
	      ProductoTO productoTO = (ProductoTO) getItem(position);
	      ViewHolder holder;

	      if (convertView == null) {
	        convertView = mInflater.inflate(R.layout.avance_content, null);

	        // Creates a ViewHolder and store references to the two children
	        // views
	        // we want to bind data to.
	        holder = new ViewHolder();
	        
	        holder.txViewProd = (TextView) convertView.findViewById(R.id.txViewProd); 
	        holder.txViewCarg = (TextView) convertView.findViewById(R.id.txViewCarg);	        
	        holder.txViewEntg = (TextView) convertView.findViewById(R.id.txViewEntg);    
	        holder.txViewCam = (TextView) convertView.findViewById(R.id.txViewCam);	       
	        
	        convertView.setTag(holder);
	      } else {
	        // Get the ViewHolder back to get fast access to the TextView
	        // and the ImageView.
	        holder = (ViewHolder) convertView.getTag();
	      }
	      
	      holder.txViewProd.setText(productoTO.getProducto());
	      holder.txViewCarg.setText(productoTO.getCajasCargadas()+" / "+productoTO.getBotellasCargadas());
	      holder.txViewEntg.setText(productoTO.getCajasEntregadas()+" / "+productoTO.getBotellasEntregadas());
	      holder.txViewCam.setText(productoTO.getCajasCambios()+" / "+productoTO.getBotellasCambios());
	      
	      return convertView;
	    }

	    static class ViewHolder {   
	    	TextView txViewProd;
	    	TextView txViewCarg;
	    	TextView txViewEntg;
	    	TextView txViewCam;
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
