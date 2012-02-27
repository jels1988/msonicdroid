package pe.lindley.ficha.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import pe.lindley.ficha.to.OpcionMultipleTO;
import pe.lindley.util.TOSpinnerAdapter;

public class ParametroTOAdapter extends TOSpinnerAdapter<OpcionMultipleTO>{
	
	public ParametroTOAdapter(Context context, ArrayList<OpcionMultipleTO> data) {
		super(context, data);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected View getViewTO(int position, View convertView, ViewGroup parent,
			Context context) {
		// TODO Auto-generated method stub
		TextView v = new TextView(this.context);
		v.setTextColor(Color.BLACK);
		v.setText(data.get(position).getDescripcion());
		return v;
	}

	public int findByValue(CharSequence codigo) {
		int index = 0;
		if (codigo != null) {
			if (!codigo.toString().trim().equalsIgnoreCase("")) {
				for (OpcionMultipleTO opcionMultipleTO : data) {
					if (opcionMultipleTO.getCodigo().equalsIgnoreCase(codigo.toString())) {
						index = data.indexOf(opcionMultipleTO);
						break;
					}
				}
			}

		}
		return index;
	}

}
