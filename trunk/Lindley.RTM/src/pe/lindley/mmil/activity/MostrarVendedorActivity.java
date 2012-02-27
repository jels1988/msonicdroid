package pe.lindley.mmil.activity;

import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
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

public class MostrarVendedorActivity extends ListActivityBase {
	
	@InjectView(R.id.actionBar)ActionBar mActionBar;
	@Inject ListarVendedorProxy listarVendedorProxy; 
	private EfficientAdapter adap;
	private static final int IND_VERDE = 0;
	private static final int IND_AMARRILLO = 1;
	private static final int IND_ROJO = 2;
	
	public static final String CODIGO_CDA = "codigo_cda";
	public static final String TIPO_SUPERVISOR = "supervisor";	
	public static final String CODIGO_SUPERVISOR = "codigo_supervisor";
	public static final String NOMBRE_SUPERVISOR = "nom_supervisor";
	
	private int tipo = 0;
	public static String codigo_cda = null;
	private int codigo_superv = 0;
	private String nombre_supervisor = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		Intent intent = this.getIntent();
		tipo = intent.getIntExtra(TIPO_SUPERVISOR, 0);
		codigo_cda = intent.getStringExtra(CODIGO_CDA);
		codigo_superv  = intent.getIntExtra(CODIGO_SUPERVISOR, -1);
		nombre_supervisor  = intent.getStringExtra(NOMBRE_SUPERVISOR);
		
