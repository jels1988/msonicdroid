package pe.lindley.mmil.titanium;

import java.util.ArrayList;


import pe.lindley.mmil.titanium.to.ClienteTO;
import pe.lindley.mmil.titanium.to.DocumentoTO;
import pe.lindley.mmil.titanium.ws.service.ConsultarClienteProxy;
import roboguice.inject.InjectExtra;
import roboguice.inject.InjectView;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;
import com.google.inject.Inject;
import com.thira.examples.actionbar.widget.ActionBar;
import net.msonic.lib.ExpandableListActivityBase;

public class ConsultarClienteActivity extends ExpandableListActivityBase {


	public static final String NOMBRE_CDA_KEY="NOMBRE_CDA";
	public static final String CODIGO_DEPOSITO_KEY = "CODIGO_DEPOSITO";
	public static final String CODIGO_SUPERVISOR_KEY = "CODIGO_SUPERVISOR";
	public static final String CODIGO_CLIENTE_KEY = "CODIGO_CLIENTE";
	
	@InjectExtra(CODIGO_SUPERVISOR_KEY) String codigoSupervisor;
	@InjectExtra(CODIGO_DEPOSITO_KEY) String codigoCda;
	@InjectExtra(NOMBRE_CDA_KEY) String nombre_cda;
	@InjectExtra(CODIGO_CLIENTE_KEY) String codigoCliente;
	
	@Inject ConsultarClienteProxy consultarClienteProxy;
	
	@InjectView(R.id.actionBar)  		ActionBar 	mActionBar;
	@InjectView(R.id.txtCodigo)  		TextView 	txtCodigo;
	@InjectView(R.id.txtNombre)  		TextView 	txtNombre;
	@InjectView(R.id.txtDireccion)  		TextView 	txtDireccion;
	
	//@InjectView(R.id.expandableListView1) ExpandableListView expandableListView;
	
	  /** Called when the activity is first created. */
  @Override
  public void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      inicializarRecursos();
      
      setContentView(R.layout.consultarcliente_activity);
      
     mActionBar.setHomeLogo(R.drawable.header_logo);
      mActionBar.setTitle(R.string.consultas_activity_consulta_title);
      mActionBar.setSubTitle(nombre_cda);
     
