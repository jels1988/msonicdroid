package pe.lindley.puntointeres.adapter;

import java.util.ArrayList;

import pe.lindley.puntointeres.to.ParametroTO;
import pe.lindley.util.TOSpinnerAdapter;
import android.content.Context;
import android.graphics.Color;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ParametroTOAdapter extends TOSpinnerAdapter<ParametroTO>{
	
	public ParametroTOAdapter(Context context, ArrayList<ParametroTO> data) {
		super(context, data);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected View getViewTO(int position, View convertView, ViewGroup parent,
			Context context) {
		// TODO Auto-generated method stub
		TextView v = new TextView(this.context);
		v.setTextColor(Color.BLACK);
		v.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
		v.setHeight(50);
		v.setGravity(Gravity.CENTER_VERTICAL);
		v.setText(data.get(position).getDescripcion());
		return v;
	}

	public int findByValue(CharSequence codigo) {
		int index = 0;
		if (codigo != null) {
			if (!codigo.toString().trim().equalsIgnoreCase("")) {
				for (ParametroTO parametroTO : data) {
					if (parametroTO.getCodigo().equalsIgnoreCase(codigo.toString())) {
						index = data.indexOf(parametroTO);
						break;
					}
				}
			}

		}
		return index;
	}
}