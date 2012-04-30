package pe.lindley.mmil.titanium;

import java.util.ArrayList;

import pe.lindley.mmil.titanium.to.VendedorTO;
import pe.lindley.mmil.titanium.ws.service.ListarVendedorProxy;
import roboguice.inject.InjectExtra;
import roboguice.inject.InjectView;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.inject.Inject;
import com.thira.examples.actionbar.widget.ActionBar;

import net.msonic.lib.ListActivityBase;

public class ListaTeleventaActivity extends ListActivityBase {

	public static final String NOMBRE_CDA_KEY="NOMBRE_CDA";
	public static final String CODIGO_CDA_KEY="CODIGO_CDA";
	
	@InjectExtra(NOMBRE_CDA_KEY) String nombre_cda;
	@InjectExtra(CODIGO_CDA_KEY) String codigo_cda;
	
	@InjectView(R.id.actionBar)  	ActionBar 	mActionBar;
	@Inject ListarVendedorProxy 	listarVendedorProxy;
	
	
	ListView 	lstSupervisor;
	Tablero_Adapter adapter = null;
	String codigoVendedor=null;
	String nombreVendedor=null;
	
	
	

	 @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        inicializarRecursos();
	        
	        setContentView(R.layout.listateleventa_activity);
	        
	        mActionBar.setHomeLogo(R.drawable.header_logo);
	        mActionBar.setTitle(R.string.lista_televendedores_activity_title);
	        mActionBar.setSubTitle(nombre_cda);
	        
	        lstSupervisor = getListView();
	        processAsync();
	    }
	 
	 @Override
		protected void process() {
			// TODO Auto-generated method stub
		 listarVendedorProxy.codigoDeposito=codigo_cda;
		 listarVendedorProxy.codigoSupervisor="1";
		 listarVendedorProxy.tipo="1";
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
				super(context, R.layout.listateleventa_content, detalle);
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
					view = inflator.inflate(R.layout.listateleventa_content, null);
				    
				    
					final ViewHolder holder = new ViewHolder();
					
					holder.txtCodigo = (TextView) view.findViewById(R.id.txtCodigo);
					holder.txtNombre = (TextView) view.findViewById(R.id.txtNombre);
					holder.txtPrimerRegistro= (TextView) view.findViewById(R.id.txtPrimerRegistro);
					holder.txtUltimoRegistro= (TextView) view.findViewById(R.id.txtUltimoRegistro);
					holder.txtPlanVisita = (TextView) view.findViewById(R.id.txtPlanVisita);
					holder.imgPlanVisita = (ImageView) view.findViewById(R.id.imgPlanVisita);
					holder.txtClientes = (TextView) view.findViewById(R.id.txtClientes);
					holder.txtImporte = (TextView) view.findViewById(R.id.txtImporte);
					holder.txtAtendidos = (TextView) view.findViewById(R.id.txtAtendidos);
					holder.txtCajasFisicas = (TextView) view.findViewById(R.id.txtCajasFisicas);
					holder.txtCajasUnitarias = (TextView) view.findViewById(R.id.txtCajasUnitarias);
					holder.txtTiempoEfectivo = (TextView) view.findViewById(R.id.txtTiempoEfectivo);
					
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
				holder.txtPrimerRegistro.setText(vendedorTO.primerRegistro);
				holder.txtUltimoRegistro.setText(vendedorTO.ultimoRegistro);

				holder.txtPlanVisita.setText(vendedorTO.planVisita);
				if(vendedorTO.planVisitaColor!=null){
					 switch(Integer.parseInt(vendedorTO.planVisitaColor))
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
				
				
				holder.txtClientes.setText(vendedorTO.cantidadClientes);
				holder.txtAtendidos.setText(vendedorTO.visitas);
				holder.txtPlanVisita.setText(vendedorTO.planVisita);
				holder.txtImporte.setText(vendedorTO.importe);
				holder.txtCajasFisicas.setText(vendedorTO.cajaFisica);
				holder.txtCajasUnitarias.setText(vendedorTO.cajaUni);
				holder.txtTiempoEfectivo.setText(vendedorTO.tiempoEfec);
				
				
				return view;
				
			}
			
			private class ViewHolder {
				public TextView txtCodigo;
				public TextView txtNombre;
				
				public TextView txtPrimerRegistro;
				public TextView txtUltimoRegistro;

				
				public ImageView imgPlanVisita;
				public TextView txtPlanVisita;
				
				public TextView txtClientes;
				public TextView txtAtendidos;
				
				public TextView txtImporte;
				public TextView txtCajasFisicas;
				public TextView txtCajasUnitarias;
				public TextView txtTiempoEfectivo;
			}
		   
	 }
}


