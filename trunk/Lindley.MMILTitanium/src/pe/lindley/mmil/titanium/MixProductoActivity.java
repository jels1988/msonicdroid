package pe.lindley.mmil.titanium;

import java.util.ArrayList;


import pe.lindley.mmil.titanium.to.MixProductoTO;
import pe.lindley.mmil.titanium.ws.service.MixProductoProxy;
import roboguice.inject.InjectExtra;
import roboguice.inject.InjectView;
import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.google.inject.Inject;
import com.thira.examples.actionbar.widget.ActionBar;

import net.msonic.lib.ListActivityBase;

public class MixProductoActivity extends ListActivityBase {
	
	public static final String NOMBRE_CDA_KEY="NOMBRE_CDA";
	public static final String CODIGO_DEPOSITO_KEY = "CODIGO_DEPOSITO";
	public static final String CODIGO_SUPERVISOR_KEY = "CODIGO_SUPERVISOR";
	
	@InjectExtra(CODIGO_SUPERVISOR_KEY) String codigoSupervisor;
	@InjectExtra(CODIGO_DEPOSITO_KEY) String codigoCda;
	@InjectExtra(NOMBRE_CDA_KEY) String nombre_cda;
	
	
	@Inject MixProductoProxy mixProductoProxy;
	
	@InjectView(R.id.actionBar)  		ActionBar 	mActionBar;
	  /** Called when the activity is first created. */
  @Override
  public void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      inicializarRecursos();
      
      setContentView(R.layout.mixproducto_activity);
      
      mActionBar.setHomeLogo(R.drawable.header_logo);
      mActionBar.setTitle(R.string.mix_producto_activity_title);
      mActionBar.setSubTitle(nombre_cda);
      
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
