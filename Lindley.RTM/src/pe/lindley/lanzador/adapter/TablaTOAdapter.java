package pe.lindley.lanzador.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Color;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import pe.lindley.lanzador.to.TablaTO;
import pe.lindley.util.TOSpinnerAdapter;

public class TablaTOAdapter extends TOSpinnerAdapter<TablaTO> {

	public TablaTOAdapter(Context context, ArrayList<TablaTO> data) {
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
		v.setTextSize(TypedValue.COMPLEX_UNIT_DIP ,15);
		return v;
	}

	public int findByValue(CharSequence codigo) {
		int index = 0;
		if (codigo != null) {
			if (!codigo.toString().trim().equalsIgnoreCase("")) {
				for (TablaTO tablaTO : data) {
					if (tablaTO.getCodigo().equalsIgnoreCase(codigo.toString())) {
						index = data.indexOf(tablaTO);
					}
				}
			}
		}
		return index;
	}
}
