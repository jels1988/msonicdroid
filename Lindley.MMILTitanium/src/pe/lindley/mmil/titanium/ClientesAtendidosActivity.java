package pe.lindley.mmil.titanium;

import java.util.ArrayList;

import pe.lindley.mmil.titanium.to.MercaderistaDetalleTO;
import pe.lindley.mmil.titanium.ws.service.ConsultarMercaderistaDetalleProxy;
import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.actionbarsherlock.view.MenuItem.OnMenuItemClickListener;
import com.google.inject.Inject;

import roboguice.inject.InjectExtra;
import net.msonic.lib.sherlock.ListActivityBase;

public class ClientesAtendidosActivity extends ListActivityBase {

	
	public static final String NOMBRE_CDA_KEY="NOMBRE_CDA";
	public static final String CODIGO_DEPOSITO_KEY = "CODIGO_DEPOSITO";
	public static final String CODIGO_SUPERVISOR_KEY = "CODIGO_SUPERVISOR";
	public static final String CODIGO_MERCADERISTA_KEY = "CODIGO_MERCADERISTA";
	public static final String NOMBRE_MERCADERISTA_KEY = "NOMBRE_MERCADERISTA";

	@InjectExtra(CODIGO_SUPERVISOR_KEY) String codigoSupervisor;
	@InjectExtra(CODIGO_DEPOSITO_KEY) String codigoCda;
	@InjectExtra(NOMBRE_CDA_KEY) String nombre_cda;
	@InjectExtra(CODIGO_MERCADERISTA_KEY) String codigoMercaderista;
	@InjectExtra(NOMBRE_MERCADERISTA_KEY) String nombreMercaderista;
	

	@Inject ConsultarMercaderistaDetalleProxy consultarMercaderistaDetalleProxy;
	
	
	  /** Called when the activity is first created. */
  @Override
  public void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      inicializarRecursos();
      
      setContentView(R.layout.resumenmercaderistadetalle_activity);
      

      
      setTitle(R.string.cliente_atendidos_activity_title);
      getSupportActionBar().setSubtitle(nombreMercaderista);
      
     
      processAsync();
      
  }
	
  
  @Override
	protected void process() {
		// TODO Auto-generated method stub
	  consultarMercaderistaDetalleProxy.codigoDeposito=codigoCda;
	  consultarMercaderistaDetalleProxy.codigoSupervisor=codigoSupervisor;
	  consultarMercaderistaDetalleProxy.codigoMercaderista=codigoMercaderista;
	  consultarMercaderistaDetalleProxy.execute();
		
	}

  
  @Override
	protected void processOk() {
		String message;
		
		boolean isExito = consultarMercaderistaDetalleProxy.isExito();
		if (isExito) {
			int status = consultarMercaderistaDetalleProxy.getResponse().getStatus();
			if (status == 0) {
				
				ArrayList<MercaderistaDetalleTO> detalle = consultarMercaderistaDetalleProxy.getResponse().detalle;
				Tablero_Adapter adapterMercaderista = new Tablero_Adapter(this, detalle);
				getListView().setAdapter(adapterMercaderista);
				
				super.processOk();
			}else{
				message = consultarMercaderistaDetalleProxy.getResponse().getDescripcion();
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
		if(consultarMercaderistaDetalleProxy.getResponse()!=null){
			String error = consultarMercaderistaDetalleProxy.getResponse().getDescripcion();
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

		return true;
	
	}
  
	
	public static class Tablero_Adapter extends ArrayAdapter<MercaderistaDetalleTO>{
		   private final ArrayList<MercaderistaDetalleTO> detalle;
		   private final Activity context;
		
			public Tablero_Adapter(Activity context,ArrayList<MercaderistaDetalleTO> detalle){
				super(context, R.layout.resumenmercaderistadetalle_content, detalle);
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
			
			
			public MercaderistaDetalleTO getItem(int position) {
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
					view = inflator.inflate(R.layout.resumenmercaderistadetalle_content, null);
				    
				    
					final ViewHolder holder = new ViewHolder();
					view.setClickable(true);
					
					holder.txtCodigo = (TextView)view.findViewById(R.id.txtCodigo);
					holder.txtNombre = (TextView)view.findViewById(R.id.txtNombre);
					holder.txtDireccion = (TextView) view.findViewById(R.id.txtDireccion);
					holder.txtHoraInicio = (TextView) view.findViewById(R.id.txtHoraInicio);
					holder.txtHoraFin = (TextView) view.findViewById(R.id.txtHoraFin);
					holder.txtTiempoEfectivo = (TextView) view.findViewById(R.id.txtTiempoEfectivo);

					view.setTag(holder);
					holder.txtCodigo.setTag(this.detalle.get(position));
				}else{
					view = convertView;
					((ViewHolder) view.getTag()).txtCodigo.setTag(this.detalle.get(position));
				}
				
				
				
				MercaderistaDetalleTO detalleTO = this.detalle.get(position);
				
				ViewHolder holder = (ViewHolder) view.getTag();

				holder.txtCodigo.setText(String.valueOf(detalleTO.codigo));
				holder.txtNombre.setText(detalleTO.descripcion);
				holder.txtDireccion.setText(detalleTO.direccion);
				holder.txtHoraInicio.setText(detalleTO.horaInicio);
				holder.txtHoraFin.setText(detalleTO.horaFin);
				holder.txtTiempoEfectivo.setText(detalleTO.tiempoEjecutado);
				
				return view;
				
			}
			
			private class ViewHolder {
				
				
				public TextView txtCodigo;
				public TextView txtNombre;
				public TextView txtDireccion;
				public TextView txtHoraInicio;
				public TextView txtHoraFin;
				public TextView txtTiempoEfectivo;
					
			}
		   
	 }
}

