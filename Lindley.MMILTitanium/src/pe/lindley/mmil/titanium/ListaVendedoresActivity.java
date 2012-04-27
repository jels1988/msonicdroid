package pe.lindley.mmil.titanium;

import java.util.ArrayList;

import pe.lindley.mmil.titanium.to.VendedorTO;
import pe.lindley.mmil.titanium.ws.service.ListarVendedorProxy;
import roboguice.inject.InjectExtra;
import roboguice.inject.InjectView;

import com.google.inject.Inject;
import com.thira.examples.actionbar.widget.ActionBar;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import net.msonic.lib.ListActivityBase;

public class ListaVendedoresActivity extends ListActivityBase {
	
	public static final String CODIGO_SUPERVISOR_KEY="CODIGO_SUPERVISOR";
	public static final String CODIGO_CDA_KEY="CODIGO_CDA_KEY";
	
	@InjectView(R.id.actionBar)  	ActionBar 	mActionBar;
	@Inject ListarVendedorProxy listarVendedorProxy;
	ListView 	lstSupervisor;
	Tablero_Adapter adapter = null;
	
	
	@InjectExtra(CODIGO_SUPERVISOR_KEY) String codigoSupervisor;
	@InjectExtra(CODIGO_CDA_KEY) String codigoCda;
	
	 @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        inicializarRecursos();
	        
	        setContentView(R.layout.listavendedores_activity);
	        
