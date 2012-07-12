package pe.lindley.mmil.titanium;

import java.util.ArrayList;

import pe.lindley.mmil.titanium.to.ResumenVentaDetalleTO;
import pe.lindley.mmil.titanium.ws.service.ResumenGerenteVentasDepositosProxy;
import roboguice.inject.InjectExtra;
import roboguice.inject.InjectView;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.actionbarsherlock.view.MenuItem.OnMenuItemClickListener;
import com.google.inject.Inject;

public class ResumenGerenteVentasDepositosActivity extends net.msonic.lib.sherlock.ActivityBase {
	
	public static final String NOMBRE_CDA_KEY="NOMBRE_CDA";
	public static final String CODIGO_DEPOSITO_KEY = "CODIGO_DEPOSITO";
	public static final String CODIGO_GERENTE_VENTA_KEY = "CODIGO_GERENTE_VENTA";
	
	@InjectExtra(CODIGO_GERENTE_VENTA_KEY) String codigoGerente;
	@InjectExtra(CODIGO_DEPOSITO_KEY) String codigoCda;
	@InjectExtra(NOMBRE_CDA_KEY) String nombre_cda;
	
	@InjectView(R.id.lstDetalle) ListView lstDetalle;

	
	
	@Inject ResumenGerenteVentasDepositosProxy resumenGerenteVentasDepositosProxy;
	
	int item_selected = -1; // select at 0
	String codigoSupervisor=null;
	String nombreSupervisor=null;
	
	//@InjectView(R.id.actionBar)  		ActionBar 	mActionBar;
	  /** Called when the activity is first created. */
  @Override
  public void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      inicializarRecursos();
      
      setContentView(R.layout.resumengerenteventasdepositos_activity);
      setTitle(R.string.resumen_gerenteventas_menu_jefeventas);
      getSupportActionBar().setSubtitle(nombre_cda);
      
      processAsync();
      
  }
  
  
  @Override
	protected void process() {
		// TODO Auto-generated method stub
	  resumenGerenteVentasDepositosProxy.codigoDeposito=codigoCda;
	  resumenGerenteVentasDepositosProxy.codigoSupervisor=codigoGerente;
	  resumenGerenteVentasDepositosProxy.execute();
		
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
 			builder.setTitle("Seleccionar Jefe de Ventas");
 			
 			
 			
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
			        			
			        			/*
				        		Intent intent = new Intent(getApplicationContext(), ResumenAdminFranquiciaActivity.class);
								intent.putExtra(ResumenAdminFranquiciaActivity.CODIGO_ADMIN_FRANQUINCIA_KEY, codigoSupervisor);
								intent.putExtra(ResumenAdminFranquiciaActivity.CODIGO_DEPOSITO_KEY, codigoCda);
								intent.putExtra(ResumenAdminFranquiciaActivity.NOMBRE_CDA_KEY, nombreSupervisor);
						    	startActivity(intent);*/
			        		
				        		Intent intent = new Intent(getApplicationContext(), ResumenJefeComercialesActivity.class);
								intent.putExtra(ResumenJefeComercialesActivity.CODIGO_JEFE_COMERCIAL_KEY, codigoSupervisor);
								intent.putExtra(ResumenJefeComercialesActivity.CODIGO_DEPOSITO_KEY, codigoCda);
								intent.putExtra(ResumenJefeComercialesActivity.NOMBRE_CDA_KEY, nombreSupervisor);
						    	startActivity(intent);
				        		
					        	dialog.dismiss();
			        	}
			        }
			    });
 			
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
		
		boolean isExito = resumenGerenteVentasDepositosProxy.isExito();
		if (isExito) {
			int status = resumenGerenteVentasDepositosProxy.getResponse().getStatus();
			if (status == 0) {
				
				ArrayList<ResumenVentaDetalleTO> resumenVentaDetalle = resumenGerenteVentasDepositosProxy.getResponse().detalle.detalle;
				ResumenDetalleVenta_Adapter adapterDetalle = new ResumenDetalleVenta_Adapter(this, resumenVentaDetalle);
				lstDetalle.setAdapter(adapterDetalle);
				
				super.processOk();
			}else{
				message = resumenGerenteVentasDepositosProxy.getResponse().getDescripcion();
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
		if(resumenGerenteVentasDepositosProxy.getResponse()!=null){
			String error = resumenGerenteVentasDepositosProxy.getResponse().getDescripcion();
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
		
		getSupportMenuInflater().inflate(R.menu.resumen_gerente_ventas_depositos_menu, menu);
		
		

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
	        case R.id.mnuDepositos:
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

}
