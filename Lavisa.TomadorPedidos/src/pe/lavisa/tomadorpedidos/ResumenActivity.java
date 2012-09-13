package pe.lavisa.tomadorpedidos;

import java.util.ArrayList;
import java.util.List;

import com.actionbarsherlock.view.MenuItem.OnMenuItemClickListener;

import pe.lavisa.tomadorpedidos.to.ResumenTO;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ResumenActivity extends net.msonic.lib.sherlock.ListActivityBase {

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        inicializarRecursos();
        
        setContentView(R.layout.resumen_activity);
        setTitle(R.string.resumen_activity_title);
        
        ResumenTO galonaje = new ResumenTO();
        galonaje.indicador=2;
        galonaje.descripcion="Galonaje";
        galonaje.actual=2537;
        galonaje.meta=5315;
        
        
        ResumenTO cobertura = new ResumenTO();
        cobertura.indicador=1;
        cobertura.descripcion="Cobertura";
        cobertura.actual=30;
        cobertura.meta=67;
        
        
        ResumenTO clientes = new ResumenTO();
        clientes.indicador=3;
        clientes.descripcion="Clientes Visitados";
        clientes.actual=30;
        clientes.meta=50;
        
        ResumenTO pedidosRegistrados = new ResumenTO();
        pedidosRegistrados.indicador=3;
        pedidosRegistrados.descripcion="Pedidos Registrados";
        pedidosRegistrados.actual=10;
        pedidosRegistrados.meta=100;
        
        
        ResumenTO encuestasRealizadas = new ResumenTO();
        encuestasRealizadas.indicador=1;
        encuestasRealizadas.descripcion="Encuestas Registrados";
        encuestasRealizadas.actual=10;
        encuestasRealizadas.meta=10;
        
        
        
        ArrayList<ResumenTO> indicadores = new ArrayList<ResumenTO>();
        
        indicadores.add(galonaje);
        indicadores.add(cobertura);
        indicadores.add(clientes);
        indicadores.add(pedidosRegistrados);
        indicadores.add(encuestasRealizadas);
        
        
        Resumen_Adapter adapterIndicadores = new Resumen_Adapter(this, indicadores);
		setListAdapter(adapterIndicadores);
		
        
    }
	
	
	@Override
	public boolean onCreateOptionsMenu(com.actionbarsherlock.view.Menu menu) {
		// TODO Auto-generated method stub
		
		getSupportMenuInflater().inflate(R.menu.resumen_activity_menu, menu);
		
		

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
            
        
		return true;
	
	}
	
	
	@Override
	public boolean onOptionsItemSelected(com.actionbarsherlock.view.MenuItem item) {
		switch(item.getItemId()){
			case R.id.mnuClientes:
				Intent intent = new Intent(this,BuscarClienteActivity.class);
				startActivity(intent);
				return true;
			default:
				return super.onOptionsItemSelected(item);
		}
		
	}
	
	public static class Resumen_Adapter extends ArrayAdapter<ResumenTO>{
			
		// pad with " " to the right to the given length (n)
		  public static String padRight(String s, int n) {
		    return String.format("%1$-" + n + "s", s);
		  }

		  // pad with " " to the left to the given length (n)
		  public static String padLeft(String s, int n) {
		    return String.format("%1$" + n + "s", s);
		  }
		  
		   private final List<ResumenTO> detalle;
		   private final Activity context;
		
		
		   
		   public Resumen_Adapter(Activity context,List<ResumenTO> detalle){
				super(context, R.layout.resumen_content, detalle);
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
			
			public ResumenTO getItem(int position) {
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
					view = inflator.inflate(R.layout.resumen_content, null);
					
					final ViewHolder holder = new ViewHolder();
					
					holder.imgIndicador = (ImageView)view.findViewById(R.id.imgIndicador);
					holder.txtDescripcion = (TextView) view.findViewById(R.id.txtDescripcion);
					holder.txtActual = (TextView)view.findViewById(R.id.txtActual);
					holder.txtMeta = (TextView)view.findViewById(R.id.txtMeta);
					
					view.setTag(holder);
					holder.txtDescripcion.setTag(this.detalle.get(position));
					
				}else{
					view = convertView;
					((ViewHolder) view.getTag()).txtDescripcion.setTag(this.detalle.get(position));
				}
				
				ViewHolder holder = (ViewHolder) view.getTag();
				
				holder.txtDescripcion.setText(padRight(this.detalle.get(position).descripcion,30));
				holder.txtActual.setText(padLeft(String.valueOf(this.detalle.get(position).actual),10));
				holder.txtMeta.setText(padLeft(String.valueOf(this.detalle.get(position).meta),10));
				
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
				return view;
				
			}
			
			
			private class ViewHolder {
				 public ImageView imgIndicador;
				 public TextView txtDescripcion;
				 public TextView txtActual;
				 public TextView txtMeta;
					 
			 }
			
	}

}