	        mActionBar.setHomeLogo(R.drawable.header_logo);
	        mActionBar.setTitle(R.string.lista_vendedores_activity_title);

	        
	        lstSupervisor = getListView();
	        processAsync();
	    }
	 
	 
	 @Override
		protected void process() {
			// TODO Auto-generated method stub
		 listarVendedorProxy.codigoDeposito=codigoCda;
		 listarVendedorProxy.codigoSupervisor=codigoSupervisor;
		 listarVendedorProxy.tipo="0";
		 listarVendedorProxy.execute();
			 
			super.process();
		}
	 
	 
		@Override
		protected void processOk() {
			String message;
			boolean isExito = listarVendedorProxy.isExito();
			if (isExito) {
				int status = listarVendedorProxy.getResponse().getStatus();
				if (status == 0) {
					
					adapter = new Tablero_Adapter(this, listarVendedorProxy.getResponse().listaVendedor,lstSupervisor);
					lstSupervisor.setAdapter(adapter);
		
					
					 
					super.processOk();
				}else{
					message = listarVendedorProxy.getResponse().getDescripcion();
					super.processOk();
					showToast(message);
				}
			}else{
				processError();
			}
			
		}
		
		
	 
		@Override
		protected void processError() {
			String message;
			if(listarVendedorProxy.getResponse()!=null){
				String error = listarVendedorProxy.getResponse().getDescripcion();
				message = error;
			}else{
				message = error_generico_process;
			}
			super.processError();
			showToast(message);
		}
		
		
	 
	 public static class Tablero_Adapter extends ArrayAdapter<VendedorTO>{
		   private final ArrayList<VendedorTO> detalle;
		   private final Activity context;
		
					
		   private static final int IND_VERDE = 0;
		   private static final int IND_AMARRILLO = 1;
		   private static final int IND_ROJO = 2;
		   
			public Tablero_Adapter(Activity context,ArrayList<VendedorTO> detalle,ListView l){
				super(context, R.layout.listavendedores_content, detalle);
				this.detalle = detalle;
				this.context = context;
			
			}
			
			
			
			public int getCount() {
				// TODO Auto-generated method stub
				if (detalle == null) {
					return 0;
				} else {
					return detalle.size();
				}
			}
			
			
			public VendedorTO getItem(int position) {
				// TODO Auto-generated method stub
				return this.detalle.get(position);
			}
			
			
			public long getItemId(int position) {
				// TODO Auto-generated method stub
				return position;
			}
			
			
			Integer selectedLocation = null;

			public void onItemClick(AdapterView<?> adapter, View view, int location,
			                long arg3) {
			            selectedLocation = location;
			        }
			
		
			
			public View getView(int position, View convertView, ViewGroup parent) {
				// TODO Auto-generated method stub
				
				View view = null;	
				
			    
				
				if (convertView == null) {
					
					LayoutInflater inflator = context.getLayoutInflater();
					view = inflator.inflate(R.layout.listavendedores_content, null);
				    
				    
					final ViewHolder holder = new ViewHolder();
					
					view.setClickable(true);
					holder.txtCodigo = (TextView) view.findViewById(R.id.txtCodigo);
					holder.txtNombre = (TextView) view.findViewById(R.id.txtNombre);
					holder.txtEstado = (TextView) view.findViewById(R.id.txViewEstado);
					holder.imgEstado = (ImageView) view.findViewById(R.id.imgEstado);
					
					holder.txCuota = (TextView) view.findViewById(R.id.txCuota);
					holder.imgCuota = (ImageView) view.findViewById(R.id.imgCuota);
					
					holder.txtEficienciaGlobal = (TextView) view.findViewById(R.id.txtEficienciaGlobal);
					holder.imgEficienciaGlobal = (ImageView) view.findViewById(R.id.imgEficienciaGlobal);
					
					holder.txtPlanVisita = (TextView) view.findViewById(R.id.txtPlanVisita);
					holder.imgPlanVisita = (ImageView) view.findViewById(R.id.imgPlanVisita);
					
					holder.txtEficienciaPreventa = (TextView) view.findViewById(R.id.txtEficienciaPreventa);
					holder.imgEficienciaPreventa = (ImageView) view.findViewById(R.id.imgEficienciaPreventa);
					
					
					view.setTag(holder);
					holder.txtCodigo.setTag(this.detalle.get(position));
				}else{
					view = convertView;
					((ViewHolder) view.getTag()).txtCodigo.setTag(this.detalle.get(position));
				}
				
				
				
				VendedorTO vendedorTO = this.detalle.get(position);
				
				ViewHolder holder = (ViewHolder) view.getTag();
				holder.txtCodigo.setText(String.valueOf(vendedorTO.codigo));
				holder.txtNombre.setText(vendedorTO.nombre);
				holder.txtEstado.setText(vendedorTO.estado);
				
				if(vendedorTO.estado!=null){
				 switch(Integer.parseInt(vendedorTO.estado))
			      {
			      	case IND_VERDE:
			      		holder.imgEstado.setImageResource(R.drawable.icon_verde);
			    	  break;
			      	case IND_AMARRILLO:
			      		holder.imgEstado.setImageResource(R.drawable.icon_amarrillo);
			      		break;
			      	case IND_ROJO:
			      		holder.imgEstado.setImageResource(R.drawable.icon_rojo);
			      		break;	 
			       }	
				}
				
				holder.txCuota.setText(vendedorTO.cuota);
				
				if(vendedorTO.cuotaColor!=null){
					 switch(Integer.parseInt(vendedorTO.cuotaColor))
				      {
				      	case IND_VERDE:
				      		holder.imgCuota.setImageResource(R.drawable.icon_verde);
				    	  break;
				      	case IND_AMARRILLO:
				      		holder.imgCuota.setImageResource(R.drawable.icon_amarrillo);
				      		break;
				      	case IND_ROJO:
				      		holder.imgCuota.setImageResource(R.drawable.icon_rojo);
				      		break;	 
				       }	
					}
				
				holder.txCuota = (TextView) view.findViewById(R.id.txCuota);
				holder.imgCuota = (ImageView) view.findViewById(R.id.imgCuota);
				
				
				holder.txtEficienciaGlobal.setText(vendedorTO.eficGlobal);
				if(vendedorTO.eficGlobalColor!=null){
					 switch(Integer.parseInt(vendedorTO.eficGlobalColor))
				      {
				      	case IND_VERDE:
				      		holder.imgEficienciaGlobal.setImageResource(R.drawable.icon_verde);
				    	  break;
				      	case IND_AMARRILLO:
				      		holder.imgEficienciaGlobal.setImageResource(R.drawable.icon_amarrillo);
				      		break;
				      	case IND_ROJO:
				      		holder.imgEficienciaGlobal.setImageResource(R.drawable.icon_rojo);
				      		break;	 
				       }	
					}
				
				holder.txtPlanVisita.setText(vendedorTO.planVisita);
				if(vendedorTO.planVisitaColor!=null){
					 switch(Integer.parseInt(vendedorTO.eficGlobalColor))
				      {
				      	case IND_VERDE:
				      		holder.imgPlanVisita.setImageResource(R.drawable.icon_verde);
				    	  break;
				      	case IND_AMARRILLO:
				      		holder.imgPlanVisita.setImageResource(R.drawable.icon_amarrillo);
				      		break;
				      	case IND_ROJO:
				      		holder.imgPlanVisita.setImageResource(R.drawable.icon_rojo);
				      		break;	 
				       }	
					}
				
				holder.txtEficienciaPreventa.setText(vendedorTO.eficPreventa);
				if(vendedorTO.planVisitaColor!=null){
					 switch(Integer.parseInt(vendedorTO.eficPreventaColor))
				      {
				      	case IND_VERDE:
				      		holder.imgEficienciaPreventa.setImageResource(R.drawable.icon_verde);
				    	  break;
				      	case IND_AMARRILLO:
				      		holder.imgEficienciaPreventa.setImageResource(R.drawable.icon_amarrillo);
				      		break;
				      	case IND_ROJO:
				      		holder.imgEficienciaPreventa.setImageResource(R.drawable.icon_rojo);
				      		break;	 
				       }	
					}
				
				
				return view;
				
			}
			
			private class ViewHolder {
				public TextView txtCodigo;
				public TextView txtNombre;
				
				public ImageView imgEstado;
				public TextView txtEstado;
				
				
				public ImageView imgCuota;
				public TextView txCuota;
				
				public ImageView imgEficienciaGlobal;
				public TextView txtEficienciaGlobal;
				
				public ImageView imgPlanVisita;
				public TextView txtPlanVisita;
				
				
				public ImageView imgEficienciaPreventa;
				public TextView txtEficienciaPreventa;
				
				
		
			}
		   
	 }
}

