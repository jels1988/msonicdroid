package pe.lindley.plandesarrollo.adapter;

import java.util.ArrayList;
import pe.lindley.plandesarrollo.to.ParametroTO;
import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import pe.lindley.util.TOSpinnerAdapter;

public class ParametroTOAdapter  extends TOSpinnerAdapter<ParametroTO>{
	
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
