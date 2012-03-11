package pe.pae.encuesta.activity;

import java.util.ArrayList;

import pe.pae.encuesta.R;
import pe.pae.encuesta.to.OpcionTO;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;

public class MultipleOpcion_Adapter extends ArrayAdapter<OpcionTO> {
	
	private final ArrayList<OpcionTO> opciones;
	private final Activity context;
	
	public MultipleOpcion_Adapter(Activity context,ArrayList<OpcionTO> opciones){
		super(context, R.layout.pregunta_multi_content, opciones);
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
			view = inflator.inflate(R.layout.pregunta_multi_content, null);
			
			final ViewHolder holder = new ViewHolder();
			
			holder.chkSeleccion = (CheckBox) view.findViewById(R.id.chkOpcion);
			
			holder.chkSeleccion.setOnCheckedChangeListener(new OnCheckedChangeListener() {
				
				public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
					// TODO Auto-generated method stub
					OpcionTO opcion = (OpcionTO) holder.chkSeleccion.getTag();
					opcion.seleccionado=isChecked;
				}
			});
			
			view.setTag(holder);
			holder.chkSeleccion.setTag(this.opciones.get(position));
		}else{
			view = convertView;
			((ViewHolder) view.getTag()).chkSeleccion.setTag(this.opciones.get(position));
		}
		
		ViewHolder holder = (ViewHolder) view.getTag();
		holder.chkSeleccion.setText(this.opciones.get(position).descripcion);
		holder.chkSeleccion.setChecked(this.opciones.get(position).seleccionado);
		
		return view;
	}

	class ViewHolder {
		 public CheckBox chkSeleccion;
		 
	 }
}

 
