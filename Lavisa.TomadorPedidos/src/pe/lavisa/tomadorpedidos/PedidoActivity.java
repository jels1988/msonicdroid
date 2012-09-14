package pe.lavisa.tomadorpedidos;

import java.util.ArrayList;
import java.util.List;

import pe.lavisa.tomadorpedidos.to.ClienteTO;
import pe.lavisa.tomadorpedidos.to.PedidoDetalleTO;
import roboguice.inject.InjectExtra;
import roboguice.inject.InjectView;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class PedidoActivity extends net.msonic.lib.sherlock.ListActivityBase  {
	
	public static final String CLIENTE_CODIGO_KEY="codigo_cliente";
	public static final String CLIENTE_DESCRIPCION_KEY="descripcion_cliente";
	
	@InjectExtra(value=CLIENTE_CODIGO_KEY)private String cliente_codigo;
	@InjectExtra(value=CLIENTE_DESCRIPCION_KEY)private String cliente_descripcion;
	@InjectView(R.id.txtProducto) private TextView txtProducto;
	@InjectView(R.id.txtCantidad) private TextView txtCantidad;
	@InjectView(R.id.cboPrecio) private Spinner cboPrecio;
	
	List<PedidoDetalleTO> lista;
	Pedido_Adapter adapter;
	
	 @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        inicializarRecursos();
	        
	        setContentView(R.layout.pedido_activity);
	        setTitle(R.string.pedido_activity_title);
	        
	        setSubTitle(cliente_descripcion);
	        
	        lista = new ArrayList<PedidoDetalleTO>();
	        adapter = new Pedido_Adapter(this, lista);
	        
	        setListAdapter(adapter);
	        
	    }
	 
	 
	 public void btnBuscar_onlick(View v){
		   Intent intent = new Intent(this,BuscarProductoActivity.class);
	        startActivityForResult(intent, 0);
	 }

	 public void btnAgregar_onlick(View v){
		 PedidoDetalleTO detalle = new PedidoDetalleTO();
		 detalle.producto = txtProducto.getText().toString();
		 detalle.cantidad = Integer.parseInt(txtCantidad.getText().toString());
		 detalle.precio = Double.parseDouble(cboPrecio.getSelectedItem().toString());
		 
		 lista.add(detalle);
		 
		 adapter.notifyDataSetChanged();
	 }

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		//Log.d(PedidoActivity.class.getSimpleName(), String.valueOf(resultCode));
		if(resultCode==Activity.RESULT_OK){
			String producto = data.getStringExtra(BuscarProductoActivity.PRODUCTO_DESCRIPCION_KEY);
			int producto_id = data.getIntExtra(BuscarProductoActivity.PRODUCTO_ID_KEY,0);
			txtProducto.setText(producto);
			txtCantidad.requestFocus();
		}
		super.onActivityResult(requestCode, resultCode, data);
	}
	 
	
	public static class Pedido_Adapter extends ArrayAdapter<PedidoDetalleTO>{
		
		
		  
		   private final List<PedidoDetalleTO> detalle;
		   private final Activity context;
		
		
		   
		   public Pedido_Adapter(Activity context,List<PedidoDetalleTO> detalle){
				super(context, R.layout.pedido_content, detalle);
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
			
			public PedidoDetalleTO getItem(int position) {
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
					view = inflator.inflate(R.layout.pedido_content, null);
					
					final ViewHolder holder = new ViewHolder();
					
					holder.txtProducto = (TextView) view.findViewById(R.id.txtProducto);
					holder.txtCantidad = (TextView)view.findViewById(R.id.txtCantidad);
					holder.txtPrecio = (TextView)view.findViewById(R.id.txtPrecio);
					holder.btnEliminar = (Button)view.findViewById(R.id.btnEliminar);
					holder.btnEliminar.setOnClickListener(new OnClickListener() {
						
						@Override
						public void onClick(View v) {
							// TODO Auto-generated method stub
							PedidoDetalleTO pedidoDetalleTO = (PedidoDetalleTO) holder.txtProducto.getTag();
							detalle.remove(pedidoDetalleTO);
							notifyDataSetInvalidated();
						}
					});
					
					view.setTag(holder);
					holder.txtProducto.setTag(this.detalle.get(position));
					
				}else{
					view = convertView;
					((ViewHolder) view.getTag()).txtProducto.setTag(this.detalle.get(position));
				}
				
				ViewHolder holder = (ViewHolder) view.getTag();
				
				holder.txtProducto.setText(this.detalle.get(position).producto);
				holder.txtCantidad.setText(String.valueOf(this.detalle.get(position).cantidad));
				holder.txtPrecio.setText(String.valueOf(this.detalle.get(position).precio));
				
			
				return view;
				
			}
			
			
			private class ViewHolder {
				 public TextView txtProducto;
				 public TextView txtCantidad;
				 public TextView txtPrecio;
				 public Button btnEliminar;
					 
			 }
			
	}
	 
}
