package lindley.desarrolloxcliente.activity;

import java.util.ArrayList;

import lindley.desarrolloxcliente.R;
import lindley.desarrolloxcliente.to.PosicionCompromisoTO;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

public class CompromisoPosicionClose_Adapter extends ArrayAdapter<PosicionCompromisoTO> {

	private final ArrayList<PosicionCompromisoTO> posiciones;
	private final Activity context;
	
	
	public CompromisoPosicionClose_Adapter(Activity context,ArrayList<PosicionCompromisoTO> posiciones){
		super(context, R.layout.consultarposicioncompromisoclose_content, posiciones);
		this.posiciones = posiciones;
		this.context = context;
	}
	
	public int getCount() {
		// TODO Auto-generated method stub
		if (posiciones == null) {
			return 0;
		} else {
			return posiciones.size();
		}
	}

	public PosicionCompromisoTO getItem(int position) {
		// TODO Auto-generated method stub
		return this.posiciones.get(position);
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
			view = inflator.inflate(R.layout.consultarposicioncompromisoclose_content, null);
			
			final ViewHolder holder = new ViewHolder();
			
			holder.txViewVariable = (TextView) view.findViewById(R.id.txViewVariable);
			holder.txViewRed = (TextView) view.findViewById(R.id.txViewRed);
			holder.txViewMaximo = (TextView) view.findViewById(R.id.txViewMaximo);
			holder.txViewDiferencia = (TextView) view.findViewById(R.id.txViewDiferencia);
			holder.txViewPuntos = (TextView) view.findViewById(R.id.txViewPuntos);
			holder.txViewAccComp = (TextView) view.findViewById(R.id.txViewAccComp);
			holder.txViewFecComp = (TextView) view.findViewById(R.id.txViewFecComp);
			holder.txViewCnfComp = (TextView) view.findViewById(R.id.txViewCnfComp);
			
			holder.btnFotoInicial = (Button) view.findViewById(R.id.btnFotoInicial);
			holder.btnFotoExito = (Button) view.findViewById(R.id.btnFotoExito);
			holder.btnFotoFinal = (Button) view.findViewById(R.id.btnFotoFinal);
			
			/*holder.chkSeleccion.setOnCheckedChangeListener(new OnCheckedChangeListener() {
				
				public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
					// TODO Auto-generated method stub
					OpcionTO opcion = (OpcionTO) holder.chkSeleccion.getTag();
					opcion.seleccionado=isChecked;
				}
			});*/
			
			view.setTag(holder);
			//holder.chkSeleccion.setTag(this.opciones.get(position));
		}else{
			view = convertView;
			//((ViewHolder) view.getTag()).chkSeleccion.setTag(this.opciones.get(position));
		}
		
		ViewHolder holder = (ViewHolder) view.getTag();
		holder.txViewVariable.setText(this.posiciones.get(position).getDescripcionVariable());
		holder.txViewRed.setText(this.posiciones.get(position).getRed());
		holder.txViewMaximo.setText(this.posiciones.get(position).getPtoMaximo());
		holder.txViewDiferencia.setText(this.posiciones.get(position).getDiferencia());
		holder.txViewPuntos.setText(this.posiciones.get(position).getPuntosSugeridos());
		holder.txViewAccComp.setText(this.posiciones.get(position).getAccionCompromiso());
		holder.txViewFecComp.setText(this.posiciones.get(position).getFechaCompromiso());
		holder.txViewCnfComp.setText(this.posiciones.get(position).getConfirmacion());
				
		return view;
	}

	class ViewHolder {
		 public TextView txViewVariable;
		 public TextView txViewRed;
		 public TextView txViewMaximo;
		 public TextView txViewDiferencia;
		 public TextView txViewPuntos;
		 public Button btnFotoInicial;
		 public Button btnFotoExito;
		 public TextView txViewAccComp;
		 public TextView txViewFecComp;
		 public Button btnFotoFinal;
		 public TextView txViewCnfComp;
	 }
}
