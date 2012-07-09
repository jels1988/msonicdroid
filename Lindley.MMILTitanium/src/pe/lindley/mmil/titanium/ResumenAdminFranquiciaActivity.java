package pe.lindley.mmil.titanium;

import java.util.ArrayList;

import pe.lindley.mmil.titanium.to.ResumenVentaDetalleTO;
import pe.lindley.mmil.titanium.to.ResumenVentaTO;
import pe.lindley.mmil.titanium.ws.service.ResumenVentasProxy;
import roboguice.inject.InjectExtra;
import roboguice.inject.InjectView;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TextView;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.ActionBar.Tab;
import com.actionbarsherlock.view.MenuItem.OnMenuItemClickListener;
import com.google.inject.Inject;


public class ResumenAdminFranquiciaActivity extends net.msonic.lib.sherlock.ActivityBase implements ActionBar.TabListener  {
	
	public static final String NOMBRE_CDA_KEY="NOMBRE_CDA";
	public static final String CODIGO_DEPOSITO_KEY = "CODIGO_DEPOSITO";
	public static final String CODIGO_ADMIN_FRANQUINCIA_KEY = "CODIGO_ADMIN_FRANQUICIA";
	
	@InjectExtra(CODIGO_ADMIN_FRANQUINCIA_KEY) String codigoAdminFranquicia;
	@InjectExtra(CODIGO_DEPOSITO_KEY) String codigoCda;
	@InjectExtra(NOMBRE_CDA_KEY) String nombre_cda;
	
	@InjectView(R.id.lstResumen) ListView lstResumen;
	@InjectView(R.id.lstDetalle) ListView lstDetalle;
	
	@InjectView(R.id.tblResumen) TableLayout tblResumen;
	@InjectView(R.id.hView) HorizontalScrollView hView;
	
	
	@Inject ResumenVentasProxy resumenVentasProxy;
	
	int item_selected = -1; // select at 0
	String codigoSupervisor=null;
	String nombreSupervisor=null;
	
	//@InjectView(R.id.actionBar)  		ActionBar 	mActionBar;
	  /** Called when the activity is first created. */
  @Override
  public void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      inicializarRecursos();
      
      setContentView(R.layout.resumenadminfranquicia_activity);
      setTitle(R.string.resumen_adminfranquicia_activity_title);
      getSupportActionBar().setSubtitle(nombre_cda);
      
      getSupportActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
      
