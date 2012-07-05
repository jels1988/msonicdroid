package pe.lindley.mmil.titanium;

import java.util.ArrayList;
import pe.lindley.mmil.titanium.to.ResumenVentaTO;
import pe.lindley.mmil.titanium.ws.service.ResumenMercaderistasProxy;
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

import com.actionbarsherlock.view.MenuItem.OnMenuItemClickListener;
import com.google.inject.Inject;
import com.thira.examples.actionbar.widget.ActionBar;


public class ResumenMercaderistaActivity extends net.msonic.lib.sherlock.ListActivityBase {
	
	public static final String NOMBRE_CDA_KEY="NOMBRE_CDA";
	public static final String CODIGO_DEPOSITO_KEY = "CODIGO_DEPOSITO";
	public static final String CODIGO_SUPERVISOR_KEY = "CODIGO_SUPERVISOR";
	
	@InjectExtra(CODIGO_SUPERVISOR_KEY) String codigoSupervisor;
	@InjectExtra(CODIGO_DEPOSITO_KEY) String codigoCda;
	@InjectExtra(NOMBRE_CDA_KEY) String nombre_cda;
	
	
	
	@Inject ResumenMercaderistasProxy resumenMercaderistasProxy;
	
	//@InjectView(R.id.actionBar)  		ActionBar 	mActionBar;
	  /** Called when the activity is first created. */
  @Override
  public void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      inicializarRecursos();
      
      setContentView(R.layout.resumenventa_activity);
      
     // mActionBar.setHomeLogo(R.drawable.header_logo);
      
      setTitle(R.string.resumen_mercaderista_activity_title);
      getSupportActionBar().setSubtitle(nombre_cda);
      
      /*
      mActionBar.setTitle(R.string.resumen_mercaderista_activity_title);
      mActionBar.setSubTitle(nombre_cda);
      */
      processAsync();
      
  }
	
  
  @Override
	protected void process() {
		// TODO Auto-generated method stub
	  resumenMercaderistasProxy.codigoDeposito=codigoCda;
	  resumenMercaderistasProxy.codigoSupervisor=codigoSupervisor;
	  resumenMercaderistasProxy.execute();
		
	}

  
  @Override
	protected void processOk() {
		String message;
		
		boolean isExito = resumenMercaderistasProxy.isExito();
		if (isExito) {
			int status = resumenMercaderistasProxy.getResponse().getStatus();
			if (status == 0) {
				
				ArrayList<ResumenVentaTO> resumenVenta = resumenMercaderistasProxy.getResponse().resumenVenta;
				ResumenVenta_Adapter adapterSupervisor = new ResumenVenta_Adapter(this, resumenVenta);
				getListView().setAdapter(adapterSupervisor);
				
				super.processOk();
			}else{
				message = resumenMercaderistasProxy.getResponse().getDescripcion();
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
		if(resumenMercaderistasProxy.getResponse()!=null){
			String error = resumenMercaderistasProxy.getResponse().getDescripcion();
			message = error;
		}else{
			message = error_generico_process;
		}
		super.processError();
		showToast(message);
	}
  
	
	@Override
	public boolean onCreateOptionsMenu(com.actionbarsherlock.view.Menu menu) {
		// TODO Auto-generated method stub
		
		getSupportMenuInflater().inflate(R.menu.resumen_venta_menu, menu);
		
		

        menu.add("Refresh")
            .setIcon(R.drawable.reload)
            .setOnMenuItemClickListener(new OnMenuItemClickListener() {
				
				@Override
				public boolean onMenuItemClick(com.actionbarsherlock.view.MenuItem item) {
					// TODO Auto-generated method stub
					processAsync();
					return true;
				}
			})
            .setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM | MenuItem.SHOW_AS_ACTION_WITH_TEXT);
            
        	

        
		/*
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.resumen_venta_menu, menu);
		*/
		return true;
	
	}
  


	@Override
	public boolean onOptionsItemSelected(com.actionbarsherlock.view.MenuItem item) {
	    // Handle item selection
	    switch (item.getItemId()) {
	        case R.id.mnuVentas:
	        	
	        	Intent intent1 = new Intent(this, ResumenVentaActivity.class);
	        	intent1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
	        	intent1.putExtra(ResumenMercaderistaActivity.CODIGO_SUPERVISOR_KEY, codigoSupervisor);
	        	intent1.putExtra(ResumenMercaderistaActivity.CODIGO_DEPOSITO_KEY, codigoCda);
	        	intent1.putExtra(ResumenMercaderistaActivity.NOMBRE_CDA_KEY, nombre_cda);
		    	startActivity(intent1);
		    	
	            return true;
	        case R.id.mnuMix:
	        	
	        	Intent intent2 = new Intent(this, MixProductoActivity.class);
	        	intent2.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
	        	intent2.putExtra(ResumenMercaderistaActivity.CODIGO_SUPERVISOR_KEY, codigoSupervisor);
	        	intent2.putExtra(ResumenMercaderistaActivity.CODIGO_DEPOSITO_KEY, codigoCda);
	        	intent2.putExtra(ResumenMercaderistaActivity.NOMBRE_CDA_KEY, nombre_cda);
		    	startActivity(intent2);
		    	
	            return true;
	        case R.id.mnuVendedores:
	        	Intent intent = new Intent(this, ListaVendedoresActivity.class);
	        	intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				intent.putExtra(ListaVendedoresActivity.CODIGO_SUPERVISOR_KEY, codigoSupervisor);
				intent.putExtra(ListaVendedoresActivity.CODIGO_CDA_KEY, codigoCda);
				intent.putExtra(ListaVendedoresActivity.NOMBRE_CDA_KEY, nombre_cda);
		    	startActivity(intent);
	            return true;
	        case R.id.mnuConfrontacion:
	        	Intent intent3 = new Intent(this, ConfrontacionActivity.class);
	        	intent3.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
	        	intent3.putExtra(ConfrontacionActivity.CODIGO_SUPERVISOR_KEY, codigoSupervisor);
	        	intent3.putExtra(ConfrontacionActivity.CODIGO_DEPOSITO_KEY, codigoCda);
	        	intent3.putExtra(ConfrontacionActivity.NOMBRE_CDA_KEY, nombre_cda);
		    	startActivity(intent3);
	        	return true;
	        case R.id.mnuConsultas:
	        	Intent intent5 = new Intent(this, ConsultasActivity.class);
	        	intent5.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
	        	intent5.putExtra(ConsultasActivity.CODIGO_SUPERVISOR_KEY, codigoSupervisor);
	        	intent5.putExtra(ConsultasActivity.CODIGO_DEPOSITO_KEY, codigoCda);
	        	intent5.putExtra(ConsultasActivity.NOMBRE_CDA_KEY, nombre_cda);
		    	startActivity(intent5);
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
				
				holder.imgIndicador.setImageResource(R.drawable.icon_white);
				
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