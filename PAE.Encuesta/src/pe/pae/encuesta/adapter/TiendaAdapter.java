package pe.pae.encuesta.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import pe.pae.encuesta.to.TiendaTO;
import net.msonic.lib.TOSpinnerAdapter;

public class TiendaAdapter extends TOSpinnerAdapter<TiendaTO> {

	public TiendaAdapter(Context context, ArrayList<TiendaTO> data) {
		super(context, data);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected View getViewTO(int position, View convertView, ViewGroup parent,
			Context context) {
		// TODO Auto-generated method stub
		TextView v = new TextView(this.context);
		v.setTextColor(Color.BLACK);
		v.setText(data.get(position).nombre);
		return v;
	}

}
