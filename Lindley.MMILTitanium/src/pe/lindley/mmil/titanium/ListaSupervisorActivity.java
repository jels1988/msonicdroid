package pe.lindley.mmil.titanium;

import java.util.ArrayList;

import pe.lindley.mmil.titanium.to.SupervisorTO;
import pe.lindley.mmil.titanium.ws.service.ListarSupervisorProxy;
import roboguice.inject.InjectExtra;
import roboguice.inject.InjectView;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.inject.Inject;
import com.thira.examples.actionbar.widget.ActionBar;

import net.msonic.lib.ListActivityBase;

public class ListaSupervisorActivity extends ListActivityBase {

	public static final String NOMBRE_CDA_KEY="NOMBRE_CDA";
	public static final String CODIGO_CDA_KEY="CODIGO_CDA";
	
	@InjectExtra(NOMBRE_CDA_KEY) String nombre_cda;
	@InjectExtra(CODIGO_CDA_KEY) String codigo_cda;
	
	@Inject ListarSupervisorProxy listarSupervisorProxy;
	ListView 	lstSupervisor;
	@InjectView(R.id.actionBar)  	ActionBar 	mActionBar;
	Tablero_Adapter adapter = null;
	int item_selected = -1; // select at 0
	String codigoSupervisor=null;
	String nombreSupervisor=null;
	
	 @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        inicializarRecursos();
	        
	        setContentView(R.layout.listasupervisor_activity);
	        
	        mActionBar.setHomeLogo(R.drawable.header_logo);
	        mActionBar.setTitle(R.string.listasupervisores_activity_title);
	        mActionBar.setSubTitle(nombre_cda);
	        
