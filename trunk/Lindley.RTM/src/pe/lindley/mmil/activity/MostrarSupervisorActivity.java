package pe.lindley.mmil.activity;

import java.util.List;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.inject.Inject;
import com.thira.examples.actionbar.widget.ActionBar;
import pe.lindley.activity.R;
import pe.lindley.mmil.to.SupervisorTO;
import pe.lindley.mmil.ws.service.ListarSupervisorProxy;
import pe.lindley.util.ListActivityBase;
import roboguice.inject.InjectView;

public class MostrarSupervisorActivity extends ListActivityBase {
	
	@InjectView(R.id.actionBar)ActionBar mActionBar;
	@Inject ListarSupervisorProxy listarSupervisorProxy;
	private EfficientAdapter adap;
	private static final int IND_VERDE = 0;
	private static final int IND_AMARRILLO = 1;
	private static final int IND_ROJO = 2;
	public static final String TIPO_SUPERVISOR = "supervisor";
	public static final String CODIGO_CDA = "codigo";
	public static final String NOMBRE_CDA = "nom_cda";
	
	
	
	
	private int tipo = 0;
	private static String codigo_cda = null;
	private static String nombre_cda = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		Intent intent = this.getIntent();
		tipo = intent.getIntExtra(TIPO_SUPERVISOR, 0);
		codigo_cda = intent.getStringExtra(CODIGO_CDA);
		nombre_cda = intent.getStringExtra(NOMBRE_CDA);
		
