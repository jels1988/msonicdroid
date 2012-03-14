package pe.pae.encuesta.activity;

import java.util.ArrayList;

import pe.pae.encuesta.R;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.RadioButton;

public class SingleOpcion_Adapter extends ArrayAdapter<OpcionTO> {
	
	private final ArrayList<OpcionTO> opciones;
	private final Activity context;
	private int posicionSeleccionada = -1;
	private RadioButton radioButton=null;
	
	public SingleOpcion_Adapter(Activity context,ArrayList<OpcionTO> opciones){
		super(context, R.layout.pregunta_single_content, opciones);
		this.opciones = opciones;
		this.context = context;
	}
	
	public int getCount() {
		// TODO Auto-generated method stub
		if (opciones == null) {
			return 0;
		} else {
			return opciones.size();
		}
	}

	public OpcionTO getItem(int position) {
		// TODO Auto-generated method stub
		return this.opciones.get(position);
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
			view = inflator.inflate(R.layout.pregunta_single_content, null);
			
			final ViewHolder holder = new ViewHolder();
			final int pos = position;
			holder.rdbSeleccion = (RadioButton) view.findViewById(R.id.rdOption);
			
			holder.rdbSeleccion.setOnClickListener(new OnClickListener() {
				
				public void onClick(View v) {
					// TODO Auto-generated method stub
					if(posicionSeleccionada>-1){
						opciones.get(posicionSeleccionada).seleccionado=false;
						if(radioButton!=null){
						radioButton.setChecked(false);
						}
					}
					
					radioButton = (RadioButton)v;
					posicionSeleccionada=pos;
					OpcionTO opcion = (OpcionTO) holder.rdbSeleccion.getTag();
					opcion.seleccionado=radioButton.isChecked();
				}
			});
			holder.rdbSeleccion.setOnCheckedChangeListener(new OnCheckedChangeListener() {
				
				public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
					// TODO Auto-generated method stub
					
				}
			});
			
			view.setTag(holder);
			holder.rdbSeleccion.setTag(this.opciones.get(position));
		}else{
			view = convertView;
			((ViewHolder) view.getTag()).rdbSeleccion.setTag(this.opciones.get(position));
		}
		
		ViewHolder holder = (ViewHolder) view.getTag();
		holder.rdbSeleccion.setText(this.opciones.get(position).descripcion);
		holder.rdbSeleccion.setChecked(this.opciones.get(position).seleccionado);
		
		return view;
	}

	class ViewHolder {
		 public RadioButton rdbSeleccion;
		 
	 }
}