	        lstSupervisor = getListView();
	        processAsync();
	    }
	 
	 
	 
	 @Override
	protected void process() {
		// TODO Auto-generated method stub
		 item_selected=-1;
		 listarSupervisorProxy.codigoDeposito=codigo_cda;
		 listarSupervisorProxy.tipo=0;
		 listarSupervisorProxy.execute();
		 
		super.process();
	}


	 
		@Override
		protected void processOk() {
			String message;
			boolean isExito = listarSupervisorProxy.isExito();
			if (isExito) {
				int status = listarSupervisorProxy.getResponse().getStatus();
				if (status == 0) {
					
					adapter = new Tablero_Adapter(this, listarSupervisorProxy.getResponse().listaSupervisor,lstSupervisor);
					lstSupervisor.setAdapter(adapter);
					super.processOk();
				}else{
					message = listarSupervisorProxy.getResponse().getDescripcion();
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
			if(listarSupervisorProxy.getResponse()!=null){
				String error = listarSupervisorProxy.getResponse().getDescripcion();
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
		MenuInflater menuInflater = new MenuInflater(this);
		menuInflater.inflate(R.menu.listasupervisores_menu, menu);
			return super.onCreateOptionsMenu(menu);
		}

	
	
	

	@Override
	protected Dialog onCreateDialog(int id) {
		
		ArrayList<String> supervisores = new ArrayList<String>();
		
		if(adapter!=null){
			for (SupervisorTO supervisorTO : adapter.detalle) {
				supervisores.add(supervisorTO.nombre);
			}
		}
		final String[] values = supervisores.toArray(new String[supervisores.size()]);
		ArrayAdapter<String> arrAdap = new ArrayAdapter<String>(this, android.R.layout.select_dialog_singlechoice, android.R.id.text1, values);
		
		
		
		// TODO Auto-generated method stub
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle(R.string.listasupervisores_activity_title_dialog);
		
		builder.setSingleChoiceItems(arrAdap,-1, new DialogInterface.OnClickListener() {
		    public void onClick(DialogInterface dialog, int item) {
		    	item_selected = item;
		    	codigoSupervisor=String.valueOf(adapter.detalle.get(item_selected).codigo);
		    	nombreSupervisor=adapter.detalle.get(item_selected).nombre;
		    }
		});
		
		builder.setPositiveButton(R.string.listasupervisores_activity_title_dialog_Aceptar, new DialogInterface.OnClickListener() {
	        public void onClick(DialogInterface dialog, int id) {
	        	if(item_selected>-1){
		        	Intent i = new Intent(getApplicationContext(),ListaVendedoresActivity.class);
		        	i.putExtra(ListaVendedoresActivity.CODIGO_SUPERVISOR_KEY, codigoSupervisor);
		        	i.putExtra(ListaVendedoresActivity.CODIGO_CDA_KEY, listarSupervisorProxy.codigoDeposito);
		        	i.putExtra(ListaVendedoresActivity.NOMBRE_CDA_KEY, nombreSupervisor);
		        	startActivity(i);
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
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		// TODO Auto-generated method stub
		if(item.getItemId()==R.id.mnuVendedores){		    
			showDialog(0);
		}
		return super.onMenuItemSelected(featureId, item);
	}


	public static class Tablero_Adapter extends ArrayAdapter<SupervisorTO>{
		   private final ArrayList<SupervisorTO> detalle;
		   private final Activity context;
		
					
		   private static final int IND_VERDE = 0;
		   private static final int IND_AMARRILLO = 1;
		   private static final int IND_ROJO = 2;
		   
			public Tablero_Adapter(Activity context,ArrayList<SupervisorTO> detalle,ListView l){
				super(context, R.layout.listasupervisor_content, detalle);
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
			
			
			public SupervisorTO getItem(int position) {
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
					view = inflator.inflate(R.layout.listasupervisor_content, null);
					view.setClickable(true);
				    view.setFocusable(true);
				    
				    view.setOnClickListener(new OnClickListener() {
						
						@Override
						public void onClick(View v) {
							// TODO Auto-generated method stub
							Log.d("TAG","DATA");
						}
					});
				    
				    
					final ViewHolder holder = new ViewHolder();
					
					view.setClickable(true);
					holder.txtCodigo = (TextView) view.findViewById(R.id.txtCodigo);
					holder.txtNombre = (TextView) view.findViewById(R.id.txtNombre);
					
					holder.imgCuotaIndicador = (ImageView)view.findViewById(R.id.imgCuotaIndicador);
					holder.txtCuota = (TextView) view.findViewById(R.id.txtCuota);
					
					holder.imgEficienciaGlobal = (ImageView)view.findViewById(R.id.imgEficienciaGlobal);
					holder.txtEficienciaGlobal = (TextView) view.findViewById(R.id.txtEficienciaGlobal);
					
					
					holder.imgPlanVisitas = (ImageView)view.findViewById(R.id.imgPlanVisitas);
					holder.txtPlanVisitas = (TextView) view.findViewById(R.id.txtPlanVisitas);
					
					
					holder.imgEficienciaPreventa = (ImageView)view.findViewById(R.id.imgEficienciaPreVenta);
					holder.txtEficienciaPreventa = (TextView) view.findViewById(R.id.txtEficienciaPreVenta);
					

					holder.txtClientes = (TextView) view.findViewById(R.id.txtClientes);
					
					holder.txtVisitas = (TextView) view.findViewById(R.id.txtVisitas);
					
					holder.txtConPedido = (TextView)view.findViewById(R.id.txtConPedido);
					holder.txtSinPedido = (TextView) view.findViewById(R.id.txtSinPedido);
					
					holder.txtImporte = (TextView)view.findViewById(R.id.txtImporte);
					holder.txtTiempoEfectivo = (TextView) view.findViewById(R.id.txtTiempoEfectivo);
					
					
					
					
					view.setTag(holder);
					holder.txtCodigo.setTag(this.detalle.get(position));
				}else{
					view = convertView;
					((ViewHolder) view.getTag()).txtCodigo.setTag(this.detalle.get(position));
				}
				
				
				
				SupervisorTO supervisorTO = this.detalle.get(position);
				
				ViewHolder holder = (ViewHolder) view.getTag();
				holder.txtCodigo.setText(String.valueOf(supervisorTO.codigo));
				holder.txtNombre.setText(supervisorTO.nombre);
			
				 switch(Integer.parseInt(supervisorTO.cuotaColor))
			      {
			      	case IND_VERDE:
			      		holder.imgCuotaIndicador.setImageResource(R.drawable.icon_verde);
			    	  break;
			      	case IND_AMARRILLO:
			      		holder.imgCuotaIndicador.setImageResource(R.drawable.icon_amarrillo);
			      		break;
			      	case IND_ROJO:
			      		holder.imgCuotaIndicador.setImageResource(R.drawable.icon_rojo);
			      		break;	 
			       }
				 
				holder.txtCuota.setText(supervisorTO.cuota);
				
				holder.txtEficienciaGlobal.setText(supervisorTO.eficGlobal);
				
				 switch(Integer.parseInt(supervisorTO.eficGlobalColor))
			      {
			      	case IND_VERDE:
			      		holder.imgEficienciaGlobal.setImageResource(R.drawable.icon_verde);
			    	  break;
			      	case IND_AMARRILLO:
			      		holder.imgEficienciaGlobal.setImageResource(R.drawable.icon_amarrillo);
			      		break;
			      	case IND_ROJO:
			      		holder.imgEficienciaGlobal.setImageResource(R.drawable.icon_rojo);
			      		break;	 
			       }
				 
				 holder.txtPlanVisitas.setText(supervisorTO.planVisita);
				 switch(Integer.parseInt(supervisorTO.planVisitaColor))
			      {
			      	case IND_VERDE:
			      		holder.imgPlanVisitas.setImageResource(R.drawable.icon_verde);
			    	  break;
			      	case IND_AMARRILLO:
			      		holder.imgPlanVisitas.setImageResource(R.drawable.icon_amarrillo);
			      		break;
			      	case IND_ROJO:
			      		holder.imgPlanVisitas.setImageResource(R.drawable.icon_rojo);
			      		break;	 
			       }
				 
				 holder.txtEficienciaPreventa.setText(supervisorTO.eficPreventa);
					
				 switch(Integer.parseInt(supervisorTO.eficPreventaColor))
			      {
			      	case IND_VERDE:
			      		holder.imgEficienciaPreventa.setImageResource(R.drawable.icon_verde);
			    	  break;
			      	case IND_AMARRILLO:
			      		holder.imgEficienciaPreventa.setImageResource(R.drawable.icon_amarrillo);
			      		break;
			      	case IND_ROJO:
			      		holder.imgEficienciaPreventa.setImageResource(R.drawable.icon_rojo);
			      		break;	 
			       }
				 
				 holder.txtClientes.setText(supervisorTO.cantidadClientes);
				 holder.txtVisitas.setText(supervisorTO.visitas);
				 
				 holder.txtConPedido.setText(supervisorTO.conPedido);
				 holder.txtSinPedido.setText(supervisorTO.sinPedido);
				 
				 holder.txtImporte.setText(supervisorTO.importe);
				 holder.txtTiempoEfectivo.setText(supervisorTO.tiempoEfec);
				 
				
				return view;
				
			}
			
			private class ViewHolder {
				public TextView txtCodigo;
				public TextView txtNombre;
				
				public ImageView imgCuotaIndicador;
				public TextView txtCuota;
				
				public ImageView imgEficienciaGlobal;
				public TextView txtEficienciaGlobal;
				
				public ImageView imgPlanVisitas;
				public TextView txtPlanVisitas;
				
				
				public ImageView imgEficienciaPreventa;
				public TextView txtEficienciaPreventa;
				
			
				public TextView txtClientes;
				public TextView txtVisitas;
				
				public TextView txtConPedido;
				public TextView txtSinPedido;
				
				public TextView txtImporte;
				public TextView txtTiempoEfectivo;
				
		
			}
		   
	 }
}
