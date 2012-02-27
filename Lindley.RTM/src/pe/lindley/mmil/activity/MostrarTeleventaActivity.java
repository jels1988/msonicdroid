package pe.lindley.mmil.activity;

import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.inject.Inject;
import com.thira.examples.actionbar.widget.ActionBar;
import pe.lindley.activity.R;
import pe.lindley.mmil.to.VendedorTO;
import pe.lindley.mmil.ws.service.ListarVendedorProxy;
import pe.lindley.util.ListActivityBase;
import roboguice.inject.InjectView;

public class MostrarTeleventaActivity extends ListActivityBase {
	
	@InjectView(R.id.actionBar)ActionBar mActionBar;
	@Inject ListarVendedorProxy listarVendedorProxy; 
	private EfficientAdapter adap;
	private static final int IND_VERDE = 0;
	private static final int IND_AMARRILLO = 1;
	private static final int IND_ROJO = 2;
	public static final String TIPO_SUPERVISOR = "televenta";
	public static final String CODIGO_CDA = "codigo";
	public static final String CODIGO_SUPERVISOR = "supervisor";
	public static final String NOMBRE_CDA = "nom_cda";
	public static final int COD_TELEVENTA = 1;
	
	private int tipo = 0;
	private String codigo_cda = null;
	private int codigo_televenta = 1;
	private String nombre_cda = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		Intent intent = this.getIntent();
		tipo = intent.getIntExtra(TIPO_SUPERVISOR, 1);
		codigo_cda = intent.getStringExtra(CODIGO_CDA);
		codigo_televenta = intent.getIntExtra(CODIGO_SUPERVISOR, 1);
		nombre_cda = intent.getStringExtra(NOMBRE_CDA);
		
		setContentView(R.layout.mmil_mostrar_televenta_activity);
		mActionBar.setTitle(R.string.mostrar_Televendedor_activity_title);
		mActionBar.setSubTitle(nombre_cda);
		mActionBar.setHomeLogo(R.drawable.header_logo);
		processAsync();
	}
	
	@Override
	protected void process() {
		listarVendedorProxy.setTipo(tipo+"");
		listarVendedorProxy.setCodigoDeposito(codigo_cda);
		listarVendedorProxy.setCodigoSupervisor(codigo_televenta+"");
		listarVendedorProxy.execute();
	}
	
	@Override
	protected void processOk() {
		// TODO Auto-generated method stub
		boolean isExito = listarVendedorProxy.isExito();
		if (isExito) {
			int status = listarVendedorProxy.getResponse().getStatus();
			if (status == 0) {
				List<VendedorTO> vendedores = listarVendedorProxy
						.getResponse().getListaVendedor();
				adap = new EfficientAdapter(this, vendedores);
				setListAdapter(adap);
			}
			else  {
				showToast(listarVendedorProxy.getResponse().getDescripcion());
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
	    private List<VendedorTO> detalles;
	    
	    public EfficientAdapter(Context context, List<VendedorTO> valores) {
		      // Cache the LayoutInflate to avoid asking for a new one each time.
		      mInflater = LayoutInflater.from(context);
		      this.detalles = valores;
		    }

	    /**
	     * Make a view to hold each row.
	     * 
	     * @see android.widget.ListAdapter#getView(int, android.view.View,
	     *      android.view.ViewGroup)
	     */
	    public View getView(final int position, View convertView, ViewGroup parent) {
	    	VendedorTO vendedorTO = (VendedorTO) getItem(position);
	      ViewHolder holder;

	      if (convertView == null) {
	        convertView = mInflater.inflate(R.layout.mmil_mostrar_televenta_content, null);

	        // Creates a ViewHolder and store references to the two children
	        // views
	        // we want to bind data to.
	        holder = new ViewHolder();
	        
	        holder.txViewCod = (TextView) convertView.findViewById(R.id.txViewCod); 
	        holder.txViewTlVend = (TextView) convertView.findViewById(R.id.txViewTlVend);	        
	        holder.txViewPrReg =  (TextView) convertView.findViewById(R.id.txViewPrReg);
	        holder.txViewUltReg =  (TextView) convertView.findViewById(R.id.txViewUltReg);
	    	holder.imagePlVist = (ImageView) convertView.findViewById(R.id.imagePlVist);		    	
	    	holder.txViewPlVist = (TextView) convertView.findViewById(R.id.txViewPlVist);		    	
	    	holder.txViewCli = (TextView) convertView.findViewById(R.id.txViewCli);	    	
	    	holder.txViewVist = (TextView) convertView.findViewById(R.id.txViewVist);	    	
	    	holder.txViewCPed = (TextView) convertView.findViewById(R.id.txViewCPed);	    	
	    	holder.txViewImp = (TextView) convertView.findViewById(R.id.txViewImp);	  
	    	holder.txViewCjFis = (TextView) convertView.findViewById(R.id.txViewCjFis);
	    	holder.txViewCjUnit = (TextView) convertView.findViewById(R.id.txViewCjUnit);
	    	holder.txViewTEfc = (TextView) convertView.findViewById(R.id.txViewTEfc);    	
	        
	        convertView.setTag(holder);
	      } else {
	        // Get the ViewHolder back to get fast access to the TextView
	        // and the ImageView.
	        holder = (ViewHolder) convertView.getTag();
	      }
	      
	      holder.txViewCod.setText(vendedorTO.getCodigo());
	    holder.txViewTlVend.setText(vendedorTO.getNombre());		      
	      holder.txViewPrReg.setText(vendedorTO.getPrimerRegistro());	      
	      holder.txViewUltReg.setText(vendedorTO.getUltimoRegistro());
	   
	      switch(Integer.parseInt(vendedorTO.getPlanVisitaColor()))
	      {
	      	case IND_VERDE:
	      		holder.imagePlVist.setImageResource(R.drawable.icon_verde);
	    	  break;
	      	case IND_AMARRILLO:
	      		holder.imagePlVist.setImageResource(R.drawable.icon_amarrillo);
	      		break;
	      	case IND_ROJO:
	      		holder.imagePlVist.setImageResource(R.drawable.icon_rojo);
	      		break;	 
	       }	
	      
	      holder.txViewPlVist.setText(vendedorTO.getPlanVisita());
	   
	      holder.txViewCli.setText(vendedorTO.getCantidadClientes());
	     holder.txViewVist.setText(vendedorTO.getVisitas());  
	     holder.txViewCPed.setText(vendedorTO.getConPedido());
	       holder.txViewImp.setText(vendedorTO.getImporte());
	      holder.txViewCjFis.setText(vendedorTO.getCajaFisica());
	      holder.txViewCjUnit.setText(vendedorTO.getCajaUni());
	      holder.txViewTEfc.setText(vendedorTO.getTiempoEfec());
	      
	      return convertView;
	    }

	    static class ViewHolder {   
	    	TextView txViewCod;
	    	TextView txViewTlVend;
	    	TextView txViewPrReg;
	    	TextView txViewUltReg; 	
	    	ImageView imagePlVist;	    	
	    	TextView txViewPlVist;
	    	TextView txViewCli;
	    	TextView txViewVist;
	    	TextView txViewCPed;	   	 
	    	TextView txViewImp;
	    	TextView txViewCjFis; 
	    	TextView txViewCjUnit;
	    	TextView txViewTEfc;	    	
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
