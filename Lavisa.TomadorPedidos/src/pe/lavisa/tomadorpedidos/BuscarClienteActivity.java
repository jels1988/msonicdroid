package pe.lavisa.tomadorpedidos;


import java.util.ArrayList;
import java.util.List;

import pe.lavisa.tomadorpedidos.to.ClienteTO;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class BuscarClienteActivity extends net.msonic.lib.sherlock.ListActivityBase {
	
	BuscarCliente_Adapter adapter;

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        inicializarRecursos();
        this.validarConexionInternet=false;
        setContentView(R.layout.buscarcliente_activity);
        setTitle(R.string.buscarcliente_activity_title);
        
    }
	
	public void btnbuscar_onclick(View v){
		processAsync();
	}
	
	@Override
	protected void process() {
		ClienteTO clienteTO = new ClienteTO();
		clienteTO.descripcion="M-Sonic EIRL";
		clienteTO.documento="20543400361";
		clienteTO.deuda=1200;
		clienteTO.galonesActual=423;
		clienteTO.galonesMeta=452;
		clienteTO.galonesIndicador=Util.IND_VERDE;
		clienteTO.creditoLimite=2000;
		clienteTO.creditoCartera=1791.12;
		clienteTO.creditoDisponible=1253.17;
		
		ClienteTO clienteTO1 = new ClienteTO();
		clienteTO1.descripcion="Lau Vidal SAC";
		clienteTO1.documento="20543400361";
		clienteTO1.deuda=1200;
		clienteTO1.galonesActual=423;
		clienteTO1.galonesMeta=452;
		clienteTO1.galonesIndicador=Util.IND_ROJO;
		clienteTO1.creditoLimite=2000;
		clienteTO1.creditoCartera=1791.12;
		clienteTO1.creditoDisponible=1253.17;
		
		ClienteTO clienteTO2 = new ClienteTO();
		clienteTO2.descripcion="Corporaci—n Lindley";
		clienteTO2.documento="20543400361";
		clienteTO2.deuda=110;
		clienteTO2.galonesActual=423;
		clienteTO2.galonesMeta=452;
		clienteTO2.galonesIndicador=Util.IND_AMARRILLO;
		clienteTO2.creditoLimite=3000;
		clienteTO2.creditoCartera=1230.12;
		clienteTO2.creditoDisponible=1500.17;
		
		
		ArrayList<ClienteTO> lista = new ArrayList<ClienteTO>();
		lista.add(clienteTO);
		lista.add(clienteTO1);
		lista.add(clienteTO2);
		
		adapter = new BuscarCliente_Adapter(this, lista);
		
	}
	
	@Override
	protected void processOk() {
		setListAdapter(adapter);
		super.processOk();
	}
	
	public static class BuscarCliente_Adapter extends ArrayAdapter<ClienteTO>{
		   
		  private final List<ClienteTO> detalle;
		   private final Activity context;
		   
		   public BuscarCliente_Adapter(Activity context,List<ClienteTO> detalle){
				super(context, R.layout.buscarcliente_content, detalle);
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
			
			public ClienteTO getItem(int position) {
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
					view = inflator.inflate(R.layout.buscarcliente_content, null);
					
					final ViewHolder holder = new ViewHolder();
					
					holder.txtDescripcion = (TextView) view.findViewById(R.id.txtDescripcion);
					holder.txtDocumento = (TextView) view.findViewById(R.id.txtDocumento);
					holder.txtDeuda = (TextView) view.findViewById(R.id.txtDeuda);
					holder.txtGalones = (TextView) view.findViewById(R.id.txtGalones);
					holder.imgGalones = (ImageView) view.findViewById(R.id.imgGalones);
					holder.txtCreditoLimite = (TextView) view.findViewById(R.id.txtCreditoLimite);
					holder.txtCreditoDisponible = (TextView) view.findViewById(R.id.txtCreditoDisponible);
					holder.txtCreditoCartera = (TextView) view.findViewById(R.id.txtCreditoCartera);
					holder.txtTotalVencido = (TextView) view.findViewById(R.id.txtTotalVencido);
					holder.btnAddPedido = (Button) view.findViewById(R.id.btnAddPedido);
					holder.btnAddPedido.setOnClickListener(new OnClickListener() {
						
						@Override
						public void onClick(View v) {
							// TODO Auto-generated method stub
							ClienteTO clienteTO = (ClienteTO) holder.txtDescripcion.getTag();
							Intent intent = new Intent(context,PedidoActivity.class);
							
							intent.putExtra(PedidoActivity.CLIENTE_CODIGO_KEY, "001");
							intent.putExtra(PedidoActivity.CLIENTE_DESCRIPCION_KEY, clienteTO.descripcion);
							
							context.startActivity(intent);
						}
					});
					
					view.setTag(holder);
					holder.txtDescripcion.setTag(this.detalle.get(position));
					
				}else{
					view = convertView;
					((ViewHolder) view.getTag()).txtDescripcion.setTag(this.detalle.get(position));
				}
				
				ViewHolder holder = (ViewHolder) view.getTag();
				
				holder.txtDescripcion.setText(this.detalle.get(position).descripcion);
				holder.txtDocumento.setText(this.detalle.get(position).documento);
				holder.txtDeuda.setText(String.format("US$ %s",this.detalle.get(position).deuda));
				holder.txtGalones.setText(String.format("%s / %S",this.detalle.get(position).galonesActual,this.detalle.get(position).galonesMeta));
				
				holder.imgGalones.setImageResource(R.drawable.icon_white);
				
				 switch(this.detalle.get(position).galonesIndicador)
			      {
			      	case Util.IND_VERDE:
			      		holder.imgGalones.setImageResource(R.drawable.icon_verde);
			    	  break;
			      	case Util.IND_AMARRILLO:
			      		holder.imgGalones.setImageResource(R.drawable.icon_amarrillo);
			      		break;
			      	case Util.IND_ROJO:
			      		holder.imgGalones.setImageResource(R.drawable.icon_rojo);
			      		break;	 
			       }
				 
				holder.txtCreditoLimite.setText(String.format("US$ %s",this.detalle.get(position).creditoLimite));
				holder.txtCreditoDisponible.setText(String.format("US$ %s",this.detalle.get(position).creditoDisponible));
				holder.txtCreditoCartera.setText(String.format("US$ %s",this.detalle.get(position).creditoCartera));
				holder.txtTotalVencido.setText(String.format("US$ %s",this.detalle.get(position).totalVencido));
				
				return view;
				
			}
			private class ViewHolder {
				 public TextView txtDescripcion;
				 public TextView txtDocumento;
				 public TextView txtDeuda;
				 public TextView txtGalones;
				 public ImageView imgGalones;
				 public TextView txtCreditoLimite;
				 public TextView txtCreditoDisponible;
				 public TextView txtCreditoCartera;
				 public TextView txtTotalVencido;
				 public Button btnAddPedido;
				
			 }
	}
	
	  
	   
	   
}