      ActionBar.Tab tab = getSupportActionBar().newTab();
      tab.setText("Resumen");
      tab.setTabListener(this);
      getSupportActionBar().addTab(tab);
      
      
      ActionBar.Tab tab2 = getSupportActionBar().newTab();
      tab2.setText("Detalle");
      tab2.setTabListener(this);
      getSupportActionBar().addTab(tab2);

      
      processAsync();
      
  }
  @Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
	  if(tab.getPosition()==0){
		  tblResumen.setVisibility(View.VISIBLE);
		  lstResumen.setVisibility(View.VISIBLE);  
		  
		  hView.setVisibility(View.GONE);
	  }else{
		  tblResumen.setVisibility(View.GONE);
		  lstResumen.setVisibility(View.GONE); 
		  hView.setVisibility(View.VISIBLE);
	  }
		
	}


	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		
	}
  
  @Override
	protected void process() {
		// TODO Auto-generated method stub
	  resumenVentasProxy.codigoDeposito=codigoCda;
	  resumenVentasProxy.codigoSupervisor=codigoAdminFranquicia;
	  resumenVentasProxy.execute();
		
	}

  
  @Override
 	protected Dialog onCreateDialog(int id) {
 		 
	  
	  	final ResumenDetalleVenta_Adapter adapter = (ResumenDetalleVenta_Adapter)lstDetalle.getAdapter();
	  
	  
 		 ArrayList<String> vendedores = new ArrayList<String>();
 			
 			if(adapter!=null){
 				for (ResumenVentaDetalleTO resumenVentaDetalleTO : adapter.detalle) {
 					vendedores.add(resumenVentaDetalleTO.descripcion);
 				}
 			}
 			
 			final String[] values = vendedores.toArray(new String[vendedores.size()]);
 			ArrayAdapter<String> arrAdap = new ArrayAdapter<String>(this, android.R.layout.select_dialog_singlechoice, android.R.id.text1, values);
 			

 			// TODO Auto-generated method stub
 			AlertDialog.Builder builder = new AlertDialog.Builder(this);
 			builder.setTitle(R.string.resumen_adminfranquicia_seleccionar_supervisores);
 			
 			
 			
 			builder.setSingleChoiceItems(arrAdap,-1, new DialogInterface.OnClickListener() {
 			    public void onClick(DialogInterface dialog, int item) {
 			    	item_selected = item;
 			    	codigoSupervisor =adapter.detalle.get(item_selected).codigo;
 			    	nombreSupervisor=adapter.detalle.get(item_selected).descripcion;
 			    }
 			});
 			
 			builder.setPositiveButton(R.string.listasupervisores_activity_title_dialog_Aceptar, new DialogInterface.OnClickListener() {
			        public void onClick(DialogInterface dialog, int id) {
			        	if(item_selected>-1){
			        		Intent intent = new Intent(getApplicationContext(), ResumenVentaActivity.class);
							intent.putExtra(ResumenVentaActivity.CODIGO_SUPERVISOR_KEY, codigoSupervisor);
							intent.putExtra(ResumenVentaActivity.CODIGO_DEPOSITO_KEY, codigoCda);
							intent.putExtra(ResumenVentaActivity.NOMBRE_CDA_KEY, nombreSupervisor);
					    	startActivity(intent);
				        	dialog.dismiss();
			        	}
			        }
			    });
 			
 			/*
 			if(id==0){
 				builder.setPositiveButton(R.string.listasupervisores_activity_title_dialog_Aceptar, new DialogInterface.OnClickListener() {
 			        public void onClick(DialogInterface dialog, int id) {
 			        	if(item_selected>-1){
 				        	Intent i = new Intent(getApplicationContext(),ListaPedidosActivity.class);
 				        	i.putExtra(ListaPedidosActivity.CODIGO_CDA_KEY, codigoCda);
 				        	i.putExtra(ListaPedidosActivity.CODIGO_VENDEDOR_KEY, codigoVendedor);
 				        	i.putExtra(ListaPedidosActivity.NOMBRE_VENDEDOR_KEY, nombreVendedor);
 				        	startActivity(i);
 				        	dialog.dismiss();
 			        	}
 			        }
 			    });
 			}else{
 				builder.setPositiveButton(R.string.listasupervisores_activity_title_dialog_Aceptar, new DialogInterface.OnClickListener() {
 			        public void onClick(DialogInterface dialog, int id) {
 			        	if(item_selected>-1){
 			        		Intent intent = new Intent(getApplicationContext(), DetallePedidosActivity.class);
 							intent.putExtra(DetallePedidosActivity.CODIGO_VENDEDOR_KEY, codigoVendedor);
 							intent.putExtra(DetallePedidosActivity.CODIGO_CDA_KEY, codigoCda);
 							intent.putExtra(DetallePedidosActivity.NOMBRE_VENDEDOR_KEY, nombreVendedor);
 					    	startActivity(intent);
 				        	dialog.dismiss();
 			        	}
 			        }
 			    });
 				
 			}*/
 			
 			builder.setNegativeButton(R.string.listasupervisores_activity_title_dialog_Cancelar, new DialogInterface.OnClickListener() {
 				
 		        public void onClick(DialogInterface dialog, int id) {
 		        	 dialog.dismiss();
 		        }
 		    });
 			
 			AlertDialog alert = builder.create();
 			
 		    
 			alert.setCanceledOnTouchOutside(true);
 			
 			return builder.create();
 			
 	 }
  
  @Override
	protected void processOk() {
		String message;
		
		boolean isExito = resumenVentasProxy.isExito();
		if (isExito) {
			int status = resumenVentasProxy.getResponse().getStatus();
			if (status == 0) {
				
				ArrayList<ResumenVentaTO> resumenVenta = resumenVentasProxy.getResponse().detalle.resumen;
				ResumenVenta_Adapter adapterResumen = new ResumenVenta_Adapter(this, resumenVenta);
				lstResumen.setAdapter(adapterResumen);
				
				ArrayList<ResumenVentaDetalleTO> resumenVentaDetalle = resumenVentasProxy.getResponse().detalle.detalle;
				ResumenDetalleVenta_Adapter adapterDetalle = new ResumenDetalleVenta_Adapter(this, resumenVentaDetalle);
				lstDetalle.setAdapter(adapterDetalle);
				
				super.processOk();
			}else{
				message = resumenVentasProxy.getResponse().getDescripcion();
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
		if(resumenVentasProxy.getResponse()!=null){
			String error = resumenVentasProxy.getResponse().getDescripcion();
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
		
		getSupportMenuInflater().inflate(R.menu.resumen_admin_menu, menu);
		
		

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
		// TODO Auto-generated method stub
		  switch (item.getItemId()) {
	        case R.id.mnuSupervisores:
	        	
	        	/*
	        	Intent intent1 = new Intent(this, ResumenMercaderistaActivity.class);
	        	intent1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
	        	intent1.putExtra(ResumenMercaderistaActivity.CODIGO_SUPERVISOR_KEY, codigoAdminFranquicia);
	        	intent1.putExtra(ResumenMercaderistaActivity.CODIGO_DEPOSITO_KEY, codigoCda);
	        	intent1.putExtra(ResumenMercaderistaActivity.NOMBRE_CDA_KEY, nombre_cda);
		    	startActivity(intent1);*/
		    	showDialog(0);
	            return true;
	        
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}


	
	public static class ResumenDetalleVenta_Adapter extends ArrayAdapter<ResumenVentaDetalleTO>{
		 private final ArrayList<ResumenVentaDetalleTO> detalle;
		 private final Activity context;
		 
		 
		   public ResumenDetalleVenta_Adapter(Activity context,ArrayList<ResumenVentaDetalleTO> detalle){
				super(context,R.layout.resumenadminfranquicia_detalle_content,detalle);
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
			
			public ResumenVentaDetalleTO getItem(int position) {
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
					view = inflator.inflate(R.layout.resumenadminfranquicia_detalle_content, null);
				    
				    
					final ViewHolder_Detalle holder = new ViewHolder_Detalle();
					
					view.setClickable(true);
					holder.txtCodigo = (TextView) view.findViewById(R.id.txtCodigo);
					holder.txtDescripcion = (TextView) view.findViewById(R.id.txtDescripcion);
					holder.txtAvance = (TextView) view.findViewById(R.id.txtAvance);
					holder.txtCuota = (TextView) view.findViewById(R.id.txtCuota);
					holder.txtVendido = (TextView) view.findViewById(R.id.txtVendido);
					holder.txtCliProgramados = (TextView) view.findViewById(R.id.txtCliProgramados);
					holder.txtCliVisitados = (TextView) view.findViewById(R.id.txtCliVisitados);
					holder.txtConPedidos = (TextView) view.findViewById(R.id.txtConPedido);
					holder.txtSinPedidos = (TextView) view.findViewById(R.id.txtSinPedido);

					
					view.setTag(holder);
					holder.txtCodigo.setTag(this.detalle.get(position));
				}else{
					view = convertView;
					((ViewHolder_Detalle) view.getTag()).txtCodigo.setTag(this.detalle.get(position));
				}
				
				ViewHolder_Detalle holder = (ViewHolder_Detalle) view.getTag();
				
				ResumenVentaDetalleTO resumenVentaDetalleTO = this.detalle.get(position);
				
				holder.txtCodigo.setText(resumenVentaDetalleTO.codigo);
				holder.txtDescripcion.setText(resumenVentaDetalleTO.descripcion);
				holder.txtAvance.setText(resumenVentaDetalleTO.avance);
				holder.txtCuota.setText(resumenVentaDetalleTO.cuota);
				holder.txtVendido.setText(resumenVentaDetalleTO.vendido);
				holder.txtCliProgramados.setText(resumenVentaDetalleTO.clientesProgramados);
				holder.txtCliVisitados.setText(resumenVentaDetalleTO.clientesVisitados);
				holder.txtConPedidos.setText(resumenVentaDetalleTO.clientesPedido);
				holder.txtSinPedidos.setText(resumenVentaDetalleTO.clientesSinPedido);
				
				return view;
				
			}
			
			private class ViewHolder_Detalle {
				public TextView txtCodigo;
				public TextView txtDescripcion;
				public TextView txtAvance;
				public TextView txtCuota;
				public TextView txtVendido;
				public TextView txtCliProgramados;
				public TextView txtCliVisitados;
				public TextView txtConPedidos;
				public TextView txtSinPedidos;
				
				
			}
			
		   
	}
	public static class ResumenVenta_Adapter extends ArrayAdapter<ResumenVentaTO>{
		
		   private final ArrayList<ResumenVentaTO> detalle;
		   private final Activity context;
		
					
		   private static final int IND_VERDE = 3;
		   private static final int IND_AMARRILLO = 2;
		   private static final int IND_ROJO = 1;
		   
		   
		   public ResumenVenta_Adapter(Activity context,ArrayList<ResumenVentaTO> detalle){
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
			
			public ResumenVentaTO getItem(int position) {
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
				
				holder.txtDescripcion.setText(this.detalle.get(position).descripcion);
				holder.txtValor.setText(this.detalle.get(position).valor);
				
				
				holder.imgIndicador.setImageResource(R.drawable.icon_white);
				
				 switch(Integer.parseInt(this.detalle.get(position).indicador))
			      {
			      	case IND_VERDE:
			      		holder.imgIndicador.setImageResource(R.drawable.icon_verde);
			    	  break;
			      	case IND_AMARRILLO:
			      		holder.imgIndicador.setImageResource(R.drawable.icon_amarrillo);
			      		break;
			      	case IND_ROJO:
			      		holder.imgIndicador.setImageResource(R.drawable.icon_rojo);
			      		break;	 
			       }
				return view;
				
			}
			
			
			private class ViewHolder {
				 public ImageView imgIndicador;
				 public TextView txtDescripcion;
				 public TextView txtValor;
					 
			 }
			
			
			
	}



	

}
