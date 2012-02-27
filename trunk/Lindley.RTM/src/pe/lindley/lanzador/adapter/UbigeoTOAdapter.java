package pe.lindley.lanzador.adapter;

import java.util.ArrayList;

import pe.lindley.lanzador.to.UbigeoTO;
import pe.lindley.util.TOSpinnerAdapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class UbigeoTOAdapter extends TOSpinnerAdapter<UbigeoTO> {

	public UbigeoTOAdapter(Context context, ArrayList<UbigeoTO> data) {
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
				for (UbigeoTO ubigeoTO : data) {
					if (ubigeoTO.getCodigo()
							.equalsIgnoreCase(codigo.toString())) {
						index = data.indexOf(ubigeoTO);
					}
				}
			}

		}
		return index;
	}

}
