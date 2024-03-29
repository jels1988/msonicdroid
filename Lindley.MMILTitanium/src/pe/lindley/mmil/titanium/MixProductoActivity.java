package pe.lindley.mmil.titanium;

import java.util.ArrayList;


import pe.lindley.mmil.titanium.to.MixProductoTO;
import pe.lindley.mmil.titanium.ws.service.MixProductoProxy;
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
import android.widget.TextView;

import com.actionbarsherlock.view.MenuItem.OnMenuItemClickListener;
import com.google.inject.Inject;
//import com.thira.examples.actionbar.widget.ActionBar;

import net.msonic.lib.ListActivityBase;

public class MixProductoActivity extends net.msonic.lib.sherlock.ListActivityBase {
	
	public static final String NOMBRE_CDA_KEY="NOMBRE_CDA";
	public static final String CODIGO_DEPOSITO_KEY = "CODIGO_DEPOSITO";
	public static final String CODIGO_SUPERVISOR_KEY = "CODIGO_SUPERVISOR";
	
	@InjectExtra(CODIGO_SUPERVISOR_KEY) String codigoSupervisor;
	@InjectExtra(CODIGO_DEPOSITO_KEY) String codigoCda;
	@InjectExtra(NOMBRE_CDA_KEY) String nombre_cda;
	
	
	@Inject MixProductoProxy mixProductoProxy;
	
	//@InjectView(R.id.actionBar)  		ActionBar 	mActionBar;
	  /** Called when the activity is first created. */
  @Override
  public void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      inicializarRecursos();
      
      setContentView(R.layout.mixproducto_activity);
      
      /*
      mActionBar.setHomeLogo(R.drawable.header_logo);
      mActionBar.setTitle(R.string.mix_producto_activity_title);
      mActionBar.setSubTitle(nombre_cda);*/
      
      setTitle(R.string.mix_producto_activity_title);
      getSupportActionBar().setSubtitle(nombre_cda);
      
      processAsync();
      
  }
	
  
  @Override
	protected void process() {
		// TODO Auto-generated method stub
	  mixProductoProxy.codigoDeposito=codigoCda;
	  mixProductoProxy.codigoSupervisor=codigoSupervisor;
	  mixProductoProxy.execute();
		
	}

  
  @Override
	protected void processOk() {
		String message;
		
		boolean isExito = mixProductoProxy.isExito();
		if (isExito) {
			int status = mixProductoProxy.getResponse().getStatus();
			if (status == 0) {
				
				ArrayList<MixProductoTO> mixProductos = mixProductoProxy.getResponse().mixProductos;
				MixProducto_Adapter adapterSupervisor = new MixProducto_Adapter(this, mixProductos);
				getListView().setAdapter(adapterSupervisor);
				
				super.processOk();
			}else{
				message = mixProductoProxy.getResponse().getDescripcion();
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
		if(mixProductoProxy.getResponse()!=null){
			String error = mixProductoProxy.getResponse().getDescripcion();
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
	}*/
	
	
	@Override
	public boolean onCreateOptionsMenu(com.actionbarsherlock.view.Menu menu) {
		// TODO Auto-generated method stub
		
		getSupportMenuInflater().inflate(R.menu.mix_producto_menu, menu);
		
		

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
	public boolean onOptionsItemSelected(
			com.actionbarsherlock.view.MenuItem item) {
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
	        case R.id.mnuConfrontacion:
	        	Intent intent3 = new Intent(this, ConfrontacionActivity.class);
	        	intent3.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
	        	intent3.putExtra(ConfrontacionActivity.CODIGO_SUPERVISOR_KEY, codigoSupervisor);
	        	intent3.putExtra(ConfrontacionActivity.CODIGO_DEPOSITO_KEY, codigoCda);
	        	intent3.putExtra(ConfrontacionActivity.NOMBRE_CDA_KEY, nombre_cda);
		    	startActivity(intent3);
	        	return true;
	        case R.id.mnuClienteCreditos:
	        	Intent intent4 = new Intent(this, ClienteCreditosActivity.class);
	        	intent4.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
	        	intent4.putExtra(ClienteCreditosActivity.CODIGO_SUPERVISOR_KEY, codigoSupervisor);
	        	intent4.putExtra(ClienteCreditosActivity.CODIGO_DEPOSITO_KEY, codigoCda);
	        	intent4.putExtra(ClienteCreditosActivity.NOMBRE_CDA_KEY, nombre_cda);
		    	startActivity(intent4);
	        	return true;
	        case R.id.mnuConsultas:
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}

	
	public static class MixProducto_Adapter extends ArrayAdapter<MixProductoTO>{
		
		   private final ArrayList<MixProductoTO> detalle;
		   private final Activity context;
		
	
		   public MixProducto_Adapter(Activity context,ArrayList<MixProductoTO> detalle){
				super(context, R.layout.mixproducto_content, detalle);
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
			
			public MixProductoTO getItem(int position) {
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
					view = inflator.inflate(R.layout.mixproducto_content, null);
					
					final ViewHolder holder = new ViewHolder();
					holder.txtProducto = (TextView) view.findViewById(R.id.txtProducto);
					holder.txtUnitarios = (TextView) view.findViewById(R.id.txtCajasUnitarias);
					holder.txtFisicas = (TextView)view.findViewById(R.id.txtCajasFisicas);
					
					view.setTag(holder);
					holder.txtProducto.setTag(this.detalle.get(position));
					
				}else{
					view = convertView;
					((ViewHolder) view.getTag()).txtProducto.setTag(this.detalle.get(position));
				}
				
				ViewHolder holder = (ViewHolder) view.getTag();
				
				holder.txtProducto.setText(this.detalle.get(position).producto);
				holder.txtUnitarios.setText(this.detalle.get(position).cajasUnitarias);
				holder.txtFisicas.setText(this.detalle.get(position).cajasFisicas);
				
				
				return view;
				
			}
			
			
			private class ViewHolder {
				 public TextView txtProducto;
				 public TextView txtUnitarios;
				 public TextView txtFisicas;
					 
			 }
			
	}

}
