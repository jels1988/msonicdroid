package pe.lindley.mmil.activity;

import java.util.List;
import com.google.inject.Inject;
import com.thira.examples.actionbar.widget.ActionBar;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import pe.lindley.activity.R;
import pe.lindley.mmil.to.PizarraDataTO;
import pe.lindley.mmil.ws.service.MostrarPizarraProxy;
import pe.lindley.util.ListActivityBase;
import roboguice.inject.InjectView;

public class MostrarPizarraActivity extends ListActivityBase {
	
	
	public static boolean CARGADO=false;
	
	
	@Override
	public void finish() {
		// TODO Auto-generated method stub
		CARGADO=false;
		super.finish();
	}
	
	@InjectView(R.id.actionBar)ActionBar mActionBar;
	@Inject MostrarPizarraProxy mostrarPizarraCdaProxy;
	//@Inject MostrarPizarraCdaProxy mostrarPizarraCdaProxy; 
	private EfficientAdapter adap;
	private static final int INDICADOR_1 = 0;
	private static final int INDICADOR_2 = 1;
	private static final int INDICADOR_3 = 2;
	private static final int INDICADOR_4 = 3;
	private static final int IND_VERDE = 0;
	private static final int IND_AMARRILLO = 1;
	private static final int IND_ROJO = 2;
	String codigoCda = "";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mmil_mostrar_pizarra_activity);
		mActionBar.setTitle(R.string.mostrar_pizarra_activity_title);
		mActionBar.setHomeLogo(R.drawable.header_logo);
		
		codigoCda =  getIntent().getData().getQueryParameter("cda");
		
		Log.v("MostrarPizarraActivity",codigoCda);
		
		processAsync();
	}
	
	@Override
	protected void process() {
		mostrarPizarraCdaProxy.setCodigoDeposito(codigoCda);
		mostrarPizarraCdaProxy.execute();
	}

	@Override
	protected void processOk() {
		// TODO Auto-generated method stub
		
		if(mostrarPizarraCdaProxy.getResponse()==null){
			processError();
			return;
		}
		
		boolean isExito = mostrarPizarraCdaProxy.isExito();
		if (isExito) {
			int status = mostrarPizarraCdaProxy.getResponse().getStatus();
			if (status == 0) {
				List<PizarraDataTO> pizarraData = mostrarPizarraCdaProxy
						.getResponse().getListaPizarra();
				adap = new EfficientAdapter(this, pizarraData);
				setListAdapter(adap);
			}
		else  {
			showToast(mostrarPizarraCdaProxy.getResponse().getDescripcion());
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
	    private List<PizarraDataTO> detalles;
	    
	    public EfficientAdapter(Context context, List<PizarraDataTO> valores) {
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
	    	PizarraDataTO pizarraDataTO = (PizarraDataTO) getItem(position);
	      ViewHolder holder;

	      if (convertView == null) {
	        convertView = mInflater.inflate(R.layout.mmil_mostrar_pizarra_content, null);

	        // Creates a ViewHolder and store references to the two children
	        // views
	        // we want to bind data to.
	        holder = new ViewHolder();
	        
	        holder.txViewCda = (TextView) convertView.findViewById(R.id.txViewCda);
	        holder.txViewSuprv =  (TextView) convertView.findViewById(R.id.txViewSuprv);
	        holder.txViewTelev =  (TextView) convertView.findViewById(R.id.txViewTelev);

	    	//  	indicador 1
	        holder.txViewIndSup1 = (TextView) convertView.findViewById(R.id.txViewIndSup1);
	        holder.imageIndSup1 = (ImageView) convertView.findViewById(R.id.imageIndSup1);	    	
	    	holder.txViewValSup1 = (TextView) convertView.findViewById(R.id.txViewValSup1);	    	
	    	holder.txViewIndTlv1 = (TextView) convertView.findViewById(R.id.txViewIndTlv1);
	        holder.imageIndTlv1 = (ImageView) convertView.findViewById(R.id.imageIndTlv1);	    	
	    	holder.txViewValTlv1 = (TextView) convertView.findViewById(R.id.txViewValTlv1);	    	
	    	holder.txViewVerInd1 = (TextView) convertView.findViewById(R.id.txViewVerInd1);	 
	    	
	    	// indicador 2
	    	holder.txViewIndSup2 = (TextView) convertView.findViewById(R.id.txViewIndSup2);
	        holder.imageIndSup2 = (ImageView) convertView.findViewById(R.id.imageIndSup2);	    	
	    	holder.txViewValSup2 = (TextView) convertView.findViewById(R.id.txViewValSup2);
	    	
	    	holder.txViewIndTlv2 = (TextView) convertView.findViewById(R.id.txViewIndTlv2);
	        holder.imageIndTlv2 = (ImageView) convertView.findViewById(R.id.imageIndTlv2);	    	
	    	holder.txViewValTlv2 = (TextView) convertView.findViewById(R.id.txViewValTlv2);	    	
	    	holder.txViewVerInd2 = (TextView) convertView.findViewById(R.id.txViewVerInd2);	   
	    	
	    	//indicador 3
	    	holder.txViewIndSup3 = (TextView) convertView.findViewById(R.id.txViewIndSup3);
	        holder.imageIndSup3 = (ImageView) convertView.findViewById(R.id.imageIndSup3);	    	
	    	holder.txViewValSup3 = (TextView) convertView.findViewById(R.id.txViewValSup3);
	    	
	    	holder.txViewIndTlv3 = (TextView) convertView.findViewById(R.id.txViewIndTlv3);
	        holder.imageIndTlv3 = (ImageView) convertView.findViewById(R.id.imageIndTlv3);	    	
	    	holder.txViewValTlv3 = (TextView) convertView.findViewById(R.id.txViewValTlv3);
	    	holder.txViewVerInd3 = (TextView) convertView.findViewById(R.id.txViewVerInd3);
	    	
	    	//indicador 4
	    	holder.txViewIndSup4 = (TextView) convertView.findViewById(R.id.txViewIndSup4);
	        holder.imageIndSup4 = (ImageView) convertView.findViewById(R.id.imageIndSup4);	    	
	    	holder.txViewValSup4 = (TextView) convertView.findViewById(R.id.txViewValSup4);
	    	
	    	holder.txViewIndTlv4 = (TextView) convertView.findViewById(R.id.txViewIndTlv4);
	        holder.imageIndTlv4 = (ImageView) convertView.findViewById(R.id.imageIndTlv4);	    	
	    	holder.txViewValTlv4 = (TextView) convertView.findViewById(R.id.txViewValTlv4);
	    	holder.txViewVerInd4 = (TextView) convertView.findViewById(R.id.txViewVerInd4);
	        
	        convertView.setTag(holder);
	      } else {
	        // Get the ViewHolder back to get fast access to the TextView
	        // and the ImageView.
	        holder = (ViewHolder) convertView.getTag();
	      }
	      
	      holder.txViewCda.setText(pizarraDataTO.getCda());
	      String detalleSuperivor = "<a href=''>" + pizarraDataTO.getNombreSupervisor()+" - "+pizarraDataTO.getCantidadSupervisor() + "</a>";    		  
	      holder.txViewSuprv.setText(Html.fromHtml(detalleSuperivor));	      
	      String detalleTeleventa = "<a href=''>" + pizarraDataTO.getNombreTeleventa()+" - "+pizarraDataTO.getCantidadTeleventa() + "</a>";
	      holder.txViewTelev.setText(Html.fromHtml(detalleTeleventa));	      
	      //holder.txViewSuprv.setText(pizarraDataTO.getNombreSupervisor()+" - "+pizarraDataTO.getCantidadSupervisor());
	      //holder.txViewTelev.setText(pizarraDataTO.getNombreTeleventa()+" - "+pizarraDataTO.getCantidadTeleventa());
	      
	      if(pizarraDataTO.getDetalleSupervisor()!=null)
	      {
	    	  //indicador 1
	    	  holder.txViewIndSup1.setText(pizarraDataTO.getDetalleSupervisor().get(INDICADOR_1).getIndicador());
		      switch(Integer.parseInt(pizarraDataTO.getDetalleSupervisor().get(INDICADOR_1).getColor()))
		      {
		      	case IND_VERDE:
		      		holder.imageIndSup1.setImageResource(R.drawable.icon_verde);
		    	  break;
		      	case IND_AMARRILLO:
		      		holder.imageIndSup1.setImageResource(R.drawable.icon_amarrillo);
		      		break;
		      	case IND_ROJO:
		      		holder.imageIndSup1.setImageResource(R.drawable.icon_rojo);
		      		break;	 
		       }
		      
		      holder.txViewValSup1.setText(pizarraDataTO.getDetalleSupervisor().get(INDICADOR_1).getValorReal()+"/"+pizarraDataTO.getDetalleSupervisor().get(INDICADOR_1).getValorEsperado());
		   
		      // indicador 2
		      holder.txViewIndSup2.setText(pizarraDataTO.getDetalleSupervisor().get(INDICADOR_2).getIndicador());
		      switch(Integer.parseInt(pizarraDataTO.getDetalleSupervisor().get(INDICADOR_2).getColor()))
		      {
		      	case IND_VERDE:
		      		holder.imageIndSup2.setImageResource(R.drawable.icon_verde);
		    	  break;
		      	case IND_AMARRILLO:
		      		holder.imageIndSup2.setImageResource(R.drawable.icon_amarrillo);
		      		break;
		      	case IND_ROJO:
		      		holder.imageIndSup2.setImageResource(R.drawable.icon_rojo);
		     		break;	 
		       }
		      
		      holder.txViewValSup2.setText(pizarraDataTO.getDetalleSupervisor().get(INDICADOR_2).getValorReal()+"/"+pizarraDataTO.getDetalleSupervisor().get(INDICADOR_2).getValorEsperado());
		  	
		    //indicador 3
		      
		      holder.txViewIndSup3.setText(pizarraDataTO.getDetalleSupervisor().get(INDICADOR_3).getIndicador());
		     switch(Integer.parseInt(pizarraDataTO.getDetalleSupervisor().get(INDICADOR_3).getColor()))
		      {
		      	case IND_VERDE:
		      		holder.imageIndSup3.setImageResource(R.drawable.icon_verde);
		    	  break;
		      	case IND_AMARRILLO:
		      		holder.imageIndSup3.setImageResource(R.drawable.icon_amarrillo);
		      		break;
		      	case IND_ROJO:
		      		holder.imageIndSup3.setImageResource(R.drawable.icon_rojo);
		      		break;	 
		       }
		      
		      holder.txViewValSup3.setText(pizarraDataTO.getDetalleSupervisor().get(INDICADOR_3).getValorReal()+"/"+pizarraDataTO.getDetalleSupervisor().get(INDICADOR_3).getValorEsperado());
		      
		    //indicador 4
		       
		       holder.txViewIndSup4.setText(pizarraDataTO.getDetalleSupervisor().get(INDICADOR_4).getIndicador());
			     switch(Integer.parseInt(pizarraDataTO.getDetalleSupervisor().get(INDICADOR_4).getColor()))
			      {
			      	case IND_VERDE:
			      		holder.imageIndSup4.setImageResource(R.drawable.icon_verde);
			    	  break;
			      	case IND_AMARRILLO:
			      		holder.imageIndSup4.setImageResource(R.drawable.icon_amarrillo);
			      		break;
			      	case IND_ROJO:
			      		holder.imageIndSup4.setImageResource(R.drawable.icon_rojo);
			      		break;	 
			       }
			      
			      holder.txViewValSup4.setText(pizarraDataTO.getDetalleSupervisor().get(INDICADOR_4).getValorReal()+"/"+pizarraDataTO.getDetalleSupervisor().get(INDICADOR_4).getValorEsperado());
			     		  	
	      }
	      
	      if(pizarraDataTO.getDetalleTeleventas()!=null)
	      {
	    	  holder.txViewIndTlv1.setText(pizarraDataTO.getDetalleTeleventas().get(INDICADOR_1).getIndicador());
		      switch(Integer.parseInt(pizarraDataTO.getDetalleTeleventas().get(INDICADOR_1).getColor()))
		      {
		      	case IND_VERDE:
		      		holder.imageIndTlv1.setImageResource(R.drawable.icon_verde);
		    	  break;
		      	case IND_AMARRILLO:
		      		holder.imageIndTlv1.setImageResource(R.drawable.icon_amarrillo);
		      		break;
		      	case IND_ROJO:
		      		holder.imageIndTlv1.setImageResource(R.drawable.icon_rojo);
		      		break;	 
		       }
		      holder.txViewValTlv1.setText(pizarraDataTO.getDetalleTeleventas().get(INDICADOR_1).getValorReal()+"/"+pizarraDataTO.getDetalleTeleventas().get(INDICADOR_1).getValorEsperado());
		      
		      holder.txViewIndTlv2.setText(pizarraDataTO.getDetalleTeleventas().get(INDICADOR_2).getIndicador());
		      switch(Integer.parseInt(pizarraDataTO.getDetalleTeleventas().get(INDICADOR_2).getColor()))
		      {
		      	case IND_VERDE:
		      		holder.imageIndTlv2.setImageResource(R.drawable.icon_verde);
		    	  break;
		      	case IND_AMARRILLO:
		      		holder.imageIndTlv2.setImageResource(R.drawable.icon_amarrillo);
		      		break;
		      	case IND_ROJO:
		      		holder.imageIndTlv2.setImageResource(R.drawable.icon_rojo);
		      		break;	 
		       }
		      holder.txViewValTlv2.setText(pizarraDataTO.getDetalleTeleventas().get(INDICADOR_2).getValorReal()+"/"+pizarraDataTO.getDetalleTeleventas().get(INDICADOR_2).getValorEsperado());
		   
		      holder.txViewIndTlv3.setText(pizarraDataTO.getDetalleTeleventas().get(INDICADOR_3).getIndicador());
		      switch(Integer.parseInt(pizarraDataTO.getDetalleTeleventas().get(INDICADOR_3).getColor()))
		      {
		      	case IND_VERDE:
		      		holder.imageIndTlv3.setImageResource(R.drawable.icon_verde);
		    	  break;
		      	case IND_AMARRILLO:
		      		holder.imageIndTlv3.setImageResource(R.drawable.icon_amarrillo);
		      		break;
		      	case IND_ROJO:
		      		holder.imageIndTlv3.setImageResource(R.drawable.icon_rojo);
		      		break;	 
		       }
		       holder.txViewValTlv3.setText(pizarraDataTO.getDetalleTeleventas().get(INDICADOR_3).getValorReal()+"/"+pizarraDataTO.getDetalleTeleventas().get(INDICADOR_3).getValorEsperado());
		      	  
		       holder.txViewIndTlv4.setText(pizarraDataTO.getDetalleTeleventas().get(INDICADOR_4).getIndicador());
			    switch(Integer.parseInt(pizarraDataTO.getDetalleTeleventas().get(INDICADOR_4).getColor()))
			      {
			      	case IND_VERDE:
			      		holder.imageIndTlv4.setImageResource(R.drawable.icon_verde);
			    	  break;
			      	case IND_AMARRILLO:
			      		holder.imageIndTlv4.setImageResource(R.drawable.icon_amarrillo);
			      		break;
			      	case IND_ROJO:
			      		holder.imageIndTlv4.setImageResource(R.drawable.icon_rojo);
			      		break;	 
			       }
			       holder.txViewValTlv4.setText(pizarraDataTO.getDetalleTeleventas().get(INDICADOR_4).getValorReal()+"/"+pizarraDataTO.getDetalleTeleventas().get(INDICADOR_4).getValorEsperado());
			      	   	
	      }      
	      
	      holder.txViewVerInd1.setOnClickListener(new OnClickListener() {
	    	  PizarraDataTO pizarratemporal = (PizarraDataTO)getItem(position);

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub					
					Intent profit = new Intent(context,MostrarDashBoardActivity.class);
					profit.putExtra(MostrarDashBoardActivity.TIPO_DASHBOARD, 0);
					profit.putExtra(MostrarDashBoardActivity.CODIGO_CDA, pizarratemporal.getCodigoDeposito());
					profit.putExtra(MostrarDashBoardActivity.NOMBRE_INDICADOR, pizarratemporal.getDetalleSupervisor().get(INDICADOR_1).getIndicador());
					context.startActivity(profit);
				}
			});
	      
	      holder.txViewVerInd2.setOnClickListener(new OnClickListener() {
	    	  PizarraDataTO pizarratemporal = (PizarraDataTO)getItem(position);

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent profit = new Intent(context,MostrarDashBoardActivity.class);
					profit.putExtra(MostrarDashBoardActivity.TIPO_DASHBOARD, 0);
					profit.putExtra(MostrarDashBoardActivity.CODIGO_CDA, pizarratemporal.getCodigoDeposito());
					profit.putExtra(MostrarDashBoardActivity.NOMBRE_INDICADOR, pizarratemporal.getDetalleSupervisor().get(INDICADOR_2).getIndicador());
					context.startActivity(profit);
				}
			});
	      
	      holder.txViewVerInd3.setOnClickListener(new OnClickListener() {
	    	  PizarraDataTO pizarratemporal = (PizarraDataTO)getItem(position);

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent profit = new Intent(context,MostrarDashBoardActivity.class);
					profit.putExtra(MostrarDashBoardActivity.TIPO_DASHBOARD, 0);
					profit.putExtra(MostrarDashBoardActivity.CODIGO_CDA, pizarratemporal.getCodigoDeposito());
					profit.putExtra(MostrarDashBoardActivity.NOMBRE_INDICADOR, pizarratemporal.getDetalleSupervisor().get(INDICADOR_3).getIndicador());
					context.startActivity(profit);
				}
			});
	      
	      holder.txViewVerInd4.setOnClickListener(new OnClickListener() {
	    	  PizarraDataTO pizarratemporal = (PizarraDataTO)getItem(position);

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent profit = new Intent(context,MostrarDashBoardActivity.class);
					profit.putExtra(MostrarDashBoardActivity.TIPO_DASHBOARD, 0);
					profit.putExtra(MostrarDashBoardActivity.CODIGO_CDA, pizarratemporal.getCodigoDeposito());
					profit.putExtra(MostrarDashBoardActivity.NOMBRE_INDICADOR, pizarratemporal.getDetalleSupervisor().get(INDICADOR_4).getIndicador());
					context.startActivity(profit);
				}
			});
	      
	      holder.txViewSuprv.setOnClickListener(new OnClickListener() {
	    	  PizarraDataTO pizarratemporal = (PizarraDataTO)getItem(position);

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent profit = new Intent(context,MostrarSupervisorActivity.class);
					profit.putExtra(MostrarSupervisorActivity.TIPO_SUPERVISOR, 0);
					profit.putExtra(MostrarSupervisorActivity.CODIGO_CDA, pizarratemporal.getCodigoDeposito());
					profit.putExtra(MostrarSupervisorActivity.NOMBRE_CDA, pizarratemporal.getCda());
					context.startActivity(profit);
				}
			});
	      
	      holder.txViewTelev.setOnClickListener(new OnClickListener() {
	    	  PizarraDataTO pizarratemporal = (PizarraDataTO)getItem(position);

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent profit = new Intent(context,MostrarTeleventaActivity.class);
					profit.putExtra(MostrarTeleventaActivity.TIPO_SUPERVISOR, 1);
					profit.putExtra(MostrarTeleventaActivity.CODIGO_CDA, pizarratemporal.getCodigoDeposito());
					profit.putExtra(MostrarTeleventaActivity.CODIGO_SUPERVISOR, MostrarTeleventaActivity.COD_TELEVENTA);
					profit.putExtra(MostrarTeleventaActivity.NOMBRE_CDA, pizarratemporal.getCda());
					context.startActivity(profit);
				}
			});
	      
	      return convertView;
	    }

	    static class ViewHolder {
	    	TextView txViewCda;
	    	TextView txViewSuprv;
	    	TextView txViewTelev;

	    	//  	indicador 1
	    	TextView txViewIndSup1;
	    	ImageView imageIndSup1;	    	
	    	TextView txViewValSup1;
	    	
	    	TextView txViewIndTlv1;
	    	ImageView imageIndTlv1;
	    	TextView txViewValTlv1;
	    	
	    	TextView txViewVerInd1;
	    	
	    	// indicador 2
	    	TextView txViewIndSup2;
	    	ImageView imageIndSup2;
	    	TextView txViewValSup2;
	    	
	    	TextView txViewIndTlv2;
	    	ImageView imageIndTlv2;
	    	TextView txViewValTlv2;
	    	
	    	TextView txViewVerInd2;
	    	
	    	//indicador 3
	    	TextView txViewIndSup3;
	    	ImageView imageIndSup3;
	    	TextView txViewValSup3;
	    	
	    	TextView txViewIndTlv3;
	    	ImageView imageIndTlv3;	    	 
	    	TextView txViewValTlv3;
	    	
	    	TextView txViewVerInd3;
	    	
	    	//indicador 4
	    	TextView txViewIndSup4;
	    	ImageView imageIndSup4;
	    	TextView txViewValSup4;
	    	
	    	TextView txViewIndTlv4;
	    	ImageView imageIndTlv4;
	    	TextView txViewValTlv4;
	    	
	    	TextView txViewVerInd4;
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
