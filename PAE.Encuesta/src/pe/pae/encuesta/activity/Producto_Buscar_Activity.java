package pe.pae.encuesta.activity;

import java.util.ArrayList;

import pe.pae.encuesta.R;
import pe.pae.encuesta.negocio.EncuestaBLL;
import pe.pae.encuesta.to.ProductoTO;
import roboguice.inject.InjectView;

import com.google.inject.Inject;
import com.thira.examples.actionbar.widget.ActionBar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import net.msonic.lib.ListActivityBase;

public class Producto_Buscar_Activity extends ListActivityBase {

	@InjectView(R.id.actionBar)  	ActionBar 	mActionBar;
	@InjectView(R.id.txtProducto) 	EditText 	txtProducto;
	@Inject 	EncuestaBLL encuestaBLL;
	private Producto_Buscar_Adapter adap;
	
	   @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        inicializarRecursos();
	        
	        setContentView(R.layout.producto_buscar_activity);
	        mActionBar.setTitle(R.string.producto_buscar_activity_title);
	        
	        MiApp miApp = (MiApp)getApplication();
	        mActionBar.setSubTitle(miApp.tienda);
	        
	        
	        txtProducto.setOnKeyListener(new OnKeyListener() {			
				public boolean onKey(View v, int keyCode, KeyEvent event) {
					// TODO Auto-generated method stub
					if(keyCode == KeyEvent.KEYCODE_ENTER){  
						btnBuscar_onclick(null);
					}
					return false;
				}
			});
	    }

	   
	   @Override
	protected void process() {
		// TODO Auto-generated method stub
		   
		ArrayList<ProductoTO> productos = encuestaBLL.buscarProducto(txtProducto.getText().toString());
		
		adap = new Producto_Buscar_Adapter(this, productos);
		
		
		super.process();
	}







	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		super.onCreateOptionsMenu(menu);
		MenuInflater inflater = new MenuInflater(this);
		inflater.inflate(R.menu.producto_buscar_menu,menu);
		return true;
	}


	@Override
	protected void processOk() {
		// TODO Auto-generated method stub
		setListAdapter(adap);
		super.processOk();
	}







	public void btnBuscar_onclick(View v){
			processAsync();
	   }
	   
	   
	   public static class Producto_Buscar_Adapter extends ArrayAdapter<ProductoTO>{
		   
		   
		   private final ArrayList<ProductoTO> productos;
			private final Activity context;
			
			public Producto_Buscar_Adapter(Activity context,ArrayList<ProductoTO> productos){
				super(context, R.layout.producto_buscar_content, productos);
				this.productos = productos;
				this.context = context;
			}
			
			
			
			public int getCount() {
				// TODO Auto-generated method stub
				if (productos == null) {
					return 0;
				} else {
					return productos.size();
				}
			}

			public ProductoTO getItem(int position) {
				// TODO Auto-generated method stub
				return this.productos.get(position);
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
					view = inflator.inflate(R.layout.producto_buscar_content, null);
					
					final ViewHolder holder = new ViewHolder();
					
					holder.txtProducto = (TextView) view.findViewById(R.id.txtProducto);
					holder.btnCuestionario = (Button)view.findViewById(R.id.btnCuestionario);
					holder.btnCuestionario.setOnClickListener(new OnClickListener() {
						
						public void onClick(View v) {
							// TODO Auto-generated method stub
							ProductoTO productoTO = (ProductoTO) holder.txtProducto.getTag();
							Intent intent = new Intent("pae.activity.custionarioProducto");
							intent.putExtra("ENCUESTAID", productoTO.encuestaId);
							intent.putExtra("PRODUCTOID", productoTO.productoId);
							intent.putExtra("PRODUCTO", productoTO.descripcion);
							
							context.startActivity(intent);
							//MiApp miApp = (MiApp)context.getApplication();
							//miApp.preguntas = 
						}
					});
					
					
					view.setTag(holder);
					holder.txtProducto.setTag(this.productos.get(position));
				}else{
					view = convertView;
					((ViewHolder) view.getTag()).txtProducto.setTag(this.productos.get(position));
				}
				
				ViewHolder holder = (ViewHolder) view.getTag();
				holder.txtProducto.setText(this.productos.get(position).descripcion);
				//holder.chkSeleccion.setChecked(this.opciones.get(position).seleccionado);
				
				return view;
			}

			class ViewHolder {
				 public TextView txtProducto;
				 public Button btnCuestionario;
					 
			 }

			
			
	   }
}