		setContentView(R.layout.mmil_mostrar_supervisor_activity);
		mActionBar.setTitle(R.string.mostrar_supervisor_activity_title);
		mActionBar.setSubTitle(nombre_cda);
		mActionBar.setHomeLogo(R.drawable.header_logo);
		processAsync();
	}
	
	@Override
	protected void process() {
		listarSupervisorProxy.setTipo(tipo);
		listarSupervisorProxy.setCodigoDeposito(codigo_cda);
		listarSupervisorProxy.execute();
	}
	
	@Override
	protected void processOk() {
		// TODO Auto-generated method stub
		boolean isExito = listarSupervisorProxy.isExito();
		if (isExito) {
			int status = listarSupervisorProxy.getResponse().getStatus();
			if (status == 0) {
				List<SupervisorTO> supervisores = listarSupervisorProxy
						.getResponse().getListaSupervisor();
				adap = new EfficientAdapter(this, supervisores);
				setListAdapter(adap);
			}
			else  {
				showToast(listarSupervisorProxy.getResponse().getDescripcion());
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
	    private List<SupervisorTO> detalles;
	    
	    public EfficientAdapter(Context context, List<SupervisorTO> valores) {
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
	    	SupervisorTO supervisorTO = (SupervisorTO) getItem(position);
	      ViewHolder holder;

	      if (convertView == null) {
	        convertView = mInflater.inflate(R.layout.mmil_mostrar_supervisor_content, null);

	        // Creates a ViewHolder and store references to the two children
	        // views
	        // we want to bind data to.
	        holder = new ViewHolder();
	        
	        holder.txViewCod = (TextView) convertView.findViewById(R.id.txViewCod); 
	        holder.txViewSup = (TextView) convertView.findViewById(R.id.txViewSup);
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
	      
	      holder.txViewCod.setText(supervisorTO.getCodigo()+"");
	      //holder.txViewSup.setText(supervisorTO.getNombre());	      		  
	      holder.txViewSup.setText(Html.fromHtml("<a href=''>" + supervisorTO.getNombre() + "</a>"));	
	      
	      holder.txViewPrReg.setText(supervisorTO.getPrimerRegistro());	      
	      holder.txViewUltReg.setText(supervisorTO.getUltimoRegistro());	      
	      
	      switch(Integer.parseInt(supervisorTO.getCuotaColor()))
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
	     holder.txViewCuota.setText((Html.fromHtml("<a href=''>" + supervisorTO.getCuota() + "</a>")));
	      
	      
	      
	      switch(Integer.parseInt(supervisorTO.getEficGlobalColor()))
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
	      holder.txViewEfcGlo.setText((Html.fromHtml("<a href=''>" + supervisorTO.getEficGlobal() + "</a>")));
	   	      
	      switch(Integer.parseInt(supervisorTO.getPlanVisitaColor()))
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
	      holder.txViewPlVist.setText((Html.fromHtml("<a href=''>" + supervisorTO.getPlanVisita() + "</a>")));
	      	     
	      switch(Integer.parseInt(supervisorTO.getEficPreventaColor()))
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
	      holder.txViewEfcPrv.setText((Html.fromHtml("<a href=''>" + supervisorTO.getEficPreventa() + "</a>")));
	      	   
	      holder.txViewCli.setText(supervisorTO.getCantidadClientes()+"");
	      holder.txViewVist.setText(supervisorTO.getVisitas()+"");  
	      holder.txViewCPed.setText(supervisorTO.getConPedido()+"");
	      holder.txViewSPed.setText(supervisorTO.getSinPedido()+"");
	      holder.txViewImp.setText(supervisorTO.getImporte()+"");
	      holder.txViewTEfc.setText(supervisorTO.getTiempoEfec());
	      
	      holder.txViewSup.setOnClickListener(new OnClickListener() {
	    	  SupervisorTO supervisorTemporal = (SupervisorTO)getItem(position);
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent profit = new Intent(context,MostrarVendedorActivity.class);
					profit.putExtra(MostrarVendedorActivity.TIPO_SUPERVISOR, 0);
					profit.putExtra(MostrarVendedorActivity.CODIGO_CDA, codigo_cda);
					profit.putExtra(MostrarVendedorActivity.CODIGO_SUPERVISOR, supervisorTemporal.getCodigo());
					profit.putExtra(MostrarVendedorActivity.NOMBRE_SUPERVISOR, supervisorTemporal.getNombre());
					context.startActivity(profit);
				}
			});
	      
	      holder.txViewCuota.setOnClickListener(new OnClickListener() {
	    	  SupervisorTO supervisorTemporal = (SupervisorTO)getItem(position);
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent profit = new Intent(context,MostrarDashBoardActivity.class);
					profit.putExtra(MostrarDashBoardActivity.TIPO_DASHBOARD, 1);
					profit.putExtra(MostrarDashBoardActivity.CODIGO_CDA, codigo_cda);
					profit.putExtra(MostrarDashBoardActivity.CODIGO_SUPERVISOR, supervisorTemporal.getCodigo());
					profit.putExtra(MostrarDashBoardActivity.NOMBRE_INDICADOR, MostrarDashBoardActivity.VOLUMEN);
					context.startActivity(profit);
				}
			});
	      
	      holder.txViewEfcGlo.setOnClickListener(new OnClickListener() {
	    	  SupervisorTO supervisorTemporal = (SupervisorTO)getItem(position);
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent profit = new Intent(context,MostrarDashBoardActivity.class);
					profit.putExtra(MostrarDashBoardActivity.TIPO_DASHBOARD, 1);
					profit.putExtra(MostrarDashBoardActivity.CODIGO_CDA, codigo_cda);
					profit.putExtra(MostrarDashBoardActivity.CODIGO_SUPERVISOR, supervisorTemporal.getCodigo());
					profit.putExtra(MostrarDashBoardActivity.NOMBRE_INDICADOR, MostrarDashBoardActivity.EFICIENCIA_GLOBAL);
					context.startActivity(profit);
				}
			});
	      
	      holder.txViewPlVist.setOnClickListener(new OnClickListener() {
	    	  SupervisorTO supervisorTemporal = (SupervisorTO)getItem(position);
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent profit = new Intent(context,MostrarDashBoardActivity.class);
					profit.putExtra(MostrarDashBoardActivity.TIPO_DASHBOARD, 1);
					profit.putExtra(MostrarDashBoardActivity.CODIGO_CDA, codigo_cda);
					profit.putExtra(MostrarDashBoardActivity.CODIGO_SUPERVISOR, supervisorTemporal.getCodigo());
					profit.putExtra(MostrarDashBoardActivity.NOMBRE_INDICADOR, MostrarDashBoardActivity.PLAN_VISITA);
					context.startActivity(profit);
				}
			});
	      
	      holder.txViewEfcPrv.setOnClickListener(new OnClickListener() {
	    	  SupervisorTO supervisorTemporal = (SupervisorTO)getItem(position);
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent profit = new Intent(context,MostrarDashBoardActivity.class);
					profit.putExtra(MostrarDashBoardActivity.TIPO_DASHBOARD, 1);
					profit.putExtra(MostrarDashBoardActivity.CODIGO_CDA, codigo_cda);
					profit.putExtra(MostrarDashBoardActivity.CODIGO_SUPERVISOR, supervisorTemporal.getCodigo());
					profit.putExtra(MostrarDashBoardActivity.NOMBRE_INDICADOR, MostrarDashBoardActivity.EFICIENCIA_PREVENTA);
					context.startActivity(profit);
				}
			});
	      
	      
	      return convertView;
	    }

	    static class ViewHolder {   
	    	TextView txViewCod;
	    	TextView txViewSup;
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
