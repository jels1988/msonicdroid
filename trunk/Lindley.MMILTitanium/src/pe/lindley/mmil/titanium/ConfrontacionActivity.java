package pe.lindley.mmil.titanium;

import java.util.ArrayList;

import pe.lindley.mmil.titanium.to.ConfrontacionTO;
import pe.lindley.mmil.titanium.ws.service.ConfrontacionProxy;
import roboguice.inject.InjectExtra;
import roboguice.inject.InjectView;
import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.google.inject.Inject;
import com.thira.examples.actionbar.widget.ActionBar;

import net.msonic.lib.ListActivityBase;

public class ConfrontacionActivity extends ListActivityBase {
	
	public static final String NOMBRE_CDA_KEY="NOMBRE_CDA";
	public static final String CODIGO_DEPOSITO_KEY = "CODIGO_DEPOSITO";
	public static final String CODIGO_SUPERVISOR_KEY = "CODIGO_SUPERVISOR";
	
	@InjectExtra(CODIGO_SUPERVISOR_KEY) String codigoSupervisor;
	@InjectExtra(CODIGO_DEPOSITO_KEY) String codigoCda;
	@InjectExtra(NOMBRE_CDA_KEY) String nombre_cda;
	
	
	@Inject ConfrontacionProxy confrontacionProxy;
	
	@InjectView(R.id.actionBar)  		ActionBar 	mActionBar;
	  /** Called when the activity is first created. */
  @Override
  public void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      inicializarRecursos();
      
      setContentView(R.layout.confrontacion_activity);
      
      mActionBar.setHomeLogo(R.drawable.header_logo);
      mActionBar.setTitle(R.string.confrontacion_activity_title);
      mActionBar.setSubTitle(nombre_cda);
      
      processAsync();
      
  }
	
  
  @Override
	protected void process() {
		// TODO Auto-generated method stub
	  confrontacionProxy.codigoDeposito=codigoCda;
	  confrontacionProxy.codigoSupervisor=codigoSupervisor;
	  confrontacionProxy.execute();
		
	}

  
  @Override
	protected void processOk() {
		String message;
		
		boolean isExito = confrontacionProxy.isExito();
		if (isExito) {
			int status = confrontacionProxy.getResponse().getStatus();
			if (status == 0) {
				
				ArrayList<ConfrontacionTO> confrontacion = confrontacionProxy.getResponse().confrontacion;
				Confrontaciono_Adapter adapterSupervisor = new Confrontaciono_Adapter(this, confrontacion);
				getListView().setAdapter(adapterSupervisor);
				
				super.processOk();
			}else{
				message = confrontacionProxy.getResponse().getDescripcion();
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
		if(confrontacionProxy.getResponse()!=null){
			String error = confrontacionProxy.getResponse().getDescripcion();
			message = error;
		}else{
			message = error_generico_process;
		}
		super.processError();
		showToast(message);
	}
	
	
	/*
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.mix_producto_menu, menu);
		return true;
	}






	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle item selection
	    switch (item.getItemId()) {
	    case R.id.mnuVentas:
        	
        	Intent intent2 = new Intent(this, ResumenVentaActivity.class);
        	intent2.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        	intent2.putExtra(ResumenMercaderistaActivity.CODIGO_SUPERVISOR_KEY, codigoSupervisor);
        	intent2.putExtra(ResumenMercaderistaActivity.CODIGO_DEPOSITO_KEY, codigoCda);
        	intent2.putExtra(ResumenMercaderistaActivity.NOMBRE_CDA_KEY, nombre_cda);
	    	startActivity(intent2);
	    	
            return true;
	        case R.id.mnuMercaderista:
	        	
	        	Intent intent1 = new Intent(this, ResumenMercaderistaActivity.class);
	        	intent1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
	        	intent1.putExtra(ResumenMercaderistaActivity.CODIGO_SUPERVISOR_KEY, codigoSupervisor);
	        	intent1.putExtra(ResumenMercaderistaActivity.CODIGO_DEPOSITO_KEY, codigoCda);
	        	intent1.putExtra(ResumenMercaderistaActivity.NOMBRE_CDA_KEY, nombre_cda);
		    	startActivity(intent1);
		    	
	            return true;

	        case R.id.mnuVendedores:
	        	Intent intent = new Intent(this, ListaVendedoresActivity.class);
	        	intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
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
	}*/

	
	public static class Confrontaciono_Adapter extends ArrayAdapter<ConfrontacionTO>{
		
		   private final ArrayList<ConfrontacionTO> detalle;
		   private final Activity context;
		
	
		   public Confrontaciono_Adapter(Activity context,ArrayList<ConfrontacionTO> detalle){
				super(context, R.layout.confrontacion_content, detalle);
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
			
			public ConfrontacionTO getItem(int position) {
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
					view = inflator.inflate(R.layout.confrontacion_content, null);
					
					final ViewHolder holder = new ViewHolder();
					holder.txtDescripcion = (TextView) view.findViewById(R.id.txtDescripcion);
					holder.txtMonto = (TextView) view.findViewById(R.id.txtMonto);
					holder.txtSupervisor = (TextView)view.findViewById(R.id.txtSupervisor);
					
					view.setTag(holder);
					holder.txtDescripcion.setTag(this.detalle.get(position));
					
				}else{
					view = convertView;
					((ViewHolder) view.getTag()).txtDescripcion.setTag(this.detalle.get(position));
				}
				
				ViewHolder holder = (ViewHolder) view.getTag();
				
				holder.txtDescripcion.setText(this.detalle.get(position).descripcion);
				holder.txtMonto.setText(this.detalle.get(position).monto);
				holder.txtSupervisor.setText(this.detalle.get(position).supervisor);
				
				
				return view;
				
			}
			
			
			private class ViewHolder {
				 public TextView txtDescripcion;
				 public TextView txtMonto;
				 public TextView txtSupervisor;
					 
			 }
			
	}

}