      processAsync();
      
  }
	
  
  @Override
	protected void process() {
		// TODO Auto-generated method stub
	  consultarClienteProxy.codigoDeposito=codigoCda;
	  consultarClienteProxy.codigoCliente=codigoCliente;
	  consultarClienteProxy.execute();
		
	}

  
  @Override
	protected void processOk() {
		String message;
		
		boolean isExito = consultarClienteProxy.isExito();
		if (isExito) {
			int status = consultarClienteProxy.getResponse().getStatus();
			if (status == 0) {
				
				ClienteTO clienteTO = consultarClienteProxy.getResponse().cliente;
				if(clienteTO!=null){
					txtCodigo.setText(clienteTO.codigo);
					txtNombre.setText(clienteTO.nombre);
					txtDireccion.setText(clienteTO.direccion);
				}else{
					txtCodigo.setText("");
					txtNombre.setText("");
					txtDireccion.setText("");
				}
				ConsultarCliente_Adapter adapterSupervisor = new ConsultarCliente_Adapter(this, clienteTO.documentos);
				getExpandableListView().setAdapter(adapterSupervisor);
				super.processOk();
			}else{
				message = consultarClienteProxy.getResponse().getDescripcion();
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
		if(consultarClienteProxy.getResponse()!=null){
			String error = consultarClienteProxy.getResponse().getDescripcion();
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
	        	
	        	Intent intent1 = new Intent(this, ResumenMercaderistaActivity.class);
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
	        case R.id.mnuClienteCreditos:
	        	Intent intent4 = new Intent(this, ClienteCreditosActivity.class);
	        	intent4.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
	        	intent4.putExtra(ClienteCreditosActivity.CODIGO_SUPERVISOR_KEY, codigoSupervisor);
	        	intent4.putExtra(ClienteCreditosActivity.CODIGO_DEPOSITO_KEY, codigoCda);
	        	intent4.putExtra(ClienteCreditosActivity.NOMBRE_CDA_KEY, nombre_cda);
		    	startActivity(intent4);
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




	

	public static class ConsultarCliente_Adapter extends BaseExpandableListAdapter{
		
		   private final ArrayList<DocumentoTO> detalle;
		   private final Activity context;
		
		   public ConsultarCliente_Adapter(Activity context,ArrayList<DocumentoTO> detalle){
				this.detalle = detalle;
				this.context = context;
			}
		   
		   /*
		   public ConsultarCliente_Adapter(Activity context,ArrayList<DocumentoTO> detalle){
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
			
			public DocumentoTO getItem(int position) {
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
				
				//holder.txtDescripcion.setText(this.detalle.get(position).descripcion);
				//holder.txtValor.setText(this.detalle.get(position).valor)
				return view;
				
			}
			
			
			private class ViewHolder {
				 public ImageView imgIndicador;
				 public TextView txtDescripcion;
				 public TextView txtValor;
					 
			 }*/


			@Override
			public Object getChild(int groupPosition, int childPosition) {
				// TODO Auto-generated method stub
				return null;
			}



			@Override
			public long getChildId(int groupPosition, int childPosition) {
				// TODO Auto-generated method stub
				return 0;
			}



			@Override
			public View getChildView(int groupPosition, int childPosition,
					boolean isLastChild, View convertView, ViewGroup parent) {
				// TODO Auto-generated method stub
				if (convertView == null) {
				    LayoutInflater inflater =  (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				    convertView = inflater.inflate(R.layout.consultarcliente_row, null);
				   }
				    
				   TextView txtProducto = (TextView) convertView.findViewById(R.id.txtProducto);
				   TextView txtCantidad = (TextView) convertView.findViewById(R.id.txtCantidad);
				   
				   txtProducto.setText(detalle.get(groupPosition).productos.get(childPosition).producto);
				   txtCantidad.setText(detalle.get(groupPosition).productos.get(childPosition).cantidad);
				    
				   return convertView;
			}



			@Override
			public int getChildrenCount(int groupPosition) {
				// TODO Auto-generated method stub
				
				return detalle.get(groupPosition).productos.size();
				
			}



			@Override
			public Object getGroup(int groupPosition) {
				// TODO Auto-generated method stub
				return null;
			}



			@Override
			public int getGroupCount() {
				// TODO Auto-generated method stub
				return detalle.size();
			}



			@Override
			public long getGroupId(int groupPosition) {
				// TODO Auto-generated method stub
				return 0;
			}



			@Override
			public View getGroupView(int groupPosition, boolean isExpanded,
					View convertView, ViewGroup parent) {
				// TODO Auto-generated method stub
				if (convertView == null) {
				    LayoutInflater inflater =  (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				    convertView = inflater.inflate(R.layout.consultarcliente_group, null);
				   }
				    
				   TextView txtNumero = (TextView) convertView.findViewById(R.id.txtNumero);
				   TextView txtTipo = (TextView) convertView.findViewById(R.id.txtTipo);
				   TextView txtEstado = (TextView) convertView.findViewById(R.id.txtEstado);
				   TextView txtTransportista = (TextView) convertView.findViewById(R.id.txtTransportista);
				   
				   txtNumero.setText(detalle.get(groupPosition).numero);
				   txtTipo.setText(detalle.get(groupPosition).tipoDocumento);
				   txtEstado.setText(detalle.get(groupPosition).estado);
				   txtTransportista.setText(detalle.get(groupPosition).transportista);
				    
				   return convertView;
			}



			@Override
			public boolean hasStableIds() {
				// TODO Auto-generated method stub
				return false;
			}



			@Override
			public boolean isChildSelectable(int groupPosition,
					int childPosition) {
				// TODO Auto-generated method stub
				return false;
			}
			
	}

}
