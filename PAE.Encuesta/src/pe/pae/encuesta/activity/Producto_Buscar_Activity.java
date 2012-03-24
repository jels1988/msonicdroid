package pe.pae.encuesta.activity;

import java.util.ArrayList;

import pe.pae.encuesta.R;
import pe.pae.encuesta.negocio.EncuestaBLL;
import pe.pae.encuesta.negocio.RespuestaBLL;
import pe.pae.encuesta.to.ProductoTO;
import pe.pae.encuesta.ws.service.GuardarRespuestaProxy;
import roboguice.inject.InjectView;

import com.google.inject.Inject;
import com.thira.examples.actionbar.widget.ActionBar;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import net.msonic.lib.ListActivityBase;
import net.msonic.lib.MessageBox;

public class Producto_Buscar_Activity extends ListActivityBase {

	@InjectView(R.id.actionBar)  	ActionBar 	mActionBar;
	@InjectView(R.id.txtProducto) 	EditText 	txtProducto;
	@Inject 	EncuestaBLL 	encuestaBLL;
	@Inject 	RespuestaBLL 	respuestaBLL;
	@Inject		GuardarRespuestaProxy	guardarRespuestaProxy;
	public  final static int ENVIAR_RESPUESTAS=0;
	
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
	protected void process(int accion) {
		// TODO Auto-generated method stub
		if(accion==ENVIAR_RESPUESTAS){
			guardarRespuestaProxy.respuestas = respuestaBLL.listarRespuesta();
			guardarRespuestaProxy.execute();
		}
		
	}
	

	



	@Override
	protected void processOk(int accion) {
		// TODO Auto-generated method stub
		
		String message;
		
		if(accion==ENVIAR_RESPUESTAS){
			
				boolean isExito = guardarRespuestaProxy.isExito();
				
				if (isExito) {
					int status = guardarRespuestaProxy.getResponse().getStatus();
					
					message = guardarRespuestaProxy.getResponse().getDescripcion();
					
						if (status == 0) {
							
							
							SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(this);
							Editor editor = settings.edit();
							editor.putInt("ENCUESTA_INICIO", 0);
							editor.putInt("CLIENTE_ID", 0);
							editor.putInt("TIENDA_ID", 0);
							editor.putString("TIENDA", "");
							editor.commit();
							
							
					    	Intent intentService = new Intent("pae.service.uploadFileService");
					        startService(intentService);
							
							Intent intent = new Intent("pae.activity.seleccionarTienda");
							startActivity(intent);
						}
				
				super.processOk();
				showToast(message);
			}else{
				processError();
			}
		}
		
		super.processOk(accion);
	}


	@Override
	protected void processError(int accion) {
		// TODO Auto-generated method stub
		super.processError(accion);
	}


	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		processAsync();
		
		
		super.onStart();
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
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		if(item.getItemId() == R.id.mnuEnviar){
			
			 MessageBox.showConfirmDialog(this, "Confirmar", "ÀDeseas enviar las respuestas?", "Si", 
					   new android.content.DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int which) {
								// TODO Auto-generated method stub
								
								processAsync(ENVIAR_RESPUESTAS);
								
							}
			   		}, "No", null);
			 
			
		}
		
		return super.onOptionsItemSelected(item);
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
					
					holder.imgIndicador = (ImageView)view.findViewById(R.id.imgIndicador);
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
				if(this.productos.get(position).tieneRespuesta==0){
					holder.imgIndicador.setImageResource(R.drawable.icon_rojo);
				}else{
					holder.imgIndicador.setImageResource(R.drawable.icon_verde);
				}
				
				return view;
			}

			class ViewHolder {
				 public ImageView imgIndicador;
				 public TextView txtProducto;
				 public Button btnCuestionario;
					 
			 }

			
			
	   }
}
