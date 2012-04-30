package pe.lindley.mmil.titanium;

import java.util.ArrayList;

import pe.lindley.mmil.titanium.to.PizarraDataTO;
import pe.lindley.mmil.titanium.to.PizarraDetalleTO;
import pe.lindley.mmil.titanium.ws.service.MostrarPizarraProxy;
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
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import net.msonic.lib.ActivityBase;

public class TableroActivity extends ActivityBase {
	
	@InjectView(R.id.actionBar)  		ActionBar 	mActionBar;
	@InjectView(R.id.txtSupervisores)	TextView	txtSupervisores;
	@InjectView(R.id.txtTeleventa)		TextView	txtTeleventa;
	@InjectView(R.id.lstSupervisores) 	ListView 	lstSupervisores;
	@InjectView(R.id.lstTeleventa) 		ListView 	lstTeleventa;
	
	@Inject MostrarPizarraProxy mostrarPizarraProxy;
	private String nombre_cda;
	private String codigo_cda;
	
	  /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        inicializarRecursos();
        
        setContentView(R.layout.tablero_activity);
        
        mActionBar.setHomeLogo(R.drawable.header_logo);
        mActionBar.setTitle(R.string.tablero_activity_title);
        
        codigo_cda="C4";
        
        processAsync();
    }

	@Override
	protected void process() {
		// TODO Auto-generated method stub
		mostrarPizarraProxy.codigoDeposito=codigo_cda;
		mostrarPizarraProxy.execute();
		
	}
    
	@Override
	protected void processOk() {
		String message;
		
		boolean isExito = mostrarPizarraProxy.isExito();
		if (isExito) {
			int status = mostrarPizarraProxy.getResponse().getStatus();
			if (status == 0) {
				
				PizarraDataTO pizarra = mostrarPizarraProxy.getResponse().pizarra;
				nombre_cda=pizarra.cda;
				mActionBar.setSubTitle(nombre_cda);
				
				txtSupervisores.setText(pizarra.nombreSupervisor + " Cantidad:" + String.valueOf(pizarra.cantidadSupervisor));
				txtTeleventa.setText(pizarra.nombreTeleventa + " Cantidad:" + String.valueOf(pizarra.cantidadTeleventa));
				
				Tablero_Adapter adapterSupervisor = new Tablero_Adapter(this, pizarra.detalleSupervisor);
				lstSupervisores.setAdapter(adapterSupervisor);
				
				Tablero_Adapter adapterTelevente = new Tablero_Adapter(this, pizarra.detalleTeleventas);
				lstTeleventa.setAdapter(adapterTelevente);
				
				super.processOk();
			}else{
				message = mostrarPizarraProxy.getResponse().getDescripcion();
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
		if(mostrarPizarraProxy.getResponse()!=null){
			String error = mostrarPizarraProxy.getResponse().getDescripcion();
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
		   menuInflater.inflate(R.menu.tablero_menu, menu);
		   
		return super.onCreateOptionsMenu(menu);
	}



	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		// TODO Auto-generated method stub
		if(item.getItemId()==R.id.mnuSupervisor){
			if(nombre_cda!=null){
				Intent intent = new Intent(this,ListaSupervisorActivity.class);
				intent.putExtra(ListaSupervisorActivity.NOMBRE_CDA_KEY, nombre_cda);
				intent.putExtra(ListaSupervisorActivity.CODIGO_CDA_KEY, codigo_cda);
				startActivity(intent);
			}
		}
		return super.onMenuItemSelected(featureId, item);
	}






	public static class Tablero_Adapter extends ArrayAdapter<PizarraDetalleTO>{
		   private final ArrayList<PizarraDetalleTO> detalle;
		   private final Activity context;
		   
		   private static final int IND_VERDE = 0;
		   private static final int IND_AMARRILLO = 1;
		   private static final int IND_ROJO = 2;
			
			public Tablero_Adapter(Activity context,ArrayList<PizarraDetalleTO> detalle){
				super(context, R.layout.tablero_content, detalle);
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
			
			public PizarraDetalleTO getItem(int position) {
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
					view = inflator.inflate(R.layout.tablero_content, null);
					
					final ViewHolder holder = new ViewHolder();
					
					holder.imgIndicador = (ImageView)view.findViewById(R.id.imgIndicador);
					holder.txtTitulo = (TextView) view.findViewById(R.id.txtTitulo);
					holder.txtRatio = (TextView)view.findViewById(R.id.txtRatio);
					
					view.setTag(holder);
					holder.txtTitulo.setTag(this.detalle.get(position));
					
				}else{
					view = convertView;
					((ViewHolder) view.getTag()).txtTitulo.setTag(this.detalle.get(position));
				}
				
				ViewHolder holder = (ViewHolder) view.getTag();
				
				holder.txtTitulo.setText(this.detalle.get(position).indicador);
				holder.txtRatio.setText(this.detalle.get(position).valorReal + "/" +  this.detalle.get(position).valorEsperado);
				
				 switch(Integer.parseInt(this.detalle.get(position).color))
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
				 public TextView txtTitulo;
				 public TextView txtRatio;
					 
			 }
	   }
}
