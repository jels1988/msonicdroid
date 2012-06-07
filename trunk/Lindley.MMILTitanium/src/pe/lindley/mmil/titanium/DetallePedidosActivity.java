package pe.lindley.mmil.titanium;


import java.util.ArrayList;
import pe.lindley.mmil.titanium.to.PedidoTO;
import pe.lindley.mmil.titanium.ws.service.PedidoServiceProxy;
import roboguice.inject.InjectExtra;
import roboguice.inject.InjectView;

import com.google.inject.Inject;
import com.thira.examples.actionbar.widget.ActionBar;

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
import net.msonic.lib.ListActivityBase;

public class DetallePedidosActivity extends ListActivityBase {

	

	public static final String CODIGO_CDA_KEY = "CODIGO_CDA";
	public static final String CODIGO_VENDEDOR_KEY = "CODIGO_VENDEDOR";
	public static final String NOMBRE_VENDEDOR_KEY = "NOMBRE_VENDEDOR";
	
	@Inject PedidoServiceProxy pedidoServiceProxy;
	
	@InjectView(R.id.actionBar)  	ActionBar 	mActionBar;
	@InjectExtra(CODIGO_CDA_KEY) String codigoCDA;
	@InjectExtra(CODIGO_VENDEDOR_KEY) String codigoVendedor;
	@InjectExtra(NOMBRE_VENDEDOR_KEY) String nombreVendedor;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		inicializarRecursos();
	      
	      setContentView(R.layout.detallepedidos_activity);
	      
	      mActionBar.setHomeLogo(R.drawable.header_logo);
	      mActionBar.setTitle(R.string.detalle_pedido_activity_title);
	      mActionBar.setSubTitle(nombreVendedor);
	      
	      processAsync();
	}

	  
	  @Override
		protected void process() {
			// TODO Auto-generated method stub
		  pedidoServiceProxy.codigoDeposito=codigoCDA;
		  pedidoServiceProxy.codigoVendedor=codigoVendedor;
		  pedidoServiceProxy.execute();
			
		}

	  
	  @Override
		protected void processOk() {
			String message;
			
			boolean isExito = pedidoServiceProxy.isExito();
			if (isExito) {
				int status = pedidoServiceProxy.getResponse().getStatus();
				if (status == 0) {
					
					ArrayList<PedidoTO> pedidos = pedidoServiceProxy.getResponse().pedidos;
					DetallePedido_Adapter adapterSupervisor = new DetallePedido_Adapter(this, pedidos);
					getListView().setAdapter(adapterSupervisor);
					
					super.processOk();
				}else{
					message = pedidoServiceProxy.getResponse().getDescripcion();
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
			if(pedidoServiceProxy.getResponse()!=null){
				String error = pedidoServiceProxy.getResponse().getDescripcion();
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
			inflater.inflate(R.menu.detallepedidos_menu, menu);
			return true;
		}
	
		
		

		@Override
		public boolean onOptionsItemSelected(MenuItem item) {

		    switch (item.getItemId()) {
		    case R.id.mnuPedidoMapa:
		    	Intent intent = new Intent(this, ListaPedidosActivity.class);
		    	intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				intent.putExtra(ListaPedidosActivity.CODIGO_VENDEDOR_KEY, codigoVendedor);
				intent.putExtra(ListaPedidosActivity.CODIGO_CDA_KEY, codigoCDA);
				intent.putExtra(ListaPedidosActivity.NOMBRE_VENDEDOR_KEY, nombreVendedor);
		    	startActivity(intent);		    	
		    	return true;
		    	
		       default:
		            return super.onOptionsItemSelected(item);
		    }
		}
		
		
		public static class DetallePedido_Adapter extends ArrayAdapter<PedidoTO>{
			
			   private final ArrayList<PedidoTO> detalle;
			   private final Activity context;
			
		
			   public DetallePedido_Adapter(Activity context,ArrayList<PedidoTO> detalle){
					super(context, R.layout.detallepedidos_content, detalle);
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
				
				public PedidoTO getItem(int position) {
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
						view = inflator.inflate(R.layout.detallepedidos_content, null);
						
						final ViewHolder holder = new ViewHolder();
						holder.txtPedido = (TextView) view.findViewById(R.id.txtNro);
						holder.txtCliente = (TextView) view.findViewById(R.id.txtCliente);
						holder.txtHora = (TextView)view.findViewById(R.id.txtHora);
						holder.txtEstado = (TextView)view.findViewById(R.id.txtEstado);
						
						view.setTag(holder);
						holder.txtPedido.setTag(this.detalle.get(position));
						
					}else{
						view = convertView;
						((ViewHolder) view.getTag()).txtPedido.setTag(this.detalle.get(position));
					}
					
					ViewHolder holder = (ViewHolder) view.getTag();
					
					holder.txtPedido.setText(this.detalle.get(position).pedido);
					holder.txtCliente.setText(this.detalle.get(position).cliente);
					holder.txtHora.setText(this.detalle.get(position).hora);
					holder.txtEstado.setText(this.detalle.get(position).estado);
					
					return view;
					
				}
				
				
				private class ViewHolder {
					 public TextView txtPedido;
					 public TextView txtCliente;
					 public TextView txtHora;
					 public TextView txtEstado;
						 
				 }
				
		}

	}
