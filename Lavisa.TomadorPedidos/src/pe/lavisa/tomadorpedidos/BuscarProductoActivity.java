package pe.lavisa.tomadorpedidos;

import java.util.ArrayList;
import java.util.List;

import pe.lavisa.tomadorpedidos.to.ProductoTO;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class BuscarProductoActivity extends net.msonic.lib.sherlock.ListActivityBase  {
	
	public static final String PRODUCTO_ID_KEY="producto_id_key";
	public static final String PRODUCTO_DESCRIPCION_KEY="producto_descripcion_key";
	
	BuscarProducto_Adapter adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		inicializarRecursos();
		this.validarConexionInternet=false;
		setContentView(R.layout.buscarproducto_activity);
		
		final Context ctx = getApplicationContext();
		
		getListView().setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View arg1,int arg2, long arg3) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(ctx,ProductoEstrategiaActivity.class);
				startActivity(intent);
				return true;
			}
		});
		
		
	}

	public void btnbuscar_onclick(View v){
		processAsync();
	}
	
	@Override
	protected void process() {
		ArrayList<ProductoTO> lista = new ArrayList<ProductoTO>();
		
		ProductoTO p1 = new ProductoTO();
		p1.id=1;
		p1.descripcion="Shell Helix HX5 gal—n x6";
		p1.indicador=Util.IND_VERDE;
		lista.add(p1);
		
		
		ProductoTO p2 = new ProductoTO();
		p2.id=2;
		p2.descripcion="Shell Helix HX5 gal—n x10";
		p2.indicador=Util.IND_VERDE;
		lista.add(p2);
		
		
		ProductoTO p3 = new ProductoTO();
		p3.id=3;
		p3.descripcion="Shell Helix HX5 gal—n x12";
		p3.indicador=Util.IND_VERDE;
		lista.add(p3);
		
		ProductoTO p4 = new ProductoTO();
		p4.id=4;
		p4.descripcion="Shell Helix HX5 gal—n x24";
		p4.indicador=Util.IND_VERDE;
		lista.add(p4);
		
		ProductoTO p5 = new ProductoTO();
		p5.id=5;
		p5.descripcion="Shell Helix HX5 gal—n x24";
		p5.indicador=Util.IND_AMARRILLO;
		lista.add(p5);
		
		ProductoTO p6 = new ProductoTO();
		p6.id=6;
		p6.descripcion="Shell Helix HX5 gal—n x24";
		p6.indicador=Util.IND_AMARRILLO;
		lista.add(p6);
		

		ProductoTO p7 = new ProductoTO();
		p7.id=7;
		p7.indicador=Util.IND_ROJO;
		p7.descripcion="Shell Helix HX5 gal—n x24";
		lista.add(p7);
		
		ProductoTO p8 = new ProductoTO();
		p8.id=8;
		p8.descripcion="Shell Helix HX5 gal—n x98";
		p8.indicador=Util.IND_AMARRILLO;
		lista.add(p8);
		
		ProductoTO p9 = new ProductoTO();
		p9.id=9;
		p9.descripcion="Shell Helix HX5 gal—n x98";
		p9.indicador=Util.IND_ROJO;
		lista.add(p9);
		
		ProductoTO p10 = new ProductoTO();
		p10.id=10;
		p10.descripcion="Shell Helix HX5 gal—n x98";
		p10.indicador=Util.IND_VERDE;
		lista.add(p10);
		
		ProductoTO p11 = new ProductoTO();
		p11.id=11;
		p11.descripcion="Shell Helix HX5 gal—n x98";
		p11.indicador=Util.IND_VERDE;
		lista.add(p11);
		
		adapter = new BuscarProducto_Adapter(this, lista);
		
	}
	
	@Override
	protected void processOk() {
		setListAdapter(adapter);
		super.processOk();
	}
	public void btnAceptar_onclick(View v){
		setResult(RESULT_OK, null);
		finish();
	}
	
	public void btnCancelar_onclick(View v){
		setResult(RESULT_CANCELED, null);
		finish();
	}
	
	
	
	
	@Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        // TODO Auto-generated method stub
		
		if(adapter!=null){
			ProductoTO productoTO = adapter.getItem(position);
			Intent intent = getIntent();
			intent.putExtra(PRODUCTO_ID_KEY, productoTO.id);
			intent.putExtra(PRODUCTO_DESCRIPCION_KEY, productoTO.descripcion);
			setResult(RESULT_OK, intent);
			finish();
		}
		
        super.onListItemClick(l, v, position, id);
	}
	
	

	//OnItemLongClickListener
	
	
	



	public static class BuscarProducto_Adapter extends ArrayAdapter<ProductoTO>{
		
		 private final List<ProductoTO> detalle;
		   private final Activity context;
		   
		   public BuscarProducto_Adapter(Activity context,List<ProductoTO> detalle){
				super(context, R.layout.buscarproducto_content, detalle);
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
			
			public ProductoTO getItem(int position) {
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
					view = inflator.inflate(R.layout.buscarproducto_content, null);
					
					final ViewHolder holder = new ViewHolder();
					
					holder.txtDescripcion = (TextView) view.findViewById(R.id.txtDescripcion);
					holder.imgIndicador = (ImageView) view.findViewById(R.id.imgIndicador);
					
					
					view.setTag(holder);
					holder.txtDescripcion.setTag(this.detalle.get(position));
					
				}else{
					view = convertView;
					((ViewHolder) view.getTag()).txtDescripcion.setTag(this.detalle.get(position));
				}
				
				ViewHolder holder = (ViewHolder) view.getTag();
				
				holder.imgIndicador.setImageResource(R.drawable.icon_white);
				
				 switch(this.detalle.get(position).indicador)
			      {
			      	case Util.IND_VERDE:
			      		holder.imgIndicador.setImageResource(R.drawable.icon_verde);
			    	  break;
			      	case Util.IND_AMARRILLO:
			      		holder.imgIndicador.setImageResource(R.drawable.icon_amarrillo);
			      		break;
			      	case Util.IND_ROJO:
			      		holder.imgIndicador.setImageResource(R.drawable.icon_rojo);
			      		break;	 
			       }
				holder.txtDescripcion.setText(this.detalle.get(position).descripcion);

				return view;
				
			}
			
			
			private class ViewHolder {
				 public ImageView imgIndicador;
				 public TextView txtDescripcion;
			}
			
	}
}