		setContentView(R.layout.mmil_mostrar_vendedor_activity);
		mActionBar.setTitle(R.string.mostrar_vendedor_activity_title);
		mActionBar.setSubTitle(nombre_supervisor);
		mActionBar.setHomeLogo(R.drawable.header_logo);
		processAsync();
	}
	
	@Override
	protected void process() {
		listarVendedorProxy.setTipo(tipo+"");
		listarVendedorProxy.setCodigoDeposito(codigo_cda);
		listarVendedorProxy.setCodigoSupervisor(codigo_superv+"");
		listarVendedorProxy.execute();

		Log.v("MostrarVendedorActivity", codigo_superv+"");
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
	    private Context context;
	    
	    public EfficientAdapter(Context context, List<VendedorTO> valores) {
		      // Cache the LayoutInflate to avoid asking for a new one each time.
		      mInflater = LayoutInflater.from(context);
		      this.detalles = valores;
		      this.context = context;
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
	        convertView = mInflater.inflate(R.layout.mmil_mostrar_vendedor_content, null);

	        // Creates a ViewHolder and store references to the two children
	        // views
	        // we want to bind data to.
	        holder = new ViewHolder();
	        
	        holder.txViewRut = (TextView) convertView.findViewById(R.id.txViewRut); 
	        holder.txViewVend = (TextView) convertView.findViewById(R.id.txViewVend);	        
	        holder.imageEstado = (ImageView) convertView.findViewById(R.id.imageEstado);    
	        holder.txViewEstado = (TextView) convertView.findViewById(R.id.txViewEstado);	        
	        holder.txViewPrReg =  (TextView) convertView.findViewById(R.id.txViewPrReg);
	        holder.txViewUltReg =  (TextView) convertView.findViewById(R.id.txViewUltReg);
	        holder.imageCuota = (ImageView) convertView.findViewById(R.id.imageCuota);    
	        holder.txViewCuota = (TextView) convertView.findViewById(R.id.txViewCuota);
	        holder.imageEfGlo = (ImageView) convertView.findViewById(R.id.imageEfGlo);	   	    	
	    	holder.txViewEfcGlo = (TextView) convertView.findViewById(R.id.txViewEfcGlo);
	    	holder.imagePlVist = (ImageView) convertView.findViewById(R.id.imagePlVist);		    	
	    	holder.txViewPlVist = (TextView) convertView.findViewById(R.id.txViewPlVist);	
	    	holder.imageEfPrv = (ImageView) convertView.findViewById(R.id.imageEfPrv);	            	
	    	holder.txViewEfcPrv = (TextView) convertView.findViewById(R.id.txViewEfcPrv);	    	
	    	holder.txViewCli = (TextView) convertView.findViewById(R.id.txViewCli);	    	
	    	holder.txViewVist = (TextView) convertView.findViewById(R.id.txViewVist);	    	
	    	holder.txViewCPed = (TextView) convertView.findViewById(R.id.txViewCPed);
	        holder.txViewSPed = (TextView) convertView.findViewById(R.id.txViewSPed);	    	
	    	holder.txViewImp = (TextView) convertView.findViewById(R.id.txViewImp);	    	
	    	holder.txViewTEfc = (TextView) convertView.findViewById(R.id.txViewTEfc);    	
	    	holder.txViewVerMapa = (TextView) convertView.findViewById(R.id.txViewVerMapa);
	        
	        convertView.setTag(holder);
	      } else {
	        // Get the ViewHolder back to get fast access to the TextView
	        // and the ImageView.
	        holder = (ViewHolder) convertView.getTag();
	      }
	      
	      holder.txViewRut.setText(vendedorTO.getRuta());
	      holder.txViewVend.setText(vendedorTO.getNombre());	
	      if(vendedorTO.getColorEstado()!=null){
	      switch(Integer.parseInt(vendedorTO.getColorEstado()))
	      {
	      	case IND_VERDE:
	      		holder.imageEstado.setImageResource(R.drawable.icon_verde);
	    	  break;
	      	case IND_AMARRILLO:
	      		holder.imageEstado.setImageResource(R.drawable.icon_amarrillo);
	      		break;
	      	case IND_ROJO:
	      		holder.imageEstado.setImageResource(R.drawable.icon_rojo);
	      		break;	 
	       }	    
	      }
	      holder.txViewEstado.setText(vendedorTO.getEstado());	  
	      holder.txViewPrReg.setText(vendedorTO.getPrimerRegistro());	      
	      holder.txViewUltReg.setText(vendedorTO.getUltimoRegistro());	      
	      switch(Integer.parseInt(vendedorTO.getCuotaColor()))
	      {
	      	case IND_VERDE:
	      		holder.imageCuota.setImageResource(R.drawable.icon_verde);
	    	  break;
	      	case IND_AMARRILLO:
	      		holder.imageCuota.setImageResource(R.drawable.icon_amarrillo);
	      		break;
	      	case IND_ROJO:
	      		holder.imageCuota.setImageResource(R.drawable.icon_rojo);
	      		break;	 
	       }	       
	     holder.txViewCuota.setText(vendedorTO.getCuota());
	      switch(Integer.parseInt(vendedorTO.getEficGlobalColor()))
	      {
	      	case IND_VERDE:
	      		holder.imageEfGlo.setImageResource(R.drawable.icon_verde);
	    	  break;
	      	case IND_AMARRILLO:
	      		holder.imageEfGlo.setImageResource(R.drawable.icon_amarrillo);
	      		break;
	      	case IND_ROJO:
	      		holder.imageEfGlo.setImageResource(R.drawable.icon_rojo);
	      		break;	 
	       }	  
	      holder.txViewEfcGlo.setText(vendedorTO.getEficGlobal());
	      
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
	     
	      switch(Integer.parseInt(vendedorTO.getEficPreventaColor()))
	      {
	      	case IND_VERDE:
	      		holder.imageEfPrv.setImageResource(R.drawable.icon_verde);
	    	  break;
	      	case IND_AMARRILLO:
	      		holder.imageEfPrv.setImageResource(R.drawable.icon_amarrillo);
	      		break;
	      	case IND_ROJO:
	      		holder.imageEfPrv.setImageResource(R.drawable.icon_rojo);
	      		break;	 
	       }	 	     
	      holder.txViewEfcPrv.setText(vendedorTO.getEficPreventa());	   
	      holder.txViewCli.setText(vendedorTO.getCantidadClientes()+"");
	      holder.txViewVist.setText(vendedorTO.getVisitas()+"");  
	      holder.txViewCPed.setText(vendedorTO.getConPedido()+"");
	      holder.txViewSPed.setText(vendedorTO.getSinPedido()+"");
	      holder.txViewImp.setText(vendedorTO.getImporte()+"");
	      holder.txViewTEfc.setText(vendedorTO.getTiempoEfec());
	      
	      holder.txViewVerMapa.setOnClickListener(new OnClickListener() {
	    	  VendedorTO vendedorTemp= (VendedorTO)getItem(position);

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent map = new Intent(context,VendedorMapaActivity.class);
					map.putExtra(VendedorMapaActivity.CODIGO_CDA, codigo_cda);
					map.putExtra(VendedorMapaActivity.CODIGO_VENDEDOR, vendedorTemp.getCodigo());
					map.putExtra(VendedorMapaActivity.NOMBRE_VENDEDOR , vendedorTemp.getNombre());
					context.startActivity(map);
				}
			});
	      
	      return convertView;
	    }

	    static class ViewHolder {   
	    	TextView txViewRut;
	    	TextView txViewVend;
	    	ImageView imageEstado;
	    	TextView txViewEstado;
	    	TextView txViewPrReg;
	    	TextView txViewUltReg;
	    	ImageView imageCuota;
	    	TextView txViewCuota;  	
	    	ImageView imageEfGlo;	    	
	    	TextView txViewEfcGlo;
	    	ImageView imagePlVist;	    	
	    	TextView txViewPlVist;
	    	ImageView imageEfPrv;	    	
	    	TextView txViewEfcPrv;
	    	TextView txViewCli;
	    	TextView txViewVist;
	    	TextView txViewCPed;	    	
	    	TextView txViewSPed;   	 
	    	TextView txViewImp;
	    	TextView txViewTEfc;
	    	TextView txViewVerMapa;
	    	
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
