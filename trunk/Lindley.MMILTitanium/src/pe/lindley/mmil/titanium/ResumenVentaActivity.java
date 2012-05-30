package pe.lindley.mmil.titanium;

import java.util.ArrayList;
import com.google.inject.Inject;
import com.thira.examples.actionbar.widget.ActionBar;
import pe.lindley.mmil.titanium.to.ResumenVentaTO;
import pe.lindley.mmil.titanium.ws.service.ResumenVendedoresProxy;
import roboguice.inject.InjectExtra;
import roboguice.inject.InjectView;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import net.msonic.lib.ListActivityBase;

public class ResumenVentaActivity extends ListActivityBase {
	
	public static final String NOMBRE_CDA_KEY="NOMBRE_CDA";
	public static final String CODIGO_DEPOSITO_KEY = "CODIGO_DEPOSITO";
	public static final String CODIGO_SUPERVISOR_KEY = "CODIGO_SUPERVISOR";
	
	@InjectExtra(CODIGO_SUPERVISOR_KEY) String codigoSupervisor;
	@InjectExtra(CODIGO_DEPOSITO_KEY) String codigoCda;
	@InjectExtra(NOMBRE_CDA_KEY) String nombre_cda;
	
	
	@Inject ResumenVendedoresProxy resumenVentaProxy;
	
	@InjectView(R.id.actionBar)  		ActionBar 	mActionBar;
	  /** Called when the activity is first created. */
  @Override
  public void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      inicializarRecursos();
      
      setContentView(R.layout.resumenventa_activity);
      
      mActionBar.setHomeLogo(R.drawable.header_logo);
      mActionBar.setTitle(R.string.resumen_venta_activity_title);
      mActionBar.setSubTitle(nombre_cda);
      
      processAsync();
      
  }
	
  
  @Override
	protected void process() {
		// TODO Auto-generated method stub
	  resumenVentaProxy.codigoDeposito=codigoCda;
	  resumenVentaProxy.codigoSupervisor=codigoSupervisor;
	  resumenVentaProxy.execute();
		
	}

  
  @Override
	protected void processOk() {
		String message;
		
		boolean isExito = resumenVentaProxy.isExito();
		if (isExito) {
			int status = resumenVentaProxy.getResponse().getStatus();
			if (status == 0) {
				
				ArrayList<ResumenVentaTO> resumenVenta = resumenVentaProxy.getResponse().resumenVenta;
				ResumenVenta_Adapter adapterSupervisor = new ResumenVenta_Adapter(this, resumenVenta);
				getListView().setAdapter(adapterSupervisor);
				
				super.processOk();
			}else{
				message = resumenVentaProxy.getResponse().getDescripcion();
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
		if(resumenVentaProxy.getResponse()!=null){
			String error = resumenVentaProxy.getResponse().getDescripcion();
			message = error;
		}else{
			message = error_generico_process;
		}
		super.processError();
		showToast(message);
	}
  
  
  
  
  
  
  
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.resumen_venta_menu, menu);
		return true;
	}






	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle item selection
	    switch (item.getItemId()) {
	        case R.id.mnuMercaderista:
	            return true;
	        case R.id.mnuMix:
	            return true;
	        case R.id.mnuVendedores:
	        	Intent intent = new Intent(this, ListaVendedoresActivity.class);
				intent.putExtra(ListaVendedoresActivity.CODIGO_SUPERVISOR_KEY, codigoSupervisor);
				intent.putExtra(ListaVendedoresActivity.CODIGO_CDA_KEY, codigoCda);
				intent.putExtra(ListaVendedoresActivity.NOMBRE_CDA_KEY, nombre_cda);
		    	startActivity(intent);
	            return true;
	        case R.id.mnuConsultas:
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}






	public static class ResumenVenta_Adapter extends ArrayAdapter<ResumenVentaTO>{
		
		   private final ArrayList<ResumenVentaTO> detalle;
		   private final Activity context;
		
					
		   private static final int IND_VERDE = 3;
		   private static final int IND_AMARRILLO = 2;
		   private static final int IND_ROJO = 1;
		   
		   
		   public ResumenVenta_Adapter(Activity context,ArrayList<ResumenVentaTO> detalle){
				super(context, R.layout.resumenventa_content, detalle);
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
			
			public ResumenVentaTO getItem(int position) {
				// TODO Auto-generated method stub
				return this.detalle.get(position);
			}
			
			public long getItemId(int position) {
				// TODO Auto-generated method stub
				return 0;
			}
			
			
			
			
			public View getView(int position, View convertView, ViewGroup parent) {
				// TODO Auto-generated method stub
				
				View view = null;	
				
				if (convertView == null) {
					
					LayoutInflater inflator = context.getLayoutInflater();
					view = inflator.inflate(R.layout.resumenventa_content, null);
					
					final ViewHolder holder = new ViewHolder();
					
					holder.imgIndicador = (ImageView)view.findViewById(R.id.imgIndicador);
					holder.txtDescripcion = (TextView) view.findViewById(R.id.txtDescripcion);
					holder.txtValor = (TextView)view.findViewById(R.id.txtValor);
					
					view.setTag(holder);
					holder.txtValor.setTag(this.detalle.get(position));
					
				}else{
					view = convertView;
					((ViewHolder) view.getTag()).txtValor.setTag(this.detalle.get(position));
				}
				
				ViewHolder holder = (ViewHolder) view.getTag();
				
				holder.txtDescripcion.setText(this.detalle.get(position).descripcion);
				holder.txtValor.setText(this.detalle.get(position).valor);
				
				
				
				 switch(Integer.parseInt(this.detalle.get(position).indicador))
			      {
			      	case IND_VERDE:
			      		holder.imgIndicador.setImageResource(R.drawable.icon_verde);
			    	  break;
			      	case IND_AMARRILLO:
			      		holder.imgIndicador.setImageResource(R.drawable.icon_amarrillo);
			      		break;
			      	case IND_ROJO:
			      		holder.imgIndicador.setImageResource(R.drawable.icon_rojo);
			      		break;	 
			       }
				return view;
				
			}
			
			
			private class ViewHolder {
				 public ImageView imgIndicador;
				 public TextView txtDescripcion;
				 public TextView txtValor;
					 
			 }
			
	}

}
